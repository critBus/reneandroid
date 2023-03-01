package com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Imple;

import android.content.res.AssetManager;
import android.os.Environment;

import com.rene.android.reneandroid.Clases.Juegos.Core2D.eventos.Inter.FileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Rene on 23/6/2020.
 */

public class AndroidFileIO implements FileIO {
    AssetManager asset;
    String rutaAlmecanientoExterno;

    public AndroidFileIO(AssetManager asset) {
        this.asset = asset;
        rutaAlmecanientoExterno= Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator;
    }

    @Override
    public InputStream leerAsset(String nombreArchivo) throws Exception {
        return asset.open(nombreArchivo);
    }

    @Override
    public InputStream leerArchivo(String nombreArchivo) throws Exception {
        return new FileInputStream(rutaAlmecanientoExterno+nombreArchivo);
    }

    @Override
    public OutputStream escribirArchivo(String nombreArchivo) throws Exception {
        return new FileOutputStream(rutaAlmecanientoExterno+nombreArchivo);
    }
}
