package com.emazon.msvctransactions.infrastructure.adapters.out.persistence.repositories;

import com.emazon.msvctransactions.domain.models.Checkout;
import com.emazon.msvctransactions.domain.models.CheckoutStatus;
import com.emazon.msvctransactions.domain.ports.out.repositories.CheckoutRepository;
import com.emazon.msvctransactions.infrastructure.adapters.out.persistence.jpa.JpaCheckoutRepository;
import com.emazon.msvctransactions.infrastructure.adapters.out.persistence.mappers.CheckoutEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CheckoutRepositoryAdapter implements CheckoutRepository {
  private final JpaCheckoutRepository jpaCheckoutRepository;
  private final CheckoutEntityMapper checkoutMapper;
  @Override
  public void save(Checkout checkout) {
    jpaCheckoutRepository.save(checkoutMapper.toEntity(checkout));
  }

  @Override
  public Optional<Checkout> findByUserIdAndStatus(Long userId, CheckoutStatus status) {
    return jpaCheckoutRepository.findByUserIdAndStatus(userId, status)
        .map(checkoutMapper::toDomain);
  }
}
