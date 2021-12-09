package com.arafa.mohamed.darsidraapp.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.adapter.MonthAdapter;
import com.arafa.mohamed.darsidraapp.models.CustomSpinner;
import com.arafa.mohamed.darsidraapp.models.RatingModel;
import com.arafa.mohamed.darsidraapp.models.SubscriptionModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Objects;


public class RatingDetailsFragment extends Fragment implements CustomSpinner.OnSpinnerEventsListener {
    TextInputEditText etReview, etPreservation, etAudience, etAbsence, etTotal, etNotes;
    FloatingActionButton btAddRating;
    String codeStudent,review, preservation, audience, absence,total, notes;
    DatabaseReference databaseReference;
    RatingModel ratingModel;
    CustomSpinner spinnerMonth;
    int indexMonth;
    MonthAdapter monthAdapter;
    Context context;

    public RatingDetailsFragment() {

    }

    public RatingDetailsFragment(String codeStudent, Context context) {
        this.codeStudent = codeStudent;
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        databaseReference = FirebaseDatabase.getInstance().getReference();
        View  viewRating = inflater.inflate(R.layout.fragment_rating_details, container, false);
        etReview = viewRating.findViewById(R.id.editText_review);
        etPreservation = viewRating.findViewById(R.id.editText_preservation);
        etAudience = viewRating.findViewById(R.id.editText_audience);
        etAbsence = viewRating.findViewById(R.id.editText_absence);
        etTotal = viewRating.findViewById(R.id.editText_total);
        etNotes = viewRating.findViewById(R.id.editText_notes);
        btAddRating = viewRating.findViewById(R.id.button_add_rating);
        spinnerMonth = viewRating.findViewById(R.id.spinner_month);
        spinnerMonth.setSpinnerEventsListener(this);


        monthAdapter = new MonthAdapter(context, SubscriptionModel.getMonth());
        spinnerMonth.setAdapter(monthAdapter);
        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                indexMonth = spinnerMonth.getSelectedItemPosition();
                if (indexMonth == 0) {
                    databaseReference.child("Rating").child(codeStudent).child("jan").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {

                            ratingModel = snapshot.getValue(RatingModel.class);

                            if (ratingModel != null){
                                etReview.setText(ratingModel.getReview());
                                etPreservation.setText(ratingModel.getPreservation());
                                etAudience.setText(ratingModel.getAudience());
                                etAbsence.setText(ratingModel.getAbsence());
                                etTotal.setText(ratingModel.getTotal());
                                etNotes.setText(ratingModel.getNotes());

                            }else{
                                Objects.requireNonNull(etReview.getText()).clear();
                                Objects.requireNonNull(etPreservation.getText()).clear();
                                Objects.requireNonNull(etAudience.getText()).clear();
                                Objects.requireNonNull(etAbsence.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etNotes.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }

                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    btAddRating.setOnClickListener(v -> {
                        review = Objects.requireNonNull(etReview.getText()).toString();
                        preservation = Objects.requireNonNull(etPreservation.getText()).toString();
                        audience = Objects.requireNonNull(etAudience.getText()).toString();
                        absence = Objects.requireNonNull(etAbsence.getText()).toString();
                        total = Objects.requireNonNull(etTotal.getText()).toString();
                        notes = Objects.requireNonNull(etNotes.getText()).toString();

                        ratingModel = new RatingModel(review, preservation, audience, absence, total, notes);
                        databaseReference.child("Rating").child(codeStudent).child("jan").setValue(ratingModel).addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    });
                }

                if (indexMonth == 1) {
                    databaseReference.child("Rating").child(codeStudent).child("feb").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            ratingModel = snapshot.getValue(RatingModel.class);

                            if (ratingModel != null){
                                etReview.setText(ratingModel.getReview());
                                etPreservation.setText(ratingModel.getPreservation());
                                etAudience.setText(ratingModel.getAudience());
                                etAbsence.setText(ratingModel.getAbsence());
                                etTotal.setText(ratingModel.getTotal());
                                etNotes.setText(ratingModel.getNotes());

                            }else{
                                Objects.requireNonNull(etReview.getText()).clear();
                                Objects.requireNonNull(etPreservation.getText()).clear();
                                Objects.requireNonNull(etAudience.getText()).clear();
                                Objects.requireNonNull(etAbsence.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etNotes.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    btAddRating.setOnClickListener(v -> {
                        review = Objects.requireNonNull(etReview.getText()).toString();
                        preservation = Objects.requireNonNull(etPreservation.getText()).toString();
                        audience = Objects.requireNonNull(etAudience.getText()).toString();
                        absence = Objects.requireNonNull(etAbsence.getText()).toString();
                        total = Objects.requireNonNull(etTotal.getText()).toString();
                        notes = Objects.requireNonNull(etNotes.getText()).toString();

                        ratingModel = new RatingModel(review, preservation, audience, absence, total, notes);
                        databaseReference.child("Rating").child(codeStudent).child("feb").setValue(ratingModel).addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    });
                }

                if (indexMonth == 2) {
                    databaseReference.child("Rating").child(codeStudent).child("mar").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            ratingModel = snapshot.getValue(RatingModel.class);

                            if (ratingModel != null){
                                etReview.setText(ratingModel.getReview());
                                etPreservation.setText(ratingModel.getPreservation());
                                etAudience.setText(ratingModel.getAudience());
                                etAbsence.setText(ratingModel.getAbsence());
                                etTotal.setText(ratingModel.getTotal());
                                etNotes.setText(ratingModel.getNotes());

                            }else{
                                Objects.requireNonNull(etReview.getText()).clear();
                                Objects.requireNonNull(etPreservation.getText()).clear();
                                Objects.requireNonNull(etAudience.getText()).clear();
                                Objects.requireNonNull(etAbsence.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etNotes.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    btAddRating.setOnClickListener(v -> {
                        review = Objects.requireNonNull(etReview.getText()).toString();
                        preservation = Objects.requireNonNull(etPreservation.getText()).toString();
                        audience = Objects.requireNonNull(etAudience.getText()).toString();
                        absence = Objects.requireNonNull(etAbsence.getText()).toString();
                        total = Objects.requireNonNull(etTotal.getText()).toString();
                        notes = Objects.requireNonNull(etNotes.getText()).toString();

                        ratingModel = new RatingModel(review, preservation, audience, absence, total, notes);
                        databaseReference.child("Rating").child(codeStudent).child("mar").setValue(ratingModel).addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    });
                }

                if (indexMonth == 3) {
                    databaseReference.child("Rating").child(codeStudent).child("apr").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            ratingModel = snapshot.getValue(RatingModel.class);

                            if (ratingModel != null){
                                etReview.setText(ratingModel.getReview());
                                etPreservation.setText(ratingModel.getPreservation());
                                etAudience.setText(ratingModel.getAudience());
                                etAbsence.setText(ratingModel.getAbsence());
                                etTotal.setText(ratingModel.getTotal());
                                etNotes.setText(ratingModel.getNotes());

                            }else{
                                Objects.requireNonNull(etReview.getText()).clear();
                                Objects.requireNonNull(etPreservation.getText()).clear();
                                Objects.requireNonNull(etAudience.getText()).clear();
                                Objects.requireNonNull(etAbsence.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etNotes.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    btAddRating.setOnClickListener(v -> {
                        review = Objects.requireNonNull(etReview.getText()).toString();
                        preservation = Objects.requireNonNull(etPreservation.getText()).toString();
                        audience = Objects.requireNonNull(etAudience.getText()).toString();
                        absence = Objects.requireNonNull(etAbsence.getText()).toString();
                        total = Objects.requireNonNull(etTotal.getText()).toString();
                        notes = Objects.requireNonNull(etNotes.getText()).toString();

                        ratingModel = new RatingModel(review, preservation, audience, absence, total, notes);
                        databaseReference.child("Rating").child(codeStudent).child("apr").setValue(ratingModel).addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    });
                }

                if (indexMonth == 4) {
                    databaseReference.child("Rating").child(codeStudent).child("may").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            ratingModel = snapshot.getValue(RatingModel.class);

                            if (ratingModel != null){
                                etReview.setText(ratingModel.getReview());
                                etPreservation.setText(ratingModel.getPreservation());
                                etAudience.setText(ratingModel.getAudience());
                                etAbsence.setText(ratingModel.getAbsence());
                                etTotal.setText(ratingModel.getTotal());
                                etNotes.setText(ratingModel.getNotes());

                            }else{
                                Objects.requireNonNull(etReview.getText()).clear();
                                Objects.requireNonNull(etPreservation.getText()).clear();
                                Objects.requireNonNull(etAudience.getText()).clear();
                                Objects.requireNonNull(etAbsence.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etNotes.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    btAddRating.setOnClickListener(v -> {
                        review = Objects.requireNonNull(etReview.getText()).toString();
                        preservation = Objects.requireNonNull(etPreservation.getText()).toString();
                        audience = Objects.requireNonNull(etAudience.getText()).toString();
                        absence = Objects.requireNonNull(etAbsence.getText()).toString();
                        total = Objects.requireNonNull(etTotal.getText()).toString();
                        notes = Objects.requireNonNull(etNotes.getText()).toString();

                        ratingModel = new RatingModel(review, preservation, audience, absence, total, notes);
                        databaseReference.child("Rating").child(codeStudent).child("may").setValue(ratingModel).addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    });
                }

                if (indexMonth == 5) {
                    databaseReference.child("Rating").child(codeStudent).child("jun").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {

                            ratingModel = snapshot.getValue(RatingModel.class);

                            if (ratingModel != null){
                                etReview.setText(ratingModel.getReview());
                                etPreservation.setText(ratingModel.getPreservation());
                                etAudience.setText(ratingModel.getAudience());
                                etAbsence.setText(ratingModel.getAbsence());
                                etTotal.setText(ratingModel.getTotal());
                                etNotes.setText(ratingModel.getNotes());

                            }else{
                                Objects.requireNonNull(etReview.getText()).clear();
                                Objects.requireNonNull(etPreservation.getText()).clear();
                                Objects.requireNonNull(etAudience.getText()).clear();
                                Objects.requireNonNull(etAbsence.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etNotes.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    btAddRating.setOnClickListener(v -> {
                        review = Objects.requireNonNull(etReview.getText()).toString();
                        preservation = Objects.requireNonNull(etPreservation.getText()).toString();
                        audience = Objects.requireNonNull(etAudience.getText()).toString();
                        absence = Objects.requireNonNull(etAbsence.getText()).toString();
                        total = Objects.requireNonNull(etTotal.getText()).toString();
                        notes = Objects.requireNonNull(etNotes.getText()).toString();

                        ratingModel = new RatingModel(review, preservation, audience, absence, total, notes);
                        databaseReference.child("Rating").child(codeStudent).child("jun").setValue(ratingModel).addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    });
                }

                if (indexMonth == 6) {
                    databaseReference.child("Rating").child(codeStudent).child("jul").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            ratingModel = snapshot.getValue(RatingModel.class);

                            if (ratingModel != null){
                                etReview.setText(ratingModel.getReview());
                                etPreservation.setText(ratingModel.getPreservation());
                                etAudience.setText(ratingModel.getAudience());
                                etAbsence.setText(ratingModel.getAbsence());
                                etTotal.setText(ratingModel.getTotal());
                                etNotes.setText(ratingModel.getNotes());

                            }else{
                                Objects.requireNonNull(etReview.getText()).clear();
                                Objects.requireNonNull(etPreservation.getText()).clear();
                                Objects.requireNonNull(etAudience.getText()).clear();
                                Objects.requireNonNull(etAbsence.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etNotes.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    btAddRating.setOnClickListener(v -> {
                        review = Objects.requireNonNull(etReview.getText()).toString();
                        preservation = Objects.requireNonNull(etPreservation.getText()).toString();
                        audience = Objects.requireNonNull(etAudience.getText()).toString();
                        absence = Objects.requireNonNull(etAbsence.getText()).toString();
                        total = Objects.requireNonNull(etTotal.getText()).toString();
                        notes = Objects.requireNonNull(etNotes.getText()).toString();

                        ratingModel = new RatingModel(review, preservation, audience, absence, total, notes);
                        databaseReference.child("Rating").child(codeStudent).child("jul").setValue(ratingModel).addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    });
                }

                if (indexMonth == 7) {
                    databaseReference.child("Rating").child(codeStudent).child("aug").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            ratingModel = snapshot.getValue(RatingModel.class);

                            if (ratingModel != null){
                                etReview.setText(ratingModel.getReview());
                                etPreservation.setText(ratingModel.getPreservation());
                                etAudience.setText(ratingModel.getAudience());
                                etAbsence.setText(ratingModel.getAbsence());
                                etTotal.setText(ratingModel.getTotal());
                                etNotes.setText(ratingModel.getNotes());

                            }else{
                                Objects.requireNonNull(etReview.getText()).clear();
                                Objects.requireNonNull(etPreservation.getText()).clear();
                                Objects.requireNonNull(etAudience.getText()).clear();
                                Objects.requireNonNull(etAbsence.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etNotes.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    btAddRating.setOnClickListener(v -> {
                        review = Objects.requireNonNull(etReview.getText()).toString();
                        preservation = Objects.requireNonNull(etPreservation.getText()).toString();
                        audience = Objects.requireNonNull(etAudience.getText()).toString();
                        absence = Objects.requireNonNull(etAbsence.getText()).toString();
                        total = Objects.requireNonNull(etTotal.getText()).toString();
                        notes = Objects.requireNonNull(etNotes.getText()).toString();

                        ratingModel = new RatingModel(review, preservation, audience, absence, total, notes);
                        databaseReference.child("Rating").child(codeStudent).child("aug").setValue(ratingModel).addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    });
                }

                if (indexMonth == 8) {
                    databaseReference.child("Rating").child(codeStudent).child("sep").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            ratingModel = snapshot.getValue(RatingModel.class);

                            if (ratingModel != null){
                                etReview.setText(ratingModel.getReview());
                                etPreservation.setText(ratingModel.getPreservation());
                                etAudience.setText(ratingModel.getAudience());
                                etAbsence.setText(ratingModel.getAbsence());
                                etTotal.setText(ratingModel.getTotal());
                                etNotes.setText(ratingModel.getNotes());

                            }else{
                                Objects.requireNonNull(etReview.getText()).clear();
                                Objects.requireNonNull(etPreservation.getText()).clear();
                                Objects.requireNonNull(etAudience.getText()).clear();
                                Objects.requireNonNull(etAbsence.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etNotes.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    btAddRating.setOnClickListener(v -> {
                        review = Objects.requireNonNull(etReview.getText()).toString();
                        preservation = Objects.requireNonNull(etPreservation.getText()).toString();
                        audience = Objects.requireNonNull(etAudience.getText()).toString();
                        absence = Objects.requireNonNull(etAbsence.getText()).toString();
                        total = Objects.requireNonNull(etTotal.getText()).toString();
                        notes = Objects.requireNonNull(etNotes.getText()).toString();

                        ratingModel = new RatingModel(review, preservation, audience, absence, total, notes);
                        databaseReference.child("Rating").child(codeStudent).child("sep").setValue(ratingModel).addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    });
                }

                if (indexMonth == 9) {
                    databaseReference.child("Rating").child(codeStudent).child("oct").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            ratingModel = snapshot.getValue(RatingModel.class);

                            if (ratingModel != null){
                                etReview.setText(ratingModel.getReview());
                                etPreservation.setText(ratingModel.getPreservation());
                                etAudience.setText(ratingModel.getAudience());
                                etAbsence.setText(ratingModel.getAbsence());
                                etTotal.setText(ratingModel.getTotal());
                                etNotes.setText(ratingModel.getNotes());

                            }else{
                                Objects.requireNonNull(etReview.getText()).clear();
                                Objects.requireNonNull(etPreservation.getText()).clear();
                                Objects.requireNonNull(etAudience.getText()).clear();
                                Objects.requireNonNull(etAbsence.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etNotes.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    btAddRating.setOnClickListener(v -> {
                        review = Objects.requireNonNull(etReview.getText()).toString();
                        preservation = Objects.requireNonNull(etPreservation.getText()).toString();
                        audience = Objects.requireNonNull(etAudience.getText()).toString();
                        absence = Objects.requireNonNull(etAbsence.getText()).toString();
                        total = Objects.requireNonNull(etTotal.getText()).toString();
                        notes = Objects.requireNonNull(etNotes.getText()).toString();

                        ratingModel = new RatingModel(review, preservation, audience, absence, total, notes);
                        databaseReference.child("Rating").child(codeStudent).child("oct").setValue(ratingModel).addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    });
                }

                if (indexMonth == 10) {
                    databaseReference.child("Rating").child(codeStudent).child("nov").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            ratingModel = snapshot.getValue(RatingModel.class);

                            if (ratingModel != null){
                                etReview.setText(ratingModel.getReview());
                                etPreservation.setText(ratingModel.getPreservation());
                                etAudience.setText(ratingModel.getAudience());
                                etAbsence.setText(ratingModel.getAbsence());
                                etTotal.setText(ratingModel.getTotal());
                                etNotes.setText(ratingModel.getNotes());

                            }else{
                                Objects.requireNonNull(etReview.getText()).clear();
                                Objects.requireNonNull(etPreservation.getText()).clear();
                                Objects.requireNonNull(etAudience.getText()).clear();
                                Objects.requireNonNull(etAbsence.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etNotes.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    btAddRating.setOnClickListener(v -> {
                        review = Objects.requireNonNull(etReview.getText()).toString();
                        preservation = Objects.requireNonNull(etPreservation.getText()).toString();
                        audience = Objects.requireNonNull(etAudience.getText()).toString();
                        absence = Objects.requireNonNull(etAbsence.getText()).toString();
                        total = Objects.requireNonNull(etTotal.getText()).toString();
                        notes = Objects.requireNonNull(etNotes.getText()).toString();

                        ratingModel = new RatingModel(review, preservation, audience, absence, total, notes);
                        databaseReference.child("Rating").child(codeStudent).child("nov").setValue(ratingModel).addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    });
                }

                if (indexMonth == 11) {
                    databaseReference.child("Rating").child(codeStudent).child("dec").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            ratingModel = snapshot.getValue(RatingModel.class);

                            if (ratingModel != null){
                                etReview.setText(ratingModel.getReview());
                                etPreservation.setText(ratingModel.getPreservation());
                                etAudience.setText(ratingModel.getAudience());
                                etAbsence.setText(ratingModel.getAbsence());
                                etTotal.setText(ratingModel.getTotal());
                                etNotes.setText(ratingModel.getNotes());

                            }else{
                                Objects.requireNonNull(etReview.getText()).clear();
                                Objects.requireNonNull(etPreservation.getText()).clear();
                                Objects.requireNonNull(etAudience.getText()).clear();
                                Objects.requireNonNull(etAbsence.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etNotes.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(context, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    btAddRating.setOnClickListener(v -> {
                        review = Objects.requireNonNull(etReview.getText()).toString();
                        preservation = Objects.requireNonNull(etPreservation.getText()).toString();
                        audience = Objects.requireNonNull(etAudience.getText()).toString();
                        absence = Objects.requireNonNull(etAbsence.getText()).toString();
                        total = Objects.requireNonNull(etTotal.getText()).toString();
                        notes = Objects.requireNonNull(etNotes.getText()).toString();

                        ratingModel = new RatingModel(review, preservation, audience, absence, total, notes);
                        databaseReference.child("Rating").child(codeStudent).child("dec").setValue(ratingModel).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    });
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return viewRating;
    }
    @Override
    public void onPopupWindowOpened(AppCompatSpinner spinner) {
        spinnerMonth.setBackground(AppCompatResources.getDrawable(context,R.drawable.bg_spinner_month_up));
    }

    @Override
    public void onPopupWindowClosed(AppCompatSpinner spinner) {
        spinnerMonth.setBackground(AppCompatResources.getDrawable(context,R.drawable.bg_spinner_month));
    }
}