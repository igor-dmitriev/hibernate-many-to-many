package com.example.manytomanyexample;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
  @SequenceGenerator(name = "users_seq", sequenceName = "users_seq")
  private Long userId;

  @ManyToMany(mappedBy = "users")
  private List<Group> groups = new ArrayList<>();

  public List<Group> getGroups() {
    return groups;
  }

  public Long getUserId() {
    return userId;
  }
}