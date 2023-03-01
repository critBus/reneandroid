package com.rene.android.reneandroid.Clases.Adaptadores;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
 * Created by Rene on 7/7/2020.
 */

public class ListaChecableAdaptador2<E> extends ArrayAdapter<E> {

    E objetos[];
    //TreeSet<Integer> indicesSeleccionados=new TreeSet<>();
    //HashSet<Integer> indicesSeleccionados=new HashSet<>();
    boolean seleccionados[];
    ListaChecableAdaptador2.FilaVista filas[];


    public ListaChecableAdaptador2(Activity a, final E[] objetos) {
        super(a,R.layout.item_list_seleccionable,objetos);
        this.objetos = objetos;
        seleccionados=new boolean[objetos.length];
        Arrays.fill(seleccionados,false);

        View views[]=new View[objetos.length];
        filas=new ListaChecableAdaptador2.FilaVista[objetos.length];
        LayoutInflater lf=a.getLayoutInflater();
        for (int i = 0; i <views.length ; i++) {
            views[i]=lf.inflate(R.layout.item_list_seleccionable,null,false);

            filas[i]=new ListaChecableAdaptador2.FilaVista(views[i],i);
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
    public View getView(int position, View convertView, ViewGroup parent) {

        return filas[position].view;
        //return super.getView(position,convertView,parent);
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

  /*  E objetos[];
    boolean seleccionados[];
    Context context;
    public ListaChecableAdaptador2(@NonNull Context context, @NonNull E[] objects) {
        super(context, R.layout.item_list_seleccionable, objects);
        objetos=objects;
        seleccionados=new boolean[objetos.length];
        Arrays.fill(seleccionados,false);
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        Fila fila=null;
       if (convertView==null){
           LayoutInflater lf=((Activity)context).getLayoutInflater();
           convertView=lf.inflate(R.layout.item_list_seleccionable,parent,false);
           fila=new Fila();
           fila.checkBox=(CheckBox)convertView.findViewById(R.id.cb_item_list_seleccionable);
           fila.textView=(TextView)convertView.findViewById(R.id.tv_item_list_seleccionable);
           fila.posicion=position;
           fila.addCheck(new Utilizar2<Boolean, Integer>() {
               @Override
               public void utilizar(Boolean isChecked, Integer integer) {
                  seleccionados[integer]=isChecked;
              }
           });

           convertView.setTag(fila);
       }else{
        fila=(Fila)convertView.getTag();
       }

       fila.textView.setText(objetos[fila.posicion].toString());

       return convertView;

    }

    class Fila{
        CheckBox checkBox;
        TextView textView;
        int posicion;
        public void addCheck(final Utilizar2<Boolean,Integer> u){
            (checkBox).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    u.utilizar(isChecked,posicion);
                    // System.out.println("salida: seleccionado="+isChecked+"  i="+posicion+"  ");
                }
            });
        }
    }

    public int[] getIndicesSeleccionados(){
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

    public List<E> getSeleccionados(){
        List<E> l2=new LinkedList<E>();
       for (int i = 0; i <seleccionados.length ; i++) {
            if (seleccionados[i]){
                l2.add(objetos[i]);
            }
        }
        return l2;
    }
}
*/