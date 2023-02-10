package com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.jdbctemplate;

import com.wirtz.fpdual.proyecto.e2.domain.dto.TeacherDTO;
import com.wirtz.fpdual.proyecto.e2.domain.repository.TeacherRepository;
import com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.queries.TeacherQueries;
import com.wirtz.fpdual.proyecto.e2.infrastructure.mapper.TeacherDTOMapperInterface;
import com.wirtz.fpdual.proyecto.e2.infrastructure.rowmapper.TeacherRowMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        params.addValue("teacher_id", id);
        return teacherMapperDTO.toTeacherDTO(namedParameterJdbcTemplate.queryForObject(
                queries.getFindTeacherById()
                ,params
                ,new TeacherRowMapper()));
    }

    @Override
    public void insertTeacher(TeacherDTO teacherDTO) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("teacher_name", teacherDTO.getTeacherName());
        params.addValue("teacher_login", teacherDTO.getTeacherLogin());
        params.addValue("teacher_email", teacherDTO.getTeacherEmail());
        params.addValue("teacher_password", teacherDTO.getTeacherPassword());
        params.addValue("teacher_is_active", teacherDTO.getTeacherIsActive());
        namedParameterJdbcTemplate.update(
                queries.getInsertTeacher(),params);
    }

    @Override
    public TeacherDTO getTeacherByEmail(String email){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("teacher_email", email);
        return teacherMapperDTO.toTeacherDTO(namedParameterJdbcTemplate.query(
                queries.getFindTeacherByEmail(), params, new TeacherRowMapper()).get(0));
    }


    @Override
    public void updateTeacher(TeacherDTO teacherDTO) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("teacher_id", teacherDTO.getTeacherId());
        params.addValue("teacher_name", teacherDTO.getTeacherName());
        params.addValue("teacher_login", teacherDTO.getTeacherLogin());
        params.addValue("teacher_email", teacherDTO.getTeacherEmail());
        params.addValue("teacher_password", teacherDTO.getTeacherPassword());
        params.addValue("teacher_is_active", teacherDTO.getTeacherIsActive());
        namedParameterJdbcTemplate.update(queries.getUpdateTeacher(), params);
    }

    @Override
    public void deleteTeacher(Integer id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("teacher_id", id);
        namedParameterJdbcTemplate.update(
                queries.getDeleteTeacher()
                , params);
    }
}
