package com.wirtz.fpdual.proyecto.e2.apirest.mapper;

import com.wirtz.fpdual.proyecto.e2.apirest.to.score.ScoreTO;
import com.wirtz.fpdual.proyecto.e2.apirest.to.student.StudentTO;
import com.wirtz.fpdual.proyecto.e2.domain.dto.score.ScoreDTO;
import com.wirtz.fpdual.proyecto.e2.domain.dto.student.StudentDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ScoreMapperTO {
    ScoreDTO toScoreDTO(ScoreTO scoreTO);

    ScoreTO toScoreTO(ScoreDTO scoreDTO);

    List<ScoreTO> toScoreTOList(List<ScoreDTO> scoreDTOList);

    List<ScoreDTO> toScoreDTOList(List<ScoreTO> scoreTOList);

}
