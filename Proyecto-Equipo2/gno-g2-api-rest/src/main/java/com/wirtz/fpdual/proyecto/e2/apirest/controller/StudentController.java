package com.wirtz.fpdual.proyecto.e2.apirest.controller;

import com.wirtz.fpdual.proyecto.e2.apirest.mapper.StudentMapperTO;
import com.wirtz.fpdual.proyecto.e2.apirest.to.student.StudentTO;
import com.wirtz.fpdual.proyecto.e2.domain.service.StudentServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private final StudentMapperTO studentMapperTO;

    @Autowired
    private final StudentServiceInterface studentService;

    @GetMapping(value = "/status")
    public ResponseEntity<String> status() {
        return new ResponseEntity<>("StudentController Up", getHeader(), HttpStatus.OK);

    }
    @GetMapping(value = "/all")
    public ResponseEntity<List<StudentTO>> getAllStudents() {
        return new ResponseEntity<List<StudentTO>>(studentMapperTO.toStudentTOList(studentService.getAllStudents()),
                getHeader(), HttpStatus.OK);

    }

    @GetMapping(value = "/{studentId}")
    public ResponseEntity<StudentTO> getStudentById(@PathVariable Integer studentId){
        return new ResponseEntity<>(studentMapperTO.toStudentTO(studentService.getStudentById(studentId)),
                getHeader(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity createStudent(@RequestBody StudentTO student){
        studentService.createStudent(studentMapperTO.toStudentDTO(student));
        return new ResponseEntity(getHeader(), HttpStatus.OK);
    }

    @PutMapping(value = "/{studentId}")
    public ResponseEntity updateStudent(@PathVariable Integer studentId, @RequestBody StudentTO student){
        studentService.updateStudent(studentId, studentMapperTO.toStudentDTO(student));
        return new ResponseEntity(getHeader(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{studentId}")
    public ResponseEntity deleteStudent(@PathVariable Integer studentId){
        studentService.deleteStudent(studentId);
        return new ResponseEntity(getHeader(), HttpStatus.OK);
    }


    @GetMapping(value = "/email")
    public StudentTO getStudentByEmail(@RequestBody String email){
        return studentMapperTO.toStudentTO(studentService.getStudentByEmail(email));
    }

    private HttpHeaders getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/json");
        return headers;
    }
}
