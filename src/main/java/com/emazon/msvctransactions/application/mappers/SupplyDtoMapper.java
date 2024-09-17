package com.emazon.msvctransactions.application.mappers;

import com.emazon.msvctransactions.application.dtos.supply.CreateSupplyRequestDto;
import com.emazon.msvctransactions.application.dtos.supply.SupplyResponseDto;
import com.emazon.msvctransactions.domain.models.Supply;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplyDtoMapper {
  Supply toDomain(CreateSupplyRequestDto supplyResponseDto);
  SupplyResponseDto toDto(Supply supply);
}
