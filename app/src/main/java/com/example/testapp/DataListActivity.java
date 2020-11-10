package com.example.testapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.VO.DataItem;
import com.example.testapp.adapters.DataListAdapter;
import com.example.testapp.dialogs.AddDataDialog;
import com.example.testapp.dialogs.RadioGroupDialog;
import com.example.testapp.utils.PreferenceUtil;
import com.example.testapp.utils.Utils;

import java.util.ArrayList;

public class DataListActivity extends AppCompatActivity
{
    private final String TAG = getClass().getSimpleName();

    private final int REQUEST_ADD = 0x1001;
    private final int REQUEST_SAVE = 0x1002;

    private final int INTERNAL_STORAGE = 0;
    private final int EXTERNAL_STORAGE = 1;
    private final int SHARED_PREFERENCE = 2;
    private final int SQL_LITE = 3;
    private final int ROOM = 4;

    private PreferenceUtil pref;
    private RecyclerView recyclerView;
    private DataListAdapter adapter;

    private ImageButton btnDelete;

    private String name;
    private String price;

    private String[] saveItems;

    private ArrayList<DataItem> dataList = new ArrayList<>();

    private int saveType = 0;

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

        pref = new PreferenceUtil(getApplicationContext());

        recyclerView = findViewById(R.id.rv_data_list);
        adapter = new DataListAdapter(dataList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnDelete = findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(onClickListener);

        saveItems = getResources().getStringArray(R.array.save_type_array);

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
            showSaveDataDialog(R.string.save_data,R.array.save_type_array,saveType);
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
        else if(requestCode == REQUEST_SAVE && resultCode == Activity.RESULT_OK)
        {
            Intent intent = new Intent();
            int result = intent.getIntExtra(Utils.EXTRA_INT_VALUE,0);
            saveData(result);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void saveData(int saveType)
    {
        if(saveType == INTERNAL_STORAGE)
        {

        }
        else if(saveType == EXTERNAL_STORAGE)
        {

        }
        else if(saveType == SHARED_PREFERENCE)
        {

        }
        else if(saveType == SQL_LITE)
        {

        }
        else if(saveType == ROOM)
        {

        }
    }

    private void saveViaRoom()
    {

    }

    private void showSaveDataDialog(int title, int itemList,  int selectedItem)
    {
        Intent intent = new Intent(DataListActivity.this, RadioGroupDialog.class);
        String[] items  = getResources().getStringArray(itemList);
        intent.putExtra(Utils.EXTRA_TITLE,getString(title));
        intent.putExtra(Utils.EXTRA_STRING_ARRAY_VALUE,items);
        intent.putExtra(Utils.EXTRA_INT_VALUE,selectedItem);
        startActivityForResult(intent,REQUEST_SAVE);
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
