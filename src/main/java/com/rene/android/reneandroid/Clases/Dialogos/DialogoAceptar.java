package com.rene.android.reneandroid.Clases.Dialogos;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;

import com.rene.android.reneandroid.R;

public class DialogoAceptar extends DialogoUnMensajeAceptar {

//private    @LayoutRes    int idLayout=-1;

//    public void setIdLayout(int idLayout) {
//        this.idLayout = idLayout;
//    }

    @Override
    protected  @IdRes
    int getR_id_mensaje(){        return R.id.TVMensajeDialogo;    };

    @Override
    protected @LayoutRes int  getR_Layout(){
//        if(idLayout!=-1){
//            return idLayout;
//        }
        return R.layout.fragment_dialogo_aceptar;
    }

}
