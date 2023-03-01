package com.rene.android.reneandroid.Clases.Tipos;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Rene on 29/10/2020.
 */

public enum TipoDeFuente {
    BAUHAUS_93_NORMAL("BAUHS93.TTF"),
    BOOK_ANTIQUA("BKANT.TTF"),
    CHILLER_NORMAL("CHILLER.TTF"),
    COOPER_NEGRA("COOPBL.TTF"),

    HARLOW_SOLID_SEMIEXPANDIDA_CUSRIVA("HARLOWSI.TTF"),
    HARRINGTON_NORMAL("HARNGTON.TTF"),
    JOKERMAN_NORMAL("JOKERMAN.TTF"),
    OLD_ENGLISH_TEXT_MT_NORMAL("OLDENGL.TTF"),
    ;

    private final String direccionCarpeta="fonts",nombre;
    //private final Typeface typeface;
    TipoDeFuente(String nombre) {
        this.nombre=nombre;

    }
    public String getDireccionAsste(){
        return direccionCarpeta+"/"+nombre;
    }
    public Typeface getTypeface(Context c){
        return Typeface.createFromAsset(c.getAssets(),getDireccionAsste());
    }
    public static void setTypeface(Context c,TextView v,TipoDeFuente t){
        v.setTypeface(t.getTypeface(c));
    }
    public static void setTypeface(Context c, Button v, TipoDeFuente t){
        v.setTypeface(t.getTypeface(c));
    }

}
