package com.wirtz.fpdual.proyecto.e2.apirest.to.module;

import lombok.Data;

@Data
public class ModuleTO {
  private int moduleId;
  private String moduleName;
  private int evaluationId;
  private int courseId;
  private int schoolId;
}
