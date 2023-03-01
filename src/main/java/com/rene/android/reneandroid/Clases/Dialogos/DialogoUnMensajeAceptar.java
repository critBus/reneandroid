package com.rene.android.reneandroid.Clases.Dialogos;

import android.support.annotation.IdRes;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar2;

/**
 * Created by Rene on 28/3/2020.
 */

public abstract class DialogoUnMensajeAceptar extends DialogoFragment {
    private String mensaje;
    public void inicializarAceptar( String mensaje) {
        inicializarAceptar(mensaje,null);
    }

    //@Override
    public void inicializarAceptar(String mensaje,Utilizar2<DialogFragment,View> accionAceptar ) {
        super.inicializarAceptar(accionAceptar );
        this.mensaje=mensaje;

        preparVista=new Utilizar2<DialogFragment,View>() {
            @Override
            public void utilizar(DialogFragment df,View a) {
                // ((Toolbar)a.findViewById(R.id.TBDialogoAceptar)).setTitle("Informacion");

                ((TextView)a.findViewById(getR_id_mensaje())).setText(getMensaje());
            }
        };

    }

    protected  abstract   @IdRes
    int getR_id_mensaje();

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
