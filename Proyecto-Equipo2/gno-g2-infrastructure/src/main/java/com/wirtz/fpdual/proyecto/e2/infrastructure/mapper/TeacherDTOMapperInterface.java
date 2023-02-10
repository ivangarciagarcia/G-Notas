package com.wirtz.fpdual.proyecto.e2.infrastructure.mapper;

import com.wirtz.fpdual.proyecto.e2.domain.dto.TeacherDTO;
import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.TeacherEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TeacherDTOMapperInterface {

    TeacherEntity toTeacherEntity(TeacherDTO teacherDTO);

    TeacherDTO toTeacherDTO(TeacherEntity teacherEntity);

    List<TeacherEntity> toTeacherEntityList(List<TeacherDTO> teacherDTOList);

    List<TeacherDTO> toTeacherDTOList(List<TeacherEntity> teacherEntityList);
}
