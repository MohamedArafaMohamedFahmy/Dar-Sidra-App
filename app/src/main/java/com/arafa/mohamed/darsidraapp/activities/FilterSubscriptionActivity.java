package com.arafa.mohamed.darsidraapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatSpinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.models.CustomSpinner;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FilterSubscriptionActivity extends AppCompatActivity implements CustomSpinner.OnSpinnerEventsListener {
    CustomSpinner spinnerMonth;
    int indexMonth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_subscription);

        spinnerMonth = findViewById(R.id.spinner_month);
        spinnerMonth.setSpinnerEventsListener(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        ArrayAdapter listMonth = ArrayAdapter.createFromResource(FilterSubscriptionActivity.this, R.array.list_month, R.layout.my_spinner);
        listMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonth.setAdapter(listMonth);
        spinnerMonth.setOnItemClickListener((parent, view, position, id) -> {
            indexMonth = spinnerMonth.getSelectedItemPosition();
            if(indexMonth == 0){
                databaseReference.child("Subscription").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot snapshot) {

                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {

                    }
                });
            }

        });

    }

    @Override
    public void onPopupWindowOpened(AppCompatSpinner spinner) {
        spinnerMonth.setBackground(AppCompatResources.getDrawable(FilterSubscriptionActivity.this,R.drawable.bg_spinner_blood_type_up));
    }

    @Override
    public void onPopupWindowClosed(AppCompatSpinner spinner) {
        spinnerMonth.setBackground(AppCompatResources.getDrawable(FilterSubscriptionActivity.this,R.drawable.bg_spinner_blood_type));
    }
}