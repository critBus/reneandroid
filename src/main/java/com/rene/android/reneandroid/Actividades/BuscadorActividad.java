package com.rene.android.reneandroid.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.rene.android.reneandroid.Actividades.Logica.BuscadorAndroid;
import com.rene.android.reneandroid.ArchivoAndroid;
import com.rene.android.reneandroid.Clases.Actividad;
import com.rene.android.reneandroid.Clases.Apoyo.ListaSeleccionable;
import com.rene.android.reneandroid.R;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar3ConException;
import com.rene.android.reneandroid.VisualAndroid;

import java.io.File;

public class BuscadorActividad extends Actividad {
    public final static String BUSCADOR_SELECCIONADO="seleccionado",BUSCADOR_ANTERIOR="anterior";

    private BuscadorAndroid bs;
    private ListaSeleccionable<String> ls;
    private EditText buscar;
    private Class anterior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador_actividad);
        setVertical();
        setTitle("Buscador");
        try {
            //System.out.println("salida: anterior="+getIntent().getStringExtra(BUSCADOR_ANTERIOR));
            anterior = Class.forName(getIntent().getStringExtra(BUSCADOR_ANTERIOR));
            bs = new BuscadorAndroid(this);
            ls = setListaSeleccionable(R.id.sv_buscador_lista, bs.getNombreHijosFiltrados(),false, new Utilizar3ConException<ListaSeleccionable, String, Integer>() {
                @Override
                public void utilizar(ListaSeleccionable listaSeleccionable, String s, Integer integer) throws Exception {
                    bs.abrir(integer);
                    actualizarLista();
                    buscar.setText("");
                }
            });
            buscar = ((EditText) findViewById(R.id.et_buscador_buscar));
            addOnTextChanged(buscar, new Utilizar<String>() {
                @Override
                public void utilizar(String a) {
                    bs.setFiltroPorNombre(a);
                    bs.filtrar();
                    ls.actualizar(bs.getNombreHijosFiltrados());
                }
            });
            setVisible(R.id.b_buscador_sd, ArchivoAndroid.existeSD());
        }catch (Exception ex){
            responderException(ex);
        }
       }

   public void apretoCancelar(View view){
        irActivity(anterior);
    }
    public void apretoAceptar(View view){
       // System.out.println("salida: apreto aceptar");
        if(ls.getUltimoSeleccionado()!=-1){
            VisualAndroid.irActivity(this, anterior, new Utilizar<Intent>() {
                @Override
                public void utilizar(Intent a) {

                    File f=bs.get(ls.getUltimoSeleccionado());
                    //System.out.println("salida: sele="+f);
                    a.putExtra("sele",f.toString());
                }
            });
        }else{
            toast("Selecciona alguno!!");
        }

    }
    public void apretoAtras(View view){
        //System.out.println("salida: se llama atras");
        bs.atras();
       // System.out.println("salida: paso atras");
        actualizarLista();
      //  System.out.println("salida: actualizo");
        buscar.setText("");
    }
    public void apretoInicio(View view){
        bs.irAInicio();
        actualizarLista();
        buscar.setText("");
    }
    public void apretoSD(View view){
        bs.irASD();
        actualizarLista();
        buscar.setText("");
    }

    private void actualizarLista(){
        ls.actualizar(bs.getNombreHijosFiltrados());

    }
}
