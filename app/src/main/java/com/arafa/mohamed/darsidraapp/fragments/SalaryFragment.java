package com.arafa.mohamed.darsidraapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.models.RatingModel;
import com.arafa.mohamed.darsidraapp.models.SalaryTeacherModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class SalaryFragment extends Fragment {
    TextInputEditText etDaysIncrease, etDaysAbsence, etTotalSalary;
    AppCompatTextView tvCodeTeacher, tvNameTeacher;
    FloatingActionButton btAddSalaryTeacher;
    String daysIncrease, daysAbsence, totalSalary,codeTeacher,nameTeacher;
    DatabaseReference databaseReference;
    SalaryTeacherModel salaryTeacherModel;
    Context context;


    public SalaryFragment() {
        // Required empty public constructor
    }
    public SalaryFragment(String codeTeacher, String nameTeacher, Context context) {
        this.codeTeacher = codeTeacher;
        this.nameTeacher = nameTeacher;
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
         View viewSalary =  inflater.inflate(R.layout.fragment_salary, container, false);

         etDaysIncrease = viewSalary.findViewById(R.id.editText_number_days_increase);
         etDaysAbsence = viewSalary.findViewById(R.id.editText_number_days_of_absence);
         etTotalSalary = viewSalary.findViewById(R.id.editText_salary);
         btAddSalaryTeacher = viewSalary.findViewById(R.id.button_add_salary);
         tvCodeTeacher = viewSalary.findViewById(R.id.text_code_teacher);
         tvNameTeacher = viewSalary.findViewById(R.id.text_name_teacher);

        tvCodeTeacher.setText(codeTeacher);
        tvNameTeacher.setText(nameTeacher);

         databaseReference.child("SalaryTeachers").child(codeTeacher).addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 salaryTeacherModel = snapshot.getValue(SalaryTeacherModel.class);
                 if (salaryTeacherModel != null) {

                     etDaysIncrease.setText(salaryTeacherModel.getDaysIncrease());
                     etDaysAbsence.setText(salaryTeacherModel.getDaysAbsence());
                     etTotalSalary.setText(salaryTeacherModel.getTotalSalary());
                 }
             }

             @Override
             public void onCancelled(@NonNull  DatabaseError error) {
                 Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
             }
         });

         btAddSalaryTeacher.setOnClickListener(v -> {

             daysIncrease = Objects.requireNonNull(etDaysIncrease.getText()).toString();
             daysAbsence = Objects.requireNonNull(etDaysAbsence.getText()).toString();
             totalSalary = Objects.requireNonNull(etTotalSalary.getText()).toString();

                  salaryTeacherModel = new SalaryTeacherModel(daysIncrease, daysAbsence, totalSalary);
                  databaseReference.child("SalaryTeachers").child(codeTeacher).setValue(salaryTeacherModel).addOnCompleteListener(task -> {
                      if (task.isSuccessful()){
                          Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                      }else{
                          Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                      }
                  });

         });
         return viewSalary;

    }
}