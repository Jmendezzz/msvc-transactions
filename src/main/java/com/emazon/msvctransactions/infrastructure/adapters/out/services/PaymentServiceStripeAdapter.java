package com.emazon.msvctransactions.infrastructure.adapters.out.services;

import com.emazon.msvctransactions.domain.models.CheckoutItem;
import com.emazon.msvctransactions.domain.models.CheckoutSession;
import com.emazon.msvctransactions.domain.ports.out.services.PaymentService;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PaymentServiceStripeAdapter implements PaymentService {

  @Value("${stripe.secret.key}")
  private String secretKey;

  @Value("${frontend.url}")
  private String frontendUrl;
  @PostConstruct
  public void init() {
    Stripe.apiKey = secretKey;
  }

  @Override
  public CheckoutSession createPaymentSession(List<CheckoutItem> items, Long expiresAtMs) {
    try {
      List<SessionCreateParams.LineItem> lineItems = createSessionParamsLineItems(items);
      SessionCreateParams params = SessionCreateParams.builder()
              .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
              .setMode(SessionCreateParams.Mode.PAYMENT)
              .setSuccessUrl(frontendUrl + "/success-payment")
              .setCancelUrl(frontendUrl + "/cart")
              .addAllLineItem(lineItems)
              .setExpiresAt(expiresAtMs)
              .build();

      Session session = Session.create(params);

      return new CheckoutSession(session.getUrl(), session.getId());

    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Error creating payment session");
    }
  }

  @Override
  public CheckoutSession getPaymentSession(String sessionId) {
    try {
      Session session = Session.retrieve(sessionId);
      return new CheckoutSession(session.getUrl(), session.getId());
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Error retrieving payment session");
    }  }

  private List<SessionCreateParams.LineItem> createSessionParamsLineItems(List<CheckoutItem> items) {
    return items.stream()
            .map(item -> SessionCreateParams.LineItem.builder()
                    .setQuantity((long) item.getQuantity())
                    .setPriceData(
                            SessionCreateParams.LineItem.PriceData.builder()
                                    .setCurrency("usd") // Cambiado a una cadena para evitar problemas.
                                    .setUnitAmount((long) (item.getPrice() * 100))
                                    .setProductData(
                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                    .setName(item.getArticleName())
                                                    .build()
                                    )
                                    .build()
                    )
                    .build()
            )
            .toList();
  }
}
