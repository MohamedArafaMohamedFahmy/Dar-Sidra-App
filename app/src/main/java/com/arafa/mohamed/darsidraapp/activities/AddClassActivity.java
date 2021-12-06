package com.arafa.mohamed.darsidraapp.activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.models.BranchModel;
import com.arafa.mohamed.darsidraapp.models.ClassModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Objects;

public class AddClassActivity extends AppCompatActivity {

    TextInputEditText etDays, etTiming, etNameClass;
    AppCompatButton btAddClass;
    AppCompatTextView tvToolbar;
    AppCompatImageButton btBackArrow;
    DatabaseReference databaseReference;
    String days, timing, idClass, nameClass ;
    int radioButtonID,retrieveID;
    ClassModel classModel, retrieveDataClass;
    RadioGroup rgSelected;
    AppCompatRadioButton rbChooseStatus;
    Bundle extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        etDays = findViewById(R.id.editText_days);
        etTiming = findViewById(R.id.editText_timing);
        etNameClass = findViewById(R.id.editText_name_class);
        btAddClass = findViewById(R.id.button_add_class);
        rgSelected = findViewById(R.id.group_selected);
        btBackArrow = findViewById(R.id.button_back_arrow);
        tvToolbar = findViewById(R.id.text_toolbar);
        extra = getIntent().getExtras();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        final Object objDetailed = getIntent().getSerializableExtra("dataClass");
        retrieveDataClass = (ClassModel) objDetailed;

        if (retrieveDataClass != null){
            etNameClass.setText(retrieveDataClass.getNameClass());
            etTiming.setText(retrieveDataClass.getTiming());
            etDays.setText(retrieveDataClass.getDays());
            if (retrieveDataClass.getStatus().equals("مغلق")){

                rbChooseStatus = findViewById(R.id.radio_button_close);
                rbChooseStatus.setChecked(true);
            }
            if (retrieveDataClass.getStatus().equals("مفتوح")){

                rbChooseStatus = findViewById(R.id.radio_button_open);
                rbChooseStatus.setChecked(true);
            }
            btAddClass.setText("تعديل الفصل");
        }

        tvToolbar.setText(R.string.add_class_appbar);
        btBackArrow.setOnClickListener(v -> finish());

        btAddClass.setOnClickListener(v -> {
            days = Objects.requireNonNull(etDays.getText()).toString();
            timing = Objects.requireNonNull(etTiming.getText()).toString();
            nameClass = Objects.requireNonNull(etNameClass.getText()).toString();
            radioButtonID = rgSelected.getCheckedRadioButtonId();
            rbChooseStatus = findViewById(radioButtonID);
            retrieveID = rbChooseStatus.getId();

            if (!days.isEmpty() && !timing.isEmpty() && !nameClass.isEmpty() && retrieveDataClass == null) {
                if (retrieveID == R.id.radio_button_open) {
                    idClass = databaseReference.push().getKey();
                    classModel = new ClassModel(days, timing, nameClass, idClass,rbChooseStatus.getText().toString() );
                    databaseReference.child("Classes").child(extra.getString("idBranch")).child(idClass).setValue(classModel).addOnCompleteListener(task -> Toast.makeText(AddClassActivity.this, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show());

                }else if(retrieveID == R.id.radio_button_close){
                    idClass = databaseReference.push().getKey();
                    classModel = new ClassModel(days, timing, nameClass, idClass,rbChooseStatus.getText().toString() );
                    databaseReference.child("Classes").child(extra.getString("idBranch")).child(idClass).setValue(classModel).addOnCompleteListener(task -> Toast.makeText(AddClassActivity.this, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show());
                }
            }

            if (!days.isEmpty() && !timing.isEmpty() && !nameClass.isEmpty() && retrieveDataClass != null) {
                if (retrieveID == R.id.radio_button_open) {
                    idClass = retrieveDataClass.getIdClass();
                    classModel = new ClassModel(days, timing, nameClass, idClass,rbChooseStatus.getText().toString() );
                    databaseReference.child("Classes").child(extra.getString("idBranch")).child(idClass).setValue(classModel).addOnCompleteListener(task -> Toast.makeText(AddClassActivity.this, "تم التعديل بنجاح", Toast.LENGTH_SHORT).show());

                }else if(retrieveID == R.id.radio_button_close){
                    idClass = retrieveDataClass.getIdClass();
                    classModel = new ClassModel(days, timing, nameClass, idClass,rbChooseStatus.getText().toString() );
                    databaseReference.child("Classes").child(extra.getString("idBranch")).child(idClass).setValue(classModel).addOnCompleteListener(task -> Toast.makeText(AddClassActivity.this, "تم التعديل بنجاح", Toast.LENGTH_SHORT).show());
                }
            }

            if (days.isEmpty()){
                etDays.setError("من فضلك ادخل الايام");
            }
            if (days.isEmpty()){
                etTiming.setError("من فضلك ادخل التوقيت");
            }
            if (days.isEmpty()){
                etNameClass.setError("من فضلك ادخل اسم الفصل");
            }
        });
    }
}