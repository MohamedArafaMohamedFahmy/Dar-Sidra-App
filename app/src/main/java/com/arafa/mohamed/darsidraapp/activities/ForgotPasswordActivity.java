package com.arafa.mohamed.darsidraapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.arafa.mohamed.darsidraapp.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;


import java.util.Objects;

public class ForgotPasswordActivity extends AppCompatActivity {

    TextInputEditText etEmailAddress;
    AppCompatTextView tvMessageReset,tvToolbar;
    AppCompatButton btFindAccount;
    FirebaseAuth firebaseauth;
    String emailAddress;
    LinearLayout linearProgressBar;
    AppCompatImageButton btBackArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        firebaseauth= FirebaseAuth.getInstance();
        etEmailAddress=findViewById(R.id.editText_email);
        btFindAccount=findViewById(R.id.button_findAccount);
        tvMessageReset=findViewById(R.id.text_message_reset);
        linearProgressBar = findViewById(R.id.linear_progress_bar);
        btBackArrow = findViewById(R.id.button_back_arrow);
        tvToolbar = findViewById(R.id.text_toolbar);

        tvToolbar.setText(R.string.reset_password_appbar);
        btBackArrow.setOnClickListener(v -> finish());

        resetPassword();
    }

    public void resetPassword(){

        btFindAccount.setOnClickListener(v -> {
            emailAddress= Objects.requireNonNull(etEmailAddress.getText()).toString();

            if(!emailAddress.isEmpty()){
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                etEmailAddress.setCursorVisible(false);
                closeKeyboard();
                linearProgressBar.setVisibility(View.VISIBLE);

               firebaseauth.sendPasswordResetEmail(emailAddress).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        linearProgressBar.setVisibility(View.GONE);
                        etEmailAddress.setCursorVisible(true);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        tvMessageReset.setText(getResources().getString(R.string.message_forgot_password,emailAddress));
                    }
                    else{
                        linearProgressBar.setVisibility(View.GONE);
                        etEmailAddress.setCursorVisible(true);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(ForgotPasswordActivity.this, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            if (emailAddress.isEmpty()){
                etEmailAddress.setError("الرجاء إدخال عنوان البريد الإلكتروني");
            }
        });
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}