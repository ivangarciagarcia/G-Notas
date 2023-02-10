package com.wirtz.fpdual.proyecto.e2.infrastructure.rowmapper;

import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.EvaluationEntity;
import com.wirtz.fpdual.proyecto.e2.infrastructure.entity.ScoreEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScoreRowMapper implements RowMapper<ScoreEntity> {
    @Override
    public ScoreEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ScoreEntity.builder()
                .scoreId(rs.getInt("score_id"))
                .scoreName(rs.getString("score_name"))
                .scoreNumber(rs.getFloat("score_number"))
                .scoreType(rs.getString("score_type"))
                .scorePercentage(rs.getInt("score_percentage"))
                .evaluationId(rs.getInt("evaluation_id"))
                .scoreVersion(rs.getInt("score_version"))
                .studentId(rs.getInt("student_id"))
                .scoreVersion(rs.getFloat("score_version"))
                .build();

    }

}
