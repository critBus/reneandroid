package com.rene.android.reneandroid.Clases.Apoyo;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.rene.android.reneandroid.R;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar3ConException;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.UtilizarConException;
import com.rene.android.reneandroid.VisualAndroid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Rene on 08/01/2021.
 */

public class ListaAdaptable<E>{
    private Activity activity;
    private TableLayout tableLayout;

    private  @LayoutRes int idFila;
    private E objetos[];
    private Utilizar3ConException<ListaAdaptable,E,Integer> accionClick;
     private ArrayList<View> filas;



    private Utilizar3ConException<View,E,Integer> accionCrearView;

    private int idHeader;
    private UtilizarConException<View> accionCrearHeader;



    public ListaAdaptable(Activity activity,@IdRes int idTableLayout,@LayoutRes  int idFila, E[] objetos,Utilizar3ConException<View,E,Integer> accionCrearView) {
        this(activity,idTableLayout,idFila,objetos,accionCrearView,null);

    }
    public ListaAdaptable(Activity activity,@IdRes int idTableLayout,@LayoutRes  int idFila, E[] objetos,Utilizar3ConException<View,E,Integer> accionCrearView,Utilizar3ConException<ListaAdaptable,E,Integer> accionClick ) {
        this(activity,idTableLayout,-1,null,idFila,objetos,accionCrearView,accionClick);

    }
    public ListaAdaptable(Activity activity,@IdRes int idTableLayout,@LayoutRes  int idHeader,UtilizarConException<View> accionCrearHeader,@LayoutRes  int idFila, E[] objetos,Utilizar3ConException<View,E,Integer> accionCrearView,Utilizar3ConException<ListaAdaptable,E,Integer> accionClick ) {
        this.idHeader=idHeader;
        this.accionCrearHeader=accionCrearHeader;
        this.accionClick=accionClick;
        this.accionCrearView=accionCrearView;

        View view=activity.findViewById(idTableLayout);
        if (view instanceof TableLayout){

            inicializar(idFila,(TableLayout) view,objetos,activity);
        }else {
            if (view instanceof ScrollView){
                ScrollView sv=(ScrollView)view;
                sv.removeAllViews();
                tableLayout =new TableLayout(activity);
                sv.addView(tableLayout);
                inicializar(idFila,tableLayout,objetos,activity);
            }else{
                System.out.println("salida: no fue "+view);
            }
        }
    }

    private void inicializar(@LayoutRes  int idFila,TableLayout tableLayout, E[] objetos, Activity activity) {
        this.tableLayout = tableLayout;
        this.activity = activity;
        this.idFila=idFila;
        filas=new ArrayList<>();
        crearHeader();
        actualizar(objetos);
    }

    public void actualizar(E[] objetos){
        this.objetos = objetos;
       crearFilas();
    }



    public void crearTabla(){
        tableLayout.removeAllViews();
        crearHeader();
        crearFilas();
        tableLayout.invalidate();
    }
    private void crearHeader(){
        try {
            //System.out.println("salida: lo va a intentar poner");
        if(idHeader>=0){
            View v=activity.getLayoutInflater().inflate(idHeader,tableLayout,false);
//            System.out.println("salida: lo puso");
            tableLayout.addView(v,0);
            if (accionCrearHeader!=null){
                accionCrearHeader.utilizar(v);
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void crearFilas(){
        for (int i = 0; i <objetos.length ; i++) {
            View v=null;
            final int posicion=i;
            if(i>(filas.size()-1)){
                v=activity.getLayoutInflater().inflate(idFila,tableLayout,false);
                tableLayout.addView(v);
                filas.add(v);
            }else{
                v=filas.get(i);

            }
            try {
                // System.out.println("salida: intenta llamarlo");
                accionCrearView.utilizar(v,objetos[posicion],posicion);
                //   System.out.println("salida: lo llamo");
            } catch (Exception e) {
                System.out.println("salida: error al crearse");
                e.printStackTrace();
            }

            if (!VisualAndroid.esVisible(v)){
                VisualAndroid.setVisibleSinEspacion(v,true);
            }
            if(accionClick!=null){
                final ListaAdaptable lthis=this;
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            accionClick.utilizar(lthis,objetos[posicion],posicion);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }


        }

        for (int i = objetos.length; i <filas.size() ; i++) {
            //VisualAndroid.setVisible(filas.get(i),false);
            VisualAndroid.setVisibleSinEspacion(filas.get(i),false);
        }

    }
    public void actualizarFila(int i){
        View v=filas.get(i);
        final int posicion=i;
        try {
            // System.out.println("salida: intenta llamarlo");
            accionCrearView.utilizar(v,objetos[posicion],posicion);
            //   System.out.println("salida: lo llamo");
        } catch (Exception e) {

            System.out.println("salida: error al crearse");
            e.printStackTrace();
        }


    }
    public E get(int indice){
        return objetos[indice];
    }
}
