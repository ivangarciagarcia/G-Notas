package com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.jdbctemplate;


import com.wirtz.fpdual.proyecto.e2.domain.dto.ScoreDTO;
import com.wirtz.fpdual.proyecto.e2.domain.repository.ScoreRepository;
import com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.queries.ScoreQueries;
import com.wirtz.fpdual.proyecto.e2.infrastructure.mapper.ScoreDTOMapperInterface;
import com.wirtz.fpdual.proyecto.e2.infrastructure.rowmapper.ScoreRowMapper;
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

    @Override
    public void createScore(ScoreDTO scoreDTO) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("score_name", scoreDTO.getScoreName());
        params.addValue("score_number", scoreDTO.getScoreNumber());
        params.addValue("score_type", scoreDTO.getScoreType());
        params.addValue("score_percentage", scoreDTO.getScorePercentage());
        params.addValue("evaluation_id", scoreDTO.getEvaluationId());
        params.addValue("student_id", scoreDTO.getStudentId());
        params.addValue("score_version", scoreDTO.getScoreVersion());
        namedParameterJdbcTemplate.update(queries.getCreateScore(), params);
    }

    @Override
    public Integer getScoreIdFromScoreMethod(ScoreDTO scoreDTO) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("score_name", scoreDTO.getScoreName());
        params.addValue("score_number", scoreDTO.getScoreNumber());
        params.addValue("score_type", scoreDTO.getScoreType());
        params.addValue("score_percentage", scoreDTO.getScorePercentage());
        params.addValue("evaluation_id", scoreDTO.getEvaluationId());
        params.addValue("student_id", scoreDTO.getStudentId());
        params.addValue("score_version", scoreDTO.getScoreVersion());
        return namedParameterJdbcTemplate.query(
            queries.getGetScoreIdFromScoreObject(), params,
            new ScoreRowMapper()
        ).get(0).getScoreId();
    }

    @Override
    public void updateScoreNumberById(ScoreDTO scoreDTO){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("score_id", scoreDTO.getScoreId());
        params.addValue("score_number", scoreDTO.getScoreNumber());
        namedParameterJdbcTemplate.update(queries.getUpdateScoreNumberById(), params);

    }
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
