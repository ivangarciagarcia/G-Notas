package com.wirtz.fpdual.proyecto.e2.apirest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontController {
  @RequestMapping("/login")
  public String login(){
    return "login";
  }

  @RequestMapping({"/index", "/"})
  public String index(){
    return "index";
  }

}
