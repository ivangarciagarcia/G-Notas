package com.wirtz.fpdual.proyecto.e2.application.servicios;

import com.wirtz.fpdual.proyecto.e2.domain.dto.EvaluationDTO;
import com.wirtz.fpdual.proyecto.e2.domain.repository.EvaluationRepository;
import com.wirtz.fpdual.proyecto.e2.domain.service.EvaluationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService implements EvaluationServiceInterface {

    @Autowired
    EvaluationRepository evaluationRepository;

    @Override
    public List<EvaluationDTO> getAllEvaluations() {
        return evaluationRepository.getAllEvaluations();
    }

    @Override
    public EvaluationDTO getEvaluationById(Integer evaluationId) {
        return evaluationRepository.getEvaluationById(evaluationId);
    }


}
