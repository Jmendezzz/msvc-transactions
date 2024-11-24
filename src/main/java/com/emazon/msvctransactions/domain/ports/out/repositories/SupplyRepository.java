package com.emazon.msvctransactions.domain.ports.out.repositories;

import com.emazon.msvctransactions.domain.enums.SupplyStatus;
import com.emazon.msvctransactions.domain.models.Supply;

import java.util.List;
import java.util.Optional;

public interface SupplyRepository {
  Supply saveSupply(Supply supply);
  List<Supply> getSuppliesByStatus(SupplyStatus status);
  Optional<Supply> getNextAvailableSupplyForArticle(Long articleId);
}
