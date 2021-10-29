package com.arafa.mohamed.darsidraapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.models.SubscriptionModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class SubscriptionDetailsFragment extends Fragment {
    TextInputEditText etJan, etFeb, etMar, etApr, etMay, etJun, etJul, etAug, etSep, etOct, etNov, etDec;
    FloatingActionButton btAddSubscription;
    String jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec;
    SubscriptionModel subscriptionModel;
    DatabaseReference databaseReference;
    String codeStudent;

    public SubscriptionDetailsFragment(String codeStudent) {
        this.codeStudent = codeStudent;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        databaseReference = FirebaseDatabase.getInstance().getReference();

        View viewSubscription = inflater.inflate(R.layout.fragment_subscription_details, container, false);
        etJan = viewSubscription.findViewById(R.id.editText_jan);
        etFeb = viewSubscription.findViewById(R.id.editText_feb);
        etMar = viewSubscription.findViewById(R.id.editText_mar);
        etApr = viewSubscription.findViewById(R.id.editText_apr);
        etMay = viewSubscription.findViewById(R.id.editText_may);
        etJun = viewSubscription.findViewById(R.id.editText_jun);
        etJul = viewSubscription.findViewById(R.id.editText_jul);
        etAug = viewSubscription.findViewById(R.id.editText_aug);
        etSep = viewSubscription.findViewById(R.id.editText_sep);
        etOct = viewSubscription.findViewById(R.id.editText_oct);
        etNov = viewSubscription.findViewById(R.id.editText_nov);
        etDec = viewSubscription.findViewById(R.id.editText_dec);
        btAddSubscription = viewSubscription.findViewById(R.id.button_add_subscription);

        databaseReference.child("Subscription").child(codeStudent).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                subscriptionModel = snapshot.getValue(SubscriptionModel.class);
                if(subscriptionModel != null){
                    etJan.setText(subscriptionModel.getJan());
                    etFeb.setText(subscriptionModel.getFeb());
                    etMar.setText(subscriptionModel.getMar());
                    etApr.setText(subscriptionModel.getApr());
                    etMay.setText(subscriptionModel.getMay());
                    etJun.setText(subscriptionModel.getJun());
                    etJul.setText(subscriptionModel.getJul());
                    etAug.setText(subscriptionModel.getAug());
                    etSep.setText(subscriptionModel.getSep());
                    etOct.setText(subscriptionModel.getOct());
                    etNov.setText(subscriptionModel.getNov());
                    etDec.setText(subscriptionModel.getDec());
                }else{
                    Toast.makeText(getActivity(), "لا يوجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                Toast.makeText(getActivity(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btAddSubscription.setOnClickListener(v -> {
            jan = Objects.requireNonNull(etJan.getText()).toString();
            feb = Objects.requireNonNull(etFeb.getText()).toString();
            mar = Objects.requireNonNull(etMar.getText()).toString();
            apr = Objects.requireNonNull(etApr.getText()).toString();
            may = Objects.requireNonNull(etMay.getText()).toString();
            jun = Objects.requireNonNull(etJun.getText()).toString();
            jul = Objects.requireNonNull(etJul.getText()).toString();
            aug = Objects.requireNonNull(etAug.getText()).toString();
            sep = Objects.requireNonNull(etSep.getText()).toString();
            oct = Objects.requireNonNull(etOct.getText()).toString();
            nov = Objects.requireNonNull(etNov.getText()).toString();
            dec = Objects.requireNonNull(etDec.getText()).toString();
            subscriptionModel = new SubscriptionModel(jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec);
            databaseReference.child("Subscription").child(codeStudent).setValue(subscriptionModel).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    Toast.makeText(getActivity(), "تم الاضاغة بنجاح", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        });

        return viewSubscription;
    }
}