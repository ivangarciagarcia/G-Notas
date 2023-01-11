package com.wirtz.fpdual.proyecto.e2.domain.dto.evaluation;

import lombok.Data;

@Data
public class EvaluationDTO {
  private Integer evaluationId;
  private String evaluationType;
  private int practicePercentage;
  private int theoryPercentage;
  private int evaluationPercentage;
  private int moduleId;
}
