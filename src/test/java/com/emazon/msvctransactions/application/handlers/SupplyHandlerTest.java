package com.emazon.msvctransactions.application.handlers;

import com.emazon.msvctransactions.application.dtos.supply.CreateSupplyRequestDto;
import com.emazon.msvctransactions.application.dtos.supply.SupplyResponseDto;
import com.emazon.msvctransactions.application.handlers.imp.SupplyHandlerImp;
import com.emazon.msvctransactions.application.mappers.SupplyDtoMapper;
import com.emazon.msvctransactions.domain.enums.SupplyStatus;
import com.emazon.msvctransactions.domain.models.Supply;
import com.emazon.msvctransactions.domain.ports.in.usecases.SupplyUseCase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class SupplyHandlerTest {
  @Mock
  private SupplyUseCase supplyUseCase;

  @Mock
  private SupplyDtoMapper mapper;

  @InjectMocks
  private SupplyHandlerImp supplyHandler;

  @Test
  void whenCreateSupplyCalledShouldReturnSupplyResponseDto() {
    // Arrange
// Arrange
    CreateSupplyRequestDto requestDto = new CreateSupplyRequestDto(1L, 10, LocalDateTime.now().plusDays(1));
    Supply supply = new Supply();
    SupplyResponseDto responseDto = new SupplyResponseDto(1L, 1L, 10, SupplyStatus.PENDING, 100L, LocalDateTime.now(), LocalDateTime.now().plusDays(1));


    when(mapper.toDomain(any(CreateSupplyRequestDto.class))).thenReturn(supply);
    when(supplyUseCase.createSupply(any(Supply.class))).thenReturn(supply);
    when(mapper.toDto(any(Supply.class))).thenReturn(responseDto);

    // Act
    SupplyResponseDto result = supplyHandler.createSupply(requestDto);

    // Assert
    assertEquals(responseDto, result);
    verify(mapper).toDomain(requestDto);
    verify(supplyUseCase).createSupply(supply);
    verify(mapper).toDto(supply);
  }

  @Test
  void whenUpdateStockCalledShouldCallUpdateStockOnSupplyUseCase() {
    // Act
    supplyHandler.updateStock();

    // Assert
    verify(supplyUseCase).updateStock();
  }
}
