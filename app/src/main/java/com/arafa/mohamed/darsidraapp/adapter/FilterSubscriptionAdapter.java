package com.arafa.mohamed.darsidraapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.activities.StudentDetailsActivity;
import com.arafa.mohamed.darsidraapp.models.StudentModel;
import java.util.ArrayList;

public class FilterSubscriptionAdapter extends RecyclerView.Adapter<FilterSubscriptionAdapter.MyViewHolder> {

    Context context;
    ArrayList<StudentModel> downloadData;

    public FilterSubscriptionAdapter (Context context, ArrayList<StudentModel> downloadData) {
        this.context = context;
        this.downloadData = downloadData;
    }

    @NonNull
    @Override
    public FilterSubscriptionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.recycler_filter_student,parent,false);
        return new FilterSubscriptionAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  FilterSubscriptionAdapter.MyViewHolder holder, int position) {

        holder.tvNameStudent.setText(downloadData.get(position).getNameStudent());
        holder.tvCodeStudent.setText(downloadData.get(position).getCodeStudent());
        holder.tvClassStudent.setText(downloadData.get(position).getClassStudent());
        holder.itemView.setOnClickListener(v -> {
            Intent intentDetailed = new Intent(context, StudentDetailsActivity.class);
            intentDetailed.putExtra("detailed",downloadData.get(position));
            context.startActivity(intentDetailed);
        });

    }

    @Override
    public int getItemCount() {
        return downloadData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvNameStudent,tvCodeStudent,tvClassStudent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameStudent = itemView.findViewById(R.id.text_name_students);
            tvCodeStudent = itemView.findViewById(R.id.text_code_students);
            tvClassStudent = itemView.findViewById(R.id.text_class_student);

        }
    }

}
