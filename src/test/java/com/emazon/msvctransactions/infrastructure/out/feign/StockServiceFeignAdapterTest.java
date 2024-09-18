package com.emazon.msvctransactions.infrastructure.out.feign;


import com.emazon.msvctransactions.application.dtos.stock.UpdateArticleStockRequestDto;
import com.emazon.msvctransactions.infrastructure.adapters.out.feign.StockClient;
import com.emazon.msvctransactions.infrastructure.adapters.out.services.StockServiceFeignAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockServiceFeignAdapterTest {

  @Mock
  private StockClient stockClient;

  @InjectMocks
  private StockServiceFeignAdapter stockServiceFeignAdapter;

  @BeforeEach
  void setUp() {
    // Set the @Value fields using ReflectionTestUtils
    ReflectionTestUtils.setField(stockServiceFeignAdapter, "machineKey", "testMachineKey");
    ReflectionTestUtils.setField(stockServiceFeignAdapter, "machineHeader", "testMachineHeader");
  }

  @Test
  void whenUpdateArticleStockCalledShouldCallStockClientWithCorrectArguments() {
    // Arrange
    Long articleId = 1L;
    int quantity = 10;
    UpdateArticleStockRequestDto updateDto = new UpdateArticleStockRequestDto(quantity);

    // Act
    stockServiceFeignAdapter.updateArticleStock(articleId, quantity);

    // Assert
    verify(stockClient).updateArticleStock(eq(articleId), eq(updateDto), eq("testMachineKey"));
  }

  @Test
  void whenArticleExistsCalledShouldReturnStockClientResponse() {
    // Arrange
    Long articleId = 1L;
    when(stockClient.articleExists(anyLong())).thenReturn(true);

    // Act
    boolean result = stockServiceFeignAdapter.articleExists(articleId);

    // Assert
    assertTrue(result);
    verify(stockClient).articleExists(articleId);
  }
}
