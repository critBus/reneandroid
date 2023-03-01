/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rene.android.reneandroid.Utiles.ClasesUtiles.Matematicas;

import com.rene.android.reneandroid.Utiles.MetodosUtiles.MetodosUtiles;
import com.rene.android.reneandroid.Utiles.MetodosUtiles.Operaciones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeSet;

/**
 *   version 0.2
 * @author Rene
 */
public class ConjuntoResultadoBostran extends ConjuntoResultadoEstadistico {

    private int valorBootstrap, frecuenciaDeOriginalesOrdenados[];
    private double datosOriginales[], datosOriginalesOrdenados[], limiteSuperiorBootstrap, limiteInferiorBootstrap;
    private ConjuntoResultadoEstadistico resultadosIndependientes[];

    public ConjuntoResultadoBostran(String conjuntoDeDatos, int valorBootstrap) {
        this(MetodosUtiles.getArreglodouble(conjuntoDeDatos), valorBootstrap);
    }

    public ConjuntoResultadoBostran(double[] datosOriginales, int valorBootstrap) {
        super();
        inicializar(datosOriginales, valorBootstrap);

    }


//    protected void inicializar(double[] datosOriginales, int valorBootstrap) {
//        this.datosOriginales = datosOriginales;
//
//        TreeSet<Double> tree=new TreeSet<Double>();
//        for (int i = 0; i < datosOriginales.length; i++) {
//            tree.add(datosOriginales[i]);
//        }
//        datosOriginalesOrdenados = new double[tree.size()];
//        Iterator<Double> I=tree.iterator();
//        int indice=0;
//        while(I.hasNext()){
//            datosOriginalesOrdenados[indice++]=I.next();
//        }
//
////        datosOriginalesOrdenados = new double[datosOriginales.length];
////        System.arraycopy(datosOriginales, 0, datosOriginalesOrdenados, 0, datosOriginales.length);
////        Arrays.sort(datosOriginalesOrdenados);
//
//        frecuenciaDeOriginalesOrdenados = new int[datosOriginalesOrdenados.length];
//        Arrays.fill(frecuenciaDeOriginalesOrdenados, 0);
//        this.valorBootstrap = valorBootstrap;
////        System.out.println("valorBootstrap="+valorBootstrap);
//
//        ArrayList<Double> listaDatos=new ArrayList<>();
//        datos = new double[datosOriginales.length * valorBootstrap];
//        for (int i = 0; i < datosOriginales.length; i++) {
//            for (int j = 0; j < valorBootstrap; j++) {
//                listaDatos.add(datosOriginales[i]);
//              // datos[(i * valorBootstrap) + j] = datosOriginales[i];
//            }
//        }
//
//        Collections.shuffle(listaDatos);
//        for (int i = 0; i < listaDatos.size(); i++) {
//            datos[i]=listaDatos.get(i);
//        }
//
//
////        System.out.println("datos="+datos.length);
//        resultadosIndependientes = new ConjuntoResultadoEstadistico[valorBootstrap];
//        Random r = new Random();
//        for (int i = 0; i < resultadosIndependientes.length; i++) {
//            double muestraAutodocimante[] = new double[datosOriginales.length];
//            for (int j = 0; j < muestraAutodocimante.length; j++) {
//                //toma uno aletario pero se queda en la lista, osea puede volverse a tomar
//                double datoAleatorio = datos[r.nextInt(datos.length)];
//
//                //toma uno aleatorio y lo quita
//                //int indiceDatoAleatorio=listaDatos.size()==1?0:r.nextInt(listaDatos.size());
//               // double datoAleatorio =listaDatos.remove(indiceDatoAleatorio);
//
//                // Aumento la frecuencia
//                for (int k = 0; k < datosOriginalesOrdenados.length; k++) {
//
//                    if (datosOriginalesOrdenados[k] == datoAleatorio) {
//                        frecuenciaDeOriginalesOrdenados[k]++;
//                        break;
//                    }
//                    if (datosOriginalesOrdenados[k] > datoAleatorio) {
//                        break;
//                    }
//                }
//                muestraAutodocimante[j] = datoAleatorio;
//            }
//            resultadosIndependientes[i] = new ConjuntoResultadoEstadistico(muestraAutodocimante);
//        }
//
//        iniBasicos(getTodosLosPromedios());
//    }

//Alternativa 2
    protected void inicializar(double[] datosOriginales, int valorBootstrap) {
        this.datosOriginales = datosOriginales;

        TreeSet<Double> tree=new TreeSet<Double>();
        for (int i = 0; i < datosOriginales.length; i++) {
            tree.add(datosOriginales[i]);
        }
        datosOriginalesOrdenados = new double[tree.size()];
        Iterator<Double> I=tree.iterator();
        int indice=0;
        while(I.hasNext()){
            datosOriginalesOrdenados[indice++]=I.next();
        }

//        datosOriginalesOrdenados = new double[datosOriginales.length];
//        System.arraycopy(datosOriginales, 0, datosOriginalesOrdenados, 0, datosOriginales.length);
//        Arrays.sort(datosOriginalesOrdenados);

        frecuenciaDeOriginalesOrdenados = new int[datosOriginalesOrdenados.length];
        Arrays.fill(frecuenciaDeOriginalesOrdenados, 0);
        this.valorBootstrap = valorBootstrap;
//        System.out.println("valorBootstrap="+valorBootstrap);

        ArrayList<Double> listaDatos=new ArrayList<>();
        datos = new double[datosOriginales.length * valorBootstrap];
        for (int i = 0; i < datosOriginales.length; i++) {
            for (int j = 0; j < valorBootstrap; j++) {
                listaDatos.add(datosOriginales[i]);
                // datos[(i * valorBootstrap) + j] = datosOriginales[i];
            }
        }

        Collections.shuffle(listaDatos);
        for (int i = 0; i < listaDatos.size(); i++) {
            datos[i]=listaDatos.get(i);
        }


//        System.out.println("datos="+datos.length);
        resultadosIndependientes = new ConjuntoResultadoEstadistico[valorBootstrap];
        //Random r = new Random();

        int indiceDatosOriginales=0;
        for (int i = 0; i < resultadosIndependientes.length; i++) {
            double muestraAutodocimante[] = new double[datosOriginales.length];
            for (int j = 0; j < muestraAutodocimante.length; j++) {
                //toma uno aletario pero se queda en la lista, osea puede volverse a tomar
                //double datoAleatorio = datos[r.nextInt(datos.length)];
                double datoAleatorio = datos[indiceDatosOriginales++];

                //toma uno aleatorio y lo quita
                //int indiceDatoAleatorio=listaDatos.size()==1?0:r.nextInt(listaDatos.size());
                // double datoAleatorio =listaDatos.remove(indiceDatoAleatorio);

                // Aumento la frecuencia
                for (int k = 0; k < datosOriginalesOrdenados.length; k++) {

                    if (datosOriginalesOrdenados[k] == datoAleatorio) {
                        frecuenciaDeOriginalesOrdenados[k]++;
                        break;
                    }
                    if (datosOriginalesOrdenados[k] > datoAleatorio) {
                        break;
                    }
                }
                muestraAutodocimante[j] = datoAleatorio;
            }
            resultadosIndependientes[i] = new ConjuntoResultadoEstadistico(muestraAutodocimante);
        }

        iniBasicos(getTodosLosPromedios());
    }


