package com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.BD;


import com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos.DatosBDConect;

/**
 * Created by Rene on 07/01/2021.
 */

public interface GestorDeConexion {
    public Object _execute(String sql) throws Exception;
    //public void _closeConexion() throws Exception ;
    //public void _openConexion() throws Exception ;

    public void _mostrarResultadoConsola()  ;
}
