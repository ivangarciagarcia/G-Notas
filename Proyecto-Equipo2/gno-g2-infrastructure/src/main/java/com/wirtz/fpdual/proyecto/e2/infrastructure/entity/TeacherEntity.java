package com.wirtz.fpdual.proyecto.e2.infrastructure.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherEntity {

  private Integer teacherId;

  private String teacherName;

  private String teacherLogin;

  private String teacherEmail;

  private String teacherPassword;

  private Boolean teacherIsActive;
}
