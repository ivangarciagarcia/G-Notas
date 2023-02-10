package com.wirtz.fpdual.proyecto.e2.infrastructure.rowmapper;

import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.CourseModuleEntity;
import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.ModuleEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseModuleRowMapper implements RowMapper<CourseModuleEntity> {
    @Override
    public CourseModuleEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return CourseModuleEntity.builder()
                .courseModuleId(rs.getInt("course_module_id"))
                .courseId(rs.getInt("course_id"))
                .moduleId(rs.getInt("module_id"))
                .schoolId(rs.getInt("school_id"))
                .courseModuleGrade(rs.getString("course_module_grade"))
                .schoolYear(rs.getInt("school_year"))
                .teacherId(rs.getInt("teacher_id"))
                .build();
    }
}
