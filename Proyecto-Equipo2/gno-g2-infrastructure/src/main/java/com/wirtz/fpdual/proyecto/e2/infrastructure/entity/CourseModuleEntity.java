package com.wirtz.fpdual.proyecto.e2.infrastructure.entity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseModuleEntity {
    private Integer courseModuleId;
    private Integer courseId;
    private Integer moduleId;
    private Integer schoolId;
    private String courseModuleGrade;
    private Integer schoolYear;
    private Integer teacherId;
}
