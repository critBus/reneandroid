package com.rene.android.reneandroid.Clases.Juegos.Core2D.juego;

import com.rene.android.reneandroid.Clases.Juegos.Core2D.juego.Inter.Juego;

/**
 * Created by Rene on 24/6/2020.
 */

public abstract class Pantalla {
    protected final Juego juego;

    public Pantalla(Juego juego) {
        this.juego = juego;
    }

    public  abstract void update(float deltaTime) throws Exception;
    public  abstract void present(float deltaTime)throws Exception;
    public  abstract void pause()throws Exception;
    public  abstract void resume()throws Exception;
    public  abstract void dispose()throws Exception;
}
