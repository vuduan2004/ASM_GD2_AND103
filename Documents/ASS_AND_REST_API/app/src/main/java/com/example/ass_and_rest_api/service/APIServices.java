package com.example.ass_and_rest_api.service;

import com.example.ass_and_rest_api.model.CategoryModel;
import com.example.ass_and_rest_api.model.ProductModel;
import com.example.ass_and_rest_api.model.ResponseModel;
import com.example.ass_and_rest_api.model.UserModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIServices {
    public static String BASE_URL = "http://10.24.8.123:3000/api/";

    @POST("register-account")
    Call<ResponseModel<UserModel>> registerAccount(@Body UserModel user);

    @POST("login")
    Call<ResponseModel<UserModel>> login(@Body UserModel user);
    @GET("get-list-category")
    Call<ResponseModel<ArrayList<CategoryModel>>> getListCategory();
    @GET("get-all-product")
    Call<ResponseModel<ArrayList<ProductModel>>> getAllProduct();
    @GET("get-product-by-category/{cateID}")
    Call<ResponseModel<ArrayList<ProductModel>>> getProductByCategory(@Path("cateID") String cateID);
    @GET("get-product-cart")
    Call<ResponseModel<ArrayList<ProductModel>>> getProductCart();
    @POST("add-product-to-cart")
    Call<ResponseModel<ProductModel>> addProductToCart(@Body ProductModel product);

}
