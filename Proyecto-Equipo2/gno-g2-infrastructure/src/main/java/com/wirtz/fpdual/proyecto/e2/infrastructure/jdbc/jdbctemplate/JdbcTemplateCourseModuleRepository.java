package com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.jdbctemplate;

import com.wirtz.fpdual.proyecto.e2.domain.dto.CourseModuleDTO;
import com.wirtz.fpdual.proyecto.e2.domain.repository.CourseModuleRepository;
import com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.queries.CourseModuleQueries;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class JdbcTemplateCourseModuleRepository implements CourseModuleRepository {

    @Autowired
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private final CourseModuleQueries queries;

    @Override
    public Integer getCourseModuleId(Integer courseId, Integer moduleId, Integer schoolYear){
        /*SACAR LA ASIGNATURA DE UN DETERMINADO CICLO CON UN DETERMINADO PROFESOR DE LA QUE SE QUIEREN LAS NOTAS*/
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("courseId", courseId);
        params.addValue("moduleId", moduleId);
        params.addValue("schoolYear", schoolYear);

        List<Map<String, Object>> moduleCourseList = namedParameterJdbcTemplate.queryForList(queries.getFindModuleCourseByIds(), params);
        Map<String, Object> moduleCourse = moduleCourseList.get(0);
        Integer courseModuleId = (Integer) moduleCourse.get("course_module_id");

        return courseModuleId;
    }

    @Override
    public Integer selectIdFromCourseModuleObject(CourseModuleDTO courseModuleDTO){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("course_id", courseModuleDTO.getCourseId());
        params.addValue("module_id", courseModuleDTO.getModuleId());
        params.addValue("school_id", courseModuleDTO.getSchoolId());
        params.addValue("course_module_grade", courseModuleDTO.getCourseModuleGrade());
        params.addValue("school_year", courseModuleDTO.getSchoolYear());
        params.addValue("teacher_id", courseModuleDTO.getTeacherId());

         return (Integer) namedParameterJdbcTemplate.queryForList(
                queries.getSelectIdFromCourseModuleObject(),
                params
        ).get(0).get("course_module_id");
    }

    @Override
    public void insertCourseModule(CourseModuleDTO courseModuleDTO){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("course_id", courseModuleDTO.getCourseId());
        params.addValue("module_id", courseModuleDTO.getModuleId());
        params.addValue("school_id", courseModuleDTO.getSchoolId());
        params.addValue("course_module_grade", courseModuleDTO.getCourseModuleGrade());
        params.addValue("school_year", courseModuleDTO.getSchoolYear());
        params.addValue("teacher_id", courseModuleDTO.getTeacherId());

        namedParameterJdbcTemplate.update(
                queries.getInsertCourseModule()
                , params);
    }




}
