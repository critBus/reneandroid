package com.rene.android.reneandroid;

import android.content.Context;
import android.hardware.SensorEvent;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.rene.android.reneandroid.Clases.RetainedFragment;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Creador;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;


/**
 * Created by Rene on 21/3/2020.
 */

public abstract class UtilAndroid {

    public static DateTime getDateTimeForStrBD(String s){
        //System.out.println("salida: s->d="+s);
        DateTime d=new DateTime(s);
        //System.out.println("salida: d="+d);
        return d;
    }
    public static DateTime getDateTime(Date d){
        return new DateTime(d.getTime());
    }

    public static String getStrBDDateTime(DateTime d){
        return d.toString("yyyy-MM-dd HH:mm:ss");
    }
    public static Date getDate(DateTime d){
        return new Date(d.toInstant().getMillis());
    }
    public static Date getDateForStrBD(String s){
        DateTime d=getDateTimeForStrBD(s);
        return  getDate(d);
    }

     public static <E extends RetainedFragment> E getRetainedFragment(FragmentActivity a, Creador<E> c){
        FragmentManager fm = a.getSupportFragmentManager();
        E  dataFragment =(E)  fm.findFragmentByTag("data");

        // create the fragment and data the first time
        if (dataFragment == null) {
            // add the fragment
            dataFragment =c.crear();
            fm.beginTransaction().add(dataFragment, "data").commit();
            // load the data from the web

        }
        return dataFragment;
    }
    public static <E extends RetainedFragment> E getRetainedFragment(FragmentActivity a,String tag, Creador<E> c){
        FragmentManager fm = a.getSupportFragmentManager();
        E  dataFragment =(E)  fm.findFragmentByTag(tag);

        // create the fragment and data the first time
        if (dataFragment == null) {
           // System.out.println("salida: creo");
            // add the fragment
            dataFragment =c.crear();
            fm.beginTransaction().add(dataFragment, tag).commit();
            // load the data from the web

        }
        return dataFragment;
    }

    public static String getLooaleString(Context c){
        return c.getResources().getConfiguration().locale.getDisplayName();
    }

    /**
     * No es mio pero funciona
     * @param event
     * @return
     */
    public static double[] getAceleracionLinear(SensorEvent event){
// alpha is calculated as t / (t + dT)
        // with t, the low-pass filter's time-constant
        // and dT, the event delivery rate

        final float alpha = 0.8f,g = 9.81f ;
double[] gravity={g,g,g},linear_acceleration={0,0,0};
        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];

return linear_acceleration;
    }

    public static String getString_JSONObject(JSONObject params)throws Exception{
        StringBuilder result=new StringBuilder();
        boolean first=true;
        Iterator<String> itr=params.keys();
        while (itr.hasNext()){
            String key=itr.next();
            Object value=params.get(key);

            if (first){
                first=false;
            }else{
             result.append("&");
            }
            result.append(URLEncoder.encode(key,"UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(),"UTF-8"));
        }
        return result.toString();
    }

    public static JSONArray getJSON_Data(String httpDireccion) throws Exception{
        String sql=httpDireccion;
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        URL url=null;
        HttpURLConnection conn;
        //try{
            url=new URL(sql);
            conn=(HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response=new StringBuffer();
            String json="";
            while((inputLine=in.readLine())!=null){
                response.append(inputLine);
            }
            json=response.toString();
            JSONArray jsonArray=null;
            jsonArray=new JSONArray(json);
           // for (int i = 0; i <jsonArray.length() ; i++) {
           //     JSONObject jsonObject=jsonArray.getJSONObject(i);
           // }
            return jsonArray;
       // }catch (Exception ex){ex.printStackTrace();}
        //return null;
    }


}
