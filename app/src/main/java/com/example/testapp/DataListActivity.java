package com.example.testapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.VO.DataItem;
import com.example.testapp.adapters.DataListAdapter;
import com.example.testapp.dialogs.AddDataDialog;
import com.example.testapp.dialogs.SaveDataDialog;
import com.example.testapp.utils.Utils;

import java.util.ArrayList;

public class DataListActivity extends AppCompatActivity
{
    private final int REQUEST_ADD = 0x1001;
    private final int REQUEST_SAVE = 0x1002;

    private RecyclerView recyclerView;
    private DataListAdapter adapter;

    private ImageButton btnDelete;

    private String name;
    private String price;

    private ArrayList<DataItem> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);

        initView();

    }

    private void initView()
    {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.data_list);

        recyclerView = findViewById(R.id.rv_data_list);
        adapter = new DataListAdapter(dataList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnDelete = findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(onClickListener);

        addTestData();
    }

    private void addTestData()
    {
        dataList.add(new DataItem("apple","1000"));
        dataList.add(new DataItem("banana","2000"));
        dataList.add(new DataItem("strawberry","3000"));
        dataList.add(new DataItem("pear","4000"));
        dataList.add(new DataItem("grape","500"));
        dataList.add(new DataItem("grapefruit","100"));

        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId() == R.id.action_save)
        {
            Intent intent = new Intent(this, SaveDataDialog.class);
            startActivityForResult(intent,REQUEST_SAVE);
        }
        else if(item.getItemId() == R.id.action_add)
        {
            Intent intent = new Intent(this, AddDataDialog.class);
            intent.putExtra(Utils.EXTRA_TITLE,getString(R.string.add_data));
            startActivityForResult(intent,REQUEST_ADD);
        }
        else if(item.getItemId() == R.id.action_delete)
        {
            btnDelete.setVisibility(View.VISIBLE);
        }
        else if(item.getItemId() == R.id.action_select_all)
        {
            for(int i = 0; i < dataList.size(); i++)
            {
                dataList.get(i).setCheck(true);
            }

            adapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(requestCode == REQUEST_ADD && resultCode == Activity.RESULT_OK)
        {
            name = data.getStringExtra(Utils.EXTRA_NAME_VALUE);
            price = data.getStringExtra(Utils.EXTRA_PRICE_VALUE);
            dataList.add(new DataItem(name,price));
            adapter.notifyItemChanged(dataList.size()-1);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            if(view.getId() == R.id.btn_delete)
            {

                    for(int i = dataList.size() -1; i >= 0; i--)
                    {
                        if(dataList.get(i).isCheck())
                        {
                            dataList.remove(i);
                        }
                    }

                btnDelete.setVisibility(View.GONE);
                adapter.notifyDataSetChanged();
            }
        }
    };
}
