package com.example.rubafikri.ass1;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    ImageView imDown;
    ImageView imRight;
    ImageView imLeft;
    ImageView imUp;
    Sensor s = null;
    TextView tv;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        imDown =findViewById(R.id.imDown);
        imRight = findViewById(R.id.imRight);
        imLeft = findViewById(R.id.imLeft);
        imUp = findViewById(R.id.imUp);

        sensorManager   = ( SensorManager ) getSystemService(this.SENSOR_SERVICE);
        if(sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER) != null){
            s = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }else{
            Toast.makeText(this, "not found", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            tv.setText("x: "+event.values[0] + ", Y: "+event.values[1]);
            imUp.setVisibility(View.GONE);
            imLeft.setVisibility(View.GONE);
            imRight.setVisibility(View.GONE);
            imDown.setVisibility(View.GONE);

            if (event.values[0] > 7) {
                imRight.setVisibility(View.VISIBLE);
            }else if (event.values[1] > 0){
               imDown.setVisibility(View.VISIBLE);
           }else if (event.values[0] > -3){
                imUp.setVisibility(View.VISIBLE);
            }else if (event.values[1] < 0){
                imLeft.setVisibility(View.VISIBLE);
            }


            }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}