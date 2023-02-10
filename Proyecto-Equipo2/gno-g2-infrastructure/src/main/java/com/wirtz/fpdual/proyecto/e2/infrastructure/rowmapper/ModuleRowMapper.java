package com.wirtz.fpdual.proyecto.e2.infrastructure.rowmapper;

import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.ModuleEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModuleRowMapper implements RowMapper<ModuleEntity> {
  @Override
  public ModuleEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
    return ModuleEntity.builder()
        .moduleId(rs.getInt("module_id"))
        .moduleName(rs.getString("module_name"))
        .build();
  }
}
