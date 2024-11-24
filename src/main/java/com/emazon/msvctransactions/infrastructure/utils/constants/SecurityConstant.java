package com.emazon.msvctransactions.infrastructure.utils.constants;

public class SecurityConstant {
  public static final String ROLE_PREFIX = "ROLE_";
  public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this resource";
  public static final String INVALID_TOKEN_MESSAGE = "Invalid token";
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String USERNAME_NOT_FOUND_MESSAGE = "User not found";
  public static final String ADMIN_ROLE = "ADMIN";
  public static final String WAREHOUSE_ASSISTANT_ROLE = "WAREHOUSE_ASSISTANT";
  public static final String MACHINE_HEADER = "X-Machine-Key";
  public static final String ERROR_URL = "/error";
  public static final String SWAGGER_UI_URL = "/swagger-ui/**";
  public static final String SWAGGER_API_DOCS_URL = "/v3/api-docs/**";
  public static final String SUPPLY_URL = "/api/v1/supplies/**";


  private SecurityConstant() {
  }
}
