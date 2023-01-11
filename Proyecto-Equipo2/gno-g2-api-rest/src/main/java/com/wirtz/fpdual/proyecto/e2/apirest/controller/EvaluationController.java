package com.wirtz.fpdual.proyecto.e2.apirest.controller;

import com.wirtz.fpdual.proyecto.e2.apirest.mapper.EvaluationMapperTO;
import com.wirtz.fpdual.proyecto.e2.apirest.to.evaluation.EvaluationTO;
import com.wirtz.fpdual.proyecto.e2.apirest.to.student.StudentTO;
import com.wirtz.fpdual.proyecto.e2.domain.service.EvaluationServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/evaluation")
public class EvaluationController {

    @Autowired
    EvaluationServiceInterface evaluationService;

    @Autowired
    EvaluationMapperTO evaluationMapperTO;

    @GetMapping(value = "/all")
    ResponseEntity<List<EvaluationTO>> getAllEvaluations(){
        return new ResponseEntity<List<EvaluationTO>>(evaluationMapperTO.toEvaluationTOList(evaluationService.getAllEvaluations()),
                getHeader(),HttpStatus.OK);

         }
    @GetMapping(value = "/{evaluationId}")
    public ResponseEntity<EvaluationTO> getEvaluationtById(@PathVariable Integer evaluationId){
        return new ResponseEntity<>(evaluationMapperTO.toEvaluationTO(evaluationService.getEvaluationById(evaluationId)),
                getHeader(), HttpStatus.OK);

    }

    private HttpHeaders getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/json");
        return headers;
    }
}
