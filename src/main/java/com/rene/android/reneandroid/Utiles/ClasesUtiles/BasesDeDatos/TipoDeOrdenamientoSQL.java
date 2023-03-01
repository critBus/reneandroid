package com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos;

/**
 * Created by Rene on 4/4/2022.
 */

public enum TipoDeOrdenamientoSQL {
    ASC("ASC"),DESC("DESC");
    private final String  valor;

    private TipoDeOrdenamientoSQL(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }



    public static boolean esTipoDeOrdenamientoSQL(Object e){
        return e instanceof TipoDeOrdenamientoSQL;
    }

    public static TipoDeOrdenamientoSQL get(String a){
        return TipoDeOrdenamientoSQL.valueOf(a);
    }

    public static boolean pertenece(String a){
        return TipoDeOrdenamientoSQL.get(a)!=null;
    }

}

