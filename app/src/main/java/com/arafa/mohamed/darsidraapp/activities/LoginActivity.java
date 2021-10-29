package com.arafa.mohamed.darsidraapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.arafa.mohamed.darsidraapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    AppCompatTextView btForgotPassword, btSignUp,textError;
    AppCompatButton btSignIn;
    TextInputEditText etEmailAddress,etPassword;
    TextInputLayout textLayoutPassword;
    String emailAddress,password;
    FirebaseAuth firebaseAuth;
    LinearLayout linearProgressBar;
    boolean checkPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();
        etEmailAddress=findViewById(R.id.editText_email);
        etPassword=findViewById(R.id.editText_password);
        btForgotPassword=findViewById(R.id.text_forgot_password);
        btSignIn=findViewById(R.id.button_signIn);
        btSignUp=findViewById(R.id.text_sign_up);
        textError=findViewById(R.id.text_error);
        linearProgressBar = findViewById(R.id.linear_progress_bar);
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

        signIn();
        signUp();
        forgotPassword();
    }

    public void signUp(){
        btSignUp.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegistrationActivity.class)));
    }
    public void signIn(){

        btSignIn.setOnClickListener(v -> {
            emailAddress= Objects.requireNonNull(etEmailAddress.getText()).toString();
            password= Objects.requireNonNull(etPassword.getText()).toString();
            if(!emailAddress.isEmpty() && !password.isEmpty() && password.length() >= 6){
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                etPassword.setCursorVisible(false);
                closeKeyboard();
                textError.setVisibility(View.GONE);
                linearProgressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(emailAddress,password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        linearProgressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this, "تم تسجيل الدخول بنجاح", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, RegisteredStudentsActivity.class));
                        finish();
                    }
                    else{

                        linearProgressBar.setVisibility(View.GONE);
                        etPassword.setCursorVisible(true);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(LoginActivity.this, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }

            if(emailAddress.isEmpty()){
                etEmailAddress.setError("الرجاء إدخال عنوان البريد الإلكتروني");
            }
            if( password.isEmpty()) {
                textError.setVisibility(View.VISIBLE);
                textError.setText(R.string.text_error1);
            }
            if ( password.length() < 6 && password.length() > 0 )
            {
                textError.setVisibility(View.VISIBLE);
                textError.setText(R.string.text_error2);
            }
        });
    }

    public void forgotPassword(){
        btForgotPassword.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class)));
    }

    private void closeKeyboard(){
        View view= this.getCurrentFocus();
        if (view != null){
            InputMethodManager imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);

        }
    }
}