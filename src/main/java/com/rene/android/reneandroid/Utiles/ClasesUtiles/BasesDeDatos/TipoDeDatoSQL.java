/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos;

import com.rene.android.reneandroid.UtilAndroid;

import org.joda.time.DateTime;

/**
 *
 * @author Rene
 */
public enum TipoDeDatoSQL {
    INTEGER("INTEGER","0")
    ,VARCHAR("VARCHAR","")
    ,REAL("REAL","0")
    ,DATE("DATE", null)//UtilAndroid.getStrBDDateTime(new DateTime())

    ,POINT("POINT","(0,0)")
    ,BLOB("BLOB",null)
    ,BOOLEAN("BOOLEAN","false")
    ,DOUBLE_PRECISION("DOUBLE PRECISION","0")
    ,TIME("TIME",null)
    ;

    private final String  valor;
    private final String  porDefecto;

    private TipoDeDatoSQL(String valor,String  porDefecto) {
        this.valor = valor;
        this.porDefecto=porDefecto;
    }

    public String getValor() {
        return valor;
    }

    public String getPorDefecto() {
        return porDefecto;
    }




    public static boolean esTipoDeDatoSQl(Object e){
        return e instanceof TipoDeDatoSQL;
    }


}
