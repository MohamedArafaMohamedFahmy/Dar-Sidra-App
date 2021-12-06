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
import com.arafa.mohamed.darsidraapp.activities.ViewClassesActivity;
import com.arafa.mohamed.darsidraapp.models.BranchModel;
import com.arafa.mohamed.darsidraapp.models.ReservationModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class ReservationAdapter  extends RecyclerView.Adapter<ReservationAdapter.MyViewHolder> {
    Context context;
    ArrayList<ReservationModel> downloadData;
    DatabaseReference databaseReference;
    AppCompatButton btYes,btNo;
    AppCompatTextView tvMessage;


    public ReservationAdapter(Context context, ArrayList<ReservationModel> downloadData ){
        this.context=context;
        this.downloadData=downloadData;
    }

    @NonNull
    @Override
    public ReservationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_reservations, parent, false);
        return new ReservationAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationAdapter.MyViewHolder holder, int position) {
        databaseReference = FirebaseDatabase.getInstance().getReference();

        holder.tvNamePerson.setText(downloadData.get(position).getNamePerson());
        holder.tvMobileNumber.setText(downloadData.get(position).getMobileNumber());
        holder.tvDays.setText(downloadData.get(position).getDays());
        holder.tvTiming.setText(downloadData.get(position).getTiming());
        holder.tvNameClass.setText(downloadData.get(position).getNameClass());

        holder.itemView.setOnLongClickListener(v -> {
            showCustomDialog(position);
            return false;
        });

    }

    @Override
    public int getItemCount () {

        return downloadData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvNamePerson, tvMobileNumber, tvDays, tvTiming, tvNameClass;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamePerson = itemView.findViewById(R.id.text_name_person);
            tvMobileNumber = itemView.findViewById(R.id.text_mobile_number);
            tvDays = itemView.findViewById(R.id.text_days);
            tvTiming = itemView.findViewById(R.id.text_timing);
            tvNameClass = itemView.findViewById(R.id.text_name_class);
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

        tvMessage.setText(R.string.do_you_want_to_delete_this_reservation);
        btYes.setOnClickListener(v -> {
            databaseReference.child("Reservations").child(downloadData.get(position).getIdReservation()).removeValue().addOnCompleteListener(task -> {
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
