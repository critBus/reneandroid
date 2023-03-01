package com.rene.android.reneandroid.Utiles.ClasesUtiles.Tiempo.Calculador;

/**
 * Created by Rene on 28/12/2021.
 */

public class CantidadDeTiempo {
    private int cantidadDeSeguntos;
    private int horas,minutos,segundos;
    public CantidadDeTiempo(){
        this(0);
    }
    public CantidadDeTiempo(int horas, int minutos, int segundos) {
        //this((60*60*horas)+(60*46)+segundos);
        this((60*60*horas)+(60*minutos)+segundos);
    }


    public CantidadDeTiempo add(CantidadDeTiempo c){
        return new CantidadDeTiempo(this.cantidadDeSeguntos+c.cantidadDeSeguntos);
    }

    public CantidadDeTiempo sub(CantidadDeTiempo c){
//        System.out.println("salida: this.cantidadDeSeguntos="+this.cantidadDeSeguntos);
//        System.out.println("salida: c.cantidadDeSeguntos="+c.cantidadDeSeguntos);
//        System.out.println("salida: resta="+(this.cantidadDeSeguntos-c.cantidadDeSeguntos));
        CantidadDeTiempo r=new CantidadDeTiempo(this.cantidadDeSeguntos-c.cantidadDeSeguntos);
//        System.out.println("salida r="+r);
        return r;
    }

    public CantidadDeTiempo(int cantidadDeSeguntos) {
        if(cantidadDeSeguntos<0){
            cantidadDeSeguntos=0;
        }
        setCantidadDeSeguntos(cantidadDeSeguntos);
    }
    public boolean isEmpty(){
        return cantidadDeSeguntos==0;
    }


    public int getCantidadDeSeguntos() {
        return cantidadDeSeguntos;
    }

    public void setCantidadDeSeguntos(int cantidadDeSeguntos) {
        this.cantidadDeSeguntos = cantidadDeSeguntos;
        this.horas = cantidadDeSeguntos / 3600;
        this.minutos = (cantidadDeSeguntos % 3600) / 60;
        this.segundos = cantidadDeSeguntos % 60;
    }

    @Override
    public String toString() {
        return String.format("%03d:%02d:%02d", this.horas,this.minutos,this.segundos); //To change body of generated methods, choose Tools | Templates.
    }

    public int getHoras() {
        return horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public int getSegundos() {
        return segundos;
    }
}
