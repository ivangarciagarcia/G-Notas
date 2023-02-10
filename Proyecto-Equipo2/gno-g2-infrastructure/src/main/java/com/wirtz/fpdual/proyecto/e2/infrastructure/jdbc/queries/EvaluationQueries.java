package com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.queries;

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

    @Value("${selectEvaluationByCourseModuleId}")
    private String selectByCourseModule;

    @Value("${selectIdFromEvaluationObject}")
    private String selectIdFromEvaluationObject;

    @Value("${createEvaluation}")
    private String createEvaluation;

    @Value("${selectIdByTypeNCourseModule}")
    private String selectIdByTypeNCourseModule;
}
