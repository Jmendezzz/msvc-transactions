package com.emazon.msvctransactions.application.dtos.supply;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

import static com.emazon.msvctransactions.domain.utils.constants.supply.SupplyConstant.MIN_SUPPLY_QUANTITY;
import static com.emazon.msvctransactions.domain.utils.constants.supply.SupplyExceptionMessage.*;

public record CreateSupplyRequestDto(
        @NotNull(message = EMPTY_ARTICLE_ID)
        Long articleId,
        @NotNull(message = EMPTY_SUPPLY_QUANTITY)
        @Min(value = MIN_SUPPLY_QUANTITY, message = INVALID_SUPPLY_QUANTITY)
        Integer quantity,
        @NotNull(message = EMPTY_AVAILABLE_AT)
        @Future(message = INVALID_AVAILABLE_AT)
        LocalDateTime availableAt
) {
}
