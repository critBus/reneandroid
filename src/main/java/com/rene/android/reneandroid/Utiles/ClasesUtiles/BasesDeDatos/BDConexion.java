/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos;

//import static Utiles.MetodosUtiles.BD.getConexionPOSTGRES;
//import static Utiles.MetodosUtiles.BD.getConexionSQL_LITE;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar2ConException;
import com.rene.android.reneandroid.Utiles.MetodosUtiles.Arreglos;
import com.rene.android.reneandroid.Utiles.MetodosUtiles.BD;
import com.rene.android.reneandroid.Utiles.MetodosUtiles.MetodosUtiles;
import com.rene.android.reneandroid.Utiles.MetodosUtiles.SQLUtil;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Rene
 */
public class BDConexion {
    
//    private String controlador,
//            url_basesDeDatos,
//            usuario, contraseña, servidor, nombreBD, puerto, ultimuSQl;
//    private BDTipoDeConexion tipoDeConxion;
//    private File url;
//
//    private Object resultado;
//    private boolean mostrarResultadoConsola = false;

    private GestorDeConexionImple gestorDeConexionImple;
    private DatosBDConect datosBDConect;

    
    public BDConexion(String usuario, String contraseña, String servidor, String nombreBD, String puerto, BDTipoDeConexion tipoDeConxion, File url) {
//        this.usuario = usuario;
//        this.contraseña = contraseña;
//        this.servidor = servidor;
//        this.nombreBD = nombreBD;
//        this.puerto = puerto;
//        this.tipoDeConxion = tipoDeConxion;
//        ultimuSQl = "";
//        this.url = url == null ? null : url.getAbsoluteFile();
//        controlador = tipoDeConxion.getDriver();

        String controlador=tipoDeConxion.getDriver();
         datosBDConect=new DatosBDConect(controlador,null,usuario,contraseña,servidor,nombreBD,puerto,"",tipoDeConxion,url,null,false);
    }

    public DatosBDConect getDatosBDConect() {
        return datosBDConect;
    }

    public BDConexion mostrarResultadoConsola() {
//        if (resultado instanceof Object[][]) {
//            Arreglos.MostrarMatrizObject((Object[][]) resultado);
//        } else {
//            System.out.println("resultado: " + resultado);
//        }

        if (datosBDConect.getResultado() instanceof Object[][]) {
            Arreglos.MostrarMatrizObject((Object[][]) datosBDConect.getResultado());
        } else {
            System.out.println("resultado: " + datosBDConect.getResultado());
        }
        return this;
    }

    /**
     * activa la muestra de resultada en consola de forma automatica
     *
     * @return
     */
    public BDConexion csl() {
       // mostrarResultadoConsola = true;
        datosBDConect.setMostrarResultadoConsola(true);
        return this;
    }

    public GestorDeConexionImple getGestorDeConexionImple() {
        if(gestorDeConexionImple==null){
            gestorDeConexionImple= new GestionadorDeConexion(datosBDConect);
        }
        return gestorDeConexionImple;
    }

    public void setGestorDeConexionImple(GestorDeConexionImple gestorDeConexionImple) {
        this.gestorDeConexionImple = gestorDeConexionImple;
    }

    public boolean existe(String nombreTabla,Object ...paresColumnaValor) throws Exception{
        int cantidad=getCantidad_Where(nombreTabla, paresColumnaValor);
        return cantidad>0;
    }

    public Object[] select_forID(String nombreTabla, int id) throws Exception {
        return ((Object[][]) getGestorDeConexionImple()._execute(SQLUtil.select_Id(nombreTabla, id)))[0];
        // return select_Where(nombreTabla,"id",id);
    }

    public BDConexion delete_id(String nombreTabla, int id) throws Exception {
        return delete_id(nombreTabla, id + "");
    }

    public BDConexion delete_id(String nombreTabla, String id) throws Exception {
        getGestorDeConexionImple()._execute(SQLUtil.delete_id(nombreTabla, id));
        return this;
    }

    public BDConexion delete(String nombreTabla, Object... a) throws Exception {
        getGestorDeConexionImple()._execute(SQLUtil.delete(nombreTabla, a));
        return this;
    }

