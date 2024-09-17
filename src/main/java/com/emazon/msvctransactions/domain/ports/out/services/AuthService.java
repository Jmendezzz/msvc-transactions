package com.emazon.msvctransactions.domain.ports.out.services;

import com.emazon.msvctransactions.domain.models.User;

import java.util.Optional;

public interface AuthService {
  Optional<User> getUserByToken(String token);
  Long getUserIdAuthenticated();
}
