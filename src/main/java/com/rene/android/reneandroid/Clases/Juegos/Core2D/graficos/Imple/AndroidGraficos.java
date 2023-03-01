package com.rene.android.reneandroid.Clases.Juegos.Core2D.graficos.Imple;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.rene.android.reneandroid.Clases.Juegos.Core2D.graficos.Inter.Graficos;
import com.rene.android.reneandroid.Clases.Juegos.Core2D.graficos.Inter.Pixmap;

import java.io.InputStream;

/**
 * Created by Rene on 24/6/2020.
 */

public class AndroidGraficos implements Graficos {
    private AssetManager assetManager;
    private Bitmap frameBuffer;
    private Canvas canvas;
    private Paint paint;
    private Rect srcRect=new Rect();
    private Rect dstRect=new Rect();

    public AndroidGraficos(AssetManager assetManager, Bitmap frameBuffer) {
        this.assetManager = assetManager;
        this.frameBuffer = frameBuffer;
        canvas=new Canvas(frameBuffer);
        paint=new Paint();
    }

    @Override
    public Pixmap newPixmap(String fileName, PixmapFormat format) throws Exception {
        Bitmap.Config config=null;
        if (format==PixmapFormat.RGB565){
config=Bitmap.Config.RGB_565;
        }else{if (format==PixmapFormat.ARGB4444){
            config=Bitmap.Config.ARGB_4444;
        }else{
            config=Bitmap.Config.ARGB_8888;
        }}

        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inPreferredConfig=config;

        InputStream in=null;
        Bitmap bitmap=null;

        try{
            //assetManager.
            in=assetManager.open(fileName);
             bitmap=BitmapFactory.decodeStream(in);
            //bitmap=BitmapFactory.decodeStream(in,null,options);
            if (bitmap==null){
                throw new RuntimeException("No se a podido cargar bits de "+fileName);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            if (in!=null){
                try{
                    in.close();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }

        if(bitmap.getConfig()== Bitmap.Config.RGB_565){
format=PixmapFormat.RGB565;
        }else{
            if(bitmap.getConfig()== Bitmap.Config.ARGB_4444){
                format=PixmapFormat.ARGB4444;
            }else{
                format=PixmapFormat.ARGB8888;
            }
        }


        return new AndroidPixmap(bitmap,format);
    }

    @Override
    public void clear(int color) throws Exception {
canvas.drawRGB((color&0xff0000)>>16,(color&0xff00)>>8,(color&0xff));
    }

    @Override
    public void drawPixel(int x, int y, int color) throws Exception {
paint.setColor(color);
        canvas.drawPoint(x,y,paint);
    }

    @Override
    public void drawLine(int x, int y, int x2, int y2, int color) throws Exception {
        paint.setColor(color);
        canvas.drawLine(x,y,x2,y2,paint);
    }

    @Override
    public void drawRect(int x, int y, int width, int height, int color) throws Exception {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawLine(x,y,x+width-1,y+height-1,paint);
    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight) throws Exception {
     srcRect.left=srcX;
        srcRect.top=srcY;
        srcRect.right=srcX+srcWidth-1;
        srcRect.bottom=srcY+srcHeight-1;

        dstRect.left=x;
        dstRect.top=y;
        dstRect.right=x+srcWidth-1;
        dstRect.bottom=y+srcHeight-1;

        canvas.drawBitmap(((AndroidPixmap)pixmap).getBitmap(),srcRect,dstRect,null);

    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y) throws Exception {
        canvas.drawBitmap(((AndroidPixmap)pixmap).getBitmap(),x,y,null);
    }

    @Override
    public int getWidth() {
        return frameBuffer.getWidth();
    }

    @Override
    public int getHeight() {
        return frameBuffer.getHeight();
    }
}
