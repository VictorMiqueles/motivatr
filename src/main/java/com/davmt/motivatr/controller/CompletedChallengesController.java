package com.davmt.motivatr.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public RedirectView markAsDone(Principal principal) {
        
        User user = userService.getUserFromPrincipal(principal);
        Challenge challenge = challengeService.getTodaysChallenge();

        CompletedChallenge completedChallenge = new CompletedChallenge();

        completedChallenge.setUser(user);
        completedChallenge.setChallenge(challenge);

        completedChallengeRepository.save(completedChallenge);
        
        return new RedirectView("/home");
    }

    @DeleteMapping("/notCompletedChallenges")
    public RedirectView markAsNotDone(Principal principal) {
        User user = userService.getUserFromPrincipal(principal);
        Challenge challenge = challengeService.getTodaysChallenge();

        CompletedChallenge completedChallenge = new CompletedChallenge();

        completedChallenge.setUser(user);
        completedChallenge.setChallenge(challenge);

        completedChallengeRepository.delete(completedChallenge);
        
        return new RedirectView("/home");
    }



    @GetMapping("/completedChallenges/check")
    public String checkIfdone(Model model, Principal principal) {
        Challenge todaysChallenge = challengeService.getTodaysChallenge();
        User user = userService.getUserFromPrincipal(principal);

        Long todaysChallengeId = todaysChallenge.getId();
        Long userId = user.getId();

        List<CompletedChallenge> usersCompletedChallenges = completedChallengeRepository.findByUserId(userId);

        String completedChallengeToday = "FALSE"; 

        if (usersCompletedChallenges.contains(todaysChallengeId)) {
                String completedChallengeToday = "TRUE";
        }
        model.addAttribute("todaysChallengeCheck", completedChallengeToday);
    }

}
