package com.emazon.msvctransactions.domain.models;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

  private User user;

  @BeforeEach
  public void setUp() {
    user = new User(1L,"test@example.com","ADMIN");
  }

  @Test
  void testSetAndGetId() {
    Long id = 1L;
    user.setId(id);
    assertEquals(id, user.getId());
  }

  @Test
   void testSetAndGetEmail() {
    String email = "test@example.com";
    user.setEmail(email);
    assertEquals(email, user.getEmail());
  }

  @Test
  void testSetAndGetRole() {
    String role = "admin";
    user.setRole(role);
    assertEquals(role, user.getRole());
  }
}