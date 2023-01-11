package com.wirtz.fpdual.proyecto.e2.domain.dto.score;

import lombok.Data;

@Data
public class ScoreDTO {

  private Integer scoreNumber;
  private Integer scorePercentage;
  private String scoreType;
  private Integer evaluationId;
  private Integer studentId;
}
