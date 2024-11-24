package com.emazon.msvctransactions.domain.ports.out.services;

import com.emazon.msvctransactions.domain.models.CheckoutItem;
import com.emazon.msvctransactions.domain.models.CheckoutSession;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentService {
  CheckoutSession createPaymentSession(List<CheckoutItem> items, Long expiresAtMs);
  CheckoutSession getPaymentSession(String sessionId);
}
