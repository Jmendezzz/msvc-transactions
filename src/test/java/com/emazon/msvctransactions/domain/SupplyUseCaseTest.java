package com.emazon.msvctransactions.domain;

import com.emazon.msvctransactions.domain.exceptions.InvalidInputException;
import com.emazon.msvctransactions.domain.models.Supply;
import com.emazon.msvctransactions.domain.ports.out.services.AuthService;
import com.emazon.msvctransactions.domain.ports.out.services.StockService;
import com.emazon.msvctransactions.domain.ports.out.repositories.SupplyRepository;
import com.emazon.msvctransactions.domain.usecases.SupplyUseCaseImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static com.emazon.msvctransactions.domain.utils.constants.supply.SupplyConstant.MIN_SUPPLY_QUANTITY;
import static com.emazon.msvctransactions.domain.utils.constants.supply.SupplyExceptionCode.*;
import static com.emazon.msvctransactions.domain.utils.constants.supply.SupplyExceptionMessage.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SupplyUseCaseTest {

  @Mock
  private SupplyRepository repository;

  @Mock
  private StockService stockServiceClient;

  @Mock
  private AuthService authService;

  @InjectMocks
  private SupplyUseCaseImp supplyUseCase;

  @Test
  void whenValidSupplyShouldCreateSupply() {
    // Given
    Supply supply = new Supply(
            1L,
            10,
            1L,
            null,
            LocalDateTime.now()
    );


    when(repository.saveSupply(supply)).thenReturn(supply);
    when(authService.getUserIdAuthenticated()).thenReturn(1L);

    // When
    Supply result = supplyUseCase.createSupply(supply);

    // Then
    assertEquals(supply, result);
    verify(stockServiceClient).updateArticleStock(1L, 10);
    verify(repository).saveSupply(supply);
  }

  @Test
  void whenSupplyQuantityIsNullShouldThrowInvalidInputException() {
    // Given
    Supply supply = new Supply(
            1L,
            null,
            1L,
            null,
            LocalDateTime.now()
    );

    // When & Then
    InvalidInputException exception = assertThrows(InvalidInputException.class,
            () -> supplyUseCase.createSupply(supply));
    assertEquals(EMPTY_SUPPLY_QUANTITY, exception.getMessage());
    assertEquals(EMPTY_SUPPLY_QUANTITY_CODE, exception.getCode());

    verifyNoInteractions(stockServiceClient);
    verifyNoInteractions(repository);
  }

  @Test
  void whenSupplyQuantityIsLessThanMinimumShouldThrowInvalidInputException() {
    // Given
    Supply supply = new Supply(
            1L,
            MIN_SUPPLY_QUANTITY - 1,
            1L,
            null,
            LocalDateTime.now()
    );

    // When & Then
    InvalidInputException exception = assertThrows(InvalidInputException.class,
            () -> supplyUseCase.createSupply(supply));
    assertEquals(INVALID_SUPPLY_QUANTITY, exception.getMessage());
    assertEquals(INVALID_SUPPLY_QUANTITY_CODE, exception.getCode());

    verifyNoInteractions(stockServiceClient);
    verifyNoInteractions(repository);
  }

  @Test
  void whenArticleIdIsNullShouldThrowInvalidInputException() {
    // Given
    Supply supply = new Supply(
            1L,
            10,
            null,
            null,
            LocalDateTime.now()
    );


    // When & Then
    InvalidInputException exception = assertThrows(InvalidInputException.class,
            () -> supplyUseCase.createSupply(supply));
    assertEquals(EMPTY_ARTICLE_ID, exception.getMessage());
    assertEquals(EMPTY_ARTICLE_ID_CODE, exception.getCode());

    verifyNoInteractions(stockServiceClient);
    verifyNoInteractions(repository);
  }
}
