package com.wirtz.fpdual.proyecto.e2.domain.dto.module;

import lombok.Data;

@Data
public class ModuleDTO {
  private Integer moduleId;
  private String moduleName;
  private Integer evaluationId;
  private Integer courseId;
  private Integer schoolId;
}
