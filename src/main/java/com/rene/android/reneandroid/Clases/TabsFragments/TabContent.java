package com.rene.android.reneandroid.Clases.TabsFragments;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;

import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar_Parcelable;

/**
 * Created by Rene on 30/10/2020.
 */

public class TabContent {
    private TabFragment tf;

    public TabContent(@LayoutRes int id_layout_fragment,Utilizar_Parcelable<View> accion_actualizar) {

        Bundle b=new Bundle();
        b.putInt(TabFragmentImpl.LLAVE_ID_LAYOUT,id_layout_fragment);
        b.putParcelable(TabFragmentImpl.LLAVE_METODO_ACTUALIZAR,accion_actualizar);

        tf=new TabFragmentImpl();
        tf.setArguments(b);
    }

    public TabFragment getTf() {
        return tf;
    }
}
