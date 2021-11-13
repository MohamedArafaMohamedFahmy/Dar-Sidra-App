package com.arafa.mohamed.darsidraapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.models.StudentModel;
import java.net.URLEncoder;
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
        holder.tvMobileFather.setText(downloadData.get(position).getMobileFather());
        holder.tvMobileMother.setText(downloadData.get(position).getMobileMother());

        holder.btWhatsFather.setOnClickListener(v -> openWhatsapp(downloadData.get(position).getMobileFather(),position));

        holder.btWhatsMother.setOnClickListener(v -> openWhatsapp(downloadData.get(position).getMobileMother(),position));



    }

    @Override
    public int getItemCount() {
        return downloadData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvNameStudent,tvCodeStudent,tvClassStudent,tvMobileFather,tvMobileMother;
        AppCompatImageButton btWhatsFather, btWhatsMother;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameStudent = itemView.findViewById(R.id.text_name_students);
            tvCodeStudent = itemView.findViewById(R.id.text_code_students);
            tvClassStudent = itemView.findViewById(R.id.text_class_student);
            tvMobileFather = itemView.findViewById(R.id.text_mobile_father);
            tvMobileMother = itemView.findViewById(R.id.text_mobile_mother);
            btWhatsFather = itemView.findViewById(R.id.whats_father);
            btWhatsMother = itemView.findViewById(R.id.whats_mother);

        }
    }

    void openWhatsapp(String number, int position) {
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent i = new Intent(Intent.ACTION_VIEW);
            String url = "https://api.whatsapp.com/send?phone="+"+2"+number+"&text=" + URLEncoder.encode(downloadData.get(position).getCodeStudent()+"\n"+context.getResources().getString(R.string.message_payment_whatsapp), "UTF-8");
            i.setPackage("com.whatsapp");
            i.setData(Uri.parse(url));
            if (i.resolveActivity(packageManager) != null) {
                context.startActivity(i);
            }
        } catch (Exception e){
            Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
