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
import com.rene.android.reneandroid.VisualAndroid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Rene on 8/7/2020.
 */

public class ListaSeleccionable<E>{
    private Activity activity;
    private TableLayout tableLayout;
    private @LayoutRes
    int idFila;
    private @IdRes
    int idCB;
    private @IdRes int idTV;
    private boolean seleccionadosBol[];
    private E objetos[];
    private Utilizar3ConException<ListaSeleccionable,String,Integer> accionClick;
    private boolean seleccionMultiple;
    private int ultimoSeleccionado;

    private ArrayList<View> filas;
    public ListaSeleccionable(Activity activity,@IdRes int idTableLayout, E[] objetos ) {
        this(activity,idTableLayout,objetos,null);

    }
    public ListaSeleccionable(Activity activity,@IdRes int idTableLayout, E[] objetos,Utilizar3ConException<ListaSeleccionable,String,Integer> accionClick ) {
        this(activity,idTableLayout,objetos,true,accionClick);

    }
    public ListaSeleccionable(Activity activity,@IdRes int idTableLayout, E[] objetos,boolean seleccionMultiple,Utilizar3ConException<ListaSeleccionable,String,Integer> accionClick ) {
        this.accionClick=accionClick;
        this.seleccionMultiple=seleccionMultiple;
        ultimoSeleccionado=-1;
        View view=activity.findViewById(idTableLayout);
        if (view instanceof TableLayout){
            inicializar((TableLayout) view,objetos,activity);
        }else {
            if (view instanceof ScrollView){
                ScrollView sv=(ScrollView)view;
                sv.removeAllViews();
                tableLayout =new TableLayout(activity);
                sv.addView(tableLayout);
                inicializar(tableLayout,objetos,activity);
            }else{
                System.out.println("salida: no fue "+view);
            }
        }
    }
    public ListaSeleccionable( Activity activity,TableLayout tableLayout, E[] objetos) {
        inicializar(tableLayout,objetos,activity);
        /* this.tableLayout = tableLayout;
        this.activity = activity;
        idFila= R.layout.item_list_seleccionable;
        idCB=R.id.cb_item_list_seleccionable;
        idTV=R.id.tv_item_list_seleccionable;
        actualizar(objetos); */
    }

    private void inicializar(TableLayout tableLayout, E[] objetos, Activity activity) {
        this.tableLayout = tableLayout;
        this.activity = activity;
        idFila= R.layout.item_list_seleccionable;
        idCB=R.id.cb_item_list_seleccionable;
        idTV=R.id.tv_item_list_seleccionable;
        filas=new ArrayList<>();

        actualizar(objetos);
    }

    public void actualizar(E[] objetos){
        this.objetos = objetos;
        seleccionadosBol=new boolean[objetos.length];
        Arrays.fill(seleccionadosBol,false);
        ultimoSeleccionado=-1;

        crearFilas();
    }

    public void crearTabla(){
        tableLayout.removeAllViews();
        crearFilas();
        tableLayout.invalidate();
    }

    private void crearFilas(){
        for (int i = 0; i <objetos.length ; i++) {
            View v=null;
            CheckBox cb=null;
            TextView tv=null;
            final int posicion=i;
            if(i>(filas.size()-1)){
                v=activity.getLayoutInflater().inflate(idFila,tableLayout,false);
                //final int posicion=i;
                cb= ((CheckBox)v.findViewById(idCB));
               cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        seleccionadosBol[posicion]=isChecked;
                        if(isChecked){
                            ultimoSeleccionado=posicion;
                            if (!seleccionMultiple){
                                for (int j = 0; j <filas.size() ; j++) {
                                    if(j!=posicion){
                                        CheckBox cb2=((CheckBox)filas.get(j).findViewById(idCB));
                                        if (cb2.isChecked()){
                                            cb2.setChecked(false);
                                        }
                                    }
                                }
                            }
                        }else{
                            if (posicion==ultimoSeleccionado){
                                ultimoSeleccionado=-1;
                            }

                        }
                    }
                });

                tableLayout.addView(v);
                filas.add(v);
            }else{
                v=filas.get(i);
                cb= ((CheckBox)v.findViewById(idCB));

            }
            tv=((TextView)v.findViewById(idTV));
            tv.setText(objetos[i].toString());

            //cb.setSelected(false);
            //cb.invalidate();
            cb.setChecked(false);
            if (posicion==ultimoSeleccionado){
                ultimoSeleccionado=-1;
            }
            if (!VisualAndroid.esVisible(v)){
                VisualAndroid.setVisibleSinEspacion(v,true);
               // v.setVisibility(View.GONE);
            }
            if(accionClick!=null){
               final ListaSeleccionable lthis=this;
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            accionClick.utilizar(lthis,((TextView)v).getText()+"",posicion);
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

    public int[] getIndicesSeleccionados(){

        LinkedList<Integer> ind=new LinkedList<>();
        for (int i = 0; i <seleccionadosBol.length ; i++) {
            if (seleccionadosBol[i]){
                ind.add(i);
            }
        }
        int sel[]=new int[ind.size()];
        for (int i = 0; i <sel.length ; i++) {
            sel[i]=ind.get(i);
        }
        return sel;
    }

    public List<E> getSeleccionados(){
        List<E> l2=new LinkedList<E>();

        for (int i = 0; i <seleccionadosBol.length ; i++) {
            if (seleccionadosBol[i]){
                l2.add(objetos[i]);
            }
        }
        return l2;
    }

    public int getUltimoSeleccionado() {
        return ultimoSeleccionado;
    }
}
