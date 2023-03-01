package com.rene.android.reneandroid.Clases.BD;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos.BDResultadoInsertar;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos.DatosBDConect;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos.GestorDeConexionImple;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos.TipoDeDatoSQL;
import com.rene.android.reneandroid.Utiles.MetodosUtiles.Arreglos;
import com.rene.android.reneandroid.Utiles.MetodosUtiles.MetodosUtiles;
import com.rene.android.reneandroid.Utiles.MetodosUtiles.SQLUtil;

import java.io.File;
import java.util.LinkedList;

/**
 * Created by Rene on 07/01/2021.
 */

public class GestorDeConexionAndroid extends GestorDeConexionImple {
    private DatosDeConexion dc;
    private Context c;

    private File direccion;

    public GestorDeConexionAndroid(Context c,DatosBDConect datosBDConect) {
        super(datosBDConect);
        this.c=c;
    }

    public GestorDeConexionAndroid( Context c,DatosBDConect datosBDConect, File direccion) {
        super(datosBDConect);

        this.c = c;
        this.direccion = direccion;
    }

    @Override
    public Object _execute( String sql) throws Exception {
        Object res = null;
         _openConexion();
        getDatosBDConect().setUltimuSQl(sql);
        System.out.println("sql=" + sql);


        dc.sqLiteStatement=dc.sqLiteDatabase.compileStatement(sql);

        if (SQLUtil.esSelect(sql)) {
            dc.cursor=dc.sqLiteDatabase.rawQuery(sql,null);
            if (SQLUtil.esSelectValor(sql)) {

                if(dc.cursor.moveToNext()){
                    String nombreColmuna1=dc.cursor.getColumnName(0);
                    
                    //String o =dc.cursor.getExtras().get(nombreColmuna1)+"";
                    int i=dc.cursor.getPosition();
                    Object o="";
                    switch (dc.cursor.getType(i)){
                        case Cursor.FIELD_TYPE_STRING:
                            o=dc.cursor.getString(i);
                            break;
                        case Cursor.FIELD_TYPE_INTEGER:
                            o=dc.cursor.getInt(i);
                            break;
                        case Cursor.FIELD_TYPE_FLOAT:
                            o=dc.cursor.getFloat(i);
                            break;
                        case Cursor.FIELD_TYPE_NULL:
                            o=null;
                            break;
                        case Cursor.FIELD_TYPE_BLOB:
                            o=dc.cursor.getBlob(i);
                            break;
                        default:
                            o=null;

                    }

                    if (MetodosUtiles.esNumeroAll(o+"")) {
                        res = MetodosUtiles.dou(o+"");
                    }
                }

            }else {
                LinkedList<Object[]> l = new LinkedList<>();
                int j = 0;
                while (dc.cursor.moveToNext()){
                    Object[] Ofila = new Object[dc.cursor.getColumnCount()];
                    for (int i = 0; i < Ofila.length; i++) {
                        //String nombreColmuna=dc.cursor.getColumnName(i);
                        //System.out.println("salida: nomC="+nombreColmuna+" i="+i);
                        switch (dc.cursor.getType(i)){
                            case Cursor.FIELD_TYPE_STRING:
                                Ofila[i]=dc.cursor.getString(i);
                                break;
                            case Cursor.FIELD_TYPE_INTEGER:
                                Ofila[i]=dc.cursor.getInt(i);
                                break;
                            case Cursor.FIELD_TYPE_FLOAT:
                                Ofila[i]=dc.cursor.getFloat(i);
                                break;
                            case Cursor.FIELD_TYPE_NULL:
                                Ofila[i]=null;
                                break;
                            case Cursor.FIELD_TYPE_BLOB:
                                Ofila[i]=dc.cursor.getBlob(i);
                                break;
                            default:
                                Ofila[i]=null;

                        }

                       // Ofila[i] = dc.cursor.getExtras().get(nombreColmuna);
                    }
                    l.add(Ofila);
                }
                Object[][] n = new Object[l.size()][];
                for (int i = 0; i < n.length; i++) {
                    n[i] = l.get(i);
                }
                res = n;

            }
            if (getDatosBDConect().isMostrarResultadoConsola()) {
                getDatosBDConect().setResultado(res);
                _mostrarResultadoConsola();
            }
        }else{

            if(SQLUtil.esCREATE(sql)||SQLUtil.esDROP(sql)){
                dc.sqLiteStatement.execute();
            }else{
                if(SQLUtil.esINSERT(sql)){
                    long id=dc.sqLiteStatement.executeInsert();

                    BDResultadoInsertar resultadoDeInsertar = new BDResultadoInsertar();
                    resultadoDeInsertar.add(TipoDeDatoSQL.INTEGER,(int)id);
//                    ResultSet rs = dc.instruccion.getGeneratedKeys();
//                    //System.out.println("rs class=" + rs.getClass());
//                    ResultSetMetaData metaDatos = rs.getMetaData();
//                    int j = 0;
//                    while (rs.next()) {
//                        for (int i = 1; i <= metaDatos.getColumnCount(); i++) {
//                            Object o = rs.getObject(i);
//                            resultadoDeInsertar.add(metaDatos.getColumnTypeName(i).equals("integer") ? TipoDeDatoSQL.INTEGER : TipoDeDatoSQL.VARCHAR, o);
//
//                            //System.out.println("i=" + i + "o=" + o);
//                            //System.out.println("metaDatos.getColumnClassName(i)=" + metaDatos.getColumnClassName(i));
//                            //System.out.println("metaDatos.getColumnLabel(i)=" + metaDatos.getColumnLabel(i));
//                            //System.out.println("metaDatos.getColumnName(i)=" + metaDatos.getColumnName(i));
//                            //System.out.println("metaDatos.getColumnTypeName(i)=" + metaDatos.getColumnTypeName(i));
//                        }
//                        j++;
//                        //System.out.println("j=" + j);
//                    }
                    if (!resultadoDeInsertar.isEmpty()) {
                        res = resultadoDeInsertar;
                    }

                }else{

                    if(SQLUtil.esUPDATE(sql)||SQLUtil.esDELETE(sql)){
                        dc.sqLiteStatement.executeUpdateDelete();
                    }
                    else{
                        dc.sqLiteDatabase.execSQL(sql);
                    }
                }
            }

        }




        _closeConexion();
        getDatosBDConect().setResultado(res);
        return getDatosBDConect().getResultado();
    }

