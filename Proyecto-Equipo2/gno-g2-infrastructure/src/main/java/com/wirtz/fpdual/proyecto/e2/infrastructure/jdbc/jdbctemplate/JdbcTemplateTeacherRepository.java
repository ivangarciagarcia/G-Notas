package com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.jdbctemplate;

import com.wirtz.fpdual.proyecto.e2.domain.dto.teacher.TeacherDTO;
import com.wirtz.fpdual.proyecto.e2.domain.repository.TeacherRepository;
import com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.queries.studentqueries.TeacherQueries;
import com.wirtz.fpdual.proyecto.e2.infrastructure.mapper.TeacherDTOMapperInterface;
import com.wirtz.fpdual.proyecto.e2.infrastructure.rowmapper.TeacherRowMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class JdbcTemplateTeacherRepository implements TeacherRepository {

    @Autowired
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private final TeacherQueries queries;

    @Autowired
    private final TeacherDTOMapperInterface teacherMapperDTO;

    @Override
    public List<TeacherDTO> getAllTeachers() {
        return teacherMapperDTO.toTeacherDTOList(
                namedParameterJdbcTemplate.query(
                        queries.getFindAllTeachers(),
                        new TeacherRowMapper()));
    }

    @Override
    public TeacherDTO getTeacherById(Integer id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return teacherMapperDTO.toTeacherDTO(namedParameterJdbcTemplate.queryForObject(
                queries.getFindTeacherById()
                ,params
                ,new TeacherRowMapper()));
    }

    @Override
    public void insertTeacher(TeacherDTO teacherDTO) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", teacherDTO.getTeacherName());
        params.addValue("login", teacherDTO.getTeacherLogin());
        params.addValue("email", teacherDTO.getTeacherEmail());
        params.addValue("password", teacherDTO.getTeacherPassword());
        params.addValue("isActive", teacherDTO.getTeacherIsActive());
        namedParameterJdbcTemplate.update(
                queries.getInsertTeacher(),params);
    }

    @Override
    public void updateTeacher(TeacherDTO teacherDTO) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", teacherDTO.getTeacherId());
        params.addValue("name", teacherDTO.getTeacherName());
        params.addValue("login", teacherDTO.getTeacherLogin());
        params.addValue("email", teacherDTO.getTeacherEmail());
        params.addValue("password", teacherDTO.getTeacherPassword());
        params.addValue("isActive", teacherDTO.getTeacherIsActive());
        namedParameterJdbcTemplate.update(queries.getUpdateTeacher(), params);
    }

    @Override
    public void deleteTeacher(Integer id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        namedParameterJdbcTemplate.update(
                queries.getDeleteTeacher()
                , params);
    }
}
