package com.emazon.msvctransactions.application.handlers;

import com.emazon.msvctransactions.application.dtos.supply.CreateSupplyRequestDto;
import com.emazon.msvctransactions.application.dtos.supply.SupplyResponseDto;

public interface SupplyHandler {
  SupplyResponseDto createSupply(CreateSupplyRequestDto createSupplyRequestDto);
  void updateStock();
}
