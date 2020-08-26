package com.example.testapp.holders;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.R;

public class DataListViewHolder extends RecyclerView.ViewHolder
{
    public CheckBox checkBox;
    public TextView tvName;
    public TextView tvPrice;

    public DataListViewHolder(@NonNull View itemView)
    {
        super(itemView);

        checkBox = itemView.findViewById(R.id.checkbox);
        tvName = itemView.findViewById(R.id.tv_name);
        tvPrice = itemView.findViewById(R.id.tv_price);
    }
}
