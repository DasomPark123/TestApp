package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testapp.VO.Todo;
import com.example.testapp.dao.TodoDao;
import com.example.testapp.room.TodoDatabase;

import java.util.List;

public class MainActivity extends BaseActivity
{
    private TextView mTvResult;
    private EditText mEtTodo;
    private Button mBtnAdd;

    private TodoDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init()
    {
        mTvResult = findViewById(R.id.textView);
        mEtTodo = findViewById(R.id.editText);
        mBtnAdd = findViewById(R.id.btn_add);
        mBtnAdd.setOnClickListener(mOnClickListener);

        //create DB
        db = TodoDatabase.getAppDatabase(this);
        db.todoDao().getAll().observe(this, new Observer<List<Todo>>()
        {
            @Override
            public void onChanged(List<Todo> todos)
            {
                mTvResult.setText(todos.toString());
            }
        });
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch(view.getId())
            {
                case R.id.btn_add:
                    if(mEtTodo.getText().toString().trim().length() == 0)
                    {
                        showToast(MainActivity.this,"Please enter text");
                    }
                    else
                    {
                        new InsertAsyncTask(db.todoDao()).execute(new Todo(mEtTodo.getText().toString()));
                        mEtTodo.setText("");
                    }
                    break;
            }
        }
    };

    private class InsertAsyncTask extends AsyncTask<Todo, Void, Void>
    {
        private TodoDao mTodoDao;

        public InsertAsyncTask(TodoDao mTodoDao)
        {
            this.mTodoDao = mTodoDao;
        }

        @Override
        protected void onPreExecute()
        {
            showProgress(MainActivity.this,true);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Todo... todos)
        {
            mTodoDao.insert(todos[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            showProgress(MainActivity.this,false);
            super.onPostExecute(aVoid);
        }
    }


}
