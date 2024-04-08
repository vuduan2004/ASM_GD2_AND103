package com.example.ass_and_rest_api.screen;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ass_and_rest_api.R;
import com.example.ass_and_rest_api.adapter.ProductAdapter;
import com.example.ass_and_rest_api.adapter.ProductCartAdapter;
import com.example.ass_and_rest_api.model.ProductModel;
import com.example.ass_and_rest_api.model.ResponseModel;
import com.example.ass_and_rest_api.service.HttpRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    ImageView imgBack;
    private HttpRequest httpRequest;
    private ProductCartAdapter productCartAdapter;
    private RecyclerView rcvCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);

        imgBack = findViewById(R.id.img_back_atv_cart);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        rcvCart = findViewById(R.id.rcv_cart);
        httpRequest = new HttpRequest();
        httpRequest.callApi().getProductCart().enqueue(getProductCartAPI);

    }

    private void getData(ArrayList<ProductModel> list) {
        productCartAdapter = new ProductCartAdapter(this, list);
        rcvCart.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        rcvCart.setAdapter(productCartAdapter);

    }

    Callback<ResponseModel<ArrayList<ProductModel>>> getProductCartAPI = new Callback<ResponseModel<ArrayList<ProductModel>>>() {
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
            Log.d(">>>GetListProductCart", "onFailure: " + t.getMessage());
        }
    };
}