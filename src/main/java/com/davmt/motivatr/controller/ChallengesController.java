package com.davmt.motivatr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.davmt.motivatr.repository.ChallengeRepository;

@Controller
public class ChallengesController {

  @Autowired
  ChallengeRepository challengeRepository;

  @GetMapping("/challenges")
  public String listChallenges(Model model) {
    return null;
  }

}
