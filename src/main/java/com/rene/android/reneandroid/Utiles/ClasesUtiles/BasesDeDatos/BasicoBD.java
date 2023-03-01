package com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos;

import com.rene.android.reneandroid.Utiles.MetodosUtiles.MetodosUtiles;
import com.rene.android.reneandroid.Utiles.MetodosUtiles.SQLUtil;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Rene on 4/4/2022.
 */

public abstract class BasicoBD {
    public  int toInt(Object o) {
        if (o == null || !esNumeroInt(o)) {
            return -1;
        }
        return inT(o);
    }
    public  double toDou(Object o) {
        if (o == null || !esNumeroDou(o)) {
            return -1;
        }
        return dou(o);
    }

    public  String to_String(Object o) {
        if (o == null) {
            return "";
        }
        return o.toString();
    }

    public  boolean toBool(Object o) {

        if (o == null) {
            return false;
        }
        String a = o.toString().trim().toLowerCase();
        if (!MetodosUtiles.esBool(a)) {
            return false;
        }
        return Boolean.valueOf(a);
    }

    public  Date toDate(Object o) {
        if (o == null) {
            return null;
        }

        if (o instanceof Date) {
            return (Date) o;
        }
        String a=o.toString().trim();
        if(a.isEmpty()){
            return null;
        }
        return getDate(a);
    }
    public Time toTime(Object o) {
        if(o==null){
            return null;
        }
        return Time.valueOf(o+"");
//        Date d=toDate(o);
//        return new Time(d.getHours(),d.getMinutes(),d.getSeconds());
    }
    public  byte[] toBlob(Object o) {
        if(o==null){
            return new byte[0];
        }
        //algo para llevar a byte[]
        return new byte[0];
    }

    public  String fromBlob(Object o) {
        if(o instanceof byte[]){
            //algo
        }
        //algo
        return "";
    }


    public  Date getDate(String timestamp) {
        return SQLUtil.getDate(timestamp);
    }



    public  boolean esNumeroInt(Object o) {
        if (o instanceof Integer) {
            return true;
        }
        return MetodosUtiles.esNumeroAll(o.toString());
    }

    public  int inT(Object o) {
        return MetodosUtiles.inT(o.toString());
    }
    public  double dou(Object o) {
        return MetodosUtiles.dou(o.toString());
    }

    public  boolean esNumeroDou(Object o) {
        if (o instanceof Integer) {
            return true;
        }
        if (o instanceof Float) {
            return true;
        }
        if (o instanceof Double) {
            return true;
        }
        return MetodosUtiles.esNumeroAll(o.toString());
    }

    public  boolean esArregloString(Object a) {
        return MetodosUtiles.esArregloString(a);
    }

    public   boolean esArreglo(Object a) {
        return MetodosUtiles.esArreglo(a);
    }
}
