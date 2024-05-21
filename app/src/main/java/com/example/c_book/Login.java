package com.example.c_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.c_book.database.LoginDatasource;

public class Login extends AppCompatActivity {

    EditText edtUsername, edtPass;
    LoginDatasource loginDatasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edt_user);
        edtPass = findViewById(R.id.edt_pass);
        Button btnLogin = findViewById(R.id.btn_login);
        Button btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        loginDatasource = new LoginDatasource(this);
        loginDatasource.open();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginButtonClick(v);
            }
        });


    }

    // Trong một phương thức xử lý sự kiện khi người dùng nhấn nút đăng nhập
    public void onLoginButtonClick(View view) {
        String username = edtUsername.getText().toString();
        String password = edtPass.getText().toString();

        if (loginDatasource.checkLogin(username, password)) {
            saveUsernameToSharedPreferences(username);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        } else {
            Toast.makeText(this, "Đăng nhập thất bại. Vui lòng kiểm tra tên đăng nhập và mật khẩu.", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUsernameToSharedPreferences(String username) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.apply();
    }
}