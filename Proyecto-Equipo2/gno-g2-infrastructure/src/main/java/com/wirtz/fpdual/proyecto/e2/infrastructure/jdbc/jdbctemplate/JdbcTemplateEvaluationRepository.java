package com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.jdbctemplate;

import com.wirtz.fpdual.proyecto.e2.domain.dto.EvaluationDTO;
import com.wirtz.fpdual.proyecto.e2.domain.repository.EvaluationRepository;
import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.EvaluationEntity;
import com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.queries.EvaluationQueries;
import com.wirtz.fpdual.proyecto.e2.infrastructure.mapper.EvaluationDTOMapperInterface;
import com.wirtz.fpdual.proyecto.e2.infrastructure.rowmapper.EvaluationRowMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class JdbcTemplateEvaluationRepository implements EvaluationRepository {

    @Autowired
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private final EvaluationQueries queries;

    @Autowired
    private final EvaluationDTOMapperInterface mapper;


    @Override
    public List<EvaluationDTO> getAllEvaluations() {
        return mapper.toListEvaluationDTO(
                namedParameterJdbcTemplate.query(queries.getSelectAllEvaluations(), new EvaluationRowMapper()));
    }

    @Override
    public EvaluationDTO getEvaluationById(Integer evaluationId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("evaluationId", evaluationId);
        List<EvaluationEntity> evaluation = namedParameterJdbcTemplate.query(queries.getSelectById(), params, new EvaluationRowMapper());
        return mapper.toEvaluationDTO(evaluation.get(0));
    }

    @Override
    public List<EvaluationDTO> getEvaluationByCourseModuleId(Integer courseModuleId){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("courseModuleId", courseModuleId);
        List<EvaluationEntity> evaluation = namedParameterJdbcTemplate.query(queries.getSelectByCourseModule(), params, new EvaluationRowMapper());
        return mapper.toListEvaluationDTO(evaluation);
    }

    @Override
    public List<EvaluationDTO> selectIdFromEvaluationObject(EvaluationDTO evaluationDTO) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("evaluation_type", evaluationDTO.getEvaluationType());
        params.addValue("evaluation_exams_percentage", evaluationDTO.getExamsPercentage());
        params.addValue("evaluation_percentage", evaluationDTO.getEvaluationPercentage());
        params.addValue("course_module_id", evaluationDTO.getCourseModuleId());
        return mapper.toListEvaluationDTO(namedParameterJdbcTemplate.query(queries.getSelectIdFromEvaluationObject(), params, new EvaluationRowMapper()));
    }

    @Override
    public void createEvaluation(EvaluationDTO evaluationDTO) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("evaluation_type" , evaluationDTO.getEvaluationType());
        sqlParameterSource.addValue("evaluation_exams_percentage",evaluationDTO.getExamsPercentage());
        sqlParameterSource.addValue("evaluation_percentage" , evaluationDTO.getEvaluationPercentage());
        sqlParameterSource.addValue("course_module_id" , evaluationDTO.getCourseModuleId());
        namedParameterJdbcTemplate.update(queries.getCreateEvaluation(), sqlParameterSource);
    }

    @Override
    public Integer selectIdByTypeNCourseModule(EvaluationDTO evaluationDTO){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("evaluation_type", evaluationDTO.getEvaluationType());
        params.addValue("course_module_id", evaluationDTO.getCourseModuleId());
        return namedParameterJdbcTemplate.query(queries.getSelectIdByTypeNCourseModule(), params, new EvaluationRowMapper()).get(0).getEvaluationId();
    }
}
