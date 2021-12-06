package com.arafa.mohamed.darsidraapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.adapter.ReservationAdapter;
import com.arafa.mohamed.darsidraapp.adapter.StudentsAdapter;
import com.arafa.mohamed.darsidraapp.models.ReservationModel;
import com.arafa.mohamed.darsidraapp.models.StudentModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewReservationActivity extends AppCompatActivity {
    AppCompatTextView tvToolbar;
    AppCompatImageButton btBackArrow;
    DatabaseReference databaseReference;
    ArrayList<ReservationModel> listReservation;
    ReservationModel reservationModel;
    ReservationAdapter reservationAdapter;
    RecyclerView recReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservation);

        btBackArrow = findViewById(R.id.button_back_arrow);
        tvToolbar = findViewById(R.id.text_toolbar);
        recReservation = findViewById(R.id.rec_reservations);
        listReservation = new ArrayList<>();

        tvToolbar.setText(R.string.view_reservation);
        btBackArrow.setOnClickListener(v -> finish());

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("Reservations").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listReservation.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                     reservationModel = postSnapshot.getValue(ReservationModel.class);
                     listReservation.add(reservationModel);
                }
                if(!listReservation.isEmpty()){

                    reservationAdapter = new ReservationAdapter(ViewReservationActivity.this,listReservation);
                    reservationAdapter.notifyDataSetChanged();
                    recReservation.setAdapter(reservationAdapter);
                    recReservation.setLayoutManager(new LinearLayoutManager(ViewReservationActivity.this));

                }else {
                    Toast.makeText(ViewReservationActivity.this, "لا يوجد حاليا حجوزات", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewReservationActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}