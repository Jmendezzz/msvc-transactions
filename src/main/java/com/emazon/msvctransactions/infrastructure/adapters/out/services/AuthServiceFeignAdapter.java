package com.emazon.msvctransactions.infrastructure.adapters.out.services;

import com.emazon.msvctransactions.domain.models.User;
import com.emazon.msvctransactions.domain.ports.out.services.AuthService;
import com.emazon.msvctransactions.infrastructure.adapters.in.security.models.CustomUserDetails;
import com.emazon.msvctransactions.infrastructure.adapters.out.feign.AuthClient;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceFeignAdapter implements AuthService {
  private final AuthClient authClient;
  @Override
  public Optional<User> getUserByToken(String token) {
    try{
       User user =  authClient.getUserDetails(token);
       return Optional.of(user);
    }catch (Exception e){
      return Optional.empty();
    }

  }

  @Override
  public Long getUserIdAuthenticated() {
    CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    return customUserDetails.getId();
  }
}
