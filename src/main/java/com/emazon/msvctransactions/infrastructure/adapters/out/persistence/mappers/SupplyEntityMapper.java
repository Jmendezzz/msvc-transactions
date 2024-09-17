package com.emazon.msvctransactions.infrastructure.adapters.out.persistence.mappers;

import com.emazon.msvctransactions.domain.models.Supply;
import com.emazon.msvctransactions.infrastructure.adapters.out.persistence.entities.SupplyEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplyEntityMapper {
  SupplyEntity toEntity(Supply supply);
  Supply toDomain(SupplyEntity supplyEntity);
  List<Supply> toDomain(List<SupplyEntity> supplyEntities);
}
