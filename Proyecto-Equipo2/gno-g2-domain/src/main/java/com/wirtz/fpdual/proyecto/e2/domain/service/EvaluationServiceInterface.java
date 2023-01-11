package com.wirtz.fpdual.proyecto.e2.domain.service;

import com.wirtz.fpdual.proyecto.e2.domain.dto.evaluation.EvaluationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EvaluationServiceInterface {

    List<EvaluationDTO> getAllEvaluations();
    EvaluationDTO getEvaluationById(Integer evaluationId);

}
