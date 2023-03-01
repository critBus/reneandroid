package com.rene.android.reneandroid.Clases.BD;

import com.rene.android.reneandroid.UtilAndroid;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos.BasicoBD;

import org.joda.time.DateTime;

/**
 * Created by Rene on 5/4/2022.
 */

public abstract class BasicoBDAndroid extends BasicoBD {
    public DateTime toJodaDateTime(Object o){
        if(o==null){
            return null;
        }
        String a=o.toString().trim();
        return  UtilAndroid.getDateTimeForStrBD(a);

    }
}
