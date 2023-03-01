package com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos;

import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Creador1ConException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rene on 4/4/2022.
 */

public class BDUpdates extends BasicoBD {

    public static final Pattern PATRON_BDUPDATE_COLUMNA_INFORMACION = Pattern.compile("[<][{](.*)[}][>]=[<][{](.*)");
    public static final String TIPO_DE_MOTIVO_NO_ESPECIFICADO = "TIPO_DE_MOTIVO_NO_ESPECIFICADO",
            TIPO_DE_MOTIVO_CREADO = "TIPO_DE_MOTIVO_CREADO";

    public class BDUpdate_Model {

        public int idKey_Tabla;
        public String nombreTabla;
        public byte[] contenido;
        private String __contenidoStr;
        private HashMap<String, String> __contenidoDic;
        public String motivo;
        public Date fecha;
        public int idKey;
        private Object[] __rowObj;

        public BDUpdate_Model(int idKey_Tabla, String nombreTabla, byte[] contenido, String motivo, Date fecha) {
            this(idKey_Tabla, nombreTabla, contenido, motivo, fecha, -1);
        }

        public BDUpdate_Model(int idKey_Tabla, String nombreTabla, byte[] contenido, String motivo, Date fecha, int idKey) {
            if (motivo == null || motivo.trim().isEmpty()) {
                motivo = TIPO_DE_MOTIVO_NO_ESPECIFICADO;
            }
            if (fecha == null) {
                fecha = new Date();
            }

            this.idKey_Tabla = idKey_Tabla;
            this.nombreTabla = nombreTabla;
            this.contenido = contenido;
            this.motivo = motivo;
            this.fecha = fecha;
            this.idKey = idKey;
        }

        public String getContenidoStr() {
            if (this.__contenidoStr == null) {
                this.__contenidoStr = fromBlob(this.contenido);
            }
            return this.__contenidoStr;
        }

        public String getValorEnColumna(String nombreColumna) {
            if (this.__contenidoDic == null) {
                this.getRowObj();
            }
            return this.__contenidoDic.get(nombreColumna);
        }

        public Object[] getRowObj() {
            if (this.__rowObj == null) {
                List l = new ArrayList();
                if (this.idKey_Tabla != -1) {
                    l.add(this.idKey_Tabla);
                }
                this.__contenidoDic = new HashMap<String, String>();

                String lista[] = this.getContenidoStr().split("}> ");
                for (int i = 0; i < lista.length; i++) {
                    Matcher m = PATRON_BDUPDATE_COLUMNA_INFORMACION.matcher(lista[i]);
                    if (m.find() && m.groupCount() > 0) {
                        this.__contenidoDic.put(m.group(1), m.group(2));
                        l.add(m.group(2));
                    }
                }

                this.__rowObj = l.toArray(new Object[0]);
            }
            return this.__rowObj;
        }

        public void print() {
            BDUpdate_Model u = this;
            System.out.println("BDUpdate_Model: idKey=" + u.idKey + " idKey_Tabla=" + u.idKey_Tabla + "\ncontenido=" + u.getContenidoStr() + "\nmotivo=" + u.motivo + " fecha=" + u.fecha);
        }

    }

    public static final String TABLA_UPDATES = "TABLA_UPDATES",
            COLUMNA_ID_TABLA = "COLUMNA_ID_TABLA",
            COLUMNA_NOMBRE_TABLA = "COLUMNA_NOMBRE_TABLA",
            COLUMNA_CONTENIDO = "COLUMNA_CONTENIDO",
            COLUMNA_MOTIVO = "COLUMNA_MOTIVO",
            COLUMNA_FECHA = "COLUMNA_FECHA";
    private BDConexion __BD;

    public BDUpdates(BDConexion BD) {
        this.__BD = BD;
    }

    public BDUpdates createTabla() throws Exception {
        this.__BD.crearTabla(TABLA_UPDATES,
                COLUMNA_ID_TABLA, TipoDeClasificacionSQL.NOT_NULL,
                COLUMNA_NOMBRE_TABLA, 50, TipoDeClasificacionSQL.NOT_NULL,
                COLUMNA_CONTENIDO, TipoDeDatoSQL.BLOB, TipoDeClasificacionSQL.NOT_NULL,
                COLUMNA_MOTIVO, 50, TipoDeClasificacionSQL.NOT_NULL,
                COLUMNA_FECHA, TipoDeClasificacionSQL.NOT_NULL);
        return this;
    }

