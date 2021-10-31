package com.arafa.mohamed.darsidraapp.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.arafa.mohamed.darsidraapp.R;
import com.arafa.mohamed.darsidraapp.models.MonthModel;
import com.arafa.mohamed.darsidraapp.models.SubscriptionModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MonthAdapter implements SpinnerAdapter {
    Context context;
    ArrayList<MonthModel> listMonth;

    public MonthAdapter(Context context, ArrayList<MonthModel> listMonth) {
        this.context = context;
        this.listMonth = listMonth;
    }



    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = new MyViewHolder();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.item_spinner,parent,false);
        myViewHolder.tvName = convertView.findViewById(R.id.text_month);

        myViewHolder.tvName.setText(listMonth.get(position).nameMonth);
        return convertView;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return listMonth != null ? listMonth.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = new MyViewHolder();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView =  layoutInflater.inflate(R.layout.item_spinner,parent,false);
        myViewHolder.tvName = convertView.findViewById(R.id.text_month);

        myViewHolder.tvName.setText(listMonth.get(position).nameMonth);
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public class MyViewHolder {
        AppCompatTextView tvName;

        public MyViewHolder() {
        }
    }
}
