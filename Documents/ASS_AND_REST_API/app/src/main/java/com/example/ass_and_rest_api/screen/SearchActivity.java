package com.example.ass_and_rest_api.screen;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ass_and_rest_api.R;

public class SearchActivity extends AppCompatActivity {
    ImageView imgBack;
    EditText edtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);

        imgBack = findViewById(R.id.img_back_atv_search);
        edtSearch = findViewById(R.id.edt_search);

        edtSearch.requestFocus();
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}