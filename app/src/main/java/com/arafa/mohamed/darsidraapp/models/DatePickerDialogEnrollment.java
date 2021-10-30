package com.arafa.mohamed.darsidraapp.models;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Calendar;

public class DatePickerDialogEnrollment  implements DatePickerDialog.OnDateSetListener {
    public String  enrollmentDate;
    public TextInputEditText  etEnrollmentStudent;
    Context context;

    public DatePickerDialogEnrollment(Context context, TextInputEditText  etEnrollmentStudent) {
        this.context = context;
        this. etEnrollmentStudent =  etEnrollmentStudent;
    }

    public void showDatePickerDialogEnrollment() {
        DatePickerDialog datePickerDialogEnrollment = new DatePickerDialog(
                context ,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialogEnrollment.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        enrollmentDate = dayOfMonth + " - " + (month + 1) + " - " + year;
        etEnrollmentStudent.setText(enrollmentDate);
    }
}
