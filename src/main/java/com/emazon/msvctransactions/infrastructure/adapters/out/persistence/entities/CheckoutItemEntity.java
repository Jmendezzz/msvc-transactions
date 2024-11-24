package com.emazon.msvctransactions.infrastructure.adapters.out.persistence.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class CheckoutItemEntity {
  private Long articleId;
  private String articleName;
  private Integer quantity;
  private Double price;

}
