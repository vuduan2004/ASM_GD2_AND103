package com.example.lab5.services;

import com.example.lab5.model.Distributor;
import com.example.lab5.model.Response;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServices {
//    Trường hợp dùng JSON server trên máy cá nhân:
//            - Nếu máy ảo android studio thì: Chạy json server không cho tham số -h , trong code app thì viết link:  http://10.0.2.2/xxxxxx
//            - Nếu máy ảo genymotion thì: Chạy json server không cho tham số -h , trong code app thì viết link:  http://10.0.3.2/xxxxxx
//            - Nếu máy ảo iphone (với macbook) thì: Chạy json server không cho tham số -h , trong code app thì viết link:  http://127.0.0.1/xxxxxx  hoặc  http://localhost/xxxxxx
//    không cần lấy IP wifi
//định nghĩa các phương thức trong interface
// để mô tả các request mà ứng dụng  sẽ gửi tới server
    //Tạo các interface Java để mô tả các yêu cầu API
// bằng cách sử dụng các annotation của Retrofit như @GET, @POST, @PUT, @DELETE,...
public static String BASE_URL = "http://10.0.3.2:3000/api/";//url cua api
    @GET("get-list-distributor")
    Call<Response<ArrayList<Distributor>>> getListDistributor();

    @GET("search-distributor")
    Call<Response<ArrayList<Distributor>>> searchDistributor(@Query("key") String key);

    @POST("add-distributor")
    Call<Response<Distributor>> addDistributor(@Body Distributor distributor);

    //param url se bo vao {}
    @DELETE("delete-distributor-by-id/{id}")
    Call<Response<Distributor>> deleteDistributorById(@Path("id") String id);

    @PUT("update-distributor-by-id/{id}")
    Call<Response<Distributor>> updateDistributorById(@Path("id") String id, @Body Distributor distributor);
}
