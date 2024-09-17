package com.emazon.msvctransactions.infrastructure.adapters.out.persistence.repositories;

import com.emazon.msvctransactions.domain.enums.SupplyStatus;
import com.emazon.msvctransactions.domain.models.Supply;
import com.emazon.msvctransactions.domain.ports.out.repositories.SupplyRepository;
import com.emazon.msvctransactions.infrastructure.adapters.out.persistence.entities.SupplyEntity;
import com.emazon.msvctransactions.infrastructure.adapters.out.persistence.jpa.JpaSupplyRepository;
import com.emazon.msvctransactions.infrastructure.adapters.out.persistence.mappers.SupplyEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class SupplyRepositoryAdapter implements SupplyRepository {
  private final JpaSupplyRepository repository;
  private final SupplyEntityMapper mapper;
  @Override
  public Supply saveSupply(Supply supply) {
    SupplyEntity supplyEntityToSave = mapper.toEntity(supply);
    return mapper.toDomain(
            repository.save(supplyEntityToSave)
    );
  }

  @Override
  public List<Supply> getSuppliesByStatus(SupplyStatus status) {
    return mapper.toDomain(
            repository.findByStatus(status)
    );
  }
}
