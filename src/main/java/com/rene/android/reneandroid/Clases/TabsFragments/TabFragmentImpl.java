package com.rene.android.reneandroid.Clases.TabsFragments;

import android.view.View;

import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar_Parcelable;

/**
 * Created by Rene on 30/10/2020.
 */

public class TabFragmentImpl extends TabFragment {
    public static final String LLAVE_ID_LAYOUT="LLAVE_ID_LAYOUT",LLAVE_METODO_ACTUALIZAR="LLAVE_METODO_ACTUALIZAR";
    private static Utilizar_Parcelable<View> accion_actualizar;

    @Override
    protected void inicializar(View raiz) {
        super.inicializar(raiz);
        accion_actualizar=(Utilizar_Parcelable<View>)  getArguments().get(LLAVE_METODO_ACTUALIZAR);
        accion_actualizar.utilizar(raiz);
    }

    @Override
    protected int getID_LayoutFragment() {
        return getArguments().getInt(LLAVE_ID_LAYOUT);
    }
}
