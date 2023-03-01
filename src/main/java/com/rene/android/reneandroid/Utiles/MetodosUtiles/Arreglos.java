package com.rene.android.reneandroid.Utiles.MetodosUtiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Rene on 07/01/2021.
 */

public abstract class Arreglos {
    public static <E> E[] colocarDeUltimoObject(E A[], E a) {
        ArrayList U = new ArrayList(Arrays.asList(A));
        U.add(a);
        // A=(E[]) U.toArray(A);
        return (E[]) U.toArray(A);
    }
    public static int[] colocarDeUltimo(int A[], int B) {
        return Arreglos.convertirArregloAint(colocarDeUltimoObject(Arreglos.convertirArregloAObjetoInteger(A), new Integer(B)));
    }

    public static short[] colocarDeUltimo(short A[], short B) {
        return Arreglos.convertirArregloAshort(colocarDeUltimoObject(Arreglos.convertirArregloAObjetoShort(A), new Short(B)));
    }

    public static long[] colocarDeUltimo(long A[], long B) {
        return Arreglos.convertirArregloAlong(colocarDeUltimoObject(Arreglos.convertirArregloAObjetoLong(A), new Long(B)));
    }

    public static float[] colocarDeUltimo(float A[], float B) {
        return Arreglos.convertirArregloAfloat(colocarDeUltimoObject(Arreglos.convertirArregloAObjetoFloat(A), new Float(B)));
    }

    public static double[] colocarDeUltimo(double A[], double B) {
        return Arreglos.convertirArregloAdouble(colocarDeUltimoObject(Arreglos.convertirArregloAObjetoDouble(A), new Double(B)));
    }

    public static char[] colocarDeUltimo(char A[], char B) {
        return Arreglos.convertirArregloAchar(colocarDeUltimoObject(Arreglos.convertirArregloAObjetoCharacter(A), new Character(B)));
    }

    public static boolean[] convertirArregloAboolean(ArrayList<Boolean> A) {
        return convertirArregloAboolean(A.toArray(new Boolean[]{}));
    }


