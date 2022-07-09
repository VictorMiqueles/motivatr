package com.davmt.motivatr.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.davmt.motivatr.MotivatrApplication;
import com.davmt.motivatr.model.Challenge;
import com.davmt.motivatr.model.User;
import com.davmt.motivatr.service.ChallengeService;
import com.davmt.motivatr.service.UserService;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MotivatrApplication.class)
@DatabaseSetup("users-entries.xml")
@DatabaseSetup("challenge-entries.xml")
public class ChallengeServiceTests {

  @Autowired
  private ChallengeService challengeService;

  @Autowired
  private UserService userService;

  private User user;
  private Challenge challenge0 = new Challenge();
  private Challenge challenge1 = new Challenge();
  private Challenge challenge2 = new Challenge();
  private LocalDateTime today = LocalDateTime.now();
  private LocalDateTime yesterday = today.minusDays(1);
  private LocalDateTime dayBeforeYesterday = today.minusDays(2);

  private static Boolean setupDone = false;

  @Before
  public void init() {
    if (setupDone)
      return;

    user = new User("John",
        "James",
        "jjames",
        "jjames@gmail.com",
        "password");
    userService.createUser(user);

    challenge0.setTitle("Challenge 0");
    challenge0.setDescription("Challenge description 0");
    challenge0.setAuthor(user);
    challenge0.setPublishedOn(dayBeforeYesterday);
    challengeService.save(challenge0);

    challenge1.setTitle("Challenge 1");
    challenge1.setDescription("Challenge description 1");
    challenge1.setAuthor(user);
    challenge1.setPublishedOn(yesterday);
    challengeService.save(challenge1);

    challenge2.setTitle("Challenge 2");
    challenge2.setDescription("Challenge description 2");
    challenge2.setAuthor(user);
    challenge2.setPublishedOn(today);
    challengeService.save(challenge2);

    setupDone = true;
  }

  @Test
  public void returnTodaysChallengeIfLatestChallengesDateIsSameAsTodaysDate() {
    assertThat(challengeService.getTodaysChallenge().getTitle()).isEqualTo("Challenge 2");
  }

  @Test
  public void returnNewChallengeIfLatestChallengesDateIsNotTodaysDate() {
    Challenge challenge = challengeService.getChallengeFromId(2L);
    challenge.setPublishedOn(null);
    challengeService.save(challenge);
    assertThat(challengeService.getTodaysChallenge().getTitle()).isEqualTo("Challenge 2");
  }

  @Test
  public void returnYesterdaysChallengeIfThereAreNoNewChallenges() {
    Challenge challenge = challengeService.getChallengeFromId(2L);
    challenge.setPublishedOn(null);
    challengeService.save(challenge);
    assertThat(challengeService.getTodaysChallenge().getTitle()).isEqualTo("Challenge 2");
  }

  @Test
  public void returnListOfUnpublishedChallenges() {
    Challenge challenge = challengeService.getChallengeFromId(2L);
    challenge.setPublishedOn(today);
    challengeService.save(challenge);
    assertThat(challengeService.getUnpublishedChallenges().size()).isEqualTo(0);
    challenge.setPublishedOn(null);
    challengeService.save(challenge);
    assertThat(challengeService.getUnpublishedChallenges().size()).isEqualTo(1);
  }

  @Test
  public void returnListOfPublishedChallenges() {
    Challenge challenge = challengeService.getChallengeFromId(2L);
    challenge.setPublishedOn(yesterday);
    challengeService.save(challenge);
    assertThat(challengeService.getPublishedChallenges().size()).isEqualTo(3);
    challenge.setPublishedOn(null);
    challengeService.save(challenge);
    assertThat(challengeService.getPublishedChallenges().size()).isEqualTo(2);
  }
}
