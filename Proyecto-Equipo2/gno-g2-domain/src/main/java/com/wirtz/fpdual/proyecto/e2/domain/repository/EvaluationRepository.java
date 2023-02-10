package com.wirtz.fpdual.proyecto.e2.domain.repository;

import com.wirtz.fpdual.proyecto.e2.domain.dto.EvaluationDTO;

import java.util.List;

public interface EvaluationRepository {

    List<EvaluationDTO> getAllEvaluations();

    EvaluationDTO getEvaluationById(Integer evaluationId);

    List<EvaluationDTO> getEvaluationByCourseModuleId(Integer courseModuleId);

    List<EvaluationDTO> selectIdFromEvaluationObject(EvaluationDTO evaluationDTO);

    void createEvaluation(EvaluationDTO evaluationDTO);

    Integer selectIdByTypeNCourseModule(EvaluationDTO evaluationDTO);
}
