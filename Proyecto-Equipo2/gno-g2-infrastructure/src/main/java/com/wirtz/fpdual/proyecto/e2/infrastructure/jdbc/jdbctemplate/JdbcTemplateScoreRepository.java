package com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.jdbctemplate;


import com.wirtz.fpdual.proyecto.e2.domain.dto.score.ScoreDTO;
import com.wirtz.fpdual.proyecto.e2.domain.dto.student.StudentDTO;
import com.wirtz.fpdual.proyecto.e2.domain.repository.ScoreRepository;
import com.wirtz.fpdual.proyecto.e2.domain.repository.StudentRepository;
import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.StudentEntity;
import com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.queries.studentqueries.ScoreQueries;
import com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.queries.studentqueries.StudentQueries;
import com.wirtz.fpdual.proyecto.e2.infrastructure.mapper.ScoreDTOMapperInterface;
import com.wirtz.fpdual.proyecto.e2.infrastructure.mapper.StudentDTOMapperInterface;
import com.wirtz.fpdual.proyecto.e2.infrastructure.rowmapper.ScoreRowMapper;
import com.wirtz.fpdual.proyecto.e2.infrastructure.rowmapper.StudentRowMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class JdbcTemplateScoreRepository implements ScoreRepository {

    @Autowired
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private final ScoreQueries queries;

    @Autowired
    private final ScoreDTOMapperInterface scoreMapperDTO;

    @Override
    public List<ScoreDTO> getAllScores() {
        return scoreMapperDTO.toScoreDTOList(
                namedParameterJdbcTemplate.query(
                        queries.getSelectAllScores(),
                        new ScoreRowMapper()));
    }

//    @Override
//    public StudentDTO getStudentById(Integer studentId) {
//        MapSqlParameterSource params = new MapSqlParameterSource();
//        params.addValue("studentId", studentId);
//        List <StudentEntity> list = namedParameterJdbcTemplate.query(
//                queries.getSelectById(),
//                params,
//                new StudentRowMapper());
//        return studentMapperDTO.toStudentDTO(list.get(0));
//    }
//
//    @Override
//    public void createStudent(StudentDTO studentDTO) {
//        MapSqlParameterSource params = new MapSqlParameterSource();
//        params.addValue("studentName", studentDTO.getStudentName());
//        params.addValue("studentLastname", studentDTO.getStudentLastName());
//        params.addValue("studentBirthdate", studentDTO.getStudentBirthdate());
//        params.addValue("studentEmail", studentDTO.getStudentEmail());
//        params.addValue("studentDni", studentDTO.getStudentDni());
//        params.addValue("studentAddress", studentDTO.getStudentAddress());
//        namedParameterJdbcTemplate.update(queries.getCreateStudent(), params);
//    }
//
//    @Override
//    public void updateStudent(Integer studentId, StudentDTO studentDTO) {
//        MapSqlParameterSource params = new MapSqlParameterSource();
//        params.addValue("studentId", studentId);
//        params.addValue("studentName", studentDTO.getStudentName());
//        params.addValue("studentLastname", studentDTO.getStudentLastName());
//        params.addValue("studentBirthdate", studentDTO.getStudentBirthdate());
//        params.addValue("studentEmail", studentDTO.getStudentEmail());
//        params.addValue("studentDni", studentDTO.getStudentDni());
//        params.addValue("studentAddress", studentDTO.getStudentAddress());
//        namedParameterJdbcTemplate.update(queries.getUpdateStudent(), params);
//
//
//    }
//
//    @Override
//    public void deleteStudentById(Integer studentId) {
//        MapSqlParameterSource params = new MapSqlParameterSource();
//        params.addValue("studentId", studentId);
//        namedParameterJdbcTemplate.update(queries.getDeleteStudent(), params);
//    }


}
