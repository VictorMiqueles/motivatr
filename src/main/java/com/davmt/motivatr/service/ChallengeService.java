package com.davmt.motivatr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davmt.motivatr.model.Challenge;
import com.davmt.motivatr.repository.ChallengeRepository;

@Service
public class ChallengeService {

  @Autowired
  private ChallengeRepository challengeRepository;

  public Challenge getTodaysChallenge() {
    List<Challenge> unpublishedChallenges = challengeRepository.findAllByPublishedOnIsNull();
    if (unpublishedChallenges.size() == 0) {
      return null;
    }
    return unpublishedChallenges.get(0);
  }

  public void save(Challenge challenge) {
    challengeRepository.save(challenge);
  }
}
