package com.rene.android.reneandroid.Clases.Juegos.Core2D.juego;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.rene.android.reneandroid.Clases.Juegos.Core2D.juego.Impl.AndroidJuegoAbstracta;

/**
 * Created by Rene on 24/6/2020.
 */

public class AndroidFastRenderView extends SurfaceView implements Runnable {
    private AndroidJuegoAbstracta juego;
    private Bitmap framebuffer;
    private Thread renderThread;
    private SurfaceHolder holder;
    private volatile boolean running=false;

    public AndroidFastRenderView(AndroidJuegoAbstracta androidJuegoAbstracta, Bitmap framebuffer) {
        super(androidJuegoAbstracta);
        this.juego= androidJuegoAbstracta;
        this.framebuffer=framebuffer;
        holder=getHolder();
    }



   // public AndroidFastRenderView(Context context) {
     //   super(context);
       // holder=getHolder();
    //}

    public void resume(){
        running=true;
        renderThread=new Thread(this);
        renderThread.start();
    }


    public void pause(){
        running=false;

        while (true){
            try{
                 renderThread.join();
            }catch (Exception ex){

            }
        }

    }

    @Override
    public void run() {
        try{
          //  System.out.println("salida: se intenta pintar runing="+running);
        Rect dstRect=new Rect();
        long starTime=System.nanoTime();
        while (running){
            /* */ if(!holder.getSurface().isValid()){
                //System.out.println("salida: no fue valido el surface");
                continue;
            }
            float deltaTime=(System.nanoTime()-starTime)/1000000000.0f;
            starTime=System.nanoTime();

            juego.getCurrentScreen().update(deltaTime);
            juego.getCurrentScreen().present(deltaTime);


            Canvas canvas=holder.lockCanvas();
            if(canvas!=null){
                canvas.getClipBounds(dstRect);
              //  System.out.println("salida: se pinta");
                //canvas.drawRGB(250,0,0);
                canvas.drawBitmap(framebuffer,null,dstRect,null);
                holder.unlockCanvasAndPost(canvas);
            }


        }
    }catch (Exception ex){
        ex.printStackTrace();
    }
    }
}

