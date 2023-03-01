package com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Inter;

import android.view.View;

import java.util.List;

/**
 * Created by Rene on 24/6/2020.
 */

public interface TouchHandler extends View.OnTouchListener {
    public boolean isTouchDown(int pointer) throws Exception;
    public int getTouchX(int pointer) throws Exception;
    public int getTouchY(int pointer) throws Exception;
    public List<Input.TouchEvent> getTouchEvents() throws Exception;
}
