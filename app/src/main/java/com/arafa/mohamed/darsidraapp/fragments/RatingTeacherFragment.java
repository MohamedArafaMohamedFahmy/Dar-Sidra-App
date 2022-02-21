package com.arafa.mohamed.darsidraapp.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.adapter.MonthAdapter;
import com.arafa.mohamed.darsidraapp.models.CustomSpinner;
import com.arafa.mohamed.darsidraapp.models.RatingTeacherModel;
import com.arafa.mohamed.darsidraapp.models.SubscriptionModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Objects;

public class RatingTeacherFragment extends Fragment implements CustomSpinner.OnSpinnerEventsListener {

    TextInputEditText etNotesAttendanceDeparture, etScoreAttendanceDeparture, etNotesClassroomCleanTidy,
            etScoreClassroomCleanTidy, etNotesValueGame, etScoreValueGame, etNotesDealingAtmosphere,
            etScoreDealingAtmosphere, etNotesTimeManagement, etScoreTimeManagement, etTotal, etAchievements;
    FloatingActionButton btAddRatingTeacher;

    private String  notesAttendanceDeparture, scoreAttendanceDeparture, notesClassroomCleanTidy, scoreClassroomCleanTidy,
            notesValueGame, scoreValueGame, notesDealingAtmosphere, scoreDealingAtmosphere, notesTimeManagement,
            scoreTimeManagement, total, codeTeacher, achievements;

    DatabaseReference databaseReference;
    RatingTeacherModel ratingTeacherModel;
    CustomSpinner spinnerMonth;
    int indexMonth;
    MonthAdapter monthAdapter;
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
        etAchievements = viewRating.findViewById(R.id.editText_achievements);
        btAddRatingTeacher = viewRating.findViewById(R.id.button_add_rating_teacher);

        spinnerMonth = viewRating.findViewById(R.id.spinner_month);
        spinnerMonth.setSpinnerEventsListener(this);


