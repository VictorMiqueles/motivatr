package com.davmt.motivatr.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.davmt.motivatr.service.ChallengeService;

@Controller
public class HomeController {

  @Autowired
  ChallengeService challengeService;

  @RequestMapping(value = "/")
  public RedirectView index() {
    return new RedirectView("/login");
  }

  @GetMapping("/home")
  public String home(Model model, Principal principal) {

    model.addAttribute("challenge", challengeService.getTodaysChallenge());

    // return current user object
    // so we can pass it to the template
    // and access points/streak, etc

    // get the top 10 players
    // pass it to the template

    return "home";
  }

}
