package com.wirtz.fpdual.proyecto.e2.domain.repository;

import com.wirtz.fpdual.proyecto.e2.domain.dto.CourseModuleDTO;

import java.io.File;

public interface CourseModuleRepository {


    Integer getCourseModuleId(
            Integer courseId,
            Integer moduleId,
            Integer teacherId);


    Integer selectIdFromCourseModuleObject(CourseModuleDTO courseModuleDTO);


    void insertCourseModule(CourseModuleDTO courseModuleDTO);
}
