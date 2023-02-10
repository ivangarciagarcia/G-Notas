package com.wirtz.fpdual.proyecto.e2.apirest.controller;

import com.wirtz.fpdual.proyecto.e2.apirest.mapper.ScoreMapperTO;
import com.wirtz.fpdual.proyecto.e2.apirest.to.ScoreTO;
import com.wirtz.fpdual.proyecto.e2.domain.service.ScoreServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private final ScoreMapperTO scoreMapperTO;

    @Autowired
    private final ScoreServiceInterface scoreService;


    @GetMapping(value = "/all")
    public ResponseEntity<List<ScoreTO>> getAllScores() {
        return new ResponseEntity<>(scoreMapperTO.toScoreTOList(scoreService.getAllScores()),
                getHeader(), HttpStatus.OK);

    }

//    @GetMapping(value = "/{studentId}")
//    public ResponseEntity<StudentTO> getStudentById(@PathVariable Integer studentId){
//        return new ResponseEntity<>(studentMapperTO.toStudentTO(studentService.getStudentById(studentId)),
//                getHeader(), HttpStatus.OK);
//
//    }
//
//    @PostMapping
//    public ResponseEntity createStudent(@RequestBody StudentTO student){
//        studentService.createStudent(studentMapperTO.toStudentDTO(student));
//        return new ResponseEntity(getHeader(), HttpStatus.OK);
//    }
//
//    @PutMapping(value = "/{studentId}")
//    public ResponseEntity createStudent(@PathVariable Integer studentId, @RequestBody StudentTO student){
//        studentService.updateStudent(studentId, studentMapperTO.toStudentDTO(student));
//        return new ResponseEntity(getHeader(), HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/{studentId}")
//    public ResponseEntity deleteStudent(@PathVariable Integer studentId){
//        studentService.deleteStudent(studentId);
//        return new ResponseEntity<>(getHeader(), HttpStatus.OK);
//    }

    private HttpHeaders getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/json");
        return headers;
    }
}
