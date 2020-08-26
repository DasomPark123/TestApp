package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class SensorActivity extends AppCompatActivity
{
    private final String TAG = getClass().getSimpleName();
    private TextView resultTv;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        resultTv = findViewById(R.id.tv_result);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        getSensorInfo();
    }

    private void getSensorInfo()
    {
        List<android.hardware.Sensor>list = sensorManager.getSensorList(android.hardware.Sensor.TYPE_ALL);
        String str = String.format(getResources().getString(R.string.total_sensor),list.size());

        for(int i = 0; i < list.size(); i++)
        {
            android.hardware.Sensor s = list.get(i);
            str += String.format(getResources().getString(R.string.info_sensor)
                    ,i,s.getName(),s.getPower(),s.getResolution(),s.getMaximumRange());
            Log.d(TAG,"i : "+ i);
            Log.d(TAG,"Name : "+ s.getName());
            Log.d(TAG,"Power : " + s.getPower());
            Log.d(TAG,"Resolution" + s.getResolution());
            Log.d(TAG,"MaxRange : " + s.getMaximumRange());
        }
        resultTv.setText(str);
    }
}
