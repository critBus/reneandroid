package com.rene.android.reneandroid.Clases.TabsFragments;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rene.android.reneandroid.Clases.ExtendedFragment;

/**
 * Created by Rene on 30/10/2020.
 */

public abstract class TabFragment extends ExtendedFragment {

    private View raiz;

    public View getRaiz() {
        return raiz;
    }
    protected void inicializar(View raiz){

    }


    protected abstract  @LayoutRes int  getID_LayoutFragment();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        raiz=inflater.inflate(getID_LayoutFragment(), container, false);
        inicializar(raiz);
        return raiz;
    }


}
