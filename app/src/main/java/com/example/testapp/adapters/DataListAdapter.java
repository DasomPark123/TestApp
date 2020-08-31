package com.example.testapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.R;
import com.example.testapp.VO.DataItem;

import java.util.ArrayList;

public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.DataListViewHolder>
{
    private ArrayList<DataItem> itemList;

    public DataListAdapter(ArrayList<DataItem> itemList)
    {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public DataListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item,parent,false);
        DataListViewHolder viewHolder = new DataListViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataListViewHolder holder, int position)
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

    public class DataListViewHolder extends RecyclerView.ViewHolder
    {
        private CheckBox checkBox;
        private TextView tvName;
        private TextView tvPrice;

        public DataListViewHolder(@NonNull View itemView)
        {
            super(itemView);

            checkBox = itemView.findViewById(R.id.checkbox);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);

            checkBox.setOnClickListener(onClickListener);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("ViewHolder","onClick");
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION)
                {
                    if(view.getId() == R.id.checkbox)
                    {
                        Log.d("ViewHolder","itemClickListener position: " + position);
                        if(itemList.get(position).isCheck())
                        {
                            itemList.get(position).setCheck(false);
                            Log.d("ViewHolder",itemList.get(position).isCheck() ?
                                    "checkbox : true" : "checkbox : false");
                        }
                        else
                        {
                            itemList.get(position).setCheck(true);
                            Log.d("ViewHolder",itemList.get(position).isCheck() ?
                                    "checkbox : true" : "checkbox : false");
                        }
                    }
                }
            }
        };
    }
}
