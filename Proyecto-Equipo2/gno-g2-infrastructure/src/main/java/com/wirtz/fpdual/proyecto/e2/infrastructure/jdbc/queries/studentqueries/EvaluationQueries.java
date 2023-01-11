package com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.queries.studentqueries;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "evaluation-queries.properties")
public class EvaluationQueries {

    @Value("${selectAllEvaluations}")
    private String selectAllEvaluations;

    @Value("${selectEvaluationById}")
    private String selectById;
}
