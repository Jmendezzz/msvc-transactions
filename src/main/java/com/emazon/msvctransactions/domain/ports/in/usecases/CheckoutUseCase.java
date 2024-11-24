package com.emazon.msvctransactions.domain.ports.in.usecases;

import com.emazon.msvctransactions.domain.models.CheckoutSession;

public interface CheckoutUseCase {
  CheckoutSession generateCheckoutUserCart();
}
