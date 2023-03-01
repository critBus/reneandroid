package com.rene.android.reneandroid.Clases.Tipos;

import android.view.MotionEvent;

/**
 * Created by Rene on 15/6/2020.
 */

public enum TipoDeMotionEvent {
    DOWN(MotionEvent.ACTION_DOWN),POINTER_DOWN(MotionEvent.ACTION_POINTER_DOWN),UP(MotionEvent.ACTION_UP)
    ,POINTER_UP(MotionEvent.ACTION_POINTER_UP),CANCEL(MotionEvent.ACTION_CANCEL),MOVE(MotionEvent.ACTION_MOVE)
    ,SCROLL(MotionEvent.ACTION_SCROLL);

    private final int valor;

    TipoDeMotionEvent(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static TipoDeMotionEvent get(MotionEvent e){
        int action=e.getAction()&MotionEvent.ACTION_MASK;
        for (TipoDeMotionEvent t: TipoDeMotionEvent.values()) {
            if (t.getValor()==action){
             return t;
            }
        }
        return null;
    }
    public static int getPointerID(MotionEvent e){
        int pointerIndex=(e.getAction()&MotionEvent.ACTION_POINTER_ID_MASK)>>MotionEvent.ACTION_POINTER_ID_SHIFT;
        int pointerID=e.getPointerId(pointerIndex);
        return pointerID;
    }
}
