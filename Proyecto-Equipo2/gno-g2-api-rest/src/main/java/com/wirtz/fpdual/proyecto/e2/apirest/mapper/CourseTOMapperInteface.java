package com.wirtz.fpdual.proyecto.e2.apirest.mapper;

import com.wirtz.fpdual.proyecto.e2.apirest.to.CourseTO;
import com.wirtz.fpdual.proyecto.e2.domain.dto.CourseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CourseTOMapperInteface {

    CourseDTO toCourseDTO(CourseTO courseTO);

    CourseTO toCourseTO(CourseDTO courseDTO);

    List<CourseDTO> toCourseDTOList(List<CourseTO> courseTOList);

    List<CourseTO> toCourseTOList(List<CourseDTO> courseDTOList);
}
