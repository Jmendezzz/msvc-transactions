package com.emazon.msvctransactions.domain.ports.in.usecases;

import com.emazon.msvctransactions.domain.models.Supply;

import java.util.Optional;

public interface SupplyUseCase {
  Supply createSupply(Supply supply);
  void updateStock();
  Optional<Supply> getNextAvailableSupplyForArticle(Long articleId);
}
