package com.neosoft.model;

public class OrderDetails {
    private int id;
    private int productId;
    private int orderId;
    private int quantity;
    private int price;

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", productId=" + productId +
                ", orderId=" + orderId +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
