package com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.jdbctemplate;

import com.wirtz.fpdual.proyecto.e2.domain.dto.course.CourseDTO;
import com.wirtz.fpdual.proyecto.e2.domain.repository.CourseRepository;
import com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.queries.studentqueries.CourseQueries;
import com.wirtz.fpdual.proyecto.e2.infrastructure.mapper.CourseDTOMapperInterface;
import com.wirtz.fpdual.proyecto.e2.infrastructure.rowmapper.CourseRowMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class JdbcTemplateCourseRepository implements CourseRepository {

    @Autowired
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private final CourseQueries queries;

    @Autowired
    private final CourseDTOMapperInterface courseMapperDTO;

    @Override
    public List<CourseDTO> getAllCourses() {
        return courseMapperDTO.toCourseDTOList(
                namedParameterJdbcTemplate.query(
                        queries.getFindAllCourses(),
                        new CourseRowMapper()
                )
        );
    }

    @Override
    public CourseDTO getCourseById(Integer id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return courseMapperDTO.toCourseDTO(namedParameterJdbcTemplate.queryForObject(
                queries.getFindCourseById()
                , params
                , new CourseRowMapper()));
    }

    @Override
    public void createCourse(CourseDTO courseDTO) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", courseDTO.getCourseName());
        namedParameterJdbcTemplate.update(
                queries.getInsertCourse()
                , params);
    }

    @Override
    public void updateCourse(CourseDTO courseDTO) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", courseDTO.getCourseId());
        params.addValue("name", courseDTO.getCourseName());
        namedParameterJdbcTemplate.update(
                queries.getUpdateCourse()
                , params);
    }

    @Override
    public void deleteCourse(Integer id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        namedParameterJdbcTemplate.update(
                queries.getDeleteCourse()
                , params);
    }
}
