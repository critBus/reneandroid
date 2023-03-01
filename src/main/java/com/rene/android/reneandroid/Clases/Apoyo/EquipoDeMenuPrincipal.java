package com.rene.android.reneandroid.Clases.Apoyo;

import android.support.annotation.MenuRes;
import android.view.Menu;
import android.view.MenuItem;

import com.rene.android.reneandroid.Clases.Actividad;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Creador;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar2;

/**
 * Created by Rene on 4/7/2020.
 */

public class EquipoDeMenuPrincipal {

   private @MenuRes int menuRes;
  // private int  IDs_MI_A_Invisible[];
   private Utilizar2<Actividad,MenuItem> accionSeleccionarMI;
  private Creador<int[]> creador_IDs_MI_A_Invisibles;

    public EquipoDeMenuPrincipal(@MenuRes int menuRes) {
        this(menuRes,null);
    }
    public EquipoDeMenuPrincipal(@MenuRes int menuRes, Utilizar2<Actividad,MenuItem> accionSeleccionarMI) {
       //this(menuRes,null,accionSeleccionarMI);
        inicializar(menuRes,null,accionSeleccionarMI);
    }

    public EquipoDeMenuPrincipal(@MenuRes int menuRes,final int[] IDs_MI_A_Invisible, Utilizar2<Actividad,MenuItem> accionSeleccionarMI) {
        inicializar(menuRes,new Creador<int[]>() {
            @Override
            public int[] crear() {
                return IDs_MI_A_Invisible;
            }
        },accionSeleccionarMI);
        /* this.menuRes = menuRes;
       // this.IDs_MI_A_Invisible = IDs_MI_A_Invisible;
        this.accionSeleccionarMI = accionSeleccionarMI;
        creador_IDs_MI_A_Invisibles=new Creador<int[]>() {
            @Override
            public int[] crear() {
                return IDs_MI_A_Invisible;
            }
        }; */
    }
    public EquipoDeMenuPrincipal(@MenuRes int menuRes,Creador<int[]> creador_IDs_MI_A_Invisibles, Utilizar2<Actividad,MenuItem> accionSeleccionarMI) {
       inicializar(menuRes,creador_IDs_MI_A_Invisibles,accionSeleccionarMI);
    }


    public boolean onCreateOptionsMenu(Actividad a, Menu menu){
        a.getMenuInflater().inflate(menuRes,menu);
        //if (IDs_MI_A_Invisible!=null){
        if(creador_IDs_MI_A_Invisibles!=null){
            //a.setVisible(menu,false,IDs_MI_A_Invisible);
            int ids[]=creador_IDs_MI_A_Invisibles.crear();
            if (ids!=null&&ids.length>0){
            a.setVisible(menu,false,ids);
            }
        }
        return true;
    }

    private void inicializar(@MenuRes int menuRes,Creador<int[]> creador_IDs_MI_A_Invisibles, Utilizar2<Actividad,MenuItem> accionSeleccionarMI) {
        this.menuRes = menuRes;
        this.accionSeleccionarMI = accionSeleccionarMI;
        this.creador_IDs_MI_A_Invisibles=creador_IDs_MI_A_Invisibles;
    }
    public void onOptionsItemSelected(Actividad a,MenuItem item) {
        if (accionSeleccionarMI!=null){
            accionSeleccionarMI.utilizar(a,item);
        }

    }

    public @MenuRes int getMenuRes() {
        return menuRes;
    }

    public void setMenuRes(@MenuRes int menuRes) {
        this.menuRes = menuRes;
    }
/*
    public int[] getIDs_MI_A_Invisible() {
        return IDs_MI_A_Invisible;
    }

    public void setIDs_MI_A_Invisible(int[] IDs_MI_A_Invisible) {
        this.IDs_MI_A_Invisible = IDs_MI_A_Invisible;
    }

    public Utilizar<MenuItem> getAccionSeleccionarMI() {
        return accionSeleccionarMI;
    }

    public void setAccionSeleccionarMI(Utilizar<MenuItem> accionSeleccionarMI) {
        this.accionSeleccionarMI = accionSeleccionarMI;
    }
    */
}
