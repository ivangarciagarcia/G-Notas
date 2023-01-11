package com.wirtz.fpdual.proyecto.e2.apirest.to.teacher;

import lombok.Data;

@Data
public class TeacherTO {
  private int teacherId;
  private String teacherName;
  private String teacherLogin;
  private String teacherEmail;
  private String teacherPassword;
  private Boolean teacherIsActive;
}
