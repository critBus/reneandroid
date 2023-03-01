package com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Imple;

import android.media.SoundPool;

import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Inter.Sonido;

/**
 * Created by Rene on 23/6/2020.
 */

public class AndroidSonido implements Sonido{
    private SoundPool soundPool;
    private int soundID;

    public AndroidSonido(SoundPool soundPool, int soundID) {
        this.soundPool = soundPool;
        this.soundID = soundID;
    }

    @Override
    public void play(float volumen) throws Exception {
soundPool.play(soundID,volumen,volumen,0,0,1);
    }

    @Override
    public void dispose() throws Exception {
soundPool.unload(soundID);
    }
}
