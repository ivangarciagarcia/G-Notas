package com.wirtz.fpdual.proyecto.e2.infrastructure.rowmapper;

import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.StudentEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<StudentEntity> {
    @Override
    public StudentEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return StudentEntity.builder()
                .studentId(rs.getInt("student_id"))
                .studentName(rs.getString("student_name"))
                .studentLastName(rs.getString("student_lastname"))
                .studentBirthdate(rs.getDate("student_birthdate").toLocalDate())
                .studentEmail(rs.getString("student_email"))
                .studentDni(rs.getString("student_dni"))
                .studentAddress(rs.getString("student_address"))
                .build();
    }
}
