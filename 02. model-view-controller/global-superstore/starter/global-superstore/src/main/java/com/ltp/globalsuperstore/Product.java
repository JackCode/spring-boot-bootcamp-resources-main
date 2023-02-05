package com.ltp.globalsuperstore;

import java.util.UUID;

public class Product {
  private String category;
  private String name;
  private String price;
  private String discount;
  private String orderDate;
  private String id;

  public Product() {
    this.id = UUID.randomUUID().toString();
  }

  public String getCategory() {
    return this.category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPrice() {
    return this.price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getDiscount() {
    return this.discount;
  }

  public void setDiscount(String discount) {
    this.discount = discount;
  }

  public String getOrderDate() {
    return this.orderDate;
  }

  public void setOrderDate(String orderDate) {
    this.orderDate = orderDate;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
