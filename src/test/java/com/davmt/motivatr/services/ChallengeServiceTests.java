package com.davmt.motivatr.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.davmt.motivatr.model.Challenge;
import com.davmt.motivatr.model.User;
import com.davmt.motivatr.service.ChallengeService;
import com.davmt.motivatr.service.UserService;

@SpringBootTest
public class ChallengeServiceTests {

  @Autowired
  private ChallengeService challengeService;

  @Autowired
  private UserService userService;

  @Test
  public void shouldReturnUnpublishedChallengeTitle() {
    Challenge challenge = new Challenge();
    User user = new User("xxx", "xxx", "xxx", "xxx", "xxx");
    userService.createUser(user);

    challenge.setTitle("Challenge 1");
    challenge.setDescription("Challenge description 1");
    challenge.setAuthor(user);
    challengeService.save(challenge);

    assertThat(challengeService.getTodaysChallenge().getTitle()).isEqualTo("Challenge 1");
  }
}