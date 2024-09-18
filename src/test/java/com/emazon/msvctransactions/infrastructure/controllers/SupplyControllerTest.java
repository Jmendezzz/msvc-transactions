package com.emazon.msvctransactions.infrastructure.controllers;

import com.emazon.msvctransactions.application.dtos.supply.CreateSupplyRequestDto;
import com.emazon.msvctransactions.application.dtos.supply.SupplyResponseDto;
import com.emazon.msvctransactions.application.handlers.SupplyHandler;
import com.emazon.msvctransactions.domain.enums.SupplyStatus;
import com.emazon.msvctransactions.infrastructure.adapters.in.controllers.SupplyController;
import com.emazon.msvctransactions.infrastructure.utils.constants.SecurityConstant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
@WebMvcTest(SupplyController.class)
class SupplyControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private SupplyHandler supplyHandler;

  @BeforeEach
  void setUp() {
    // You can set up default behavior for SupplyHandler if needed
  }

  @Test
  @WithMockUser(username = "warehouse_assistant", roles = {SecurityConstant.WAREHOUSE_ASSISTANT_ROLE  })
  void whenCreateSupplyWithValidRequestShouldReturnCreated() throws Exception {
    // Arrange
    SupplyResponseDto responseDto = new SupplyResponseDto(1L, 1L, 10, SupplyStatus.PENDING, 100L, LocalDateTime.now(), LocalDateTime.now().plusDays(1));

    Mockito.when(supplyHandler.createSupply(any(CreateSupplyRequestDto.class))).thenReturn(responseDto);

    // Act and Assert
    mockMvc.perform(post("/api/v1/supplies/create")
                    .with(SecurityMockMvcRequestPostProcessors.csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                        {
                          "articleId": 1,
                          "quantity": 10,
                          "availableAt": "2024-09-20T10:15:30"
                        }
                        """))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(responseDto.id()))
            .andExpect(jsonPath("$.articleId").value(responseDto.articleId()))
            .andExpect(jsonPath("$.quantity").value(responseDto.quantity()))
            .andExpect(jsonPath("$.status").value(responseDto.status().toString()));
  }

  @Test
  @WithMockUser(username = "warehouse_assistant", roles = {SecurityConstant.WAREHOUSE_ASSISTANT_ROLE  })
  void whenCreateSupplyWithInvalidRequestShouldReturnBadRequest() throws Exception {
    // Act and Assert
    mockMvc.perform(post("/api/v1/supplies/create")
                    .with(SecurityMockMvcRequestPostProcessors.csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                        {
                          "articleId": 1,
                          "quantity": 5
                        }
                        """)) // Missing "availableAt" field
            .andExpect(status().isBadRequest());
  }

  @Test
  void whenUnauthorizedUserAccessShouldReturnForbidden() throws Exception {
    // Act and Assert
    mockMvc.perform(post("/api/v1/supplies/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                        {
                          "articleId": 1,
                          "quantity": 10,
                          "availableAt": "2024-09-20T10:15:30"
                        }
                        """))
            .andExpect(status().isForbidden()); // No authenticated user, should return 403 Forbidden
  }
}
