/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rene.android.reneandroid.Utiles.MetodosUtiles;

import com.rene.android.reneandroid.UtilAndroid;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos.TipoDeDatoSQL;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos.TipoDeClasificacionSQL;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos.TipoDeOrdenamientoSQL;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Creador2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.rene.android.reneandroid.Utiles.MetodosUtiles.MetodosUtiles.or;
//import javafx.util.Callback;

/**
 *
 * @author Rene
 */
public abstract class SQLUtil {

    public static final String CREATE = "CREATE",
            TABLE = "TABLE",
            CREATE_TABLE = CREATE + " " + TABLE,
            PRIMARY_KEY = "PRIMARY KEY",
            INSERT = "INSERT",
            INTO = "INTO",
            INSERT_INTO = INSERT + " " + INTO,
            VALUES = "VALUES",
            SELECT = "SELECT",
            MAX = "MAX",
            MIN = "MIN",
            AVG = "AVG",
            SELECT_MAX = SELECT + " " + MAX,
            SELECT_MIN = SELECT + " " + MIN,
            SELECT_AVG = SELECT + " " + AVG,
            FROM = "FROM",
            DROP = "DROP",
            DROP_TABLE = DROP + " " + TABLE,
            COPY = "COPY",
            WHERE = "WHERE",
            ORDER_BY = "ORDER BY",
            DISTINCT = "DISTINCT",
            SELECT_DISTINCT = SELECT + " " + DISTINCT,
            INNER_JOIN = "INNER JOIN",
            ON = "ON",
            GROUP_BY = "GROUP BY",
            HAVING = "HAVING",
            COUNT = "COUNT",
            SUM = "SUM",
            SELECT_COUNT = SELECT + " " + COUNT,
            SELECT_SUM = SELECT + " " + SUM,
            UPDATE = "UPDATE",
            SET = "SET",
            DELETE = "DELETE",
            IF_EXISTS = "IF EXISTS",
            DROP_TABLE_IF_EXISTS = DROP_TABLE + " " + IF_EXISTS,
            AUTOINCREMENT = "AUTOINCREMENT",
            IF_NOT_EXISTS = "IF NOT EXISTS",
            CREATE_TABLE_IF_NOT_EXISTS = CREATE_TABLE + " " + IF_NOT_EXISTS,
            DEFAULT = "DEFAULT",
            ALTER = "ALTER",
            ALTER_TABLE = ALTER + " " + TABLE,
            COLUMN = "COLUMN",
            DROP_COLUMN = DROP + " " + COLUMN,
            CASCADE = "CASCADE",
            RENAME = "RENAME",
            RENAME_COLUMN = RENAME + " " + COLUMN,
            TO = "TO",
            ADD = "ADD",
            ADD_COLUMN = ADD + " " + COLUMN,
            NOT_NULL = "NOT NULL";

    private static class Configuracion_De_Columnas {

        public HashMap<String, Set<String>> columnasSeleccionadas;

        public Configuracion_De_Columnas() {
            columnasSeleccionadas = new HashMap<>();
        }

        public String getNombreDeTabla(int indice) {
            return columnasSeleccionadas.keySet().toArray(new String[0])[indice];
        }
//        public String[] getNombreTablas(int indice) {
//
//            Set<String> s = columnasSeleccionadas.get(columnasSeleccionadas.keySet().toArray(new String[0])[indice]);
//            return s.toArray(new String[0]);
//
//        }

        public Set<String> get(String nombreTabla) {
            return columnasSeleccionadas.get(nombreTabla);
        }

        public Set<String> add(String nombreTabla) {

            if (columnasSeleccionadas.containsKey(nombreTabla)) {
                return get(nombreTabla);
            }
            Set<String> l = new HashSet<>();
            columnasSeleccionadas.put(nombreTabla, l);
            return l;
        }

        public Set<String> addSiNoContiene(String nombreTabla, String columna) {

            if (columnasSeleccionadas.containsKey(nombreTabla)) {
                Set<String> l = get(nombreTabla);
                l.add(columna);
                return l;
            }
//            System.out.println("Agrego nuevo");
            Set<String> l = new HashSet<>();
            l.add(columna);
            columnasSeleccionadas.put(nombreTabla, l);
            return l;
        }

        public String getParTC_siSeEncuentra(String columna) {
            String keys[] = columnasSeleccionadas.keySet().toArray(new String[0]);
            for (int i = 0; i < keys.length; i++) {
                if (columnasSeleccionadas.get(keys[i]).contains(columna)) {
                    return keys[i] + "." + columna;
                }
            }
            return columna;
        }

        public String getC_T(Object[][] matriz, int indice) {
            String c = matriz.length == 1 ? "id" : matriz[indice][1].toString();//posiblemente sea este mal y sea  en vez de matriz.length sea matriz[indice].length
            String t = matriz[indice][0].toString();
//            System.out.println("t="+t);
            addSiNoContiene(t, c);
            return t + "." + c;
        }

        public String getC_T(Object[] lista) {

            String c = lista.length == 1 ? "id" : lista[1].toString();
            String t = lista[0].toString();
//            System.out.println("t="+t);
            addSiNoContiene(t, c);
            return t + "." + c;
        }
//        public List<String> nombreTalas;
//        public List<List<String>> nombreTalas;
    }

    public static String __getStr_ORDER_BY(Object a[], int inicioDeColumnas) {
        String sql = "";

        for (int i = inicioDeColumnas; i < a.length; i++) {
            boolean esOrdenamiento = TipoDeOrdenamientoSQL.esTipoDeOrdenamientoSQL(a[i]);
            if (esOrdenamiento) {
                sql += " " + ((TipoDeOrdenamientoSQL) a[i]).getValor();
            } else {
                if (i != inicioDeColumnas) {
                    sql += " , ";
                }
                String t = "", c = "";
                if (MetodosUtiles.esArreglo(a[i])) {
                    Object A[] = (Object[]) a[i];
                    t = A[0] + "";
                    if (!(A.length > 1)) {
                        c = "id";
                    } else {
                        c = A[1]+"";
                        sql += t + "." + c;
                    }
                } else {
                    sql += a[i];
                }
            }

        }
        if (sql.isEmpty()) {
            return "";
        }
        return " " + ORDER_BY + " " + sql;
    }

    public static String __getStr_Where(Object a[], int inicioDePares) {
        return __getStr_Where(a, new Configuracion_De_Columnas(), inicioDePares);
    }

    public static String __getStr_Where(Object a[]) {
        return __getStr_Where(a, new Configuracion_De_Columnas(), 0);
    }

