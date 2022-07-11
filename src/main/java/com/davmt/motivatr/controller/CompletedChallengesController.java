package com.davmt.motivatr.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.davmt.motivatr.model.Challenge;
import com.davmt.motivatr.model.CompletedChallenge;
import com.davmt.motivatr.model.User;
import com.davmt.motivatr.repository.CompletedChallengeRepository;
import com.davmt.motivatr.repository.UserRepository;
import com.davmt.motivatr.service.ChallengeService;
import com.davmt.motivatr.service.CompletedChallengeService;
import com.davmt.motivatr.service.UserService;

@Controller
public class CompletedChallengesController {

    @Autowired
    CompletedChallengeService completedChallengeService;

  @Autowired
  UserRepository userRepository;

  @Autowired
  CompletedChallengeRepository completedChallengeRepository;

  @Autowired
  UserService userService;

    @GetMapping("/completedChallenges/{id}")
    public RedirectView listCompletedChallenges(Model model, Principal principal, @PathVariable Long id) {
        model.addAttribute("checkIfChallengeDone", completedChallengeService.checkIfChallengeDone(principal, id));
        return new RedirectView("home");
    }
}
