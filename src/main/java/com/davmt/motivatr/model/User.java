package com.davmt.motivatr.model;

import static java.lang.Boolean.TRUE;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

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

  @OneToMany(mappedBy = "user")
  Set<CompletedChallenge> completedChallenges;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "users_data_id", referencedColumnName = "id")
  private UsersData usersData;

  public User() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
    this.enabled = TRUE;
  }

  public User(String firstName, String lastName, String username, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
    this.email = email;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
    this.enabled = TRUE;
  }
}