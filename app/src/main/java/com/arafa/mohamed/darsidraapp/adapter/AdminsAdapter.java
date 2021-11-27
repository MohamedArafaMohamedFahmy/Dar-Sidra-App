package com.arafa.mohamed.darsidraapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.models.AdminModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class AdminsAdapter extends RecyclerView.Adapter<AdminsAdapter.MyViewHolder> {
    Context context;
    ArrayList<AdminModel> downloadData;
    DatabaseReference databaseReference;


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

        holder.tvAdminName.setText(downloadData.get(position).getNameAdmin());
        holder.tvAdminEmail.setText(downloadData.get(position).getEmailAdmin());
    }

    @Override
    public int getItemCount () {

        return downloadData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvAdminName, tvAdminEmail;
        LinearLayout linearViewAdmin;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAdminName = itemView.findViewById(R.id.text_admin_name);
            tvAdminEmail = itemView.findViewById(R.id.text_admin_email);
            linearViewAdmin = itemView.findViewById(R.id.linear_view_admins);

        }
    }
}
