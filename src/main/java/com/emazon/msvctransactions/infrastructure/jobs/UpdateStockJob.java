package com.emazon.msvctransactions.infrastructure.jobs;

import com.emazon.msvctransactions.application.handlers.SupplyHandler;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.emazon.msvctransactions.domain.utils.constants.supply.SupplyConstant.CRON_JOB_SUPPLY_UPDATE;

@Component
@AllArgsConstructor
public class UpdateStockJob {
  private final SupplyHandler supplyHandler;

  @Scheduled(cron = CRON_JOB_SUPPLY_UPDATE)
  public void updateStock() {
    supplyHandler.updateStock();
  }
}
