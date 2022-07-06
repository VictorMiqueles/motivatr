package com.davmt.motivatr.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.davmt.motivatr.service.ChallengeService;
import com.davmt.motivatr.service.UserService;

@Controller
public class ChallengesController {

  @Autowired
  ChallengeService challengeService;

  @Autowired
  UserService userService;

  @GetMapping("/challenges")
  public String listChallenges(Model model, Principal principal) {
    model.addAttribute("challenge", challengeService.getTodaysChallenge());
    model.addAttribute("principal", userService.getUserFromPrincipal(principal));
    model.addAttribute("topten", userService.getTopTenUsers());
    return "challenges";
  }

}
