package com.davmt.motivatr.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davmt.motivatr.model.Challenge;
import com.davmt.motivatr.repository.ChallengeRepository;

@Service
public class ChallengeService {

  @Autowired
  private ChallengeRepository challengeRepository;

  @Autowired
  private CompletedChallengeRepository completedChallengeRepository;

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

  public List<HashMap<Challenge, Boolean>> getPublishedChallengesWithStatus() {
    List<HashMap<Challenge, Boolean>>  returnList;
    List<Challenge> challenges = getPublishedChallenges();

    for (challenge : challenges) {
      if (completedChallengeRepository.findByUserIdAndChallengeId(id, challenge.getId())) {
        returnList.put(challenge, true);
      } else {
        returnList.put(challenge, false);
      }
    }
    return returnList;
  }
}
