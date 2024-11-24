package com.emazon.msvctransactions.infrastructure.adapters.in.controllers;

import com.emazon.msvctransactions.application.dtos.checkout.CheckoutSessionDto;
import com.emazon.msvctransactions.application.handlers.CheckoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/checkout")
@RequiredArgsConstructor
public class CheckoutController {
  private final CheckoutHandler checkoutHandler;
  @GetMapping("/user-cart")
  public ResponseEntity<CheckoutSessionDto> generateCheckout() {
    return ResponseEntity.ok(checkoutHandler.generateCheckout());
  }
}
