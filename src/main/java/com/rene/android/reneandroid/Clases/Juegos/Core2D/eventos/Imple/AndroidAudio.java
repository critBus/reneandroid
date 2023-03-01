package com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Imple;

import android.app.Activity;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Inter.Audio;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Inter.Musica;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Inter.Sonido;

/**
 * Created by Rene on 23/6/2020.
 */

public class AndroidAudio implements Audio {
      private  AssetManager asset;
     private SoundPool soundPool;

    public AndroidAudio(Activity a) {
        a.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        asset=a.getAssets();
        soundPool=new SoundPool(20,AudioManager.STREAM_MUSIC,0);

    }

    @Override
    public Musica nuevaMusica(String NombreArchivo) throws Exception {
        return new AndroidMusica(asset.openFd(NombreArchivo));
    }

    @Override
    public Sonido nuevoSonido(String filename) throws Exception {
        return new AndroidSonido(soundPool,soundPool.load(asset.openFd(filename),0));
    }
}
