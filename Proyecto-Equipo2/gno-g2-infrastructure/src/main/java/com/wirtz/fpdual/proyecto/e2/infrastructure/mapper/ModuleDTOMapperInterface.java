package com.wirtz.fpdual.proyecto.e2.infrastructure.mapper;

import com.wirtz.fpdual.proyecto.e2.domain.dto.ModuleDTO;
import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.ModuleEntity;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper
public interface ModuleDTOMapperInterface {

  ModuleEntity toModuleEntity(ModuleDTO moduleDTO);

  ModuleDTO toModuleDto(ModuleEntity moduleEntity);
  List<ModuleDTO> toModuleDtoList(List<ModuleEntity> moduleEntityList);


}
