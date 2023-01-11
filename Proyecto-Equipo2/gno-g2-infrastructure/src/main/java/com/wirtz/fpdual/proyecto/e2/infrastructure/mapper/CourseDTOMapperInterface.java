package com.wirtz.fpdual.proyecto.e2.infrastructure.mapper;

import com.wirtz.fpdual.proyecto.e2.domain.dto.course.CourseDTO;
import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.CourseEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CourseDTOMapperInterface {

    CourseEntity toCourseEntity(CourseDTO courseDTO);

    CourseDTO toCourseDTO(CourseEntity courseEntity);

    List<CourseEntity> toCourseEntityList(List<CourseDTO> courseDTOList);

    List<CourseDTO> toCourseDTOList(List<CourseEntity> courseEntityList);
}
