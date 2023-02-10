package com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.jdbctemplate;


import com.wirtz.fpdual.proyecto.e2.domain.dto.ScoreDTO;
import com.wirtz.fpdual.proyecto.e2.domain.dto.StudentDTO;
import com.wirtz.fpdual.proyecto.e2.domain.repository.StudentRepository;
import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.StudentEntity;
import com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.queries.StudentQueries;
import com.wirtz.fpdual.proyecto.e2.infrastructure.mapper.ScoreDTOMapperInterface;
import com.wirtz.fpdual.proyecto.e2.infrastructure.mapper.StudentDTOMapperInterface;
import com.wirtz.fpdual.proyecto.e2.infrastructure.rowmapper.ScoreRowMapper;
import com.wirtz.fpdual.proyecto.e2.infrastructure.rowmapper.StudentRowMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Repository
@AllArgsConstructor
public class JdbcTemplateStudentRepository implements StudentRepository {

    @Autowired
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private final StudentQueries queries;

    @Autowired
    private final StudentDTOMapperInterface studentMapperDTO;

    @Autowired
    private final ScoreDTOMapperInterface scoreDTOMapper;

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentMapperDTO.toStudentDTOList(
                namedParameterJdbcTemplate.query(
                        queries.getSelectAllStudents(),
                        new StudentRowMapper()));
    }

    @Override
    public StudentDTO getStudentById(Integer studentId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("studentId", studentId);
        List <StudentEntity> list = namedParameterJdbcTemplate.query(
                queries.getSelectByStudentId(),
                params,
                new StudentRowMapper());
        return studentMapperDTO.toStudentDTO(list.get(0));
    }

    @Override
    public void createStudent(StudentDTO studentDTO) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("studentName", studentDTO.getStudentName());
        params.addValue("studentLastname", studentDTO.getStudentLastName());
        if(studentDTO.getStudentBirthdate() == null){
            studentDTO.setStudentBirthdate(LocalDate.now());
        }
        params.addValue("studentBirthdate", studentDTO.getStudentBirthdate());
        params.addValue("studentEmail", studentDTO.getStudentEmail());
        if(studentDTO.getStudentDni() == null){
            studentDTO.setStudentDni(dniGenerator());
        }
        params.addValue("studentDni", studentDTO.getStudentDni());
        if(studentDTO.getStudentAddress() == null){
            studentDTO.setStudentDni("Default address");
        }
        params.addValue("studentAddress", studentDTO.getStudentAddress());
        namedParameterJdbcTemplate.update(queries.getCreateStudent(), params);
    }

    @Override
    public void updateStudent(Integer studentId, StudentDTO studentDTO) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("studentId", studentId);
        params.addValue("studentName", studentDTO.getStudentName());
        params.addValue("studentLastname", studentDTO.getStudentLastName());
        params.addValue("studentBirthdate", studentDTO.getStudentBirthdate());
        params.addValue("studentEmail", studentDTO.getStudentEmail());
        params.addValue("studentDni", studentDTO.getStudentDni());
        params.addValue("studentAddress", studentDTO.getStudentAddress());
        namedParameterJdbcTemplate.update(queries.getUpdateStudent(), params);


    }

    @Override
    public void deleteStudentById(Integer studentId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("studentId", studentId);
        namedParameterJdbcTemplate.update(queries.getDeleteStudent(), params);
    }

    @Override
    public StudentDTO getStudentByEmail(String email) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", email);
        return studentMapperDTO.toStudentDTO(namedParameterJdbcTemplate.queryForObject(queries.getSearchByEmail(), params, new StudentRowMapper()));
    }

    @Override
    public List<StudentDTO> getListStudentsByCourseModuleId(Integer courseModuleId){

        /*SACAR LA LISTA DE ALUMNOS DE ESA ASIGNATURA PARA UN AÑO EN CONCRETO */
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("courseModuleId", courseModuleId);

        List<StudentDTO> listStudents = studentMapperDTO.toStudentDTOList(namedParameterJdbcTemplate.query(queries.getFindListStudentsByCourseModule(), params, new StudentRowMapper()));

        /*INICIALIZAR LA LISTA DE NOTAS DE CADA ESTUDIANTE*/
        for (int i = 0; i < listStudents.size(); i++){
            MapSqlParameterSource params2 = new MapSqlParameterSource();
            params2.addValue("studentId", listStudents.get(i).getStudentId());
            params2.addValue("courseModuleId", courseModuleId);
            List<ScoreDTO> scoreList = scoreDTOMapper.toScoreDTOList(namedParameterJdbcTemplate.query(queries.getFindStudentScoreList(), params2, new ScoreRowMapper()));
            listStudents.get(i).setStudentListScore(scoreList);
        }

        return listStudents;
    }

    @Override
    public void insertStudentCourseModule(Integer studentId, Integer courseModuleId){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("student_id", studentId);
        params.addValue("course_module_id", courseModuleId);

        namedParameterJdbcTemplate.update(queries.getInsertStudentCourseModule(), params);
    }
    private String dniGenerator(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }



}
