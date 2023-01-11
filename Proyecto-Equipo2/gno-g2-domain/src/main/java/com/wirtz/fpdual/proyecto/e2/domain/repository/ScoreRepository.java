package com.wirtz.fpdual.proyecto.e2.domain.repository;

import com.wirtz.fpdual.proyecto.e2.domain.dto.score.ScoreDTO;
import com.wirtz.fpdual.proyecto.e2.domain.dto.student.StudentDTO;

import java.util.List;

public interface ScoreRepository {

    List<ScoreDTO> getAllScores();
//    ScoreDTO getScoreById(Integer scoreId);
//
//    void createScore(ScoreDTO scoreDTO);
//    void updateScore(Integer scoreId, ScoreDTO scoreDTO);
//    void deleteScoreById(Integer id);
}
