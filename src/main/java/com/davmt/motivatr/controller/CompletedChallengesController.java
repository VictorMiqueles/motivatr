package com.davmt.motivatr.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.davmt.motivatr.model.Challenge;
import com.davmt.motivatr.model.CompletedChallenge;
import com.davmt.motivatr.model.User;
import com.davmt.motivatr.repository.CompletedChallengeRepository;
import com.davmt.motivatr.repository.UserRepository;
import com.davmt.motivatr.service.ChallengeService;
import com.davmt.motivatr.service.UserService;

@Controller
public class CompletedChallengesController {

  @Autowired
  ChallengeService challengeService;

  @Autowired
  UserRepository userRepository;

  @Autowired
  CompletedChallengeRepository completedChallengeRepository;

  @Autowired
  UserService userService;

  @PostMapping("/completedChallenges")
  public RedirectView create(Principal principal) {

    User user = userService.getUserFromPrincipal(principal);
    // Long userId = user.getId();
    Challenge challenge = challengeService.getTodaysChallenge();

    CompletedChallenge completedChallenge = new CompletedChallenge();

    completedChallenge.setUser(user);
    completedChallenge.setChallenge(challenge);

    completedChallengeRepository.save(completedChallenge);

    return new RedirectView("/home");
  }

}
