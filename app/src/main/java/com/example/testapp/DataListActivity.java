package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.VO.DataItem;
import com.example.testapp.adapters.DataListAdapter;
import com.example.testapp.dialogs.AddDataDialog;
import com.example.testapp.utils.Utils;

import java.util.ArrayList;

public class DataListActivity extends AppCompatActivity
{
    private final int REQUEST_ADD = 0x1001;
    private final int REQUEST_SAVE = 0x1002;

    private RecyclerView recyclerView;
    private ArrayList<DataItem> dataList = new ArrayList<DataItem>();
    private DataListAdapter adapter;
    private Utils utils;

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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new DataListAdapter(dataList);
        recyclerView.setAdapter(adapter);

        utils = new Utils();

        addTestData();

    }

    private void addTestData()
    {
        dataList.add(new DataItem(true,"apple","1000"));
        dataList.add(new DataItem(true,"banana","2000"));
        dataList.add(new DataItem(true,"strawberry","3000"));
        dataList.add(new DataItem(true,"pear","4000"));
        dataList.add(new DataItem(true,"grape","500"));
        dataList.add(new DataItem(true,"grapefruit","100"));

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

        }
        else if(item.getItemId() == R.id.action_add)
        {
            Intent intent = new Intent(this, AddDataDialog.class);
            intent.putExtra(Utils.EXTRA_TITLE,getString(R.string.add_data));
            startActivityForResult(intent,REQUEST_ADD);

        }
        else if(item.getItemId() == R.id.action_delete)
        {

        }
        else if(item.getItemId() == R.id.action_select_all)
        {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
