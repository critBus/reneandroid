package com.rene.android.reneandroid.Clases.Dialogos;

import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.rene.android.reneandroid.R;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar2;

/**
 * Created by Rene on 28/3/2020.
 */

public class DialogoAdvertenciaAceptarCancelar extends DialogoFragment {

    private String mensaje;
    private Utilizar2<DialogFragment,View> accionCancelar;
    public void inicializarAceptarCancelar(String mensaje,Utilizar2<DialogFragment,View> accionAceptar) {
        inicializarAceptarCancelar(mensaje,accionAceptar,null);
    }


    public void inicializarAceptarCancelar(String mensaje,final Utilizar2<DialogFragment,View> accionAceptar, final Utilizar2<DialogFragment,View> accionCancelar) {
        //super.inicializarAceptar(mensaje,accionAceptar );
        super.inicializarAceptar(accionAceptar );
        this.mensaje=mensaje;
        this.accionCancelar=accionCancelar;
        final DialogFragment df=this;
        preparVista=new Utilizar2<DialogFragment,View>() {
            @Override
            public void utilizar(DialogFragment df, View a) {
                // ((Toolbar)a.findViewById(R.id.TBDialogoAceptar)).setTitle("Informacion");

                ((TextView)a.findViewById(R.id.TVMensajeDialogoAdvertencia)).setText(getMensaje());
            }
        };
        preparBuilder=new Utilizar2<View, AlertDialog.Builder>() {
            @Override
            public void utilizar(final View view, AlertDialog.Builder builder) {
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                     if(accionCancelar!=null){
                         accionCancelar.utilizar(df,view);
                     }
                        dialog.cancel();

                    }
                });
            }
        };

    }
    @Override
    protected @LayoutRes
    int  getR_Layout(){
        return R.layout.fragment_dilago_advertencia;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
