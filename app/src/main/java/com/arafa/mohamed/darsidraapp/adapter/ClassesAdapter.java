package com.arafa.mohamed.darsidraapp.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.activities.AddClassActivity;
import com.arafa.mohamed.darsidraapp.models.ClassModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ClassesAdapter  extends RecyclerView.Adapter<ClassesAdapter.MyViewHolder> {
    Context context;
    ArrayList<ClassModel> downloadData;
    DatabaseReference databaseReference;
    AppCompatButton btYes,btNo;
    AppCompatTextView tvMessage;
    String idBranch;


    public ClassesAdapter(Context context, ArrayList<ClassModel> downloadData, String idBranch ){
        this.context=context;
        this.downloadData=downloadData;
        this.idBranch = idBranch;
    }

    @NonNull
    @Override
    public ClassesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_classes, parent, false);
        return new ClassesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassesAdapter.MyViewHolder holder, int position) {
        databaseReference = FirebaseDatabase.getInstance().getReference();


        if (downloadData.get(position).getStatus().equals("مغلق")){
            holder.imgStatus.setImageResource(R.drawable.close);
            holder.tvNameClass.setText(downloadData.get(position).getNameClass());
            holder.tvDays.setText(downloadData.get(position).getDays());
            holder.tvTiming.setText(downloadData.get(position).getTiming());
        }
        if (downloadData.get(position).getStatus().equals("مفتوح")){
            holder.imgStatus.setImageResource(R.drawable.open);
            holder.tvNameClass.setText(downloadData.get(position).getNameClass());
            holder.tvDays.setText(downloadData.get(position).getDays());
            holder.tvTiming.setText(downloadData.get(position).getTiming());
        }

        holder.itemView.setOnLongClickListener(v -> {
            showCustomDialog(position);
            return false;
        });

        holder.itemView.setOnClickListener(v -> {
            Intent intentClasses = new Intent(context, AddClassActivity.class);
            intentClasses.putExtra("idBranch",idBranch);
            intentClasses.putExtra("dataClass",downloadData.get(position));
            context.startActivity(intentClasses);
        });
    }

    @Override
    public int getItemCount () {

        return downloadData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvNameClass, tvDays, tvTiming;
        AppCompatImageView imgStatus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameClass = itemView.findViewById(R.id.text_name_class);
            tvDays = itemView.findViewById(R.id.text_days);
            tvTiming = itemView.findViewById(R.id.text_timing);
            imgStatus = itemView.findViewById(R.id.image_status);
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

        tvMessage.setText(R.string.do_you_want_to_delete_this_class);
        btYes.setOnClickListener(v -> {
            databaseReference.child("Classes").child(idBranch).child(downloadData.get(position).getIdClass()).removeValue().addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    dialog.dismiss();
                    Toast.makeText(context, "تم الحذف بنجاح", Toast.LENGTH_SHORT).show();
                }
            });
        });

        btNo.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}
