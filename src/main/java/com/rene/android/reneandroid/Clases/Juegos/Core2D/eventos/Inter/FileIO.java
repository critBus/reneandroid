package com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Inter;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Rene on 23/6/2020.
 */

public interface FileIO {

    public InputStream leerAsset(String nombreArchivo) throws Exception;
    public InputStream leerArchivo(String nombreArchivo) throws Exception;
    public OutputStream escribirArchivo(String nombreArchivo) throws Exception;
}
