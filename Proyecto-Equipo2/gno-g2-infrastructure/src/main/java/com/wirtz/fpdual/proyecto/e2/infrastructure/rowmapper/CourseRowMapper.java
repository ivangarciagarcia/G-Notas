package com.wirtz.fpdual.proyecto.e2.infrastructure.rowmapper;

import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.CourseEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRowMapper implements RowMapper<CourseEntity> {
    @Override
    public CourseEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return CourseEntity.builder()
                .courseId(rs.getInt("course_id"))
                .courseName(rs.getString("course_name"))
                .build();
    }
}
