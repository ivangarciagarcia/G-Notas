package com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.jdbctemplate;

import com.wirtz.fpdual.proyecto.e2.domain.dto.SchoolDTO;
import com.wirtz.fpdual.proyecto.e2.domain.repository.SchoolRepository;
import com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.queries.SchoolQueries;
import com.wirtz.fpdual.proyecto.e2.infrastructure.mapper.SchoolDTOMapperInterface;
import com.wirtz.fpdual.proyecto.e2.infrastructure.rowmapper.SchoolRowMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class JdbcTemplateSchoolRepository implements SchoolRepository {

  @Autowired
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  private final SchoolQueries schoolQueries;

  @Autowired
  private final SchoolDTOMapperInterface schoolDTOMapper;

  @Override
  public List<SchoolDTO> getAllSchools() {
    return schoolDTOMapper.toSchoolDtoList(
        namedParameterJdbcTemplate.query(
            schoolQueries.getSelectAllSchools(),
            new SchoolRowMapper()
        )
    );
  }

  @Override
  public SchoolDTO getSchoolById(Integer schoolId) {
    MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
    sqlParameterSource.addValue("schoolId",schoolId);
    return schoolDTOMapper.toSchoolDto(namedParameterJdbcTemplate.queryForObject(
        schoolQueries.getSelectBySchoolId(),
            sqlParameterSource,
        new SchoolRowMapper()
    ));
  }

  @Override
  public void createSchool(SchoolDTO schoolDTO) {
    MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
    sqlParameterSource.addValue("schoolName",schoolDTO.getSchoolName());
    namedParameterJdbcTemplate.update(schoolQueries.getCreateSchool(), sqlParameterSource);
      }

  @Override
  public void updateSchool(Integer schoolId, SchoolDTO schoolDTO) {
    MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
    sqlParameterSource.addValue("schoolId", schoolDTO.getSchoolId());
    sqlParameterSource.addValue("schoolName",schoolDTO.getSchoolName());
    namedParameterJdbcTemplate.update(schoolQueries.getUpdateSchool(), sqlParameterSource);
  }

  @Override
  public void deleteSchoolById(Integer id) {
    MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
    sqlParameterSource.addValue("schoolId",id);
    namedParameterJdbcTemplate.update(schoolQueries.getDeleteSchool(), sqlParameterSource);
  }

  @Override
  public Integer getSchoolIdByObject(SchoolDTO schoolDTO) {
    MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
    sqlParameterSource.addValue("school_name",schoolDTO.getSchoolName());
    return namedParameterJdbcTemplate.query(
            schoolQueries.getSelectIdFromSchoolObject(),
            sqlParameterSource,
            new SchoolRowMapper()
    ).get(0).getSchoolId();
  }
}
