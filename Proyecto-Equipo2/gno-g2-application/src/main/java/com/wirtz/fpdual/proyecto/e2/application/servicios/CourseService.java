package com.wirtz.fpdual.proyecto.e2.application.servicios;

import com.wirtz.fpdual.proyecto.e2.domain.dto.course.CourseDTO;
import com.wirtz.fpdual.proyecto.e2.domain.repository.CourseRepository;
import com.wirtz.fpdual.proyecto.e2.domain.service.CourseServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService implements CourseServiceInterface {

    @Autowired
    private final CourseRepository courseRepository;



    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepository.getAllCourses();
    }

    @Override
    public CourseDTO getCourseById(Integer courseId) {
        return courseRepository.getCourseById(courseId);
    }

    @Override
    public void createCourse(CourseDTO courseDTO) {
        courseRepository.createCourse(courseDTO);
    }

    @Override
    public void modifyCourse(CourseDTO courseDTO) {
        courseRepository.updateCourse(courseDTO);
    }

    @Override
    public void deleteCourse(Integer courseId) {
        courseRepository.deleteCourse(courseId);
    }
}
