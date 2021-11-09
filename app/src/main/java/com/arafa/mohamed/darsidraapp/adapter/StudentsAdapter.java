package com.arafa.mohamed.darsidraapp.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.MyViewHolder> implements Filterable {
    Context context;
    ArrayList<StudentModel> downloadData;
    ArrayList<StudentModel> dataListFilter;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    AppCompatButton btYes,btNo;
    AppCompatTextView tvMessage;


    public StudentsAdapter(Context context, ArrayList<StudentModel> downloadData) {
        this.context = context;
        this.downloadData = downloadData;
        dataListFilter = new ArrayList<>(downloadData);
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
        holder.tvClassStudent.setText(downloadData.get(position).getClassStudent());
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

    @Override
    public Filter getFilter() {
        return patientFilter;
    }

     private  Filter patientFilter = new Filter() {
         @Override
         protected FilterResults performFiltering(CharSequence constraint) {
             ArrayList<StudentModel> filteredList = new ArrayList<>();
             if (constraint == null || constraint.length() == 0) {
                 filteredList.addAll(dataListFilter);
             } else {
                 String filterPattern = constraint.toString().toLowerCase().trim();
                 for (StudentModel item : dataListFilter) {
                     if (item.getCodeStudent().toLowerCase().contains(filterPattern)) {
                         filteredList.add(item);
                     }
                     if (item.getClassStudent().toLowerCase().contains(filterPattern)) {
                         filteredList.add(item);
                     }
                 }
             }
             FilterResults results = new FilterResults();
             results.values = filteredList;
             return results;
         }
         @SuppressWarnings("unchecked")
         @Override
         protected void publishResults(CharSequence constraint, FilterResults results) {
             downloadData.clear();
             downloadData.addAll((Collection<? extends StudentModel>) results.values);
             notifyDataSetChanged();
         }
     };

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvNameStudent,tvCodeStudent,tvClassStudent;
        AppCompatImageView imgStudent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameStudent = itemView.findViewById(R.id.text_name_students);
            tvCodeStudent = itemView.findViewById(R.id.text_code_students);
            tvClassStudent = itemView.findViewById(R.id.text_class_student);
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
        tvMessage = dialog.findViewById(R.id.text_message);

        tvMessage.setText(R.string.do_you_want_to_delete_this_student);
        btYes.setOnClickListener(v -> {

            storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(downloadData.get(position).getUrlStudent());
            storageReference.delete().addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    databaseReference.child("Rating").child(downloadData.get(position).getCodeStudent()).removeValue();
                    databaseReference.child("Subscription").child(downloadData.get(position).getCodeStudent()).removeValue();
                    databaseReference.child("StudentsData").child(downloadData.get(position).getCodeStudent()).removeValue().addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            Toast.makeText(context, "تم الحذف بنجاح", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, ""+ Objects.requireNonNull(task1.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }else {
                    Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }

            });

            dialog.dismiss();
        });

        btNo.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}
