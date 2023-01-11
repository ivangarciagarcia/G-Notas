package com.wirtz.fpdual.proyecto.e2.domain.repository;

import com.wirtz.fpdual.proyecto.e2.domain.dto.evaluation.EvaluationDTO;

import java.util.List;

public interface EvaluationRepository {

    List<EvaluationDTO> getAllEvaluations();

    EvaluationDTO getEvaluationById(Integer evaluationId);
}
