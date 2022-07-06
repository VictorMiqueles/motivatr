package com.davmt.motivatr.model;

import static java.lang.Boolean.TRUE;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "USERS")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private String imageUrl;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private Boolean enabled;
  @Transient
  private String passwordConfirm;

  @OneToMany(mappedBy = "author")
  private Set<Challenge> challenges;

  @ManyToMany
  @JoinTable(name = "challenges_completions", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "challenge_id"))
  private Set<Challenge> completedChallenges;

  public User() {
    this.createdAt = LocalDateTime.now();
    this.enabled = TRUE;
  }

  public User(String firstName, String lastName, String username, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
    this.password = password;
    this.createdAt = LocalDateTime.now();
    this.enabled = TRUE;
  }

}