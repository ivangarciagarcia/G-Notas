package com.wirtz.fpdual.proyecto.e2.infrastructure.entity;

import lombok.*;



import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModuleEntity {

  private Integer moduleId;

  private String moduleName;

  private EvaluationEntity evaluationId;

  private Set<CourseEntity> courseId;

  private Set<SchoolEntity> schoolId;
}
