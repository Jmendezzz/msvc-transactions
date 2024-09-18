package com.emazon.msvctransactions.infrastructure.adapters.in.controllers;

import com.emazon.msvctransactions.application.dtos.supply.CreateSupplyRequestDto;
import com.emazon.msvctransactions.application.dtos.supply.SupplyResponseDto;
import com.emazon.msvctransactions.application.handlers.SupplyHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.emazon.msvctransactions.infrastructure.utils.constants.SecurityConstant.ADMIN_ROLE;
import static com.emazon.msvctransactions.infrastructure.utils.constants.SecurityConstant.WAREHOUSE_ASSISTANT_ROLE;
import static com.emazon.msvctransactions.infrastructure.utils.constants.SwaggerConstant.*;
import static com.emazon.msvctransactions.infrastructure.utils.constants.SwaggerConstant.SECURITY_NAME;

@RequestMapping("api/v1/supplies")
@RestController
@AllArgsConstructor
public class SupplyController {
  private final SupplyHandler supplyHandler;

  @PostMapping("/create")
  @PreAuthorize("hasRole('" + WAREHOUSE_ASSISTANT_ROLE + "')")
  @Operation(
          summary = CREATE_SUPPLY_OPERATION,
          description = CREATE_SUPPLY_NOTE,
          responses = {
                  @ApiResponse(responseCode = STATUS_201, description = STATUS_201_DESCRIPTION, content = @Content(schema = @Schema(implementation = SupplyResponseDto.class))),
                  @ApiResponse(responseCode = STATUS_401, description = STATUS_401_DESCRIPTION),
                  @ApiResponse(responseCode = STATUS_403, description = STATUS_403_DESCRIPTION)
          },
          parameters = {
                  @Parameter(description = CREATE_SUPPLY_REQUEST_DESCRIPTION, required = true, schema = @Schema(implementation = CreateSupplyRequestDto.class))
          },
          security = @SecurityRequirement(name = SECURITY_NAME)
  )
  public ResponseEntity<SupplyResponseDto> createSupply(@Valid @RequestBody CreateSupplyRequestDto createSupplyRequestDto) {
    return new ResponseEntity<>(
            supplyHandler.createSupply(createSupplyRequestDto),
            HttpStatus.CREATED);
  }

}
