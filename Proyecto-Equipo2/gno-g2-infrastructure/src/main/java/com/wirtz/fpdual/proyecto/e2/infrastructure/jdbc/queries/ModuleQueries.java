package com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.queries;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "module-queries.properties")
public class ModuleQueries {

  @Value("${selectAllModules}")
  private String selectAllModules;

  @Value("${createModule}")
  private String createModule;

  @Value("${getModuleIdByName}")
  private String getModuleIdByName;

  @Value("${selectIdFromModuleObject}")
  private String selectIdByModuleObject;
}
