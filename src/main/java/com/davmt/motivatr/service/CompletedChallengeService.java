package com.davmt.motivatr.service;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import com.davmt.motivatr.model.Challenge;
import com.davmt.motivatr.model.CompletedChallenge;
import com.davmt.motivatr.model.User;
import com.davmt.motivatr.repository.ChallengeRepository;
import com.davmt.motivatr.repository.CompletedChallengeRepository;
import com.davmt.motivatr.repository.UserRepository;

@Service
public class CompletedChallengeService {

  @Autowired
  private CompletedChallengeRepository completedChallengeRepository;

  @Autowired
  UserService userService;

  @Autowired
  ChallengeService challengeService;


  public void markAsDone(User user, Challenge todaysChallenge) {

      CompletedChallenge completedChallenge = new CompletedChallenge();

      completedChallenge.setUser(user);
      completedChallenge.setChallenge(todaysChallenge);

      completedChallengeRepository.save(completedChallenge);
  }


  public void markAsNotDone(User user, Challenge todaysChallenge) {

      CompletedChallenge completedChallenge = new CompletedChallenge();

      completedChallenge.setUser(user);
      completedChallenge.setChallenge(todaysChallenge);

      completedChallengeRepository.delete(completedChallenge);
  }


  public Boolean checkIfChallengeDone( Principal principal, Long challengeId) {
      Challenge todaysChallenge = challengeService.getChallengeFromId(challengeId);

      User user = userService.getUserFromPrincipal(principal);
      Long userId = user.getId();

      Boolean completedChallengeToday = completedChallengeRepository.existsByUserIdAndChallengeId(userId, challengeId);

      if (completedChallengeToday) {
          // markAsNotDone(user, todaysChallenge);
          completedChallengeToday = false;
      }
      else {
        // markAsDone(user, todaysChallenge);
        completedChallengeToday = true;
      }

      return completedChallengeToday;
  }
      

  public void save(CompletedChallenge completedChallenge) {
    completedChallengeRepository.save(completedChallenge);
  }
}
