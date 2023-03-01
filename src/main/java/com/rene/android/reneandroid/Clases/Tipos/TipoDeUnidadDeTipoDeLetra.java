package com.rene.android.reneandroid.Clases.Tipos;

import android.util.TypedValue;

/**
 * Created by Rene on 9/4/2022.
 */

public enum TipoDeUnidadDeTipoDeLetra {
    SP(TypedValue.COMPLEX_UNIT_SP),PX(TypedValue.COMPLEX_UNIT_PX);
    private final int unidad;

    TipoDeUnidadDeTipoDeLetra(int unidad) {
        this.unidad = unidad;
    }

    public int getUnidad() {
        return unidad;
    }
}
