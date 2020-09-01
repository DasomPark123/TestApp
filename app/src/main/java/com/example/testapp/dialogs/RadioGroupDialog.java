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

        tvTitle = findViewById(R.id.tv_title);
        radioGroup = findViewById(R.id.radio_group);

        findViewById(R.id.tv_cancel).setOnClickListener(onClickListener);
        findViewById(R.id.tv_ok).setOnClickListener(onClickListener);

        init();
    }

    private void init()
    {
        Intent intent = getIntent();
        String title = intent.getStringExtra(Utils.EXTRA_TITLE);
        tvTitle.setText(title);

        int selectedItem = pref.getIntPreference(Utils.EXTRA_INT_VALUE,0);
        String[] itemList = intent.getStringArrayExtra(Utils.EXTRA_STRING_ARRAY_VALUE);
        for(int i = 0; i < itemList.length; i++ )
        {
            RadioButton radioButton = new RadioButton(getApplicationContext());
            radioButton.setText(itemList[i]);
            radioButton.setTextColor(getResources().getColor(R.color.colorWhite));
            radioButton.setButtonTintList(getResources().getColorStateList(R.color.colorWhite));
            radioButton.setId(i);
            if(i == selectedItem)
            {
                radioButton.setChecked(true);
            }
            radioGroup.addView(radioButton);
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            if(view.getId() ==  R.id.tv_cancel)
            {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
            else if(view.getId() == R.id.tv_ok)
            {
                Intent intent = new Intent();
                intent.putExtra(Utils.EXTRA_INT_VALUE,radioGroup.getCheckedRadioButtonId());
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        }
    };
}
