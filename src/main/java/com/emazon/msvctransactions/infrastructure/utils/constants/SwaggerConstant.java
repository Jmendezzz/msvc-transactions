package com.emazon.msvctransactions.infrastructure.utils.constants;

public class SwaggerConstant {
  public static final String API_TITLE = "Transactions Microservice API";
  public static final String API_DESCRIPTION = "Transactions API Documentation contains all the endpoints to manage transactions in the system.";
  public static final String API_VERSION = "1.0.0";
  public static final String CONTACT_NAME = "Juan Gerardo Méndez López";
  public static final String CONTACT_EMAIL = "juange.mendez.lopez@gmail.com";
  public static final String SERVER_URL = "http://localhost:8092";
  public static final String SERVER_DESCRIPTION = "Local Dev Server";
  public static final String SECURITY_NAME = "JWT Token";
  public static final String SCHEME = "bearer";
  public static final String BEARER_FORMAT = "JWT";
  public static final String CREATE_SUPPLY_OPERATION = "Create a new supply";
  public static final String CREATE_SUPPLY_NOTE = "This operation can only be performed by a user with the Warehouse Assistant role";
  public static final String CREATE_SUPPLY_RESPONSE = "Returns the created supply";
  public static final String AUTHORIZATION_HEADER = "Authorization";
  public static final String AUTHORIZATION_DESCRIPTION = "Bearer token for authorization";
  public static final String STATUS_201 = "201";
  public static final String STATUS_401 = "401";
  public static final String STATUS_403 = "403";
  public static final String STATUS_201_DESCRIPTION = "Created";
  public static final String STATUS_401_DESCRIPTION = "Unauthorized";
  public static final String STATUS_403_DESCRIPTION = "Forbidden";
  public static final String CREATE_SUPPLY_REQUEST_DESCRIPTION = "The supply to create";
  private SwaggerConstant() {
  }
}
