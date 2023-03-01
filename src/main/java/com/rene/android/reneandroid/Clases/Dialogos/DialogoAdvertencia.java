package com.rene.android.reneandroid.Clases.Dialogos;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;

import com.rene.android.reneandroid.R;


public class DialogoAdvertencia extends DialogoUnMensajeAceptar {

    @Override
    protected  @IdRes
    int getR_id_mensaje(){        return R.id.TVMensajeDialogoAdvertencia;    };

    @Override
    protected @LayoutRes
    int  getR_Layout(){
        return R.layout.fragment_dilago_advertencia;
    }
}
