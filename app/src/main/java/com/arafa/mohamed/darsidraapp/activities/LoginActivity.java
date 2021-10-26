package com.arafa.mohamed.darsidraapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import com.arafa.mohamed.darsidraapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Locale;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText etPassword;
    TextInputLayout textLayoutPassword;
    boolean checkPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etPassword = findViewById(R.id.editText_password);
        textLayoutPassword = findViewById(R.id.password_layout);
        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        checkPassword = true;

        textLayoutPassword.setStartIconOnClickListener(v -> {
            if(Objects.requireNonNull(AppCompatResources.getDrawable(LoginActivity.this, R.drawable.ic_eye)).isVisible() && checkPassword ){
                textLayoutPassword.setStartIconDrawable(R.drawable.ic_eye_off);
                etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                checkPassword = false;
            }
            else {
                textLayoutPassword.setStartIconDrawable(R.drawable.ic_eye);
                etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                checkPassword = true;
            }
        });
    }
}