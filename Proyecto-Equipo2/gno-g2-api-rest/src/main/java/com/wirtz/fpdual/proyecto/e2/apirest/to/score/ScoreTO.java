package com.wirtz.fpdual.proyecto.e2.apirest.to.score;

import lombok.Data;

@Data
public class ScoreTO {
  private int scoreNumber;
  private int scorePercentage;
  private String scoreType;
  private int evaluationId;
  private int studentId;
}
