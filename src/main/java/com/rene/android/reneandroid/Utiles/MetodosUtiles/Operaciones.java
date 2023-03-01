package com.rene.android.reneandroid.Utiles.MetodosUtiles;

/**
 * Created by Rene on 19/2/2020.
 */

public abstract class Operaciones {
    public static double consumoDeCorrienteCuba(double consumo){

        final double rango[][]={{0.09,100},{0.3,150},{0.4,200},{0.6,250},{0.8,300},{1.5,350},{1.8,500},{2,1000},{3,5000},{5,Double.MAX_VALUE}};
        double gasto=0;
        for (int k = 0; k <rango.length ; k++) {
            if (consumo<rango[k][1]){
                gasto+=(k==0?consumo:consumo-rango[k-1][1])*rango[k][0];
                break;
            }else{
                gasto+=(k==0?rango[k][1]:rango[k][1]-rango[k-1][1])*rango[k][0];
            }
        }
        return gasto;
    }
    public static double consumoDeCorrienteCuba(double kwAnteriores,double kwActuales){

        //final double a=0.09,b=0.3,c=0.4,d=0.6,e=0.8,f=1.5,g=1.8,h=2,i=3,j=5;
        //  final double rango[][]={{0.09,100},{0.3,150},{0.4,200},{0.6,250},{0.8,300},{1.5,350},{1.8,500},{2,1000},{3,5000},{5,Double.MAX_VALUE}};
        double consumo=kwActuales-kwAnteriores;
        return consumoDeCorrienteCuba(consumo);
      /*  //System.out.println("consumo="+consumo);
        double gasto=0;
        for (int k = 0; k <rango.length ; k++) {
           // System.out.println("k="+k+" a="+rango[k][0]+" b="+rango[k][1]);
            if (consumo<rango[k][1]){
              //  System.out.println("(k==0?consumo:consumo-rango[k-1][1])="+(k==0?consumo:consumo-rango[k-1][1]));
              //  System.out.println("(k==0?consumo:consumo-rango[k-1][1])*rango[k][0]="+(k==0?consumo:consumo-rango[k-1][1])*rango[k][0]);
              //  System.out.println("consumo="+consumo);
                //gasto+=(rango[k][1]-consumo)*rango[k][0];
                gasto+=(k==0?consumo:consumo-rango[k-1][1])*rango[k][0];
              //  System.out.println("gasto+=(consumo-rango[k][1])*rango[k][0]="+gasto);
                break;
            }else{
                // consumo-=rango[k][1];
//                gasto+=(consumo-rango[k][1])*rango[k][0];
              //  System.out.println("k==0?rango[k][1]:rango[k][1]-rango[k-1][1])*rango[k][0]="+(k==0?rango[k][1]:rango[k][1]-rango[k-1][1])*rango[k][0]);
                gasto+=(k==0?rango[k][1]:rango[k][1]-rango[k-1][1])*rango[k][0];
              //  System.out.println("gasto+=rango[k][1]="+gasto);
            }


        }
        return gasto;*/
    }

    public static double estimadorBootstrap(double datos[], double promedio) {
        double u2F=0;
        for (int i = 0; i < datos.length; i++) {
            u2F+=(Math.pow(datos[i]-promedio, 2)/datos.length);
        }
        return Math.sqrt(u2F/datos.length);
    }
    public static double varibilidadRelativa(double desviacionEstandar ,double promedio){
        return (desviacionEstandar / promedio) * 100;
    }
    public static double posicionMediaDatosOrdenados(double datosOrdenados[]){
        return datosOrdenados.length==0?Double.NaN:(datosOrdenados.length==1?datosOrdenados[0]:(datosOrdenados.length % 2 == 0 ? (datosOrdenados[(datosOrdenados.length / 2) - 1] + datosOrdenados[(datosOrdenados.length / 2)]) / 2 : datosOrdenados[(datosOrdenados.length / 2) + 1]));
    }
    public static double varianza(double datos[], double promedio) {
        double diferencias[] = new double[datos.length], elevaciones[] = new double[datos.length], suma = 0, varianza;
        for (int i = 0; i < datos.length; i++) {
            diferencias[i] = datos[i] - promedio;
            elevaciones[i] = Math.pow(diferencias[i], 2);
            suma += elevaciones[i];
        }
        //return suma / (datos.length - 1);
        return suma / (datos.length );
    }
    public static double varianzaBootstrap(double datos[], double promedio) {
        double diferencias[] = new double[datos.length], elevaciones[] = new double[datos.length], suma = 0, varianza;
        for (int i = 0; i < datos.length; i++) {
            diferencias[i] = datos[i] - promedio;
            elevaciones[i] = Math.pow(diferencias[i], 2);
            suma += elevaciones[i];
        }
        return suma / (datos.length );
    }
    public static double desviacionEstandar(double varianza) {
        return Math.sqrt(varianza);
    }
    public static double promedio(double A[]) {
        double total = 0;
        for (double a : A) {
            total += a;
        }
        return total / A.length;
    }

    /**
     * [0] max [1] min
     * @param medianaBootstrap
     * @param valorBootstrap
     * @param desviacionEstandar
     * @return
     */
    public static double[] limite(double medianaBootstrap,double valorBootstrap,double desviacionEstandar){
        double z=1.96;
        double max=medianaBootstrap+z*(desviacionEstandar/Math.sqrt(valorBootstrap));
        double min=medianaBootstrap-z*(desviacionEstandar/Math.sqrt(valorBootstrap));

        return new double[]{max,min};
    }

}