    /**
     * (nombreTabla,id#,columna,setValor1,columna2,setValor2,... )
     *
     * @param nombreTabla
     * @param id
     * @param paresColumnaValor
     * @return
     */
    public BDConexion update_Id(String nombreTabla, int id, Object... paresColumnaValor) throws Exception {
        System.out.println("salida: nombreTabla=" + nombreTabla + " id=" + id + " " + Arrays.toString(paresColumnaValor));
        String sql = SQLUtil.update_Id(nombreTabla, id + "", paresColumnaValor);
        getGestorDeConexionImple()._execute(sql);
        return this;
    }

    public BDConexion update_Id(String nombreTabla, String id, Object... paresColumnaValor) throws Exception {
        getGestorDeConexionImple()._execute(SQLUtil.update_Id(nombreTabla, id, paresColumnaValor));
        return this;
    }

    public BDConexion update(String nombreTabla, String columna, Object valor, Object... a) throws Exception {
        return update(nombreTabla, new Object[]{columna, valor}, a);
    }

    /**
     * (nombreTabla,[columna,setValor1,columna2,setValor2,...],
     * whereColumna1,whereValor1,whereColumna2,whereValor2,...)
     *
     * @param nombreTabla
     * @param paresColumnaValor
     * @param a
     * @return
     * @throws Exception
     */
    public BDConexion update(String nombreTabla, Object paresColumnaValor[], Object... a) throws Exception {
        getGestorDeConexionImple()._execute(SQLUtil.update(nombreTabla, paresColumnaValor, a));
        return this;
    }

    public boolean contiene(String nombreTabla, Object... paresColumnaValor) throws Exception {
        return getCantidad_Where(nombreTabla, paresColumnaValor) > 0;
    }

    public boolean isEmpty(String nombreTabla, String columna) throws Exception {
        return getCantidad(nombreTabla, columna) == 0;
    }

    public double getSuma(String nombreTabla, String columna) throws Exception {
        return (double) getGestorDeConexionImple()._execute(SQLUtil.getSuma(nombreTabla, columna));
    }

    public int getCantidad_Where(String nombreTabla, Object... paresColumnaValor) throws Exception {
        return (int) ((double) getGestorDeConexionImple()._execute(SQLUtil.getCantidad_Where(nombreTabla, paresColumnaValor)));
    }

    public int getCantidad(String nombreTabla, String columna) throws Exception {
        return (int) ((double) getGestorDeConexionImple()._execute(SQLUtil.getCantidad(nombreTabla, columna)));
    }

    public double getValorPromedio(String nombreTabla, String columna) throws Exception {
        return (double) getGestorDeConexionImple()._execute(SQLUtil.getValorPromedio(nombreTabla, columna));
    }

    public double getValorMinimo(String nombreTabla, String columna) throws Exception {
        return (double) getGestorDeConexionImple()._execute(SQLUtil.getValorMinimo(nombreTabla, columna));
    }

    public double getValorMaximo(String nombreTabla, String columna) throws Exception {
        return (double) getGestorDeConexionImple()._execute(SQLUtil.getValorMaximo(nombreTabla, columna));
    }

    public Object[][] select_Distinct_Group_By_By_Having(String nombreTabla, String columnas[], String grupBy, String heavinColumna, String heavinValor) throws Exception {
        return (Object[][]) getGestorDeConexionImple()._execute(SQLUtil.select_Distinct_Group_By_By_Having(nombreTabla, columnas, grupBy, heavinColumna, heavinValor));

    }

    public Object[][] select_Group_By_Having(String nombreTabla, String columnas[], String grupBy, String heavinColumna, String heavinValor) throws Exception {
        return (Object[][]) getGestorDeConexionImple()._execute(SQLUtil.select_Group_By_Having(nombreTabla, columnas, grupBy, heavinColumna, heavinValor));
    }

    public Object[][] select_Distinct_Group_By(String nombreTabla, String columnas[], String grupBy) throws Exception {
        return (Object[][]) getGestorDeConexionImple()._execute(SQLUtil.select_Distinct_Group_By(nombreTabla, columnas, grupBy));
    }

    public Object[][] select_Group_By(String nombreTabla, String columnas[], String grupBy) throws Exception {
        return (Object[][]) getGestorDeConexionImple()._execute(SQLUtil.select_Group_By(nombreTabla, columnas, grupBy));
    }

