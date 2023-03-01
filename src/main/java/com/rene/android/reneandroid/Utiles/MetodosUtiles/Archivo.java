package com.rene.android.reneandroid.Utiles.MetodosUtiles;

import com.rene.android.reneandroid.Utiles.ClasesUtiles.Comparadores.ComparadorOrdenAlfabeticoFileName;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Creador1ConException;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Predicate;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar2;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.UtilizarConException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import static com.rene.android.reneandroid.Utiles.MetodosUtiles.MetodosUtiles.contieneStringContrario;
import static com.rene.android.reneandroid.Utiles.MetodosUtiles.MetodosUtiles.isEmptyOR;

import com.rene.android.reneandroid.LibreriasExternas.javx.imageio.stream.FileImageInputStream;
import com.rene.android.reneandroid.LibreriasExternas.javx.imageio.stream.FileImageOutputStream;

/**
 * Created by Rene on 24/2/2020.
 */

public abstract class Archivo {

    public static enum extencionImagen {
        JPG(".jpg"), JPNG(".jpng"), GIF(".gif"), ICO(".ico");
        private final String extencion;

        private extencionImagen(String extencion) {
            this.extencion = extencion;
        }

        public String getExtencion() {
            return extencion;
        }

        public static String[] extencionesImagen() {
            String extencionesVideos[] = new String[extencionImagen.values().length];
            for (int i = 0; i < extencionesVideos.length; i++) {
                extencionesVideos[i] = extencionImagen.values()[i].getExtencion();
            }
            return extencionesVideos;
        }

    }

    public static boolean esImagen(String nombre) {
        return esImagen(new File(nombre));
    }

    public static boolean esImagen(File f) {
        return Arrays.asList(extencionImagen.extencionesImagen()).contains(getExtencion(f).toLowerCase());
    }

    public static byte[] getBytesDeImg(String direccion) throws IOException {
        return getBytesDeImg(new File(direccion));
    }

    public static byte[] getBytesDeImg(File f) throws IOException {
        //ImageInputBuilder im=new ImageInputBuilder<ImageInputBuilder<B>>();

        FileImageInputStream img = new FileImageInputStream(f);
        byte[] contenido = new byte[(int) img.length()];
        img.read(contenido);
        return contenido;
    }

    public static void crearImagenYBorrarSiExiste(byte[] bytes, String newFileName) throws IOException {
        File dire = new File(newFileName);
        if (dire.exists()) {
            dire.delete();
        }
        crearImagen(bytes, dire);
    }

    public static void crearImagen(byte[] bytes, String newFileName) throws IOException {
        crearImagen(bytes, new File(newFileName));
    }

    public static void crearImagen(byte[] bytes, File dire) throws IOException {
//        File dire = new File(newFileName);
        dire.getParentFile().mkdirs();
        if(!dire.exists()){
            dire.createNewFile();
        }
//        System.out.println("salida: by="+bytes.length);
        //System.out.println("crear a");
        FileImageOutputStream imageOutput = new FileImageOutputStream(dire);
        //System.out.println("crear b");
        imageOutput.write(bytes, 0, bytes.length);
        //System.out.println("crear c");
        imageOutput.close();

    }

    public static String adaptarAntesDeExtencion(String direccion, String antesDeExtencion) {
        String exte = getExtencion(direccion);
        if (exte.isEmpty()) {
            return direccion + antesDeExtencion;
        }
        String nombre = direccion.substring(0, direccion.indexOf(exte));
        return nombre + antesDeExtencion + exte;
    }



