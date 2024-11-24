package com.emazon.msvctransactions.domain.ports.out.services;

public interface StockService {
  void updateArticleStock(Long articleId, int quantity);
  void decreaseArticleStock(Long articleId, int quantity);
  boolean articleExists(Long articleId);
}
