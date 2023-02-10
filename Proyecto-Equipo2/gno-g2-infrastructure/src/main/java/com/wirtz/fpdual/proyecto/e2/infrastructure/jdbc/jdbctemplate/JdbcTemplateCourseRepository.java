package com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.jdbctemplate;

import com.wirtz.fpdual.proyecto.e2.domain.dto.CourseDTO;
import com.wirtz.fpdual.proyecto.e2.domain.repository.CourseRepository;
import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.CourseEntity;
import com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.queries.CourseQueries;
import com.wirtz.fpdual.proyecto.e2.infrastructure.mapper.CourseDTOMapperInterface;
import com.wirtz.fpdual.proyecto.e2.infrastructure.rowmapper.CourseRowMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
    public Integer getCourseIdByName(String name) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);

        return namedParameterJdbcTemplate.queryForObject(queries.getGetCourseIdByName(), params, Integer.class);
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

    @Override
    public CourseDTO selectIdFromCourseObject(CourseDTO courseDTO){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("course_name", courseDTO.getCourseName());
        List<CourseEntity> courseEntity = namedParameterJdbcTemplate.query(queries.getSelectIdFromCourseObject(), params, new CourseRowMapper());
        CourseDTO courseDTO2 = courseMapperDTO.toCourseDTO(courseEntity.get(0)) ;
        return courseDTO2;
    }
}
