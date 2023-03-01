package com.rene.android.reneandroid.Clases.Juegos.Core2D.graficos.Imple;

import android.graphics.Bitmap;

import com.rene.android.reneandroid.Clases.Juegos.Core2D.graficos.Inter.Graficos;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.graficos.Inter.Pixmap;

/**
 * Created by Rene on 24/6/2020.
 */

public class AndroidPixmap implements Pixmap {
    private Bitmap bitmap;
    private Graficos.PixmapFormat pixmapFormat;

    public AndroidPixmap(Bitmap bitmap, Graficos.PixmapFormat pixmapFormat) {
        this.bitmap = bitmap;
        this.pixmapFormat = pixmapFormat;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    @Override
    public Graficos.PixmapFormat getFormat() {
        return pixmapFormat;
    }

    @Override
    public void dispose() {
bitmap.recycle();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Graficos.PixmapFormat getPixmapFormat() {
        return pixmapFormat;
    }
}
