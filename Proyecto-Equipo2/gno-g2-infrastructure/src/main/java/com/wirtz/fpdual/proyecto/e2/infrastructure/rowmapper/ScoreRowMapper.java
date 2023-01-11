package com.wirtz.fpdual.proyecto.e2.infrastructure.rowmapper;

import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.EvaluationEntity;
import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.ScoreEntity;
import com.wirtz.fpdual.proyecto.e2.infrastructure.util.ScoreType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ScoreRowMapper implements RowMapper<ScoreEntity> {

    @Override
    public ScoreEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ScoreEntity.builder()
                .scoreId(rs.getInt("score_id"))
                .scoreNumber(rs.getInt("score_number"))
                .scorePercentage(rs.getInt("score_percentage"))
                .scoreType(ScoreType.valueOf(rs.getString("score_type")))
                .evaluationId(rs.getInt("evaluation_id"))
                .studentId(rs.getInt("student_id"))
                .build();
    }
}
