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
import com.example.ass_and_rest_api.model.CategoryModel;
import com.example.ass_and_rest_api.model.ProductModel;
import com.example.ass_and_rest_api.screen.DetailProductActivity;
import com.example.ass_and_rest_api.service.HttpRequest;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private ArrayList<ProductModel> itemProduct;
    private Context context;
    private HttpRequest httpRequest;

    public ProductAdapter(Context context, ArrayList<ProductModel> itemProduct) {
        this.context = context;
        this.itemProduct = itemProduct;
    }
    public void updateData(ArrayList<ProductModel> newData) {
        itemProduct.clear();
        itemProduct.addAll(newData);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_frm_home, parent, false);
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo Intent
                Intent intent = new Intent(context, DetailProductActivity.class);
                // Truyền dữ liệu sản phẩm
                intent.putExtra("product", productModel);
                // Chuyển sang DetailProductActivity
                context.startActivity(intent);
            }
        });

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

            nameProduct = itemView.findViewById(R.id.txt_name_product_frm_home);
            imgProduct = itemView.findViewById(R.id.img_product_frm_home);
            price = itemView.findViewById(R.id.txt_price_frm_home);
        }
    }
}