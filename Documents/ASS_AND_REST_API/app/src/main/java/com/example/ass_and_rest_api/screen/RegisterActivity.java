package com.example.ass_and_rest_api.screen;

import android.os.Bundle;
import android.view.View;
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
import com.example.ass_and_rest_api.service.HttpRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    TextView txtLogin;
    private HttpRequest httpRequest;
    EditText edtEmail, edtUserName, edtPassword, edtRePassword;
    AppCompatButton btnRegiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        edtEmail = findViewById(R.id.ed_email_atv_register);
        edtUserName = findViewById(R.id.ed_username_atv_register);
        edtPassword = findViewById(R.id.ed_password_atv_register);
        edtUserName = findViewById(R.id.ed_username_atv_register);
        edtRePassword = findViewById(R.id.ed_re_password_atv_register);
        btnRegiter = findViewById(R.id.btn_register);
        txtLogin = findViewById(R.id.txt_login);
        httpRequest = new HttpRequest();
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnRegiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edtEmail.getText().toString().trim();
                String username = edtUserName.getText().toString().trim();
                String password = edtPassword.getText().toString();
                String rePassword = edtRePassword.getText().toString();

                // Kiểm tra các trường thông tin không được để trống
                if (email.isEmpty() || username.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
                    // Hiển thị thông báo lỗi
                    Toast.makeText(RegisterActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra mật khẩu và nhập lại mật khẩu khớp nhau
                if (!password.equals(rePassword)) {
                    // Hiển thị thông báo lỗi
                    Toast.makeText(RegisterActivity.this, "Mật khẩu và nhập lại mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Tạo đối tượng UserModel từ các thông tin đã nhập
                UserModel user = new UserModel(email, username, password, true);

                // Gửi yêu cầu đăng ký
                Call<ResponseModel<UserModel>> registerCall = httpRequest.callApi().registerAccount(user);
                registerCall.enqueue(new Callback<ResponseModel<UserModel>>() {
                    @Override
                    public void onResponse(Call<ResponseModel<UserModel>> call, Response<ResponseModel<UserModel>> response) {
                        if (response.isSuccessful()) {
                            ResponseModel<UserModel> responseBody = response.body();
                            // Xử lý kết quả trả về khi đăng ký thành công
                            if (responseBody != null) {
                                Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

                            }
                        } else {
                            // Xử lý khi đăng ký thất bại
                            Toast.makeText(RegisterActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel<UserModel>> call, Throwable t) {
                        // Xử lý khi có lỗi xảy ra trong quá trình gửi yêu cầu
                        Toast.makeText(RegisterActivity.this, "Đã xảy ra lỗi", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }


}