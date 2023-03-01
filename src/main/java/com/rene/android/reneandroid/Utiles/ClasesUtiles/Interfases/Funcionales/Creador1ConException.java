package com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales;

/**
 *  Del Tipo ( E -> R )
 * @author Rene
 */

public interface Creador1ConException <E,R> {
    public R crear(E e) throws Exception;
}

