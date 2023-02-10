package com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.queries;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "course-queries.properties")
public class CourseQueries {

    @Value("${findAllCourses}")
    private String findAllCourses;

    @Value("${findCourseById}")
    private String findCourseById;

    @Value("${insertCourse}")
    private String insertCourse;

    @Value("${updateCourse}")
    private String updateCourse;

    @Value("${deleteCourse}")
    private String deleteCourse;

    @Value(("${getCourseIdByName}"))
    private String getCourseIdByName;

    @Value("${selectIdFromCourseObject}")
    private String selectIdFromCourseObject;

}
