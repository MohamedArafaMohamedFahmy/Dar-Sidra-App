package com.arafa.mohamed.darsidraapp.adapter;

import android.app.Dialog;
import android.content.Context;
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
import com.arafa.mohamed.darsidraapp.models.TeachersModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class SupervisorTeacherAdapter extends RecyclerView.Adapter<SupervisorTeacherAdapter.MyViewHolder> {

    Context context;
    ArrayList<TeachersModel> downloadData;
    DatabaseReference databaseReference;
    AppCompatButton btYes, btNo;
    AppCompatTextView tvMessage;
    String idAdmin;


    public SupervisorTeacherAdapter(Context context, ArrayList<TeachersModel> downloadData, String idAdmin) {
        this.context = context;
        this.downloadData = downloadData;
        this.idAdmin = idAdmin;
    }

    @NonNull
    @Override
    public SupervisorTeacherAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_supervisor_teacher, parent, false);
        return new SupervisorTeacherAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SupervisorTeacherAdapter.MyViewHolder holder, int position) {
        databaseReference = FirebaseDatabase.getInstance().getReference();

        holder.tvNameTeacher.setText(downloadData.get(position).getNameTeacher());
        holder.tvCodeTeacher.setText(downloadData.get(position).getCodeTeacher());

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
        AppCompatTextView tvNameTeacher, tvCodeTeacher;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameTeacher = itemView.findViewById(R.id.text_name_teacher);
            tvCodeTeacher = itemView.findViewById(R.id.text_code_teacher);

        }
    }

    public void showCustomDialog(int position) {

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
            databaseReference.child("SupervisorTeacher").child(idAdmin).child(downloadData.get(position).getCodeTeacher()).removeValue().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    dialog.dismiss();
                    Toast.makeText(context, "تم الحذف بنجاح", Toast.LENGTH_SHORT).show();
                }
            });
        });

        btNo.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}
