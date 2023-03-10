package com.wirtz.fpdual.proyecto.e2.apirest.mapper;

import com.wirtz.fpdual.proyecto.e2.apirest.to.EvaluationTO;
import com.wirtz.fpdual.proyecto.e2.domain.dto.EvaluationDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface EvaluationMapperTO {
    EvaluationTO toEvaluationTO(EvaluationDTO evaluationDTO);
    List<EvaluationTO> toEvaluationTOList(List<EvaluationDTO> list);
}
