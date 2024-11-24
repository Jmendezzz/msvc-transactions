package com.emazon.msvctransactions.infrastructure.adapters.out.persistence.mappers;

import com.emazon.msvctransactions.domain.models.Checkout;
import com.emazon.msvctransactions.infrastructure.adapters.out.persistence.entities.CheckoutEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CheckoutEntityMapper {
  CheckoutEntity toEntity(Checkout checkout);
  Checkout toDomain(CheckoutEntity checkoutEntity);

}
