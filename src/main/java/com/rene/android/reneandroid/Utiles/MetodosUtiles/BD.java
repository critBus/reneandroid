/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rene.android.reneandroid.Utiles.MetodosUtiles;

import com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos.BDConexion;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos.BDTipoDeConexion;
import static com.rene.android.reneandroid.Utiles.MetodosUtiles.MetodosUtiles.or;
import java.io.File;

/**
 * Metodos que fasilitan las acciones basicas con Bases de Datos Version 0.3
 *
 * @author Rene
 */
public abstract class BD {

    //private static final String JDBC="jdbc:";
    public static boolean esCuentaValida(String cuenta) {
        return MetodosUtiles.contieneLetrasOR(cuenta);
    }

    public static String getFormaString(Object o) {
        Class c = o.getClass();
        if (or(c, java.util.Date.class)) {
            return new java.sql.Date(((java.util.Date) o).getTime()) + "";
        }

        return o.toString();
    }

    public static String getConexionPOSTGRES(String servidor, String puerto, String nombreBD) {
        return BDTipoDeConexion.POSTGRES.getUrl() + "//" + servidor + ":" + puerto + "/" + nombreBD;
    }

    public static String getConexionSQL_LITE(File direccion) {
        return BDTipoDeConexion.SQL_LITE.getUrl() + direccion;
    }

    public static String getConexionMY_SQL(String servidor, String nombreBD) {
        return BDTipoDeConexion.MY_SQL.getUrl() + "/" + servidor + "/" + nombreBD;
    }

    public static boolean esSQL_LITE(String direccion) {

        return MetodosUtiles.or(Archivo.getExtencion(direccion), true, BDTipoDeConexion.SQL_LITE.getExtencionDeArchivo());
    }

    
   
    public static File crearDBEstadoActualSiNoExiste() throws Exception {
        File car = new File("Data/BD"), dire = new File("Data/BD/EstadoActual.s3db");
        if (dire.exists()) {
            dire.delete();
        } else {
            car.mkdirs();
            Archivo.copiarDesdeJar("Utiles/ClasesUtiles/BasesDeDatos/EstadoActual.s3db", dire);
        }

        return dire;
    }
    public static BDConexion getConexionDBEstadoActual() throws Exception{
    return BDConexion.getConexionSQL_LITE(crearDBEstadoActualSiNoExiste());
    }
    
    //public static Date get

//    public static final String URL_SQL_LITE = "jdbc:sqlite:";
//    public static final String URL_SQL_LITE = "jdbc:sqlite:";
}
