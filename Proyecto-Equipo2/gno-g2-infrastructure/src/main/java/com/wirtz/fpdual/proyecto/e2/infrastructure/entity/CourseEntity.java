package com.wirtz.fpdual.proyecto.e2.infrastructure.entity;
import lombok.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseEntity {

  private Integer courseId;

  private String courseName;

  private Set<ModuleEntity> moduleEntitySet;
}
