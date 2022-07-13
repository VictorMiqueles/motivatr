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

  private Boolean highestStreakChanged = false;

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

  public void addToStreak(User user) {
    Integer streak = user.getUsersData().getStreak();
    user.getUsersData().setStreak(streak + 1);

    Integer totalCompleted = user.getUsersData().getTotalCompleted();
    user.getUsersData().setTotalCompleted(totalCompleted + 1);

    Integer highestStreak = user.getUsersData().getHighestStreak();
    if (streak + 1 > highestStreak) {
      user.getUsersData().setHighestStreak(streak + 1);
      highestStreakChanged = true;
    }
    userService.save(user);
  }

  public void removeFromStreak(User user) {
    Integer streak = user.getUsersData().getStreak();
    user.getUsersData().setStreak(streak - 1);

    Integer totalCompleted = user.getUsersData().getTotalCompleted();
    user.getUsersData().setTotalCompleted(totalCompleted - 1);

    Integer highestStreak = user.getUsersData().getHighestStreak();
    if (highestStreakChanged) {
      user.getUsersData().setHighestStreak(highestStreak - 1);
      highestStreakChanged = false;
    }
    userService.save(user);
  }

  public void toggleChallengeStatus(Principal principal, Long challengeId) {
    Challenge currentChallenge = challengeService.getChallengeFromId(challengeId);
    User user = userService.getUserFromPrincipal(principal);
    if (checkChallengeStatus(principal, currentChallenge)) {
      removeFromDb(user, currentChallenge);
      removeFromStreak(user);
    } else {
      addToDb(user, currentChallenge);
      addToStreak(user);
    }
  }

  public Boolean checkChallengeStatus(Principal principal, Challenge challenge) {
    User user = userService.getUserFromPrincipal(principal);
    Long userId = user.getId();
    Long challengeId = challenge.getId();
    Boolean isCurrentChallengeCompleted = completedChallengeRepository.existsByUserIdAndChallengeId(userId,
        challengeId);
    return isCurrentChallengeCompleted;
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
