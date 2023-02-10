package com.wirtz.fpdual.proyecto.e2.domain.dto;

import lombok.Data;

@Data
public class TeacherDTO {
  private Integer teacherId;
  private String teacherName;
  private String teacherLogin;
  private String teacherEmail;
  private String teacherPassword;
  private Boolean teacherIsActive;
}
