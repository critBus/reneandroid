package com.rene.android.reneandroid.Utiles.MetodosUtiles;

import android.os.RemoteException;

import com.rene.android.reneandroid.Utiles.ClasesUtiles.Comparadores.ComparadorOrdenAlfabetico;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Exepciones.ExisteException;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Exepciones.IndiceFinalIncorrectoException;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Exepciones.IndiceIncorrectoException;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Exepciones.IndiceInicialIncorrectoException;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Exepciones.NoEncontradoException;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Exepciones.NoPermitidoException;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Exepciones.PINException;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Callback;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;



/**
 * Created by Rene on 19/2/2020.
 */

public abstract class MetodosUtiles {
    public static final String StringMinusculas[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    public static final String StringMayusculas[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public static boolean esArregloString(Object a) {
        return a instanceof String[];
    }

    public static  boolean esArreglo(Object a) {
        return a instanceof Object[];
    }
    public static boolean esBool(String a){
        return a.equals("true")||a.equals("false");
    }

    public static LinkedList<String> sortAlfabetico(LinkedList<String> a){
        Collections.sort(a,new ComparadorOrdenAlfabetico());
        return a;
    }

    public static String[] getResponderExceptionMensage(Exception ex) {
        ex.printStackTrace();
        if (ex instanceof NoEncontradoException || ex instanceof PINException
                || ex instanceof ExisteException || ex instanceof NoPermitidoException
                || ex instanceof IndiceFinalIncorrectoException || ex instanceof IndiceIncorrectoException
                || ex instanceof IndiceInicialIncorrectoException) {
            return new String[]{ex.getLocalizedMessage(), "Error"};
        }
        if (ex instanceof IOException) {
            return new String[]{"No se pudo utilizar el archibo", "Error"};
        }
        if (ex instanceof FileNotFoundException) {
            return new String[]{"No se encuentra el archibo", "Error"};
        }
        if (ex instanceof NumberFormatException) {
            return new String[]{"No es un numero valido", "Error"};
        }//RemoteException NotBoundException
        if (ex instanceof RemoteException) {
            return new String[]{"Hay un error en servidor", "Error"};
        }

        return null;
    }

    public static <E> boolean or(E a, E... B) {
        for (E b : B) {
            if (a.equals(b)) {
                return true;
            }
        }
        return false;
    }
    public static String eliminarCerosDerecha(String numero) {
        int i = numero.length();
        while (i > 0 && or(numero.charAt(i - 1), '0')) {
            i--;
        }
        if (i > 0 && or(numero.charAt(i - 1), '.', ',')) {
            i--;
        }
        return numero.substring(0, i);
    }

    public static String redondearAString(double n, int lugaresDesDeComa) {
        return eliminarCerosDerecha(String.format("%." + lugaresDesDeComa + "f", n));
    }
    public static double redondearADouble(double n, int lugaresDesDeComa) {
        return dou(redondearAString(n, lugaresDesDeComa));
    }
    public static boolean entreAll(double menores, double mayores,boolean iguales, double ... numeros) {
        for (double n : numeros) {
            if(!entre(menores, n, mayores, iguales, iguales)){
                return false;
            }
        }
        return true;
    }
    public static boolean entre(double menores, double numero, double mayores, boolean iguales) {
        return entre(menores, numero, mayores, iguales, iguales);
    }

    public static boolean entre(double menores, double numero, double mayores) {
        return entre(menores, numero, mayores, false, false);
    }

    public static boolean entre(double menores, double numero, double mayores, boolean igualesMenores, boolean igualesMayores) {
        return !((igualesMenores ? numero < menores : numero <= menores) || (igualesMayores ? numero > mayores : numero >= mayores));
    }
    public static int contieneStringContrario(String original, String busqueda) {
//        int indice = -1, respuesta = -1;
//        while ((indice = original.indexOf(busqueda, ++indice)) != -1) {
//            respuesta = indice;
//        }
//        return respuesta;
        return original.lastIndexOf(busqueda);
    }
    public static boolean isEmptyOR(String... A){
        for (String a : A) {
            if(StringRealmenteVacio(a)){
                return true;
            }
        }
        return false;
    }
    public static boolean StringRealmenteVacio(String a) {
        if (a == null || a.isEmpty() || a.length() == 0) {
            //System.out.println("true");
            return true;
        }
        //  System.out.println("a="+a+" leng"+a.length());
        for (int i = 0; i < a.length(); i++) {//System.out.println("i="+i);
            //  System.out.println("a.charAt(i)="+a.charAt(i));
            if (a.charAt(i) != ' ' && a.charAt(i) != 10) {
                return false;
            }
        }
        //  System.out.println("true 2");
        return true;
    }
    public static void recorrerLineas(String lineas,Utilizar2<String,Integer > u){
        Scanner s=new Scanner(lineas);
        int i=0;
        while(s.hasNextLine()){
            u.utilizar(s.nextLine(), i++);
        }
    }
    public static double[] getArreglodouble(String conjuntoDeDatos) {
        if (!conjuntoDeDatos.isEmpty()) {
            final LinkedList<Double> l = new LinkedList<>();
            MetodosUtiles.recorrerTokenizer(conjuntoDeDatos, new Utilizar2<String, Integer>() {
                @Override
                public void utilizar(String token, Integer integer) {
                    {
                        if (MetodosUtiles.esNumeroAll(token)) {
                            l.add(MetodosUtiles.dou(token));
                        }
                    }
                }
            });
            double datos[] = new double[l.size()];
            for (int i = 0; i < l.size(); i++) {
                datos[i] = l.get(i);
            }
            return datos;
        }
        return new double[0];
    }
    public static String unirStringC(Callback<String, String> c, String... A) {
        String res = "";
        for (int i = 0; i < A.length; i++) {
            res += c.call(A[i].toString());
        }
        return res;
    }
    public static String unirString(Object... A) {
        String res = "";
        for (int i = 0; i < A.length; i++) {
            res += A[i].toString();
        }
        return res;
    }

    public static void recorrerTokenizer(String palabra, Utilizar2<String, Integer> c) {
        StringTokenizer s = new StringTokenizer(palabra);
        int cont = 0;
        while (s.hasMoreTokens()) {
            c.utilizar(s.nextToken(), cont++);
        }
    }
    public static boolean esNumeroAll(String... A) {
        try {
            for (String a : A) {
                Double.parseDouble(a);
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static double dou(String a) {
        return Double.parseDouble(a);
    }
    public static int inT(String a) {
        return Integer.parseInt(a);
    }
    public static String eliminarEspaciosDelPrincipioString(String a) {
        if (a.isEmpty()) {
            return a;
        }
        int i = 0;
        while (a.charAt(i) == ' ' || a.charAt(i) == 10) {
            i++;
        }
        return a.substring(i);
    }
    public static double buscarNumero(String a, int posicionInicial) {
        return buscarNumero(a, posicionInicial, new ArrayList<String>());
    }
    public static double buscarNumero(String a, int posicionInicial, String permitidos[]) {
        return buscarNumero(a, posicionInicial, new ArrayList<String>(Arrays.asList(permitidos)));
    }

    public static double buscarNumero(String a, int posicionInicial, ArrayList<String> permitidos) {
   return buscarNumeroYCantidadDeCaracteresOriginal(a, posicionInicial, permitidos, new ArrayList<String>())[0];
    }
    /**
     * explota si en esta posicion no hay un numero<br>
     * {numero,cantidad De Caracteres original,i a continuacion,posicion
     * inicial} o {-1,-1,-1,-1}
     *
     * @param a
     * @param posicionInicial
     * @param conSigno
     * @return
     */
    public static double[] buscarNumeroYCantidadDeCaracteresOriginal(String a, int posicionInicial, boolean conSigno) {
        return buscarNumeroYCantidadDeCaracteresOriginal(a, posicionInicial, new ArrayList<String>(), new ArrayList<String>(), conSigno);
    }
    /**
     * explota si en esta posicion no hay un numero<br>
     * {numero,cantidad De Caracteres original,i a continuacion,posicion
     * inicial} o {-1,-1,-1,-1}
     *
     * @param a
     * @param posicionInicial
     * @param permitidos
     * @param conSigno
     * @return
     */
    public static double[] buscarNumeroYCantidadDeCaracteresOriginal(String a, int posicionInicial, String permitidos[], boolean conSigno) {
        return buscarNumeroYCantidadDeCaracteresOriginal(a, posicionInicial, new ArrayList<String>(Arrays.asList(permitidos)), new ArrayList<String>(), conSigno);
    }

    /**
     * explota si en esta posicion no hay un numero<br>
     * {numero,cantidad De Caracteres original,i a continuacion,posicion
     * inicial} o {-1,-1,-1,-1}
     *
     * @param a
     * @param posicionInicial
     * @param permitidos
     * @param conSigno
     * @return
     */
    public static double[] buscarNumeroYCantidadDeCaracteresOriginal(String a, int posicionInicial, ArrayList<String> permitidos, boolean conSigno) {
        return buscarNumeroYCantidadDeCaracteresOriginal(a, posicionInicial, permitidos, new ArrayList<String>(), conSigno);
    }

    /**
     * explota si en esta posicion no hay un numero<br>
     * {numero,cantidad De Caracteres original,i a continuacion,posicion
     * inicial} o {-1,-1,-1,-1}
     *
     * @param a
     * @param posicionInicial
     * @return
     */
    public static double[] buscarNumeroYCantidadDeCaracteresOriginal(String a, int posicionInicial) {
        return buscarNumeroYCantidadDeCaracteresOriginal(a, posicionInicial, new ArrayList<String>(), new ArrayList<String>(), true);
    }

    /**
     * explota si en esta posicion no hay un numero<br>
     * {numero,cantidad De Caracteres original,i a continuacion,posicion
     * inicial} o {-1,-1,-1,-1}
     *
     * @param a
     * @param posicionInicial
     * @param permitidos
     * @return
     */
    public static double[] buscarNumeroYCantidadDeCaracteresOriginal(String a, int posicionInicial, String permitidos[]) {
        return buscarNumeroYCantidadDeCaracteresOriginal(a, posicionInicial, new ArrayList<String>(Arrays.asList(permitidos)), new ArrayList<String>(), true);
    }

    /**
     * explota si en esta posicion no hay un numero<br>
     * {numero,cantidad De Caracteres original,i a continuacion,posicion
     * inicial} o {-1,-1,-1,-1}
     *
     * @param a
     * @param posicionInicial
     * @param permitidos
     * @return
     */
    public static double[] buscarNumeroYCantidadDeCaracteresOriginal(String a, int posicionInicial, ArrayList<String> permitidos) {
        return buscarNumeroYCantidadDeCaracteresOriginal(a, posicionInicial, permitidos, new ArrayList<String>(), true);
    }

    /**
     * explota si en esta posicion no hay un numero<br>
     * {numero,cantidad De Caracteres original,i a continuacion,posicion
     * inicial} o {-1,-1,-1,-1}
     *
     * @param a
     * @param posicionInicial
     * @param permitidosAContinuacion
     * @param permitidosAntes
     * @return
     */
    public static double[] buscarNumeroYCantidadDeCaracteresOriginal(String a, int posicionInicial, ArrayList<String> permitidosAContinuacion, ArrayList<String> permitidosAntes) {
        return buscarNumeroYCantidadDeCaracteresOriginal(a, posicionInicial, permitidosAContinuacion, permitidosAntes, true);
    }

    /**
     * explota si en esta posicion no hay un numero<br>
     * {numero,cantidad De Caracteres original,i a continuacion,posicion
     * inicial} o {-1,-1,-1,-1}
     *
     * @param a
     * @param posicionInicial
     * @param permitidosAContinuacion
     * @param permitidosAntes
     * @param conSigno
     * @return
     */
    public static double[] buscarNumeroYCantidadDeCaracteresOriginal(String a, int posicionInicial, ArrayList<String> permitidosAContinuacion, ArrayList<String> permitidosAntes, boolean conSigno) {
        return buscarNumeroYCantidadDeCaracteresOriginal(a, posicionInicial, permitidosAContinuacion, permitidosAntes, conSigno, false);
    }

    /**
     * explota si en esta posicion no hay un numero<br>
     * {numero,cantidad De Caracteres original,i a continuacion,posicion
     * inicial} o {-1,-1,-1,-1}
     *
     * @param a
     * @param posicionInicial
     * @param conSigno
     * @param soloEntero
     * @return
     */
    public static double[] buscarNumeroYCantidadDeCaracteresOriginal(String a, int posicionInicial, boolean conSigno, boolean soloEntero) {
        return buscarNumeroYCantidadDeCaracteresOriginal(a, posicionInicial, new ArrayList<String>(), new ArrayList<String>(), conSigno, soloEntero);
    }

    /**
     * explota si en esta posicion no hay un numero<br>
     * {numero,cantidad De Caracteres original,i a continuacion,posicion
     * inicial} o {-1,-1,-1,-1}
     *
     * @param a
     * @param posicionInicial
     * @param permitidosAContinuacion
     * @param permitidosAntes
     * @param conSigno
     * @param soloEntero
     * @return
     */
    //A[0] es el numero , A[1] es la cantidad de caracteres
    public static double[] buscarNumeroYCantidadDeCaracteresOriginal(String a, int posicionInicial, ArrayList<String> permitidosAContinuacion, ArrayList<String> permitidosAntes, boolean conSigno, boolean soloEntero) {
        double A[] = {-1, -1, -1, -1};
        A[1] = 0;
        String numero = "";
        for (int i = posicionInicial; i < a.length() && ((a.charAt(i) >= 48 && a.charAt(i) <= 57) || (!soloEntero && (a.charAt(i) == '.' || a.charAt(i) == ',')) || (conSigno && numero.isEmpty() && esSignoMenos(a, i, permitidosAContinuacion, permitidosAntes))); i++) {
            //System.out.println("a.charAt(i)=" + a.charAt(i));
            if (a.charAt(i) == ',') {
                numero += '.' + "";
            } else {
                numero += a.charAt(i) + "";
            }
            A[1]++;
        }
        A[0] = Double.parseDouble(numero);
        A[2] = posicionInicial + A[1];
        A[3] = posicionInicial;
        return A;
    }
    public static boolean esSignoMenos(String a, int indice) {
        return esSignoMenos(a, indice, new ArrayList<String>());
    }

    //    public static boolean esSignoMenos(String a, int indice, ArrayList<String> permitidosAcontinuacion) {
//        String permitidosAntes[] = {"!", "+", "/", "*", "^"};
//        return esSignoMenos(a, indice, permitidosAcontinuacion, new ArrayList<String>(Arrays.asList(permitidosAntes)));
//    }
    public static boolean esSignoMenos(String a, int indice, ArrayList<String> permitidosAcontinuacion) {
        //String permitidosAntes[] = {"!", "+", "/", "*", "^"};
        return esSignoMenos(a, indice, new ArrayList<String>(), new ArrayList<String>());
    }
//numeros (a.charAt(i) >= 48 && a.charAt(i)<= 57)
    //letras minusculas (a.charAt(i) >= 97 && a.charAt(i) <= 122)
    //letras mayusculas (a.charAt(i) >= 65 && a.charAt(i) <= 90)

    public static boolean esSignoMenos(String a, int indice, ArrayList<String> permitidosAContinuacion, ArrayList<String> permitidosAntes) {
        //  System.out.println("***********************");
        //System.out.println("a=" + a + " a.charAt(indice)=" + a.charAt(indice) + " indice=" + indice);

        if (a.charAt(indice) != '-' || indice == a.length() - 1 || (indice > 0 ? (a.charAt(indice - 1) >= '0' && a.charAt(indice - 1) <= '9') || (a.charAt(indice - 1) >= 'a' && a.charAt(indice - 1) <= 'z') : false)) {
            return false;
        }
        if (indice == 0 && a.charAt(indice) == '-') {
            return true;
        }

        if (indice == 0 && a.length() > 0 && ((a.charAt(indice + 1) >= '0' && a.charAt(indice + 1) <= '9') || (a.charAt(indice + 1) >= 'a' && a.charAt(indice + 1) <= 'z') || contieneStringAEnIndiceAArrayListStringB(indice + 1, a, permitidosAContinuacion) >= 0)) {
            return true;
        }
        String permitidosAntes2[] = {"!", "+", "/", "*", "^", "("};
        permitidosAntes.addAll(new ArrayList<String>(Arrays.asList(permitidosAntes2)));

        if ((indice > 0 && indice < a.length() - 1) && ((a.charAt(indice + 1) >= '0' && a.charAt(indice + 1) <= '9') || (a.charAt(indice + 1) >= 'a' && a.charAt(indice + 1) <= 'z') || contieneStringAEnIndiceAArrayListStringB(indice + 1, a, permitidosAContinuacion) >= 0)
                && (a.charAt(indice - 1) == '-' || (a.charAt(indice - 1) >= '0' && a.charAt(indice - 1) <= '9') || (a.charAt(indice - 1) >= 'a' && a.charAt(indice - 1) <= 'z') || (contieneStringAEnIndiceAArregloStringBInverso(indice - 1, a, permitidosAntes.toArray(new String[0])) >= 0))) {
            return true;
        }

        return false;
    }

    public static int contieneStringAEnIndiceAArrayListStringB(int indice, String a, ArrayList<String> b) {
        return contieneStringAEnIndiceAArregloStringB(indice, a, b.toArray(new String[0]));
    }

    public static int contieneStringAEnIndiceAArregloStringB(int indice, String a, String... b) {
        return contieneStringAEnIndiceAArregloStringB(false, indice, a, b);
    }

    public static int contieneStringAEnIndiceAArregloStringB(boolean ignoreCase, int indice2, String a, String... b2) {
        return contieneStringAEnIndiceAArregloStringB(ignoreCase, indice2, a, 0, 0, b2);
    }

    public static int contieneStringAEnIndiceAArregloStringB(boolean ignoreCase, int indice2, String a, int indiceInicialB, int indiceInicialArregloB, String... B) {
        // System.out.println("a=" + a + " a.length()=" + a.length() + " indice=" + indice2);
        // String b[] = new String[b2.length];
        // System.arraycopy(b2, 0, b, 0, b2.length);
        if (ignoreCase) {
            a = a.toLowerCase();
        }
        For1:
        for (int j = indiceInicialArregloB; j < B.length; j++) {
            String b = B[j];
            if (ignoreCase) {
                b = B[j].toLowerCase();
            }
            if (indiceInicialB != 0) {
                b = b.substring(indiceInicialB);
            }

            int indice = indice2;
            //System.out.println(" j=" + j + " b[j]=" + b[j] + " b[j].length()=" + b[j].length());
            //System.out.println("a.length() - indice=" + (a.length() - indice));
            if (b.length() > a.length() - indice) {
                //System.out.println("continue");
                continue;
            }
            for (int i = 0; i < b.length(); i++, indice++) {
                //  System.out.println("i=" + i);
                //System.out.println("indice=" + indice + " a.charAt(indice)=" + a.charAt(indice) + " b[j].charAt(i)=" + b[j].charAt(i));
                if (a.charAt(indice) != b.charAt(i)) {
                    // System.out.println("continue2");
                    continue For1;
                }
                // System.out.println("j=" + j);
                if (i == b.length() - 1) {
                    return j;
                }
            }

        }
        // System.out.println("-1");
        return -1;
    }
    public static int contieneStringAEnIndiceAArrayListStringBInverso(int indice, String a, ArrayList<String> b) {
        return contieneStringAEnIndiceAArregloStringBInverso(indice, a, b.toArray(new String[0]));
    }

    public static int contieneStringAEnIndiceAArregloStringBInverso(int indice2, String a, String... b) {
        return contieneStringAEnIndiceAArregloStringBInverso(false, indice2, a, 0, b);

    }

    public static int contieneStringAEnIndiceAArregloStringBInverso(int indice2, String a, int indice0B, String... b) {
        return contieneStringAEnIndiceAArregloStringBInverso(false, indice2, a, indice0B, b);

    }
//    public static int contieneStringAEnIndiceAArregloStringBInverso(boolean ignoreCase, int indice2, String a, String... b) {
//        return contieneStringAEnIndiceAArregloStringBInverso(ignoreCase, indice2, a, 0, b);
//    }

    public static int contieneStringAEnIndiceAArregloStringBInverso(boolean ignoreCase, int indice2, String a, int indice0B, String... b) {
        //System.out.println("a=" + a + " a.length()=" + a.length() + " indice=" + indice2);
        if (ignoreCase) {
            a = a.toLowerCase();
        }
        For1:
        for (int j = indice0B; j < b.length; j++) {
            if (ignoreCase) {
                b[j] = b[j].toLowerCase();
            }
            int indice = indice2;
            //  System.out.println(" j=" + j + " b[j]=" + b[j] + " b[j].length()=" + b[j].length());
            // System.out.println("b[j].length()=" + b[j].length() + " indice+1=" + (indice + 1) + " " + (b[j].length() > indice + 1));
            if (b[j].length() > indice + 1) {
                // System.out.println("continue1");
                continue;
            }
            for (int i = b[j].length() - 1; i >= 0; i--, indice--) {
                // System.out.println("r i=" + i);
                // System.out.println("indice=" + indice + " a.charAt(indice)=" + a.charAt(indice) + " b[j].charAt(i)=" + b[j].charAt(i));
                if (a.charAt(indice) != b[j].charAt(i)) {
                    //  System.out.println("continue 2");
                    continue For1;
                }
                if (i == 0) {
                    //  System.out.println("j=" + j);
                    return j;
                }
            }

        }
        // System.out.println("-1");
        return -1;
    }
    public static char arreglarChar(char a) {
        char malos[] = {'ä', 'á', 'é', 'í', 'ó', 'ú', 'Á', 'É', 'Í', 'Ú', 'Ó', 'Ñ', 'ñ', 'â', 'å', 'à', 'ä', 'ê', 'ë', 'è', 'ï', 'î', 'ì', 'Ä', 'Å', 'É', 'ô', 'ò', 'ö', 'û', 'ù', 'ÿ', 'Ö', 'Ü', 'Á', 'Â', 'À', 'ã', 'Ã', 'ð', 'Ð', 'Ê', 'Ë', 'È', 'Í', 'Ï', 'Ï', 'Ì', 'Ó', 'Ô', 'Ô', 'Ò', 'õ', 'Õ', 'Ú', 'Û', 'Ù', 'ý', 'Ý'};
        char buenos[] = {'a', 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'U', 'O', 'N', 'n', 'a', 'a', 'a', 'a', 'e', 'e', 'e', 'i', 'i', 'i', 'A', 'A', 'E', 'o', 'o', 'o', 'u', 'u', 'y', 'O', 'U', 'A', 'A', 'A', 'a', 'A', 'o', 'D', 'E', 'E', 'E', 'I', 'I', 'I', 'I', 'O', 'O', 'O', 'O', 'o', 'O', 'U', 'U', 'U', 'y', 'y'};
        for (int i = 0; i < malos.length; i++) {
            if (a == malos[i]) {
                return buenos[i];
            }
        }
//        System.out.println("b="+b);
        return a;
    }

    public static <E extends Comparable> boolean entre(E menores, E numero, E mayores) {
        return entre(menores, numero, mayores, false, false);
    }

    public static <E extends Comparable> boolean entre(E menores, E numero, E mayores, boolean iguales) {
        return entre(menores, numero, mayores, iguales, iguales);
    }

    public static <E extends Comparable> boolean entre(E menores, E numero, E mayores, boolean igualesMenores, boolean igualesMayores) {
        return !((igualesMenores ? menorQue(numero, menores) : menorIgualQue(numero, menores)) || (igualesMayores ? mayorQue(numero, mayores) : mayorIgualQue(numero, mayores)));
    }

    public static <E extends Comparable> boolean menorQue(E a, E b) {
        return a.compareTo(b) == -1;
    }

    public static <E extends Comparable> boolean mayorQue(E a, E b) {
        return a.compareTo(b) == 1;
    }

    public static <E extends Comparable> boolean igualQue(E a, E b) {
        return a.compareTo(b) == 0;
    }

    public static <E extends Comparable> boolean menorIgualQue(E a, E b) {
        return menorQue(a, b) || igualQue(a, b);
    }

    public static <E extends Comparable> boolean mayorIgualQue(E a, E b) {
        return mayorQue(a, b) || igualQue(a, b);
    }

    public static boolean startsWithOR(String a, String... A) {
        return startsWith(a, A);
    }
    public static boolean startsWith(String a, char... A) {
        for (int i = 0; i < A.length; i++) {
            if (!a.isEmpty() && a.charAt(0) == A[i]) {
                return true;
            }

        }
        return false;
    }

    public static boolean startsWith(String a, String... A) {
        for (int i = 0; i < A.length; i++) {
            if (a.startsWith(A[i])) {
                return true;
            }

        }
        return false;
    }
    public static boolean endsWitchOR(String a, String... A) {
        for (String s : A) {
            if (a.endsWith(s)) {
                return true;
            }
        }
        return false;
    }
    public static boolean contieneLetrasOR(String a) {
        for (int i = 0; i < a.length(); i++) {
            if (esCharLetra(a.charAt(i))) {
                return true;
            }
        }
        return false;
    }
    public static boolean esCharLetra(char a) {
        a = arreglarChar(a);
        return esCharLetraMinuscula(a) || esCharLetraMayuscula(a);
    }

    public static boolean esCharLetraMinuscula(char a) {
        return Arrays.asList(StringMinusculas).contains(arreglarChar(a) + "");
    }

    public static boolean esCharLetraMayuscula(char a) {
        return Arrays.asList(StringMayusculas).contains(arreglarChar(a) + "");
    }

    public static boolean containsOR(String a, String... B) {
        return containsOR(a,false,B);
    }
    public static boolean containsOR(String a, boolean toLowerCase, String... B) {
        if(toLowerCase){  a=a.toLowerCase();}
        for (int i = 0; i < B.length; i++) {
            if(toLowerCase){
            if(a.contains(B[i].toLowerCase())){
                return true;
            }}else{
                if(a.contains(B[i])){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean contieneAlgunoA_A(String A, String... B) {
        return contieneAlgunoA_A(A,false,B);
    }
    public static boolean contieneAlgunoA_A(String A, boolean toLowerCase, String... B) {
        if(toLowerCase){
        A=A.toLowerCase();}
        for (int i = 0; i < B.length; i++) {
            if(toLowerCase){
                if(B[i].toLowerCase().contains(A)){
                    return true;
                }
            }else{
                if(B[i].contains(A)){
                    return true;
                }
            }

        }
        return false;

    }
}
