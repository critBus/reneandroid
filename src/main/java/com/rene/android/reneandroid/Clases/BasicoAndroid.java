package com.rene.android.reneandroid.Clases;

import com.rene.android.reneandroid.ArchivoAndroid;

/**
 * Created by Rene on 8/4/2022.
 */

public class BasicoAndroid {
    public static void log(Object o){
        ArchivoAndroid.log(o);
    }
    public static void vaciarLogYErrors(){
        ArchivoAndroid.vaciarLogYErrors();
    }
    public static void vaciarLog(){
        ArchivoAndroid.vaciarLog();

    }
    public static void vaciarErrors(){
        ArchivoAndroid.vaciarErrors();
    }
}
