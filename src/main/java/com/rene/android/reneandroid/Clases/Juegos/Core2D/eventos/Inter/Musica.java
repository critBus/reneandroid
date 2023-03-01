package com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Inter;

/**
 * Created by Rene on 23/6/2020.
 */

public interface Musica {
    public void play() throws Exception;
    public void stop() throws Exception;
    public void pause() throws Exception;

    public void setLooping(boolean looping) throws Exception;
    public void setVolumen(float volumen) throws Exception;


    public boolean isLooping() throws Exception;
    public boolean isPlaying() throws Exception;
    public boolean isPause() throws Exception;



    public void dispose() throws Exception;

}