    public static String __getStr_Where(Object a[], Configuracion_De_Columnas cnf, int inicioDePares) {
        String sqlWhere = "";
        if (inicioDePares < 0) {
            inicioDePares = 0;
        }
        if (cnf == null) {
            cnf = new Configuracion_De_Columnas();
        }
        int pos = 0;
        for (int i = inicioDePares; i < a.length; i++) {
            if ((i != inicioDePares) && (pos == 0)) {
                sqlWhere += " AND ";
            }
            if (pos == 0) {
                if (MetodosUtiles.esArreglo(a[i])) {
                    String t = "", c = "";
                    Object[] A = (Object[]) a[i];
                    t = A[0]+"";
                    if (!(A.length > 1)) {
                        c = "id";
                    } else {
                        c = A[1]+"";
                    }
                    if (cnf != null) { //&&cnf.addSiNoContiene!=None:
                        cnf.addSiNoContiene(t, c);
                    }
                    sqlWhere += t + "." + c;
                } else {
                    if (cnf != null) { //and cnf.getParTC_siSeEncuentra!=None:
                        sqlWhere += cnf.getParTC_siSeEncuentra(a[i] + "");
                    } else {
                        sqlWhere += a[i];
                    }
                }
            } else if (pos == 1) {
                sqlWhere += " = ";
                if (MetodosUtiles.esArreglo(a[i])) {
                    String t = "", c = "";
                    Object[] A = (Object[]) a[i];
                    t = A[0]+"";
                    if (!(A.length > 1)) {
                        c = "id";
                    } else {
                        c = A[1]+"";
                    }
                    if (cnf != null) {//and cnf.addSiNoContiene != None:
                        cnf.addSiNoContiene(t, c);
                    }
                    sqlWhere += t + "." + c;
                } else if ((a[i] + "").startsWith("(") && (a[i] + "").endsWith(")") && esSelect((a[i] + "").substring(1, (a[i] + "").length() - 2))) {
                    sqlWhere += a[i];
                } else {
                    sqlWhere += "'" + a[i] + "'";
                }

            }
            pos = (pos + 1) % 2;

        }
        if (!sqlWhere.isEmpty()) {
            sqlWhere = " " + WHERE + " " + sqlWhere;
        }
        return sqlWhere;
    }

    public static String __getFrom_Inner_Join_Where_ORDER_BY(String select, Object args[]) {
        Configuracion_De_Columnas cnf = new Configuracion_De_Columnas();
        Object[] tw = (Object[]) args[1];
        return __getFrom_Inner_Join(select, args, cnf) + __getStr_Where(tw, cnf, 0) + __getStr_ORDER_BY(args, 2);
    }

    public static String __getFrom_Inner_Join_Where(String select, Object args[]) {
        Configuracion_De_Columnas cnf = new Configuracion_De_Columnas();
        return __getFrom_Inner_Join(select, args, cnf) + __getStr_Where(args, cnf, 1);
    }

    public static String __getFrom_Inner_Join(String select, Object a[]) {
        return __getFrom_Inner_Join(select, a, new Configuracion_De_Columnas());
    }

    /**
     * ELEMENTO RELACIONES ENTRE TABLAS (ON): siempre son [Pares]
     * <br> plq la lista de ellos es ejemplo:
     * <br>[ [1 [T],[T,CID],[T,CID],[T] ] , [2 [T,CID],[T] ] , [3
     * [T,CID],[T,CID] ] ]
     * <br>recordar que siempre la un T en uno de los i tiene que aparecer en el
     * siguiente(i+1) pq es un recorrido
     *
     * <br> TABLA.CULUMNA_REFERENCIA_A_ID == a
     * <br> [T] ,[T,CID] a TABLA.COLUMNA_NOMBRE_PERSONALIZADO_ID
     *
     * <br> [T,CID],[T] a TABLA.ID el default dado de automatico
     *
     * <br> [T,CID],[T,CID]
     *
     *
     *
     *
     *
     * <br> PAR COLUMNA VALOR
     * <br> [T,C],[T,C]
     *
     * <br> [T,C],V
     *
     * <br> C,[T,C]
     *
     * <br> C,V
     *
     * <br> [T],[T,C] la [T] es [T,"id"]
     *
     * <br> [T],V la [T] es [T,"id"]
     *
     * <br> C,[T] la [T] es [T,"id"]
     *
     *
     *
     * <br> (listaDe ELEMENTO RELACIONES ENTRE TABLAS,paresColumnaValor)
     *
     * <br> ([TABLA,COLUMNA_REFERENCIA_ID],paresColumnaValor)
     *
     * @param select
     * @param a
     * @param cnf
     * @return
     */
    public static String __getFrom_Inner_Join(String select, Object a[], Configuracion_De_Columnas cnf) {
        if (cnf == null) {
            cnf = new Configuracion_De_Columnas();
        }
        String sqlOn = "";
        Object[] A0 = (Object[]) a[0];
        int leng = A0.length;
        ArrayList<String> lSqlOn = new ArrayList<>();

        if (leng > 0) {
            if (esString(A0[0])) {
                //Nada por ahora
            } else {
                String tabla1inerjoin = null;  // la que se encuentra tambien en a[0][1]
                String tablaFrom = null;
                int pos = 0;
                int len2 = A0.length; //len(a[0])
                for (int i = 0; i < len2; i++) {
                    sqlOn = "";
                    Object[] l = (Object[]) A0[i];
                    leng = l.length;
                    for (int j = 0; j < leng; j++) {
                        if ((j != 0) && (pos == 0)) {
                            sqlOn += " AND ";
                        }
                        if (pos == 0) {
//                            System.out.println("add 0 a "+l[j]);
                            sqlOn += cnf.getC_T((Object[]) l[j])+" ";//(Object[][]), j
                        }
                        if (pos == 1) {
//                            System.out.println("add 1 a "+l[j]);
                            sqlOn += " = " + cnf.getC_T((Object[])l[j])+" ";//(Object[][]) , j
                        }
                        pos = (pos + 1) % 2;
                        if ((i == 1) && (tabla1inerjoin == null)) {
                            Object[] l_0 = (Object[]) l[j];
                            if (l_0[0].equals(cnf.getNombreDeTabla(1))) {
                                tabla1inerjoin = cnf.getNombreDeTabla(1);
                                tablaFrom = cnf.getNombreDeTabla(0);
                            }
                        }


                    }
                    lSqlOn.add(sqlOn);

                }
                if (tabla1inerjoin == null) {
                    tabla1inerjoin = cnf.getNombreDeTabla(0);
                    tablaFrom = cnf.getNombreDeTabla(1);
                }
                sqlOn = FROM + " " + tablaFrom + " " + INNER_JOIN + " " + tabla1inerjoin;
                len2 = lSqlOn.size();
                for (int i = 0; i < len2; i++) {
                    if (i != 0) {
                        sqlOn += " " + INNER_JOIN + " " + cnf.getNombreDeTabla(i + 1);
                    }
                    sqlOn += " " + ON + " " + lSqlOn.get(i);
                }
            }
        }
        return select + sqlOn;
    }

    public static String addColumn(String nombreTabla, String Columna, TipoDeDatoSQL tipo, String defaultt, Object clasificacion) {
        if (tipo == null) {
            tipo = TipoDeDatoSQL.VARCHAR;
        }
        if (defaultt == null) {
            defaultt = tipo.getPorDefecto();
        }
        if (clasificacion == null) {
            clasificacion = "";
        } else if (TipoDeClasificacionSQL.esTipoDeClasificacionSQL(clasificacion)) {
            clasificacion = "  " + ((TipoDeClasificacionSQL) clasificacion).getValor();
        }
        return ALTER_TABLE + " " + nombreTabla + " " + ADD_COLUMN + " " + Columna + " " + tipo.getValor() + " " + DEFAULT + " " + defaultt + clasificacion;
    }

