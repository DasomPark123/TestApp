package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class CompassActivity extends AppCompatActivity
{

    private ImageView compassIv;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor magnetometer;
    private float[] lastAccelerometer = new float[3];
    private float[] lastMagnetometer = new float[3];
    private boolean lastAccelerometerSet = false;
    private boolean lastMagnetometerSet = false;
    private float[] r = new float[9];
    private float[] orientation = new float[3];
    private float currentDegree = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        compassIv = findViewById(R.id.iv_compass);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        compassIv = findViewById(R.id.iv_compass);
    }

    @Override
    protected void onResume()
    {
        sensorManager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(sensorEventListener, magnetometer, SensorManager.SENSOR_DELAY_GAME);

        super.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }

    private SensorEventListener sensorEventListener = new SensorEventListener()
    {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent)
        {
            if(sensorEvent.sensor == accelerometer)
            {
                System.arraycopy(sensorEvent.values, 0, lastAccelerometer, 0, sensorEvent.values.length);
                lastAccelerometerSet = true;
                Log.e("AAAA","accelerometer");
            }
            else if(sensorEvent.sensor == magnetometer)
            {
                System.arraycopy(sensorEvent.values, 0, lastMagnetometer, 0, sensorEvent.values.length);
                lastMagnetometerSet = true;
                Log.e("AAAA","magnetometer");
            }

            if(lastAccelerometerSet && lastMagnetometerSet)
            {
                SensorManager.getRotationMatrix(r,null,lastAccelerometer,lastMagnetometer);
                float azimuthinDegress = (int) (Math.toDegrees(SensorManager.getOrientation(r, orientation)[0]) + 360) % 360;
                RotateAnimation rotateAnimation = new RotateAnimation(
                        currentDegree,
                        -azimuthinDegress,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f
                );
                rotateAnimation.setDuration(250);
                rotateAnimation.setFillAfter(true);
                compassIv.startAnimation(rotateAnimation);
                currentDegree = -azimuthinDegress;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i)
        {

        }
    };
}
