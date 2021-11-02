package com.arafa.mohamed.darsidraapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.models.UserInformationModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;
import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {
    AppCompatTextView btSignIn,textErrorPassword,textErrorConfirm;
    AppCompatButton btSignUp;
    AppCompatEditText etUserName,etEmailAddress,etPassword,etConfirmPassword;
    LinearLayout linearProgressBar;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    String userName,emailAddress,password,confirmPassword,userId;
    UserInformationModel userInformationModel;
    TextInputLayout textLayoutPassword,textLayoutConfirmPassword;
    boolean checkPassword,checkConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btSignUp=findViewById(R.id.button_signUp);
        btSignIn=findViewById(R.id.text_sign_in);
        etUserName=findViewById(R.id.editText_userName);
        etEmailAddress=findViewById(R.id.editText_email);
        etPassword=findViewById(R.id.editText_password);
        etConfirmPassword=findViewById(R.id.editText_confirm_password);
        textErrorPassword = findViewById(R.id.text_error_password);
        textErrorConfirm = findViewById(R.id.text_error_confirm);
        linearProgressBar = findViewById(R.id.linear_progress_bar);
        firebaseAuth= FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        textLayoutPassword = findViewById(R.id.password_layout);
        textLayoutConfirmPassword = findViewById(R.id.confirm_password_layout);
        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        etConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
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
                etConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                checkConfirmPassword = false;
            }
            else {
                textLayoutConfirmPassword.setStartIconDrawable(R.drawable.ic_eye);
                etConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                checkConfirmPassword = true;
            }
        });

        signIn();
        signUp();

    }

    public void signUp(){
        btSignUp.setOnClickListener(v -> {
            userName= Objects.requireNonNull(etUserName.getText()).toString();
            emailAddress= Objects.requireNonNull(etEmailAddress.getText()).toString();
            password= Objects.requireNonNull(etPassword.getText()).toString();
            confirmPassword= Objects.requireNonNull(etConfirmPassword.getText()).toString();


            if(!userName.isEmpty() && !emailAddress.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty() && password.length() >=6 && confirmPassword.equals(password)){
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                etConfirmPassword.setCursorVisible(false);
                closeKeyboard();
                textErrorPassword.setVisibility(View.GONE);
                textErrorConfirm.setVisibility(View.GONE);
                linearProgressBar.setVisibility(View.VISIBLE);

                firebaseAuth.createUserWithEmailAndPassword(emailAddress,password).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        userInformationModel=new UserInformationModel(userName,emailAddress);
                        userId= Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                        databaseReference.child("Users").child(userId).child("YourData").setValue(userInformationModel).addOnCompleteListener(task1 -> {
                            if(task1.isSuccessful()){
                                linearProgressBar.setVisibility(View.GONE);
                                Toast.makeText(RegistrationActivity.this, "تم التسجيل بنجاح", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistrationActivity.this, RegisteredTeachersStudentsActivity.class));
                                finish();
                            }
                        });
                    }
                    else {
                        linearProgressBar.setVisibility(View.GONE);
                        etPassword.setCursorVisible(true);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(RegistrationActivity.this, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                    }

                });
            }

            if(userName.isEmpty()){
                etUserName.setError("الرجاء إدخال اسم المستخدم");
            }

            if(emailAddress.isEmpty()){
                etEmailAddress.setError("الرجاء إدخال عنوان البريد الإلكتروني");
            }
            if(password.isEmpty()){
                textErrorPassword.setVisibility(View.VISIBLE);
                textErrorPassword.setText(R.string.text_error1);
            }
            if (password.length() < 6 && password.length() > 0)
            {
                textErrorPassword.setVisibility(View.VISIBLE);
                textErrorPassword.setText(R.string.text_error2);
            }

            if(confirmPassword.isEmpty()){
                textErrorConfirm.setVisibility(View.VISIBLE);
                textErrorConfirm.setText(R.string.text_error3);
            }
            if(!confirmPassword.equals(password)){
                textErrorConfirm.setVisibility(View.VISIBLE);
                textErrorConfirm.setText(R.string.text_error4);
            }
        });
    }

    public void signIn(){
        btSignIn.setOnClickListener(v -> startActivity(new Intent(RegistrationActivity.this, LoginActivity.class)));
    }

    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            updateUI();
            finish();
        }
    }

    public void updateUI() {
        startActivity(new Intent(RegistrationActivity.this, RegisteredTeachersStudentsActivity.class));
    }
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}