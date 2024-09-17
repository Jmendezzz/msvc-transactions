package com.emazon.msvctransactions.infrastructure.utils.constants;

import org.springframework.beans.factory.annotation.Value;

public class SecurityConstant {
  public static final String ROLE_PREFIX = "ROLE_";
  public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this resource";
  public static final String INVALID_TOKEN_MESSAGE = "Invalid token";
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String USERNAME_NOT_FOUND_MESSAGE = "User not found";
  public static final String ADMIN_ROLE = "ADMIN";
  public static final String WAREHOUSE_ASSISTANT_ROLE = "WAREHOUSE_ASSISTANT";

  public static final String MACHINE_HEADER = "X-Machine-Key";

  private SecurityConstant() {
  }
}
