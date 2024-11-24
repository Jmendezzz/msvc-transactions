package com.emazon.msvctransactions.infrastructure.adapters.out.persistence.jpa;

import com.emazon.msvctransactions.domain.models.CheckoutStatus;
import com.emazon.msvctransactions.infrastructure.adapters.out.persistence.entities.CheckoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaCheckoutRepository extends JpaRepository<CheckoutEntity, Long>{
  Optional<CheckoutEntity> findByUserIdAndStatus(Long cartId, CheckoutStatus status);

}
