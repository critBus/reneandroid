package com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.handler;

import android.view.MotionEvent;
import android.view.View;

import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Inter.Input;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Inter.TouchHandler;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.Pools.Pool;
import com.rene.android.reneandroid.Clases.Tipos.TipoDeMotionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rene on 24/6/2020.
 */

public class MultiTouchHandler implements TouchHandler {
    private boolean[] isTouched=new boolean[20];
    private int[] touchX=new int[20],touchY=new int[20];
    private Pool<Input.TouchEvent> touchEventPool;
    private List<Input.TouchEvent> touchEvents=new ArrayList<>();
    private List<Input.TouchEvent> touchEventsBuffered=new ArrayList<>();
    private float scaleX,scaleY;

    public MultiTouchHandler(View view, float scaleX, float scaleY) {
        Pool.PoolObjectFactory<Input.TouchEvent> factory=new Pool.PoolObjectFactory<Input.TouchEvent>() {
            @Override
            public Input.TouchEvent createObject() {
                return new Input.TouchEvent();
            }
        };
        touchEventPool=new Pool<>(factory,100);
        view.setOnTouchListener(this);
        this.scaleX=scaleX;
        this.scaleY=scaleY;

    }

    @Override
    public boolean isTouchDown(int pointer) throws Exception {
        synchronized (this){
            if (pointer<0||pointer>=20)
            {
                return false;
            }else {
                return isTouched[pointer];
            }

        }
    }

    @Override
    public int getTouchX(int pointer) throws Exception {
        synchronized (this){
            if (pointer<0||pointer>=20)
            {
                return 0;
            }else {
                return touchX[pointer];
            }
        }
    }

    @Override
    public int getTouchY(int pointer) throws Exception {
        synchronized (this){
            if (pointer<0||pointer>=20)
            {
                return 0;
            }else {
                return touchY[pointer];
            }
        }
    }

    @Override
    public List<Input.TouchEvent> getTouchEvents() throws Exception {
        synchronized (this){
            int len=touchEvents.size();
            for (int i = 0; i <len ; i++) {
                touchEventPool.free(touchEvents.get(i));
            }
            touchEvents.clear();
            touchEvents.addAll(touchEventsBuffered);
            touchEventsBuffered.clear();
            return touchEvents;
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        synchronized (this){
            //Input.TouchEvent touchEvent=touchEventPool.newObject();
            TipoDeMotionEvent tipo= TipoDeMotionEvent.get(event);
            int pointerID= TipoDeMotionEvent.getPointerID(event);
            Input.TouchEvent touchEvent;

            switch (tipo){
                case DOWN:
                case POINTER_DOWN:
                    touchEvent=touchEventPool.newObject();
                    touchEvent.type= Input.TouchEvent.TOUCH_DOWN;
                    touchEvent.pointer=pointerID;
                    if(pointerID>=event.getPointerCount()){
                        System.out.println("salida: break en down por index ");
                        break;}
                    touchEvent.x=touchX[pointerID]=(int)(event.getX(pointerID)*scaleX);
                    touchEvent.y=touchY[pointerID]=(int)(event.getY(pointerID)*scaleY);
                    isTouched[pointerID]=true;
                    touchEventsBuffered.add(touchEvent);
                    break;
                case UP:
                case CANCEL:
                case POINTER_UP:
                    touchEvent=touchEventPool.newObject();
                    touchEvent.type= Input.TouchEvent.TOUCH_UP;
                    touchEvent.pointer=pointerID;
                    if(pointerID>=event.getPointerCount()){
                        System.out.println("salida: break en Up por index ");
                        break;}
                    touchEvent.x=touchX[pointerID]=(int)(event.getX(pointerID)*scaleX);
                    touchEvent.y=touchY[pointerID]=(int)(event.getY(pointerID)*scaleY);
                    isTouched[pointerID]=false;
                    touchEventsBuffered.add(touchEvent);
                    break;
                case MOVE:
                    int pointerEvntCount=event.getPointerCount();

                    //System.out.println("salida: touchX.length="+touchX.length);
                   // System.out.println("salida: pointerEvntCount="+pointerEvntCount);

                    for (int i = 0; i <pointerEvntCount ; i++) {
                        int pointerIndex=i;
                        pointerID=event.getPointerId(pointerIndex);
                        if(pointerID>=pointerEvntCount){
                            System.out.println("salida: break en move por index ");
                            break;}

                      //  System.out.println("salida: pointerID="+pointerID);
                        touchEvent=touchEventPool.newObject();

                        touchEvent.type= Input.TouchEvent.TOUCH_DRAGGED;
                        touchEvent.pointer=pointerID;
                        touchEvent.x=touchX[pointerID]=(int)(event.getX(pointerID)*scaleX);
                        touchEvent.y=touchY[pointerID]=(int)(event.getY(pointerID)*scaleY);
                        isTouched[pointerID]=true;
                        touchEventsBuffered.add(touchEvent);
                    }
                    break;
            }
  return true;
        }
    }
}
