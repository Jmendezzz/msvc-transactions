package com.emazon.msvctransactions.domain.models;

public class SaleDetail {
  private Long id;
  private Long articleId;
  private Integer quantity;
  private Double subTotal;
  public SaleDetail() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Double getSubTotal() {
    return subTotal;
  }

  public void setSubTotal(Double subTotal) {
    this.subTotal = subTotal;
  }
}
