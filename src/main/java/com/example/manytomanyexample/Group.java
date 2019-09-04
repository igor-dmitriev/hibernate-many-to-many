package com.example.manytomanyexample;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "groups")
class Group {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_seq")
  @SequenceGenerator(name = "group_seq", sequenceName = "group_seq")
  private Long groupId;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(
      name = "group_user",
      joinColumns = @JoinColumn(name = "group_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id")
  )
  private List<User> users = new ArrayList<>();

  public void addUser(User user) {
    users.add(user);
    user.getGroups().add(this);
  }

  public List<User> getUsers() {
    return users;
  }

  public Long getGroupId() {
    return groupId;
  }
}