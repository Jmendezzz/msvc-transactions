package com.emazon.msvctransactions.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record Cart(
    Long id,
    Long userId,
    List<CartArticleItem> cartItems,
    BigDecimal totalPrice,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