    //@Override
    public void _openConexion() throws Exception {
        //Inicializar
        class creadorBD extends SQLiteOpenHelper{


            public creadorBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
                super(context, name, factory, version);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {

            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        }
        dc=new DatosDeConexion();

        if(direccion!=null){
            dc.sqLiteDatabase=SQLiteDatabase.openOrCreateDatabase(direccion,null);
        }else{
            dc.sqLiteOpenHelper=new creadorBD(c, getDatosBDConect().getNombreBD(), null, 1);//NUllllllllllllllllllllllllllll
            dc.sqLiteDatabase=dc.sqLiteOpenHelper.getWritableDatabase();
        }
       // dc.sqLiteOpenHelper=


        dc.sqLiteDatabase.beginTransaction();

    }


    //@Override
    public void _closeConexion() throws Exception {
        if(dc.cursor!=null){
            dc.cursor.close();
        }

        if(dc.sqLiteStatement!=null){
            dc.sqLiteStatement.close();
        }




        if(dc.sqLiteDatabase!=null){
            dc.sqLiteDatabase.setTransactionSuccessful();
            dc.sqLiteDatabase.endTransaction();
            dc.sqLiteDatabase.close();
        }


        if(dc.sqLiteOpenHelper!=null){
            dc.sqLiteOpenHelper.close();
        }


    }


    @Override
    public void _mostrarResultadoConsola() {
        if (getDatosBDConect().getResultado() instanceof Object[][]) {
            Arreglos.MostrarMatrizObject((Object[][]) getDatosBDConect().getResultado());
        } else {
            System.out.println("resultado: " + getDatosBDConect().getResultado());
        }
    }

    private class DatosDeConexion {
        SQLiteDatabase sqLiteDatabase;
        SQLiteStatement sqLiteStatement;
        Cursor cursor;
        SQLiteOpenHelper sqLiteOpenHelper;
//        Connection conexion;
//        Statement instruccion;
//        ResultSet conjuntoResultados;
    }



}
