package com.example.ass_and_rest_api.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ass_and_rest_api.R;
import com.example.ass_and_rest_api.adapter.CategoryAdapter;
import com.example.ass_and_rest_api.adapter.ProductAdapter;
import com.example.ass_and_rest_api.adapter.SliderAdapter;
import com.example.ass_and_rest_api.handler.ItemCategoryHandler;
import com.example.ass_and_rest_api.model.CategoryModel;
import com.example.ass_and_rest_api.model.ProductModel;
import com.example.ass_and_rest_api.model.ResponseModel;
import com.example.ass_and_rest_api.model.SliderItem;
import com.example.ass_and_rest_api.screen.AllProductActivity;
import com.example.ass_and_rest_api.screen.CartActivity;
import com.example.ass_and_rest_api.screen.SearchActivity;
import com.example.ass_and_rest_api.service.HttpRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements ItemCategoryHandler {

    private ViewPager2 viewPager2;
    SliderAdapter sliderAdapter;
    private Handler sliderHandler = new Handler();
    private RecyclerView rcvCategory, rcvProduct;
    private CategoryAdapter categoryAdapter;
    private ProductAdapter productAdapter;
    private List<CategoryModel> categoryList;
    private ArrayList<ProductModel> productModelList;
    ImageView imgSearch, imgCart;
    private HttpRequest httpRequest;
    LinearLayout lnlXemThem;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager2 = view.findViewById(R.id.banner_frm_home);
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.img_banner01, "https://www.ysl.com/en-en/saint-laurent-men/ready-to-wear/all-ready-to-wear"));
        sliderItems.add(new SliderItem(R.drawable.img_banner02, "https://www.ysl.com/en-en/saint-laurent-men/ready-to-wear/all-ready-to-wear"));
        sliderItems.add(new SliderItem(R.drawable.img_banner03, "https://www.ysl.com/en-en/saint-laurent-men/ready-to-wear/all-ready-to-wear"));
        sliderItems.add(new SliderItem(R.drawable.img_banner04, "https://www.ysl.com/en-en/saint-laurent-men/ready-to-wear/all-ready-to-wear"));
        sliderAdapter = new SliderAdapter(sliderItems, viewPager2);
        viewPager2.setAdapter(sliderAdapter);

        rcvCategory = view.findViewById(R.id.rcv_category);
        httpRequest = new HttpRequest();
        //thực thi call api
        httpRequest.callApi().getListCategory().enqueue(getCategoryAPI);
        rcvProduct = view.findViewById(R.id.rcv_product_frm_home);
        getProductListByCategory("1");
        sliderAdapter.setCallBack(new SliderAdapter.OncallBack() {
            @Override
            public void onclick(String url) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(4);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(50));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 3000);
            }
        });

        imgSearch = view.findViewById(R.id.img_search_atv_home);
        imgCart = view.findViewById(R.id.img_cart_avt_home);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CartActivity.class);
                startActivity(intent);
            }
        });

        lnlXemThem = view.findViewById(R.id.lnl_xemthem);
        lnlXemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AllProductActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void getData(ArrayList<CategoryModel> list) {
        categoryAdapter = new CategoryAdapter(getContext(), list, this);
        rcvCategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcvCategory.setAdapter(categoryAdapter);

    }

    Callback<ResponseModel<ArrayList<CategoryModel>>> getCategoryAPI = new Callback<ResponseModel<ArrayList<CategoryModel>>>() {
        @Override
        public void onResponse(Call<ResponseModel<ArrayList<CategoryModel>>> call, Response<ResponseModel<ArrayList<CategoryModel>>> response) {
            // khi call thành công sẽ chạy vào hàm này
            if (response.isSuccessful()) {
                // check status
                if (response.body().getStatus() == 200) {
                    // lấy data
                    ArrayList<CategoryModel> list = response.body().getData();
                    Log.d("List", "onResponse: " + list);
                    // set dữ liệu lên rcv
                    getData(list);

                }
            }
        }

        @Override
        public void onFailure(Call<ResponseModel<ArrayList<CategoryModel>>> call, Throwable t) {
            Log.d(">>>GetListDistributor", "onFailure: " + t.getMessage());
        }
    };



    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 3000);
    }
    private void getProductListByCategory(String cateID) {
        // Gọi API hoặc xử lý logic để lấy danh sách sản phẩm dựa trên categoryId
        // Ví dụ:
        httpRequest.callApi().getProductByCategory(cateID).enqueue(new Callback<ResponseModel<ArrayList<ProductModel>>>() {
            @Override
            public void onResponse(Call<ResponseModel<ArrayList<ProductModel>>> call, Response<ResponseModel<ArrayList<ProductModel>>> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 200) {
                        ArrayList<ProductModel> productList = response.body().getData();
                        // Hiển thị danh sách sản phẩm lên RecyclerView rcvProduct
                        showProductList(productList);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<ArrayList<ProductModel>>> call, Throwable t) {
                Log.d(">>>GetProductList", "onFailure: " + t.getMessage());
            }
        });
    }

    private void showProductList(ArrayList<ProductModel> productList) {
        // Khởi tạo adapter và set data cho RecyclerView rcvProduct
        productAdapter = new ProductAdapter(getContext(), productList);
        rcvProduct.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rcvProduct.setLayoutManager(layoutManager);
        rcvProduct.setAdapter(productAdapter);
    }

    @Override
    public void FillProductByCategory(String cateID) {
        getProductListByCategory(cateID);
    }
}