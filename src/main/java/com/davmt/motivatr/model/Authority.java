package com.davmt.motivatr.model;

import javax.persistence.*;

@Entity
@Table(name = "AUTHORITIES")
public class Authority {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String authority;

  public Authority() {
  }

  public Authority(String username, String authority) {
    this.username = username;
    this.authority = authority;
  }
}
