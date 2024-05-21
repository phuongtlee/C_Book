package com.example.c_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.c_book.database.LoginDatasource;

public class Register extends AppCompatActivity {

    EditText edtUsername, edtPass;
    LoginDatasource registerDatasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUsername = findViewById(R.id.edt_user);
        edtPass = findViewById(R.id.edt_pass);
        Button btnRegister = findViewById(R.id.btn_register_2);

        registerDatasource = new LoginDatasource(this);
        registerDatasource.open();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegisterButtonClick(v);
            }
        });

    }

    public void onRegisterButtonClick(View view) {
        String username = edtUsername.getText().toString();
        String password = edtPass.getText().toString();

        boolean isRegistered = registerDatasource.register(username, password);
        if (isRegistered) {
            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Đăng ký thất bại. Tên đăng nhập đã tồn tại.", Toast.LENGTH_SHORT).show();
        }
    }
}