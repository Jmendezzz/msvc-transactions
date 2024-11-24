package com.emazon.msvctransactions.domain.models;

import java.time.LocalDateTime;
import java.util.List;

public record Article(
    Long id,
    String name,
    String description,
    Double price,
    Integer stock,
    LocalDateTime supplyDate
) {
  public Article withSupplyDate(LocalDateTime supplyDate) {
    return new Article(id, name, description, price, stock, supplyDate);
  }
}
