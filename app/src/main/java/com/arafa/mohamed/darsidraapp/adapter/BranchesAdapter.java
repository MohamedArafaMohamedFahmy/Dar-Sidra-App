package com.arafa.mohamed.darsidraapp.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.activities.AddClassActivity;
import com.arafa.mohamed.darsidraapp.activities.ViewClassesActivity;
import com.arafa.mohamed.darsidraapp.models.BranchModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class BranchesAdapter extends RecyclerView.Adapter<BranchesAdapter.MyViewHolder> {
    Context context;
    ArrayList<BranchModel> downloadData;
    DatabaseReference databaseReference;
    AppCompatButton btYes,btNo;
    AppCompatTextView tvMessage;


    public BranchesAdapter(Context context, ArrayList<BranchModel> downloadData ){
        this.context=context;
        this.downloadData=downloadData;
    }

    @NonNull
    @Override
    public BranchesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_branch, parent, false);
        return new BranchesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BranchesAdapter.MyViewHolder holder, int position) {
        databaseReference = FirebaseDatabase.getInstance().getReference();

        holder.tvNameBranch.setText(downloadData.get(position).getNameBranch());

        holder.itemView.setOnLongClickListener(v -> {
            showCustomDialog(position);
            return false;
        });

        holder.itemView.setOnClickListener(v -> {
            Intent intentClasses = new Intent(context, ViewClassesActivity.class);
            intentClasses.putExtra("dataBranch",downloadData.get(position));
            context.startActivity(intentClasses);
        });
    }

    @Override
    public int getItemCount () {

        return downloadData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvNameBranch;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameBranch = itemView.findViewById(R.id.text_name_branch);
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

        tvMessage.setText(R.string.do_you_want_to_delete_this_branch);
        btYes.setOnClickListener(v -> {
            databaseReference.child("Classes").child(downloadData.get(position).getIdBranch()).removeValue();
            databaseReference.child("Branches").child(downloadData.get(position).getIdBranch()).removeValue().addOnCompleteListener(task -> {
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
