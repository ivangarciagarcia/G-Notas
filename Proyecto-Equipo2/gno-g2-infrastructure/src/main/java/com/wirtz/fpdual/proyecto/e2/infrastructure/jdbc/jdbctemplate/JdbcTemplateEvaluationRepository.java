package com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.jdbctemplate;

import com.wirtz.fpdual.proyecto.e2.domain.dto.evaluation.EvaluationDTO;
import com.wirtz.fpdual.proyecto.e2.domain.repository.EvaluationRepository;
import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.EvaluationEntity;
import com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.queries.studentqueries.EvaluationQueries;
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
}
