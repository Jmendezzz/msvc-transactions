package com.emazon.msvctransactions.infrastructure.adapters.out.services;

import com.emazon.msvctransactions.domain.models.Cart;
import com.emazon.msvctransactions.domain.ports.out.services.CartService;
import com.emazon.msvctransactions.infrastructure.adapters.out.feign.CartFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CartServiceFeignAdapter implements CartService {
  private final CartFeign cartFeign;
  @Override
  public Cart getUserCart() {
    return cartFeign.getUserCart();
  }
}
