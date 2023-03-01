package com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rene on 30/10/2020.
 */

public abstract class Utilizar_Parcelable<E> implements Utilizar<E>,Parcelable {
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // dest.
    }
}
