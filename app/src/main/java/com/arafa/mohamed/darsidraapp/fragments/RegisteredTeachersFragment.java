package com.arafa.mohamed.darsidraapp.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.adapter.TeachersAdapter;
import com.arafa.mohamed.darsidraapp.models.TeachersModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public class RegisteredTeachersFragment extends Fragment {

    DatabaseReference databaseReference;
    TeachersModel teachersModel;
    TeachersAdapter teachersAdapter;
    ArrayList<TeachersModel> listTeacher;
    RecyclerView recTeacher;
    SearchView searchTeacher;
    LinearLayout linearProgressBar;
    AppCompatTextView tvMessage;
    Context context;

    public RegisteredTeachersFragment() {

    }

    public RegisteredTeachersFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View viewStudents = inflater.inflate(R.layout.fragment_registered_teachers, container, false);

        recTeacher = viewStudents.findViewById(R.id.rec_teacher);
        searchTeacher = viewStudents.findViewById(R.id.search_teacher);
        linearProgressBar = viewStudents.findViewById(R.id.linear_progress_bar);
        tvMessage = viewStudents.findViewById(R.id.text_message);
        listTeacher = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        linearProgressBar.setVisibility(View.VISIBLE);

        databaseReference.child("TeachersData").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listTeacher.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    teachersModel = postSnapshot.getValue(TeachersModel.class);
                    listTeacher.add(teachersModel);
                }
                if(!listTeacher.isEmpty()){

                    teachersAdapter = new TeachersAdapter(getActivity(),listTeacher);
                    teachersAdapter.notifyDataSetChanged();
                    recTeacher.setAdapter(teachersAdapter);
                    recTeacher.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recTeacher.setVisibility(View.VISIBLE);
                    tvMessage.setVisibility(View.GONE);
                    linearProgressBar.setVisibility(View.GONE);

                } else {
                    linearProgressBar.setVisibility(View.GONE);
                    recTeacher.setVisibility(View.GONE);
                    tvMessage.setVisibility(View.VISIBLE);
                    tvMessage.setText(R.string.message_not_data_teacher);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        searchTeacher.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchTeacher.setQueryHint(getString(R.string.search_code_teacher));
        searchTeacher.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (teachersAdapter != null) {
                    teachersAdapter.notifyDataSetChanged();
                    Filter filter = teachersAdapter.getFilter();
                    if (filter != null){
                        filter.filter(query);
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (teachersAdapter != null) {
                    teachersAdapter.notifyDataSetChanged();
                    Filter filter = teachersAdapter.getFilter();
                    if (filter != null){
                        filter.filter(newText);
                    }
                }

                return false;
            }
        });

        return viewStudents;
    }
}