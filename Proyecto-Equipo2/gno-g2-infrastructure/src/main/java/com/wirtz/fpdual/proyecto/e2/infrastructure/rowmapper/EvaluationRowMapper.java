package com.wirtz.fpdual.proyecto.e2.infrastructure.rowmapper;

import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.EvaluationEntity;
import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.StudentEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class EvaluationRowMapper implements RowMapper<EvaluationEntity> {

    @Override
    public EvaluationEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return EvaluationEntity.builder()
                .evaluationId(rs.getInt("evaluation_id"))
                .evaluationType(rs.getString("evaluation_type"))
                .theoryPercentage(rs.getInt("evaluation_theory_percentage"))
                .practicePercentage(rs.getInt("evaluation_practice_percentage"))
                .evaluationPercentage(rs.getInt("evaluation_percentage"))
                .moduleId(rs.getInt("module_id"))
                .build();
    }
}
