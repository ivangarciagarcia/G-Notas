package com.wirtz.fpdual.proyecto.e2.infrastructure.mapper;

import com.wirtz.fpdual.proyecto.e2.domain.dto.EvaluationDTO;
import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.EvaluationEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface EvaluationDTOMapperInterface {

    EvaluationEntity toEvaluationEntity(EvaluationDTO evaluationDTO);

    List<EvaluationEntity> toListEvaluationEntity(List<EvaluationDTO> evaluationDTOList);

    EvaluationDTO toEvaluationDTO(EvaluationEntity evaluation);

    List<EvaluationDTO> toListEvaluationDTO(List<EvaluationEntity> evaluationEntityList) ;

}