package com.emazon.msvctransactions.domain.utils.constants.supply;

public class SupplyExceptionMessage {
  public static final String EMPTY_SUPPLY_QUANTITY = "Supply quantity cannot be empty";
  public static final String INVALID_SUPPLY_QUANTITY = "Invalid supply quantity must be greater than 0";
  public static final String EMPTY_ARTICLE_ID = "Article id cannot be empty";
  public static final String EMPTY_AVAILABLE_AT = "Available at cannot be empty";
  public static final String INVALID_AVAILABLE_AT = "Invalid available at date must be in the future";
  public static final String ARTICLE_NOT_FOUND = "Article not found";
  public static final String SUPPLY_NOT_FOUND = "There is no supply available for this article";
  private SupplyExceptionMessage() {
  }
}
