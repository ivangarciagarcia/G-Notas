package com.wirtz.fpdual.proyecto.e2.apirest.controller;

import com.wirtz.fpdual.proyecto.e2.apirest.mapper.ModuleMapperTO;
import com.wirtz.fpdual.proyecto.e2.apirest.to.ModuleTO;
import com.wirtz.fpdual.proyecto.e2.domain.service.ModuleServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/module")
public class ModuleController {

  @Autowired
  private final ModuleMapperTO moduleMapperTO;

  @Autowired
  private final ModuleServiceInterface moduleService;

  @GetMapping(value = "/status")
  public ResponseEntity<String> status() {
    return new ResponseEntity<>("ModuleController Up", getHeader(), HttpStatus.OK);

  }

  @GetMapping(value = "/all")
  public ResponseEntity<List<ModuleTO>> getAllModules() {
    return new ResponseEntity<List<ModuleTO>>(moduleMapperTO.toModuleTOList(moduleService.getAllModules()),
        getHeader(), HttpStatus.OK);
  }






  private HttpHeaders getHeader() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-type", "application/json");
    return headers;
  }
}
