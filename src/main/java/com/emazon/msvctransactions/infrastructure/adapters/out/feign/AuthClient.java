package com.emazon.msvctransactions.infrastructure.adapters.out.feign;

import com.emazon.msvctransactions.domain.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import static com.emazon.msvctransactions.infrastructure.utils.constants.FeignConstant.USER_CLIENT;
import static com.emazon.msvctransactions.infrastructure.utils.constants.FeignConstant.USER_URL;

@FeignClient(name = USER_CLIENT, url = USER_URL)
public interface AuthClient {
  @GetMapping("/validate-token")
  User getUserDetails(@RequestHeader(HttpHeaders.AUTHORIZATION) String token);
}
