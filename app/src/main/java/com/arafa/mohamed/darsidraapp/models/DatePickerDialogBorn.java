package com.arafa.mohamed.darsidraapp.models;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class DatePickerDialogBorn implements DatePickerDialog.OnDateSetListener {
    public  String bornDateStudent;
    TextInputEditText etBornDate;
    Context context;

    public DatePickerDialogBorn(Context context, TextInputEditText etBornDate) {
        this.context = context;
        this.etBornDate = etBornDate;
    }

    public void showDatePickerDialogBorn() {
        DatePickerDialog datePickerDialogBorn = new DatePickerDialog(
                 context ,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialogBorn.show();
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        bornDateStudent = dayOfMonth + " - " + (month + 1) + " - " + year;
        etBornDate.setText(bornDateStudent);
    }
}
