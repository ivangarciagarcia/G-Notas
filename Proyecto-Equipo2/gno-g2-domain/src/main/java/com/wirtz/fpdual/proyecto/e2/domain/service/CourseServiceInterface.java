package com.wirtz.fpdual.proyecto.e2.domain.service;

import com.wirtz.fpdual.proyecto.e2.domain.dto.CourseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseServiceInterface {

    public List<CourseDTO> getAllCourses();

    public CourseDTO getCourseById(Integer courseId);

    public void createCourse(CourseDTO courseDTO);

    public void modifyCourse(CourseDTO courseDTO);

    public void deleteCourse(Integer courseId);
}
