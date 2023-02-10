package com.wirtz.fpdual.proyecto.e2.infrastructure.entity;

import com.wirtz.fpdual.proyecto.e2.infrastructure.util.ScoreType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoreEntity {

  private Integer scoreId;

  private String scoreName;

  private Float scoreNumber;
  
  private String scoreType;

  private Integer scorePercentage;

  private Integer evaluationId;

  private Integer studentId;

  private float scoreVersion;
}
