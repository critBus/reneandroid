package com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos;

/**
 * Created by Rene on 4/4/2022.
 */

public abstract class ModeloDeApiBD<E> {
    public int idkey;
    public E apibd;

    public ModeloDeApiBD(int idkey, E apibd) {
        this.idkey = idkey;
        this.apibd = apibd;
    }

    public int getIdkey() {
        return idkey;
    }

    public void setIdkey(int idkey) {
        this.idkey = idkey;
    }

    public E getApibd() {
        return apibd;
    }

    public void setApibd(E apibd) {
        this.apibd = apibd;
    }
}
