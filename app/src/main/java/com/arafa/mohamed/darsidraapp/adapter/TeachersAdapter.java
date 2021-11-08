package com.arafa.mohamed.darsidraapp.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.activities.StudentDetailsActivity;
import com.arafa.mohamed.darsidraapp.activities.TeacherDetailsActivity;
import com.arafa.mohamed.darsidraapp.models.TeachersModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class TeachersAdapter extends RecyclerView.Adapter<TeachersAdapter.MyViewHolder> implements Filterable {
    Context context;
    ArrayList<TeachersModel> downloadData;
    ArrayList<TeachersModel> dataListFilter;
    DatabaseReference databaseReference;
    AppCompatButton btYes,btNo;
    AppCompatTextView tvMessage;


    public TeachersAdapter(Context context, ArrayList<TeachersModel> downloadData) {
        this.context = context;
        this.downloadData = downloadData;
        dataListFilter = new ArrayList<>(downloadData);
    }

    @NonNull
    @Override
    public TeachersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.recycler_teachers,parent,false);
        return new TeachersAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  TeachersAdapter.MyViewHolder holder, int position) {
        databaseReference = FirebaseDatabase.getInstance().getReference();

        holder.tvNameTeacher.setText(downloadData.get(position).getNameTeacher());
        holder.tvCodeTeacher.setText(downloadData.get(position).getCodeTeacher());
        holder.tvPhoneNumber.setText(downloadData.get(position).getPhoneNumber());
        holder.itemView.setOnClickListener(v -> {
            Intent intentDetailed = new Intent(context, TeacherDetailsActivity.class);
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
            ArrayList<TeachersModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(dataListFilter);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (TeachersModel item : dataListFilter) {
                    if (item.getCodeTeacher().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                    if (item.getNameTeacher().toLowerCase().contains(filterPattern)) {
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
            downloadData.addAll((Collection<? extends TeachersModel>) results.values);
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvNameTeacher,tvCodeTeacher,tvPhoneNumber;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameTeacher = itemView.findViewById(R.id.text_name_teacher);
            tvCodeTeacher = itemView.findViewById(R.id.text_code_teacher);
            tvPhoneNumber = itemView.findViewById(R.id.text_phone_number);
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

        tvMessage.setText(R.string.do_you_want_to_delete_this_teacher);
        btYes.setOnClickListener(v -> {


                    databaseReference.child("TeachersData").child(downloadData.get(position).getCodeTeacher()).removeValue().addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            databaseReference.child("RatingTeachers").child(downloadData.get(position).getCodeTeacher()).removeValue();
                            databaseReference.child("SalaryTeachers").child(downloadData.get(position).getCodeTeacher()).removeValue();
                            Toast.makeText(context, "تم الحذف بنجاح", Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(context, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    });

            dialog.dismiss();
        });

        btNo.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

}
