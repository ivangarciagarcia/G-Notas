package com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.queries.studentqueries;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "score-queries.properties")
public class ScoreQueries {

    @Value("${selectAllScores}")
    private String selectAllScores;

//    @Value("${selectById}")
//    private String selectById;
//
//    @Value("${createScore}")
//    private String createScore;
//
//    @Value("${updateScore}")
//    private String updateScore;
//
//    @Value("${deleteScore}")
//    private String deleteScore;
}
