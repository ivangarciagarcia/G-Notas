package com.wirtz.fpdual.proyecto.e2.infrastructure.rowmapper;

import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.SchoolEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SchoolRowMapper implements RowMapper<SchoolEntity> {
  @Override
  public SchoolEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
    return SchoolEntity.builder()
        .schoolId(rs.getInt("school_id"))
        .schoolName(rs.getString("school_name"))
        .build();
  }
}
