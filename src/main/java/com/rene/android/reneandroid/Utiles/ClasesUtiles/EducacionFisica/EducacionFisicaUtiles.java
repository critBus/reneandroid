/*


 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rene.android.reneandroid.Utiles.ClasesUtiles.EducacionFisica;

import com.rene.android.reneandroid.Utiles.MetodosUtiles.MetodosUtiles;
import static com.rene.android.reneandroid.Utiles.MetodosUtiles.MetodosUtiles.entre;
import static com.rene.android.reneandroid.Utiles.MetodosUtiles.Tempus.tiMin;

import java.util.Date;

/**
 * private byte calidadDeFlexiones[][]={ //Eddad (-de)Malo Medio Bueno MuyBueno
 * (+=)Exelente {20,29 , 20 ,20,34 ,35,44 ,45,53 ,54 }, {30,39 , 15 ,15,23
 * ,24,34 ,35,43 ,44 }, {40,49 , 12 ,12,19 ,20,29 ,30,38 ,39 }, {50,59 , 8 ,8
 * ,14 ,15,23 ,24,33 ,34 }, {60,100 , 5 ,5 ,9 ,10,19 ,20,28 ,29 }, };
 *
 * @author Rene
 */
public abstract class EducacionFisicaUtiles {

    public final static double[][] testCooper = {{1500, 22.2}, {1550, 23.4}, {1600, 24.5},
    {1650, 25.6}, {1700, 26.7}, {1750, 27.8}, {1800, 28.9}, {1850, 30.0}, {1900, 31.2},
    {1950, 32.3}, {2000, 33.4}, {2050, 34.5}, {2100, 35.6}, {2150, 36.8}, {2200, 37.9},
    {2250, 39.0}, {2300, 40.1}, {2350, 41.6}, {2400, 42.4}, {2450, 43.5}, {2500, 44.6},
    {2550, 45.7}, {2600, 46.5}, {2650, 47.9}, {2700, 49.0}, {2750, 50.2}, {2800, 51.3},
    {2850, 52.1}, {2900, 53.3}, {2950, 54.0}, {3000, 55.5}, {3050, 56.9}, {3100, 58.0},
    {3150, 59.1}, {3200, 60.2}, {3250, 61.4}, {3300, 62.3}, {3350, 63.6}, {3400, 64.7},
    {3450, 65.8}, {3500, 66.9}, {3550, 68.0}, {3600, 69.1}, {3650, 70.3}, {3700, 71.4},
    {3750, 72.3}, {3800, 73.6}, {3850, 74.8}, {3900, 75.9}, {3950, 77.0}, {4000, 78.1}};

    public static double getTestCooperFormula(int distancia) {

        return MetodosUtiles.entre(1500, distancia, 4000, true) ? (22.351 * ((double) distancia / (double) 1000)) - 11.288 : -1;
    }

    public static double getTestCooperTabla(int distancia) {
        if (MetodosUtiles.entre(1500, distancia, 4000, true)) {
            for (int i = 0; i < testCooper.length; i++) {
                if (distancia == testCooper[i][0]) {
                    return testCooper[i][1];
                }
                if (distancia < testCooper[i][0]) {
                    double distanciaAlAnterior = distancia - testCooper[i - 1][0], distanciaAlSiguiente = testCooper[i][0] - distancia;
                    return distanciaAlAnterior < distanciaAlSiguiente ? testCooper[i - 1][1] : testCooper[i][1];
                }

            }
        }
        return -1;
    }
//60
    public static final int calidadDeFlexiones[][] = {
        //Eddad  (-de)Malo Medio  Bueno   MuyBueno  (+=)Exelente
        {20, 29, 20, 20, 34, 35, 44, 45, 53, 54},
        {30, 39, 15, 15, 23, 24, 34, 35, 43, 44},
        {40, 49, 12, 12, 19, 20, 29, 30, 38, 39},
        {50, 59, 8, 8, 14, 15, 23, 24, 33, 34},
        {60, 100, 5, 5, 9, 10, 19, 20, 28, 29},};

    public static enum tipoDeCalidad {
        MALO, MEDIO, BUENO, MUY_BUENO, EXELENTE

    }

