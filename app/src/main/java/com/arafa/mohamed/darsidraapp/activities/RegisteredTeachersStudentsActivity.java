package com.arafa.mohamed.darsidraapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.adapter.TabsRegisteredAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class RegisteredTeachersStudentsActivity extends AppCompatActivity {

    AppCompatTextView tvToolbar;
    AppCompatImageButton btBackArrow, btLogout, btRegistered, btFilter;
    AppCompatButton btYes,btNo,btRegisterStudent,btRegisterTeacher;
    TabLayout tabsItemsRegistered;
    ViewPager2 viewPager;
    TabsRegisteredAdapter tabsAdapter;
    ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_teachers_students);

        btBackArrow = findViewById(R.id.button_back_arrow);
        btLogout = findViewById(R.id.button_log_out);
        btFilter = findViewById(R.id.button_filter);
        btRegistered = findViewById(R.id.button_registered);
        tvToolbar = findViewById(R.id.text_toolbar);

        tvToolbar.setText(R.string.main_home_appbar);
        btBackArrow.setVisibility(View.GONE);
        btLogout.setVisibility(View.VISIBLE);
        btFilter.setVisibility(View.VISIBLE);
        btRegistered.setVisibility(View.VISIBLE);

        btLogout.setOnClickListener(v -> showCustomDialog());

        btRegistered.setOnClickListener(v -> showCustomDialogSelected() );
        btFilter.setOnClickListener(v -> startActivity(new Intent(RegisteredTeachersStudentsActivity.this,FilterSubscriptionActivity.class)));

        tabsItemsRegistered = findViewById(R.id.tabs_item_registered);
        viewPager = findViewById(R.id.view_pager);
        items = new ArrayList<>();

        items.add("الطلاب المسجلين");
        items.add("المعلمين المسجلين");


        tabsAdapter = new TabsRegisteredAdapter(RegisteredTeachersStudentsActivity.this);
        viewPager.setAdapter(tabsAdapter);
        new TabLayoutMediator(tabsItemsRegistered, viewPager, (tab, position) -> tab.setText(items.get(position))).attach();

    }

    public  void showCustomDialogSelected(){

        Dialog dialog = new Dialog(RegisteredTeachersStudentsActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_select);

        btRegisterStudent = dialog.findViewById(R.id.button_register_student);
        btRegisterTeacher = dialog.findViewById(R.id.button_register_teacher);

        btRegisterStudent.setOnClickListener(v -> {
            dialog.dismiss();
            startActivity(new Intent(RegisteredTeachersStudentsActivity.this,StudentDetailsActivity.class));

        });

        btRegisterTeacher.setOnClickListener(v -> {
            startActivity(new Intent(RegisteredTeachersStudentsActivity.this,TeacherDetailsActivity.class));
            dialog.dismiss(); });
        dialog.show();

    }

    public  void showCustomDialog(){

        Dialog dialog = new Dialog(RegisteredTeachersStudentsActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_log_out);

        btYes = dialog.findViewById(R.id.button_yes);
        btNo = dialog.findViewById(R.id.button_no);

        btYes.setOnClickListener(v -> {

            FirebaseAuth.getInstance().signOut();
            dialog.dismiss();
            startActivity(new Intent(RegisteredTeachersStudentsActivity.this, LoginActivity.class));
            finish();
        });

        btNo.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}