    public static String renombrarColumna(String nombreTabla, String Columna, String NuevoNombre) {
        return ALTER_TABLE + " " + nombreTabla + " " + RENAME_COLUMN + " " + Columna + " " + TO + " " + NuevoNombre;
    }

    public static String eliminarColumna(String nombreTabla, String Columna) {
        return ALTER_TABLE + " " + nombreTabla + " " + DROP_COLUMN + " " + Columna + " " + CASCADE;
    }

    public static boolean esInsertar(String sql) {
        return sql.trim().toUpperCase().startsWith(INSERT_INTO + " ");
    }

    //lo que ya estaba!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public static String select_Id(String nombreTabla, int id) {
        return select_Where(nombreTabla, "id", id);
    }

    public static boolean esDELETE(String sql) {
        return sql.trim().toUpperCase().startsWith(DELETE + " ");
    }

    public static boolean esUPDATE(String sql) {
        return sql.trim().toUpperCase().startsWith(UPDATE + " ");
    }

    public static boolean esINSERT(String sql) {
        return sql.trim().toUpperCase().startsWith(INSERT + " ");
    }

    public static boolean esDROP(String sql) {
        return sql.trim().toUpperCase().startsWith(DROP + " ");
    }

    public static boolean esCREATE(String sql) {
        return sql.trim().toUpperCase().startsWith(CREATE + " ");
    }

    public static boolean esSelect(String sql) {
        return sql.trim().toUpperCase().startsWith(SELECT + " ");
    }

    public static boolean esSelectValor(String sql) {
        String sub = sql.trim().toUpperCase().substring((SELECT + " ").length());
        //System.out.println("sub="+sub);
        return (MetodosUtiles.startsWithOR(sub, MAX, MIN, AVG, COUNT, SUM));

        //.startsWith(CREATE)
    }

    //    public static String getCantidad_Where(String nombreTabla, Object... paresColumnaValor) {
//        paresColumnaValor = adaptarTipos(paresColumnaValor);
//
//        if (paresColumnaValor.length > 0) {
//            String sqlWhere = "", columna = "";
//            int pos = 0;
//            for (int i = 0; i < paresColumnaValor.length; i++) {
//                // sqlWhere += (!sqlWhere.isEmpty() ? " AND " : ""); Antes aqui
//
//                if (pos == 0) {
//                    sqlWhere += (!sqlWhere.isEmpty() ? " AND " : "");//Ahora aqui
//                    sqlWhere += paresColumnaValor[i];
//                    if (columna.isEmpty()) {
//                        columna = paresColumnaValor[i].toString();
//                    }
//                } else if (pos == 1) {
//                    sqlWhere += " = '" + paresColumnaValor[i] + "'";
//                }
//
//                pos = (++pos) % 2;
//            }
//            return SELECT_COUNT + "(" + nombreTabla + "." + columna + ") " + FROM + " " + nombreTabla + " " + WHERE + " " + sqlWhere;
//        } else {
//            return null;
//        }
//
//    }
    public static String getCantidad_Where(String nombreTabla, Object... paresColumnaValor) {
        String columna = paresColumnaValor[0] + "";
        return getCantidad(nombreTabla, columna) + __getStr_Where(paresColumnaValor);
    }

    public static String getCantidad_Where_Inner_Join(String nombreTabla, String columna, Object... args) {

        return __getFrom_Inner_Join_Where(SELECT_COUNT + "(" + nombreTabla + "." + columna + ") ", args);
    }

    /**
     * (nombre,.. nombreColumna,TipoDeDatoSQL,capacidad#,isKeyPrimary o
     * tipoDeClasificacionSQL)<br/>
     * (nombre,.. nombreColumna,TipoDeDatoSQL,capacidad#)<br/> asumo que no es
     * llave primaria (nombre,.. nombreColumna,capacidad#) asumo que es VARCHAR
     * <br/>
     * (nombre,.. nombreColumna) asumo que es VARCHAR(255)<br/>
     * si el siguiente a nombreColumna no es un TipoDeDatoSQL asumo que es
     * VARCHAR(255) <br/>
     * si el siguiente a TipoDeDatoSQL no es un # asumo que es VARCHAR(255)
     * <br/>
     *
     * @param nombreTabla
     * @param NombreTipos
     * @return
     */
    public static String crearTablaSiNoExiste(String nombreTabla, Object... NombreTipos) {
        return crearTabla(nombreTabla, NombreTipos).replaceFirst(CREATE_TABLE, CREATE_TABLE_IF_NOT_EXISTS);
    }

    public static String drop_table_if_exist(String nombreTabla) {
        return DROP_TABLE_IF_EXISTS + " " + nombreTabla;
    }

    public static String delete_id(String nombreTabla, String id) {
        return delete(nombreTabla, "id", id);
    }

    public static String delete(String nombreTabla, Object... a) {
        a = adaptarTipos(a);
        String sqlWhere = "";
        int pos = 0;

        for (int i = 0; i < a.length; i++) {
            //sqlWhere += (!sqlWhere.isEmpty() ? " AND " : "");antes aqui

            if (pos == 0) {
                sqlWhere += (!sqlWhere.isEmpty() ? " AND " : "");//Ahora aqui
                sqlWhere += a[i];
            } else if (pos == 1) {
                sqlWhere += " = '" + a[i] + "'";
            }

            pos = (++pos) % 2;
        }
        return DELETE + " " + FROM + " " + nombreTabla + " " + WHERE + " " + sqlWhere;
    }

    /**
     * (nombreTabla,id#,columna,setValor1,columna2,setValor2,... )
     *
     * @param nombreTabla
     * @param id
     * @param paresColumnaValor
     * @return
     */
    public static String update_Id(String nombreTabla, String id, Object... paresColumnaValor) {
        return update(nombreTabla, paresColumnaValor, "id", id);
    }

    /**
     * (nombreTabla,[columna,setValor1,columna2,setValor2,...
     * ],whereColumna1,whereValor1,whereColumna2,whereValor2,...)
     *
     * @param nombreTabla
     * @param paresColumnaValor
     * @param a
     * @return
     */
    public static String update(String nombreTabla, Object paresColumnaValor[], Object... a) {
        a = adaptarTipos(a);
        paresColumnaValor = adaptarTipos(paresColumnaValor);
        String sqlSet = "", sqlWhere = "";
        int pos = 0;
        for (int i = 0; i < paresColumnaValor.length; i++) {
            if (pos == 0) {
                sqlSet += (!sqlSet.isEmpty() ? " , " : "") + paresColumnaValor[i];
            } else if (pos == 1) {
                sqlSet += "='" + paresColumnaValor[i] + "'";
            }
            pos = (pos + 1) % 2;
        }
        pos = 0;
        for (int i = 0; i < a.length; i++) {

            if (pos == 0) {
                sqlWhere += (!sqlWhere.isEmpty() ? " AND " : "");
                sqlWhere += a[i];

            } else if (pos == 1) {
                sqlWhere += " = '" + a[i] + "'";

            }

            pos = (++pos) % 2;
        }

        return UPDATE + " " + nombreTabla + " " + SET + " " + sqlSet + " " + WHERE + " " + sqlWhere;
    }

    public static String getSuma(String nombreTabla, String columna) {
        return SELECT_SUM + "(" + nombreTabla + "." + columna + ") " + FROM + " " + nombreTabla;
    }

    public static String getSuma_Where(String nombreTabla, Object... paresColumnaValor) {
        String columna = paresColumnaValor[0] + "";
        return getSuma(nombreTabla, columna) + __getStr_Where(paresColumnaValor);
    }

    public static String getSuma_Where_Inner_Join(String nombreTabla, String columna, Object... args) {

        return __getFrom_Inner_Join_Where(SELECT_SUM + "(" + nombreTabla + "." + columna + ") ", args);
    }

    public static String getCantidad(String nombreTabla, String columna) {
        return SELECT_COUNT + "(" + nombreTabla + "." + columna + ") " + FROM + " " + nombreTabla;
    }

    public static String getLastId(String nombreTabla) {
        return getValorMaximo(nombreTabla, "id");
    }

    public static String select_ConUltimoID(String nombreTabla) {
        return select_forID(nombreTabla, "(" + getLastId(nombreTabla) + ")");
    }

    public static String select_forID(String nombreTabla, String id) {
        return select_Where(nombreTabla, "id", id);
    }

    public static String select_Distinct_Group_By_By_Having(String nombreTabla, String columnas[], String grupBy, String heavinColumna, String heavinValor) {
        return select_Group_By_Having(nombreTabla, columnas, grupBy, heavinColumna, heavinValor).replaceFirst(SELECT + " ", SELECT_DISTINCT + " ");
    }

    public static String select_Group_By_Having(String nombreTabla, String columnas[], String grupBy, String heavinColumna, String heavinValor) {
        heavinValor = getObjectCorrecto(heavinValor) + "";
        return select_Group_By(nombreTabla, columnas, grupBy) + " " + HAVING + " " + heavinColumna + "=" + heavinValor;
    }

    public static String select_Distinct_Group_By(String nombreTabla, String columnas[], String grupBy) {
        return select_Group_By(nombreTabla, columnas, grupBy).replaceFirst(SELECT + " ", SELECT_DISTINCT + " ");
    }

    /**
     * (nombreTabla,[]columnas,grupBy)
     *
     * @param nombreTabla
     * @param columnas
     * @param grupBy
     * @return
     */
    public static String select_Group_By(String nombreTabla, String columnas[], String grupBy) {
        return select(nombreTabla, columnas) + " " + GROUP_BY + " " + grupBy;

    }

