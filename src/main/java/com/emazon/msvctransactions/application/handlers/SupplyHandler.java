package com.emazon.msvctransactions.application.handlers;

import com.emazon.msvctransactions.application.dtos.supply.CreateSupplyRequestDto;
import com.emazon.msvctransactions.application.dtos.supply.SupplyResponseDto;

import java.util.Optional;

public interface SupplyHandler {
  SupplyResponseDto createSupply(CreateSupplyRequestDto createSupplyRequestDto);
  void updateStock();
  Optional<SupplyResponseDto> getNextAvailableSupplyForArticle(Long articleId);
}
