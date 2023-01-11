package com.wirtz.fpdual.proyecto.e2.infrastructure.mapper;

import com.wirtz.fpdual.proyecto.e2.domain.dto.school.SchoolDTO;
import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.SchoolEntity;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper
public interface SchoolDTOMapperInterface {

  SchoolEntity toSchoolEntity(SchoolDTO schoolDTO);
  SchoolDTO toSchoolDto(SchoolEntity schoolEntity);
  List<SchoolDTO> toSchoolDtoList(List<SchoolEntity> schoolEntityList);
}
