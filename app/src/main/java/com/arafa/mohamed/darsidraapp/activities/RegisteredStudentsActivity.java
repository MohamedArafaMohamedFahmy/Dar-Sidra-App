package com.arafa.mohamed.darsidraapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;

import com.arafa.mohamed.darsidraapp.R;

public class RegisteredStudentsActivity extends AppCompatActivity {

    AppCompatTextView tvToolbar;
    AppCompatImageButton btBackArrow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_students);

        btBackArrow = findViewById(R.id.button_back_arrow);
        tvToolbar = findViewById(R.id.text_toolbar);

        tvToolbar.setText(R.string.registered_student_appbar);
        btBackArrow.setOnClickListener(v -> finish());

    }
}