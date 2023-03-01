package com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Imple;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Inter.Musica;

/**
 * Created by Rene on 23/6/2020.
 */

public class AndroidMusica implements Musica,MediaPlayer.OnCompletionListener {
    private AssetFileDescriptor assetFileDescriptor;
    private MediaPlayer mediaPlayer;
    private boolean isPrepared=false;

    public AndroidMusica(AssetFileDescriptor assetFileDescriptor) {
        this.assetFileDescriptor = assetFileDescriptor;
        mediaPlayer=new MediaPlayer();
        try{
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),assetFileDescriptor.getStartOffset(),assetFileDescriptor.getLength());
            mediaPlayer.prepare();
            isPrepared=true;
            mediaPlayer.setOnCompletionListener(this);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void play() throws Exception {
if (mediaPlayer.isPlaying()){
    return;
 }
 synchronized (this){
     if(!isPrepared){
         mediaPlayer.prepare();
         mediaPlayer.start();
     }
 }
    }

    @Override
    public void stop() throws Exception {
mediaPlayer.stop();
        synchronized (this){
            isPrepared=false;
        }
    }

    @Override
    public void pause() throws Exception {

        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }

    }

    @Override
    public void setLooping(boolean looping) throws Exception {
mediaPlayer.setLooping(looping);
    }

    @Override
    public void setVolumen(float volumen) throws Exception {
mediaPlayer.setVolume(volumen,volumen);
    }

    @Override
    public boolean isLooping() throws Exception {
        return mediaPlayer.isLooping();
    }

    @Override
    public boolean isPlaying() throws Exception {
        return mediaPlayer.isPlaying();
    }

    @Override
    public boolean isPause() throws Exception {
        return !isPrepared;
    }

    @Override
    public void dispose() throws Exception {
if(mediaPlayer.isPlaying()){
    mediaPlayer.stop();
    mediaPlayer.release();
}
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
synchronized (this){
 isPrepared=false;
}
    }
}
