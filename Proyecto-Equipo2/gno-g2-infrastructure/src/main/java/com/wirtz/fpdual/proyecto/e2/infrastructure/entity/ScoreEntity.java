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

  private Integer scoreNumber;

  private Integer scorePercentage;

  private ScoreType scoreType;

  private Integer evaluationId;

  private Integer studentId;
}
