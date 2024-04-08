package com.example.ass_and_rest_api.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ass_and_rest_api.R;
import com.example.ass_and_rest_api.model.ProductModel;
import com.example.ass_and_rest_api.screen.DetailProductActivity;
import com.example.ass_and_rest_api.service.HttpRequest;

import java.util.ArrayList;

public class ProductCartAdapter extends RecyclerView.Adapter<ProductCartAdapter.ViewHolder> {
    private ArrayList<ProductModel> itemProduct;
    private Context context;
    private HttpRequest httpRequest;

    public ProductCartAdapter(Context context, ArrayList<ProductModel> itemProduct) {
        this.context = context;
        this.itemProduct = itemProduct;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_atv_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        httpRequest = new HttpRequest();
        ProductModel productModel = itemProduct.get(position);
        holder.nameProduct.setText(productModel.getProductName());
        holder.price.setText(String.valueOf("$ " +productModel.getPrice()));
        Glide.with(context)
                .load(productModel.getImage())
                .into(holder.imgProduct);


    }

    @Override
    public int getItemCount() {
        return itemProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameProduct, price;
        public ImageView imgProduct;

        public ViewHolder(View itemView) {
            super(itemView);

            nameProduct = itemView.findViewById(R.id.txt_name_product_atv_cart);
            imgProduct = itemView.findViewById(R.id.img_product_atv_cart);
            price = itemView.findViewById(R.id.txt_price_atv_cart);
        }
    }
}