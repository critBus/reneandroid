package com.rene.android.reneandroid.Clases.Juegos.Core2D.graficos.Inter;

/**
 * Created by Rene on 24/6/2020.
 */

public interface Pixmap {
    public int getWidth();
    public int getHeight();
    public Graficos.PixmapFormat getFormat();
    public void dispose();

}
