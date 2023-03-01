package com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales;

/**
 * Created by Rene on 23/2/2020.
 */
public interface Callback<P,R> {
    /**
     * The <code>call</code> method is called when required, and is given a
     * single argument of type P, with a requirement that an object of type R
     * is returned.
     *
     * @param param The single argument upon which the returned value should be
     *      determined.
     * @return An object of type R that may be determined based on the provided
     *      parameter value.
     */
    public R call(P param);
}

