package com.emazon.msvctransactions.infrastructure.in.security.models;

import com.emazon.msvctransactions.domain.models.User;
import com.emazon.msvctransactions.infrastructure.adapters.in.security.models.CustomUserDetails;
import com.emazon.msvctransactions.infrastructure.utils.constants.SecurityConstant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class CustomUserDetailsTest {

  private CustomUserDetails customUserDetails;

  @BeforeEach
  void setUp() {
    User user = new User(1L, "test@example.com", "USER");
    customUserDetails = new CustomUserDetails(user);
  }

  @Test
  void getId() {
    assertEquals(1L, customUserDetails.getId());
  }

  @Test
  void getUsername() {
    assertEquals("test@example.com", customUserDetails.getUsername());
  }

  @Test
  void getAuthorities() {
    Collection<? extends GrantedAuthority> authorities = customUserDetails.getAuthorities();
    assertEquals(1, authorities.size());
    assertTrue(authorities.stream().anyMatch(a -> a.getAuthority().equals(SecurityConstant.ROLE_PREFIX + "USER")));
  }

  @Test
  void getPassword() {
    assertNull(customUserDetails.getPassword());
  }
}
