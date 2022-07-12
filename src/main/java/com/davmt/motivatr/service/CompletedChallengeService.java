package com.davmt.motivatr.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davmt.motivatr.model.Challenge;
import com.davmt.motivatr.model.CompletedChallenge;
import com.davmt.motivatr.model.User;
import com.davmt.motivatr.repository.CompletedChallengeRepository;

// TODO: Write test for CompletedChallengeService class
@Service
public class CompletedChallengeService {

  @Autowired
  private CompletedChallengeRepository completedChallengeRepository;

  @Autowired
  UserService userService;

  @Autowired
  ChallengeService challengeService;

  private Boolean isChallengeCompleted = false;

  public void addToDb(User user, Challenge currentChallenge) {
    CompletedChallenge completedChallenge = new CompletedChallenge();

    completedChallenge.setUser(user);
    completedChallenge.setChallenge(currentChallenge);
    completedChallengeRepository.save(completedChallenge);
  }

  public void removeFromDb(User user, Challenge curreChallenge) {
    CompletedChallenge completedChallenge = completedChallengeRepository
        .findByUserIdAndChallengeId(user.getId(), curreChallenge.getId()).get(0);
    completedChallengeRepository.delete(completedChallenge);
  }

  public void checkIfChallengeDone(Principal principal, Long challengeId) {
    Challenge currentChallenge = challengeService.getChallengeFromId(challengeId);
    User user = userService.getUserFromPrincipal(principal);
    Long userId = user.getId();
    Boolean completedChallengeToday = completedChallengeRepository.existsByUserIdAndChallengeId(userId, challengeId);

    if (completedChallengeToday) {
      removeFromDb(user, currentChallenge);
    } else {
      addToDb(user, currentChallenge);
    }

    isChallengeCompleted = !completedChallengeToday;
  }

  public Boolean getIsChallengeCompleted() {
    // TODO: check if the date is the same as today
    return isChallengeCompleted;
  }

  public void completeChallenge(User user, Challenge challenge) {
    CompletedChallenge completedChallenge = new CompletedChallenge();

    completedChallenge.setUser(user);
    completedChallenge.setChallenge(challenge);

    save(completedChallenge);
  }

  public void save(CompletedChallenge completedChallenge) {
    completedChallengeRepository.save(completedChallenge);
  }
}
