package com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.queries.studentqueries;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "teacher-queries.properties")
public class TeacherQueries {

    @Value("${findTeacherById}")
    private String findTeacherById;

    @Value("${findAllTeachers}")
    private String findAllTeachers;

    @Value("${insertTeacher}")
    private String insertTeacher;

    @Value("${updateTeacher}")
    private String updateTeacher;

    @Value("${deleteTeacher}")
    private String deleteTeacher;
}
