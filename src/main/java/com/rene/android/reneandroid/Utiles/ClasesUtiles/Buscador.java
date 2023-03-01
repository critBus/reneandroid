package com.rene.android.reneandroid.Utiles.ClasesUtiles;

import com.rene.android.reneandroid.Utiles.MetodosUtiles.Archivo;

import java.io.File;
import java.util.LinkedList;

/**
 * Created by Rene on 15/07/2020.
 */

public class Buscador {
   //public final static String TIPO_CARPETAS="carpetas";
   private File actual,hijos[],hijosFiltrados[];
   private String  nombreHijosFiltrados[],filtroPorNombre,extenciones[];
   private boolean soloCarpetas,solosArchivos;

    public Buscador() {
    }

    public Buscador(File actual) {
        inicializar(actual);
    }
    protected void inicializar(File actual) {
        this.actual = actual;
        soloCarpetas=false;
        solosArchivos=false;
        filtrar();
    }

    public void filtrar(){
        if(actual.isDirectory()){
            hijos=actual.listFiles();
            if(filtroPorNombre!=null&&!filtroPorNombre.isEmpty()){
                String fil=filtroPorNombre.toLowerCase();//ty=filtroTipo.toLowerCase();
                LinkedList<File> lf=new LinkedList<>();

                for (int i = 0; i <hijos.length ; i++) {
                    String nombre=hijos[i].getName();
                    if ((!fil.isEmpty())?(nombre.toLowerCase().contains(fil)):(true)){
                        if (hijos[i].isFile()){
                            if (soloCarpetas){
                                continue;
                            }
                            if(extenciones!=null&&extenciones.length>0){
                                String ext=Archivo.getExtencion(hijos[i]);
                                for (int j = 0; j <extenciones.length ; j++) {
                                    if (extenciones[i].equals(ext)){
                                        lf.add(hijos[i]);
                                        break;
                                    }
                                }
                                continue;
                            }

                        }else {
                            if (solosArchivos){
                                continue;
                            }

                        }
                        lf.add(hijos[i]);

                    }
                }
                hijosFiltrados=lf.toArray(new File[0]);
            }else{
                hijosFiltrados=hijos;
            }

            nombreHijosFiltrados=new String[hijosFiltrados.length];
            for (int i = 0; i <hijosFiltrados.length ; i++) {
                nombreHijosFiltrados[i]=hijosFiltrados[i].getName();
            }
        }else{
            releaseRecursosBasicos();
        }
    }

    public void atras(){
        //System.out.println("salida: actual="+actual);
        if (actual.isDirectory()){
           // System.out.println("salida: esDirectorio");
           // System.out.println("salida: actual.getParentFile()="+actual.getParentFile());
        actual=actual.getParentFile();
        filtrar();
        }
        //else{
          //  releaseRecursosBasicos();
        //}
    }

    private void releaseRecursosBasicos(){
        hijos=null;
        hijosFiltrados=null;
        nombreHijosFiltrados=null;
        filtroPorNombre=null;
        extenciones=null;
        solosArchivos=soloCarpetas=false;
    }

    public void abrir(File f){
        if(f.isDirectory()){
          actual=f;
            filtrar();
        }
    }
    public void abrir(int i){
        if (hijosFiltrados.length>i&&i>-1){
            abrir(hijosFiltrados[i]);
        }
    }
    public File getActual() {
        return actual;
    }

    public void setActual(File actual) {
        this.actual = actual;
    }

    public File[] getHijos() {
        return hijos;
    }

    public void setHijos(File[] hijos) {
        this.hijos = hijos;
    }

    public File[] getHijosFiltrados() {
        return hijosFiltrados;
    }

    public void setHijosFiltrados(File[] hijosFiltrados) {
        this.hijosFiltrados = hijosFiltrados;
    }

    public String[] getNombreHijosFiltrados() {
        return nombreHijosFiltrados;
    }

    public void setNombreHijosFiltrados(String[] nombreHijosFiltrados) {
        this.nombreHijosFiltrados = nombreHijosFiltrados;
    }

    public String getFiltroPorNombre() {
        return filtroPorNombre;
    }

    public void setFiltroPorNombre(String filtroPorNombre) {
        this.filtroPorNombre = filtroPorNombre;
    }

    public String[] getExtenciones() {
        return extenciones;
    }

    public void setExtenciones(String[] extenciones) {
        this.extenciones = extenciones;
    }

    public boolean isSoloCarpetas() {
        return soloCarpetas;
    }

    public void setSoloCarpetas(boolean soloCarpetas) {
        this.soloCarpetas = soloCarpetas;
        if (soloCarpetas){
            solosArchivos=false;
        }
    }

    public boolean isSolosArchivos() {
        return solosArchivos;
    }

    public void setSolosArchivos(boolean solosArchivos) {
        this.solosArchivos = solosArchivos;
        if (solosArchivos){
            soloCarpetas=false;
        }
    }

    public File get(int i){
        return getHijosFiltrados()[i];
    }
}
