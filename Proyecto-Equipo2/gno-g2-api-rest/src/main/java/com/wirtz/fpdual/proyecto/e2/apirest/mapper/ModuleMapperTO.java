package com.wirtz.fpdual.proyecto.e2.apirest.mapper;

import com.wirtz.fpdual.proyecto.e2.apirest.to.ModuleTO;
import com.wirtz.fpdual.proyecto.e2.domain.dto.ModuleDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ModuleMapperTO {

  ModuleDTO toModuleDTO(ModuleTO moduleTO);

  ModuleTO toModuleTO(ModuleDTO moduleDTO);

  List<ModuleTO> toModuleTOList(List<ModuleDTO> moduleDTOList);
}
