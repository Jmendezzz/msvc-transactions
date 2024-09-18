package com.emazon.msvctransactions.infrastructure.out.mappers;

import com.emazon.msvctransactions.domain.enums.SupplyStatus;
import com.emazon.msvctransactions.domain.models.Supply;
import com.emazon.msvctransactions.infrastructure.adapters.out.persistence.entities.SupplyEntity;
import com.emazon.msvctransactions.infrastructure.adapters.out.persistence.mappers.SupplyEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class SupplyEntityMapperTest {
  private SupplyEntityMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = Mappers.getMapper(SupplyEntityMapper.class);
  }

  @Test
  void whenToEntityCalledWithValidSupplyShouldReturnSupplyEntity() {
    // Arrange
    Supply supply = new Supply();
    supply.setId(1L);
    supply.setQuantity(10);
    supply.setArticleId(100L);
    supply.setCreatedBy(200L);
    supply.setStatus(SupplyStatus.PENDING);
    supply.setAvailableAt(LocalDateTime.now().plusDays(1));
    supply.setCreatedAt(LocalDateTime.now());

    // Act
    SupplyEntity result = mapper.toEntity(supply);

    // Assert
    assertNotNull(result);
    assertEquals(1L, result.getId());
    assertEquals(10, result.getQuantity());
    assertEquals(100L, result.getArticleId());
    assertEquals(200L, result.getCreatedBy());
    assertEquals(SupplyStatus.PENDING, result.getStatus());
    assertEquals(supply.getAvailableAt(), result.getAvailableAt());
    assertEquals(supply.getCreatedAt(), result.getCreatedAt());
  }

  @Test
  void whenToEntityCalledWithNullShouldReturnNull() {
    // Act
    SupplyEntity result = mapper.toEntity(null);

    // Assert
    assertNull(result);
  }

  @Test
  void whenToDomainCalledWithValidSupplyEntityShouldReturnSupply() {
    // Arrange
    SupplyEntity supplyEntity = new SupplyEntity();
    supplyEntity.setId(1L);
    supplyEntity.setQuantity(10);
    supplyEntity.setArticleId(100L);
    supplyEntity.setCreatedBy(200L);
    supplyEntity.setStatus(SupplyStatus.PENDING);
    supplyEntity.setAvailableAt(LocalDateTime.now().plusDays(1));
    supplyEntity.setCreatedAt(LocalDateTime.now());

    // Act
    Supply result = mapper.toDomain(supplyEntity);

    // Assert
    assertNotNull(result);
    assertEquals(1L, result.getId());
    assertEquals(10, result.getQuantity());
    assertEquals(100L, result.getArticleId());
    assertEquals(200L, result.getCreatedBy());
    assertEquals(SupplyStatus.PENDING, result.getStatus());
    assertEquals(supplyEntity.getAvailableAt(), result.getAvailableAt());
    assertEquals(supplyEntity.getCreatedAt(), result.getCreatedAt());
  }

  @Test
  void whenToDomainCalledWithNullShouldReturnNull() {
    // Act
    Supply result = mapper.toDomain((SupplyEntity) null);

    // Assert
    assertNull(result);
  }

  @Test
  void whenToDomainListCalledWithValidSupplyEntitiesShouldReturnSupplyList() {
    // Arrange
    SupplyEntity supplyEntity1 = new SupplyEntity();
    supplyEntity1.setId(1L);
    supplyEntity1.setQuantity(10);
    supplyEntity1.setArticleId(100L);
    supplyEntity1.setStatus(SupplyStatus.PENDING);

    SupplyEntity supplyEntity2 = new SupplyEntity();
    supplyEntity2.setId(2L);
    supplyEntity2.setQuantity(20);
    supplyEntity2.setArticleId(200L);
    supplyEntity2.setStatus(SupplyStatus.DELIVERED);

    List<SupplyEntity> supplyEntities = List.of(supplyEntity1, supplyEntity2);

    // Act
    List<Supply> result = mapper.toDomain(supplyEntities);

    // Assert
    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(1L, result.get(0).getId());
    assertEquals(10, result.get(0).getQuantity());
    assertEquals(2L, result.get(1).getId());
    assertEquals(20, result.get(1).getQuantity());
  }

  @Test
  void whenToDomainListCalledWithNullShouldReturnNull() {
    // Act
    List<Supply> result = mapper.toDomain((List<SupplyEntity>) null);

    // Assert
    assertNull(result);
  }
}
