package com.emazon.msvctransactions.domain.exceptions.checkout;

import com.emazon.msvctransactions.domain.exceptions.BusinessException;

import static com.emazon.msvctransactions.domain.utils.constants.checkout.CheckoutException.NO_CART_ITEMS_FOR_CHECKOUT;
import static com.emazon.msvctransactions.domain.utils.constants.checkout.CheckoutException.NO_CART_ITEMS_FOR_CHECKOUT_CODE;

public class NoCartItemsForCheckoutException extends BusinessException {
  public NoCartItemsForCheckoutException() {
    super(NO_CART_ITEMS_FOR_CHECKOUT, NO_CART_ITEMS_FOR_CHECKOUT_CODE);
  }
}
