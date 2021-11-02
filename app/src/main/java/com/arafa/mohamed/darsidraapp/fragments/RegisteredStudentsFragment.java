package com.arafa.mohamed.darsidraapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.adapter.StudentsAdapter;
import com.arafa.mohamed.darsidraapp.models.StudentModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class RegisteredStudentsFragment extends Fragment {

    DatabaseReference databaseReference;
    StudentModel studentModel;
    StudentsAdapter studentsAdapter;
    ArrayList<StudentModel> listStudent;
    RecyclerView recStudents;
    SearchView searchStudent;
    LinearLayout linearProgressBar;


    public RegisteredStudentsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View viewStudents = inflater.inflate(R.layout.fragment_registered_students, container, false);

        recStudents = viewStudents.findViewById(R.id.rec_student);
        searchStudent = viewStudents.findViewById(R.id.search_student);
        linearProgressBar = viewStudents.findViewById(R.id.linear_progress_bar);
        listStudent = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        linearProgressBar.setVisibility(View.VISIBLE);

        databaseReference.child("StudentsData").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listStudent.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    studentModel = postSnapshot.getValue(StudentModel.class);
                    listStudent.add(studentModel);
                }
                if(!listStudent.isEmpty()){
                    studentsAdapter = new StudentsAdapter(getActivity(),listStudent);
                    studentsAdapter.notifyDataSetChanged();
                    recStudents.setAdapter(studentsAdapter);
                    recStudents.setLayoutManager(new LinearLayoutManager(getActivity()));
                    linearProgressBar.setVisibility(View.GONE);

                }else {
                    studentsAdapter.notifyDataSetChanged();
                    linearProgressBar.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "لا يوجد طلبه مسجلين", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        searchStudent.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchStudent.setQueryHint(getString(R.string.search_code_student));
        searchStudent.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.isEmpty()) {
                    studentsAdapter.getFilter().filter(newText);
                    studentsAdapter.notifyDataSetChanged();
                }else{
                    studentsAdapter.getFilter().filter(newText);
                    studentsAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });

        return viewStudents;
    }
}