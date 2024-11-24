package com.emazon.msvctransactions.infrastructure.config;

import com.emazon.msvctransactions.domain.ports.in.usecases.CheckoutUseCase;
import com.emazon.msvctransactions.domain.ports.in.usecases.SupplyUseCase;
import com.emazon.msvctransactions.domain.ports.out.repositories.CheckoutRepository;
import com.emazon.msvctransactions.domain.ports.out.repositories.SupplyRepository;
import com.emazon.msvctransactions.domain.ports.out.services.AuthService;
import com.emazon.msvctransactions.domain.ports.out.services.CartService;
import com.emazon.msvctransactions.domain.ports.out.services.PaymentService;
import com.emazon.msvctransactions.domain.ports.out.services.StockService;
import com.emazon.msvctransactions.domain.usecases.CheckoutUseCaseImpl;
import com.emazon.msvctransactions.domain.usecases.SupplyUseCaseImp;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class BeanConfig {
  private final SupplyRepository supplyRepository;
  private final StockService stockService;
  private final AuthService authService;
  private final CartService cartService;
  private final PaymentService paymentService;
  private final CheckoutRepository checkoutRepository;

  @Bean
  public SupplyUseCase supplyUseCase() {
    return new SupplyUseCaseImp(supplyRepository, stockService, authService);
  }

  @Bean
  public CheckoutUseCase checkoutUseCase() {
    return new CheckoutUseCaseImpl(paymentService, cartService, stockService, checkoutRepository);
  }
}
