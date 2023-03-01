package com.rene.android.reneandroid.Clases.Juegos.Core2D.juego.Inter;

import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Inter.Audio;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Inter.FileIO;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Inter.Input;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.graficos.Inter.Graficos;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.juego.Pantalla;

/**
 * Created by Rene on 24/6/2020.
 */

public interface Juego {
    public Input getInput() throws Exception;
    public FileIO getFileIo() throws Exception;
    public Graficos getGraficos() throws Exception;
    public Audio getAudio() throws Exception;
    public void setScreen(Pantalla pantalla) throws Exception;
    public Pantalla getCurrentScreen() throws Exception;
    public Pantalla getStartScreen() throws Exception;



}
