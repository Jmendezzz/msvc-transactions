package com.emazon.msvctransactions.application.mappers;

import com.emazon.msvctransactions.application.dtos.checkout.CheckoutSessionDto;
import com.emazon.msvctransactions.domain.models.CheckoutSession;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CheckoutDtoMapper {
  CheckoutSessionDto toDto(CheckoutSession checkoutSession);
}
