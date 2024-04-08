package com.example.ass_and_rest_api.model;

import com.google.gson.annotations.SerializedName;

public class CategoryModel {
    @SerializedName("_id")
    private String id;
    private String cateName;
    private String cateID;

    public CategoryModel(String id, String cateName, String cateID) {
        this.id = id;
        this.cateName = cateName;
        this.cateID = cateID;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCateID() {
        return cateID;
    }

    public void setCateID(String cateID) {
        this.cateID = cateID;
    }
}
