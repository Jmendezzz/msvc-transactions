package com.emazon.msvctransactions.domain.models;

import java.time.LocalDateTime;
import java.util.List;

public class Checkout {
  private Long id;
  private Long userId;
  private List<CheckoutItem> checkoutItems;
  private String sessionId;
  private CheckoutStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime expiresAt;

  public Checkout(Long id, Long userId, List<CheckoutItem> checkoutItems, String sessionId, CheckoutStatus status, LocalDateTime createdAt, LocalDateTime expiresAt) {
    this.id = id;
    this.userId = userId;
    this.checkoutItems = checkoutItems;
    this.sessionId = sessionId;
    this.status = status;
    this.createdAt = createdAt;
    this.expiresAt = expiresAt;
  }

  public Checkout() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(LocalDateTime expiresAt) {
    this.expiresAt = expiresAt;
  }

  public CheckoutStatus getStatus() {
    return status;
  }

  public void setStatus(CheckoutStatus status) {
    this.status = status;
  }

  public List<CheckoutItem> getCheckoutItems() {
    return checkoutItems;
  }

  public void setCheckoutItems(List<CheckoutItem> checkoutItems) {
    this.checkoutItems = checkoutItems;
  }
}