    public static LinkedList<File> sortAlfabeticoName(LinkedList<File> a){
        Collections.sort(a,new ComparadorOrdenAlfabeticoFileName());
        return a;
    }
    /**
     * si profundidad=0 no entra en las subCarpetas internas
     * @param f
     * @param profundidad
     *
     * @param c
     * @throws Exception
     */
    public static void recorrerCarpetasExternas(File f, int profundidad,Creador1ConException<File,Boolean> c) throws Exception {
        if (f.isDirectory()) {
            File F[] = f.listFiles();
            if (F!=null) {
                for (int i = 0; i < F.length; i++) {
                    recorrerCarpetasYUtilizarTambienCarpetas(F[i], profundidad, c);
                }
            }

        } else {
//            System.out.println("realiza");
            c.crear(f);
        }
    }
    /**
     * si profundidad=0 no entra en las carpetas internas
     * @param f
     * @param profundidad
     *
     * @param c
     * @throws Exception
     */
    public static void recorrerCarpetasYUtilizarTambienCarpetas(File f, int profundidad, Creador1ConException<File,Boolean> c) throws Exception {
//        System.out.println("recorre");
        if (c.crear(f)&&f.isDirectory() && profundidad>0) {
            File F[] = f.listFiles();
            if (F!=null) {
                for (int i = 0; i < F.length; i++) {
                    recorrerCarpetasYUtilizarTambienCarpetas(F[i], profundidad-1, c);
                }
            }

        }
    }

    /**
     * si profundidad=0 no entra en las subCarpetas internas
     * @param f
     * @param profundidad
     * @param continuarDespuesDe
     * @param c
     * @throws Exception
     */
    public static void recorrerCarpetasExternas(File f, int profundidad,Predicate<File> continuarDespuesDe, UtilizarConException<File> c) throws Exception {
        if (f.isDirectory()) {
            File F[] = f.listFiles();
            if (F!=null) {
                for (int i = 0; i < F.length; i++) {
                    recorrerCarpetasYUtilizarTambienCarpetas(F[i], profundidad,continuarDespuesDe, c);
                }
            }

        } else {
//            System.out.println("realiza");
            c.utilizar(f);
        }
    }
    /**
     * si profundidad=0 no entra en las carpetas internas
     * @param f
     * @param profundidad
     * @param continuarDespuesDe
     * @param c
     * @throws Exception
     */
    public static void recorrerCarpetasYUtilizarTambienCarpetas(File f, int profundidad, Predicate<File> continuarDespuesDe, UtilizarConException<File> c) throws Exception {
//        System.out.println("recorre");
        c.utilizar(f);

        if (continuarDespuesDe.test(f)&&f.isDirectory() && profundidad>0) {
            File F[] = f.listFiles();
            if (F!=null) {
                for (int i = 0; i < F.length; i++) {
                    recorrerCarpetasYUtilizarTambienCarpetas(F[i], profundidad-1,continuarDespuesDe, c);
                }
            }

        }
    }
    /**
     * si profundidad=0 no entra en las subCarpetas internas
     * @param f
     * @param profundidad
     * @param c
     * @throws Exception
     */
    public static void recorrerCarpetasExternas(File f, int profundidad, UtilizarConException<File> c) throws Exception {
        if (f.isDirectory()) {
            File F[] = f.listFiles();
            if (F!=null) {
                for (int i = 0; i < F.length; i++) {
                  //  if(F[i]!=null){
                    recorrerCarpetasYUtilizarTambienCarpetas(F[i], profundidad, c);
                //}
                }
            }

        } else {
//            System.out.println("realiza");
            c.utilizar(f);
        }
    }

    /**
     * si profundidad=0 no entra en las carpetas internas
     * @param f
     * @param profundidad
     * @param c
     * @throws Exception
     */
    public static void recorrerCarpetasYUtilizarTambienCarpetas(File f,int profundidad, UtilizarConException<File> c) throws Exception {
//        System.out.println("recorre");
        c.utilizar(f);
        if (f.isDirectory() && profundidad>0) {
            File F[] = f.listFiles();
            if (F!=null) {
                for (int i = 0; i < F.length; i++) {
                    recorrerCarpetasYUtilizarTambienCarpetas(F[i], profundidad-1, c);
                }
            }

        }
    }


    public static void recorrerCarpetasExternas(File f, UtilizarConException<File> c) throws Exception {
        recorrerCarpetasExternas(f, false, c);
    }

