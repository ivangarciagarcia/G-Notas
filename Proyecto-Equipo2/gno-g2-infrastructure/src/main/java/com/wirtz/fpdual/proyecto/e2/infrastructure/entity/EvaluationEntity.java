package com.wirtz.fpdual.proyecto.e2.infrastructure.entity;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationEntity {

  private Integer evaluationId;

  private String evaluationType;

  private int practicePercentage;

  private int theoryPercentage;

  private int evaluationPercentage;

  private Integer moduleId;

}
