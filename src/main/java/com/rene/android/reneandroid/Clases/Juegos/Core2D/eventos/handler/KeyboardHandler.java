package com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.handler;

import android.view.KeyEvent;
import android.view.View;

import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Inter.Input;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.Pools.Pool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rene on 24/6/2020.
 */

public class KeyboardHandler implements View.OnKeyListener {
   private boolean  []pressedKeys=new boolean[128];
   private Pool<Input.KeyEvent> keyEventPool;
    private List<Input.KeyEvent> keyEventsBuffer=new ArrayList<>();
    private List<Input.KeyEvent> keyEvents=new ArrayList<>();

    public KeyboardHandler(View view){
        Pool.PoolObjectFactory<Input.KeyEvent> factory=new Pool.PoolObjectFactory<Input.KeyEvent>() {
            @Override
            public Input.KeyEvent createObject() {
                return new Input.KeyEvent();
            }
        };
        keyEventPool=new Pool<>(factory,100);
        view.setOnKeyListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction()== KeyEvent.ACTION_MULTIPLE){
            return false;
        }
        synchronized (this){
            Input.KeyEvent keyEvent=keyEventPool.newObject();
            keyEvent.keyCode=keyCode;
            keyEvent.keyChar=(char)event.getUnicodeChar();
            if (event.getAction()== KeyEvent.ACTION_DOWN){
                keyEvent.type=Input.KeyEvent.KEY_DOWN;
                if(keyCode>0&&keyCode<127){
                    pressedKeys[keyCode]=true;
                }
            }
            if (event.getAction()== KeyEvent.ACTION_UP){
                keyEvent.type=Input.KeyEvent.KEY_UP;
                if(keyCode>0&&keyCode<127){
                    pressedKeys[keyCode]=true;
                    keyEventsBuffer.add(keyEvent);
                }
            }
        }
    return true;
    }

    public boolean isKeyPressed(int keyCode){
        if(keyCode<0||keyCode>127){
            return false;
        }
        return  pressedKeys[keyCode];
    }

    public List<Input.KeyEvent> getKeyEvents(){
        synchronized (this){
            int len=keyEvents.size();
            for (int i = 0; i <len ; i++) {
                keyEventPool.free(keyEvents.get(i));
            }
            keyEvents.clear();
            keyEvents.addAll(keyEventsBuffer);
            keyEventsBuffer.clear();
            return keyEvents;
        }


    }
}
