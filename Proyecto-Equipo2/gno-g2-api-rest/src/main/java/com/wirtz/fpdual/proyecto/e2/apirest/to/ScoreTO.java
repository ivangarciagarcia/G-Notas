package com.wirtz.fpdual.proyecto.e2.apirest.to;

import lombok.Data;

@Data
public class ScoreTO {
  private Integer scoreId;
  private int scoreNumber;
  private String scoreType;
  private Integer scorePercentage;
  private int evaluationId;
  private int studentId;
  private float scoreVersion;
}