    public static void recorrerCarpetasExternas(File f, boolean carpetasInternas, UtilizarConException<File> c) throws Exception {
//        System.out.println("recorre");
        if (f.isDirectory()) {
            File F[] = f.listFiles();
            if (F!=null) {
                for (int i = 0; i < F.length; i++) {
                    recorrerCarpetasYUtilizarTambienCarpetas(F[i], carpetasInternas, c);
                }
            }

        } else {
//            System.out.println("realiza");
            c.utilizar(f);
        }
    }

    private static void recorrerCarpetasYUtilizarTambienCarpetas(File f, boolean carpetasInternas, UtilizarConException<File> c) throws Exception {
//        System.out.println("recorre");
        c.utilizar(f);
        if (f.isDirectory() && carpetasInternas) {
            File F[] = f.listFiles();
            if (!f.getName().equals("Contenido_Carpeta_A_TXT")) {
                for (int i = 0; i < F.length; i++) {
                    recorrerCarpetasYUtilizarTambienCarpetas(F[i], carpetasInternas, c);
                }
            }

        }
    }
    public static boolean esAudio(File f){
        String extencion=getExtencion(f);
        if(extencion==null||extencion.isEmpty()){
            return false;
        }
        return MetodosUtiles.or(extencion,".mp3",".obb");
    }
    public static String cargarArchivoDeTexto(InputStream in) throws IOException {
        ByteArrayOutputStream op=new ByteArrayOutputStream();
        byte by[]=new byte[4096];
        int len=0;
        while((len=in.read(by))>0){
            op.write(by,0,len);

        }
        return new String(op.toByteArray(),"UTF-8");
    }

    public static void copiar(InputStream original, File copia) throws FileNotFoundException, IOException {
        if ( copia.isDirectory() ) {
            System.out.println("salta dire"+copia.getAbsolutePath());
            return;
        }
        if (!copia.exists()) {
            crearArchivo(copia);
        }
        BufferedInputStream lector = new BufferedInputStream(original);
        BufferedOutputStream escritor = new BufferedOutputStream(new FileOutputStream(copia));
        byte B[] = new byte[1000];
        int byteLeidos = lector.read(B);
        while (byteLeidos > 0) {
            escritor.write(B, 0, byteLeidos);
            byteLeidos = lector.read(B);
        }
        lector.close();
        escritor.close();
    }

    public static File adaptarExtencion(String direccion, String extencion, boolean sustituir) {
        return adaptarExtencion(new File(direccion), extencion, sustituir);
    }

    public static File adaptarExtencion(String direccion, String extencion) {
        return adaptarExtencion(new File(direccion), extencion, false);
    }

    public static File adaptarExtencion(File direccion, String extencion) {
        return adaptarExtencion(direccion, extencion, false);
    }

    public static File adaptarExtencion(File direccion, String extencion, boolean sustituir) {
        String direccionReal = direccion + "";
        if (!(direccionReal).contains(extencion) && !getExtencion(direccionReal).equals(extencion)) {
            direccionReal += extencion;
        }
        int numeroDeCopy = 2;
        String direccionReal2 = direccionReal;
        while (new File(direccionReal2).exists()) {
            if (sustituir) {
                new File(direccionReal2).delete();
                break;
            }
            direccionReal2 = direccionReal.substring(0, direccionReal.length() - extencion.length());
            direccionReal2 += "(" + numeroDeCopy + ")" + extencion;
            numeroDeCopy++;
        }
        return new File(direccionReal2);
    }
    /**
     * devulebe "" si no tiene una extencion
     *
     * @param direccion
     * @return
     */
    public static String getExtencion(String direccion) {
        return getExtencion(new File(direccion));
    }

