package com.rene.android.reneandroid.Clases.Tipos;

/**
 * Created by Rene on 17/3/2020.
 */

public enum TipoDePermiso {
    ESCRITURA("WRITE_EXTERNAL_STORAGE"),LECTURA("READ_EXTERNAL_STORAGE"),CAMERA("CAMERA")
    ,LLAMAR("CALL_PHONE"),NO_BLOQUEAR_PANTALLA("WAKE_LOCK"),LEER_CONTACTOS("READ_CONTACTS")
    ,ESCRIBIR_CONTACTOS("WRITE_CONTACTS"),INTERNET("INTERNET"),LOCALIZACION_BUENA("ACCESS_FINE_LOCATION")
    ,LOCALIZACION_REGULAR("ACCESS_COARSE_LOCATION"),VIBRAR("VIBRATE");
    private final  String nombre;
    TipoDePermiso(String nombre){
        this.nombre="android.permission."+nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static String[] getNombres(TipoDePermiso... T){
        String r[]=new String[T.length];
        for (int i=0;i<T.length;i++){
            r[i]=T[i].getNombre();
        }
        return r;
    }
}
