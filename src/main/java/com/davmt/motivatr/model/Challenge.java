package com.davmt.motivatr.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CHALLENGES")
public class Challenge {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String description;
  private String imageUrl;
  private String videoUrl;
  private Date publishedOn;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @ManyToOne
  @JoinColumn(name = "author_id", nullable = false)
  private User author;

  @OneToMany(mappedBy = "challenge")
    Set<CompletedChallenge> compleatedChallenges;


  public Challenge() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

}