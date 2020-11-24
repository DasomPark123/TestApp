package com.example.testapp.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.R;
import com.example.testapp.VO.Fruits;

import java.util.ArrayList;
import java.util.List;

public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.DataListViewHolder>
{
    private ArrayList<Fruits> itemList;
    private DataListViewHolder viewHolder;

    public DataListAdapter(ArrayList<Fruits> itemList)
    {
        this.itemList = itemList;
    }

    public void setList(List<Fruits> list)
    {
        itemList.clear();
        itemList.addAll(list);
    }

    public void setCheckboxVisibility(boolean isCheck)
    {

        viewHolder.setCheckBoxVisibility(isCheck);
    }

    @NonNull
    @Override
    public DataListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item, parent, false);
        viewHolder = new DataListViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataListViewHolder holder, int position)
    {
        Fruits fruits = itemList.get(position);
        holder.checkBox.setChecked(fruits.isCheck());
        holder.tvName.setText(fruits.getName());
        holder.tvPrice.setText(fruits.getPrice());
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
            checkBox.setVisibility(View.INVISIBLE);
        }

        public void setCheckBoxVisibility(boolean isCheck)
        {
            if(isCheck)
                checkBox.setVisibility(View.VISIBLE);
            else
                checkBox.setVisibility(View.INVISIBLE);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("ViewHolder", "onClick");
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION)
                {
                    if(view.getId() == R.id.checkbox)
                    {
                        Log.d("ViewHolder", "itemClickListener position: " + position);
                        if(itemList.get(position).isCheck())
                        {
                            itemList.get(position).setCheck(false);
                            Log.d("ViewHolder", itemList.get(position).isCheck() ?
                                    "checkbox : true" : "checkbox : false");
                        }
                        else
                        {
                            itemList.get(position).setCheck(true);
                            Log.d("ViewHolder", itemList.get(position).isCheck() ?
                                    "checkbox : true" : "checkbox : false");
                        }
                    }
                }
            }
        };
    }
}
