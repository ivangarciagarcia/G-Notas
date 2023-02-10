package com.wirtz.fpdual.proyecto.e2.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ScoreDTO {

  private Integer scoreId;
  private String scoreName;
  private Float scoreNumber;
  private String scoreType;
  private Integer scorePercentage;
  private Integer evaluationId;
  private Integer studentId;
  private Float scoreVersion;
}
