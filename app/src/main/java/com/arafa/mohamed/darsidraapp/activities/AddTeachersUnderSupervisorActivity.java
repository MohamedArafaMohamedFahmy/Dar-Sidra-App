package com.arafa.mohamed.darsidraapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.adapter.SupervisorTeacherAdapter;
import com.arafa.mohamed.darsidraapp.models.AdminModel;
import com.arafa.mohamed.darsidraapp.models.TeachersModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Objects;

public class AddTeachersUnderSupervisorActivity extends AppCompatActivity {

    TextInputEditText etNameTeacher, etCodeTeacher;
    AppCompatButton btAddTeacher;
    AppCompatImageButton btBackArrow;
    AppCompatTextView tvToolbar,tvMessage;
    DatabaseReference databaseReference;
    TeachersModel  teacherData, retrieveTeacherData;
    AdminModel retrieveDataAdmin;
    ArrayList<TeachersModel> retrieveTeacher;
    SupervisorTeacherAdapter supervisorTeacherAdapter;
    RecyclerView recyclerViewTeachers;
    String nameTeacher, codeTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teachers_under_supervisor);

        etNameTeacher = findViewById(R.id.editText_name_teacher);
        etCodeTeacher = findViewById(R.id.editText_code_teacher);
        btAddTeacher = findViewById(R.id.button_add_teacher);
        recyclerViewTeachers = findViewById(R.id.recyclerView_supervisor_teachers);
        tvMessage = findViewById(R.id.text_message_not_data_teacher);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        retrieveTeacher = new ArrayList<>();
        btBackArrow = findViewById(R.id.button_back_arrow);
        tvToolbar = findViewById(R.id.text_toolbar);


        final Object objDetailed = getIntent().getSerializableExtra("adminData");
        retrieveDataAdmin = (AdminModel) objDetailed;

        tvToolbar.setText(R.string.add_supervisor_appbar);
        btBackArrow.setOnClickListener(v -> finish());


        btAddTeacher.setOnClickListener(v -> {

            nameTeacher = Objects.requireNonNull(etNameTeacher.getText()).toString();
            codeTeacher = Objects.requireNonNull(etCodeTeacher.getText()).toString();

            if (!nameTeacher.isEmpty() && !codeTeacher.isEmpty()) {

                teacherData = new TeachersModel(nameTeacher, codeTeacher, retrieveDataAdmin.getEmailAdmin());
                databaseReference.child("SupervisorTeacher").child(retrieveDataAdmin.getIdAdmin()).child(codeTeacher).setValue(teacherData).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(AddTeachersUnderSupervisorActivity.this, "تم الاضافه بنجاح", Toast.LENGTH_SHORT).show();
                        etNameTeacher.getText().clear();
                        etCodeTeacher.getText().clear();
                    }
                });

            }

            if (nameTeacher.isEmpty()) {
                etNameTeacher.setError("من فضلك ادخل اسم المعلم / المعلمة");
            }
            if (codeTeacher.isEmpty()) {
                etCodeTeacher.setError("من فضلك أدخل كود المعلم / المعلمة");
            }
        });

        databaseReference.child("SupervisorTeacher").child(retrieveDataAdmin.getIdAdmin()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                retrieveTeacher.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    retrieveTeacherData = postSnapshot.getValue(TeachersModel.class);
                    retrieveTeacher.add(retrieveTeacherData);
                }

                if (!retrieveTeacher.isEmpty()) {
                    supervisorTeacherAdapter = new SupervisorTeacherAdapter(AddTeachersUnderSupervisorActivity.this, retrieveTeacher);
                    supervisorTeacherAdapter.notifyDataSetChanged();
                    tvMessage.setVisibility(View.GONE);
                    recyclerViewTeachers.setAdapter(supervisorTeacherAdapter);
                    recyclerViewTeachers.setLayoutManager(new LinearLayoutManager(AddTeachersUnderSupervisorActivity.this));

                }
                if (retrieveTeacher.isEmpty()) {
                    tvMessage.setVisibility(View.VISIBLE);
                    tvMessage.setText(R.string.message_not_data_supervisor_teachers);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddTeachersUnderSupervisorActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}