    //    public Object[][] select_Distinct_Where_Inner_Join(Object... a) throws Exception {
//        return (Object[][]) getGestorDeConexionImple()._execute(SQLUtil.select_Distinct_Where_Inner_Join(a));
//    }
//    public Object[][] select_Where_Inner_Join(Object... a) throws Exception {
//        return (Object[][]) getGestorDeConexionImple()._execute(SQLUtil.select_Where_Inner_Join(a));
//    }
//    public Object[][] select_Distinct_Inner_Join(Object... a) throws Exception {
//        return (Object[][]) getGestorDeConexionImple()._execute(SQLUtil.select_Distinct_Inner_Join(a));
//    }
//
//    public Object[][] select_Inner_Join(Object... a) throws Exception {
//        return (Object[][]) getGestorDeConexionImple()._execute(SQLUtil.select_Inner_Join(a));
//    }
    public Object[][] select_Distinct_Where(String nombreTabla, Object... a) throws Exception {
        return (Object[][]) getGestorDeConexionImple()._execute(SQLUtil.select_Distinct_Where(nombreTabla, a));
    }

    public Object[][] select_Distinct_Todo(String nombreTabla) throws Exception {
        return (Object[][]) getGestorDeConexionImple()._execute(SQLUtil.select_Distinct_Todo(nombreTabla));
    }

    public Object[][] select_Distinct(String nombreTabla, String... a) throws Exception {
        return (Object[][]) getGestorDeConexionImple()._execute(SQLUtil.select_Distinct(nombreTabla, a));
    }

    public Object[][] select_Distinct_ORDER_BY(String nombreTabla, Object... a) throws Exception {
        return (Object[][]) getGestorDeConexionImple()._execute(SQLUtil.select_Distinct_ORDER_BY(nombreTabla, a));
    }//

    public Object[][] select_Distinct_Where_ORDER_BY(String nombreTabla, Object... a) throws Exception {
        return (Object[][]) getGestorDeConexionImple()._execute(SQLUtil.select_Distinct_Where_ORDER_BY(nombreTabla, a));
    }

    /**
     * (nombreTabla,columnas[],where[pares .. Columna-Valor],columnas por los
     * que ordenar)<br/>
     * (nombreTabla,where[pares .. Columna-Valor],columnas por los que ordenar)
     *
     * @param nombreTabla
     * @param a
     * @return
     * @throws Exception
     */
    public Object[][] select_Where_ORDER_BY(String nombreTabla, Object... a) throws Exception {
        return (Object[][]) getGestorDeConexionImple()._execute(SQLUtil.select_Where_ORDER_BY(nombreTabla, a));
    }

    public Object[] select_FirstRow_Where_ValorMaximo(String  nombreTabla,String columnaValorMaximo,Object... paresColumnaValor) throws Exception{
        Object[][] O = (Object[][]) getGestorDeConexionImple()._execute(SQLUtil.select_Where_ValorMaximo(nombreTabla, columnaValorMaximo, paresColumnaValor));
        if (O == null || O.length == 0) {
            return null;
        }
        return O[0];
    }
    /**
     * (nombreTabla,listaDe ELEMENTO RELACIONES ENTRE TABLAS,paresColumnaValor)
     * <br>
     * <br>(nombreTabla,[TABLA,COLUMNA_REFERENCIA_ID],paresColumnaValor)
     * <br>
     * <br>ELEMENTO RELACIONES ENTRE TABLAS (ON): siempre son [Pares]
     * <br>plq la lista de ellos es ejemplo:
     * <br>[ [1 [T],[T,CID],[T,CID],[T] ] , [2 [T,CID],[T] ] , [3
     * [T,CID],[T,CID] ] ]
     * <br>recordar que siempre la un T en uno de los i tiene que aparecer en el
     * siguiente(i+1) pq es un recorrido
     * <br>
     * <br>TABLA.CULUMNA_REFERENCIA_A_ID == a
     * <ul><li>
     * [T] ,[T,CID] a TABLA.COLUMNA_NOMBRE_PERSONALIZADO_ID</li>
     *
     * <li>[T,CID],[T] a TABLA.ID el default dado de automatico</li>
     *
     * <li>[T,CID],[T,CID]</li>
     * </ul>
     * <br>
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
     * </li><li>C,[T] la [T] es [T,"id"]<li><ul>
     *
     * @param nombreTabla
     * @param a
     * @return
     * @throws Exception
     */


