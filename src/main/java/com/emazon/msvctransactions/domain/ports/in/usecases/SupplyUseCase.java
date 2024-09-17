package com.emazon.msvctransactions.domain.ports.in.usecases;

import com.emazon.msvctransactions.domain.models.Supply;

public interface SupplyUseCase {
  Supply createSupply(Supply supply);
  void updateStock();
}
