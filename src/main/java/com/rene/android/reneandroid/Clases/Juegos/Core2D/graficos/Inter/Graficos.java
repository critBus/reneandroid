package com.rene.android.reneandroid.Clases.Juegos.Core2D.graficos.Inter;

/**
 * Created by Rene on 24/6/2020.
 */

public interface Graficos {
    public static enum PixmapFormat{
        ARGB8888,ARGB4444,RGB565
    }

    public Pixmap newPixmap(String fileName,PixmapFormat format) throws Exception;
    public void clear(int Color) throws Exception;
    public void drawPixel(int x,int y,int Color) throws Exception;
    public void drawLine(int x,int y,int x2,int y2,int Color) throws Exception;
    public void drawRect(int x,int y,int width,int height,int Color) throws Exception;
    public void drawPixmap(Pixmap pixmap,int x,int y,int srcX,int srcY,int srcWidth,int srcHeight) throws Exception;
    public void drawPixmap(Pixmap pixmap,int x,int y) throws Exception;

    public int getWidth();
    public int getHeight();

}