    /**
     * devulebe "" si no tiene una extencion
     *
     * @param f
     * @return
     */
    public static String getExtencion(File f) {
        return f.getName().contains(".") ? f.getName().substring(contieneStringContrario(f.getName(), "."), f.getName().length()) : "";
    }
    public static File crearTextoYSustituir(File f, String nombre, String extencion, String lines) throws IOException {
        if (f.isDirectory()) {
            File archivo = adaptarExtencion(f + "/" + nombre, extencion, true);
            archivo.deleteOnExit();
         final   PrintWriter p = new PrintWriter(new FileWriter(archivo));
            MetodosUtiles.recorrerLineas(lines, new Utilizar2<String, Integer>() {
                @Override
                public void utilizar(String v, Integer integer) {
                    p.println(v);
                }
            });
          //  MetodosUtiles.recorrerLineas(lines, (v,indice)->p.println(v));
            p.close();
            return archivo;
        }
        return null;
    }
    public static boolean sonDirectory(File... F) {
        for (File f : F) {
            if (!f.isDirectory()) {
                System.out.println("salida: no es directori f="+f);
                return false;
            }
        }
        return true;
    }

    public static boolean existenYSonDirectory(File... f) {
        return existen(f) && sonDirectory(f);
    }
    public static void recorrerCarpetas(File f, UtilizarConException<File> c) throws Exception {
//        System.out.println("recorre");
        if (f.isDirectory()) {
            File F[] = f.listFiles();
            if (!f.getName().equals("Contenido_Carpeta_A_TXT")) {
                for (int i = 0; i < F.length; i++) {
                    recorrerCarpetas(F[i], c);
                }
            }

        } else {
//            System.out.println("realiza");
            c.utilizar(f);
        }
    }
    public static void copiarCarpetasYAplastarSiExistenDiferentes(final File original,final File destino) throws  Exception {
        boolean b=existenYSonDirectory(original, destino);
        System.out.println("salida: existenYSonDirectory(original, destino)="+b);
        if (b) {

            recorrerCarpetas(original, new UtilizarConException<File>() {
                @Override
                public void utilizar(File f) throws Exception {

                    File nuevaDireccion = new File(destino+"/" + getDireccionRelativa(original, f));
                    if (nuevaDireccion.exists()) {
                        if (nuevaDireccion.length() == f.length()) {
                            return;
                        }
                        nuevaDireccion.delete();
                    }else{
                        File parent=nuevaDireccion.getParentFile();
                        if(parent!=null){
                            parent.mkdirs();
                        }

                    }

                    copiar(f, nuevaDireccion);
                }
            });


        }
    }

    public static String getDireccionRelativa(File carpeta, File archivoInterno) {
        String patchInterno = archivoInterno.getAbsolutePath(), patchCarpeta = carpeta.getParentFile().getAbsolutePath();
        if (isEmptyOR(patchInterno, patchCarpeta) || patchCarpeta.length() + 2 > patchInterno.length()) {
            return "";
        }
        return patchInterno.substring(patchCarpeta.length() + 1);
    }
    private static void crearArchivo(File f) throws IOException {
        f.createNewFile();
    }
    /**
     * copia dos Archibos de texto de original a copia no es mio pero funciona
     *
     * @param original
     * @param copia
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void copiar(File original, File copia) throws FileNotFoundException, IOException {
        if (original.isDirectory() || copia.isDirectory() || !original.exists()) {
            return;
        }
        if (!copia.exists()) {
            crearArchivo(copia);
        }
        BufferedInputStream lector = new BufferedInputStream(new FileInputStream(original));
        BufferedOutputStream escritor = new BufferedOutputStream(new FileOutputStream(copia));
        byte B[] = new byte[1000];
        int byteLeidos = lector.read(B);
        while (byteLeidos > 0) {
            escritor.write(B, 0, byteLeidos);
            byteLeidos = lector.read(B);
        }
        lector.close();
        escritor.close();
    }

    public static boolean existen(File... fs) {
        for (File f : fs) {
            if (!f.exists()) {
                System.out.println("salida: no existe f="+f);
                return false;
            }
        }
        return true;
    }
    private static Object cargarArchivo(File f, String direccion) throws IOException, ClassNotFoundException {
        ObjectInputStream F3;
        if (direccion.isEmpty()) {
            F3 = new ObjectInputStream(new FileInputStream(f));
        } else {
            F3 = new ObjectInputStream(new FileInputStream(direccion));
        }

        Object O = F3.readObject();
        F3.close();
        return O;
    }

    public static Object cargarArchivo(String direccion) throws IOException, ClassNotFoundException {
        return cargarArchivo(new File(direccion), "");
    }
    public static void crearArchivo(File f, String nombre, String extencion, Object objetoPrederterminado) throws IOException {
        if (f.exists()) {
            ObjectOutputStream F = new ObjectOutputStream(new FileOutputStream(adjuntar(f, nombre, extencion)));
            F.writeObject(objetoPrederterminado);
            F.close();
        }
    }
    /**
     * devuelbe un File (f + "/" + nombre + extencion)
     *
     * @param f
     * @param nombre
     * @param extencion
     * @return
     */
    public static File adjuntar(File f, String nombre, String extencion) {
        return new File(f + "/" + nombre + extencion);
    }

