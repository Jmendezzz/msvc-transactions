package com.emazon.msvctransactions.domain.exceptions;

public class InvalidInputException extends BusinessException{
  public InvalidInputException(String message, String code) {
    super(message, code);
  }
}
