package com.arafa.mohamed.darsidraapp.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.models.StudentModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudentDetailsActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    AppCompatTextView tvToolbar;
    AppCompatImageButton btBackArrow,btSubRating;
    TextInputEditText etNameStudent,etEnrollmentStudent,etCodeStudent,etMobileFather,etMobileMother,etClassStudent,etDateSession;
    TextInputLayout layoutEnrollmentStudent;
    AppCompatButton btRegisterData;
    AppCompatImageView imgStudent,imgQRCode;
    LinearLayout linearProgressBar;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    StudentModel studentModel, retrieveDataStudent ;
    public Uri imgUri;
    String nameStudent, enrollmentStudent, codeStudent, mobileFather, mobileMother, classStudent, dateSession, urlStudent, enrollmentDate;

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
        linearProgressBar = findViewById(R.id.linear_progress_bar);
        imgStudent = findViewById(R.id.image_student_details);
        imgQRCode = findViewById(R.id.image_qr);
        layoutEnrollmentStudent = findViewById(R.id.date_student_layout);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();

        tvToolbar.setText(R.string.student_details_appbar);
        btBackArrow.setOnClickListener(v -> finish());
        imgStudent.setOnClickListener(v -> imageChooser());

        final Object objDetailed = getIntent().getSerializableExtra("detailed");
        retrieveDataStudent = (StudentModel) objDetailed;

        if(retrieveDataStudent != null){

            btSubRating.setVisibility(View.VISIBLE);
            btRegisterData.setText("تحديث البيانات");
            Picasso.get().load(Uri.parse(retrieveDataStudent.getUrlStudent())).into(imgStudent);
            etNameStudent.setText(retrieveDataStudent.getNameStudent());
            etEnrollmentStudent.setText(retrieveDataStudent.getEnrollmentStudent());
            etCodeStudent.setText(retrieveDataStudent.getCodeStudent());
            etMobileFather.setText(retrieveDataStudent.getMobileFather());
            etMobileMother.setText(retrieveDataStudent.getMobileMother());
            etClassStudent.setText(retrieveDataStudent.getClassStudent());
            etDateSession.setText(retrieveDataStudent.getDateSession());
            generateQRCode(retrieveDataStudent.getCodeStudent());
        }


        btSubRating.setOnClickListener(v -> {
            Intent intentRatingSubscription = new Intent(StudentDetailsActivity.this,RatingSubscriptionDetailsActivity.class);
            intentRatingSubscription.putExtra("codeStudent",retrieveDataStudent.getCodeStudent());
            startActivity(intentRatingSubscription);
        });

        layoutEnrollmentStudent.setStartIconOnClickListener(v -> {
           showDatePickerDialog();
        });

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

                linearProgressBar.setVisibility(View.VISIBLE);
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
                           linearProgressBar.setVisibility(View.GONE);
                           Toast.makeText(this, "تم التسجيل بنجاح", Toast.LENGTH_LONG).show();
                       }else{
                           linearProgressBar.setVisibility(View.GONE);
                           Toast.makeText(this, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                       }
                    });
                });
            }

            if(!nameStudent.isEmpty() && !enrollmentStudent.isEmpty() && !codeStudent.isEmpty() &&
                    mobileFather.length() == 11 && mobileMother.length() == 11 &&
                    !classStudent.isEmpty() && !dateSession.isEmpty()  ){

                linearProgressBar.setVisibility(View.VISIBLE);

                    urlStudent = retrieveDataStudent.getUrlStudent();
                    studentModel = new StudentModel(nameStudent, enrollmentStudent, codeStudent, mobileFather,
                            mobileMother, classStudent, dateSession, urlStudent);
                    databaseReference.child("StudentsData").child(codeStudent).setValue(studentModel).addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()){
                            linearProgressBar.setVisibility(View.GONE);
                            Toast.makeText(this, "تم التحديث بنجاح", Toast.LENGTH_LONG).show();
                        }else{
                            linearProgressBar.setVisibility(View.GONE);
                            Toast.makeText(this, ""+ Objects.requireNonNull(task1.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
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

    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        enrollmentDate = dayOfMonth + " - " + (month + 1) + " - " + year;
        etEnrollmentStudent.setText(enrollmentDate);
    }

    public void generateQRCode(String text){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {

            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 1000, 1000);
            Bitmap bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.RGB_565);
            for (int x = 0; x < 1000; x++) {
                for (int y = 0; y < 1000; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.parseColor("#DBEFD6"));
                }
            }

            imgQRCode.setImageBitmap(bitmap);

        } catch (Exception e) {
            Toast.makeText(StudentDetailsActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

            handler.post(() -> {
                //UI Thread work here
            });
        });
    }


}