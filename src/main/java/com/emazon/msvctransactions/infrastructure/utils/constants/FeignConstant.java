package com.emazon.msvctransactions.infrastructure.utils.constants;

public class FeignConstant {
  public static final String USER_CLIENT = "msvc-users";
  public static final String STOCK_CLIENT = "msvc-stock";
  public static final String CART_CLIENT = "msvc-cart";
  public static final String USER_URL = "http://localhost:8091/api/v1/auth";
  public static final String ARTICLE_URL = "http://localhost:8090/api/v1/articles";
  public static final String CART_URL = "http://localhost:8093/api/v1/cart";
  public static final String FEIGN_ERROR_MESSAGE = "An error occurred while trying to communicate with the service";

  private FeignConstant() {
  }
}
