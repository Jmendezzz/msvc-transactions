package com.emazon.msvctransactions.infrastructure.adapters.in.security.models;

import com.emazon.msvctransactions.domain.models.User;
import com.emazon.msvctransactions.infrastructure.utils.constants.SecurityConstant;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
  private final Long id;
  private final String email;
  private final Collection<? extends GrantedAuthority> authorities;

  public CustomUserDetails(User user) {
    this.id = user.getId();
    this.email = user.getEmail();
    this.authorities = mapRolesToAuthorities(user.getRole());
  }

  private Collection<? extends GrantedAuthority> mapRolesToAuthorities(String role) {
    return List.of(
            new SimpleGrantedAuthority(SecurityConstant.ROLE_PREFIX + role)
    );
  }
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return email;
  }
  public Long getId() {
    return id;
  }
}
