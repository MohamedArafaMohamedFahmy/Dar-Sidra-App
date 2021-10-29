package com.arafa.mohamed.darsidraapp.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.MimeTypeMap;
import android.widget.Toast;
import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.models.StudentModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import java.util.Objects;

public class StudentDetailsActivity extends AppCompatActivity {

    AppCompatTextView tvToolbar;
    AppCompatImageButton btBackArrow,btSubRating;
    TextInputEditText etNameStudent,etEnrollmentStudent,etCodeStudent,etMobileFather,etMobileMother,etClassStudent,etDateSession;
    AppCompatButton btRegisterData;
    AppCompatImageView imgStudent;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    StudentModel studentModel, retrieveDataStudent ;
    public Uri imgUri;
    String nameStudent, enrollmentStudent, codeStudent, mobileFather, mobileMother, classStudent, dateSession, urlStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        btBackArrow = findViewById(R.id.button_back_arrow);
        btSubRating = findViewById(R.id.button_subscription_rating);
        tvToolbar = findViewById(R.id.text_toolbar);
        etNameStudent = findViewById(R.id.editText_name_student);
        etEnrollmentStudent = findViewById(R.id.editText_date_student);
        etCodeStudent = findViewById(R.id.editText_code_student);
        etMobileFather = findViewById(R.id.editText_mobile_father);
        etMobileMother = findViewById(R.id.editText_mobile_mother);
        etClassStudent = findViewById(R.id.editText_class_student);
        etDateSession = findViewById(R.id.editText_date_session_student);
        btRegisterData = findViewById(R.id.button_submit);
        imgStudent = findViewById(R.id.image_student_details);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();

        tvToolbar.setText(R.string.student_details_appbar);
        btBackArrow.setOnClickListener(v -> finish());
        imgStudent.setOnClickListener(v -> imageChooser());

        final Object objDetailed = getIntent().getSerializableExtra("detailed");
        retrieveDataStudent = (StudentModel) objDetailed;

        if(retrieveDataStudent != null){

            btSubRating.setVisibility(View.VISIBLE);
            Picasso.get().load(Uri.parse(retrieveDataStudent.getUrlStudent())).into(imgStudent);
            etNameStudent.setText(retrieveDataStudent.getNameStudent());
            etEnrollmentStudent.setText(retrieveDataStudent.getEnrollmentStudent());
            etCodeStudent.setText(retrieveDataStudent.getCodeStudent());
            etMobileFather.setText(retrieveDataStudent.getMobileFather());
            etMobileMother.setText(retrieveDataStudent.getMobileMother());
            etClassStudent.setText(retrieveDataStudent.getClassStudent());
            etDateSession.setText(retrieveDataStudent.getDateSession());
        }

        btSubRating.setOnClickListener(v -> startActivity(new Intent(StudentDetailsActivity.this,RatingSubscriptionDetailsActivity.class)));

        btRegisterData.setOnClickListener(v -> {
            nameStudent = Objects.requireNonNull(etNameStudent.getText()).toString();
            enrollmentStudent = Objects.requireNonNull(etEnrollmentStudent.getText()).toString();
            codeStudent = Objects.requireNonNull(etCodeStudent.getText()).toString();
            mobileFather = Objects.requireNonNull(etMobileFather.getText()).toString();
            mobileMother = Objects.requireNonNull(etMobileMother.getText()).toString();
            classStudent = Objects.requireNonNull(etClassStudent.getText()).toString();
            dateSession = Objects.requireNonNull(etDateSession.getText()).toString();

            if(!nameStudent.isEmpty() && !enrollmentStudent.isEmpty() && !codeStudent.isEmpty() &&
                    mobileFather.length() == 11 && mobileMother.length() == 11 &&
                    !classStudent.isEmpty() && !dateSession.isEmpty() && imgUri != null ){

              StorageReference filePath = storageReference.child("UploadImages").child(codeStudent + "." + getFileExtension(imgUri));
                final UploadTask uploadTaskFather = filePath.putFile(imgUri);
                uploadTaskFather.continueWithTask(task -> {
                    if (!task.isSuccessful()) {
                        Toast.makeText(StudentDetailsActivity.this, "" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                    }
                    urlStudent = filePath.getDownloadUrl().toString();
                    return filePath.getDownloadUrl();
                }).addOnCompleteListener(task -> {
                    urlStudent = Objects.requireNonNull(task.getResult()).toString();
                    studentModel = new StudentModel(nameStudent, enrollmentStudent, codeStudent, mobileFather,
                            mobileMother, classStudent, dateSession, urlStudent);
                    databaseReference.child("StudentsData").child(codeStudent).setValue(studentModel).addOnCompleteListener(task1 -> {
                       if (task1.isSuccessful()){
                           Toast.makeText(this, "تم التسجيل بنجاح", Toast.LENGTH_LONG).show();
                       }else{
                           Toast.makeText(this, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                       }
                    });
                });
            }

            if (nameStudent.isEmpty()){
                etNameStudent.setError("من فضلك ادخل اسم الطالب");
            }
            if (enrollmentStudent.isEmpty()){
                etEnrollmentStudent.setError("من فضلك ادخل تاريخ الالتحاق");
            }
            if (codeStudent.isEmpty()){
                etCodeStudent.setError("من فضلك ادخل كود الطالب");
            }
            if (mobileFather.isEmpty()){
                etMobileFather.setError("من فضلك ادخل تليفون الاب");
            }
            if (mobileMother.isEmpty()){
                etMobileMother.setError("من فضلك ادخل تليفون الام");
            }
            if (classStudent.isEmpty()){
                etClassStudent.setError("من فضلك ادخل فصل الطالب");
            }
            if (dateSession.isEmpty()){
                etDateSession.setError("من فضلك ادخل ميعاد الطالب");
            }
        });
    }

    public void imageChooser() {
        Intent intentImage = new Intent();
        intentImage.setType("image/*");
        intentImage.setAction(Intent.ACTION_GET_CONTENT);
        someActivityResultLauncher.launch(intentImage);
    }

    public String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null && data.getData() != null) {
                            imgUri = data.getData();
                            Picasso.get().load(imgUri).into(imgStudent);
                        }
                    }
                }
            });
}