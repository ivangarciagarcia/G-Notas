package com.wirtz.fpdual.proyecto.e2.apirest.controller;

import com.wirtz.fpdual.proyecto.e2.apirest.mapper.CourseTOMapperInteface;
import com.wirtz.fpdual.proyecto.e2.apirest.to.course.CourseTO;
import com.wirtz.fpdual.proyecto.e2.apirest.to.teacher.TeacherTO;
import com.wirtz.fpdual.proyecto.e2.domain.service.CourseServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private final CourseServiceInterface service;

    @Autowired
    private final CourseTOMapperInteface mapper;

    @GetMapping(value = "/status")
    public ResponseEntity<String> status() {
        return new ResponseEntity<>("CourseController Up", getHeader(), HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public List<CourseTO> getAllCourses() {
        return mapper.toCourseTOList(service.getAllCourses());
    }

    @GetMapping(value = "/search/{id}")
    public CourseTO getCourseById(@PathVariable Integer id){
        return mapper.toCourseTO(service.getCourseById(id));
    }

    @PostMapping(value ="/insert")
    public void insertCourse(@RequestBody CourseTO courseTO){
        service.createCourse(mapper.toCourseDTO(courseTO));
    }

    @PutMapping(value = "/modify/{id}")
    public void updateCourse(@RequestBody CourseTO courseTO, @PathVariable Integer id){
        courseTO.setCourseId(id);
        service.modifyCourse(mapper.toCourseDTO(courseTO));
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteCourse(@PathVariable Integer id){
        service.deleteCourse(id);
    }

    private HttpHeaders getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/json");
        return headers;
    }
}
