package com.davmt.motivatr.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davmt.motivatr.model.Challenge;
import com.davmt.motivatr.model.User;
import com.davmt.motivatr.repository.ChallengeRepository;
import com.davmt.motivatr.repository.CompletedChallengeRepository;

@Service
public class ChallengeService {

  @Autowired
  private ChallengeRepository challengeRepository;
  
    @Autowired
  private CompletedChallengeRepository completedChallengeRepository;


  public Challenge getChallengeFromId (Long challenge_id) {
    Optional<Challenge> challengeOptionsl = challengeRepository.findById(challenge_id);
    Challenge challenge = challengeOptionsl.get();
    return challenge;
  }

  public Challenge getTodaysChallenge() {
    List<Challenge> unpublishedChallenges = getUnpublishedChallenges();
    List<Challenge> publishedChallenges = getPublishedChallenges();
    LocalDate today = LocalDateTime.now().toLocalDate();
    LocalDate mostRecentChallengeDate;

    if (challengeRepository.count() == 0) {
      return null;
    }

    if (publishedChallenges.size() > 0) {
      mostRecentChallengeDate = publishedChallenges.get(0).getPublishedOn().toLocalDate();

      if (today.equals(mostRecentChallengeDate) || unpublishedChallenges.size() == 0) {
        return publishedChallenges.get(0);
      }
    }

    return unpublishedChallenges.get(0);
  }

  public void save(Challenge challenge) {
    challengeRepository.save(challenge);
  }

  public Challenge getChallengeFromId(Long id) {
    Optional<Challenge> challengeOptional = challengeRepository.findById(id);
    Challenge challenge = challengeOptional.get();

    return challenge;
  }

  public List<Challenge> getUnpublishedChallenges() {
    return challengeRepository.findAllByPublishedOnIsNull();
  }

  public List<Challenge> getPublishedChallenges() {
    return challengeRepository.findAllByPublishedOnIsNotNullOrderByPublishedOnDesc();
  }

  public List<HashMap<Challenge, Boolean>> getPublishedChallengesWithStatus(User user) {
    List<HashMap<Challenge, Boolean>> returnList;
    List<Challenge> challenges = getPublishedChallenges();

    // for (Challenge challenge : challenges) {
    // if (completedChallengeRepository.findByUserIdAndChallengeId(user.id,
    // challenge.getId())) {
    // returnList.add(challenge, true);
    // } else {
    // returnList.put(challenge, false);
    // }
    // }
    // return returnList;

    return null;
  }
}
