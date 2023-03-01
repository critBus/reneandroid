/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos;

/**
 *
 * @author Rene
 */
public enum TipoDeClasificacionSQL {
    PRIMARY_KEY("PRIMARY KEY"),PRIMARY_KEY_AUTOINCREMENT("PRIMARY KEY AUTOINCREMENT"),UNIQUE("UNIQUE"),NOT_NULL("NOT NULL");
     private final String  valor;

    private TipoDeClasificacionSQL(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
    
    public  boolean esLlave(){
        return this==PRIMARY_KEY||this==PRIMARY_KEY_AUTOINCREMENT;
    }
    public static boolean esTipoDeClasificacionSQL(Object a){
    return a instanceof TipoDeClasificacionSQL;    
    }
}
