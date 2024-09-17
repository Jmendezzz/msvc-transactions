package com.emazon.msvctransactions.infrastructure.adapters.in.security.services;


import com.emazon.msvctransactions.domain.models.User;
import com.emazon.msvctransactions.domain.ports.out.services.AuthService;
import com.emazon.msvctransactions.infrastructure.adapters.in.security.models.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.emazon.msvctransactions.infrastructure.utils.constants.SecurityConstant.USERNAME_NOT_FOUND_MESSAGE;

@Service
@AllArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {
  private final AuthService authService;

  @Override
  public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
    Optional<User> user = authService.getUserByToken(token);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException(USERNAME_NOT_FOUND_MESSAGE);
    }
    return new CustomUserDetails(user.get());
  }
}
