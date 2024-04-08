package com.example.ass_and_rest_api.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.ass_and_rest_api.R;
import com.example.ass_and_rest_api.model.ProductModel;
import com.example.ass_and_rest_api.service.HttpRequest;

public class DetailProductActivity extends AppCompatActivity {
    private ProductModel product;
    private HttpRequest httpRequest;
    ImageView imgBack, imgProduct;
    TextView txtNameProduct, txtPrice, txtDescription;
    AppCompatButton btnAddProductToCart, btnMuaNgay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_product);
        httpRequest = new HttpRequest();
        btnAddProductToCart = findViewById(R.id.btn_add_product_to_cart);

        Intent intent = getIntent();
        if (intent != null) {
            product = (ProductModel) intent.getSerializableExtra("product");
            if (product != null) {
                txtNameProduct = findViewById(R.id.txt_name_product_atv_detail);
                txtDescription = findViewById(R.id.txt_description_atv_detail);
                txtPrice = findViewById(R.id.txt_price_atv_detail);
                imgProduct = findViewById(R.id.img_product_atv_detail);
                txtNameProduct.setText(product.getProductName());
                txtDescription.setText(product.getDescription());
                txtPrice.setText(String.valueOf("$" + product.getPrice()));
                Glide.with(this)
                        .load(product.getImage())
                        .into(imgProduct);
            }
        }

        imgBack = findViewById(R.id.img_back_atv_detail);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnAddProductToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProductToCart(product);
            }
        });
    }

    private void addProductToCart(ProductModel product) {

        if (product != null) {
            ProductModel productModel = new ProductModel();
            String nameProduct = product.getProductName();
            String image = product.getImage();
            String description = product.getDescription();
            String price = String.valueOf(product.getPrice());

            productModel.setProductName(nameProduct);
            productModel.setImage(image);
            productModel.setDescription(description);
            productModel.setPrice(Integer.parseInt(price));

            httpRequest.callApi().addProductToCart(productModel);
            Toast.makeText(DetailProductActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}