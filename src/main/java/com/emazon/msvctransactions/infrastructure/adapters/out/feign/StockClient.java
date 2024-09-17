package com.emazon.msvctransactions.infrastructure.adapters.out.feign;

import com.emazon.msvctransactions.application.dtos.stock.UpdateArticleStockRequestDto;
import com.emazon.msvctransactions.infrastructure.config.FeignConfig;
import com.emazon.msvctransactions.infrastructure.utils.constants.FeignConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import static com.emazon.msvctransactions.infrastructure.utils.constants.SecurityConstant.MACHINE_HEADER;

@FeignClient(name = FeignConstant.STOCK_CLIENT, url = FeignConstant.ARTICLE_URL, configuration = FeignConfig.class)
public interface  StockClient {

  @PatchMapping("/{articleId}/stock")
  void updateArticleStock(@PathVariable Long articleId,
                          @RequestBody UpdateArticleStockRequestDto updateArticleStockRequestDto,
                          @RequestHeader(MACHINE_HEADER) String machineKey);


  @GetMapping("/{articleId}/exists")
  boolean articleExists(@PathVariable Long articleId);

}
