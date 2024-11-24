package com.emazon.msvctransactions.application.handlers;

import com.emazon.msvctransactions.application.dtos.checkout.CheckoutSessionDto;

public interface CheckoutHandler {
  CheckoutSessionDto generateCheckout();
}
