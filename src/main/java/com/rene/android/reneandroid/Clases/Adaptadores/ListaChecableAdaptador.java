package com.rene.android.reneandroid.Clases.Adaptadores;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.rene.android.reneandroid.R;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Callback;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Rene on 6/7/2020.
 */

public class ListaChecableAdaptador<E> extends BaseAdapter {


    E objetos[];
    //TreeSet<Integer> indicesSeleccionados=new TreeSet<>();
    //HashSet<Integer> indicesSeleccionados=new HashSet<>();
    boolean seleccionados[];
    FilaVista filas[];


    public ListaChecableAdaptador(Activity a, final E[] objetos) {
        this.objetos = objetos;
        seleccionados=new boolean[objetos.length];
        Arrays.fill(seleccionados,false);

        View views[]=new View[objetos.length];
        filas=new ListaChecableAdaptador.FilaVista[objetos.length];
        LayoutInflater lf=a.getLayoutInflater();
        for (int i = 0; i <views.length ; i++) {
            views[i]=lf.inflate(R.layout.item_list_seleccionable,null,false);

            filas[i]=new FilaVista(views[i],i);
            filas[i].addCheck(new Utilizar2<Boolean, Integer>() {
                @Override
                public void utilizar(Boolean isChecked, Integer integer) {
                   // if (isChecked){
                    //    indicesSeleccionados.add(integer);
                   // }else{
                   //     indicesSeleccionados.remove(integer);
                  //  }
                    seleccionados[integer]=isChecked;
                    //System.out.println("salida: ob="+objetos[integer]);
                }
            });
            filas[i].setText(new Callback<Integer, String>() {
                @Override
                public String call(Integer param) {
                    return objetos[param].toString();
                }
            });
        }
    }

    @Override
    public int getCount() {
        return objetos.length;
    }

    @Override
    public Object getItem(int position) {
        return objetos[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return filas[position].view;
    }

    public int[] getIndicesSeleccionados(){
       /* int []sel=new int[indicesSeleccionados.size()];

        int i=0;
        for (Iterator<Integer> iterator=indicesSeleccionados.iterator();iterator.hasNext();){
            sel[i++]=iterator.next();
        }
        Arrays.sort(sel);
          return sel;
         */
       LinkedList<Integer> ind=new LinkedList<>();
        for (int i = 0; i <seleccionados.length ; i++) {
            if (seleccionados[i]){
            ind.add(i);
            }
        }
       int sel[]=new int[ind.size()];
        for (int i = 0; i <sel.length ; i++) {
            sel[i]=ind.get(i);
        }
       return sel;
    }

    public  List<E> getSeleccionados(){
        List<E> l2=new LinkedList<E>();
        //for (Iterator<Integer> iterator=indicesSeleccionados.iterator();iterator.hasNext();){
        //    l2.add(objetos[iterator.next()]);
       // }
        for (int i = 0; i <seleccionados.length ; i++) {
            if (seleccionados[i]){
                l2.add(objetos[i]);
            }
        }
       return l2;
    }



    /**
     * este es solo una copia del hashset,para que no se pueda
     * modificar al original
     * @return
     */
   /*  public HashSet<Integer> getSeleccionados() {
        return new HashSet<>(seleccionados);
    }*/

    class FilaVista{
        View view;
        int posicion;

        public FilaVista(View view, int posicion) {
            this.view = view;
            this.posicion = posicion;
        }

        public void addCheck(final Utilizar2<Boolean,Integer> u){
            ((CheckBox)view.findViewById(R.id.cb_item_list_seleccionable)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    u.utilizar(isChecked,posicion);
                   // System.out.println("salida: seleccionado="+isChecked+"  i="+posicion+"  ");
                }
            });
        }

        public void setText(Callback<Integer,String> c){
            ((TextView)view.findViewById(R.id.tv_item_list_seleccionable)).setText(c.call(posicion));
        }
    }
}
    /*
    private Activity activity;
    private E objetos[];
    private boolean seleccionados[];




    public ListaChecableAdaptador(Activity activity,E[] objetos ) {
        this.objetos = objetos;
        this.activity = activity;
        seleccionados=new boolean[objetos.length];
        for (int i = 0; i <seleccionados.length ; i++) {
            seleccionados[i]=false;
        }
        //Arrays.fill(seleccionados,false);
    }



    @Override
    public int getCount() {
        return objetos.length;
    }

    @Override
    public Object getItem(int position) {
        return objetos[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fila fila=null;
        if(convertView==null){
            LayoutInflater ly=activity.getLayoutInflater();
            convertView=ly.inflate(R.layout.item_list_seleccionable,parent,false);
            fila=new Fila();
            fila.checkBox=(CheckBox)convertView.findViewById(R.id.cb_item_list_seleccionable);
            fila.textView=(TextView)convertView.findViewById(R.id.tv_item_list_seleccionable);
            final int indice2=position;
            fila.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    seleccionados[indice2]=isChecked;
                    System.out.println("salida: seleccionado="+isChecked+"  i="+indice2+"  "+objetos[indice2]);
                }
            });
            fila.textView.setText(objetos[position].toString());
            convertView.setTag(fila);
            }else{
            fila=(Fila)convertView.getTag();
            }

        //fila.checkBox.setSelected(seleccionados[position]);

        //convertView.setTag(fila);

        return convertView;
    }

    private class Fila{
        CheckBox checkBox;
        TextView textView;
    }

    public boolean[] getSeleccionados() {
        return seleccionados;
    }
    */

