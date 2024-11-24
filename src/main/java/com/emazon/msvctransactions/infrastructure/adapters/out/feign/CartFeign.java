package com.emazon.msvctransactions.infrastructure.adapters.out.feign;

import com.emazon.msvctransactions.domain.models.Cart;
import com.emazon.msvctransactions.infrastructure.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import static com.emazon.msvctransactions.infrastructure.utils.constants.FeignConstant.CART_CLIENT;
import static com.emazon.msvctransactions.infrastructure.utils.constants.FeignConstant.CART_URL;

@FeignClient(name = CART_CLIENT, url = CART_URL, configuration = FeignConfig.class)
public interface CartFeign {

  @GetMapping("")
  Cart getUserCart();
}