//    public static String select_Distinct_Where_Inner_Join(Object... a) {
//        return select_Where_Inner_Join(a).replaceFirst(SELECT + " ", SELECT_DISTINCT + " ");
//    }
    /**
     * (nombreTabla,listaDe ELEMENTO RELACIONES ENTRE TABLAS,paresColumnaValor)
     *
     * <br>(nombreTabla,[TABLA,COLUMNA_REFERENCIA_ID],paresColumnaValor)
     *
     * <br>ELEMENTO RELACIONES ENTRE TABLAS (ON): siempre son [Pares]
     * <br>plq la lista de ellos es ejemplo:
     * <br>[ [1 [T],[T,CID],[T,CID],[T] ] , [2 [T,CID],[T] ] , [3
     * [T,CID],[T,CID] ] ]
     * <br>recordar que siempre la un T en uno de los i tiene que aparecer en el
     * siguiente(i+1) pq es un recorrido
     *
     * <br>TABLA.CULUMNA_REFERENCIA_A_ID == a
     * <br>[T] ,[T,CID] a TABLA.COLUMNA_NOMBRE_PERSONALIZADO_ID
     *
     * <br>[T,CID],[T] a TABLA.ID el default dado de automatico
     *
     * <br>[T,CID],[T,CID]
     *
     *
     *
     *
     *
     * <br>PAR COLUMNA VALOR
     * <br>[T,C],[T,C]
     *
     * <br>[T,C],V
     *
     * <br>C,[T,C]
     *
     * <br>C,V
     *
     * <br>[T],[T,C] la [T] es [T,"id"]
     *
     * <br>[T],V la [T] es [T,"id"]
     *
     * <br>C,[T] la [T] es [T,"id"]
     *
     * @param nombreTabla
     * @param args
     * @return
     */
    public static String select_Where_Inner_Join_TodoDeTabla(String nombreTabla, Object... args) {//_Todo
        return __getFrom_Inner_Join_Where(SELECT + " " + nombreTabla + ".* ", args);
    }

    /**
     * (nombreTabla,listaDe ELEMENTO RELACIONES ENTRE TABLAS,paresColumnaValor)
     *
     * <br>(nombreTabla,[TABLA,COLUMNA_REFERENCIA_ID],paresColumnaValor)
     *
     * <br>ELEMENTO RELACIONES ENTRE TABLAS (ON): siempre son [Pares]
     * <br>plq la lista de ellos es ejemplo:
     * <br>[ [1 [T],[T,CID],[T,CID],[T] ] , [2 [T,CID],[T] ] , [3
     * [T,CID],[T,CID] ] ]
     * <br>recordar que siempre la un T en uno de los i tiene que aparecer en el
     * siguiente(i+1) pq es un recorrido
     *
     * <br>TABLA.CULUMNA_REFERENCIA_A_ID == a
     * <br>[T] ,[T,CID] a TABLA.COLUMNA_NOMBRE_PERSONALIZADO_ID
     *
     * <br>[T,CID],[T] a TABLA.ID el default dado de automatico
     *
     * <br>[T,CID],[T,CID]
     *
     *
     *
     *
     *
     * <br>PAR COLUMNA VALOR
     * <br>[T,C],[T,C]
     *
     * <br>[T,C],V
     *
     * <br>C,[T,C]
     *
     * <br>C,V
     *
     * <br>[T],[T,C] la [T] es [T,"id"]
     *
     * <br>[T],V la [T] es [T,"id"]
     *
     * <br>C,[T] la [T] es [T,"id"]
     *
     * @param nombreTabla
     * @param args
     * @return
     */
    public static String select_Distinct_Todo_Where_Inner_Join(String nombreTabla, Object... args) {
        return __getFrom_Inner_Join_Where(SELECT_DISTINCT + " " + nombreTabla + ".* ", args);
    }

    /**
     * (nombreTabla,listaDe ELEMENTO RELACIONES ENTRE
     * TABLAS,where[paresColumnaValor],columnasDeOrden, o+ ordenaminento)
     *<br>
     * <br>(nombreTabla,[TABLA,COLUMNA_REFERENCIA_ID],where[paresColumnaValor],columnasDeOrden,
     * o+ ordenaminento)
     *<br>
     * <br>ELEMENTO RELACIONES ENTRE TABLAS (ON): siempre son [Pares]
     * <br>plq la lista de ellos es ejemplo:
     * <br>[ [1 [T],[T,CID],[T,CID],[T] ] , [2 [T,CID],[T] ] , [3
     * [T,CID],[T,CID] ] ]
     * <br>recordar que siempre la un T en uno de los i tiene que aparecer en el
     * siguiente(i+1) pq es un recorrido
     *<br>
     * <br>TABLA.CULUMNA_REFERENCIA_A_ID == a
     * <ul><li>[T] ,[T,CID] a TABLA.COLUMNA_NOMBRE_PERSONALIZADO_ID
     *
     * </li><li>[T,CID],[T] a TABLA.ID el default dado de automatico
     *
     * </li><li>[T,CID],[T,CID]</li></ul>
     *<br>
     *
     *
     *
     *
     * <br>PAR COLUMNA VALOR
     * <ul><li>[T,C],[T,C]
     *
     * </li><li>[T,C],V
     *
     * </li><li>C,[T,C]
     *
     * </li><li>C,V
     *
     * </li><li>[T],[T,C] la [T] es [T,"id"]
     *
     * </li><li>[T],V la [T] es [T,"id"]
     *
     * </li><li>C,[T] la [T] es [T,"id"]</li></ul>
     *
     * @param nombreTabla
     * @param args
     * @return
     */
    public static String select_Where_Inner_Join_ORDER_BY_TodoDeTabla(String nombreTabla, Object... args) {//_Todo
        return __getFrom_Inner_Join_Where_ORDER_BY(SELECT + " " + nombreTabla + ".* ", args);
    }
