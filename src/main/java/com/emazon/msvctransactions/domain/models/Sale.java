package com.emazon.msvctransactions.domain.models;

import com.emazon.msvctransactions.domain.enums.SaleStatus;

import java.time.LocalDateTime;
import java.util.List;

public class Sale {
  private Long id;
  private Long userId;
  private SaleStatus status;
  private List<SaleDetail> saleDetails;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime reservationExpiresAt;

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

  public SaleStatus getStatus() {
    return status;
  }

  public void setStatus(SaleStatus status) {
    this.status = status;
  }

  public List<SaleDetail> getSaleDetails() {
    return saleDetails;
  }

  public void setSaleDetails(List<SaleDetail> saleDetails) {
    this.saleDetails = saleDetails;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public LocalDateTime getReservationExpiresAt() {
    return reservationExpiresAt;
  }

  public void setReservationExpiresAt(LocalDateTime reservationExpiresAt) {
    this.reservationExpiresAt = reservationExpiresAt;
  }
}
