package com.emazon.msvctransactions.domain.models;

public record CartArticleItem(
        Article article,
        Integer quantity
) {
}
