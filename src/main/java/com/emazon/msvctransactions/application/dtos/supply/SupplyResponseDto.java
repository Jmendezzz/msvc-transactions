package com.emazon.msvctransactions.application.dtos.supply;

import com.emazon.msvctransactions.domain.enums.SupplyStatus;

import java.time.LocalDateTime;

public record SupplyResponseDto(
        Long id,
        Long articleId,
        Integer quantity,
        SupplyStatus status,
        Long createdBy,
        LocalDateTime createdAt,
        LocalDateTime availableAt
) {
}
