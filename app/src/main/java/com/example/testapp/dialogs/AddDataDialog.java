package com.example.testapp.dialogs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testapp.R;
import com.example.testapp.utils.Utils;

public class AddDataDialog extends AppCompatActivity
{
    private TextView tvCancel;
    private TextView tvOk;
    private EditText etName;
    private EditText etPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_add_data);
        setFinishOnTouchOutside(false);

        tvCancel = findViewById(R.id.tv_cancel);
        tvOk = findViewById(R.id.tv_ok);
        etName = findViewById(R.id.et_name);
        etPrice = findViewById(R.id.et_price);

        tvCancel.setOnClickListener(onClickListener);
        tvOk.setOnClickListener(onClickListener);
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
            if(view.getId() == R.id.tv_cancel)
            {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
            else if(view.getId() == R.id.tv_ok)
            {
                Intent data = new Intent();
                data.putExtra(Utils.EXTRA_NAME_VALUE,etName.getText().toString());
                data.putExtra(Utils.EXTRA_PRICE_VALUE,etPrice.getText().toString());
                setResult(Activity.RESULT_OK,data);
                finish();
            }
        }
    };
}
