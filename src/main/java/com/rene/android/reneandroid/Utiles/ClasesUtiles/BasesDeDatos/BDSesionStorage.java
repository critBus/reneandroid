package com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos;

import java.util.Date;

/**
 * Created by Rene on 4/4/2022.
 */

public class BDSesionStorage extends BasicoBD {

    public class BDSesionStorage_Model {

        public String sesion;
        public String propiedad;
        public Object valor;
        public int idkey;

        public BDSesionStorage_Model(String sesion, String propiedad, Object valor) {
            this(sesion, propiedad, valor, -1);
        }

        public BDSesionStorage_Model(String sesion, String propiedad, Object valor, int idkey) {
            this.sesion = sesion;
            this.propiedad = propiedad;
            this.valor = valor;
            this.idkey = idkey;
        }

    }

    public static final String TABLA_SESION_STORAGE = "TABLA_SESION_STORAGE", COLUMNA_SESION = "COLUMNA_SESION", COLUMNA_PROPIEDAD = "COLUMNA_PROPIEDAD", COLUMNA_VALOR = "COLUMNA_VALOR";

    private BDConexion __BD;

    public BDSesionStorage(BDConexion BD) {
        this.__BD = BD;
    }

    public BDSesionStorage createTabla() throws Exception {
        this.__BD.crearTabla(TABLA_SESION_STORAGE,
                COLUMNA_SESION, TipoDeClasificacionSQL.NOT_NULL,
                COLUMNA_PROPIEDAD, TipoDeClasificacionSQL.NOT_NULL,
                COLUMNA_VALOR, TipoDeDatoSQL.BLOB);
        return this;
    }

    public BDSesionStorage_Model get_Args(Object listaArgumentos[]) {
        return new BDSesionStorage_Model(to_String(listaArgumentos[1]),
                to_String(listaArgumentos[2]),
                listaArgumentos[3],
                toInt(listaArgumentos[0]));
    }

    public BDSesionStorage_Model get_id(int id) throws Exception {
        Object O[] = this.__BD.select_forID(
                TABLA_SESION_STORAGE,
                id);
        if (O == null) {
            return null;
        }
        return this.get_Args(O);
    }

    public Object get(String sesion, String propiedad) throws Exception {
        return get(sesion, propiedad, null);
    }

    public Object get(String sesion, String propiedad, Object valorDefault) throws Exception {
        Object O = this.__BD.select_Where_FirstResult(
                TABLA_SESION_STORAGE, COLUMNA_VALOR, COLUMNA_SESION, sesion, COLUMNA_PROPIEDAD, propiedad);
        if (O == null) {
            this.insertar(new BDSesionStorage_Model(
                    sesion,
                    propiedad,
                    valorDefault));
            return valorDefault;
        }
        return O;
    }

    public BDSesionStorage put(String sesion, String propiedad, Object valor) throws Exception {
        Object O[] = this.__BD.select_Where_FirstRow(
                TABLA_SESION_STORAGE, COLUMNA_SESION, sesion, COLUMNA_PROPIEDAD, propiedad);
        if (O == null) {
            this.insertar(new BDSesionStorage_Model(sesion,
                    propiedad,
                    valor));
        } else {
            BDSesionStorage_Model storage = this.get_Args(O);
            this.__BD.update_Id(this.TABLA_SESION_STORAGE, storage.idkey, COLUMNA_VALOR, valor);

        }
        return this;
    }
    public int getInt(String sesion, String propiedad,Object valorDefault) throws Exception{
        return inT(this.get(sesion,propiedad,valorDefault));
    }
    public double getDouble(String sesion, String propiedad,Object valorDefault) throws Exception{
        return dou(this.get(sesion,propiedad,valorDefault));
    }
    public boolean getBool(String sesion, String propiedad,Object valorDefault) throws Exception{
        return toBool(this.get(sesion,propiedad,valorDefault));
    }
    public Date getDate(String sesion, String propiedad, Object valorDefault) throws Exception{
        return toDate(this.get(sesion,propiedad,valorDefault));
    }
    public BDSesionStorage_Model insertar(BDSesionStorage_Model storage) throws Exception {
        if (storage.idkey != -1) {
            int id = this.__BD.insertar(this.TABLA_SESION_STORAGE,
                    storage.sesion,
                    storage.propiedad,
                    storage.valor).id;
            return this.get_id(id);
        } else {
            this.__BD.insertar_SinIdAutomatico(this.TABLA_SESION_STORAGE, storage.idkey,
                    storage.sesion,
                    storage.propiedad,
                    storage.valor);
            return this.get_id(storage.idkey);
        }
    }

}
