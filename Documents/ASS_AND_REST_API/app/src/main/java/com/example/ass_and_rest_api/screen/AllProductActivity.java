package com.example.ass_and_rest_api.screen;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ass_and_rest_api.R;
import com.example.ass_and_rest_api.adapter.ProductAdapter;
import com.example.ass_and_rest_api.model.ProductModel;
import com.example.ass_and_rest_api.model.ResponseModel;
import com.example.ass_and_rest_api.service.HttpRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllProductActivity extends AppCompatActivity {
    private HttpRequest httpRequest;
    private ProductAdapter productAdapter;
    private RecyclerView rcvAllProduct;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_product);
        imgBack = findViewById(R.id.img_back_atv_all_product);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        rcvAllProduct = findViewById(R.id.rcv_all_product);
        httpRequest = new HttpRequest();
        httpRequest.callApi().getAllProduct().enqueue(getAllProductAPI);

    }

    private void getData(ArrayList<ProductModel> list) {
        productAdapter = new ProductAdapter(this, list);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rcvAllProduct.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
        rcvAllProduct.setLayoutManager(layoutManager);
        rcvAllProduct.setAdapter(productAdapter);

    }

    Callback<ResponseModel<ArrayList<ProductModel>>> getAllProductAPI = new Callback<ResponseModel<ArrayList<ProductModel>>>() {
        @Override
        public void onResponse(Call<ResponseModel<ArrayList<ProductModel>>> call, Response<ResponseModel<ArrayList<ProductModel>>> response) {
            // khi call thành công sẽ chạy vào hàm này
            if (response.isSuccessful()) {
                // check status
                if (response.body().getStatus() == 200) {
                    // lấy data
                    ArrayList<ProductModel> list = response.body().getData();
                    Log.d("List", "onResponse: " + list);
                    getData(list);
                    // Thông báo
//                    Toast.makeText(MainActivity.this, response.body().getMessenger(), Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<ResponseModel<ArrayList<ProductModel>>> call, Throwable t) {
            Log.d(">>>GetListSinhVien", "onFailure: " + t.getMessage());
        }
    };
}