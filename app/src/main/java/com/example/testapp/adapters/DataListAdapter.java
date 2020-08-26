package com.example.testapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.R;
import com.example.testapp.VO.DataItem;
import com.example.testapp.holders.DataListViewHolder;

import java.util.ArrayList;

public class DataListAdapter extends RecyclerView.Adapter<DataListViewHolder>
{
    private Context context;
    private ArrayList<DataItem> itemList;

    public DataListAdapter(ArrayList<DataItem> itemList)
    {
        //this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public DataListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        context = parent.getContext();

        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item,parent,false);
        DataListViewHolder viewHolder = new DataListViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.testapp.holders.DataListViewHolder holder, int position)
    {
        DataItem dataItem = itemList.get(position);
        holder.checkBox.setChecked(dataItem.isCheck());
        holder.tvName.setText(dataItem.getName());
        holder.tvPrice.setText(dataItem.getPrice());
    }


    @Override
    public int getItemCount()
    {
        return itemList.size();
    }
}
