package com.emazon.msvctransactions.domain.exceptions;

public class BusinessException extends RuntimeException {
  private String code;
    public BusinessException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