    private BDUpdate_Model __get_Args(Object listaArgumentos[]) {
        return new BDUpdate_Model(
                toInt(listaArgumentos[1]),
                to_String(listaArgumentos[2]),
                toBlob(listaArgumentos[3]),
                to_String(listaArgumentos[4]),
                toDate(listaArgumentos[5]),
                toInt(listaArgumentos[0])
        );
    }

    public BDUpdate_Model get(String nombreTabla, int idTabla, Date fecha) throws Exception {
        return this.__get_Args(this.__BD.select_Where_FirstRow(TABLA_UPDATES,
                COLUMNA_NOMBRE_TABLA, nombreTabla,
                COLUMNA_ID_TABLA, idTabla,
                COLUMNA_FECHA, fecha));
    }

    public BDUpdate_Model addM(int idKey_Tabla, String nombreTabla, Object contenido) throws Exception {
        return addM(idKey_Tabla,nombreTabla,contenido,null,null);
    }

    public BDUpdate_Model addM(int idKey_Tabla, String nombreTabla, Object contenido, String motivo, Date fecha) throws Exception {
        if (motivo == null) {
            motivo = TIPO_DE_MOTIVO_NO_ESPECIFICADO;
        }
        if (esArreglo(contenido)) {
            String c = "";
            Object l[] = (Object[]) contenido;
            for (int i = 0; i < l.length; i++) {
                c += "<{" + l[0] + "}>=<{" + l[1] + "}> ";
            }

            contenido = c;
        }
        contenido = toBlob(contenido);
        return this.__add(new BDUpdate_Model(idKey_Tabla, nombreTabla, (byte[]) contenido, motivo, fecha));
    }

    public BDUpdate_Model __add(BDUpdate_Model update) throws Exception {
        if (update.idKey != -1) {
            this.__BD.insertar(TABLA_UPDATES, update.idKey_Tabla,
                    update.nombreTabla, update.contenido,
                    update.motivo, update.fecha);
        } else {
            this.__BD.insertar(TABLA_UPDATES, update.idKey, update.idKey_Tabla,
                    update.nombreTabla, update.contenido,
                    update.motivo, update.fecha);
        }
        return this.get(update.nombreTabla, update.idKey_Tabla, update.fecha);
    }

    public List<BDUpdate_Model> getAll() throws Exception{
        Object O[][] = this.__BD.select_Todo(TABLA_UPDATES);
        List<BDUpdate_Model> l=new ArrayList<>();
        for (int i = 0; i < O.length; i++) {
            l.add(__get_Args(O[i]));
        }
        return l;
    }

    public<E> List<E> getInstanciasDesc_id(String nombreTabla,int id,Creador1ConException<Object[],E> creador_Args) throws Exception{
        Object O[][]=this.__BD.select_Where_ORDER_BY(TABLA_UPDATES
                ,new Object[]{COLUMNA_NOMBRE_TABLA,nombreTabla
                        ,COLUMNA_ID_TABLA,id}
                ,COLUMNA_FECHA,TipoDeOrdenamientoSQL.DESC);
        List<E> l=new ArrayList<>();
        for (int i = 0; i < O.length; i++) {
            l.add(creador_Args.crear(this.__get_Args(O[i]).getRowObj()));

        }
        return l;

    }

    public BDUpdate_Model getLastUpdate(String nombreTabla,int id) throws Exception{
        Object O[]=this.__BD.select_FirstRow_Where_ValorMaximo(TABLA_UPDATES,COLUMNA_FECHA
                ,COLUMNA_NOMBRE_TABLA,nombreTabla
                ,COLUMNA_ID_TABLA,id);
        return this.__get_Args(O);}
    public<E> E getLastInstancia(String nombreTabla,int id,Creador1ConException<Object[],E> creador_Args) throws Exception{
        return creador_Args.crear(this.getLastUpdate(nombreTabla,id).getRowObj());
    }
    public<E> E getInstancia(int idUpdate,Creador1ConException<Object[],E> creador_Args) throws Exception{
        Object[] O=this.__BD.select_forID(TABLA_UPDATES,idUpdate);
        if (O==null){
            return null;}
        return creador_Args.crear(this.__get_Args(O).getRowObj());
    }
}
