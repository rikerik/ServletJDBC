package com.example.DTO;

import java.sql.Timestamp;

public class ProductDTO {
    private Integer productId;
    private String productName;
    private String supplier;
    private double price;
    private Integer quantity;
    private Integer createdByUserId;
    private Timestamp createdOn;
    private Integer modifiedByUserId;
    private Timestamp modifiedOn;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Integer createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getModifiedByUserId() {
        return modifiedByUserId;
    }

    public void setModifiedByUserId(Integer modifiedByUserId) {
        this.modifiedByUserId = modifiedByUserId;
    }

    public Timestamp getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Timestamp modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    @Override
    public String toString() {
        return "ProductDTO [productId=" + productId + ", productName=" + productName + ", supplier=" + supplier
                + ", price=" + price + ", quantity=" + quantity + ", createdByUserId=" + createdByUserId
                + ", createdOn=" + createdOn + ", modifiedByUserId=" + modifiedByUserId + ", modifiedOn=" + modifiedOn
                + "]";
    }

}
