/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rene.android.reneandroid.Utiles.PW;

/**
 * Version 0.1
 * @author Rene
 */
public abstract class UtilitesJavaScripts {

    public static String crearGraficoDeBarras(String idCanvas,double valoresX[],int valoresY[]){
    String Xs[]=new String[valoresX.length];
        for (int i = 0; i < Xs.length; i++) {
            Xs[i]=valoresX[i]+"";
        }
        return crearGraficoDeBarras(idCanvas, Xs, valoresY);
    }
    public static String crearGraficoDeBarras(String idCanvas,String valoresX[],int valoresY[]){
    String valoresXString="[",valoresYString="[";
        for (int i = 0; i < valoresX.length; i++) {
            String extra=i==0?"":",";
            valoresXString+=extra+valoresX[i];
            valoresYString+=extra+valoresY[i];
        }
        valoresXString+="]";
        valoresYString+="]";
        return "\n var barChartData = {\n" +
"		labels : "+valoresXString+",\n" +
"		datasets : [\n" +
"			{\n" +"fillColor : \"rgba(48, 164, 255, 0.2)\",\n" +
                "\t\t\t\tstrokeColor : \"rgba(48, 164, 255, 0.8)\",\n" +
                "\t\t\t\thighlightFill : \"rgba(48, 164, 255, 0.75)\",\n" +
                "\t\t\t\thighlightStroke : \"rgba(48, 164, 255, 1)\"," +
"				data : "+valoresYString+"}]};\n var chart2 = document.getElementById(\""+idCanvas+"\").getContext(\"2d\");\n" +
"	window.myBar = new Chart(chart2).Bar(barChartData, {\n" +
"	responsive: true,\n" +
"	scaleLineColor: \"rgba(0,0,0,.2)\",\n" +
"	scaleGridLineColor: \"rgba(0,0,0,.05)\",\n" +
"	scaleFontColor: \"#c5c7cc\"\n" +
"	});";
    }
}
