package com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.queries;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "student-queries.properties")
public class StudentQueries {

    @Value("${selectAllStudents}")
    private String selectAllStudents;

    @Value("${selectByStudentId}")
    private String selectByStudentId;

    @Value("${createStudent}")
    private String createStudent;

    @Value("${updateStudent}")
    private String updateStudent;

    @Value("${deleteStudent}")
    private String deleteStudent;

    @Value("${searchStudentByEmail}")
    private String searchByEmail;

    @Value("${findListStudentsByCourseModule}")
    private String findListStudentsByCourseModule;

    @Value("${findStudentScoreList}")
    private String findStudentScoreList;

    @Value("${insertStudentCourseModule}")
    private String insertStudentCourseModule;
}
