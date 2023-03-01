package com.rene.android.reneandroid.Utiles.ClasesUtiles.Comparadores;

import com.rene.android.reneandroid.Utiles.MetodosUtiles.MetodosUtiles;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by Rene on 12/07/2020.
 */

public class ComparadorOrdenAlfabeticoFileName implements Comparator<File>, Serializable {

    //private boolean contrario;
    private int PRIMERO_MAYOR = 1;
    private int PRIMERO_MENOR = -1;
    private boolean prioridadNumerica = false;

    public ComparadorOrdenAlfabeticoFileName() {
        //contrario=false;
    }

    public ComparadorOrdenAlfabeticoFileName(boolean prioridadNumerica) {
        //this.contrario = contrario;
        this(prioridadNumerica, false);
    }

    public ComparadorOrdenAlfabeticoFileName(boolean prioridadNumerica, boolean contrario) {
        if (contrario) {
            PRIMERO_MAYOR = -1;
            PRIMERO_MENOR = 1;
        }
        this.prioridadNumerica = prioridadNumerica;
    }

    //    private void inicializarOrden(boolean contrario){
//    if(!contrario){}
//    }
    @Override
    public int compare(File f1, File f2) {
        String o1=f1.getName();
        String o2=f2.getName();
        //System.out.println("o1="+o1+" o2="+o2);
        if (o1.equals(o2)) {
            //  System.out.println("uno");
            return 0;
        }

        if (MetodosUtiles.StringRealmenteVacio(o1)) {
            return PRIMERO_MAYOR;
        }
        if (MetodosUtiles.StringRealmenteVacio(o2)) {
            return PRIMERO_MENOR;
        }
        o1 = MetodosUtiles.eliminarEspaciosDelPrincipioString(o1);
        o2 = MetodosUtiles.eliminarEspaciosDelPrincipioString(o2);

        boolean iguales = false;
        boolean o1Mayor = false;
        if (o1.length() == o2.length()) {
            iguales = true;
        } else {
            if (o1.length() > o2.length()) {
                o1Mayor = true;
            }
        }
        //A 65-90 a 97-122   32
        //
        String mayor = o1Mayor ? o1 : o2;
        String menor = o1Mayor ? o2 : o1;
        for (int i = 0; i < menor.length(); i++) {
            char menorChar = sustituirCaracter(menor.charAt(i));
            char mayorChar = sustituirCaracter(mayor.charAt(i));
//            char menorChar = menor.charAt(i);
//            char mayorChar = mayor.charAt(i);

//            if (menorChar >= 97 && menorChar <= 122) {
//                menorChar -= 32;// System.out.println("resto1");
//            }
//            if (mayorChar >= 97 && mayorChar <= 122) {
//                mayorChar -= 32;// System.out.println("resto2");
//            }
            if (prioridadNumerica && (menorChar >= 48 && menorChar <= 57) && (mayorChar >= 48 && mayorChar <= 57)) {
                return (int) (MetodosUtiles.buscarNumero(o1, i) - MetodosUtiles.buscarNumero(o2, i));
            }

            if (menorChar == mayorChar) {
                continue;
            }

            if (menorChar < mayorChar) {

                if (o1Mayor) {
                    // System.out.println("dos");
                    return PRIMERO_MAYOR;
                } else {
                    //System.out.println("tres");
                    return PRIMERO_MENOR;
                }

            } else {
                if (o1Mayor) {
                    //System.out.println("cuatro");
                    return PRIMERO_MENOR;
                } else {
                    // System.out.println("cinco");
                    return PRIMERO_MAYOR;
                }

            }

        }
        // System.out.println("llego a qui");
        return o1Mayor ? PRIMERO_MAYOR : PRIMERO_MENOR;
    }

    private char sustituirCaracter(char c) {
        c = MetodosUtiles.arreglarChar(c);
        if (c >= 97 && c <= 122) {//new Character( c - 32)
            return (c -= 32);// System.out.println("resto1");
        }

        return c;
    }

}