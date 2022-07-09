package com.davmt.motivatr.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.davmt.motivatr.model.Challenge;
import com.davmt.motivatr.model.CompletedChallenge;

public interface CompletedChallengeRepository extends CrudRepository<CompletedChallenge, Long> {

  public List<CompletedChallenge> findByUserId(Long user_id);

}