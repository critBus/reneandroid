package com.rene.android.reneandroid.Clases.Tipos;

import android.media.AudioManager;

/**
 * Created by Rene on 16/6/2020.
 */

public enum TipoDeControlDeVolumen {
    DEFAULT(AudioManager.USE_DEFAULT_STREAM_TYPE),MUSIC(AudioManager.STREAM_MUSIC),ALARM(AudioManager.STREAM_ALARM)
    ,TONOS(AudioManager.STREAM_DTMF),NOTIFICACIONES(AudioManager.STREAM_NOTIFICATION),TIMBRE(AudioManager.STREAM_RING)
    ,SISTEMA(AudioManager.STREAM_SYSTEM),LLAMADA(AudioManager.STREAM_VOICE_CALL);
    private final int valor;

    TipoDeControlDeVolumen(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
