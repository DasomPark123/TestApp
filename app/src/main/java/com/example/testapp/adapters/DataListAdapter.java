package com.example.testapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.DataListActivity;
import com.example.testapp.R;
import com.example.testapp.VO.Fruits;
import com.example.testapp.databinding.DataItemBinding;
import com.example.testapp.dialogs.InputDataDialog;
import com.example.testapp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.DataListViewHolder>
{
    private Context context;
    private ArrayList<Fruits> itemList;
    private DataListViewHolder viewHolder;
    private boolean isCheckVisible;
    private DataListAdapter dataListAdapter;
    private OnItemClickListener listener;

    public DataListAdapter(Context context, ArrayList<Fruits> itemList)
    {
        this.context = context;
        this.itemList = itemList;
    }

    public void setList(List<Fruits> list)
    {
        itemList.clear();
        itemList.addAll(list);
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
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item, parent, false);
        //viewHolder = new DataListViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataListViewHolder holder, int position)
    {
        Fruits fruits = itemList.get(position);
        holder.dataItemBinding.setFruits(fruits);
        holder.checkBox.setChecked(fruits.isCheck());
//        holder.tvName.setText(fruits.getName());
//        holder.tvPrice.setText(fruits.getPrice());

        if(isCheckVisible)
            holder.checkBox.setVisibility(View.VISIBLE);
        else
            holder.checkBox.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount()
    {
        return itemList.size();
    }

    public Fruits getCurrentItemAt(int position)
    {
        return itemList.get(position);
    }

    public class DataListViewHolder extends RecyclerView.ViewHolder
    {
        private DataItemBinding dataItemBinding;
//        private LinearLayout linearDataItem;
        private CheckBox checkBox;
//        private TextView tvName;
//        private TextView tvPrice;
//
        public DataListViewHolder(@NonNull View itemView)
        {
            super(itemView);

//            linearDataItem = itemView.findViewById(R.id.linear_data_item);
            checkBox = itemView.findViewById(R.id.checkbox);
//            tvName = itemView.findViewById(R.id.tv_name);
//            tvPrice = itemView.findViewById(R.id.tv_price);
//
//            linearDataItem.setOnClickListener(onClickListener);
            checkBox.setOnClickListener(onClickListener);
            checkBox.setVisibility(View.INVISIBLE);
        }

        public DataListViewHolder(DataItemBinding dataItemBinding)
        {
            super(dataItemBinding.getRoot());
            this.dataItemBinding = dataItemBinding;
            itemView.setOnClickListener(onClickListener);

        }

        private View.OnClickListener onClickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("ViewHolder", "onClick");
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION && listener != null)
                {
                    listener.onItemClick(getCurrentItemAt(position), view.getId());
//                    if(view.getId() == R.id.checkbox)
//                    {
//                        Log.d("ViewHolder", "itemClickListener position: " + position);
//                        if(itemList.get(position).isCheck())
//                        {
//                            itemList.get(position).setCheck(false);
//                            Log.d("ViewHolder", itemList.get(position).isCheck() ?
//                                    "checkbox : true" : "checkbox : false");
//                        }
//                        else
//                        {
//                            itemList.get(position).setCheck(true);
//                            Log.d("ViewHolder", itemList.get(position).isCheck() ?
//                                    "checkbox : true" : "checkbox : false");
//                        }
//                    }
//                    else if(view.getId() == R.id.linear_data_item)
//                    {
//                        Log.d("ViewHolder", "itemClickListener position: " + position);
//                        Intent intent = new Intent(context, InputDataDialog.class);
//                        intent.putExtra(Intent.EXTRA_TITLE, context.getString(R.string.update_data));
//                        intent.putExtra(Utils.EXTRA_NAME_VALUE,itemList.get(position).getName());
//                        intent.putExtra(Utils.EXTRA_PRICE_VALUE,itemList.get(position).getPrice());
//                        ((Activity)context).startActivityForResult(intent, DataListActivity.REQUEST_UPDATE);
//                    }


                }
            }
        };
    }

    public interface OnItemClickListener
    {
        void onItemClick(Fruits fruits, int resId);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }

}
