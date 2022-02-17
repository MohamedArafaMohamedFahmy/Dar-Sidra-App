package com.arafa.mohamed.darsidraapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.adapter.AdminsAdapter;
import com.arafa.mohamed.darsidraapp.models.AdminModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class AddAdminActivity extends AppCompatActivity {

    TextInputEditText etEmailAdmin, etNameAdmin;
    AppCompatButton btAddAdmin;
    AppCompatImageButton btBackArrow;
    AppCompatTextView tvToolbar,tvMessage;
    DatabaseReference databaseReference;
    AdminModel adminData, retrieveAdminData;
    ArrayList<AdminModel> retrieveAdmins;
    AdminsAdapter adminsAdapter;
    RecyclerView recyclerViewAdmins;
    String idAdmin, nameAdmin, emailAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);

        etNameAdmin = findViewById(R.id.editText_name_admin);
        etEmailAdmin = findViewById(R.id.editText_email_admin);
        btAddAdmin = findViewById(R.id.button_add_admin);
        recyclerViewAdmins = findViewById(R.id.recyclerView_admins);
        tvMessage = findViewById(R.id.text_message_not_data_admin);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        retrieveAdmins = new ArrayList<>();

        btBackArrow = findViewById(R.id.button_back_arrow);
        tvToolbar = findViewById(R.id.text_toolbar);

        tvToolbar.setText(R.string.add_admin_appbar);
        btBackArrow.setOnClickListener(v -> finish());


        btAddAdmin.setOnClickListener(v -> {
            nameAdmin = Objects.requireNonNull(etNameAdmin.getText()).toString();
            emailAdmin = Objects.requireNonNull(etEmailAdmin.getText()).toString().toLowerCase();

            if (!nameAdmin.isEmpty() && !emailAdmin.isEmpty()) {
                idAdmin = databaseReference.push().getKey();
                adminData = new AdminModel(nameAdmin, emailAdmin, idAdmin);
                databaseReference.child("AdminsData").child(idAdmin).setValue(adminData).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AddAdminActivity.this, "تم الاضافه بنجاح", Toast.LENGTH_SHORT).show();
                            etNameAdmin.getText().clear();
                            etEmailAdmin.getText().clear();
                        }
                    }
                });

            }

            if (nameAdmin.isEmpty()) {
                etNameAdmin.setError("من فضلك ادخل اسم المشرف");
            }
            if (emailAdmin.isEmpty()) {
                etEmailAdmin.setError("من فضلك ادخل البريد الإلكتروني للمشرف");
            }
        });

        databaseReference.child("AdminsData").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 retrieveAdmins.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    retrieveAdminData = postSnapshot.getValue(AdminModel.class);
                    retrieveAdmins.add(retrieveAdminData);
                }

                if (!retrieveAdmins.isEmpty()) {
                    adminsAdapter = new AdminsAdapter(AddAdminActivity.this, retrieveAdmins);
                    adminsAdapter.notifyDataSetChanged();
                    tvMessage.setVisibility(View.GONE);
                    recyclerViewAdmins.setAdapter(adminsAdapter);
                    recyclerViewAdmins.setLayoutManager(new LinearLayoutManager(AddAdminActivity.this));

                }
                if (retrieveAdmins.isEmpty()) {
                    tvMessage.setVisibility(View.VISIBLE);
                    tvMessage.setText(R.string.message_not_data_admin);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}