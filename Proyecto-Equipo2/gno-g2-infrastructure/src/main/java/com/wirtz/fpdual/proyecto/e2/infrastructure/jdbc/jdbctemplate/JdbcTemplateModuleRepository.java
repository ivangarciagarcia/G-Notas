package com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.jdbctemplate;

import com.wirtz.fpdual.proyecto.e2.domain.dto.ModuleDTO;
import com.wirtz.fpdual.proyecto.e2.domain.repository.ModuleRepository;
import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.ModuleEntity;
import com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.queries.ModuleQueries;
import com.wirtz.fpdual.proyecto.e2.infrastructure.mapper.ModuleDTOMapperInterface;
import com.wirtz.fpdual.proyecto.e2.infrastructure.rowmapper.ModuleRowMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class JdbcTemplateModuleRepository implements ModuleRepository {

  @Autowired
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  private final ModuleQueries moduleQueries;

  @Autowired
  private final ModuleDTOMapperInterface moduleDTOMapper;

  @Override
  public List<ModuleDTO> getAllModules() {
    return moduleDTOMapper.toModuleDtoList(
        namedParameterJdbcTemplate.query(
            moduleQueries.getSelectAllModules(),
            new ModuleRowMapper()
        )
    );
  }

  @Override
  public void insertModule(ModuleDTO moduleDTO){
    MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
    sqlParameterSource.addValue("module_name",moduleDTO.getModuleName());


    namedParameterJdbcTemplate.update(moduleQueries.getCreateModule(),sqlParameterSource);
    
  }

  @Override
  public Integer selectIdFromModuleObject(ModuleDTO moduleDTO) {
    MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
    sqlParameterSource.addValue("module_name",moduleDTO.getModuleName());

    List<ModuleEntity> moduleEntity =  namedParameterJdbcTemplate.query(moduleQueries.getSelectIdByModuleObject(), sqlParameterSource,new ModuleRowMapper());
    ModuleDTO moduleDTO2 = moduleDTOMapper.toModuleDto(moduleEntity.get(0)) ;
    return moduleDTO2.getModuleId();

  }

  @Override
  public Integer getModuleIdByName(String name) {
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("name", name);
    return namedParameterJdbcTemplate.queryForObject(moduleQueries.getGetModuleIdByName(), params, Integer.class);
  }



}
