package com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Inter;

/**
 * Created by Rene on 23/6/2020.
 */

public interface Audio {
    public Musica nuevaMusica(String NombreArchivo) throws Exception;
    public Sonido nuevoSonido(String NombreArchivo) throws Exception;
}
