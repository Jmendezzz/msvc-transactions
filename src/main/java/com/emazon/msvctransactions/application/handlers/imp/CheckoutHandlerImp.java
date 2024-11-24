package com.emazon.msvctransactions.application.handlers.imp;

import com.emazon.msvctransactions.application.dtos.checkout.CheckoutSessionDto;
import com.emazon.msvctransactions.application.handlers.CheckoutHandler;
import com.emazon.msvctransactions.application.mappers.CheckoutDtoMapper;
import com.emazon.msvctransactions.domain.ports.in.usecases.CheckoutUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckoutHandlerImp implements CheckoutHandler {
  private final CheckoutUseCase checkoutUseCase;
  private final CheckoutDtoMapper checkoutDtoMapper;
  @Override
  public CheckoutSessionDto generateCheckout() {
    return checkoutDtoMapper.toDto(checkoutUseCase.generateCheckoutUserCart());
  }
}
