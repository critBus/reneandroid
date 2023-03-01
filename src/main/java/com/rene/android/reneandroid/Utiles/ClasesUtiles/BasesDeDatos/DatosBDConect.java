package com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos;

import java.io.File;

/**
 * Created by Rene on 07/01/2021.
 */

public class DatosBDConect {
    private String controlador, url_basesDeDatos, usuario, contraseña, servidor, nombreBD, puerto, ultimuSQl;
    private BDTipoDeConexion tipoDeConxion;
    private File url;

    private Object resultado;
    private boolean mostrarResultadoConsola = false;

    public DatosBDConect(String controlador, String url_basesDeDatos, String usuario, String contraseña, String servidor, String nombreBD, String puerto, String ultimuSQl, BDTipoDeConexion tipoDeConxion, File url, Object resultado, boolean mostrarResultadoConsola) {
        this.controlador = controlador;
        this.url_basesDeDatos = url_basesDeDatos;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.servidor = servidor;
        this.nombreBD = nombreBD;
        this.puerto = puerto;
        this.ultimuSQl = ultimuSQl;
        this.tipoDeConxion = tipoDeConxion;
        this.url = url;
        this.resultado = resultado;
        this.mostrarResultadoConsola = mostrarResultadoConsola;
    }

    public String getControlador() {
        return controlador;
    }

    public void setControlador(String controlador) {
        this.controlador = controlador;
    }

    public String getUrl_basesDeDatos() {
        return url_basesDeDatos;
    }

    public void setUrl_basesDeDatos(String url_basesDeDatos) {
        this.url_basesDeDatos = url_basesDeDatos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getNombreBD() {
        return nombreBD;
    }

    public void setNombreBD(String nombreBD) {
        this.nombreBD = nombreBD;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getUltimuSQl() {
        return ultimuSQl;
    }

    public void setUltimuSQl(String ultimuSQl) {
        this.ultimuSQl = ultimuSQl;
    }

    public BDTipoDeConexion getTipoDeConxion() {
        return tipoDeConxion;
    }

    public void setTipoDeConxion(BDTipoDeConexion tipoDeConxion) {
        this.tipoDeConxion = tipoDeConxion;
    }

    public File getUrl() {
        return url;
    }

    public void setUrl(File url) {
        this.url = url;
    }

    public Object getResultado() {
        return resultado;
    }

    public void setResultado(Object resultado) {
        this.resultado = resultado;
    }

    public boolean isMostrarResultadoConsola() {
        return mostrarResultadoConsola;
    }

    public void setMostrarResultadoConsola(boolean mostrarResultadoConsola) {
        this.mostrarResultadoConsola = mostrarResultadoConsola;
    }
}