    public Object[][] select_Where_Inner_Join_TodoDeTabla(String nombreTabla, Object ...a) throws Exception {
        //

        return (Object[][]) getGestorDeConexionImple()._execute(SQLUtil.select_Where_Inner_Join_TodoDeTabla(nombreTabla, a));
    }
    /**
     * (nombreTabla,listaDe ELEMENTO RELACIONES ENTRE TABLAS,paresColumnaValor)
     * <br>
     * <br>(nombreTabla,[TABLA,COLUMNA_REFERENCIA_ID],paresColumnaValor)
     * <br>
     * <br>ELEMENTO RELACIONES ENTRE TABLAS (ON): siempre son [Pares]
     * <br>plq la lista de ellos es ejemplo:
     * <br>[ [1 [T],[T,CID],[T,CID],[T] ] , [2 [T,CID],[T] ] , [3
     * [T,CID],[T,CID] ] ]
     * <br>recordar que siempre la un T en uno de los i tiene que aparecer en el
     * siguiente(i+1) pq es un recorrido
     * <br>
     * <br>TABLA.CULUMNA_REFERENCIA_A_ID == a
     * <ul><li>
     * [T] ,[T,CID] a TABLA.COLUMNA_NOMBRE_PERSONALIZADO_ID</li>
     *
     * <li>[T,CID],[T] a TABLA.ID el default dado de automatico</li>
     *
     * <li>[T,CID],[T,CID]</li>
     * </ul>
     * <br>
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
     * </li><li>C,[T] la [T] es [T,"id"]<li><ul>
     *
     * @param nombreTabla
     * @param a
     * @return
     * @throws Exception
     */
    public Object[] select_Where_Inner_Join_TodoDeTabla_FirstRow(String nombreTabla, Object... a) throws Exception {
        Object[][] O = select_Where_Inner_Join_TodoDeTabla(nombreTabla, a);
        if (O == null || O.length == 0) {
            return null;
        }
        return O[0];
    }

    /**
     * (nombreTabla,listaDe ELEMENTO RELACIONES ENTRE TABLAS,lista
     * where[paresColumnaValor],columnasDeOrden, o+ ordenaminento)
     * <br>
     * <br>(nombreTabla,[TABLA,COLUMNA_REFERENCIA_ID],lista
     * where[paresColumnaValor],columnasDeOrden, o+ ordenaminento)
     * <br>
     * <br>
     * <br>Las listas son arreglos <b>Object[]</b>
     * <br>
     * <br>ELEMENTO RELACIONES ENTRE TABLAS (ON): siempre son [Pares]
     * <br>plq la lista de ellos es ejemplo:
     * <br>[ [1 [T],[T,CID],[T,CID],[T] ] , [2 [T,CID],[T] ] , [3
     * [T,CID],[T,CID] ] ]
     * <br>recordar que siempre la un T en uno de los i tiene que aparecer en el
     * siguiente(i+1) pq es un recorrido
     * <br>
     * <br>TABLA.CULUMNA_REFERENCIA_A_ID == a
     * <ul><li>[T] ,[T,CID] a TABLA.COLUMNA_NOMBRE_PERSONALIZADO_ID
     *
     * </li><li>[T,CID],[T] a TABLA.ID el default dado de automatico
     *
     * </li><li>[T,CID],[T,CID]</li></ul>
     * <br>
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
     * @param a
     * @return
     * @throws Exception
     */
    public Object[][] select_Where_Inner_Join_ORDER_BY_TodoDeTabla(String nombreTabla, Object... a) throws Exception {
        return (Object[][]) getGestorDeConexionImple()._execute(SQLUtil.select_Where_Inner_Join_ORDER_BY_TodoDeTabla(nombreTabla, a));
    }

    public Object[][] select_ORDER_BY(String nombreTabla, Object... a) throws Exception {
        return (Object[][]) getGestorDeConexionImple()._execute(SQLUtil.select_ORDER_BY(nombreTabla, a));
    }

    public Object[][] select_Where(String nombreTabla, Object... a) throws Exception {
        return (Object[][]) getGestorDeConexionImple()._execute(SQLUtil.select_Where(nombreTabla, a));
    }

