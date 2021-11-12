package com.arafa.mohamed.darsidraapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
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
    AppCompatCheckBox chxJan, chxFeb, chxMar, chxApr, chxMay, chxJun, chxJul, chxAug, chxSep, chxOct, chxNov, chxDec;
    FloatingActionButton btAddSubscription;
    String jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec, codeStudent, nameStudent, classStudent;
    SubscriptionModel subscriptionModel;
    DatabaseReference databaseReference;
    Context context;

    public SubscriptionDetailsFragment() {

    }

    public SubscriptionDetailsFragment(String codeStudent, String nameStudent, String classStudent, Context context) {
        this.codeStudent = codeStudent;
        this.nameStudent = nameStudent;
        this.classStudent = classStudent;
        this.context = context;
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

        chxJan = viewSubscription.findViewById(R.id.checkbox_jan);
        chxFeb = viewSubscription.findViewById(R.id.checkbox_feb);
        chxMar = viewSubscription.findViewById(R.id.checkbox_mar);
        chxApr = viewSubscription.findViewById(R.id.checkbox_apr);
        chxMay = viewSubscription.findViewById(R.id.checkbox_may);
        chxJun = viewSubscription.findViewById(R.id.checkbox_jun);
        chxJul = viewSubscription.findViewById(R.id.checkbox_jul);
        chxAug = viewSubscription.findViewById(R.id.checkbox_aug);
        chxSep = viewSubscription.findViewById(R.id.checkbox_sep);
        chxOct = viewSubscription.findViewById(R.id.checkbox_oct);
        chxNov = viewSubscription.findViewById(R.id.checkbox_nov);
        chxDec = viewSubscription.findViewById(R.id.checkbox_dec);

        chxJan.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(chxJan.isChecked()){
                etJan.setText(R.string.payment);
                etJan.setEnabled(false);
            }else{
                etJan.setText("");
                etJan.setEnabled(true);
            }
        });

        chxFeb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(chxFeb.isChecked()){
                etFeb.setText(R.string.payment);
                etFeb.setEnabled(false);
            }else{
                etFeb.setText("");
                etFeb.setEnabled(true);
            }
        });

        chxMar.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(chxMar.isChecked()){
                etMar.setText(R.string.payment);
                etMar.setEnabled(false);
            }else{
                etMar.setText("");
                etMar.setEnabled(true);
            }
        });

        chxApr.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(chxApr.isChecked()){
                etApr.setText(R.string.payment);
                etApr.setEnabled(false);
            }else{
                etApr.setText("");
                etApr.setEnabled(true);
            }
        });

        chxMay.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(chxMay.isChecked()){
                etMay.setText(R.string.payment);
                etMay.setEnabled(false);
            }else{
                etMay.setText("");
                etMay.setEnabled(true);
            }
        });

        chxJun.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(chxJun.isChecked()){
                etJun.setText(R.string.payment);
                etJun.setEnabled(false);
            }else{
                etJun.setText("");
                etJun.setEnabled(true);
            }
        });

        chxJul.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(chxJul.isChecked()){
                etJul.setText(R.string.payment);
                etJul.setEnabled(false);
            }else{
                etJul.setText("");
                etJul.setEnabled(true);
            }
        });

        chxAug.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(chxAug.isChecked()){
                etAug.setText(R.string.payment);
                etAug.setEnabled(false);
            }else{
                etAug.setText("");
                etAug.setEnabled(true);
            }
        });

        chxSep.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(chxSep.isChecked()){
                etSep.setText(R.string.payment);
                etSep.setEnabled(false);
            }else{
                etSep.setText("");
                etSep.setEnabled(true);
            }
        });

        chxOct.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(chxOct.isChecked()){
                etOct.setText(R.string.payment);
                etOct.setEnabled(false);
            }else{
                etOct.setText("");
                etOct.setEnabled(true);
            }
        });

        chxNov.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(chxNov.isChecked()){
                etNov.setText(R.string.payment);
                etNov.setEnabled(false);
            }else{
                etNov.setText("");
                etNov.setEnabled(true);
            }
        });

        chxDec.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(chxDec.isChecked()){
                etDec.setText(R.string.payment);
                etDec.setEnabled(false);
            }else{
                etDec.setText("");
                etDec.setEnabled(true);
            }
        });


        btAddSubscription = viewSubscription.findViewById(R.id.button_add_subscription);

        databaseReference.child("Subscription").child(codeStudent).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                subscriptionModel = snapshot.getValue(SubscriptionModel.class);
                if(subscriptionModel != null){
                    if (subscriptionModel.getJan().equals("تم الدفع")){
                        etJan.setText(subscriptionModel.getJan());
                        chxJan.setChecked(true);
                    }
                    if (subscriptionModel.getFeb().equals("تم الدفع")){
                        etFeb.setText(subscriptionModel.getFeb());
                        chxFeb.setChecked(true);
                    }
                    if (subscriptionModel.getMar().equals("تم الدفع")){
                        etMar.setText(subscriptionModel.getMar());
                        chxMar.setChecked(true);
                    }
                    if (subscriptionModel.getApr().equals("تم الدفع")){
                        etApr.setText(subscriptionModel.getApr());
                        chxApr.setChecked(true);
                    }
                    if (subscriptionModel.getMay().equals("تم الدفع")){
                        etMay.setText(subscriptionModel.getMay());
                        chxMay.setChecked(true);
                    }
                    if (subscriptionModel.getJun().equals("تم الدفع")){
                        etJun.setText(subscriptionModel.getJun());
                        chxJun.setChecked(true);
                    }
                    if (subscriptionModel.getJul().equals("تم الدفع")){
                        etJul.setText(subscriptionModel.getJul());
                        chxJul.setChecked(true);
                    }
                    if (subscriptionModel.getAug().equals("تم الدفع")){
                        etAug.setText(subscriptionModel.getAug());
                        chxAug.setChecked(true);
                    }
                    if (subscriptionModel.getSep().equals("تم الدفع")){
                        etSep.setText(subscriptionModel.getSep());
                        chxSep.setChecked(true);
                    }
                    if (subscriptionModel.getOct().equals("تم الدفع")){
                        etOct.setText(subscriptionModel.getOct());
                        chxOct.setChecked(true);
                    }
                    if (subscriptionModel.getNov().equals("تم الدفع")){
                        etNov.setText(subscriptionModel.getNov());
                        chxNov.setChecked(true);
                    }
                    if (subscriptionModel.getDec().equals("تم الدفع")){
                        etDec.setText(subscriptionModel.getDec());
                        chxDec.setChecked(true);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
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
            subscriptionModel = new SubscriptionModel(jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec, codeStudent, nameStudent, classStudent );
            databaseReference.child("Subscription").child(codeStudent).setValue(subscriptionModel).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    Toast.makeText(context, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        });

        return viewSubscription;
    }
}