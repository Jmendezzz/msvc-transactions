package com.emazon.msvctransactions.domain.usecases;

import com.emazon.msvctransactions.domain.enums.SupplyStatus;
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
import java.util.Collections;
import java.util.List;

import static com.emazon.msvctransactions.domain.utils.constants.supply.SupplyConstant.MIN_SUPPLY_QUANTITY;
import static com.emazon.msvctransactions.domain.utils.constants.supply.SupplyExceptionCode.*;
import static com.emazon.msvctransactions.domain.utils.constants.supply.SupplyExceptionMessage.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SupplyUseCaseTest {

  @Mock
  private SupplyRepository repository;

  @Mock
  private StockService stockService;

  @Mock
  private AuthService authService;

  @InjectMocks
  private SupplyUseCaseImp supplyUseCase;


  @Test
  void whenValidSupplyCreateSupplyShouldReturnSavedSupply() {
    Supply supply = new Supply();
    supply.setArticleId(1L);
    supply.setQuantity(MIN_SUPPLY_QUANTITY);
    supply.setAvailableAt(LocalDateTime.now().plusDays(1));

    when(stockService.articleExists(anyLong())).thenReturn(true);
    when(authService.getUserIdAuthenticated()).thenReturn(1L);
    when(repository.saveSupply(any(Supply.class))).thenReturn(supply);

    Supply result = supplyUseCase.createSupply(supply);

    assertNotNull(result);
    assertEquals(SupplyStatus.PENDING, result.getStatus());
    assertEquals(1L, result.getCreatedBy());
    verify(repository).saveSupply(supply);
  }

  @Test
  void whenInvalidArticleIdCreateSupplyShouldThrowException() {
    Supply supply = new Supply();
    supply.setQuantity(MIN_SUPPLY_QUANTITY);
    supply.setAvailableAt(LocalDateTime.now().plusDays(1));
    supply.setArticleId(120L);

    when(stockService.articleExists(anyLong())).thenReturn(false);

    InvalidInputException exception = assertThrows(InvalidInputException.class, () -> supplyUseCase.createSupply(supply));

    assertEquals(ARTICLE_NOT_FOUND, exception.getMessage());
    verify(repository, never()).saveSupply(any());
  }

  @Test
  void whenNullQuantityCreateSupplyShouldThrowException() {
    Supply supply = new Supply();
    supply.setArticleId(1L);

    InvalidInputException exception = assertThrows(InvalidInputException.class, () -> supplyUseCase.createSupply(supply));

    assertEquals(EMPTY_SUPPLY_QUANTITY, exception.getMessage());
    verify(repository, never()).saveSupply(any());
  }

  @Test
  void whenQuantityLessThanMinimumCreateSupplyShouldThrowException() {
    Supply supply = new Supply();
    supply.setArticleId(1L);
    supply.setQuantity(MIN_SUPPLY_QUANTITY - 1);

    InvalidInputException exception = assertThrows(InvalidInputException.class, () -> supplyUseCase.createSupply(supply));

    assertEquals(INVALID_SUPPLY_QUANTITY, exception.getMessage());
    verify(repository, never()).saveSupply(any());
  }

  @Test
  void whenNullArticleIdCreateSupplyShouldThrowException() {
    Supply supply = new Supply();
    supply.setQuantity(MIN_SUPPLY_QUANTITY);
    supply.setArticleId(null); // Explicitly set ArticleId to null

    // No need to mock stockService.articleExists since it won't be called due to null ArticleId
    // Avoid stubbing the stockService behavior here

    InvalidInputException exception = assertThrows(InvalidInputException.class, () -> supplyUseCase.createSupply(supply));

    assertEquals(EMPTY_ARTICLE_ID, exception.getMessage());
    verify(repository, never()).saveSupply(any());
  }

  @Test
  void whenNullAvailableAtCreateSupplyShouldThrowException() {
    Supply supply = new Supply();
    supply.setArticleId(1L);
    supply.setQuantity(MIN_SUPPLY_QUANTITY);

    InvalidInputException exception = assertThrows(InvalidInputException.class, () -> supplyUseCase.createSupply(supply));

    assertEquals(EMPTY_AVAILABLE_AT, exception.getMessage());
    verify(repository, never()).saveSupply(any());
  }

  @Test
  void whenPastAvailableAtCreateSupplyShouldThrowException() {
    Supply supply = new Supply();
    supply.setArticleId(1L);
    supply.setQuantity(MIN_SUPPLY_QUANTITY);
    supply.setAvailableAt(LocalDateTime.now().minusDays(1));

    InvalidInputException exception = assertThrows(InvalidInputException.class, () -> supplyUseCase.createSupply(supply));

    assertEquals(INVALID_AVAILABLE_AT, exception.getMessage());
    verify(repository, never()).saveSupply(any());
  }

  @Test
  void whenPendingSuppliesAndValidUpdateStockShouldUpdateStockAndSetDeliveredStatus() {
    Supply supply = new Supply();
    supply.setArticleId(1L);
    supply.setQuantity(MIN_SUPPLY_QUANTITY);
    supply.setAvailableAt(LocalDateTime.now().minusDays(1));
    supply.setStatus(SupplyStatus.PENDING);

    List<Supply> pendingSupplies = Collections.singletonList(supply);

    when(repository.getSuppliesByStatus(SupplyStatus.PENDING)).thenReturn(pendingSupplies);

    supplyUseCase.updateStock();

    verify(stockService).updateArticleStock(supply.getArticleId(), supply.getQuantity());
    assertEquals(SupplyStatus.DELIVERED, supply.getStatus());
    verify(repository).saveSupply(supply);
  }

  @Test
  void whenNoPendingSuppliesUpdateStockShouldDoNothing() {
    when(repository.getSuppliesByStatus(SupplyStatus.PENDING)).thenReturn(Collections.emptyList());

    supplyUseCase.updateStock();

    verify(stockService, never()).updateArticleStock(anyLong(), anyInt());
    verify(repository, never()).saveSupply(any());
  }
}
