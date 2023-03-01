package com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.handler;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by Rene on 24/6/2020.
 */

public class AccelerometerHandler implements SensorEventListener {

    private float accelX,accelY,accelZ;

    public AccelerometerHandler(Context context){
    	SensorManager manager=(SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        if(manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size()!=0){
            Sensor sensor=manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            manager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_GAME);
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        accelX=event.values[0];
        accelY=event.values[1];
        accelZ=event.values[2];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public float getAccelX() {
        return accelX;
    }

    public float getAccelY() {
        return accelY;
    }

    public float getAccelZ() {
        return accelZ;
    }
}