//
//    /**
//     * (nombreTabla1,[""]columnas,nombreTabla2,columnas[""],...,[columna,valor,columna2,valor2,...])
//     * si no se pone columnas asumo que son todas<br/>
//     * [[]]una lista dentro de otra lista
//     * <br/> si se pone del lado de la columna solo el nombre de la columna
//     * busco entre lass columnas seleccionadas<br/>
//     * de las tablas y a la enlaso con la primera coincedencia de un nombre de
//     * columna seleccionada con su nombreTabla<br/>
//     * si no hay coincidencias pq la tabla fue * solo la pongo sola
//     * "columna='algo'"<br/>
//     * puedes especificar a que tabla pertenece esa columna
//     * "nombreTabla.columna='algo'"<br/>
//     * del lado de los valores tambien se puede especificar un columna simpre
//     * que se ponga el nombreTabla<br/>
//     * seguido de un punto "columna='nombreTabla.columna2'"
//     *
//     * @param b
//     * @return
//     */
//    public static String select_Where_Inner_Join(Object... b) {
//        final Object a[] = adaptarTipos(b);
//        //String sql = "";
//        LinkedList<String> nombreTablas = new LinkedList();
//        LinkedList<String[]> columnasSeleccionadas = new LinkedList();
//        Creador2<String, Boolean, Integer> getNombreTablaAlQuePertenece = (v, admitirContiene) -> {
//            for (int i = 0; i < nombreTablas.size(); i++) {
//                if (v.startsWith(nombreTablas.get(i) + ".") || (admitirContiene && MetodosUtiles.or(columnasSeleccionadas.get(i), v))) {
//                    return i;
//                }
//            }
//            return -1;
//        };
//        Creador2<Object[], String, String> recorrerWhere = (lista, sql2) -> {
//            int pos = 0;
//            for (int i = 0; i < lista.length; i++) {
//
//                if (pos == 0) {
//                    sql2 += (!sql2.isEmpty() ? " AND " : "");
//
//                    int nombreTablaAlQuePertenece = getNombreTablaAlQuePertenece.crear(lista[i].toString(), true);
//                    String ini = "";
//                    if (nombreTablaAlQuePertenece != -1) {
//                        if (!lista[i].toString().startsWith(nombreTablas.get(nombreTablaAlQuePertenece) + ".")) {
//                            ini += nombreTablas.get(nombreTablaAlQuePertenece) + ".";
//                        }
//                    }
//                    sql2 += ini + lista[i];
//                } else if (pos == 1) {
//                    // sql2 += " = ";
//                    //int nombreTablaAlQuePertenece = getNombreTablaAlQuePertenece.crear(lista[i].toString(), false);
//                    sql2 += " = " + ((getNombreTablaAlQuePertenece.crear(lista[i].toString(), false) != -1) ? a[i] : "'" + a[i] + "'");
//
//                }
//                pos = (++pos) % 2;
//            }
//
//            return sql2;
//        };
//
//        String sql = "";
//
//        int pos = 0;
//        for (int i = 0; i < a.length; i++) {
//            if (pos == 1) {
//                if (esArregloString(a[i])) {
//                    // inicioDeColumnas = 1;
//                    columnasSeleccionadas.add((String[]) a[i]);
//                } else if (esArreglo(a[i])) {
//                    sql = recorrerWhere.crear((Object[]) a[i], sql);
//                    break;
//                } else {
//                    columnasSeleccionadas.add(new String[0]);
//                    pos = 0;
//                }
//            }
//            if (pos == 0) {
//                if (esArreglo(a[i])) {
//                    sql = recorrerWhere.crear((Object[]) a[i], sql);
//                    break;
//                } else {
//                    nombreTablas.add(a[i].toString());
//                }
//
//            }
//            pos = (pos + 1) % 2;
//        }
//        Object args[] = new Object[nombreTablas.size() * 2];
//        for (int i = 0; i < nombreTablas.size(); i += 2) {
//            args[i * 2] = nombreTablas.get(i);
//            args[(i * 2) + 1] = columnasSeleccionadas.get(i);
//        }
//        return select_Inner_Join(args) + " " + WHERE + " " + sql;
//    }

