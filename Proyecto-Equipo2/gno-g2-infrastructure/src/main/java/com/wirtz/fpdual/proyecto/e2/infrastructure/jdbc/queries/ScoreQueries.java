package com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.queries;

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
/*
    @Value("${selectById}")

   /* @Value("${selectById}")
    private String selectById;*/

    @Value("${createScore}")
    private String createScore;

    @Value("${updateScoreNumberById}")
    private String updateScoreNumberById;
/*
    @Value("${deleteScore}")
    private String deleteScore;
*/
    @Value("${getScoreIdFromScoreObject}")
    private String getScoreIdFromScoreObject;

  /*  @Value("${updateScore}")
    private String updateScore;

    @Value("${deleteScore}")
    private String deleteScore;*/
}
