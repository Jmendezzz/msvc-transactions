package com.emazon.msvctransactions.domain.ports.out.repositories;

import com.emazon.msvctransactions.domain.models.Checkout;
import com.emazon.msvctransactions.domain.models.CheckoutStatus;

import java.util.Optional;

public interface CheckoutRepository {
  void save(Checkout checkout);
  Optional<Checkout> findByUserIdAndStatus(Long userId, CheckoutStatus status);
}
