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
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.activities.AddTeachersUnderSupervisorActivity;
import com.arafa.mohamed.darsidraapp.models.AdminModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.Objects;

public class AdminsAdapter extends RecyclerView.Adapter<AdminsAdapter.MyViewHolder> {
    Context context;
    ArrayList<AdminModel> downloadData;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    AppCompatButton btYes,btNo;
    AppCompatTextView tvMessage;


    public AdminsAdapter(Context context, ArrayList<AdminModel> downloadData ){
        this.context=context;
        this.downloadData=downloadData;
    }

    @NonNull
    @Override
    public AdminsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_admin, parent, false);
        return new AdminsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminsAdapter.MyViewHolder holder, int position) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        holder.tvAdminName.setText(downloadData.get(position).getNameAdmin());
        holder.tvAdminEmail.setText(downloadData.get(position).getEmailAdmin());

        holder.itemView.setOnClickListener(v -> {
            if(Objects.equals(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getEmail(), "sedra4quran@gmail.com")) {
                Intent intentAddTeachers = new Intent(context, AddTeachersUnderSupervisorActivity.class);
                intentAddTeachers.putExtra("adminData", downloadData.get(position));
                context.startActivity(intentAddTeachers);
            }else {
                Toast.makeText(context, "لا يمكن لهذا الحساب بالدخول", Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            if(Objects.equals(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getEmail(), "sedra4quran@gmail.com")) {
                showCustomDialog(position);
            }else {
                Toast.makeText(context, "لا يمكن لهذا الحساب بحذف المشرف", Toast.LENGTH_SHORT).show();

            }
            return false;
        });
    }

    @Override
    public int getItemCount () {

        return downloadData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView tvAdminName, tvAdminEmail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAdminName = itemView.findViewById(R.id.text_admin_name);
            tvAdminEmail = itemView.findViewById(R.id.text_admin_email);

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

        tvMessage.setText(R.string.do_you_want_to_delete_this_admin);
        btYes.setOnClickListener(v -> {
            databaseReference.child("SupervisorTeacher").child(downloadData.get(position).getIdAdmin()).removeValue().addOnCompleteListener(task1 -> {
                if (task1.isSuccessful()){
                    databaseReference.child("AdminsData").child(downloadData.get(position).getIdAdmin()).removeValue().addOnCompleteListener(task2 -> {
                        if(task2.isSuccessful()){
                            dialog.dismiss();
                            Toast.makeText(context, "تم الحذف بنجاح", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        });

        btNo.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}
