package com.wirtz.fpdual.proyecto.e2.apirest.controller;

import com.wirtz.fpdual.proyecto.e2.apirest.mapper.TeacherMapperTO;
import com.wirtz.fpdual.proyecto.e2.apirest.to.TeacherTO;
import com.wirtz.fpdual.proyecto.e2.domain.service.TeacherServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private final TeacherMapperTO mapper;

    @Autowired
    private final TeacherServiceInterface service;

    @GetMapping(value = "/status")
    public ResponseEntity<String> status() {
        return new ResponseEntity<>("TeacherController Up", getHeader(), HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public List<TeacherTO> getAllTeachers(){
        return mapper.toTeacherListTO(service.getAllTeachers());
    }

    @GetMapping(value ="/search/{id}")
    public TeacherTO getTeacherById(@PathVariable Integer id) {
        return mapper.toTeacherTO(service.getTeacherById(id));
    }

    @PostMapping(value ="/insert")
    public void insertTeacher(@RequestBody TeacherTO teacherTO){
        service.createTeacher(mapper.toTeacherDTO(teacherTO));
    }

    @PutMapping(value ="/modify/{id}")
    public void updateTeacher(@PathVariable Integer id, @RequestBody TeacherTO teacherTO){
        teacherTO.setTeacherId(id);
        service.updateTeacher(mapper.toTeacherDTO(teacherTO));
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteTeacher(@PathVariable Integer id){
        service.deleteTeacher(id);
    }


    private HttpHeaders getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/json");
        return headers;
    }
}
