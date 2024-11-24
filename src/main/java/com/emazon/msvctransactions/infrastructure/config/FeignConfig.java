package com.emazon.msvctransactions.infrastructure.config;

import feign.Logger;
import feign.Request;
import feign.codec.ErrorDecoder;
import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
  @Bean
  public JwtTokenInterceptor jwtTokenInterceptor() {
    return new JwtTokenInterceptor();
  }

  @Bean
  public ErrorDecoder feignErrorDecoderConfig() {
    return new FeignErrorDecoderConfig();
  }

  @Bean
  public OkHttpClient client() {
    return new OkHttpClient();
  }

}
