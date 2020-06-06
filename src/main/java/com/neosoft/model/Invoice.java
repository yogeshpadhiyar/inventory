package com.neosoft.model;

public class Invoice {

    private String productName;
    private int quantity;
    private int Price;

    @Override
    public String toString() {
        return "Invoice{" +
                "productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", Price=" + Price +
                '}';
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }
}