//    public static String select_Distinct_Inner_Join(Object... a) {
//        return select_Inner_Join(a).replaceFirst(SELECT + " ", SELECT_DISTINCT + " ");
//    }
//
//    /**
//     * (nombreTabla1,[]columnas,nombreTabla2,columnas[],...)si no se pone
//     * columnas asumo que son todas
//     *
//     * @param a
//     * @return
//     */
//    public static String select_Inner_Join(Object... a) {
//        String sql = "", sqlNombresTablas = "", nombreTablaActual = "";
//        int pos = 0;
//        for (int i = 0; i < a.length; i++) {
//            if (pos == 1) {
//                if (esArregloString(a[i])) {
//                    String columnas[] = (String[]) a[i];
//                    if (columnas.length == 0) {
//                        sql += (!sql.isEmpty() ? " , " : "") + ".*";
//                    } else {
//                        for (int j = 0; j < columnas.length; j++) {
//                            sql += (!sql.isEmpty() ? " , " : "") + nombreTablaActual + "." + columnas[j];
//                        }
//                    }
//                } else {
//                    sql += (!sql.isEmpty() ? " , " : "") + nombreTablaActual + ".*";
//                    pos = 0;
//                }
//            }
//            if (pos == 0) {
//                sqlNombresTablas += (!sqlNombresTablas.isEmpty() ? " , " : "") + a[i];
//                nombreTablaActual = a[i].toString();
//            }
//            pos = (pos + 1) % 2;
//        }
//        if (pos == 1) {
//            sql += (!sql.isEmpty() ? " , " : "") + nombreTablaActual + ".*";
//        }
//        return SELECT + " " + sql + " " + FROM + " " + sqlNombresTablas;
//    }

    public static String select_Distinct_Where(String nombreTabla, Object... a) {
        return select_Where(nombreTabla, a).replaceFirst(SELECT + " ", SELECT_DISTINCT + " ");
    }

    public static String select_Distinct_Todo(String nombreTabla) {
        return select_Todo(nombreTabla).replaceFirst(SELECT + " ", SELECT_DISTINCT + " ");
    }

    public static String select_Distinct(String nombreTabla, String... a) {
        return select(nombreTabla, a).replaceFirst(SELECT + " ", SELECT_DISTINCT + " ");
    }

    public static String select_Distinct_ORDER_BY(String nombreTabla, Object... a) {
        return select_ORDER_BY(nombreTabla, a).replaceFirst(SELECT + " ", SELECT_DISTINCT + " ");
    }

    public static String select_Distinct_Where_ORDER_BY(String nombreTabla, Object... a) {
//         a = adaptarTipos(a);
//         asd
        return select_Where_ORDER_BY(nombreTabla, a).replaceFirst(SELECT + " ", SELECT_DISTINCT + " ");
    }

    /**
     * (nombreTabla,columnaValorMaximo,paresColumnaValor)
     * <br>la idea es crear un subSql que seleccione el maximo valor utlizando
     * los pares columna valor tanto en el sql(todo) como en el subSql
     * <br>SubSql
     * <br>SELECT MAX(nombreTabla.columnaValorMaximo) FROM nombreTabla WHERE
     * pares columna valor
     * <br>sql(todo)
     * <br>SELECT nombreTabla.columnaValorMaximo FROM nombreTabla WHERE pares
     * columna valor
     * <br>AND columnaValorMaximo=(SubSql)
     *
     * <br>Ejemplo:
     * <br>SELECT nombreTabla.columnaValorMaximo FROM nombreTabla WHERE pares
     * columna valor
     * <br>AND columnaValorMaximo = ( SELECT MAX(nombreTabla.columnaValorMaximo)
     * FROM nombreTabla WHERE pares columna valor )
     *
     * @param nombreTabla
     * @param columnaValorMaximo
     * @param paresColumnaValor
     * @return
     */
    public static String select_Where_ValorMaximo(String nombreTabla, String columnaValorMaximo, Object... paresColumnaValor) {
        List<Object> pares = Arrays.asList(paresColumnaValor);

        pares.add(columnaValorMaximo);
        pares.add("(" + getValorMaximo_Where(nombreTabla, columnaValorMaximo, paresColumnaValor) + ")");
        return select_Where(nombreTabla, pares.toArray(new Object[0]));
        //return __select_Where(nombreTabla,pares.toArray(new Object[0]));
    }

    /**
     * (nombreTabla,columnas[],where[pares .. Columna-Valor],columnas por los
     * que ordenar)<br/>
     * (nombreTabla,where[pares .. Columna-Valor],columnas por los que ordenar)
     *
     * @param nombreTabla
     * @param a
     * @return
     */
    public static String select_Where_ORDER_BY(String nombreTabla, Object... a) {
        a = adaptarTipos(a);
        //String sql = "";
        int inicioDeColumnas = 0;
        String sqlSelect = "";
        if (a.length > 0) {
            if (MetodosUtiles.esArreglo(a[0])) {
                inicioDeColumnas = 1;
                if (MetodosUtiles.esArreglo(a[1])) {
                    inicioDeColumnas = 2;
                    sqlSelect = select_Where(nombreTabla, Arreglos.colocarDeUltimoObject((Object[]) a[1], a[0]));
                } else {
                    sqlSelect = select_Where(nombreTabla, (Object[]) a[0]);

                }
            } else {
                sqlSelect = select_Todo(nombreTabla);
            }
//            for (int i = inicioDeColumnas; i < a.length; i++) {
//                sql += (i != inicioDeColumnas ? " , " : "") + a[i];
//            }
        }
        return sqlSelect + " " + __getStr_ORDER_BY(a, inicioDeColumnas);
        //return sqlSelect + " " + ORDER_BY + " " + sql;

    }

    /**
     * (nombreTabla,columnas[],where[pares .. Columna-Valor],columnas por los
     * que ordenar)<br/>
     * (nombreTabla,columnas[],columnas por los que ordenar)<br/>
     * (nombreTabla,columnas por los que ordenar) selecciona todas las columnas
     *
     * @param nombreTabla
     * @param a
     * @return
     */
    public static String select_ORDER_BY(String nombreTabla, Object... a) {
        String sql = "";
        int inicioDeColumnas = 0;
        String sqlSelect = "";
        if (a.length > 0) {
            if (MetodosUtiles.esArregloString(a[0])) {
                inicioDeColumnas = 1;
                if (MetodosUtiles.esArreglo(a[1])) {
                    inicioDeColumnas = 2;
                    sqlSelect = select_Where(nombreTabla, Arreglos.colocarDeUltimoObject((Object[]) a[1], a[0]));
                } else {
                    sqlSelect = select(nombreTabla, (String[]) a[0]);
                }
            } else {
                sqlSelect = select_Todo(nombreTabla);
            }
            for (int i = inicioDeColumnas; i < a.length; i++) {
                sql += (i != inicioDeColumnas ? " , " : "") + a[i];
            }
        }
        return sqlSelect + " " + ORDER_BY + " " + sql;

    }

    /**
     * (nombreTabla,columnas[],pares .. Columna-Valor)<br/>
     * (nombreTabla,pares .. Columna-Valor)
     *
     * @param nombreTabla
     * @param a
     * @return
     */
    public static String select_Where(String nombreTabla, Object... a) {
        return __select_Where(nombreTabla, a);
    }

    /**
     * (nombreTabla,columnas[],pares .. Columna-Valor)<br/>
     * (nombreTabla,pares .. Columna-Valor)
     *
     * @param nombreTabla
     * @param a
     * @return
     */
    private static String __select_Where(String nombreTabla, Object[] a) {
        a = adaptarTipos(a);

        String sql = "";
        int inicioDePares = 0;
        String sqlSelect = "";
        //String columnas[] = null;
        if (a.length > 0) {
            if (MetodosUtiles.esArregloString(a[0])) {
                sqlSelect = (((String[]) a[0]).length > 0) ? select(nombreTabla, (String[]) a[0]) : select_Todo(nombreTabla);
                inicioDePares = 1;
            } else if (sqlSelect.isEmpty()) {
                sqlSelect = select_Todo(nombreTabla);
            }
//            int pos = 0;
//            for (int i = inicioDePares; i < a.length; i++) {
//                sql += (i != inicioDePares && pos == 0 ? " AND " : "");
//
//                if (pos == 0) {
//                    sql += a[i];
//                } else if (pos == 1) {
//                    sql += " = '" + a[i] + "'";
//                }
//
//                pos = (++pos) % 2;
//            }

        }
        return sqlSelect + __getStr_Where(a, inicioDePares);
        //return sqlSelect + " " + WHERE + " " + sql;
    }

    public static String select_Todo(String nombreTabla) {
        return select(nombreTabla, "*");
    }

    public static String select(String nombreTabla, String... columnas) {
        String sql = "";
        for (int i = 0; i < columnas.length; i++) {
            sql += (i != 0 ? " , " : "") + columnas[i];
        }
        return SELECT + " " + sql + " " + FROM + " " + nombreTabla;
    }

    public static String copyEnTXT(String nombreTabla, String direccion) {
        if (!direccion.isEmpty()) {
            return COPY + " " + nombreTabla + " " + FROM + " " + ((direccion.endsWith(".txt")) ? direccion : direccion + ".txt");
        }
        return null;
    }

    public static String getPoint(double X, double Y) {
        return "(" + X + "," + Y + ")";
    }

    public static String getDate(String año, String mes, String dia) {
        return año + "-" + mes + "-" + dia;
    }

    public static String dropTable(String nombreTabla) {
        return DROP_TABLE + " " + nombreTabla;
    }

    public static String getIdCorrespondiente(String nombreTabla) {
        return getIdCorrespondiente(nombreTabla, "id");
    }

    public static String getIdCorrespondiente(String nombreTabla, String id) {
        return "(" + getValorMaximo(nombreTabla, id) + ")+1";
    }

    public static String getValorPromedio(String nombreTabla, String columna) {
        return SELECT_AVG + "(" + nombreTabla + "." + columna + ") " + FROM + " " + nombreTabla;
    }

    public static String getValorPromedio_Where_Inner_Join(String nombreTabla, String columna, Object... args) {
        return __getFrom_Inner_Join_Where(SELECT_AVG + "(" + nombreTabla + "." + columna + ") ", args);
    }

    public static String getValorPromedio_Where(String nombreTabla, Object... paresColumnaValor) {
        String columna = paresColumnaValor[0] + "";
        return getValorPromedio(nombreTabla, columna) + __getStr_Where(paresColumnaValor);
    }

    public static String getValorMinimo(String nombreTabla, String columna) {
        return SELECT_MIN + "(" + nombreTabla + "." + columna + ") " + FROM + " " + nombreTabla;
    }

    public static String getValorMinimo_Where_Inner_Join(String nombreTabla, String columna, Object... args) {
        return __getFrom_Inner_Join_Where(SELECT_MIN + "(" + nombreTabla + "." + columna + ") ", args);
    }

    public static String getValorMinimo_Where(String nombreTabla, Object... paresColumnaValor) {
        String columna = paresColumnaValor[0] + "";
        return getValorMinimo(nombreTabla, columna) + __getStr_Where(paresColumnaValor);
    }

    public static String getValorMaximo(String nombreTabla, String columna) {
        return SELECT_MAX + "(" + nombreTabla + "." + columna + ") " + FROM + " " + nombreTabla;
    }

    public static String getValorMaximo_Where_Inner_Join(String nombreTabla, String columna, Object... args) {
        return __getFrom_Inner_Join_Where(SELECT_MAX + "(" + nombreTabla + "." + columna + ") ", args);
    }

    public static String getValorMaximo_Where(String nombreTabla, Object... paresColumnaValor) {
        String columna = paresColumnaValor[0] + "";
        return getValorMaximo(nombreTabla, columna) + __getStr_Where(paresColumnaValor);
    }

    /**
     * (nombreTabla,valores una sola fila completa)
     * (nombreTabla,[]columnas,valores)<br/>
     * es nuestra responsabiladad asegurarnos de que almenos uno de los valores
     * sea una llave
     *
     * @param nombreTabla
     * @param a
     * @return
     */
    public static String insertar_SinIdAutomatico(String nombreTabla, Object... a) {
        boolean idAutomatico = false;
        //System.out.println("1idAutomatico="+idAutomatico);
        return __insertar(nombreTabla, idAutomatico, "", a);
    }

    /**
     * (nombreTabla,valores una sola fila completa)<br/>
     * (nombreTabla,[]columnas,valores)<br/>
     * si no lo tiene pone el id de forma automatica con el nombre del argumento
     *
     * @param nombreTabla
     * @param id
     * @param a
     * @return
     */
    public static String insertar_idAutomatico(String nombreTabla, String id, Object... a) {
        // System.out.println("pasa por el id auto");
        return __insertar(nombreTabla, true, id, a);
    }

    /**
     * (nombreTabla,valores una sola fila completa)<br/>
     * (nombreTabla,[]columnas,valores) si no lo tiene pone el id de forma
     * automatica
     *
     * @param nombreTabla
     * @param a
     * @return
     */
    public static String insertar(String nombreTabla, Object... a) {
        return __insertar(nombreTabla, true, "id", a);
    }

    /**
     * (nombreTabla,valores una sola fila completa)<br/>
     * (nombreTabla,[]columnas,valores) si no lo tiene pone el id de forma
     * automatica
     *
     * @param nombreTabla
     * @param idAutomatico
     * @param nombreId
     * @param a
     * @return
     */
    private static String __insertar(String nombreTabla, boolean idAutomatico, String nombreId, Object... a) {
        //System.out.println("00idAutomatico="+idAutomatico);
        a = adaptarTipos(a);
        //System.out.println("0idAutomatico="+idAutomatico);
        String sql = "", sqlColumns = "";
        int inicioDeValores = 0;
        String columnas[] = null;
        if (a.length > 0) {
            if (MetodosUtiles.esArregloString(a[0])) {
                columnas = (String[]) a[0];
                for (int i = 0; i < columnas.length; i++) {
                    sqlColumns += (i != 0 ? " , " : "") + columnas[i];
                }
                inicioDeValores = 1;
            }
            for (int i = inicioDeValores; i < a.length; i++) {
                sql += (i != inicioDeValores ? " , " : "");
                if ((a[i] + "").startsWith("b'") && (a[i] + "").endsWith("'")) {
                    sql += a[i];
                } else {
                    if (esInt(a[i])) {
                        sql += a[i] + " ";
                    } else {
                        sql += " '" + a[i] + "' ";
                    }
                }
                //sql += (i != inicioDeValores ? " , " : "") + " '" + a[i] + "' ";
            }
        }
//        System.out.println("0sql="+sql);
//        System.out.println("idAutomatico="+idAutomatico);
        if (idAutomatico) {
            if (nombreId.isEmpty()) {
                nombreId = "id";
            }
            sql = " NULL , " + sql;
            if (!sqlColumns.isEmpty()) {
                sqlColumns = nombreId + " , " + sqlColumns;
            }
        }
//        System.out.println("sql="+sql);
        return INSERT_INTO + " " + nombreTabla + (!sqlColumns.isEmpty() ? " ( " + sqlColumns + " ) " : " ") + VALUES + " ( " + sql + " ) ";
    }

    public static String insertar_Many_SinIdAutomatico(String nombreTabla, int cantidadDeColumnas) {
        return __insertar_Many(nombreTabla, false, "", cantidadDeColumnas);
    }

    public static String insertar_Many_idAutomatico(String nombreTabla, String id, int cantidadDeColumnas) {
        return __insertar_Many(nombreTabla, true, id, cantidadDeColumnas);
    }

    public static String insertar_Many(String nombreTabla, int cantidadDeColumnas) {
        return __insertar_Many(nombreTabla, true, "id", cantidadDeColumnas);
    }

    private static String __insertar_Many(String nombreTabla, boolean idAutomatico, String nombreId, int cantidadDeColumnas) {
        String sql = "";
        for (int i = 0; i < cantidadDeColumnas; i++) {
            sql += (i != 0 ? " , " : "") + "?";
        }
        if (idAutomatico) {
            sql = " NULL , " + sql;
        }
        return INSERT_INTO + " " + nombreTabla + " " + VALUES + " ( " + sql + " ) ";
    }

    /**
     * (nombre,.. nombreColumna,TipoDeDatoSQL,capacidad#,isKeyPrimary o
     * tipoDeClasificacionSQL)<br/>
     * (nombre,.. nombreColumna,TipoDeDatoSQL,capacidad#)<br/> asumo que no es
     * llave primaria (nombre,.. nombreColumna,capacidad#) asumo que es VARCHAR
     * <br/>
     * (nombre,.. nombreColumna) asumo que es VARCHAR(255)<br/>
     * si el siguiente a nombreColumna no es un TipoDeDatoSQL asumo que es
     * VARCHAR(255) <br/>
     * si el siguiente a TipoDeDatoSQL no es un # asumo que es VARCHAR(255)
     * <br/>
     *
     * @param nombreTabla
     * @param NombreTipos
     * @return
     */
    public static String crearTabla(String nombreTabla, Object... NombreTipos) {
        String sql = "";
        TipoDeDatoSQL tipoAnterior = null;
        boolean tieneClavePrimaria = false;
        int pos = 0;
        for (int i = 0; i < NombreTipos.length; i++) {
            Object ielm = NombreTipos[i];
            if (pos == 3) {
                if (esBool(ielm)) {
                    if ((boolean) ielm) {
                        //sql += " " + PRIMARY_KEY;
                        sql += " " + NOT_NULL + " " + PRIMARY_KEY;
                        tieneClavePrimaria = true;
                    }
                } else if (TipoDeClasificacionSQL.esTipoDeClasificacionSQL(ielm)) {
                    sql += " " + ((TipoDeClasificacionSQL) ielm).getValor();
                    if (((TipoDeClasificacionSQL) ielm).esLlave()) {
                        tieneClavePrimaria = true;
                    }
                } else {
                    pos = 0;
                }
                sql += " , ";
                tipoAnterior = null;
            }
            if (pos == 2) {
                if (esInt(ielm)) {
                    sql += "(" + ielm + ")";
                } else {
                    if (tipoAnterior == TipoDeDatoSQL.VARCHAR) {
                        sql += "(255)";
                    }
                    if (esBool(ielm)) {
                        if ((boolean) ielm) {
                            sql += " " + PRIMARY_KEY;
                            tieneClavePrimaria = true;
                        }
                        sql += " , ";
                        pos = 0;
                        tipoAnterior = null;
                        continue;
                    } else if (TipoDeClasificacionSQL.esTipoDeClasificacionSQL(ielm)) {
                        sql += " " + ((TipoDeClasificacionSQL) ielm).getValor();
                        if (((TipoDeClasificacionSQL) ielm).esLlave()) {
                            tieneClavePrimaria = true;
                        }
                        sql += " , ";
                        pos = 0;
                        tipoAnterior = null;
                        continue;
                    } else {
                        pos = 0;
                        sql += " , ";
                    }

                }
            }

            if (pos == 1) {
                TipoDeDatoSQL tipo = null;
                if (TipoDeDatoSQL.esTipoDeDatoSQl(ielm)) {
                    tipo = (TipoDeDatoSQL) ielm;
                }
                if (tipo != null) {
                    sql += " " + tipo.getValor();
                    tipoAnterior = tipo;
                } else if (esInt(ielm)) {
                    tipo = TipoDeDatoSQL.VARCHAR;//sql+=" "+tipo.getValor()+"(255)";
                    sql += " " + tipo.getValor() + "(" + ielm + ")";
                    tipoAnterior = tipo;
                    pos = 3;
                    continue;
                } else {
                    tipo = TipoDeDatoSQL.VARCHAR;
                    sql += " " + tipo.getValor() + "(255)";
                    tipoAnterior = tipo;
                    if (esBool(ielm)) {
                        if ((boolean) ielm) {
                            sql += " " + PRIMARY_KEY;
                            tieneClavePrimaria = true;
                        }
                        sql += " , ";
                        pos = 0;
                        tipoAnterior = null;
                        continue;
                    } else if (TipoDeClasificacionSQL.esTipoDeClasificacionSQL(ielm)) {
                        sql += " " + ((TipoDeClasificacionSQL) ielm).getValor();
                        if (((TipoDeClasificacionSQL) ielm).esLlave()) {
                            tieneClavePrimaria = true;
                        }
                        sql += " , ";
                        pos = 0;
                        tipoAnterior = null;
                        continue;
                    } else {
                        pos = 0;
                        sql += " , ";
                    }
                }
            }
            if (pos == 0) {
                sql += " " + ielm;
            }
            pos = ++pos % 4;
        }

        if (pos == 1) {
            sql += " " + TipoDeDatoSQL.VARCHAR.getValor() + "(255)";
        } else if (pos == 2) {
            if (tipoAnterior == TipoDeDatoSQL.VARCHAR) {
                sql += "(255)";
            }
        }
        if (MetodosUtiles.endsWitchOR(sql, ", ", ",")) {
            sql = sql.substring(0, sql.lastIndexOf(","));
        }
        if (!tieneClavePrimaria && !sql.contains(" id ")) {
            sql = " id " + TipoDeDatoSQL.INTEGER.getValor() + " " + PRIMARY_KEY + " " + AUTOINCREMENT + " , " + sql;
        }
        return CREATE_TABLE + " " + nombreTabla + " ( " + sql + " ) ";
    }

    private static boolean esBool(Object a) {
        return a instanceof Boolean;
    }

    private static boolean esInt(Object a) {
        return a instanceof Integer;
    }

    private static String strg(Object... O) {
        String res = "";
        for (Object o : O) {
            res += o.toString();
        }
        return res;
    }


//

    private static Object[] adaptarTipos(Object... O) {
        for (int i = 0; i < O.length; i++) {
            Object o = O[i];
            Class c = o.getClass();
            if (or(c, java.util.Date.class)) {
                //System.out.println("adapto");
                O[i] = new java.sql.Timestamp(((java.util.Date) o).getTime());
            }
        }
        return O;
    }

    private static Object getObjectCorrecto(Object o) {
        Class c = o.getClass();
        if (or(c, java.util.Date.class)) {
            return new java.sql.Timestamp(((java.util.Date) o).getTime());
        }
        return o;
    }

    public static Date getDate(String o) {
        return UtilAndroid.getDateForStrBD(o);
        //return Date.from(java.sql.Timestamp.valueOf(o).toInstant());
    }

    public static boolean esString(Object a) {
        return a instanceof String;
    }



}