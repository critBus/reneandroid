package com.rene.android.reneandroid.Clases.Juegos.Core2D.juego.Impl;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Imple.AndroidAudio;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Imple.AndroidFileIO;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Imple.AndroidInput;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Inter.Audio;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Inter.FileIO;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Inter.Input;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.graficos.Imple.AndroidGraficos;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.graficos.Inter.Graficos;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.juego.AndroidFastRenderView;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.juego.Inter.Juego;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.juego.Pantalla;

/**
 * Created by Rene on 24/6/2020.
 */

public abstract class AndroidJuegoAbstracta extends Activity implements Juego {

    //SurfaceImple renderView;
    AndroidFastRenderView renderView;
    Graficos graficos;
    Audio audio;
    Input input;
    FileIO fileIO;
    Pantalla pantalla;
   PowerManager.WakeLock wakeLock;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
           // VisualAndroid.pedirPermiso(this, TipoDePermiso.NO_BLOQUEAR_PANTALLA,TipoDePermiso.ESCRITURA,TipoDePermiso.LECTURA);
           // pedirPermiso(TipoDePermiso.NO_BLOQUEAR_PANTALLA,TipoDePermiso.ESCRITURA,TipoDePermiso.LECTURA);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
            int frameBufferWidth = isLandscape ? 480 : 320;
            int frameBufferHeight = isLandscape ? 320 : 480;
            Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight, Bitmap.Config.RGB_565);

            float scaleX = (float) frameBufferWidth / getWindowManager().getDefaultDisplay().getWidth();
            float scaley = (float) frameBufferHeight / getWindowManager().getDefaultDisplay().getHeight();

            //renderView = new SurfaceImple(this, frameBuffer,(SurfaceView)findViewById(R.id.surfaceView2));
            renderView = new AndroidFastRenderView(this, frameBuffer);
            graficos = new AndroidGraficos(getAssets(), frameBuffer);
            fileIO = new AndroidFileIO(getAssets());

           // Configuraciones.save(fileIO);

            audio = new AndroidAudio(this);
           // input = new AndroidInput(this, renderView.getSurface(), scaleX, scaley);
            input = new AndroidInput(this, renderView, scaleX, scaley);
            pantalla = getStartScreen();
            System.out.println("salida: se pone le render");
           // setContentView(renderView.getSurface());
            setContentView(renderView);


            //renderView.resume();
            //setContentView(R.layout.activity_main);

            PowerManager powerManager=(PowerManager)getSystemService(Context.POWER_SERVICE);
            wakeLock=powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,"GLGame");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        try {
            System.out.println("salida: se resume acitivity");
            wakeLock.acquire();
            System.out.println("salida: va a resumir pantalla");

            pantalla.resume();
            System.out.println("salida: va a resumir render");
            renderView.resume();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try{
            System.out.println("salida: se pausa acitivity");
            wakeLock.release();
            System.out.println("salida: se va a pausar el render");
        renderView.pause();
        pantalla.pause();
        if(isFinishing()){
            pantalla.dispose();
        }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Input getInput() throws Exception {
        return input;
    }

    @Override
    public FileIO getFileIo() throws Exception {
        return fileIO;
    }

    @Override
    public Graficos getGraficos() throws Exception {
        return graficos;
    }

    @Override
    public Audio getAudio() throws Exception {
        return audio;
    }

    @Override
    public void setScreen(Pantalla pantalla) throws Exception {
       if(pantalla ==null){
           throw new IllegalArgumentException("Pantalla no debe ser null");
       }
        this.pantalla.pause();
       this.pantalla.dispose();
        pantalla.resume();
        pantalla.update(0);
        this.pantalla=pantalla;
    }

    @Override
    public Pantalla getCurrentScreen() throws Exception {
        return pantalla;
    }


}
