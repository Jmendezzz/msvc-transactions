package com.emazon.msvctransactions.application.mappers;

import com.emazon.msvctransactions.application.dtos.supply.CreateSupplyRequestDto;
import com.emazon.msvctransactions.application.dtos.supply.SupplyResponseDto;
import com.emazon.msvctransactions.domain.enums.SupplyStatus;
import com.emazon.msvctransactions.domain.models.Supply;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SupplyDtoMapperTest {

  private SupplyDtoMapperImpl mapper;

  @BeforeEach
  void setUp() {
    mapper = new SupplyDtoMapperImpl();
  }

  @Test
  void whenToDomainCalledWithValidDtoShouldReturnSupply() {
    // Arrange
    CreateSupplyRequestDto requestDto = new CreateSupplyRequestDto(1L, 10, LocalDateTime.now().plusDays(1));

    // Act
    Supply result = mapper.toDomain(requestDto);

    // Assert
    assertNotNull(result);
    assertEquals(1L, result.getArticleId());
    assertEquals(10, result.getQuantity());
    assertEquals(requestDto.availableAt(), result.getAvailableAt());
  }

  @Test
  void whenToDomainCalledWithNullShouldReturnNull() {
    // Act
    Supply result = mapper.toDomain(null);

    // Assert
    assertNull(result);
  }

  @Test
  void whenToDtoCalledWithValidSupplyShouldReturnSupplyResponseDto() {
    // Arrange
    Supply supply = new Supply();
    supply.setId(1L);
    supply.setArticleId(1L);
    supply.setQuantity(10);
    supply.setStatus(SupplyStatus.PENDING);
    supply.setCreatedBy(100L);
    supply.setCreatedAt(LocalDateTime.now());
    supply.setAvailableAt(LocalDateTime.now().plusDays(1));

    // Act
    SupplyResponseDto result = mapper.toDto(supply);

    // Assert
    assertNotNull(result);
    assertEquals(1L, result.id());
    assertEquals(1L, result.articleId());
    assertEquals(10, result.quantity());
    assertEquals(SupplyStatus.PENDING, result.status());
    assertEquals(100L, result.createdBy());
    assertEquals(supply.getCreatedAt(), result.createdAt());
    assertEquals(supply.getAvailableAt(), result.availableAt());
  }

  @Test
  void whenToDtoCalledWithNullShouldReturnNull() {
    // Act
    SupplyResponseDto result = mapper.toDto(null);

    // Assert
    assertNull(result);
  }
}
