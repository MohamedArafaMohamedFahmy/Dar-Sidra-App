package com.arafa.mohamed.darsidraapp.activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.models.TeachersModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;
import java.util.Objects;

public class TeacherDetailsActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    AppCompatTextView tvToolbar;
    AppCompatImageButton btBackArrow,btSalaryRating, btWhatsAppTeacher, btCallNumberTeacher;
    TextInputEditText etNameTeacher,etEnrollmentTeacher,etCodeTeacher,etMobileTeacher;
    TextInputLayout layoutEnrollmentTeacher;
    AppCompatButton btRegisterData;
    LinearLayout linearProgressBar;
    DatabaseReference databaseReference;
    TeachersModel teachersModel, retrieveDataTeacher;
    String codeTeacher, nameTeacher, phoneNumber, dateEnrollment, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_details);

        btBackArrow = findViewById(R.id.button_back_arrow);
        btSalaryRating = findViewById(R.id.button_rating);
        btWhatsAppTeacher = findViewById(R.id.whats_app_teacher);
        btCallNumberTeacher = findViewById(R.id.call_number_teacher);
        tvToolbar = findViewById(R.id.text_toolbar);
        etNameTeacher = findViewById(R.id.editText_name_teacher);
        etEnrollmentTeacher = findViewById(R.id.editText_enrollment_teacher);
        etCodeTeacher = findViewById(R.id.editText_code_teacher);
        etMobileTeacher = findViewById(R.id.editText_mobile);
        btRegisterData = findViewById(R.id.button_submit);
        linearProgressBar = findViewById(R.id.linear_progress_bar);
        layoutEnrollmentTeacher = findViewById(R.id.date_enrollment_layout);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        tvToolbar.setText(R.string.teacher_details_appbar);
        btBackArrow.setOnClickListener(v -> finish());

        final Object objDetailed = getIntent().getSerializableExtra("detailed");
        retrieveDataTeacher = (TeachersModel) objDetailed;

        if(retrieveDataTeacher != null){
            btCallNumberTeacher.setVisibility(View.VISIBLE);
            btWhatsAppTeacher.setVisibility(View.VISIBLE);
            btSalaryRating.setVisibility(View.VISIBLE);
            btRegisterData.setText("تحديث البيانات");
            etNameTeacher.setText(retrieveDataTeacher.getNameTeacher());
            etEnrollmentTeacher.setText(retrieveDataTeacher.getDateEnrollment());
            etCodeTeacher.setText(retrieveDataTeacher.getCodeTeacher());
            etMobileTeacher.setText(retrieveDataTeacher.getPhoneNumber());
        }

        btWhatsAppTeacher.setOnClickListener(v -> openWhatsappContact(retrieveDataTeacher.getPhoneNumber()));

        btCallNumberTeacher.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+retrieveDataTeacher.getPhoneNumber()));
            startActivity(intent);
        });

        btRegisterData.setOnClickListener(v -> {
            nameTeacher = Objects.requireNonNull(etNameTeacher.getText()).toString();
            codeTeacher = Objects.requireNonNull(etCodeTeacher.getText()).toString();
            phoneNumber = Objects.requireNonNull(etMobileTeacher.getText()).toString();
            dateEnrollment = Objects.requireNonNull(etEnrollmentTeacher.getText()).toString();

            if (!nameTeacher.isEmpty() && !codeTeacher.isEmpty() && phoneNumber.length() == 11 && !dateEnrollment.isEmpty() && retrieveDataTeacher == null){

                linearProgressBar.setVisibility(View.VISIBLE);

                teachersModel = new TeachersModel(codeTeacher, nameTeacher, phoneNumber, dateEnrollment);
                databaseReference.child("TeachersData").child(codeTeacher).setValue(teachersModel).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        linearProgressBar.setVisibility(View.GONE);
                        Toast.makeText(this, "تم التسجيل بنجاح", Toast.LENGTH_SHORT).show();
                        etNameTeacher.getText().clear();
                        etCodeTeacher.getText().clear();
                        etEnrollmentTeacher.getText().clear();
                        etMobileTeacher.getText().clear();
                    }else{
                        linearProgressBar.setVisibility(View.GONE);
                        Toast.makeText(this, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });
            }

            if (!nameTeacher.isEmpty() && !codeTeacher.isEmpty() && retrieveDataTeacher != null  && phoneNumber.length() == 11 && !dateEnrollment.isEmpty()){

                linearProgressBar.setVisibility(View.VISIBLE);

                teachersModel = new TeachersModel(codeTeacher, nameTeacher, phoneNumber, dateEnrollment);
                databaseReference.child("TeachersData").child(codeTeacher).setValue(teachersModel).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        linearProgressBar.setVisibility(View.GONE);
                        Toast.makeText(this, "تم التحديث بنجاح", Toast.LENGTH_SHORT).show();
                    }else{
                        linearProgressBar.setVisibility(View.GONE);
                        Toast.makeText(this, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });
            }

            if (nameTeacher.isEmpty()){
                etNameTeacher.setError("من فضلك ادخل الاسم");
            }
            if(codeTeacher.isEmpty()){
                etCodeTeacher.setError("من فضلك ادخل الكود");
            }
            if(phoneNumber.length() < 11){
                etMobileTeacher.setError("من فضلك ادخل 11 رقم");
            }
            if(dateEnrollment.isEmpty()){
                etEnrollmentTeacher.setError("من فضلك ادخل تاريخ الانضمام");
            }

        });

        btSalaryRating.setOnClickListener(v -> {
            Intent intentRatingSalary = new Intent(TeacherDetailsActivity.this,RatingSalaryActivity.class);
            intentRatingSalary.putExtra("codeTeacher",retrieveDataTeacher.getCodeTeacher());
            intentRatingSalary.putExtra("nameTeacher",retrieveDataTeacher.getNameTeacher());
            startActivity(intentRatingSalary);
        });

        layoutEnrollmentTeacher.setStartIconOnClickListener(v -> showDatePickerDialog());
    }

    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialogBorn = new DatePickerDialog(
                this ,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialogBorn.show();
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date = dayOfMonth + " - " + (month + 1) + " - " + year;
        etEnrollmentTeacher.setText(date);
    }

    void openWhatsappContact(String number) {

        try {

            Intent i = new Intent(Intent.ACTION_VIEW);
            String url = "https://api.whatsapp.com/send?phone="+"+2"+number;
            i.setData(Uri.parse(url));
            i.setPackage("com.whatsapp.w4b");
            startActivity(i);
        }catch (Exception e){
            Toast.makeText(TeacherDetailsActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}