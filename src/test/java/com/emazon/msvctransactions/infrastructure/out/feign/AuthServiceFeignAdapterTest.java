package com.emazon.msvctransactions.infrastructure.out.feign;

import com.emazon.msvctransactions.domain.models.User;
import com.emazon.msvctransactions.infrastructure.adapters.in.security.models.CustomUserDetails;
import com.emazon.msvctransactions.infrastructure.adapters.out.feign.AuthClient;
import com.emazon.msvctransactions.infrastructure.adapters.out.services.AuthServiceFeignAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceFeignAdapterTest {

  @Mock
  private AuthClient authClient;

  @Mock
  private Authentication authentication;

  @Mock
  private CustomUserDetails customUserDetails;

  @InjectMocks
  private AuthServiceFeignAdapter authServiceFeignAdapter;

  @Test
  void whenGetUserByTokenCalledWithValidTokenShouldReturnUser() {
    // Arrange
    User user = new User(1L, "john.doe@example.com", "ADMIN");
    when(authClient.getUserDetails(anyString())).thenReturn(user);

    // Act
    Optional<User> result = authServiceFeignAdapter.getUserByToken("validToken");

    // Assert
    assertTrue(result.isPresent());
    assertEquals("john.doe@example.com", result.get().getEmail());
    verify(authClient).getUserDetails("validToken");
  }

  @Test
  void whenGetUserByTokenThrowsExceptionShouldReturnEmptyOptional() {
    // Arrange
    when(authClient.getUserDetails(anyString())).thenThrow(new RuntimeException("Error occurred"));

    // Act
    Optional<User> result = authServiceFeignAdapter.getUserByToken("invalidToken");

    // Assert
    assertTrue(result.isEmpty());
    verify(authClient).getUserDetails("invalidToken");
  }

  @Test
  void whenGetUserIdAuthenticatedCalledShouldReturnAuthenticatedUserId() {
    // Mock the static SecurityContextHolder
    try (MockedStatic<SecurityContextHolder> mockedSecurityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
      // Arrange
      SecurityContext securityContext = mock(SecurityContext.class);
      mockedSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
      when(securityContext.getAuthentication()).thenReturn(authentication);
      when(authentication.getPrincipal()).thenReturn(customUserDetails);
      when(customUserDetails.getId()).thenReturn(123L);

      // Act
      Long result = authServiceFeignAdapter.getUserIdAuthenticated();

      // Assert
      assertEquals(123L, result);
      verify(customUserDetails).getId();
    }
  }
}
