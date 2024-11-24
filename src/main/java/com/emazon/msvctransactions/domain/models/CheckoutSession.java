package com.emazon.msvctransactions.domain.models;

public class CheckoutSession {
  private String url;
  private String sessionId;
  public CheckoutSession(String url, String sessionId) {
    this.url = url;
    this.sessionId = sessionId;
  }
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

}


