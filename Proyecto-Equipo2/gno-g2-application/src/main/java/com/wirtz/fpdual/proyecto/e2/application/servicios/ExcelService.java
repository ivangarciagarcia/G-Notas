package com.wirtz.fpdual.proyecto.e2.application.servicios;

import com.github.miachm.sods.Range;
import com.github.miachm.sods.Sheet;
import com.github.miachm.sods.SpreadSheet;
import com.wirtz.fpdual.proyecto.e2.domain.dto.*;
import com.wirtz.fpdual.proyecto.e2.application.util.MathService;
import com.wirtz.fpdual.proyecto.e2.domain.repository.*;
import com.wirtz.fpdual.proyecto.e2.domain.service.ExcelServiceInterface;
import org.apache.commons.math3.util.MathUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class ExcelService implements ExcelServiceInterface {

    @Autowired
    private CourseModuleRepository courseModuleRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private MathService mathUtil;
    
    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private CourseRepository courseRepository;

    private XSSFSheet sheet;
    private XSSFWorkbook workbook;

    private List<StudentDTO> listStudents;

    private List<EvaluationDTO> evaluations;

    private Integer courseModuleId;




    @Override
    public String postExcel(MultipartFile file) throws Exception {
        ArrayList<ArrayList<String>> data = fetchData(file);

        //REFERENCES ON EXCEL
        Integer nameRef = 0;
        Integer apellidoRef = 1;
        Integer numeroIdRef = 2;
        Integer institucionRef = 3;
        Integer departamentoRef = 4;
        Integer correoRef = 5;
        Integer tarefaRefStart = 6;
        Integer tarefaRefEnd = data.get(0).size() - 2;
        Integer totalCursoRef = data.get(0).size() - 1;

        ArrayList<String> referencesNames = trimFinalPart(data.get(0));

        for (ArrayList<String> fila: data.subList(1, data.size())) {

            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setStudentName(fila.get(nameRef));
            studentDTO.setStudentLastName(fila.get(apellidoRef));
            studentDTO.setStudentEmail(fila.get(correoRef));



            //Mirar que el alumno exista
            try{
                studentRepository.getStudentByEmail(fila.get(correoRef));
            } catch (EmptyResultDataAccessException e){
                studentRepository.createStudent(studentDTO);
            }
            studentDTO.setStudentId(studentRepository.getStudentByEmail(studentDTO.getStudentEmail()).getStudentId());

            //Course_Module && Evaluation
            CourseModuleDTO courseModuleDTO = new CourseModuleDTO();
            EvaluationDTO evaluationDTO = new EvaluationDTO();

            boolean ACPFound = false;
            boolean PTEFound = false;
            for(String strACP: referencesNames.subList(tarefaRefStart, tarefaRefEnd)){
                Integer startingIndex = tarefaRefStart;

                if(strACP.contains("ACP")){
                    ACPFound = true;
                    String[] strSplitACP = strACP.split("_");

                    //Course
                    CourseDTO courseDTO = new CourseDTO();
                    courseDTO.setCourseName(takeOutCourse(strSplitACP[strSplitACP.length-2]));
                    try{
                        courseRepository.selectIdFromCourseObject(courseDTO);
                    }catch(IndexOutOfBoundsException e){
                        courseRepository.createCourse(courseDTO);
                    }
                    courseModuleDTO.setCourseId(courseRepository.selectIdFromCourseObject(courseDTO).getCourseId());
                   // courseRepository.selectIdFromCourseObject(courseDTO);

                    //Module
                    ModuleDTO moduleDTO = new ModuleDTO();
                    moduleDTO.setModuleName(strSplitACP[strSplitACP.length-1]);
                    try{
                        moduleRepository.selectIdFromModuleObject(moduleDTO);
                    }catch (IndexOutOfBoundsException e){
                        moduleRepository.insertModule(moduleDTO);
                    }
                    courseModuleDTO.setModuleId(moduleRepository.selectIdFromModuleObject(moduleDTO));

                    //School
                    SchoolDTO schoolDTO = new SchoolDTO();
                    schoolDTO.setSchoolName(strSplitACP[2]);
                    try{
                        schoolRepository.getSchoolIdByObject(schoolDTO);
                    }catch (IndexOutOfBoundsException e){
                        schoolRepository.createSchool(schoolDTO);
                    }
                    courseModuleDTO.setSchoolId(schoolRepository.getSchoolIdByObject(schoolDTO));

                    //CourseModuleGrade
                    courseModuleDTO.setCourseModuleGrade(takeOutCourseNum(strSplitACP[strSplitACP.length-2]));

                    //schoolYear
                    courseModuleDTO.setSchoolYear(Integer.valueOf(strSplitACP[1]));

                    //TeacherId
                    TeacherDTO teacherDTO = new TeacherDTO();
                    teacherDTO.setTeacherEmail(strSplitACP[strSplitACP.length-3]);
                    try{
                        teacherRepository.getTeacherByEmail(strSplitACP[strSplitACP.length-3]);
                    }catch (IndexOutOfBoundsException e){
                        teacherRepository.insertTeacher(teacherDTO);
                    }
                    courseModuleDTO.setTeacherId(teacherRepository.getTeacherByEmail(strSplitACP[strSplitACP.length-3]).getTeacherId());

                    try{
                        courseModuleRepository.selectIdFromCourseModuleObject(courseModuleDTO);
                    }catch (IndexOutOfBoundsException e){
                        courseModuleRepository.insertCourseModule(courseModuleDTO);
                    }
                    courseModuleDTO.setCourseModuleId(courseModuleRepository.selectIdFromCourseModuleObject(courseModuleDTO));

                    for (String strPTE : referencesNames.subList(startingIndex, tarefaRefEnd)) {
                        //Evaluation
                        if(strPTE.contains("PTE")){
                            PTEFound = true;
                            String[] strSplitPTE = strPTE.split("_");
                            //Evaluation_type
                            evaluationDTO.setEvaluationType(takeOutNumberOfEval(strSplitPTE[1]));
                            //EvaluationExamsPercentage
                            evaluationDTO.setExamsPercentage(Integer.parseInt(strSplitPTE[strSplitPTE.length - 3]));
                            //Evaluation_percentage
                            evaluationDTO.setEvaluationPercentage(Integer.parseInt(strSplitPTE[strSplitPTE.length - 1]));
                            //course_module_id
                            evaluationDTO.setCourseModuleId(courseModuleDTO.getCourseModuleId());

                            try{
                                evaluationRepository.selectIdFromEvaluationObject(evaluationDTO).get(0).getEvaluationId();
                            }catch(IndexOutOfBoundsException e){
                                evaluationRepository.createEvaluation(evaluationDTO);
                            }
                            evaluationDTO.setEvaluationId(evaluationRepository.selectIdFromEvaluationObject(evaluationDTO).get(0).getEvaluationId());
                            break;
                        }
                    }
                    break;
                }
                startingIndex++;

            }

            if (!ACPFound){
                throw new Exception("Formato de Excel Incorrecto, ACP no encontrado");
            }

            if (!PTEFound){
                throw new Exception("Formato de Excel incorrecto, PTE no encontrado");
            }

            try{
                StudentDTO student = studentRepository.getListStudentsByCourseModuleId(courseModuleDTO.getCourseModuleId()).stream()
                        .filter(studentFilter -> (studentFilter.getStudentId().equals(studentDTO.getStudentId())))
                        .toList()
                        .get(0);
            }catch (IndexOutOfBoundsException e){
                studentRepository.insertStudentCourseModule(studentDTO.getStudentId(), courseModuleDTO.getCourseModuleId());
            }


            //Score
            for (Integer i = tarefaRefStart; i < tarefaRefEnd; i++){
                /*Actual tarefa*/
                String toWorkName = referencesNames.get(i);

                /*Valor Numerico*/
                String toWorkValue = fila.get(i);

                ScoreDTO scoreDTO = new ScoreDTO();
                if(isScoreValid(toWorkName)){
                    /*Nombre de la tarea dividida en "_"*/
                    String[] tarefaSplit = toWorkName.split("_");

                    //Score_Name
                    scoreDTO.setScoreName(toWorkName);

                    //Score_Number
                    if(toWorkValue.contains("-")){
                        scoreDTO.setScoreNumber(Float.valueOf(0));
                    } else{
                        scoreDTO.setScoreNumber(Float.valueOf(toWorkValue));
                    }

                    //Score_Type && Score_Percentage
                    if(toWorkName.contains("EXAMEN")){
                        if(toWorkName.contains("TEORICO")){
                            scoreDTO.setScoreType("TEORICO");
                            scoreDTO.setScoreVersion(parseScoreVersion(true, toWorkName));
                        } else{
                            scoreDTO.setScoreType("PRACTICA");
                            scoreDTO.setScoreVersion(parseScoreVersion(true, toWorkName));
                        }

                        scoreDTO.setScorePercentage(Integer.valueOf(tarefaSplit[tarefaSplit.length-1]));
                    }else{
                        scoreDTO.setScoreType("TAREA");
                        scoreDTO.setScorePercentage(100);
                        scoreDTO.setScoreVersion(parseScoreVersion(false, toWorkName));

                    }

                    //EvaluationId
                    scoreDTO.setEvaluationId(evaluationDTO.getEvaluationId());

                    //StudentId
                    scoreDTO.setStudentId(studentDTO.getStudentId());


                    try{
                        scoreRepository.getScoreIdFromScoreMethod(scoreDTO);
                    }catch (IndexOutOfBoundsException e){
                        scoreRepository.createScore(scoreDTO);
                    }
                    scoreDTO.setScoreId(scoreRepository.getScoreIdFromScoreMethod(scoreDTO));
                    scoreRepository.updateScoreNumberById(scoreDTO);
                }
            }
        }

        return "¡Funciona!";
    }

    private ArrayList<ArrayList<String>> fetchData(MultipartFile file){
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        try {

            InputStream inputStream = file.getInputStream();
            SpreadSheet spread = new SpreadSheet(inputStream);
            List<Sheet> sheets = spread.getSheets();
            Integer columnNum = 0;
            Integer rowNum = 0;


            for (Sheet sheet : sheets) {
                Range range = sheet.getDataRange();
           /*     //LIMITES

                //MIRAR EL LIMITE DE COLUMNAS
                Integer i = 0;
                Boolean isFilledColumn = true;
                do{
                    String toAnaliceColumns = "" + range.getCell(0,i).getValue();
                    if(toAnaliceColumns.equals("null") || toAnaliceColumns.equals("")){
                        isFilledColumn = false;
                        columnNum = i;
                    } else{
                        isFilledColumn = true;
                    }
                    i++;
                }while(isFilledColumn);

                //MIRAR EL LIMITE DE FILAS
                Integer j = 0;
                Boolean isFilledRow = true;
                do{
                    String toAnaliceRows = "" + range.getCell(j, 0).getValue();
                    if(toAnaliceRows.equals("null") || toAnaliceRows.equals("")){
                        isFilledRow = false;
                        rowNum = j;
                    } else{
                        isFilledRow = true;
                    }
                    j++;
                }while(isFilledRow);
*/
                //METER DATOS EN LISTAS

                for(Integer index = 0;index < sheet.getMaxRows() ; index++ ){
                    ArrayList<String> toAdd = new ArrayList<String>();
                    for(Integer indexj = 0;indexj < sheet.getMaxColumns() ; indexj++ ){
                        String value = "" + range.getCell(index, indexj).getValue();
                        toAdd.add(value);
                    }
                    result.add(toAdd);
                }

            }
        } catch (Exception e){
            e.printStackTrace();

        }
        return result;
    }

    private ArrayList<String> trimFinalPart(List<String> referenceName){
        ArrayList<String> toReturn = new ArrayList<String>();

        for (String str:referenceName) {
            String correct = "";
            if(str.contains("Tarefa:") || str.contains("Proba:")){
                correct = str.substring(0, str.length() -7);
            }else{
                correct = str;
            }

            toReturn.add(correct);
        }
        return toReturn;
    }

    private boolean isScoreValid(String str){
        if(str.contains("PTE")){
            return false;
        } else if (str.contains("ACP")) {
            return false;
        } else if(str.contains("Tarefa:")){
            return true;
        } else if(str.contains("Proba:")) {
            return true;
        }else{
            return false;
        }
    }
    private float parseScoreVersion(Boolean isExam, String str){
        float toReturn = 0.0f;
        if(isExam){
            String[]examSplit = str.split("_");
            toReturn = Float.parseFloat(examSplit[examSplit.length-2]);
        }else{
            String[]examSplit = str.split("\\.");
            toReturn = Float.parseFloat(str.substring(examSplit[0].length()-1, examSplit[0].length()+2));

        }
        return toReturn;
    }

    private String takeOutNumberOfEval(String str){
        String result = "";
        String toReturn = "";
        for (Integer i = 0; i < str.length(); i++){
            if(Character.isDigit(str.charAt(i))){
                result += str.charAt(i);
            }
        }
        switch (result){
            case "2":
                toReturn = "SEGUNDA";
                break;
            case "3":
                toReturn = "TERCERA";
                break;
            case "4":
                toReturn = "FINAL";
                break;
            case "5":
                toReturn = "EXTRAORDINARIA";
                break;
            default:
                toReturn = "PRIMERA";
        }
        return toReturn;
    }
    private String takeOutCourse(String str){
        StringBuilder result = new StringBuilder();
        for(int i=0;i < str.length();i++) {
            char toWork = str.charAt(i);

            if(Character.isDigit(toWork)){

            } else{
                result.append(toWork);
            }
        }
        return result.toString();
    }

    private String takeOutCourseNum(String str){
        StringBuilder result = new StringBuilder();
        for(int i=0;i < str.length();i++) {
            char toWork = str.charAt(i);

            if(Character.isDigit(toWork)){
                if(toWork == '1'){
                    return "PRIMERO";
                } else{
                    return "SEGUNDO";
                }
            }
        }
        return "Malardo";
    }



}