    public static void copiarDesdeJar(String original, File copia) throws FileNotFoundException, IOException {
//        if (original.isDirectory() || copia.isDirectory() || !original.exists()) {
//            return;
//        }
        if (!copia.exists()) {
            crearArchivo(copia);
        }
        BufferedInputStream lector = new BufferedInputStream(new FileInputStream(direccionJar(original)));
        BufferedOutputStream escritor = new BufferedOutputStream(new FileOutputStream(copia));
        byte B[] = new byte[1000];
        int byteLeidos = lector.read(B);
        while (byteLeidos > 0) {
            escritor.write(B, 0, byteLeidos);
            byteLeidos = lector.read(B);
        }
        lector.close();
        escritor.close();
    }

    public static String direccionJar(String direccion) {
        return Archivo.class.getClassLoader().getResource(direccion).toExternalForm();
    }

    public static void recorrerSoloArchivosExternosEnCarpeta(File f, UtilizarConException<File> c) throws Exception {
        if (f.isDirectory()) {
            File F[] = f.listFiles();
           for (int i = 0; i < F.length; i++) {
                if (!F[i].isDirectory()) {
                    c.utilizar(F[i]);
                }
            }

        } else {
           c.utilizar(f);
        }
    }

    /**
     * devuelve el nombre sin extencion
     *
     * @param nombre
     * @return
     */
    public static String getNombre(String nombre) {
        return getNombre(new File(nombre));
    }

    /**
     * devuelve el nombre sin extencion
     *
     * @param f
     * @return
     */
    public static String getNombre(File f) {
        int indiceExtencion = indiceExtencion(f);
//        System.out.println(f.getName().charAt(indiceExtencion-3)+""+f.getName().charAt(indiceExtencion-2)+""+f.getName().charAt(indiceExtencion-1));
        return f.getName().substring(0, indiceExtencion < 0 ? f.getName().length() : indiceExtencion);
    }
    /**
     * deubuleve el indice donde comieza la extencion solo dentro del nombre o
     * -1 sino tiene
     *
     * @param f
     * @return
     */
    public static int indiceExtencion(File f) {
//        int i= contieneStringContrario(f.getName(), ".");
//        if(esNumero(f.getName().charAt(i+1)+"")){
//        return -1;
//        }
        return indiceExtencion(f.getName());
    }

    public static int indiceExtencion(String nombre) {
        //return contieneStringContrario(nombre, ".");
        int i = contieneStringContrario(nombre, ".");
        // if (i != -1 && i < nombre.length() - 1 && (esNumero(nombre.charAt(i + 1) + ""))) {
        if (i != -1 && i < nombre.length() - 1 && ((!MetodosUtiles.esCharLetra(nombre.charAt(i + 1))) ? (i < nombre.length() - 2 ? (nombre.charAt(i + 1) != '3' && nombre.charAt(i + 2) != 'g') : true) : (false))) {
            return -1;
        }
        return i;
    }


}
