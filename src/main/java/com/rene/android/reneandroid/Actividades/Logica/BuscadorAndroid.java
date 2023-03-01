package com.rene.android.reneandroid.Actividades.Logica;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.rene.android.reneandroid.ArchivoAndroid;
import com.rene.android.reneandroid.Clases.Actividad;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Buscador;
import com.rene.android.reneandroid.VisualAndroid;

import java.io.File;

/**
 * Created by Rene on 15/07/2020.
 */

public class BuscadorAndroid extends Buscador{

    private FragmentActivity activity;
    private File SD,interno;
    private boolean existeSD;
    public BuscadorAndroid(FragmentActivity a) {
        this(a,null);

    }
    public BuscadorAndroid(FragmentActivity a,File actual) {
        //super(actual==null?ArchivoAndroid.getDirectorioPadre():actual);

        try {
            interno=ArchivoAndroid.getFileInterno();
            existeSD=ArchivoAndroid.existeSD();
            //System.out.println("salida: existe:"+existeSD);
            if (existeSD) {
                SD = ArchivoAndroid.getFileExterno();
            }
            inicializar(actual==null?ArchivoAndroid.getDirectorioPadre():actual);
        }catch (Exception ex){
            VisualAndroid.responderException(a,ex);
        }
    }

    @Override
    public void filtrar() {
        try {
            if (ArchivoAndroid.esDirectorioTipoPadre(getActual())) {
                File hijos[] = existeSD ? new File[]{interno,SD}
                        :new File[]{interno};
                setHijos(hijos);
                setHijosFiltrados(hijos);
                setNombreHijosFiltrados(hijos.length==1?new String[]{"Interno"}:new String[]{"Interno","Externo"});
            } else {
                super.filtrar();
            }
        }catch (Exception ex){
            VisualAndroid.responderException(activity,ex);
        }
    }

    public void irAInicio(){
        abrir(ArchivoAndroid.getDirectorioPadre());

    }
    public void irASD(){
        if (existeSD){
            abrir(SD);
         }

    }

}
