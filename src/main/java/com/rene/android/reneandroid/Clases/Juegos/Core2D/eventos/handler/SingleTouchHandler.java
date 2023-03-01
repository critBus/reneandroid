package com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.handler;

import android.view.MotionEvent;
import android.view.View;

import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Inter.Input;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Inter.TouchHandler;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.Pools.Pool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rene on 24/6/2020.
 */

public class SingleTouchHandler implements TouchHandler {
    private boolean isTouched;
    private int touchX,touchY;
    private Pool<Input.TouchEvent> touchEventPool;
    private List<Input.TouchEvent> touchEvents=new ArrayList<>();
    private List<Input.TouchEvent> touchEventsBuffered=new ArrayList<>();
    private float scaleX,scaleY;

    public SingleTouchHandler(View view,float scaleX,float scaleY) {
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
          if (pointer==0)
          {return isTouched;
          }else {
          return false;
          }

      }
    }

    @Override
    public int getTouchX(int pointer) throws Exception {
        synchronized (this){
            return touchX;
        }
    }

    @Override
    public int getTouchY(int pointer) throws Exception {
        synchronized (this){
            return touchY;
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
          Input.TouchEvent touchEvent=touchEventPool.newObject();
          switch (event.getAction()){
              case MotionEvent.ACTION_DOWN:
                  touchEvent.type= Input.TouchEvent.TOUCH_DOWN;
                  isTouched=true;
                  break;
              case MotionEvent.ACTION_MOVE:
                  touchEvent.type= Input.TouchEvent.TOUCH_DRAGGED;
                  isTouched=true;
                  break;
              case MotionEvent.ACTION_CANCEL:
              case MotionEvent.ACTION_UP:
                  touchEvent.type= Input.TouchEvent.TOUCH_UP;
                  isTouched=false;
                  break;
          }
          touchEvent.x=touchX=(int)(event.getX()*scaleX);
          touchEvent.y=touchY=(int)(event.getY()*scaleY);
          touchEventsBuffered.add(touchEvent);
          return true;
      }
    }
}
