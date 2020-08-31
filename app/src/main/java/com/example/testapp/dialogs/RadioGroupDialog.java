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
import com.example.testapp.utils.PreferenceUtil;
import com.example.testapp.utils.Utils;

public class RadioGroupDialog extends AppCompatActivity
{
    private TextView tvTitle;
    private RadioGroup radioGroup;

    private PreferenceUtil pref;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_radio_group);
        setFinishOnTouchOutside(false);

        pref = new PreferenceUtil(getApplicationContext());

        init();
    }

    private void init()
    {
        Intent intent = getIntent();
        String title = intent.getStringExtra()
        tvTitle = findViewById(R.id.tv_title);
        radioGroup = findViewById(R.id.radio_group);

        int selectedItem = pref.getIntPreference(PreferenceUtil.KEY_RADIO_DIALOG_VALUE,0);
        for(int i = 0; i < )

    }

    private View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {

        }
    };
}