    public Object[] select_Where_FirstRow(String nombreTabla, Object... a) throws Exception {
        Object[][] O = select_Where(nombreTabla, a);
        if (O == null || O.length == 0) {
            return null;
        }
        return O[0];
    }

    //    public <E> E select_Where_FirstResult(String nombreTabla, String columna, Object... a) throws Exception {
//        Object[] args = new Object[a.length + 1];
//        args[0] = new String[]{columna};
//        for (int i = 0; i < a.length; i++) {
//            args[i + 1] = a[i];
//        }
//        Object[][] O = select_Where(nombreTabla, args);
//        if (O.length > 0) {
//            return (E) O[0][0];
//        }
//        return null;
//    }
    public Object select_Where_FirstResult(String nombreTabla, String columna, Object... a) throws Exception {
        Object[] args = new Object[a.length + 1];
        args[0] = new String[]{columna};
        for (int i = 0; i < a.length; i++) {
            args[i + 1] = a[i];
        }
        Object[][] O = select_Where(nombreTabla, args);
        if (O.length > 0) {
            return O[0][0];
        }
        return null;
    }

    public Object[][] select_Todo(String nombreTabla) throws Exception {
        return (Object[][]) getGestorDeConexionImple()._execute(SQLUtil.select_Todo(nombreTabla));
    }

    public Object[][] select(String nombreTabla, String... columnas) throws Exception {
        return (Object[][]) getGestorDeConexionImple()._execute(SQLUtil.select(nombreTabla, columnas));
    }

    //    public <E> E[] select_Columna(String nombreTabla, String columna) throws Exception {
//        Object[][] O = select(nombreTabla, columna);
//        if (O.length > 0) {
//            E e[] = (E[]) Array.newInstance(O[0][0].getClass(), O.length);
//            for (int i = 0; i < e.length; i++) {
//                e[i] = (E) O[i][0];
//            }
//            return e;
//        }
//
//        return null;
//    }
    public String[] select_Distinct_Columna_Str(String nombreTabla, String columna) throws Exception {
        Object[][] O = select_Distinct(nombreTabla, columna);
        String f[] = new String[O.length];
        for (int i = 0; i < f.length; i++) {
            f[i] = O[i][0].toString();
        }
        return f;
    }

    public String[] select_Columna_Str(String nombreTabla, String columna) throws Exception {
        Object[][] O = select(nombreTabla, columna);
        String f[] = new String[O.length];
        for (int i = 0; i < f.length; i++) {
            f[i] = O[i][0].toString();
        }
        return f;
    }

    public BDConexion select_Columna_Recorrer(String nombreTabla, String columna, Utilizar2ConException<Object, Integer> recorrer) throws Exception {
        Object[][] O = select(nombreTabla, columna);
        //Object f[] = new Object[O.length];
        for (int i = 0; i < O.length; i++) {
            //f[i] = O[i][0];
            recorrer.utilizar(O[i][0], i);
        }
        return this;
    }

    public int[] select_Columna_Int(String nombreTabla, String columna) throws Exception {
        Object[][] O = select(nombreTabla, columna);
        int f[] = new int[O.length];
        for (int i = 0; i < f.length; i++) {
            f[i] = ((Integer) O[i][0]);
        }
        return f;
    }

    public int[] select_Distinct_Columna_Int(String nombreTabla, String columna) throws Exception {
        Object[][] O = select_Distinct(nombreTabla, columna);
        int f[] = new int[O.length];
        for (int i = 0; i < f.length; i++) {
            f[i] = ((Integer) O[i][0]);
        }
        return f;
    }

    public double[] select_Columna_Dou(String nombreTabla, String columna) throws Exception {
        Object[][] O = select(nombreTabla, columna);
        double f[] = new double[O.length];
        for (int i = 0; i < f.length; i++) {
            f[i] = ((Double) O[i][0]);
        }
        return f;
    }

    public double[] select_Distinct_Columna_Dou(String nombreTabla, String columna) throws Exception {
        Object[][] O = select_Distinct(nombreTabla, columna);
        double f[] = new double[O.length];
        for (int i = 0; i < f.length; i++) {
            f[i] = ((Double) O[i][0]);
        }
        return f;
    }

    public BDConexion copyEnTXT(String nombreTabla, String direccion) throws Exception {
        getGestorDeConexionImple()._execute(SQLUtil.copyEnTXT(nombreTabla, direccion));
        return this;
    }

