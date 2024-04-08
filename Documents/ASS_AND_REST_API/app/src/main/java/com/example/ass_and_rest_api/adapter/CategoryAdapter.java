package com.example.ass_and_rest_api.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ass_and_rest_api.R;
import com.example.ass_and_rest_api.fragment.HomeFragment;
import com.example.ass_and_rest_api.handler.ItemCategoryHandler;
import com.example.ass_and_rest_api.model.CategoryModel;
import com.example.ass_and_rest_api.service.HttpRequest;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<CategoryModel> itemCategory;
    private Context context;
    private ItemCategoryHandler itemCategoryHandler;
    private HttpRequest httpRequest;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> itemCategory, ItemCategoryHandler itemCategoryHandler) {
        this.context = context;
        this.itemCategory = itemCategory;
        this.itemCategoryHandler = itemCategoryHandler;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        httpRequest = new HttpRequest();
        CategoryModel category = itemCategory.get(position);
        holder.categoryName.setText(category.getCateName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cateID = itemCategory.get(position).getCateID();
                itemCategoryHandler.FillProductByCategory(cateID);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemCategory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryName;

        public ViewHolder(View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.txt_category_name);
        }
    }
}