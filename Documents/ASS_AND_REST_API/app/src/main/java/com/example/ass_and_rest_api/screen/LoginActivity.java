package com.example.ass_and_rest_api.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ass_and_rest_api.R;
import com.example.ass_and_rest_api.model.ResponseModel;
import com.example.ass_and_rest_api.model.UserModel;
import com.example.ass_and_rest_api.service.APIServices;
import com.example.ass_and_rest_api.service.HttpRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    AppCompatButton btnLogin;
    TextView txtRegister;
    EditText edtUserName, edtPassword;
    CheckBox cbxRememberPass;
    private HttpRequest httpRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btn_login);
        txtRegister = findViewById(R.id.txt_register);
        edtUserName = findViewById(R.id.ed_username_atv_login);
        edtPassword = findViewById(R.id.ed_password_atv_login);
        cbxRememberPass = findViewById(R.id.cbx_remember_pass);
        httpRequest = new HttpRequest();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUserName.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if (username.isEmpty() || password.isEmpty()) {
                    // Hiển thị thông báo lỗi
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                UserModel user = new UserModel(username, password);

                Call<ResponseModel<UserModel>> call = httpRequest.callApi().login(user);
                call.enqueue(new Callback<ResponseModel<UserModel>>() {
                    @Override
                    public void onResponse(Call<ResponseModel<UserModel>> call, Response<ResponseModel<UserModel>> response) {
                        if (response.isSuccessful()) {
                            ResponseModel<UserModel> responseModel = response.body();
                            if (responseModel != null && responseModel.getStatus() == 200) {
                                // Đăng nhập thành công
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // Đăng nhập thất bại
                                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }


                    @Override
                    public void onFailure(Call<ResponseModel<UserModel>> call, Throwable t) {
                        // Xảy ra lỗi trong quá trình gửi yêu cầu
                        Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
    }