    public static enum tipoDeEficiencia {
        DEVIL, REGULAR, BIEN, MUY_BIEN, EXELENTE

    }

    public static enum tipoDeValoracion {
        MALO, SUFICIENTE, BUENO, NOTABLE, SOBRESALIENTE

    }
 public static enum tipoDeRendimiento {
        MUY_BAJO, BAJO, MEDIO, BUENO, EXELENTE

    }
    public static tipoDeValoracion getBurpeeTest(int repeticiones) {//7
        if (repeticiones < 30) {
            return tipoDeValoracion.MALO;
        }
        if (entre(30, repeticiones, 39, true)) {
            return tipoDeValoracion.SUFICIENTE;
        }
        if (entre(40, repeticiones, 49, true)) {
            return tipoDeValoracion.BUENO;
        }
        if (entre(50, repeticiones, 60, true)) {
            return tipoDeValoracion.NOTABLE;
        }
        if (repeticiones > 60) {
            return tipoDeValoracion.SOBRESALIENTE;
        }
        return null;
    }
    //puntos,chicos,chicas
    public static final double TABLA_LanzamientoDeBalónMedicinal[][] = {{1, 3.5, 2.92},
    {1.5, 3.9, 3},
    {2.0, 4.2, 3.3},
    {2.5, 4.7, 3.7},
    {3, 4.85, 3.92},
    {3.5, 5, 4},
    {4, 5.3, 4.3},
    {4.5, 5.55, 4.5},
    {5, 5.9, 4.65},
    {5.5, 6, 4.9},
    {6, 6.3, 5},
    {6.5, 6.7, 5.15},
    {7, 7.05, 5.4},
    {7.5, 7.35, 5.91},
    {8, 8.2, 6.2},
    {8.5, 8.45, 6.45},
    {9, 8.9, 6.72},
    {9.5, 9.15, 7.1},
    {10, 9.45, 7.85}};
    public static final Date TABLA_TEST_DE_ROCKPORT[][][]={
        {{tiMin(0),tiMin(13)},       {tiMin(0),tiMin(14)},       {tiMin(0),tiMin(13,30)}    ,{tiMin(0),tiMin(14,30)}},
        {{tiMin(13,01),tiMin(15,30)},{tiMin(14,01),tiMin(16,30)},{tiMin(13,31),tiMin(16)}   ,{tiMin(14,31),tiMin(17)}},
        {{tiMin(15,31),tiMin(18)}   ,{tiMin(16,31),tiMin(19)}   ,{tiMin(16,01),tiMin(18,30)},{tiMin(17,01),tiMin(19,30)}},
        {{tiMin(18,01),tiMin(19,30)},{tiMin(19,01),tiMin(21,30)},{tiMin(18,31),tiMin(20)}   ,{tiMin(19,31),tiMin(22)}},
        {{tiMin(19,30),tiMin(60)},   {tiMin(21,30),tiMin(60)},   {tiMin(20),tiMin(60)}      ,{tiMin(22),tiMin(60)}}
        
    };
    
    public static tipoDeRendimiento getTEST_DE_ROCKPORT_Estimado(int edad, boolean hombre,Date tiempo){//4
        int iEd=edad<40?1:0,is=hombre?0:2;
            iEd+=is;
        for (int i = 0; i < TABLA_TEST_DE_ROCKPORT.length; i++) {
            Date menor=TABLA_TEST_DE_ROCKPORT[i][iEd][0],mayor=TABLA_TEST_DE_ROCKPORT[i][iEd][1];
            //System.out.println("iEd="+iEd);
            //System.out.println("mayor="+mayor);
            if(MetodosUtiles.menorIgualQue(tiempo, mayor)){
            return tipoDeRendimiento.values()[tipoDeRendimiento.values().length-(i+1)];
            }
        }
        return tipoDeRendimiento.MUY_BAJO;
    }
    
