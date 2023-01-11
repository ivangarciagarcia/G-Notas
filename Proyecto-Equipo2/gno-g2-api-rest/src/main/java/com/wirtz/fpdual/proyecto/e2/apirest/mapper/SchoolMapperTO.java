package com.wirtz.fpdual.proyecto.e2.apirest.mapper;

import com.wirtz.fpdual.proyecto.e2.apirest.to.school.SchoolTO;
import com.wirtz.fpdual.proyecto.e2.domain.dto.school.SchoolDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SchoolMapperTO {

  SchoolDTO toSchoolDTO(SchoolTO schoolTO);

  SchoolTO toSchoolTO(SchoolDTO schoolDTO);

  List<SchoolTO> toSchoolTOList(List<SchoolDTO> schoolDTOList);
}
