package com.emazon.msvctransactions.infrastructure.adapters.out.persistence.entities;

import com.emazon.msvctransactions.domain.enums.SupplyStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "supplies")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SupplyEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private Integer quantity;
  @Column(nullable = false)
  private Long articleId;
  @Column(nullable = false)
  private Long createdBy;
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private SupplyStatus status;
  @Column(nullable = false)
  private LocalDateTime availableAt;
  @Column(nullable = false)
  private LocalDateTime createdAt;

}
