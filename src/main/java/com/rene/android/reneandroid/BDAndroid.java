package com.rene.android.reneandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.rene.android.reneandroid.Clases.BD.GestorDeConexionAndroid;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos.BDConexion;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos.BDTipoDeConexion;
import com.rene.android.reneandroid.Utiles.MetodosUtiles.Archivo;

import java.io.File;

/**
 * Created by Rene on 07/01/2021.
 */

public abstract class BDAndroid {



    public static void deleteDB_SQLite(File f){
        SQLiteDatabase.deleteDatabase(f);
    }

    public static BDConexion getDB_SQLite(Context c, String nombreBD){
        BDConexion bdConexion =new BDConexion("","","",nombreBD,"", BDTipoDeConexion.SQL_LITE,null);
        bdConexion.setGestorDeConexionImple(new GestorDeConexionAndroid(c, bdConexion.getDatosBDConect()));
        return bdConexion;
    }
    public static BDConexion getDB_SQLite(Context c, File direccion){
        BDConexion bdConexion =new BDConexion("","","", Archivo.getNombre(direccion),"", BDTipoDeConexion.SQL_LITE,null);
        bdConexion.setGestorDeConexionImple(new GestorDeConexionAndroid(c, bdConexion.getDatosBDConect(),direccion));
        return bdConexion;
    }
    public static File crearArchivoSQLite(Context context,File direccion)throws Exception{
//    log("direccion="+direccion);
//        log("direccion.getParentFile()="+direccion.getParentFile());
//        log("Archivo.getNombre(direccion)="+direccion.getParentFile());
//        log("Archivo.getExtencion(direccion)="+Archivo.getExtencion(direccion));
    return  crearArchivoSQLite(context,direccion.getParentFile(),Archivo.getNombre(direccion),Archivo.getExtencion(direccion));

    }
    public static File crearArchivoSQLite(Context context,File CarpetaDestino,String nombreBD)throws Exception{
        return crearArchivoSQLite(context,CarpetaDestino,nombreBD,".sqlite");

    }
    public static File crearArchivoSQLite(Context context,File CarpetaDestino,String nombreBD,String extencion)throws Exception{

//        File ubicacion=new File(CarpetaDestino+"/"+nombreBD);//+".sqlite"
//        log("ubicacion="+ubicacion+" "+ubicacion.exists());
//        if(ubicacion.exists()){
//            log("va vorrar bd");
//            ubicacion.delete();
//        }
//        SQLiteDatabase.openOrCreateDatabase(ubicacion,null);
        //return ubicacion;
        return ArchivoAndroid.copiarDesdeAssets(context,"BD/bdSQLite.s3db",nombreBD+extencion,CarpetaDestino);

    }
//

    private static void log(Object o){
        try {
            ArchivoAndroid.escribirLogInterno("LeyenApps/Logs", "logs", o.toString());
        }catch (Exception ex){}
    }



}
