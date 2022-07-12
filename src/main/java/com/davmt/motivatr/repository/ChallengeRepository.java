package com.davmt.motivatr.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.davmt.motivatr.model.Challenge;

public interface ChallengeRepository extends CrudRepository<Challenge, Long> {

  public List<Challenge> findAllByPublishedOnIsNull();
}