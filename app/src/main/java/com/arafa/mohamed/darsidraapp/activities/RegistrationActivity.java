package com.arafa.mohamed.darsidraapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.ResourcesCompat;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.arafa.mohamed.darsidraapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Locale;
import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {
    TextInputEditText etPassword,etConformPassword;
    TextInputLayout textLayoutPassword,textLayoutConfirmPassword;
    boolean checkPassword,checkConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etPassword = findViewById(R.id.editText_password);
        etConformPassword = findViewById(R.id.editText_confirm_password);
        textLayoutPassword = findViewById(R.id.password_layout);
        textLayoutConfirmPassword = findViewById(R.id.confirm_password_layout);
        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        etConformPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        checkPassword = true;
        checkConfirmPassword = true;

        textLayoutPassword.setStartIconOnClickListener(v -> {
            if(Objects.requireNonNull(AppCompatResources.getDrawable(RegistrationActivity.this, R.drawable.ic_eye)).isVisible() && checkPassword ){
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


        textLayoutConfirmPassword.setStartIconOnClickListener(v -> {
            if(Objects.requireNonNull(AppCompatResources.getDrawable(RegistrationActivity.this, R.drawable.ic_eye)).isVisible() && checkConfirmPassword ){
                textLayoutConfirmPassword.setStartIconDrawable(R.drawable.ic_eye_off);
                etConformPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                checkConfirmPassword = false;
            }
            else {
                textLayoutConfirmPassword.setStartIconDrawable(R.drawable.ic_eye);
                etConformPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                checkConfirmPassword = true;
            }
        });

    }

}