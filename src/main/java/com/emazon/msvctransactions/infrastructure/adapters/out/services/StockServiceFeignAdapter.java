package com.emazon.msvctransactions.infrastructure.adapters.out.services;

import com.emazon.msvctransactions.application.dtos.stock.UpdateArticleStockRequestDto;
import com.emazon.msvctransactions.domain.ports.out.services.StockService;
import com.emazon.msvctransactions.infrastructure.adapters.out.feign.StockClient;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockServiceFeignAdapter implements StockService {
  private final StockClient stockClient;

  @Value("${security.machine.key}")
  private String machineKey;

  @Value("${security.machine.header}")
  private String machineHeader;

  @Override
  public void updateArticleStock(Long articleId, int quantity) {
    UpdateArticleStockRequestDto updateArticleStockRequestDto = new UpdateArticleStockRequestDto(quantity);

    stockClient.updateArticleStock(articleId, updateArticleStockRequestDto, machineKey);
  }

  @Override
  public boolean articleExists(Long articleId) {
    return stockClient.articleExists(articleId);
  }
}
