package com.wirtz.fpdual.proyecto.e2.apirest.to.evaluation;

import lombok.Data;

@Data
public class EvaluationTO {
  private int evaluationId;
  private String evaluationType;
  private int evaluationScore;
  private int practicePercentage;
  private int theoryPercentage;
  private int evaluationPercentage;
  private int moduleId;
}
