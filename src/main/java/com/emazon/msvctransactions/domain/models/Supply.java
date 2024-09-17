package com.emazon.msvctransactions.domain.models;

import com.emazon.msvctransactions.domain.enums.SupplyStatus;

import java.time.LocalDateTime;

public class Supply {
  private Long id;
  private Integer quantity;
  private Long articleId;
  private Long createdBy;
  private SupplyStatus status;
  private LocalDateTime availableAt;
  private LocalDateTime createdAt;

  public Supply(Long id, Integer quantity, Long articleId, Long createdBy, SupplyStatus status, LocalDateTime availableAt, LocalDateTime createdAt) {
    this.id = id;
    this.quantity = quantity;
    this.articleId = articleId;
    this.createdBy = createdBy;
    this.status = status;
    this.availableAt = availableAt;
    this.createdAt = createdAt;
  }
  public Supply() {
  }

  public void setArticleId(Long articleId) {
    this.articleId = articleId;
  }

  public SupplyStatus getStatus() {
    return status;
  }

  public void setStatus(SupplyStatus status) {
    this.status = status;
  }

  public LocalDateTime getAvailableAt() {
    return availableAt;
  }

  public void setAvailableAt(LocalDateTime availableAt) {
    this.availableAt = availableAt;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public void setProductId(Long productId) {
    this.articleId = productId;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public Long getArticleId() {
    return articleId;
  }

  public Long getCreatedBy() {
    return createdBy;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }
}
