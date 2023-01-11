package com.wirtz.fpdual.proyecto.e2.infrastructure.entity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchoolEntity {

  private Integer schoolId;

  private String schoolName;

  private Set<ModuleEntity> moduleEntitySet;
}
