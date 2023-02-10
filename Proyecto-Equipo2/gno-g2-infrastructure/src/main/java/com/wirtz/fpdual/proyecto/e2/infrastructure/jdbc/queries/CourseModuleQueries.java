package com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.queries;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "course-module-queries.properties")
public class CourseModuleQueries {

    @Value("${findModuleCourseByIds}")
    private String findModuleCourseByIds;

    @Value("${selectIdFromCourseModuleObject}")
    private  String selectIdFromCourseModuleObject;

    @Value("${insertCourseModule}")
    private String insertCourseModule;


}