        monthAdapter = new MonthAdapter(context, SubscriptionModel.getMonth());
        spinnerMonth.setAdapter(monthAdapter);
        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                indexMonth = spinnerMonth.getSelectedItemPosition();
                if (indexMonth == 0) {
                    databaseReference.child("RatingTeachers").child(codeTeacher).child("jan").addValueEventListener(new ValueEventListener() {
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
                                etAchievements.setText(ratingTeacherModel.getAchievements());

                            } else{
                                Objects.requireNonNull(etNotesAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etScoreAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etNotesValueGame.getText()).clear();
                                Objects.requireNonNull(etScoreValueGame.getText()).clear();
                                Objects.requireNonNull(etNotesDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etScoreDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etNotesTimeManagement.getText()).clear();
                                Objects.requireNonNull(etScoreTimeManagement.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etAchievements.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
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
                        achievements = Objects.requireNonNull(etAchievements.getText()).toString();

                        if( Integer.parseInt(scoreAttendanceDeparture) <= 10 && Integer.parseInt(scoreClassroomCleanTidy ) <= 10
                                && Integer.parseInt( scoreValueGame ) <=10 && Integer.parseInt( scoreDealingAtmosphere ) <=10
                                && Integer.parseInt(scoreTimeManagement) <=10){

                            ratingTeacherModel = new RatingTeacherModel(notesAttendanceDeparture, scoreAttendanceDeparture,
                                    notesClassroomCleanTidy, scoreClassroomCleanTidy, notesValueGame, scoreValueGame,
                                    notesDealingAtmosphere, scoreDealingAtmosphere, notesTimeManagement, scoreTimeManagement, total, achievements);

                            databaseReference.child("RatingTeachers").child(codeTeacher).child("jan").setValue(ratingTeacherModel).addOnCompleteListener(task -> {
                                if (task.isSuccessful()){
                                    Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }


                        if(Integer.parseInt(scoreAttendanceDeparture) > 10 ){
                            etScoreAttendanceDeparture.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt(scoreClassroomCleanTidy ) > 10 ){
                            etScoreClassroomCleanTidy.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt( scoreValueGame ) > 10 ){
                            etScoreValueGame.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if (Integer.parseInt( scoreDealingAtmosphere ) > 10 ){
                            etScoreDealingAtmosphere.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt(scoreTimeManagement) > 10 ){
                            etScoreTimeManagement.setError("تأكد من ادخالك القيمة المحددة");
                        }
                    });
                }

                if (indexMonth == 1) {
                    databaseReference.child("RatingTeachers").child(codeTeacher).child("feb").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

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
                                etAchievements.setText(ratingTeacherModel.getAchievements());

                            } else{
                                Objects.requireNonNull(etNotesAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etScoreAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etNotesValueGame.getText()).clear();
                                Objects.requireNonNull(etScoreValueGame.getText()).clear();
                                Objects.requireNonNull(etNotesDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etScoreDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etNotesTimeManagement.getText()).clear();
                                Objects.requireNonNull(etScoreTimeManagement.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etAchievements.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
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
                        achievements = Objects.requireNonNull(etAchievements.getText()).toString();

                        if( Integer.parseInt(scoreAttendanceDeparture) <= 10 && Integer.parseInt(scoreClassroomCleanTidy ) <= 10
                                && Integer.parseInt( scoreValueGame ) <=10 && Integer.parseInt( scoreDealingAtmosphere ) <=10
                                && Integer.parseInt(scoreTimeManagement) <=10){

                            ratingTeacherModel = new RatingTeacherModel(notesAttendanceDeparture, scoreAttendanceDeparture,
                                    notesClassroomCleanTidy, scoreClassroomCleanTidy, notesValueGame, scoreValueGame,
                                    notesDealingAtmosphere, scoreDealingAtmosphere, notesTimeManagement, scoreTimeManagement, total, achievements);

                            databaseReference.child("RatingTeachers").child(codeTeacher).child("feb").setValue(ratingTeacherModel).addOnCompleteListener(task -> {
                                if (task.isSuccessful()){
                                    Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }


                        if(Integer.parseInt(scoreAttendanceDeparture) > 10 ){
                            etScoreAttendanceDeparture.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt(scoreClassroomCleanTidy ) > 10 ){
                            etScoreClassroomCleanTidy.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt( scoreValueGame ) > 10 ){
                            etScoreValueGame.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if (Integer.parseInt( scoreDealingAtmosphere ) > 10 ){
                            etScoreDealingAtmosphere.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt(scoreTimeManagement) > 10 ){
                            etScoreTimeManagement.setError("تأكد من ادخالك القيمة المحددة");
                        }
                    });
                }

                if (indexMonth == 2) {
                    databaseReference.child("RatingTeachers").child(codeTeacher).child("mar").addValueEventListener(new ValueEventListener() {
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
                                etAchievements.setText(ratingTeacherModel.getAchievements());

                            } else{
                                Objects.requireNonNull(etNotesAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etScoreAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etNotesValueGame.getText()).clear();
                                Objects.requireNonNull(etScoreValueGame.getText()).clear();
                                Objects.requireNonNull(etNotesDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etScoreDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etNotesTimeManagement.getText()).clear();
                                Objects.requireNonNull(etScoreTimeManagement.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etAchievements.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
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
                        achievements = Objects.requireNonNull(etAchievements.getText()).toString();

                        if( Integer.parseInt(scoreAttendanceDeparture) <= 10 && Integer.parseInt(scoreClassroomCleanTidy ) <= 10
                                && Integer.parseInt( scoreValueGame ) <=10 && Integer.parseInt( scoreDealingAtmosphere ) <=10
                                && Integer.parseInt(scoreTimeManagement) <=10){

                            ratingTeacherModel = new RatingTeacherModel(notesAttendanceDeparture, scoreAttendanceDeparture,
                                    notesClassroomCleanTidy, scoreClassroomCleanTidy, notesValueGame, scoreValueGame,
                                    notesDealingAtmosphere, scoreDealingAtmosphere, notesTimeManagement, scoreTimeManagement, total, achievements);

                            databaseReference.child("RatingTeachers").child(codeTeacher).child("mar").setValue(ratingTeacherModel).addOnCompleteListener(task -> {
                                if (task.isSuccessful()){
                                    Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }


                        if(Integer.parseInt(scoreAttendanceDeparture) > 10 ){
                            etScoreAttendanceDeparture.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt(scoreClassroomCleanTidy ) > 10 ){
                            etScoreClassroomCleanTidy.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt( scoreValueGame ) > 10 ){
                            etScoreValueGame.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if (Integer.parseInt( scoreDealingAtmosphere ) > 10 ){
                            etScoreDealingAtmosphere.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt(scoreTimeManagement) > 10 ){
                            etScoreTimeManagement.setError("تأكد من ادخالك القيمة المحددة");
                        }
                    });
                }

                if (indexMonth == 3) {
                    databaseReference.child("RatingTeachers").child(codeTeacher).child("apr").addValueEventListener(new ValueEventListener() {
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
                                etAchievements.setText(ratingTeacherModel.getAchievements());

                            } else{
                                Objects.requireNonNull(etNotesAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etScoreAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etNotesValueGame.getText()).clear();
                                Objects.requireNonNull(etScoreValueGame.getText()).clear();
                                Objects.requireNonNull(etNotesDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etScoreDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etNotesTimeManagement.getText()).clear();
                                Objects.requireNonNull(etScoreTimeManagement.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etAchievements.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
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
                        achievements = Objects.requireNonNull(etAchievements.getText()).toString();

                        if( Integer.parseInt(scoreAttendanceDeparture) <= 10 && Integer.parseInt(scoreClassroomCleanTidy ) <= 10
                                && Integer.parseInt( scoreValueGame ) <=10 && Integer.parseInt( scoreDealingAtmosphere ) <=10
                                && Integer.parseInt(scoreTimeManagement) <=10){

                            ratingTeacherModel = new RatingTeacherModel(notesAttendanceDeparture, scoreAttendanceDeparture,
                                    notesClassroomCleanTidy, scoreClassroomCleanTidy, notesValueGame, scoreValueGame,
                                    notesDealingAtmosphere, scoreDealingAtmosphere, notesTimeManagement, scoreTimeManagement, total, achievements);

                            databaseReference.child("RatingTeachers").child(codeTeacher).child("apr").setValue(ratingTeacherModel).addOnCompleteListener(task -> {
                                if (task.isSuccessful()){
                                    Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        if(Integer.parseInt(scoreAttendanceDeparture) > 10 ){
                            etScoreAttendanceDeparture.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt(scoreClassroomCleanTidy ) > 10 ){
                            etScoreClassroomCleanTidy.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt( scoreValueGame ) > 10 ){
                            etScoreValueGame.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if (Integer.parseInt( scoreDealingAtmosphere ) > 10 ){
                            etScoreDealingAtmosphere.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt(scoreTimeManagement) > 10 ){
                            etScoreTimeManagement.setError("تأكد من ادخالك القيمة المحددة");
                        }
                    });
                }

                if (indexMonth == 4) {
                    databaseReference.child("RatingTeachers").child(codeTeacher).child("may").addValueEventListener(new ValueEventListener() {
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
                                etAchievements.setText(ratingTeacherModel.getAchievements());

                            } else{
                                Objects.requireNonNull(etNotesAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etScoreAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etNotesValueGame.getText()).clear();
                                Objects.requireNonNull(etScoreValueGame.getText()).clear();
                                Objects.requireNonNull(etNotesDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etScoreDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etNotesTimeManagement.getText()).clear();
                                Objects.requireNonNull(etScoreTimeManagement.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etAchievements.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
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
                        achievements = Objects.requireNonNull(etAchievements.getText()).toString();

                        if( Integer.parseInt(scoreAttendanceDeparture) <= 10 && Integer.parseInt(scoreClassroomCleanTidy ) <= 10
                                && Integer.parseInt( scoreValueGame ) <=10 && Integer.parseInt( scoreDealingAtmosphere ) <=10
                                && Integer.parseInt(scoreTimeManagement) <=10){

                            ratingTeacherModel = new RatingTeacherModel(notesAttendanceDeparture, scoreAttendanceDeparture,
                                    notesClassroomCleanTidy, scoreClassroomCleanTidy, notesValueGame, scoreValueGame,
                                    notesDealingAtmosphere, scoreDealingAtmosphere, notesTimeManagement, scoreTimeManagement, total, achievements);

                            databaseReference.child("RatingTeachers").child(codeTeacher).child("may").setValue(ratingTeacherModel).addOnCompleteListener(task -> {
                                if (task.isSuccessful()){
                                    Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }


                        if(Integer.parseInt(scoreAttendanceDeparture) > 10 ){
                            etScoreAttendanceDeparture.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt(scoreClassroomCleanTidy ) > 10 ){
                            etScoreClassroomCleanTidy.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt( scoreValueGame ) > 10 ){
                            etScoreValueGame.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if (Integer.parseInt( scoreDealingAtmosphere ) > 10 ){
                            etScoreDealingAtmosphere.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt(scoreTimeManagement) > 10 ){
                            etScoreTimeManagement.setError("تأكد من ادخالك القيمة المحددة");
                        }
                    });
                }

                if (indexMonth == 5) {
                    databaseReference.child("RatingTeachers").child(codeTeacher).child("jun").addValueEventListener(new ValueEventListener() {
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
                                etAchievements.setText(ratingTeacherModel.getAchievements());

                            } else{
                                Objects.requireNonNull(etNotesAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etScoreAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etNotesValueGame.getText()).clear();
                                Objects.requireNonNull(etScoreValueGame.getText()).clear();
                                Objects.requireNonNull(etNotesDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etScoreDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etNotesTimeManagement.getText()).clear();
                                Objects.requireNonNull(etScoreTimeManagement.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etAchievements.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
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
                        achievements = Objects.requireNonNull(etAchievements.getText()).toString();

                        if( Integer.parseInt(scoreAttendanceDeparture) <= 10 && Integer.parseInt(scoreClassroomCleanTidy ) <= 10
                                && Integer.parseInt( scoreValueGame ) <=10 && Integer.parseInt( scoreDealingAtmosphere ) <=10
                                && Integer.parseInt(scoreTimeManagement) <=10){

                            ratingTeacherModel = new RatingTeacherModel(notesAttendanceDeparture, scoreAttendanceDeparture,
                                    notesClassroomCleanTidy, scoreClassroomCleanTidy, notesValueGame, scoreValueGame,
                                    notesDealingAtmosphere, scoreDealingAtmosphere, notesTimeManagement, scoreTimeManagement, total, achievements);

                            databaseReference.child("RatingTeachers").child(codeTeacher).child("jun").setValue(ratingTeacherModel).addOnCompleteListener(task -> {
                                if (task.isSuccessful()){
                                    Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        if(Integer.parseInt(scoreAttendanceDeparture) > 10 ){
                            etScoreAttendanceDeparture.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt(scoreClassroomCleanTidy ) > 10 ){
                            etScoreClassroomCleanTidy.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt( scoreValueGame ) > 10 ){
                            etScoreValueGame.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if (Integer.parseInt( scoreDealingAtmosphere ) > 10 ){
                            etScoreDealingAtmosphere.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt(scoreTimeManagement) > 10 ){
                            etScoreTimeManagement.setError("تأكد من ادخالك القيمة المحددة");
                        }
                    });
                }

                if (indexMonth == 6) {
                    databaseReference.child("RatingTeachers").child(codeTeacher).child("jul").addValueEventListener(new ValueEventListener() {
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
                                etAchievements.setText(ratingTeacherModel.getAchievements());

                            } else{
                                Objects.requireNonNull(etNotesAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etScoreAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etNotesValueGame.getText()).clear();
                                Objects.requireNonNull(etScoreValueGame.getText()).clear();
                                Objects.requireNonNull(etNotesDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etScoreDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etNotesTimeManagement.getText()).clear();
                                Objects.requireNonNull(etScoreTimeManagement.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etAchievements.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
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
                        achievements = Objects.requireNonNull(etAchievements.getText()).toString();

                        if( Integer.parseInt(scoreAttendanceDeparture) <= 10 && Integer.parseInt(scoreClassroomCleanTidy ) <= 10
                                && Integer.parseInt( scoreValueGame ) <=10 && Integer.parseInt( scoreDealingAtmosphere ) <=10
                                && Integer.parseInt(scoreTimeManagement) <=10){

                            ratingTeacherModel = new RatingTeacherModel(notesAttendanceDeparture, scoreAttendanceDeparture,
                                    notesClassroomCleanTidy, scoreClassroomCleanTidy, notesValueGame, scoreValueGame,
                                    notesDealingAtmosphere, scoreDealingAtmosphere, notesTimeManagement, scoreTimeManagement, total, achievements);

                            databaseReference.child("RatingTeachers").child(codeTeacher).child("jul").setValue(ratingTeacherModel).addOnCompleteListener(task -> {
                                if (task.isSuccessful()){
                                    Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }


                        if(Integer.parseInt(scoreAttendanceDeparture) > 10 ){
                            etScoreAttendanceDeparture.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt(scoreClassroomCleanTidy ) > 10 ){
                            etScoreClassroomCleanTidy.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt( scoreValueGame ) > 10 ){
                            etScoreValueGame.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if (Integer.parseInt( scoreDealingAtmosphere ) > 10 ){
                            etScoreDealingAtmosphere.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt(scoreTimeManagement) > 10 ){
                            etScoreTimeManagement.setError("تأكد من ادخالك القيمة المحددة");
                        }
                    });
                }

                if (indexMonth == 7) {
                    databaseReference.child("RatingTeachers").child(codeTeacher).child("aug").addValueEventListener(new ValueEventListener() {
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
                                etAchievements.setText(ratingTeacherModel.getAchievements());

                            } else{
                                Objects.requireNonNull(etNotesAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etScoreAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etNotesValueGame.getText()).clear();
                                Objects.requireNonNull(etScoreValueGame.getText()).clear();
                                Objects.requireNonNull(etNotesDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etScoreDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etNotesTimeManagement.getText()).clear();
                                Objects.requireNonNull(etScoreTimeManagement.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etAchievements.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
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
                        achievements = Objects.requireNonNull(etAchievements.getText()).toString();

                        if( Integer.parseInt(scoreAttendanceDeparture) <= 10 && Integer.parseInt(scoreClassroomCleanTidy ) <= 10
                                && Integer.parseInt( scoreValueGame ) <=10 && Integer.parseInt( scoreDealingAtmosphere ) <=10
                                && Integer.parseInt(scoreTimeManagement) <=10){

                            ratingTeacherModel = new RatingTeacherModel(notesAttendanceDeparture, scoreAttendanceDeparture,
                                    notesClassroomCleanTidy, scoreClassroomCleanTidy, notesValueGame, scoreValueGame,
                                    notesDealingAtmosphere, scoreDealingAtmosphere, notesTimeManagement, scoreTimeManagement, total, achievements);

                            databaseReference.child("RatingTeachers").child(codeTeacher).child("aug").setValue(ratingTeacherModel).addOnCompleteListener(task -> {
                                if (task.isSuccessful()){
                                    Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }


                        if(Integer.parseInt(scoreAttendanceDeparture) > 10 ){
                            etScoreAttendanceDeparture.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt(scoreClassroomCleanTidy ) > 10 ){
                            etScoreClassroomCleanTidy.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt( scoreValueGame ) > 10 ){
                            etScoreValueGame.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if (Integer.parseInt( scoreDealingAtmosphere ) > 10 ){
                            etScoreDealingAtmosphere.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt(scoreTimeManagement) > 10 ){
                            etScoreTimeManagement.setError("تأكد من ادخالك القيمة المحددة");
                        }
                    });
                }

                if (indexMonth == 8) {
                    databaseReference.child("RatingTeachers").child(codeTeacher).child("sep").addValueEventListener(new ValueEventListener() {
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
                                etAchievements.setText(ratingTeacherModel.getAchievements());

                            } else{
                                Objects.requireNonNull(etNotesAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etScoreAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etNotesValueGame.getText()).clear();
                                Objects.requireNonNull(etScoreValueGame.getText()).clear();
                                Objects.requireNonNull(etNotesDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etScoreDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etNotesTimeManagement.getText()).clear();
                                Objects.requireNonNull(etScoreTimeManagement.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etAchievements.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
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
                        achievements = Objects.requireNonNull(etAchievements.getText()).toString();

                        if( Integer.parseInt(scoreAttendanceDeparture) <= 10 && Integer.parseInt(scoreClassroomCleanTidy ) <= 10
                                && Integer.parseInt( scoreValueGame ) <=10 && Integer.parseInt( scoreDealingAtmosphere ) <=10
                                && Integer.parseInt(scoreTimeManagement) <=10){

                            ratingTeacherModel = new RatingTeacherModel(notesAttendanceDeparture, scoreAttendanceDeparture,
                                    notesClassroomCleanTidy, scoreClassroomCleanTidy, notesValueGame, scoreValueGame,
                                    notesDealingAtmosphere, scoreDealingAtmosphere, notesTimeManagement, scoreTimeManagement, total, achievements);

                            databaseReference.child("RatingTeachers").child(codeTeacher).child("sep").setValue(ratingTeacherModel).addOnCompleteListener(task -> {
                                if (task.isSuccessful()){
                                    Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }


                        if(Integer.parseInt(scoreAttendanceDeparture) > 10 ){
                            etScoreAttendanceDeparture.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt(scoreClassroomCleanTidy ) > 10 ){
                            etScoreClassroomCleanTidy.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt( scoreValueGame ) > 10 ){
                            etScoreValueGame.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if (Integer.parseInt( scoreDealingAtmosphere ) > 10 ){
                            etScoreDealingAtmosphere.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt(scoreTimeManagement) > 10 ){
                            etScoreTimeManagement.setError("تأكد من ادخالك القيمة المحددة");
                        }
                    });
                }

                if (indexMonth == 9) {
                    databaseReference.child("RatingTeachers").child(codeTeacher).child("oct").addValueEventListener(new ValueEventListener() {
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
                                etAchievements.setText(ratingTeacherModel.getAchievements());

                            } else{
                                Objects.requireNonNull(etNotesAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etScoreAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etNotesValueGame.getText()).clear();
                                Objects.requireNonNull(etScoreValueGame.getText()).clear();
                                Objects.requireNonNull(etNotesDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etScoreDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etNotesTimeManagement.getText()).clear();
                                Objects.requireNonNull(etScoreTimeManagement.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etAchievements.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
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
                        achievements = Objects.requireNonNull(etAchievements.getText()).toString();

                        if( Integer.parseInt(scoreAttendanceDeparture) <= 10 && Integer.parseInt(scoreClassroomCleanTidy ) <= 10
                                && Integer.parseInt( scoreValueGame ) <=10 && Integer.parseInt( scoreDealingAtmosphere ) <=10
                                && Integer.parseInt(scoreTimeManagement) <=10){

                            ratingTeacherModel = new RatingTeacherModel(notesAttendanceDeparture, scoreAttendanceDeparture,
                                    notesClassroomCleanTidy, scoreClassroomCleanTidy, notesValueGame, scoreValueGame,
                                    notesDealingAtmosphere, scoreDealingAtmosphere, notesTimeManagement, scoreTimeManagement, total, achievements);

                            databaseReference.child("RatingTeachers").child(codeTeacher).child("oct").setValue(ratingTeacherModel).addOnCompleteListener(task -> {
                                if (task.isSuccessful()){
                                    Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }


                        if(Integer.parseInt(scoreAttendanceDeparture) > 10 ){
                            etScoreAttendanceDeparture.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt(scoreClassroomCleanTidy ) > 10 ){
                            etScoreClassroomCleanTidy.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt( scoreValueGame ) > 10 ){
                            etScoreValueGame.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if (Integer.parseInt( scoreDealingAtmosphere ) > 10 ){
                            etScoreDealingAtmosphere.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt(scoreTimeManagement) > 10 ){
                            etScoreTimeManagement.setError("تأكد من ادخالك القيمة المحددة");
                        }
                    });
                }

                if (indexMonth == 10) {
                    databaseReference.child("RatingTeachers").child(codeTeacher).child("nov").addValueEventListener(new ValueEventListener() {
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
                                etAchievements.setText(ratingTeacherModel.getAchievements());

                            } else{
                                Objects.requireNonNull(etNotesAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etScoreAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etNotesValueGame.getText()).clear();
                                Objects.requireNonNull(etScoreValueGame.getText()).clear();
                                Objects.requireNonNull(etNotesDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etScoreDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etNotesTimeManagement.getText()).clear();
                                Objects.requireNonNull(etScoreTimeManagement.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etAchievements.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
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
                        achievements = Objects.requireNonNull(etAchievements.getText()).toString();

                        if( Integer.parseInt(scoreAttendanceDeparture) <= 10 && Integer.parseInt(scoreClassroomCleanTidy ) <= 10
                                && Integer.parseInt( scoreValueGame ) <=10 && Integer.parseInt( scoreDealingAtmosphere ) <=10
                                && Integer.parseInt(scoreTimeManagement) <=10){

                            ratingTeacherModel = new RatingTeacherModel(notesAttendanceDeparture, scoreAttendanceDeparture,
                                    notesClassroomCleanTidy, scoreClassroomCleanTidy, notesValueGame, scoreValueGame,
                                    notesDealingAtmosphere, scoreDealingAtmosphere, notesTimeManagement, scoreTimeManagement, total, achievements);

                            databaseReference.child("RatingTeachers").child(codeTeacher).child("nov").setValue(ratingTeacherModel).addOnCompleteListener(task -> {
                                if (task.isSuccessful()){
                                    Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }


                        if(Integer.parseInt(scoreAttendanceDeparture) > 10 ){
                            etScoreAttendanceDeparture.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt(scoreClassroomCleanTidy ) > 10 ){
                            etScoreClassroomCleanTidy.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt( scoreValueGame ) > 10 ){
                            etScoreValueGame.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if (Integer.parseInt( scoreDealingAtmosphere ) > 10 ){
                            etScoreDealingAtmosphere.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt(scoreTimeManagement) > 10 ){
                            etScoreTimeManagement.setError("تأكد من ادخالك القيمة المحددة");
                        }
                    });
                }

                if (indexMonth == 11) {
                    databaseReference.child("RatingTeachers").child(codeTeacher).child("dec").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
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
                                etAchievements.setText(ratingTeacherModel.getAchievements());

                            } else{
                                Objects.requireNonNull(etNotesAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etScoreAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etNotesValueGame.getText()).clear();
                                Objects.requireNonNull(etScoreValueGame.getText()).clear();
                                Objects.requireNonNull(etNotesDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etScoreDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etNotesTimeManagement.getText()).clear();
                                Objects.requireNonNull(etScoreTimeManagement.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etAchievements.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(context, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
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
                        achievements = Objects.requireNonNull(etAchievements.getText()).toString();

                        if( Integer.parseInt(scoreAttendanceDeparture) <= 10 && Integer.parseInt(scoreClassroomCleanTidy ) <= 10
                                && Integer.parseInt( scoreValueGame ) <=10 && Integer.parseInt( scoreDealingAtmosphere ) <=10
                                && Integer.parseInt(scoreTimeManagement) <=10){

                            ratingTeacherModel = new RatingTeacherModel(notesAttendanceDeparture, scoreAttendanceDeparture,
                                    notesClassroomCleanTidy, scoreClassroomCleanTidy, notesValueGame, scoreValueGame,
                                    notesDealingAtmosphere, scoreDealingAtmosphere, notesTimeManagement, scoreTimeManagement, total, achievements);

                            databaseReference.child("RatingTeachers").child(codeTeacher).child("dec").setValue(ratingTeacherModel).addOnCompleteListener(task -> {
                                if (task.isSuccessful()){
                                    Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }


                        if(Integer.parseInt(scoreAttendanceDeparture) > 10 ){
                            etScoreAttendanceDeparture.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt(scoreClassroomCleanTidy ) > 10 ){
                            etScoreClassroomCleanTidy.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt( scoreValueGame ) > 10 ){
                            etScoreValueGame.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if (Integer.parseInt( scoreDealingAtmosphere ) > 10 ){
                            etScoreDealingAtmosphere.setError("تأكد من ادخالك القيمة المحددة");
                        }
                        if(Integer.parseInt(scoreTimeManagement) > 10 ){
                            etScoreTimeManagement.setError("تأكد من ادخالك القيمة المحددة");
                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        etScoreAttendanceDeparture.addTextChangedListener(textWatcher);
        etScoreClassroomCleanTidy.addTextChangedListener(textWatcher);
        etScoreValueGame.addTextChangedListener(textWatcher);
        etScoreDealingAtmosphere.addTextChangedListener(textWatcher);
        etScoreTimeManagement.addTextChangedListener(textWatcher);


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
            achievements = Objects.requireNonNull(etAchievements.getText()).toString();

            if( Integer.parseInt(scoreAttendanceDeparture) <= 10 && Integer.parseInt(scoreClassroomCleanTidy ) <= 10
                    && Integer.parseInt( scoreValueGame ) <=10 && Integer.parseInt( scoreDealingAtmosphere ) <=10
                    && Integer.parseInt(scoreTimeManagement) <=10){

                ratingTeacherModel = new RatingTeacherModel(notesAttendanceDeparture, scoreAttendanceDeparture,
                        notesClassroomCleanTidy, scoreClassroomCleanTidy, notesValueGame, scoreValueGame,
                        notesDealingAtmosphere, scoreDealingAtmosphere, notesTimeManagement, scoreTimeManagement, total, achievements);

                databaseReference.child("RatingTeachers").child(codeTeacher).setValue(ratingTeacherModel).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }


            if(Integer.parseInt(scoreAttendanceDeparture) > 10 ){
                etScoreAttendanceDeparture.setError("تأكد من ادخالك القيمة المحددة");
            }
            if(Integer.parseInt(scoreClassroomCleanTidy ) > 10 ){
                etScoreClassroomCleanTidy.setError("تأكد من ادخالك القيمة المحددة");
            }
            if(Integer.parseInt( scoreValueGame ) > 10 ){
                etScoreValueGame.setError("تأكد من ادخالك القيمة المحددة");
            }
            if (Integer.parseInt( scoreDealingAtmosphere ) > 10 ){
                etScoreDealingAtmosphere.setError("تأكد من ادخالك القيمة المحددة");
            }
            if(Integer.parseInt(scoreTimeManagement) > 10 ){
                etScoreTimeManagement.setError("تأكد من ادخالك القيمة المحددة");
            }
        });
        return viewRating;
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            if (!TextUtils.isEmpty(Objects.requireNonNull(etScoreAttendanceDeparture.getText()).toString().trim())
                    || !TextUtils.isEmpty(Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).toString().trim())
                    || !TextUtils.isEmpty(Objects.requireNonNull(etScoreDealingAtmosphere.getText()).toString().trim())
                    || !TextUtils.isEmpty(Objects.requireNonNull(etScoreTimeManagement.getText()).toString().trim())
                    || !TextUtils.isEmpty(Objects.requireNonNull(etScoreValueGame.getText()).toString().trim()))
            {
                int firstValue = TextUtils.isEmpty(Objects.requireNonNull(etScoreAttendanceDeparture.getText()).toString().trim()) ? 0 : Integer.parseInt(etScoreAttendanceDeparture.getText().toString().trim());
                int secondValue = TextUtils.isEmpty(Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).toString().trim()) ? 0 : Integer.parseInt(etScoreClassroomCleanTidy.getText().toString().trim());
                int thirdValue = TextUtils.isEmpty(Objects.requireNonNull(etScoreTimeManagement.getText()).toString().trim()) ? 0 : Integer.parseInt(etScoreTimeManagement.getText().toString().trim());
                int forthValue = TextUtils.isEmpty(Objects.requireNonNull(etScoreDealingAtmosphere.getText()).toString().trim()) ? 0 : Integer.parseInt(etScoreDealingAtmosphere.getText().toString().trim());
                int fifthValue = TextUtils.isEmpty(Objects.requireNonNull(etScoreValueGame.getText()).toString().trim()) ? 0 : Integer.parseInt(etScoreValueGame.getText().toString().trim());

                int answer = firstValue + secondValue + thirdValue + forthValue + fifthValue;

                etTotal.setText(String.valueOf(answer));
            }else {
                etTotal.setText("");
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    public void onPopupWindowOpened(AppCompatSpinner spinner) {
        spinnerMonth.setBackground(AppCompatResources.getDrawable(context,R.drawable.bg_spinner_month_up));
    }

    @Override
    public void onPopupWindowClosed(AppCompatSpinner spinner) {
        spinnerMonth.setBackground(AppCompatResources.getDrawable(context,R.drawable.bg_spinner_month));
    }
}