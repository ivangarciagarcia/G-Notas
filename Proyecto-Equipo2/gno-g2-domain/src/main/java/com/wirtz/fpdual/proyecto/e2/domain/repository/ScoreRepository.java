package com.wirtz.fpdual.proyecto.e2.domain.repository;

import com.wirtz.fpdual.proyecto.e2.domain.dto.ScoreDTO;

import java.util.List;

public interface ScoreRepository {

    List<ScoreDTO> getAllScores();

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
    void createScore(ScoreDTO scoreDTO);
//    ScoreDTO getScoreById(Integer scoreId);
//
//    void createScore(ScoreDTO scoreDTO);
//    void updateScore(Integer scoreId, ScoreDTO scoreDTO);
//    void deleteScoreById(Integer id);

    Integer getScoreIdFromScoreMethod(ScoreDTO scoreDTO);

    void updateScoreNumberById(ScoreDTO scoreDTO);
}
