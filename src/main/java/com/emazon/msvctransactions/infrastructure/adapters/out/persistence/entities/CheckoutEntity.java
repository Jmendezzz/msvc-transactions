package com.emazon.msvctransactions.infrastructure.adapters.out.persistence.entities;

import com.emazon.msvctransactions.domain.models.CheckoutStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "checkout")
public class CheckoutEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long userId;
  @CollectionTable(name = "checkout_items", joinColumns = @JoinColumn(name = "checkout_id"))
  @ElementCollection
  private List<CheckoutItemEntity> checkoutItems;
  private String sessionId;
  @Enumerated(EnumType.STRING)
  private CheckoutStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime expiresAt;

}
