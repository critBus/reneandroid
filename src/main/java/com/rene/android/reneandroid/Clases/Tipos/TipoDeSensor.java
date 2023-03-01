package com.rene.android.reneandroid.Clases.Tipos;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

/**
 * Created by Rene on 29/6/2020.
 */

public enum TipoDeSensor {
    ACELEROMETRO(Sensor.TYPE_ACCELEROMETER)
    ,TODOS(Sensor.TYPE_ALL)
    ,TEMPERATURA_AMBIENTE(Sensor.TYPE_AMBIENT_TEMPERATURE)
    ,VECTOR_DE_ROTACION_EN_JUEGOS(Sensor.TYPE_GAME_ROTATION_VECTOR)
    ,VECTOR_DE_ROTACION_GEOMAGNETISMO(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR)
    ,VECTOR_DE_ROTACION(Sensor.TYPE_ROTATION_VECTOR)
    ,GRAVEDAD(Sensor.TYPE_GRAVITY)
    ,GIROSCOPIO(Sensor.TYPE_GYROSCOPE)
    ,GIROSCOPIO_SIN_CALIBRACION(Sensor.TYPE_GYROSCOPE_UNCALIBRATED)
    ,LUZ(Sensor.TYPE_LIGHT)
    ,ACELERACION_LINEAL(Sensor.TYPE_LINEAR_ACCELERATION)
    ,CAMPO_MAGNETICO(Sensor.TYPE_MAGNETIC_FIELD)
    ,CAMPO_MAGNETICO_SIN_CALIBRACION(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED)
    ,MOVIMIENTO(Sensor.TYPE_MOTION_DETECT)
    ,MOVIMIENTO_SIGNIFICATIVO(Sensor.TYPE_SIGNIFICANT_MOTION)
    ,PRESION(Sensor.TYPE_PRESSURE)
    ,PROXIMIDAD(Sensor.TYPE_PROXIMITY)
    ,HUEMEDAD_RELATIVA(Sensor.TYPE_RELATIVE_HUMIDITY)
    ,ESTACIONARIO(Sensor.TYPE_STATIONARY_DETECT)
    ,PASOS_CONTADOR(Sensor.TYPE_STEP_COUNTER)
    ,PASOS(Sensor.TYPE_STEP_DETECTOR);//,MAGNETOMETRO(Sensor.T)
    private final int valor;

    TipoDeSensor(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static TipoDeSensor get(SensorEvent event){
        int tipo=event.sensor.getType();
        for (TipoDeSensor t: TipoDeSensor.values()) {
            if(tipo==t.getValor()){
                return t;
            }
        }
        return null;
    }
}
