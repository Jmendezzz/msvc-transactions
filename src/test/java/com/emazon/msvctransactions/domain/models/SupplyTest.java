package com.emazon.msvctransactions.domain.models;

import com.emazon.msvctransactions.domain.enums.SupplyStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplyTest {

  private Supply supply;

  @BeforeEach
  public void setUp() {
    supply = new Supply();
  }

  @Test
  void testSetAndGetId() {
    Long id = 1L;
    supply.setId(id);
    assertEquals(id, supply.getId());
  }

  @Test
  void testSetAndGetQuantity() {
    Integer quantity = 10;
    supply.setQuantity(quantity);
    assertEquals(quantity, supply.getQuantity());
  }

  @Test
  void testSetAndGetArticleId() {
    Long articleId = 1L;
    supply.setArticleId(articleId);
    assertEquals(articleId, supply.getArticleId());
  }

  @Test
  void testSetAndGetCreatedBy() {
    Long createdBy = 1L;
    supply.setCreatedBy(createdBy);
    assertEquals(createdBy, supply.getCreatedBy());
  }

  @Test
  void testSetAndGetStatus() {
    SupplyStatus status = SupplyStatus.PENDING;
    supply.setStatus(status);
    assertEquals(status, supply.getStatus());
  }

  @Test
  void testSetAndGetAvailableAt() {
    LocalDateTime availableAt = LocalDateTime.now();
    supply.setAvailableAt(availableAt);
    assertEquals(availableAt, supply.getAvailableAt());
  }

  @Test
  void testSetAndGetCreatedAt() {
    LocalDateTime createdAt = LocalDateTime.now();
    supply.setCreatedAt(createdAt);
    assertEquals(createdAt, supply.getCreatedAt());
  }
}