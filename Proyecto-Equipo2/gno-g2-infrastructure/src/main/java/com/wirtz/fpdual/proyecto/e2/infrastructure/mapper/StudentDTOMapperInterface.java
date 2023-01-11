package com.wirtz.fpdual.proyecto.e2.infrastructure.mapper;

import com.wirtz.fpdual.proyecto.e2.domain.dto.student.StudentDTO;
import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.StudentEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface StudentDTOMapperInterface {

    StudentEntity toStudentEntity(StudentDTO studentDTO) ;

    StudentDTO toStudentDTO(StudentEntity studentEntity);

    List<StudentEntity> toStudentEntityList(List<StudentDTO> studentDTOList);

    List<StudentDTO> toStudentDTOList(List <StudentEntity> studentEntityList);
}
