package com.arafa.mohamed.darsidraapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.adapter.StudentsAdapter;
import com.arafa.mohamed.darsidraapp.models.StudentModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RegisteredStudentsActivity extends AppCompatActivity {

    AppCompatTextView tvToolbar;
    AppCompatImageButton btBackArrow,btLogout,btStudentDetails;
    AppCompatButton btYes,btNo;
    DatabaseReference databaseReference;
    StudentModel studentModel;
    StudentsAdapter studentsAdapter;
    ArrayList <StudentModel> listStudent;
    RecyclerView recStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_students);

        btBackArrow = findViewById(R.id.button_back_arrow);
        btLogout = findViewById(R.id.button_log_out);
        btStudentDetails = findViewById(R.id.button_student_details);
        tvToolbar = findViewById(R.id.text_toolbar);
        recStudents = findViewById(R.id.rec_student);
        listStudent = new ArrayList<>();

        tvToolbar.setText(R.string.registered_student_appbar);
        btBackArrow.setVisibility(View.GONE);
        btBackArrow.setOnClickListener(v -> finish());
        btLogout.setVisibility(View.VISIBLE);
        btStudentDetails.setVisibility(View.VISIBLE);

        btLogout.setOnClickListener(v -> {
            showCustomDialog();
        });

        btStudentDetails.setOnClickListener(v -> {
            startActivity(new Intent(RegisteredStudentsActivity.this,StudentDetailsActivity.class));
        });

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("StudentsData").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listStudent.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    studentModel = postSnapshot.getValue(StudentModel.class);
                    listStudent.add(studentModel);
                }
                if(!listStudent.isEmpty()){
                    studentsAdapter = new StudentsAdapter(RegisteredStudentsActivity.this,listStudent);
                    recStudents.setAdapter(studentsAdapter);
                    recStudents.setLayoutManager(new LinearLayoutManager(RegisteredStudentsActivity.this));
                }else {
                    Toast.makeText(RegisteredStudentsActivity.this, "لا يوجد طلبه مسجلين", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });

    }

    public  void showCustomDialog(){

        Dialog dialog = new Dialog(RegisteredStudentsActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_log_out);

        btYes = dialog.findViewById(R.id.button_yes);
        btNo = dialog.findViewById(R.id.button_no);

        btYes.setOnClickListener(v -> {

            FirebaseAuth.getInstance().signOut();
            dialog.dismiss();
            startActivity(new Intent(RegisteredStudentsActivity.this, LoginActivity.class));
            finish();
        });

        btNo.setOnClickListener(v -> dialog.dismiss());
        dialog.show();

    }
}