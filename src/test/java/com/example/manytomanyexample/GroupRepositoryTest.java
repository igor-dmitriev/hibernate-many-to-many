package com.example.manytomanyexample;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@DataJpaTest
@RunWith(SpringRunner.class)
public class GroupRepositoryTest {
  @Autowired
  GroupRepository groupRepository;

  @PersistenceContext
  EntityManager em;

  @Test
  public void shouldCreateGroupWithUsers() {
    Group group = new Group();
    User user = new User();

    group.addUser(user);

    groupRepository.save(group);

    em.flush();
    em.clear();

    Optional<Group> getById = groupRepository.findById(group.getGroupId());
    Assert.assertTrue(getById.isPresent());
    Assert.assertNotNull(getById.get().getGroupId());
    Assert.assertEquals(1, getById.get().getUsers().size());
    Assert.assertNotNull(getById.get().getUsers().get(0).getUserId());
  }
}
