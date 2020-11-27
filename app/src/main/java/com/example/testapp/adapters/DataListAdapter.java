package com.example.testapp.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.R;
import com.example.testapp.entity.Fruits;
import com.example.testapp.databinding.DataItemBinding;

import java.util.ArrayList;
import java.util.List;

public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.DataListViewHolder>
{
    private ArrayList<Fruits> itemList;
    private boolean isCheckVisible;
    private OnItemClickListener listener;

    public DataListAdapter(ArrayList<Fruits> itemList)
    {
        this.itemList = itemList;
    }

    public void setList(List<Fruits> itemList)
    {
        this.itemList.clear();
        this.itemList.addAll(itemList);
        notifyDataSetChanged();
    }

    public void setCheckboxVisibility(boolean isCheck)
    {
        isCheckVisible = isCheck;
    }

    @NonNull
    @Override
    public DataListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        DataItemBinding dataItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.data_item, parent, false);
        return new DataListViewHolder(dataItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DataListViewHolder holder, int position)
    {
        Fruits currentFruits = itemList.get(position);
        holder.dataItemBinding.setFruits(currentFruits);

        if(isCheckVisible)
            holder.dataItemBinding.checkbox.setVisibility(View.VISIBLE);
        else
            holder.dataItemBinding.checkbox.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount()
    {
        return itemList.size();
    }

    private Fruits getCurrentItemAt(int position)
    {
        return itemList.get(position);
    }

    public class DataListViewHolder extends RecyclerView.ViewHolder
    {
        private DataItemBinding dataItemBinding;

        public DataListViewHolder(@NonNull DataItemBinding dataItemBinding)
        {
            super(dataItemBinding.getRoot());
            this.dataItemBinding = dataItemBinding;
            //dataItemBinding.setClickListener(onItemClickListener);
        }

//        private OnItemClickListener onItemClickListener = new OnItemClickListener()
//        {
//            @Override
//            public void onItemClicked(View view, Fruits fruits)
//            {
//                int position = getAdapterPosition();
//                if(position != RecyclerView.NO_POSITION && listener != null)
//                    listener.onItemClicked(itemView, getCurrentItemAt(position));
//            }
//        };

        public void bind()
        {
            Log.d("Holder","holder called");
        }
    }


    public interface OnItemClickListener
    {
        void onItemClicked(View view, Fruits fruits);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }

}
