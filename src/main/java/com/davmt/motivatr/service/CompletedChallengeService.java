package com.davmt.motivatr.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davmt.motivatr.model.Challenge;
import com.davmt.motivatr.model.CompletedChallenge;
import com.davmt.motivatr.model.User;
import com.davmt.motivatr.repository.CompletedChallengeRepository;

@Service
public class CompletedChallengeService {

  @Autowired
  private CompletedChallengeRepository completedChallengeRepository;

  @Autowired
  UserService userService;

  @Autowired
  ChallengeService challengeService;

  private Boolean isChallengeCompleted = false;

  public void addToDb(User user, Challenge todaysChallenge) {
    CompletedChallenge completedChallenge = new CompletedChallenge();

    completedChallenge.setUser(user);
    completedChallenge.setChallenge(todaysChallenge);
    completedChallengeRepository.save(completedChallenge);
  }

  public void removeFromDb(Long userId, Long challengeId) {
    CompletedChallenge completedChallenge = completedChallengeRepository
        .findByUserIdAndChallengeId(userId, challengeId).get(0);
    completedChallengeRepository.delete(completedChallenge);
  }

  public void checkIfChallengeDone(Principal principal, Long challengeId) {
    Challenge todaysChallenge = challengeService.getChallengeFromId(challengeId);
    User user = userService.getUserFromPrincipal(principal);
    Long userId = user.getId();
    Boolean completedChallengeToday = completedChallengeRepository.existsByUserIdAndChallengeId(userId, challengeId);

    if (completedChallengeToday) {
      removeFromDb(userId, challengeId);
    } else {
      addToDb(user, todaysChallenge);
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
