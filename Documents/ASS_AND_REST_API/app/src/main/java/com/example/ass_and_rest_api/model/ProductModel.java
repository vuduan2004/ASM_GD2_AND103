package com.example.ass_and_rest_api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductModel implements Serializable {
    @SerializedName("_id")
    private String id;
    private String productName;
    private String image;
    private String description;
    private int price;
    private String cateID;

    public ProductModel(String productName, String image, String description, int price, String cateID) {
        this.productName = productName;
        this.image = image;
        this.description = description;
        this.price = price;
        this.cateID = cateID;
    }
    public ProductModel(String productName, String image, String description, int price) {
        this.productName = productName;
        this.image = image;
        this.description = description;
        this.price = price;

    }

    public ProductModel(String id, String productName, String image, String description, int price, String cateID) {
        this.id = id;
        this.productName = productName;
        this.image = image;
        this.description = description;
        this.price = price;
        this.cateID = cateID;
    }

    public ProductModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCateID() {
        return cateID;
    }

    public void setCateID(String cateID) {
        this.cateID = cateID;
    }
}
