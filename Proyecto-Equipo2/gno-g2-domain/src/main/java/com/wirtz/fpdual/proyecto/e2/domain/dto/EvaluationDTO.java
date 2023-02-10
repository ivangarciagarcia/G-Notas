package com.wirtz.fpdual.proyecto.e2.domain.dto;

import lombok.Data;

@Data
public class EvaluationDTO {
  private Integer evaluationId;
  private String evaluationType;
  private Integer examsPercentage;
  private Integer evaluationPercentage;
  private Integer courseModuleId;
}
