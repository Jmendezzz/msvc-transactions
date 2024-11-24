package com.emazon.msvctransactions.domain.models;

public class CheckoutItem {
  private Long articleId;
  private String articleName;
  private Integer quantity;
  private Double price;

  public CheckoutItem(Long articleId, Integer quantity, Double price, String articleName) {
    this.articleId = articleId;
    this.quantity = quantity;
    this.price = price;
    this.articleName = articleName;
  }

  public CheckoutItem() {
  }

  public Long getArticleId() {
    return articleId;
  }

  public void setArticleId(Long articleId) {
    this.articleId = articleId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getArticleName() {
    return articleName;
  }
  public void setArticleName(String articleName) {
    this.articleName = articleName;
  }
}
