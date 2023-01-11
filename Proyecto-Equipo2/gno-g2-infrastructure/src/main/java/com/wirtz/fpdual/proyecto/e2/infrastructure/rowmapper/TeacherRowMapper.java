package com.wirtz.fpdual.proyecto.e2.infrastructure.rowmapper;

import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.TeacherEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherRowMapper implements RowMapper<TeacherEntity> {

    @Override
    public TeacherEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return TeacherEntity.builder()
                .teacherId(rs.getInt("teacher_id"))
                .teacherName(rs.getString("teacher_name"))
                .teacherLogin(rs.getString("teacher_login"))
                .teacherEmail(rs.getString("teacher_email"))
                .teacherPassword(rs.getString("teacher_password"))
                .teacherIsActive(rs.getBoolean("teacher_is_active"))
                .build();
    }
}
