package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity
{
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        init();
    }

    private void init()
    {
        mProgress = new ProgressDialog(BaseActivity.this);
    }

    public void showToast(Context context, String text)
    {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public void showProgress(final Activity act, final boolean bShow)
    {
        act.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    if(bShow)
                    {
                        mProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        mProgress.setMessage(act.getString(R.string.please_wait));
                        mProgress.setCancelable(false);
                        mProgress.show();
                    }
                    else
                    {
                        mProgress.dismiss();
                    }
                } catch(Exception e)
                {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        });
    }
}
