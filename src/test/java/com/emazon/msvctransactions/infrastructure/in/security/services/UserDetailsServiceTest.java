package com.emazon.msvctransactions.infrastructure.in.security.services;

import com.emazon.msvctransactions.domain.models.User;
import com.emazon.msvctransactions.domain.ports.out.services.AuthService;
import com.emazon.msvctransactions.infrastructure.adapters.in.security.models.CustomUserDetails;
import com.emazon.msvctransactions.infrastructure.adapters.in.security.services.UserDetailsServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static com.emazon.msvctransactions.infrastructure.utils.constants.SecurityConstant.USERNAME_NOT_FOUND_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserDetailsServiceTest {

  @Mock
  private AuthService authService;

  private UserDetailsServiceImp userDetailsServiceImp;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    userDetailsServiceImp = new UserDetailsServiceImp(authService);
  }

  @Test
  void loadUserByUsername_withValidToken_returnsUserDetails() {
    User user = new User(1L, "test@example.com", "USER");
    when(authService.getUserByToken("validToken")).thenReturn(Optional.of(user));

    CustomUserDetails userDetails = (CustomUserDetails) userDetailsServiceImp.loadUserByUsername("validToken");

    assertEquals(user.getId(), userDetails.getId());
    assertEquals(user.getEmail(), userDetails.getUsername());
  }

  @Test
  void loadUserByUsername_withInvalidToken_throwsUsernameNotFoundException() {
    when(authService.getUserByToken("invalidToken")).thenReturn(Optional.empty());

    Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
      userDetailsServiceImp.loadUserByUsername("invalidToken");
    });

    assertEquals(USERNAME_NOT_FOUND_MESSAGE, exception.getMessage());
  }
}