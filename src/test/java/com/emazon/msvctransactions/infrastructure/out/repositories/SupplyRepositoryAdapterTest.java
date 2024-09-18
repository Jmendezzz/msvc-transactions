package com.emazon.msvctransactions.infrastructure.out.repositories;

import com.emazon.msvctransactions.domain.enums.SupplyStatus;
import com.emazon.msvctransactions.domain.models.Supply;
import com.emazon.msvctransactions.infrastructure.adapters.out.persistence.entities.SupplyEntity;
import com.emazon.msvctransactions.infrastructure.adapters.out.persistence.jpa.JpaSupplyRepository;
import com.emazon.msvctransactions.infrastructure.adapters.out.persistence.mappers.SupplyEntityMapper;
import com.emazon.msvctransactions.infrastructure.adapters.out.persistence.repositories.SupplyRepositoryAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SupplyRepositoryAdapterTest {
  @Mock
  private JpaSupplyRepository repository;

  @Mock
  private SupplyEntityMapper mapper;

  @InjectMocks
  private SupplyRepositoryAdapter supplyRepositoryAdapter;

  @Test
  void whenSaveSupplyCalledShouldReturnSavedSupply() {
    // Arrange
    Supply supply = new Supply();
    SupplyEntity supplyEntity = new SupplyEntity();
    SupplyEntity savedEntity = new SupplyEntity();

    when(mapper.toEntity(any(Supply.class))).thenReturn(supplyEntity);
    when(repository.save(any(SupplyEntity.class))).thenReturn(savedEntity);
    when(mapper.toDomain(any(SupplyEntity.class))).thenReturn(supply);

    // Act
    Supply result = supplyRepositoryAdapter.saveSupply(supply);

    // Assert
    assertEquals(supply, result);
    verify(mapper).toEntity(supply);
    verify(repository).save(supplyEntity);
    verify(mapper).toDomain(savedEntity);
  }

  @Test
  void whenGetSuppliesByStatusCalledShouldReturnListOfSupplies() {
    // Arrange
    SupplyStatus status = SupplyStatus.PENDING;
    List<SupplyEntity> supplyEntities = List.of(new SupplyEntity());
    List<Supply> supplies = List.of(new Supply());

    when(repository.findByStatus(any(SupplyStatus.class))).thenReturn(supplyEntities);
    when(mapper.toDomain(anyList())).thenReturn(supplies);

    // Act
    List<Supply> result = supplyRepositoryAdapter.getSuppliesByStatus(status);

    // Assert
    assertEquals(supplies, result);
    verify(repository).findByStatus(status);
    verify(mapper).toDomain(supplyEntities);
  }
}