    public static double getTEST_DE_ROCKPORT(double peso, double edad, boolean hombre, int tiempoEnSeg, double FrecuenciaCardiaca) {
        int sexo = hombre ? 1 : 0;
        double VO2max = 132.853 - (0.1692 * peso) - (0.3877 * edad) + (6.315 * sexo) - (3.2649 * (tiempoEnSeg / 60)) - (0.1565 * FrecuenciaCardiaca);
        return VO2max;
    }

    public static double getTestDeLanzamientoDeBalónMedicinal(double metros, boolean chico) {//9
        int iCh = chico ? 1 : 2;
        double minimo = TABLA_LanzamientoDeBalónMedicinal[0][iCh];
        if (metros < minimo) {
            return 0;
        }
        for (int i = 1; i < TABLA_LanzamientoDeBalónMedicinal.length; i++) {
            double puntos = TABLA_LanzamientoDeBalónMedicinal[i][0], metrosExigidos = TABLA_LanzamientoDeBalónMedicinal[i][iCh];
            if (metros == metrosExigidos) {
                return puntos;
            }
            if (metros < metrosExigidos) {
                return TABLA_LanzamientoDeBalónMedicinal[i - 1][0];
            }
        }
        return TABLA_LanzamientoDeBalónMedicinal[TABLA_LanzamientoDeBalónMedicinal.length-1][0];
    }

    public static tipoDeCalidad getCalidadDeflexiones(int edad, int flexiones) {
        for (int i = 0; i < calidadDeFlexiones.length; i++) {
            if (entre(calidadDeFlexiones[i][0], edad, calidadDeFlexiones[i][1], true)) {
                if (flexiones < calidadDeFlexiones[i][2]) {
                    return tipoDeCalidad.MALO;
                }
                for (int j = 3; j < calidadDeFlexiones[i].length - 1; j += 2) {
                    if (entre(calidadDeFlexiones[i][j], flexiones, calidadDeFlexiones[i][j + 1], true)) {
                        switch (j) {
                            case 3:
                                return tipoDeCalidad.MEDIO;
                            case 5:
                                return tipoDeCalidad.BUENO;
                            case 7:
                                return tipoDeCalidad.MUY_BUENO;

                        }
                    }
                }
                return tipoDeCalidad.EXELENTE;
            }
        }
        return null;
    }

    public static tipoDeEficiencia getTEST_DE_RUFFIER_DICKSON(int basal, int adaptación, int recuperación) {
        int resultado = ((basal + adaptación + recuperación) - 200) / 10;
        if (resultado < 0) {
            return tipoDeEficiencia.EXELENTE;
        }
        if (entre(0, resultado, 5, true)) {
            return tipoDeEficiencia.MUY_BIEN;
        }
        if (entre(5, resultado, 10, true)) {
            return tipoDeEficiencia.BIEN;
        }
        if (entre(10, resultado, 15, true)) {
            return tipoDeEficiencia.REGULAR;
        }
        if (entre(15, resultado, 20, true)) {
            return tipoDeEficiencia.REGULAR;
        }
        return null;
    }

    public static tipoDeCalidad getTestDeFlexibilidadTrenSuperior(int estiramiento) {
        if (estiramiento >= 50) {
            return tipoDeCalidad.MALO;
        }
        if (entre(41, estiramiento, 49, true)) {
            return tipoDeCalidad.MEDIO;
        }
        if (entre(31, estiramiento, 40, true)) {
            return tipoDeCalidad.BUENO;
        }
        if (entre(21, estiramiento, 30, true)) {
            return tipoDeCalidad.MUY_BUENO;
        }
        if (estiramiento < 20) {
            return tipoDeCalidad.EXELENTE;
        }
        return null;
    }

    public static tipoDeCalidad getTestDeFlexibilidadTrenInferior(int estiramiento) {
        if (estiramiento < 23) {
            return tipoDeCalidad.MALO;
        }
        if (entre(23, estiramiento, 29, true)) {
            return tipoDeCalidad.MEDIO;
        }
        if (entre(30, estiramiento, 35, true)) {
            return tipoDeCalidad.BUENO;
        }
//    if(entre(21, estiramiento, 30, true)){return tipoDeCalidad.MUY_BUENO;}
        if (estiramiento > 20) {
            return tipoDeCalidad.MUY_BUENO;
        }
        return null;
    }
}
