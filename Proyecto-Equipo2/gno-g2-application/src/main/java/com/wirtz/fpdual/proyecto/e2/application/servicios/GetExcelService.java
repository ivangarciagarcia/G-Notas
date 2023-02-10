package com.wirtz.fpdual.proyecto.e2.application.servicios;

import com.wirtz.fpdual.proyecto.e2.application.util.MathService;
import com.wirtz.fpdual.proyecto.e2.domain.dto.EvaluationDTO;
import com.wirtz.fpdual.proyecto.e2.domain.dto.ScoreDTO;
import com.wirtz.fpdual.proyecto.e2.domain.dto.StudentDTO;
import com.wirtz.fpdual.proyecto.e2.domain.repository.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetExcelService implements com.wirtz.fpdual.proyecto.e2.domain.service.GetExcelService {

    @Autowired
    CourseModuleRepository courseModuleRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    EvaluationRepository evaluationRepository;
    @Autowired
    MathService mathUtil;
    private List<StudentDTO> listStudents;
    private List<EvaluationDTO> evaluations;
    private Integer courseModuleId;

    /*VARIABLES EXCEL*/
    private XSSFSheet sheet;
    private XSSFWorkbook workbook;

    /*FILAS DE LA CABEZERA*/
    private Row firstRow;
    private Row secondRow;

    /*ESTILOS*/
    private XSSFFont font;
    private CellStyle headerStyle;
    private CellStyle mediaStyle;
    private CellStyle porcentajeStyle;
    private CellStyle firstRowStyle;
    private CellStyle suspensoStyle;
    private CellStyle style;

    /*UTIL*/
    private int indexColumnas;

    public void initializeData(Integer courseId, Integer moduleId, Integer schoolYear){
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("StudentScores");

        courseModuleId = courseModuleRepository.getCourseModuleId(courseId, moduleId, schoolYear);
        listStudents = studentRepository.getListStudentsByCourseModuleId(courseModuleId);
        evaluations = evaluationRepository.getEvaluationByCourseModuleId(courseModuleId);

        firstRow = sheet.createRow(0);
        secondRow = sheet.createRow(1);

        font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);

        headerStyle = workbook.createCellStyle();
        headerStyle.setFont(font);

        mediaStyle = createColorStyle(IndexedColors.LIGHT_TURQUOISE);
        mediaStyle.setFont(font);

        porcentajeStyle = createColorStyle(IndexedColors.LIGHT_BLUE);
        porcentajeStyle.setFont(font);

        firstRowStyle = createColorStyle(IndexedColors.LIGHT_CORNFLOWER_BLUE);
        firstRowStyle.setFont(font);

        suspensoStyle = createColorStyle(IndexedColors.RED);

        style = workbook.createCellStyle();

        indexColumnas = 2;
    }

    @Override
    public XSSFWorkbook getExcel(String course, String module, Integer schoolYear, HttpServletResponse response) throws IOException {

        Integer courseId = courseRepository.getCourseIdByName(course);
        Integer moduleId = moduleRepository.getModuleIdByName(module);

        initializeData(courseId, moduleId, schoolYear);
        writeHeaders();
        writeData();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

        return workbook;
    }

    @Override
    public XSSFWorkbook getExcelPostman(Integer courseId, Integer moduleId, Integer schoolYear, HttpServletResponse response) throws IOException {

        initializeData(courseId, moduleId, schoolYear);
        writeHeaders();
        writeData();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

        return workbook;
    }


    private void writeHeaders(){

        List<ScoreDTO> listTareas = listStudents.get(0).getStudentListScore();

        /*CELDAS DE NOME Y APELIDOS*/
        createCell(secondRow, 0, "Nome", headerStyle);
        createCell(secondRow, 1, "Apelidos", headerStyle);

        for (EvaluationDTO eval:evaluations){

            /*Inicializar todas las tareas de la evaluación*/
            List<ScoreDTO> tareasEvalList = listTareas.stream()
                    .filter(tarea -> (tarea.getEvaluationId().equals(eval.getEvaluationId()) && tarea.getScoreType().equals("TAREA")))
                    .toList();

            /*Inicializar los exámenes prácticos*/
            List<ScoreDTO> examPracticoEvalList = listTareas.stream()
                    .filter(tarea -> (tarea.getEvaluationId().equals(eval.getEvaluationId()) && tarea.getScoreType().equals("PRACTICA")))
                    .toList();

            /*Inicializar los exámenes teóricos*/
            List<ScoreDTO> examTeoricoEvalList = listTareas.stream()
                    .filter(tarea -> (tarea.getEvaluationId().equals(eval.getEvaluationId()) && tarea.getScoreType().equals("TEORICO")))
                    .toList();

            /*Escribir todas las tareas*/
            writeHeaderTareas(tareasEvalList, eval);

            /*Escribir los exámenes*/
            writeHeaderExamenes(examPracticoEvalList, examTeoricoEvalList, eval);


            /*Si hay alguna actividad en la evaluación, colocar la celda de NotaEval*/
            if(!examPracticoEvalList.isEmpty()|| !examTeoricoEvalList.isEmpty() || !tareasEvalList.isEmpty()) {
                createCell(secondRow, indexColumnas, "Nota Eval", headerStyle);
                indexColumnas++;
            }

        }

        createCell(secondRow, indexColumnas, "Nota Final", headerStyle);

    }

    private void writeHeaderTareas(List<ScoreDTO> listTareas, EvaluationDTO eval){


        int indexStartTareas = indexColumnas;


        for (ScoreDTO tarea : listTareas){
            String tareaName = tarea.getScoreName();
            createCell(secondRow, indexColumnas, tareaName, headerStyle);
            indexColumnas++;
        }

        /*Si hay tareas, colocar la celda de Media y porcentaje y la cabecera*/
        if(!listTareas.isEmpty()) {
            /*cabecera*/
            createCell(firstRow, indexStartTareas, "ACTIVIDADES Eval:" + eval.getEvaluationType() , firstRowStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, indexStartTareas, indexColumnas));
            /*media*/
            createCell(secondRow, indexColumnas, "Media", mediaStyle);
            indexColumnas++;
            /*porcentaje*/
            createCell(secondRow, indexColumnas, (100 - eval.getExamsPercentage()) + "%", porcentajeStyle);
            indexColumnas++;
        }

    }

    private void writeHeaderExamenes(List<ScoreDTO> listExamenesPract, List<ScoreDTO> listExamenesTeoric, EvaluationDTO eval){

        int indexStartExamenes = indexColumnas;

        for (ScoreDTO tarea: listExamenesTeoric){

            /*Escribir el exámen teórico*/
            String teoricoName = tarea.getScoreName() + "(" +(tarea.getScorePercentage()) + "%)";
            createCell(secondRow, indexColumnas, teoricoName, headerStyle);
            indexColumnas++;

            /*Encontrar la pareja del examen teórico y escribirlo*/
            Float examVersion = tarea.getScoreVersion();
            ScoreDTO examenPractico = listExamenesPract.stream()
                    .filter(exam -> exam.getScoreVersion().equals(examVersion))
                    .toList().get(0);

            if (examenPractico != null) {
                String practicoName = examenPractico.getScoreName() + "(" + (examenPractico.getScorePercentage()) + "%)";
                createCell(secondRow, indexColumnas, practicoName, headerStyle);
                indexColumnas++;

                createCell(secondRow, indexColumnas, "Total Examen", headerStyle);
                indexColumnas++;
            }
        }

        /*Si hay exámenes, colocar la celda de Media y porcentaje y cabecera*/
        if(!listExamenesTeoric.isEmpty()|| !listExamenesPract.isEmpty()) {
            /*cabecera*/
            createCell(firstRow, indexStartExamenes, "EXAMENES Eval:" + eval.getEvaluationType(), firstRowStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, indexStartExamenes, indexColumnas));
            /*media*/
            createCell(secondRow, indexColumnas, "Media", mediaStyle);
            indexColumnas++;
            /*porcentaje*/
            createCell(secondRow, indexColumnas,  eval.getExamsPercentage() + "%", porcentajeStyle);
            indexColumnas++;
        }
    }

    private void writeData(){

        DecimalFormat formato = new DecimalFormat("0.00");
        int indexRow = 2;
        double notaFinal = 0;


        /*RELLENAR LOS DATOS DEL ESTUDIANTE*/
        for (StudentDTO student: listStudents){

            int indexColumnas = 2;
            Row row = sheet.createRow(indexRow);

            List<ScoreDTO> listTareas = student.getStudentListScore();

            /*NOMBRE Y APELLIDOS*/
            createCell(row, 0, student.getStudentName(), style);
            createCell(row, 1, student.getStudentLastName(), style);

            /*NOTAS DE CADA EVALUACIÓN*/

            for (EvaluationDTO eval:evaluations){

                /*Inicializar todas las tareas de la evaluación*/
                List<ScoreDTO> tareasEvalList = listTareas.stream()
                        .filter(tarea -> (tarea.getEvaluationId().equals(eval.getEvaluationId()) && tarea.getScoreType().equals("TAREA")))
                        .toList();

                /*Inicializar los exámenes prácticos*/
                List<ScoreDTO> examPracticoEvalList = listTareas.stream()
                        .filter(tarea -> (tarea.getEvaluationId().equals(eval.getEvaluationId()) && tarea.getScoreType().equals("PRACTICA")))
                        .toList();

                /*Inicializar los exámenes teóricos*/
                List<ScoreDTO> examTeoricoEvalList = listTareas.stream()
                        .filter(tarea -> (tarea.getEvaluationId().equals(eval.getEvaluationId()) && tarea.getScoreType().equals("TEORICO")))
                        .toList();

                double taskScorePercentage = 0;
                double examsScorePercentage = 0;

                /*NOTAS DE TODAS LAS TAREAS*/
                List<Float> allTareaScores = new ArrayList<>();
                for (ScoreDTO tarea : tareasEvalList){
                    float nota = tarea.getScoreNumber();
                    CellStyle currentStyle = ((nota >= 5)? style : suspensoStyle);
                    createCell(row, indexColumnas, nota, currentStyle);
                    allTareaScores.add(nota);
                    indexColumnas++;
                }

                /*La nota media de las tareas*/
                if(!tareasEvalList.isEmpty()) {
                    double media = mathUtil.obtenerMedia(allTareaScores);
                    CellStyle currentStyle = ((media >= 5)? style : suspensoStyle);
                    createCell(row, indexColumnas, "" + formato.format(media), currentStyle);
                    indexColumnas++;



                    /*La media con su correspondiente porcentaje*/
                    double taskPercentage = (100 - eval.getExamsPercentage().floatValue()) / 100;
                    taskScorePercentage = (media) * taskPercentage;
                    createCell(row, indexColumnas, "" + formato.format(taskScorePercentage), style);
                    indexColumnas++;
                }



                List<Float> allExamsScores = new ArrayList<>();
                for (ScoreDTO tarea : examTeoricoEvalList){
                    float teoricoNota = tarea.getScoreNumber();
                    CellStyle currentStyle = ((teoricoNota >= 5)? style : suspensoStyle);
                    createCell(row, indexColumnas, formato.format(teoricoNota), currentStyle );
                    indexColumnas++;

                    /*Encontrar la pareja del examen teórico y escribirlo*/
                    Float examVersion = tarea.getScoreVersion();
                    ScoreDTO examenPractico = examPracticoEvalList.stream()
                            .filter(exam -> exam.getScoreVersion().equals(examVersion))
                            .toList().get(0);
                    if (examenPractico != null){
                        float practicoNota = examenPractico.getScoreNumber();
                        currentStyle = ((practicoNota >= 5)? style : suspensoStyle);
                        createCell(row, indexColumnas, formato.format(practicoNota), currentStyle);
                        indexColumnas++;

                        float notaTotal = (teoricoNota * (tarea.getScorePercentage().floatValue() / 100)) + (practicoNota * (examenPractico.getScorePercentage().floatValue() / 100));
                        allExamsScores.add(notaTotal);
                        currentStyle = ((notaTotal >= 5)? style : suspensoStyle);
                        createCell(row, indexColumnas, formato.format(notaTotal), currentStyle);
                        indexColumnas++;
                    }

                }

                if(!allExamsScores.isEmpty()) {
                    /*La nota media de las tareas*/
                    double examsMedia = mathUtil.obtenerMedia(allExamsScores);
                    CellStyle currentStyle = ((examsMedia >= 5)? style : suspensoStyle);
                    createCell(row, indexColumnas, "" + formato.format(examsMedia), currentStyle);
                    indexColumnas++;

                    /*La media con su correspondiente porcentaje*/
                    double examsPercentage = eval.getExamsPercentage().floatValue() / 100;
                    examsScorePercentage = (examsMedia) * examsPercentage;
                    createCell(row, indexColumnas, "" + formato.format(examsScorePercentage), style);
                    indexColumnas++;


                }

                double notaEvaluacion = taskScorePercentage + examsScorePercentage;
                CellStyle currentStyle = ((notaEvaluacion >= 5)? style : suspensoStyle);

                if(!allTareaScores.isEmpty() || !allExamsScores.isEmpty()) {
                    createCell(row, indexColumnas, "" + formato.format(notaEvaluacion), currentStyle);
                    indexColumnas++;
                }

                notaFinal += ((taskScorePercentage + examsScorePercentage) * (eval.getEvaluationPercentage().floatValue() / 100));

            }

            createCell(row, indexColumnas, "" + formato.format(notaFinal), style );

            indexRow++;

        }
    }





    private Cell createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);

        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }
        else if (value instanceof Float) {
            cell.setCellValue((Float) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);

        return cell;
    }

    private CellStyle createColorStyle(IndexedColors color){
        CellStyle style = workbook.createCellStyle();
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(color.getIndex());
        return style;
    }





}
