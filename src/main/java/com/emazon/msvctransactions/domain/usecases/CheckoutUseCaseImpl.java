package com.emazon.msvctransactions.domain.usecases;

import com.emazon.msvctransactions.domain.exceptions.checkout.NoCartItemsForCheckoutException;
import com.emazon.msvctransactions.domain.models.*;
import com.emazon.msvctransactions.domain.ports.in.usecases.CheckoutUseCase;
import com.emazon.msvctransactions.domain.ports.out.repositories.CheckoutRepository;
import com.emazon.msvctransactions.domain.ports.out.services.CartService;
import com.emazon.msvctransactions.domain.ports.out.services.PaymentService;
import com.emazon.msvctransactions.domain.ports.out.services.StockService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import static com.emazon.msvctransactions.domain.utils.constants.checkout.CheckoutConstant.CHECKOUT_EXPIRATION_MINUTES;

public class CheckoutUseCaseImpl implements CheckoutUseCase {
  private final PaymentService paymentService;
  private final CartService cartService;
  private final StockService stockService;
  private final CheckoutRepository checkoutRepository;

  public CheckoutUseCaseImpl(PaymentService paymentService, CartService cartService, StockService stockService, CheckoutRepository checkoutRepository) {
    this.paymentService = paymentService;
    this.cartService = cartService;
    this.stockService = stockService;
    this.checkoutRepository = checkoutRepository;
  }
  @Override
  public CheckoutSession generateCheckoutUserCart() {
    Cart userCart = cartService.getUserCart();

    if(userCart.cartItems().isEmpty()) throw  new NoCartItemsForCheckoutException();

    Optional<Checkout> pendingCheckout = checkoutRepository.findByUserIdAndStatus(userCart.userId(), CheckoutStatus.PENDING);
    if(pendingCheckout.isPresent()) return paymentService.getPaymentSession(pendingCheckout.get().getSessionId());

    List<CheckoutItem> checkoutItems = mapCartItemsToCheckoutItems(userCart.cartItems());

    LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(CHECKOUT_EXPIRATION_MINUTES);
    long expirationTimestamp = Instant.now().getEpochSecond() + (CHECKOUT_EXPIRATION_MINUTES * 60);

    CheckoutSession checkoutSession = paymentService.createPaymentSession(checkoutItems, expirationTimestamp);

    createCheckout(userCart, checkoutSession, expirationTime);
    reserveStock(checkoutItems);

    return checkoutSession;
  }


  private List<CheckoutItem> mapCartItemsToCheckoutItems(List<CartArticleItem> cartItems) {
    return cartItems.stream()
        .map(cartItem -> new CheckoutItem(
                cartItem.article().id(),
                cartItem.quantity(),
                cartItem.article().price(),
                cartItem.article().name()))
        .toList();
  }

  private void createCheckout(Cart cart, CheckoutSession checkoutSession, LocalDateTime expirationTime) {
    Checkout checkout = new Checkout(
        null,
        cart.userId(),
        mapCartItemsToCheckoutItems(cart.cartItems()),
        checkoutSession.getSessionId(),
        CheckoutStatus.PENDING,
        LocalDateTime.now(),
            expirationTime
    );
    checkoutRepository.save(checkout);
  }

  private void reserveStock(List<CheckoutItem> checkoutItems) {
    checkoutItems.forEach(checkoutItem -> {
      stockService.decreaseArticleStock(checkoutItem.getArticleId(), checkoutItem.getQuantity());
    });
  }
}
