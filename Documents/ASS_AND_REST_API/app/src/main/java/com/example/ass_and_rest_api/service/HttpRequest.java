package com.example.ass_and_rest_api.service;

import static com.example.ass_and_rest_api.service.APIServices.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRequest {
    private APIServices apiServices;

    public HttpRequest(){
        //create retrofit
        apiServices = new Retrofit.Builder()
                .baseUrl(BASE_URL)//khởi tạo đối tượng retrofit và cấu hình thông số
                .addConverterFactory(GsonConverterFactory.create())//chuyển đổi đối tượng gson sang java
                .build().create(APIServices.class);
    }

    public APIServices callApi(){
        //Get retrofit
        return apiServices;
    }
}
