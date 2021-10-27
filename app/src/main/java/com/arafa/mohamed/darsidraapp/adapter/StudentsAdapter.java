package com.arafa.mohamed.darsidraapp.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.activities.StudentDetailsActivity;
import com.arafa.mohamed.darsidraapp.models.StudentModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.MyViewHolder> {
    Context context;
    ArrayList<StudentModel> downloadData;
    DatabaseReference databaseReference;
    AppCompatButton btYes,btNo;


    public StudentsAdapter(Context context, ArrayList<StudentModel> downloadData) {
        this.context = context;
        this.downloadData = downloadData;
    }

    @NonNull
    @Override
    public StudentsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.recycler_students,parent,false);
        return new StudentsAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  StudentsAdapter.MyViewHolder holder, int position) {
        databaseReference = FirebaseDatabase.getInstance().getReference();

        Picasso.get().load(Uri.parse(downloadData.get(position).getUrlStudent())).into(holder.imgStudent);
        holder.tvNameStudent.setText(downloadData.get(position).getNameStudent());
        holder.tvCodeStudent.setText(downloadData.get(position).getCodeStudent());
        holder.itemView.setOnClickListener(v -> {
            Intent intentDetailed = new Intent(context, StudentDetailsActivity.class);
            intentDetailed.putExtra("detailed",downloadData.get(position));
            context.startActivity(intentDetailed);
        });

        holder.itemView.setOnLongClickListener(v -> {
            showCustomDialog(position);
            return false;
        });

    }

    @Override
    public int getItemCount() {
        return downloadData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvNameStudent,tvCodeStudent;
        AppCompatImageView imgStudent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameStudent = itemView.findViewById(R.id.text_name_students);
            tvCodeStudent = itemView.findViewById(R.id.text_code_students);
            imgStudent = itemView.findViewById(R.id.image_student);
        }
    }

    public  void showCustomDialog(int position){

        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_delete);

        btYes = dialog.findViewById(R.id.button_yes);
        btNo = dialog.findViewById(R.id.button_no);

        btYes.setOnClickListener(v -> {
            dialog.dismiss();
        });

        btNo.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}
