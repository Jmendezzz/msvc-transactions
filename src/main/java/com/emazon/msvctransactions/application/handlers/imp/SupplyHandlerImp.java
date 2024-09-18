package com.emazon.msvctransactions.application.handlers.imp;

import com.emazon.msvctransactions.application.dtos.supply.CreateSupplyRequestDto;
import com.emazon.msvctransactions.application.dtos.supply.SupplyResponseDto;
import com.emazon.msvctransactions.application.handlers.SupplyHandler;
import com.emazon.msvctransactions.application.mappers.SupplyDtoMapper;
import com.emazon.msvctransactions.domain.ports.in.usecases.SupplyUseCase;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static com.emazon.msvctransactions.domain.utils.constants.supply.SupplyConstant.CRON_JOB_SUPPLY_UPDATE;

@Service
@AllArgsConstructor
public class SupplyHandlerImp implements SupplyHandler {
  private final SupplyUseCase supplyUseCase;
  private final SupplyDtoMapper mapper;

  @Override
  public SupplyResponseDto createSupply(CreateSupplyRequestDto createSupplyRequestDto) {
    return mapper.toDto(supplyUseCase.createSupply(mapper.toDomain(createSupplyRequestDto)));
  }
  @Override
  public void updateStock() {
    supplyUseCase.updateStock();
  }
}
