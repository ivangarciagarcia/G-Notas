package com.wirtz.fpdual.proyecto.e2.infrastructure.mapper;

import com.wirtz.fpdual.proyecto.e2.domain.dto.score.ScoreDTO;
import com.wirtz.fpdual.proyecto.e2.domain.dto.student.StudentDTO;
import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.ScoreEntity;
import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.ScoreEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ScoreDTOMapperInterface {

    ScoreEntity toScoreEntity(ScoreDTO scoreDTO) ;

    ScoreDTO toScoreDTO(ScoreEntity scoreEntity);

    List<ScoreEntity> toScoreEntityList(List<ScoreDTO> scoreDTOList);

    List<ScoreDTO> toScoreDTOList(List <ScoreEntity> scoreEntityList);
}