    public static Boolean[] convertirArregloAObjetoBoolean(boolean A[]) {
        Boolean B[] = new Boolean[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static Integer[] convertirArregloAObjetoInteger(int A[]) {
        Integer B[] = new Integer[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static Double[] convertirArregloAObjetoDouble(Integer A[]) {
        Double B[] = new Double[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i].doubleValue();
        }
        return B;
    }

    public static Double[] convertirArregloAObjetoDouble(Float A[]) {
        Double B[] = new Double[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i].doubleValue();
        }
        return B;
    }

    public static Double[] convertirArregloAObjetoDouble(double A[]) {
        Double B[] = new Double[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static Short[] convertirArregloAObjetoShort(short A[]) {
        Short B[] = new Short[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static Long[] convertirArregloAObjetoLong(long A[]) {
        Long B[] = new Long[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static Float[] convertirArregloAObjetoFloat(float A[]) {
        Float B[] = new Float[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static Character[] convertirArregloAObjetoCharacter(char A[]) {
        Character B[] = new Character[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }


    public static int[] convertirArregloAint(ArrayList<Integer> A) {
        return Arreglos.convertirArregloAint(A.toArray(new Integer[0]));
    }

    public static int[] convertirArregloAint(LinkedList<Integer> A) {
        return Arreglos.convertirArregloAint(A.toArray(new Integer[0]));
    }

    public static int[] convertirArregloAint(Integer A[]) {
//        System.out.println(Arrays.toString(A));

        int B[] = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static boolean[] convertirArregloAboolean(Boolean A[]) {
//        System.out.println(Arrays.toString(A));

        boolean B[] = new boolean[A.length];
        for (int i = 0; i < A.length; i++) {
            if (A[i] != null) {
                B[i] = A[i];
            }

        }
        return B;
    }

    public static double[] convertirArregloAdouble(ArrayList<Double> A) {
        return Arreglos.convertirArregloAdouble(A.toArray(new Double[0]));
    }

    public static double[] convertirArregloAdouble(LinkedList<Double> A) {
        return Arreglos.convertirArregloAdouble(A.toArray(new Double[0]));
    }

    public static double[] convertirArregloAdouble(Double A[]) {
        double B[] = new double[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static double[] convertirArregloAdouble(Integer A[]) {
        double B[] = new double[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static double[] convertirArregloAdouble(Float A[]) {
        double B[] = new double[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static short[] convertirArregloAshort(ArrayList<Short> A) {
        return Arreglos.convertirArregloAshort(A.toArray(new Short[0]));
    }

    public static short[] convertirArregloAshort(Short A[]) {
        short B[] = new short[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static long[] convertirArregloAlong(ArrayList<Long> A) {
        return Arreglos.convertirArregloAlong(A.toArray(new Long[0]));
    }

    public static long[] convertirArregloAlong(Long A[]) {
        long B[] = new long[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static float[] convertirArregloAfloat(ArrayList<Float> A) {
        return Arreglos.convertirArregloAfloat(A.toArray(new Float[0]));
    }

    public static float[] convertirArregloAfloat(Float A[]) {
        float B[] = new float[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static char[] convertirArregloAchar(ArrayList<Character> A) {
        return Arreglos.convertirArregloAchar(A.toArray(new Character[0]));
    }

    public static char[] convertirArregloAchar(Character A[]) {
        char B[] = new char[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    //---------
    public static int[][] convertirArregloAint(Integer A[][]) {
        int B[][] = new int[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new int[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c];
            }

        }
        return B;
    }

    public static double[][] convertirArregloAdouble(Integer A[][]) {
        double B[][] = new double[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new double[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c].doubleValue();
            }

        }
        return B;
    }

    public static double[][] convertirArregloAdouble(Float A[][]) {
        double B[][] = new double[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new double[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c].doubleValue();
            }

        }
        return B;
    }

    public static double[][] convertirArregloAdouble(Double A[][]) {
        double B[][] = new double[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new double[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c];
            }

        }
        return B;
    }

    public static short[][] convertirArregloAshort(Short A[][]) {
        short B[][] = new short[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new short[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c];
            }

        }
        return B;
    }

    public static long[][] convertirArregloAlong(Long A[][]) {
        long B[][] = new long[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new long[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c];
            }

        }
        return B;
    }

    public static float[][] convertirArregloAfloat(Float A[][]) {
        float B[][] = new float[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new float[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c];
            }

        }
        return B;
    }

    public static char[][] convertirArregloAchar(Character A[][]) {
        char B[][] = new char[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new char[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c];
            }

        }
        return B;
    }

    //------------
    public static Integer[][] convertirArregloAObjetoInteger(int A[][]) {
        Integer B[][] = new Integer[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new Integer[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c];
            }

        }
        return B;
    }

    public static Double[][] convertirArregloAObjetoDouble(double A[][]) {
        Double B[][] = new Double[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new Double[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c];
            }

        }
        return B;
    }

    public static Short[][] convertirArregloAObjetoShort(short A[][]) {
        Short B[][] = new Short[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new Short[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c];
            }

        }
        return B;
    }

    public static Long[][] convertirArregloAObjetoLong(long A[][]) {
        Long B[][] = new Long[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new Long[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c];
            }

        }
        return B;
    }

    public static Float[][] convertirArregloAObjetoFloat(float A[][]) {
        Float B[][] = new Float[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new Float[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c];
            }

        }
        return B;
    }

    public static Character[][] convertirArregloAObjetoCharacter(char A[][]) {
        Character B[][] = new Character[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new Character[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c];
            }

        }
        return B;
    }
    public static <E> void MostrarMatrizObject(E A[][]) {
        System.out.println("La Matris resultante es:");
        String f1 = "";
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                f1 += "  " + A[i][j];
            }
            System.out.println(f1);
            f1 = "";
        }
    }

}
