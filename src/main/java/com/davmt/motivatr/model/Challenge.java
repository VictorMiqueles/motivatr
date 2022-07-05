package com.davmt.motivatr.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import javax.persistence.GenerationType;

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
  private String publishedOn;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public Challenge() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

}