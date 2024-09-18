package com.emazon.msvctransactions.domain.usecases;

import com.emazon.msvctransactions.domain.enums.SupplyStatus;
import com.emazon.msvctransactions.domain.exceptions.InvalidInputException;
import com.emazon.msvctransactions.domain.models.Supply;
import com.emazon.msvctransactions.domain.ports.in.usecases.SupplyUseCase;
import com.emazon.msvctransactions.domain.ports.out.services.AuthService;
import com.emazon.msvctransactions.domain.ports.out.services.StockService;
import com.emazon.msvctransactions.domain.ports.out.repositories.SupplyRepository;

import java.time.LocalDateTime;
import java.util.List;

import static com.emazon.msvctransactions.domain.utils.constants.supply.SupplyConstant.MIN_SUPPLY_QUANTITY;
import static com.emazon.msvctransactions.domain.utils.constants.supply.SupplyExceptionCode.*;
import static com.emazon.msvctransactions.domain.utils.constants.supply.SupplyExceptionMessage.*;

public class SupplyUseCaseImp implements SupplyUseCase {
  private final SupplyRepository repository;
  private final StockService stockService;
  private final AuthService authService;

  public SupplyUseCaseImp(SupplyRepository repository, StockService stockService, AuthService authService) {
    this.repository = repository;
    this.stockService = stockService;
    this.authService = authService;
  }
  @Override
  public Supply createSupply(Supply supply) {
    validateSupply(supply);

    supply.setStatus(SupplyStatus.PENDING);
    supply.setCreatedBy(authService.getUserIdAuthenticated());
    supply.setCreatedAt(LocalDateTime.now());

    return repository.saveSupply(supply);
  }

  @Override
  public void updateStock() {
    List<Supply> supplies = repository.getSuppliesByStatus(SupplyStatus.PENDING);

    supplies.stream()
            .filter(supply -> supply.getAvailableAt().isBefore(LocalDateTime.now()))
            .forEach(supply -> {
              stockService.updateArticleStock(supply.getArticleId(), supply.getQuantity());
              supply.setStatus(SupplyStatus.DELIVERED);
              repository.saveSupply(supply);
            });

  }

  private void validateSupply(Supply supply) {

    if(supply.getQuantity() == null) {
      throw new InvalidInputException(EMPTY_SUPPLY_QUANTITY, EMPTY_SUPPLY_QUANTITY_CODE);
    }
    if (supply.getQuantity() < MIN_SUPPLY_QUANTITY) {
      throw new InvalidInputException(INVALID_SUPPLY_QUANTITY, INVALID_SUPPLY_QUANTITY_CODE);
    }
    if(supply.getArticleId() == null) {
      throw new InvalidInputException(EMPTY_ARTICLE_ID, EMPTY_ARTICLE_ID_CODE);
    }
    if(supply.getAvailableAt() == null) {
      throw new InvalidInputException(EMPTY_AVAILABLE_AT, EMPTY_AVAILABLE_AT_CODE);
    }
    if(supply.getAvailableAt().isBefore(LocalDateTime.now())) {
      throw new InvalidInputException(INVALID_AVAILABLE_AT, INVALID_AVAILABLE_AT_CODE);
    }
    if(!stockService.articleExists(supply.getArticleId())) {
      throw new InvalidInputException(ARTICLE_NOT_FOUND, ARTICLE_NOT_FOUND_CODE);
    }
  }
}
