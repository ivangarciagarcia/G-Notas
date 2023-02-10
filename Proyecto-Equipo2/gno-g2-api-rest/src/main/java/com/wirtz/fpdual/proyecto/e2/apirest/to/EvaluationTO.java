package com.wirtz.fpdual.proyecto.e2.apirest.to;

import lombok.Data;

@Data
public class EvaluationTO {
  private Integer evaluationId;
  private String evaluationType;
  private int examsPercentage;
  private int evaluationPercentage;
  private Integer courseModuleId;

}
