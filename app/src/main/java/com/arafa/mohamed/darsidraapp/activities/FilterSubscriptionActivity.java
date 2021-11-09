package com.arafa.mohamed.darsidraapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.adapter.FilterSubscriptionAdapter;
import com.arafa.mohamed.darsidraapp.adapter.MonthAdapter;
import com.arafa.mohamed.darsidraapp.models.CustomSpinner;
import com.arafa.mohamed.darsidraapp.models.StudentModel;
import com.arafa.mohamed.darsidraapp.models.SubscriptionModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class FilterSubscriptionActivity extends AppCompatActivity implements CustomSpinner.OnSpinnerEventsListener {
    CustomSpinner spinnerMonth;
    AppCompatTextView tvToolbar,tvCounter;
    AppCompatImageButton btBackArrow;
    int indexMonth;
    DatabaseReference databaseReference;
    String value;
    StudentModel studentModel;
    ArrayList<StudentModel> listFilterStudent;
    RecyclerView recFilterSubscription;
    FilterSubscriptionAdapter filterSubscriptionAdapter;
    MonthAdapter monthAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_subscription);

        spinnerMonth = findViewById(R.id.spinner_month);
        spinnerMonth.setSpinnerEventsListener(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        recFilterSubscription = findViewById(R.id.rec_filter_subscription);
        listFilterStudent = new ArrayList<>();
        btBackArrow = findViewById(R.id.button_back_arrow);
        tvToolbar = findViewById(R.id.text_toolbar);
        tvCounter = findViewById(R.id.text_counter);

        tvToolbar.setText(R.string.filter_month);
        btBackArrow.setOnClickListener(v -> finish());

        monthAdapter = new MonthAdapter(FilterSubscriptionActivity.this, SubscriptionModel.getMonth());
        spinnerMonth.setAdapter(monthAdapter);
        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                indexMonth = spinnerMonth.getSelectedItemPosition();
                if (indexMonth == 0) {
                    databaseReference.child("Subscription").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            listFilterStudent.clear();
                            for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                value = postSnapshot.child("jan").getValue(String.class);
                                if(value != null && !value.equals("تم الدفع")) {
                                    studentModel = postSnapshot.getValue(StudentModel.class);
                                    listFilterStudent.add(studentModel);
                                }
                            }
                                    if (!listFilterStudent.isEmpty()){
                                        filterSubscriptionAdapter = new FilterSubscriptionAdapter(FilterSubscriptionActivity.this,listFilterStudent);
                                        filterSubscriptionAdapter.notifyDataSetChanged();
                                        recFilterSubscription.setAdapter(filterSubscriptionAdapter);
                                        recFilterSubscription.setLayoutManager(new LinearLayoutManager(FilterSubscriptionActivity.this));
                                        tvCounter.setText(String.valueOf(listFilterStudent.size()));
                                    }else{
                                        filterSubscriptionAdapter.notifyDataSetChanged();
                                        tvCounter.setText("لاتوجد بيانات");
                                    }

                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(FilterSubscriptionActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                if (indexMonth == 1) {
                    databaseReference.child("Subscription").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            listFilterStudent.clear();
                            for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                value = postSnapshot.child("feb").getValue(String.class);
                                if (value != null && !value.equals("تم الدفع")) {
                                    studentModel = postSnapshot.getValue(StudentModel.class);
                                    listFilterStudent.add(studentModel);
                                }
                            }
                            if (!listFilterStudent.isEmpty()) {
                                filterSubscriptionAdapter = new FilterSubscriptionAdapter(FilterSubscriptionActivity.this, listFilterStudent);
                                filterSubscriptionAdapter.notifyDataSetChanged();
                                recFilterSubscription.setAdapter(filterSubscriptionAdapter);
                                recFilterSubscription.setLayoutManager(new LinearLayoutManager(FilterSubscriptionActivity.this));
                                tvCounter.setText(String.valueOf(listFilterStudent.size()));
                            } else {
                                filterSubscriptionAdapter.notifyDataSetChanged();
                                tvCounter.setText("لاتوجد بيانات");
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(FilterSubscriptionActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                if (indexMonth == 2) {
                    databaseReference.child("Subscription").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            listFilterStudent.clear();
                            for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                value = postSnapshot.child("mar").getValue(String.class);
                                if (value != null && !value.equals("تم الدفع")) {
                                    studentModel = postSnapshot.getValue(StudentModel.class);
                                    listFilterStudent.add(studentModel);
                                }
                            }
                            if (!listFilterStudent.isEmpty()) {
                                filterSubscriptionAdapter = new FilterSubscriptionAdapter(FilterSubscriptionActivity.this, listFilterStudent);
                                filterSubscriptionAdapter.notifyDataSetChanged();
                                recFilterSubscription.setAdapter(filterSubscriptionAdapter);
                                recFilterSubscription.setLayoutManager(new LinearLayoutManager(FilterSubscriptionActivity.this));
                                tvCounter.setText(String.valueOf(listFilterStudent.size()));
                            } else {
                                filterSubscriptionAdapter.notifyDataSetChanged();
                                tvCounter.setText("لاتوجد بيانات");
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(FilterSubscriptionActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                if (indexMonth == 3) {
                    databaseReference.child("Subscription").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            listFilterStudent.clear();
                            for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                value = postSnapshot.child("apr").getValue(String.class);
                                if (value != null && !value.equals("تم الدفع")) {
                                    studentModel = postSnapshot.getValue(StudentModel.class);
                                    listFilterStudent.add(studentModel);
                                }
                            }
                            if (!listFilterStudent.isEmpty()) {
                                filterSubscriptionAdapter = new FilterSubscriptionAdapter(FilterSubscriptionActivity.this, listFilterStudent);
                                filterSubscriptionAdapter.notifyDataSetChanged();
                                recFilterSubscription.setAdapter(filterSubscriptionAdapter);
                                recFilterSubscription.setLayoutManager(new LinearLayoutManager(FilterSubscriptionActivity.this));
                                tvCounter.setText(String.valueOf(listFilterStudent.size()));
                            } else {
                                filterSubscriptionAdapter.notifyDataSetChanged();
                                tvCounter.setText("لاتوجد بيانات");
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(FilterSubscriptionActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                if (indexMonth == 4) {
                    databaseReference.child("Subscription").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            listFilterStudent.clear();
                            for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                value = postSnapshot.child("may").getValue(String.class);
                                if (value != null && !value.equals("تم الدفع")) {
                                    studentModel = postSnapshot.getValue(StudentModel.class);
                                    listFilterStudent.add(studentModel);
                                }
                            }
                            if (!listFilterStudent.isEmpty()) {
                                filterSubscriptionAdapter = new FilterSubscriptionAdapter(FilterSubscriptionActivity.this, listFilterStudent);
                                filterSubscriptionAdapter.notifyDataSetChanged();
                                recFilterSubscription.setAdapter(filterSubscriptionAdapter);
                                recFilterSubscription.setLayoutManager(new LinearLayoutManager(FilterSubscriptionActivity.this));
                                tvCounter.setText(String.valueOf(listFilterStudent.size()));
                            } else {
                                filterSubscriptionAdapter.notifyDataSetChanged();
                                tvCounter.setText("لاتوجد بيانات");
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(FilterSubscriptionActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                if (indexMonth == 5) {
                    databaseReference.child("Subscription").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            listFilterStudent.clear();
                            for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                value = postSnapshot.child("jun").getValue(String.class);
                                if (value != null && !value.equals("تم الدفع")) {
                                    studentModel = postSnapshot.getValue(StudentModel.class);
                                    listFilterStudent.add(studentModel);
                                }
                            }
                            if (!listFilterStudent.isEmpty()) {
                                filterSubscriptionAdapter = new FilterSubscriptionAdapter(FilterSubscriptionActivity.this, listFilterStudent);
                                filterSubscriptionAdapter.notifyDataSetChanged();
                                recFilterSubscription.setAdapter(filterSubscriptionAdapter);
                                recFilterSubscription.setLayoutManager(new LinearLayoutManager(FilterSubscriptionActivity.this));
                                tvCounter.setText(String.valueOf(listFilterStudent.size()));

                            } else {
                                filterSubscriptionAdapter.notifyDataSetChanged();
                                tvCounter.setText("لاتوجد بيانات");
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(FilterSubscriptionActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                if (indexMonth == 6) {
                    databaseReference.child("Subscription").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            listFilterStudent.clear();
                            for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                value = postSnapshot.child("jul").getValue(String.class);
                                if (value != null && !value.equals("تم الدفع")) {
                                    studentModel = postSnapshot.getValue(StudentModel.class);
                                    listFilterStudent.add(studentModel);
                                }
                            }
                            if (!listFilterStudent.isEmpty()) {
                                filterSubscriptionAdapter = new FilterSubscriptionAdapter(FilterSubscriptionActivity.this, listFilterStudent);
                                filterSubscriptionAdapter.notifyDataSetChanged();
                                recFilterSubscription.setAdapter(filterSubscriptionAdapter);
                                recFilterSubscription.setLayoutManager(new LinearLayoutManager(FilterSubscriptionActivity.this));
                                tvCounter.setText(String.valueOf(listFilterStudent.size()));
                            } else {
                                filterSubscriptionAdapter.notifyDataSetChanged();
                                tvCounter.setText("لاتوجد بيانات");
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(FilterSubscriptionActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                if (indexMonth == 7) {
                    databaseReference.child("Subscription").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            listFilterStudent.clear();
                            for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                value = postSnapshot.child("aug").getValue(String.class);
                                if (value != null && !value.equals("تم الدفع")) {
                                    studentModel = postSnapshot.getValue(StudentModel.class);
                                    listFilterStudent.add(studentModel);
                                }
                            }
                            if (!listFilterStudent.isEmpty()) {
                                filterSubscriptionAdapter = new FilterSubscriptionAdapter(FilterSubscriptionActivity.this, listFilterStudent);
                                filterSubscriptionAdapter.notifyDataSetChanged();
                                recFilterSubscription.setAdapter(filterSubscriptionAdapter);
                                recFilterSubscription.setLayoutManager(new LinearLayoutManager(FilterSubscriptionActivity.this));
                                tvCounter.setText(String.valueOf(listFilterStudent.size()));
                            } else {
                                filterSubscriptionAdapter.notifyDataSetChanged();
                                tvCounter.setText("لاتوجد بيانات");
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(FilterSubscriptionActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                if (indexMonth == 8) {
                    databaseReference.child("Subscription").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            listFilterStudent.clear();
                            for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                value = postSnapshot.child("sep").getValue(String.class);
                                if (value != null && !value.equals("تم الدفع")) {
                                    studentModel = postSnapshot.getValue(StudentModel.class);
                                    listFilterStudent.add(studentModel);
                                }
                            }
                            if (!listFilterStudent.isEmpty()) {
                                filterSubscriptionAdapter = new FilterSubscriptionAdapter(FilterSubscriptionActivity.this, listFilterStudent);
                                filterSubscriptionAdapter.notifyDataSetChanged();
                                recFilterSubscription.setAdapter(filterSubscriptionAdapter);
                                recFilterSubscription.setLayoutManager(new LinearLayoutManager(FilterSubscriptionActivity.this));
                                tvCounter.setText(String.valueOf(listFilterStudent.size()));
                            } else {
                                filterSubscriptionAdapter.notifyDataSetChanged();
                                tvCounter.setText("لاتوجد بيانات");
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(FilterSubscriptionActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                if (indexMonth == 9) {
                    databaseReference.child("Subscription").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            listFilterStudent.clear();
                            for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                value = postSnapshot.child("oct").getValue(String.class);
                                if (value != null && !value.equals("تم الدفع")) {
                                    studentModel = postSnapshot.getValue(StudentModel.class);
                                    listFilterStudent.add(studentModel);
                                }
                            }
                            if (!listFilterStudent.isEmpty()) {
                                filterSubscriptionAdapter = new FilterSubscriptionAdapter(FilterSubscriptionActivity.this, listFilterStudent);
                                filterSubscriptionAdapter.notifyDataSetChanged();
                                recFilterSubscription.setAdapter(filterSubscriptionAdapter);
                                recFilterSubscription.setLayoutManager(new LinearLayoutManager(FilterSubscriptionActivity.this));
                                tvCounter.setText(String.valueOf(listFilterStudent.size()));
                            } else {
                                filterSubscriptionAdapter.notifyDataSetChanged();
                                tvCounter.setText("لاتوجد بيانات");
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(FilterSubscriptionActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                if (indexMonth == 10) {
                    databaseReference.child("Subscription").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            listFilterStudent.clear();
                            for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                value = postSnapshot.child("nov").getValue(String.class);
                                if (value != null && !value.equals("تم الدفع")) {
                                    studentModel = postSnapshot.getValue(StudentModel.class);
                                    listFilterStudent.add(studentModel);
                                }
                            }
                            if (!listFilterStudent.isEmpty()) {
                                filterSubscriptionAdapter = new FilterSubscriptionAdapter(FilterSubscriptionActivity.this, listFilterStudent);
                                filterSubscriptionAdapter.notifyDataSetChanged();
                                recFilterSubscription.setAdapter(filterSubscriptionAdapter);
                                recFilterSubscription.setLayoutManager(new LinearLayoutManager(FilterSubscriptionActivity.this));
                                tvCounter.setText(String.valueOf(listFilterStudent.size()));
                            } else {
                                filterSubscriptionAdapter.notifyDataSetChanged();
                                tvCounter.setText("لاتوجد بيانات");
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(FilterSubscriptionActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                if (indexMonth == 11) {
                    databaseReference.child("Subscription").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            listFilterStudent.clear();
                            for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                value = postSnapshot.child("dec").getValue(String.class);
                                if (value != null && !value.equals("تم الدفع")) {
                                    studentModel = postSnapshot.getValue(StudentModel.class);
                                    listFilterStudent.add(studentModel);
                                }
                            }
                            if (!listFilterStudent.isEmpty()) {
                                filterSubscriptionAdapter = new FilterSubscriptionAdapter(FilterSubscriptionActivity.this, listFilterStudent);
                                filterSubscriptionAdapter.notifyDataSetChanged();
                                recFilterSubscription.setAdapter(filterSubscriptionAdapter);
                                recFilterSubscription.setLayoutManager(new LinearLayoutManager(FilterSubscriptionActivity.this));
                                tvCounter.setText(String.valueOf(listFilterStudent.size()));
                            } else {
                                filterSubscriptionAdapter.notifyDataSetChanged();
                                tvCounter.setText("لاتوجد بيانات");
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(FilterSubscriptionActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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