    public void recalcular(){
        inicializar(datosOriginales,valorBootstrap);
    }

    @Override
    protected void iniBasicos(double[] datos) {
       super.iniBasicos(datos);
//        double variazaBootstrap = Operaciones.varianzaBootstrap(datos, promedio);
//double variazaBootstrap=varianza;
//        System.out.println("variazaBootstrap="+variazaBootstrap);
//        limiteInferiorBootstrap = promedio - variazaBootstrap;
//        limiteSuperiorBootstrap = promedio + variazaBootstrap;
//double Z=Operaciones.getZnormal(promedio, promedio, desviacionEstandar);
        //double z=1.96;
        double limites[]= Operaciones.limite(promedio,valorBootstrap,desviacionEstandar);
        limiteInferiorBootstrap = limites[1];
        limiteSuperiorBootstrap = limites[0];

    }

//    public double[] getTodosLosEstimadores() {
//        double todosLosEstimadores[] = new double[resultadosIndependientes.length];
//        for (int i = 0; i < todosLosEstimadores.length; i++) {
//            todosLosEstimadores[i] = resultadosIndependientes[i].getPromedio();
//        }
//        return todosLosEstimadores;
//    }

    public double[] getTodosLosPromedios() {
        double todosLosPromedios[] = new double[resultadosIndependientes.length];
        for (int i = 0; i < todosLosPromedios.length; i++) {
            todosLosPromedios[i] = resultadosIndependientes[i].getPromedio();
        }
        return todosLosPromedios;
    }

    public int getValorBootstrap() {
        return valorBootstrap;
    }

    public double[] getDatosOriginales() {
        return datosOriginales;
    }

    public double getLimiteSuperiorBootstrap() {
        return limiteSuperiorBootstrap;
    }

    public double getLimiteInferiorBootstrap() {
        return limiteInferiorBootstrap;
    }

    public ConjuntoResultadoEstadistico[] getResultadosIndependientes() {
        return resultadosIndependientes;
    }
    public ConjuntoResultadoEstadistico[] getResultadosIndependientes(int desde,int hasta) {
        if(MetodosUtiles.entreAll(0,  resultadosIndependientes.length,true,desde,hasta)){
            ConjuntoResultadoEstadistico resul[]=new ConjuntoResultadoEstadistico[hasta-desde];
            for (int i = 0; i < resul.length; i++) {
                resul[i]=resultadosIndependientes[desde+i];
            }
            return resul;
        }
        return null;
    }
    public int[] getFrecuenciaDeOriginalesOrdenados() {
        return frecuenciaDeOriginalesOrdenados;
    }

    public double[] getDatosOriginalesOrdenados() {
        return datosOriginalesOrdenados;
    }

}
