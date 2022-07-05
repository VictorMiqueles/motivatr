package com.davmt.motivatr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {
  @RequestMapping(value = "/")
  public RedirectView index() {
    return new RedirectView("/login");
  }

  @GetMapping("/home")
  public String home() {
    return "home";
  }

}


