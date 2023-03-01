package com.rene.android.reneandroid.Utiles.ClasesUtiles.Procedimento;

import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar;

/**
 * Created by Rene on 29/6/2020.
 */

public class ProcedimientoUtilizarConExecption<E> {
    private Utilizar<E> accionPrimera,accionSegunda;

    public ProcedimientoUtilizarConExecption(Utilizar<E> accionPrimera, Utilizar<E> accionSegunda) {
        this.accionPrimera = accionPrimera;
        this.accionSegunda = accionSegunda;
    }

    public Utilizar<E> getAccionPrimera() {
        return accionPrimera;
    }

    public void setAccionPrimera(Utilizar<E> accionPrimera) {
        this.accionPrimera = accionPrimera;
    }

    public Utilizar<E> getAccionSegunda() {
        return accionSegunda;
    }

    public void setAccionSegunda(Utilizar<E> accionSegunda) {
        this.accionSegunda = accionSegunda;
    }
}
