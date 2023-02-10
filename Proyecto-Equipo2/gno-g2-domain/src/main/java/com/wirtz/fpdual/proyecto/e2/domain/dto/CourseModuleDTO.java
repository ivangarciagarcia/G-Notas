package com.wirtz.fpdual.proyecto.e2.domain.dto;

import lombok.Data;

@Data
public class CourseModuleDTO {
    private Integer courseModuleId;
    private Integer courseId;
    private Integer moduleId;
    private Integer schoolId;
    private String courseModuleGrade;
    private Integer schoolYear;
    private Integer teacherId;
}
