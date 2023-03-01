package com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Inter;

import java.util.List;

/**
 * Created by Rene on 23/6/2020.
 */

public interface Input {
    public static class KeyEvent{
        public static final int KEY_DOWN=0;
        public static final int KEY_UP=1;

        public int type,keyCode;
        public char keyChar;

        @Override
        public String toString() {
            String res="";
            if (type==KEY_DOWN){
                res+="tecla pulsada, ";
            }else{
                res+="tecla levantada, ";
            }
            return res+=keyCode+","+keyChar;
        }
    }

    public static class TouchEvent{
        public static final int TOUCH_DOWN=0;
        public static final int TOUCH_UP=1;
        public static final int TOUCH_DRAGGED=2;

        public int type,x,y,pointer;

        @Override
        public String toString() {
            String res="";
            if (type==TOUCH_DOWN){
                res+="touch down, ";
            }else{
                if (type==TOUCH_DRAGGED){
                    res+="touch dragget, ";
                }else{
                    res+="touch up, ";
                }
            }
            return res+=pointer+","+x+","+y;
        }
    }

    public boolean isKeyPressed(int keyCode) throws Exception;
    public boolean isTouchDown(int pointer) throws Exception;

    public int getTouchX(int pointer) throws Exception;
    public int getTouchY(int pointer) throws Exception;

    public float getAccelX()throws Exception;
    public float getAccelY()throws Exception;
    public float getAccelZ()throws Exception;

    public List<KeyEvent> getKeyEvents() throws Exception;
    public List<TouchEvent> getTouchEvents() throws Exception;
}
