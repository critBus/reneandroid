package com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Imple;

import android.content.Context;
import android.os.Build;
import android.view.View;

import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Inter.Input;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Inter.TouchHandler;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.handler.AccelerometerHandler;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.handler.KeyboardHandler;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.handler.MultiTouchHandler;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.handler.SingleTouchHandler;

import java.util.List;

/**
 * Created by Rene on 24/6/2020.
 */

public class AndroidInput implements Input {

    private AccelerometerHandler accelerometerHandler;
    private KeyboardHandler keyboardHandler;
    private TouchHandler touchHandler;

    public AndroidInput(Context context, View view, float scaleX, float scaleY) {
        accelerometerHandler=new AccelerometerHandler(context);//view,scaleX,scaleY
        keyboardHandler=new KeyboardHandler(view);
        if (Build.VERSION.SDK_INT<5){
            touchHandler=new SingleTouchHandler(view,scaleX,scaleY);
        }else{
            touchHandler=new MultiTouchHandler(view,scaleX,scaleY);
        }

    }

    @Override
    public boolean isKeyPressed(int keyCode) throws Exception {
        return keyboardHandler.isKeyPressed(keyCode);
    }

    @Override
    public boolean isTouchDown(int pointer) throws Exception {
        return touchHandler.isTouchDown(pointer);
    }

    @Override
    public int getTouchX(int pointer) throws Exception {
        return touchHandler.getTouchX(pointer);
    }

    @Override
    public int getTouchY(int pointer) throws Exception {
        return touchHandler.getTouchY(pointer);
    }

    @Override
    public float getAccelX() throws Exception {
        return accelerometerHandler.getAccelX();
    }

    @Override
    public float getAccelY() throws Exception {
        return accelerometerHandler.getAccelY();
    }

    @Override
    public float getAccelZ() throws Exception {
        return accelerometerHandler.getAccelZ();
    }

    @Override
    public List<KeyEvent> getKeyEvents() throws Exception {
        return keyboardHandler.getKeyEvents();
    }

    @Override
    public List<TouchEvent> getTouchEvents() throws Exception {
        return touchHandler.getTouchEvents();
    }
}
