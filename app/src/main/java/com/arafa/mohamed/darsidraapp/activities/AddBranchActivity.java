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
import com.arafa.mohamed.darsidraapp.adapter.BranchesAdapter;
import com.arafa.mohamed.darsidraapp.adapter.StudentsAdapter;
import com.arafa.mohamed.darsidraapp.models.BranchModel;
import com.arafa.mohamed.darsidraapp.models.StudentModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class AddBranchActivity extends AppCompatActivity {

    TextInputEditText etBranchName;
    AppCompatButton btAddBranch;
    AppCompatTextView tvToolbar;
    AppCompatImageButton btBackArrow;
    RecyclerView recBranches;
    DatabaseReference databaseReference;
    String nameBranch, idBranch;
    BranchModel branchModel, retrieveBranches;
    BranchesAdapter branchesAdapter;
    ArrayList<BranchModel> listBranches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_branch);

        etBranchName = findViewById(R.id.editText_name_branch);
        btAddBranch = findViewById(R.id.button_add_branch);
        recBranches = findViewById(R.id.rec_branches);
        btBackArrow = findViewById(R.id.button_back_arrow);
        tvToolbar = findViewById(R.id.text_toolbar);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        listBranches = new ArrayList<>();

        tvToolbar.setText(R.string.add_branch_appbar);
        btBackArrow.setOnClickListener(v -> finish());

        databaseReference.child("Branches").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listBranches.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    retrieveBranches = postSnapshot.getValue(BranchModel.class);
                    listBranches.add(retrieveBranches);
                }
                if(!listBranches.isEmpty()){
                    branchesAdapter = new BranchesAdapter(AddBranchActivity.this,listBranches);
                    branchesAdapter.notifyDataSetChanged();
                    recBranches.setAdapter(branchesAdapter);
                    recBranches.setLayoutManager(new LinearLayoutManager(AddBranchActivity.this));


                }else {
                    Toast.makeText(AddBranchActivity.this, "لا يوجد فروع حاليا", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddBranchActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btAddBranch.setOnClickListener(v -> {

            nameBranch = Objects.requireNonNull(etBranchName.getText()).toString();
            if(!nameBranch.isEmpty()){
                idBranch = databaseReference.push().getKey();
                branchModel = new BranchModel(nameBranch, idBranch);
                databaseReference.child("Branches").child(idBranch).setValue(branchModel).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        Toast.makeText(this, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            if (nameBranch.isEmpty()){
                etBranchName.setError("من فضلك ادخل اسم الفرع");
            }
        });
    }
}