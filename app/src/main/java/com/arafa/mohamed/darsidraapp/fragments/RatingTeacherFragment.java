package com.arafa.mohamed.darsidraapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.models.RatingTeacherModel;
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

public class RatingTeacherFragment extends Fragment {

    TextInputEditText etNotesAttendanceDeparture, etScoreAttendanceDeparture, etNotesClassroomCleanTidy,
            etScoreClassroomCleanTidy, etNotesValueGame, etScoreValueGame, etNotesDealingAtmosphere,
            etScoreDealingAtmosphere, etNotesTimeManagement, etScoreTimeManagement, etTotal;
    FloatingActionButton btAddRatingTeacher;

    private String  notesAttendanceDeparture, scoreAttendanceDeparture, notesClassroomCleanTidy, scoreClassroomCleanTidy,
            notesValueGame, scoreValueGame, notesDealingAtmosphere, scoreDealingAtmosphere, notesTimeManagement,
            scoreTimeManagement, total, codeTeacher;

    DatabaseReference databaseReference;
    RatingTeacherModel ratingTeacherModel;
    Context context;

    public RatingTeacherFragment() {
        // Required empty public constructor
    }

    public RatingTeacherFragment(String codeTeacher, Context context) {
        this.codeTeacher = codeTeacher;
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        databaseReference = FirebaseDatabase.getInstance().getReference();
        View viewRating =  inflater.inflate(R.layout.fragment_rating_teacher, container, false);
        etNotesAttendanceDeparture = viewRating.findViewById(R.id.editText_notes_attendance_and_departure);
        etScoreAttendanceDeparture = viewRating.findViewById(R.id.editText_score_attendance_and_departure);
        etNotesClassroomCleanTidy = viewRating.findViewById(R.id.editText_notes_cleanliness_order_classroom);
        etScoreClassroomCleanTidy = viewRating.findViewById(R.id.editText_score_cleanliness_order_classroom);
        etNotesValueGame = viewRating.findViewById(R.id.editText_notes_value_game_material);
        etScoreValueGame = viewRating.findViewById(R.id.editText_score_value_game_material);
        etNotesDealingAtmosphere = viewRating.findViewById(R.id.editText_notes_dealing_with_students);
        etScoreDealingAtmosphere = viewRating.findViewById(R.id.editText_score_dealing_with_students);
        etNotesTimeManagement = viewRating.findViewById(R.id.editText_notes_time_management);
        etScoreTimeManagement = viewRating.findViewById(R.id.editText_score_time_management);
        etTotal = viewRating.findViewById(R.id.editText_score_total);
        btAddRatingTeacher = viewRating.findViewById(R.id.button_add_rating_teacher);

        databaseReference.child("RatingTeachers").child(codeTeacher).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                ratingTeacherModel = snapshot.getValue(RatingTeacherModel.class);
                if (ratingTeacherModel != null){
                    etNotesAttendanceDeparture.setText(ratingTeacherModel.getNotesAttendanceDeparture());
                    etScoreAttendanceDeparture.setText(ratingTeacherModel.getScoreAttendanceDeparture());
                    etNotesClassroomCleanTidy.setText(ratingTeacherModel.getNotesClassroomCleanTidy());
                    etScoreClassroomCleanTidy.setText(ratingTeacherModel.getScoreClassroomCleanTidy());
                    etNotesValueGame.setText(ratingTeacherModel.getNotesValueGame());
                    etScoreValueGame.setText(ratingTeacherModel.getScoreValueGame());
                    etNotesDealingAtmosphere.setText(ratingTeacherModel.getNotesDealingAtmosphere());
                    etScoreDealingAtmosphere.setText(ratingTeacherModel.getScoreDealingAtmosphere());
                    etNotesTimeManagement.setText(ratingTeacherModel.getNotesTimeManagement());
                    etScoreTimeManagement.setText(ratingTeacherModel.getScoreTimeManagement());
                    etTotal.setText(ratingTeacherModel.getTotal());
                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btAddRatingTeacher.setOnClickListener(v -> {
            notesAttendanceDeparture = Objects.requireNonNull(etNotesAttendanceDeparture.getText()).toString();
            scoreAttendanceDeparture = Objects.requireNonNull(etScoreAttendanceDeparture.getText()).toString();
            notesClassroomCleanTidy = Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).toString();
            scoreClassroomCleanTidy = Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).toString();
            notesValueGame = Objects.requireNonNull(etNotesValueGame.getText()).toString();
            scoreValueGame = Objects.requireNonNull(etScoreValueGame.getText()).toString();
            notesDealingAtmosphere = Objects.requireNonNull(etNotesDealingAtmosphere.getText()).toString();
            scoreDealingAtmosphere = Objects.requireNonNull(etScoreDealingAtmosphere.getText()).toString();
            notesTimeManagement = Objects.requireNonNull(etNotesTimeManagement.getText()).toString();
            scoreTimeManagement = Objects.requireNonNull(etScoreTimeManagement.getText()).toString();
            total = Objects.requireNonNull(etTotal.getText()).toString();

            ratingTeacherModel = new RatingTeacherModel(notesAttendanceDeparture, scoreAttendanceDeparture,
                    notesClassroomCleanTidy, scoreClassroomCleanTidy, notesValueGame, scoreValueGame,
                    notesDealingAtmosphere, scoreDealingAtmosphere, notesTimeManagement, scoreTimeManagement, total);

            databaseReference.child("RatingTeachers").child(codeTeacher).setValue(ratingTeacherModel).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }

            });
        });


        return viewRating;
    }
}