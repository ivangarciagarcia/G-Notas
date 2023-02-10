package com.wirtz.fpdual.proyecto.e2.domain.repository;

import com.wirtz.fpdual.proyecto.e2.domain.dto.CourseDTO;

import java.util.List;

public interface CourseRepository {

    List<CourseDTO> getAllCourses();

    CourseDTO getCourseById(Integer id);

    Integer getCourseIdByName(String name);

    void createCourse(CourseDTO courseDTO);

    void updateCourse(CourseDTO courseDTO);

    void deleteCourse(Integer id);

    CourseDTO selectIdFromCourseObject(CourseDTO courseDTO);
}
