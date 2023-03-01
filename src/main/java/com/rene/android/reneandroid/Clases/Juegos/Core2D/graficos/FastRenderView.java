package com.rene.android.reneandroid.Clases.Juegos.Core2D.graficos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.rene.android.reneandroid.Clases.Juegos.Core2D.juego.Inter.Juego;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar;

/**
 * Created by Rene on 24/6/2020.
 */

public class FastRenderView extends SurfaceView implements Runnable {
    private Thread renderThread = null;
    private SurfaceHolder holder;
    private volatile boolean running = false;

    private Utilizar<Canvas> pintarPantalla;
    private Juego juego;

    public FastRenderView(Context context, Utilizar<Canvas> pintarPantalla) {
        super(context);
        holder = getHolder();
        juego=(Juego)context;
    }

    public void resume() {
        running = true;
        renderThread = new Thread(this);
        renderThread.start();
    }


    public void pause() {
        running = false;
        while (true) {
            try {
                renderThread.join();
            } catch (Exception ex) {

            }
        }

    }

    @Override
    public void run() {
        try {
            Rect dstRect = new Rect();
            long starTime = System.nanoTime();
            while (running) {
                if (!holder.getSurface().isValid()) {
                    continue;
                }
                float deltaTime = (System.nanoTime() - starTime) / 1000000000.0f;
                starTime = System.nanoTime();

                juego.getCurrentScreen().update(deltaTime);
                juego.getCurrentScreen().present(deltaTime);


                Canvas canvas = holder.lockCanvas();
                pintarPantalla.utilizar(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
