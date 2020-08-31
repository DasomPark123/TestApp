package com.example.testapp.dialogs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.testapp.R;
import com.example.testapp.utils.Utils;

public class RadioGroupDialog extends AppCompatActivity
{
    private TextView tvTitle;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_radio_group);
        setFinishOnTouchOutside(false);

        init();
    }

    private void init()
    {
        tvTitle = findViewById(R.id.tv_title);
        radioGroup = findViewById(R.id.radio_group);


    }

    private View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {

        }
    };
}
