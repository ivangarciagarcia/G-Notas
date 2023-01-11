package com.wirtz.fpdual.proyecto.e2.apirest.controller;

import com.wirtz.fpdual.proyecto.e2.apirest.mapper.SchoolMapperTO;
import com.wirtz.fpdual.proyecto.e2.apirest.to.school.SchoolTO;
import com.wirtz.fpdual.proyecto.e2.domain.service.SchoolServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/school")
public class SchoolController {

  @Autowired
  private final SchoolMapperTO schoolMapperTO;

  @Autowired
  private final SchoolServiceInterface schoolService;

  @GetMapping(value = "/status")
  public ResponseEntity<String> status() {
    return new ResponseEntity<>("SchoolController Up", getHeader(), HttpStatus.OK);

  }

  @GetMapping(value = "/all")
  public ResponseEntity<List<SchoolTO>> getAllSchools() {
    return new ResponseEntity<List<SchoolTO>>(schoolMapperTO.toSchoolTOList(schoolService.getAllSchools()),
        getHeader(), HttpStatus.OK);
  }

  @GetMapping(value = "/{schoolId}")
  public ResponseEntity<SchoolTO> getSchoolById(@PathVariable Integer schoolId){
    return new ResponseEntity<>(schoolMapperTO.toSchoolTO(schoolService.getSchoolById(schoolId)),
        getHeader(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity createSchool(@RequestBody SchoolTO school){
    schoolService.createSchool(schoolMapperTO.toSchoolDTO(school));
    return new ResponseEntity(getHeader(), HttpStatus.OK);
  }

  @PutMapping(value = "/schoolPut/{schoolId}")
  public ResponseEntity updateSchool(@PathVariable Integer schoolId, @RequestBody SchoolTO school){
    schoolService.updateSchool(schoolId, schoolMapperTO.toSchoolDTO(school));
    return new ResponseEntity(getHeader(), HttpStatus.OK);
  }

  @DeleteMapping(value = "/{schoolId}")
  public ResponseEntity deleteSchool(@PathVariable Integer schoolId){
    schoolService.deleteSchool(schoolId);
    return new ResponseEntity<>(getHeader(), HttpStatus.OK);
  }

  private HttpHeaders getHeader() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-type", "application/json");
    return headers;
  }
}
