package com.emazon.msvctransactions.domain.models;

public record CartItem(
        Long id,
        Long articleId,
        Integer quantity
) {
}
