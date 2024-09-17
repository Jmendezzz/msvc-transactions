package com.emazon.msvctransactions.infrastructure.adapters.in.controllers;

import com.emazon.msvctransactions.application.dtos.supply.CreateSupplyRequestDto;
import com.emazon.msvctransactions.application.dtos.supply.SupplyResponseDto;
import com.emazon.msvctransactions.application.handlers.SupplyHandler;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.emazon.msvctransactions.infrastructure.utils.constants.SecurityConstant.ADMIN_ROLE;
import static com.emazon.msvctransactions.infrastructure.utils.constants.SecurityConstant.WAREHOUSE_ASSISTANT_ROLE;

@RequestMapping("api/v1/supplies")
@RestController
@AllArgsConstructor
public class SupplyController {
  private final SupplyHandler supplyHandler;

  @PostMapping("/create")
  @PreAuthorize("hasRole('" + WAREHOUSE_ASSISTANT_ROLE + "')")
  public ResponseEntity<SupplyResponseDto> createSupply(@Valid @RequestBody CreateSupplyRequestDto createSupplyRequestDto) {
    return new ResponseEntity<>(
            supplyHandler.createSupply(createSupplyRequestDto),
            HttpStatus.CREATED);
  }

}
