package com.wirtz.fpdual.proyecto.e2.domain.repository;

import com.wirtz.fpdual.proyecto.e2.domain.dto.ModuleDTO;

import java.util.List;

public interface ModuleRepository {

    List<ModuleDTO> getAllModules();

    void insertModule(ModuleDTO moduleDTO);

    Integer selectIdFromModuleObject(ModuleDTO moduleDTO);

    Integer getModuleIdByName(String name);
}
