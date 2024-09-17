package com.emazon.msvctransactions.infrastructure.config;

import com.emazon.msvctransactions.domain.exceptions.BusinessException;
import com.emazon.msvctransactions.infrastructure.exceptions.ExceptionResponse;
import com.emazon.msvctransactions.infrastructure.exceptions.feign.InternalFeignException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class FeignErrorDecoderConfig implements ErrorDecoder {
  private final ObjectMapper objectMapper = new ObjectMapper();
  @Override
  public Exception decode(String methodKey, Response response) {
    if(response.status() == HttpStatus.BAD_REQUEST.value()){
      try {
        ExceptionResponse exceptionResponse = objectMapper.readValue(response.body().asInputStream(), ExceptionResponse.class);
        return new BusinessException(exceptionResponse.getMessage(), exceptionResponse.getCode());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
      return new InternalFeignException();
  }
}
