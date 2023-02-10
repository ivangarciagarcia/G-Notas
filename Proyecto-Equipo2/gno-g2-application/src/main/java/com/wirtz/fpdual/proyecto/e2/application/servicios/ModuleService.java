package com.wirtz.fpdual.proyecto.e2.application.servicios;

import com.wirtz.fpdual.proyecto.e2.domain.dto.ModuleDTO;
import com.wirtz.fpdual.proyecto.e2.domain.repository.ModuleRepository;
import com.wirtz.fpdual.proyecto.e2.domain.service.ModuleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ModuleService implements ModuleServiceInterface {

  @Autowired
  private ModuleRepository moduleRepository;

  @Override
  public List<ModuleDTO> getAllModules() {
    return moduleRepository.getAllModules();
  }
}