    public BDConexion dropTable(String nombreTabla) throws Exception {
        getGestorDeConexionImple()._execute(SQLUtil.dropTable(nombreTabla));
        return this;
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
     * @throws Exception
     */
    public BDConexion insertar_SinIdAutomatico(String nombreTabla, Object... a) throws Exception {
        //System.out.println("por el correcto");
        getGestorDeConexionImple()._execute(SQLUtil.insertar_SinIdAutomatico(nombreTabla, a));
        return this;
    }

    public BDConexion insertar_idAutomatico(String nombreTabla, String id, Object... a) throws Exception {
        // System.out.println("se confunde total");
        getGestorDeConexionImple()._execute(SQLUtil.insertar_idAutomatico(nombreTabla, id, a));
        return this;
    }

    public BDConexion insertar_Filas(String nombreTabla, Object[]... A) throws Exception {
        for (int i = 0; i < A.length; i++) {
            insertar(nombreTabla, A[i]);
        }
        return this;
    }

    public BDResultadoInsertar insertar(String nombreTabla, Object... a) throws Exception {
        //System.out.println("por aqui?");
        Object O=getGestorDeConexionImple()._execute(SQLUtil.insertar(nombreTabla, a));

        if(O!=null&& O instanceof BDResultadoInsertar){
            BDResultadoInsertar res=(BDResultadoInsertar)O;
            return res;
        }

        return null;
    }

    public BDConexion crearTabla(String nombreTabla, Object... NombreTipos) throws Exception {
        getGestorDeConexionImple()._execute(SQLUtil.crearTabla(nombreTabla, NombreTipos));
        return this;
    }

    public BDConexion crearTablaSiNoExiste(String nombreTabla, Object... NombreTipos) throws Exception {
        getGestorDeConexionImple()._execute(SQLUtil.crearTablaSiNoExiste(nombreTabla, NombreTipos));
        return this;
    }

    public BDConexion crearTablaYBorrarSiExiste(String nombreTabla, Object... NombreTipos) throws Exception {

        drop_table_if_exist(nombreTabla);
        getGestorDeConexionImple()._execute(SQLUtil.crearTabla(nombreTabla, NombreTipos));
        return this;
    }

    public BDConexion drop_table_if_exist(String nombreTabla) throws Exception {
        getGestorDeConexionImple()._execute(SQLUtil.drop_table_if_exist(nombreTabla));
        return this;
    }
    
    public static BDConexion getConexionSQL_LITE(String url) {
        return getConexionSQL_LITE(new File(url));
    }
    
    public static BDConexion getConexionSQL_LITE(File url) {
        return new BDConexion("", "", "", "", "", BDTipoDeConexion.SQL_LITE, url);
    }
    
    public static BDConexion getPOSTGRESConexion(String usuario, String contraseña, String nombreBD, String servidor, String puerto) {
        return new BDConexion(usuario, contraseña, servidor, nombreBD, puerto, BDTipoDeConexion.POSTGRES, null);
    }
    
    public static BDConexion getPOSTGRESConexionLocal5432(String usuario, String contraseña, String nombreBD) {
        return getPOSTGRESConexion(usuario, contraseña, nombreBD, "localhost", "5432");
    }
    
    private class GestionadorDeConexion extends GestorDeConexionImple {

        public GestionadorDeConexion(DatosBDConect datosBDConect) {
            super(datosBDConect);
        }

        //        protected Object _execute_getValueNumero(){}
        public Object _execute(String sql) throws Exception {
            Object res = null;
            DatosDeConexion dc = _openConexion();
            datosBDConect.setUltimuSQl(sql);
            //ultimuSQl = sql;
            System.out.println("sql=" + sql);
            if (SQLUtil.esSelect(sql)) {
                ResultSet rs = (dc.conjuntoResultados = dc.instruccion.executeQuery(sql));
                System.out.println("dc.conjuntoResultados=" + dc.conjuntoResultados.getClass());
                if (SQLUtil.esSelectValor(sql)) {
                    if (rs.next()) {
                        String o = dc.conjuntoResultados.getObject(1) + "";
                        if (MetodosUtiles.esNumeroAll(o)) {
                            res = MetodosUtiles.dou(o);
                        }
                    }
                } else {
                    ResultSetMetaData metaDatos = rs.getMetaData();
                    LinkedList<Object[]> l = new LinkedList<>();
                    int j = 0;
                    while (dc.conjuntoResultados.next()) {
                        Object[] Ofila = new Object[metaDatos.getColumnCount()];
                        for (int i = 1; i <= Ofila.length; i++) {
                            Ofila[i - 1] = dc.conjuntoResultados.getObject(i);
                        }
                        l.add(Ofila);
                    }
                    Object[][] n = new Object[l.size()][];
                    for (int i = 0; i < n.length; i++) {
                        n[i] = l.get(i);
                    }
                    res = n;
                    
                }


                if (datosBDConect.isMostrarResultadoConsola()) {
                    datosBDConect.setResultado(res);
                   // resultado = res;
                    mostrarResultadoConsola();
                }
//                if (mostrarResultadoConsola) {
//                    resultado = res;
//                    mostrarResultadoConsola();
//                }
            } else {
                dc.instruccion.execute(sql);//crear tabla drop delete update
            }
            
            _closeConexion(dc);


            datosBDConect.setResultado(res);
            return datosBDConect.getResultado();
            //return (resultado = res);
        }

        @Override
        public void _mostrarResultadoConsola() {
            mostrarResultadoConsola();
        }

        public DatosDeConexion _openConexion() throws Exception {
            DatosDeConexion dc = new DatosDeConexion();
//            switch (tipoDeConxion) {
//                case MY_SQL:
//                    url_basesDeDatos = BD.getConexionMY_SQL(servidor, nombreBD);
//                    break;
//                case POSTGRES:
//                    url_basesDeDatos = BD.getConexionPOSTGRES(servidor, puerto, nombreBD);
//                    break;
//                case SQL_LITE:
//                    url_basesDeDatos = BD.getConexionSQL_LITE(url);
//                    break;
//            }
            switch (datosBDConect.getTipoDeConxion()) {
                case MY_SQL:
                    datosBDConect.setUrl_basesDeDatos(BD.getConexionMY_SQL(datosBDConect.getServidor(), datosBDConect.getNombreBD()));
                    //url_basesDeDatos = BD.getConexionMY_SQL(servidor, nombreBD);
                    break;
                case POSTGRES:
                    datosBDConect.setUrl_basesDeDatos(BD.getConexionPOSTGRES(datosBDConect.getServidor(),datosBDConect.getPuerto(), datosBDConect.getNombreBD()));
                    //url_basesDeDatos = BD.getConexionPOSTGRES(servidor, puerto, nombreBD);
                    break;
                case SQL_LITE:
                    datosBDConect.setUrl_basesDeDatos(BD.getConexionSQL_LITE(datosBDConect.getUrl()));
                    //url_basesDeDatos = BD.getConexionSQL_LITE(url);
                    break;
            }
            Class.forName(datosBDConect.getTipoDeConxion().getDriver());
            dc.conexion = DriverManager.getConnection(datosBDConect.getUrl_basesDeDatos(), datosBDConect.getUsuario(), datosBDConect.getContraseña());
            switch (datosBDConect.getTipoDeConxion()) {
                case SQL_LITE:
                    dc.instruccion = dc.conexion.createStatement();
                    break;
                case POSTGRES:
                    dc.instruccion = dc.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    break;
            }
//            Class.forName(tipoDeConxion.getDriver());
//            dc.conexion = DriverManager.getConnection(url_basesDeDatos, usuario, contraseña);
//            switch (tipoDeConxion) {
//                case SQL_LITE:
//                    dc.instruccion = dc.conexion.createStatement();
//                    break;
//                case POSTGRES:
//                    dc.instruccion = dc.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//                    break;
//            }
            return dc;
        }
        
        public void _closeConexion(DatosDeConexion dc) throws Exception {
            if (dc.conjuntoResultados != null) {
                dc.conjuntoResultados.close();
            }
            if (dc.instruccion != null) {
                dc.instruccion.close();
            }
            if (dc.conexion != null) {
                dc.conexion.close();
            }

            //dc.conjuntoResultados.updateBlob(0, inputStream);
        }
        
        private class DatosDeConexion {
            
            Connection conexion;
            Statement instruccion;
            ResultSet conjuntoResultados;
        }
    }
    
}
