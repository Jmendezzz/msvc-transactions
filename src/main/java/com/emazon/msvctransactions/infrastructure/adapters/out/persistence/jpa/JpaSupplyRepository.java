package com.emazon.msvctransactions.infrastructure.adapters.out.persistence.jpa;

import com.emazon.msvctransactions.domain.enums.SupplyStatus;
import com.emazon.msvctransactions.infrastructure.adapters.out.persistence.entities.SupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaSupplyRepository extends JpaRepository<SupplyEntity, Long>{
  List<SupplyEntity> findByStatus(SupplyStatus status);
}
