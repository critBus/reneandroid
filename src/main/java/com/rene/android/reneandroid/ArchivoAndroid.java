package com.rene.android.reneandroid;

import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;
import android.os.Environment;

import com.rene.android.reneandroid.Clases.Exceptions.SDNoExisteException;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Creador1ConException;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.UtilizarConException;
import com.rene.android.reneandroid.Utiles.MetodosUtiles.Archivo;
import com.rene.android.reneandroid.Utiles.MetodosUtiles.MetodosUtiles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

import static com.rene.android.reneandroid.Utiles.MetodosUtiles.Archivo.copiar;
import static com.rene.android.reneandroid.Utiles.MetodosUtiles.Archivo.existenYSonDirectory;
import static com.rene.android.reneandroid.Utiles.MetodosUtiles.MetodosUtiles.contieneStringContrario;
import static com.rene.android.reneandroid.Utiles.MetodosUtiles.MetodosUtiles.isEmptyOR;

/**
 * Created by Rene on 7/6/2020.
 */

public abstract class ArchivoAndroid {

    public static File escribirLogInternoLApp(String carpetaProyecto,String nombre,String contenido) throws IOException {
        return escribirLogInterno("LeyenApps/"+carpetaProyecto,nombre,contenido);
    }

    public static boolean esDirectorioPadre(File f){
        return f.toString().equals("/storage");
    }
    public static boolean esDirectorioTipoPadre(File f){
        return f.toString().equals("/storage")||f.toString().equals("/storage/emulated");
    }
    public static File getDirectorioPadre(){
        return new File("/storage");
    }
    public static boolean existeSD(){

        try {
            return getFileExterno()!=null;
        } catch (Exception e) {
            return false;
        }
    }
    public static boolean esFileInterno(File f){
        return f.toString().contains("/storage/emulated/0");
    }

    public static File getFileExterno() throws Exception{
        final String carpetasDefault[]={"storage","emulated","enc_emulated","self"};
        final File res[]={null};
        //boolean coninuar[]={true};
        Archivo.recorrerCarpetasExternas(new File((getFileInterno().toString()).replace("/emulated/0", ""))
                , 0, new Creador1ConException<File, Boolean>() {
                    @Override
                    public Boolean crear(File file) throws Exception {
                        //System.out.println("salida: file="+file);
                        if (MetodosUtiles.or(file.getName(),carpetasDefault)){
                            return true;
                        }

                        res[0]=file;
                       // System.out.println("salida: lo salvo res[0]="+res[0]);
                        return false;
                    }
                });

        return res[0];
    }
    public static File getFileExterno(String direccion){
        if (direccion.trim().isEmpty()){
            return null;
        }
        File externo=null;
        try {
            externo=getFileExterno();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (externo==null){
            return null;
        }
        File f=new File(externo+"/" + direccion);

        return f;
    }
    public static void copiarCarpetasYAplastarSiExistenDiferentes(File original,String destino,boolean interno) throws Exception{
        if(interno){
            ArchivoAndroid.copiarCarpetasEnInternoYAplastarSiExistenDiferentes( original,destino);
        }else{
           // System.out.println("salida: intenta copiar en externo");
            ArchivoAndroid.copiarCarpetasEnExternoYAplastarSiExistenDiferentes( original,destino);
        }
    }
    public static void copiarCarpetasEnInternoYAplastarSiExistenDiferentes(File original,String destino) throws Exception{
        File fdestino = ArchivoAndroid.getFileInterno(destino);
        if (!fdestino.exists()){
            fdestino.mkdirs();
        }
        Archivo.copiarCarpetasYAplastarSiExistenDiferentes(original, fdestino);
    }
    public static void copiarCarpetasEnExternoYAplastarSiExistenDiferentes(File original,String destino) throws Exception{
        File fdestino = ArchivoAndroid.getFileExterno(destino);
        //System.out.println("salida: existe externo");
        if (fdestino!=null){
            //if (!fdestino.exists()){
                fdestino.mkdirs();
            //}
            //System.out.println("salida:2 se va a copiar original="+original);
            //System.out.println("salida:2 en fdestino="+fdestino);
            Archivo.copiarCarpetasYAplastarSiExistenDiferentes(original, fdestino);
        }else{
            //System.out.println("salida: salto con no existe sd");
            throw new SDNoExisteException("No hay conectada niguna SD");
        }

    }
    public static File[] getPaquetesDeAplicaciones() throws Exception{
        LinkedList<File> lf=getPaquetesDeAplicacionesInternasLL();
        lf.addAll(getPaquetesDeAplicacionesExternasLL());
        return Archivo.sortAlfabeticoName(lf).toArray(new File[0]);
    }
    public static LinkedList<File> getPaquetesDeAplicacionesInternasLL()throws Exception{
        final  LinkedList<File> lf=new LinkedList<>();
        Archivo.recorrerCarpetasExternas(ArchivoAndroid.getFileInterno("Android/data"), new UtilizarConException<File>() {
            @Override
            public void utilizar(File file) throws Exception {
                lf.add(file);
            }
        });
        return lf;
    }
    public static File[] getPaquetesDeAplicacionesInternas()throws Exception{
       // final  LinkedList<File> lf=new LinkedList<>();
     /*   Archivo.recorrerCarpetasExternas(ArchivoAndroid.getFileInterno("Android/data"), new UtilizarConException<File>() {
            @Override
            public void utilizar(File file) throws Exception {
                lf.add(file);
            }
        }); */
        //return lf.toArray(new File[0]);
        return getPaquetesDeAplicacionesInternasLL().toArray(new File[0]);
    }
    public static LinkedList<File>  getPaquetesDeAplicacionesExternasLL()throws Exception{
        final  LinkedList<File> lf=new LinkedList<>();
        File externo=ArchivoAndroid.getFileExterno("Android/data");
        if (externo!=null){
            Archivo.recorrerCarpetasExternas(externo, new UtilizarConException<File>() {
                @Override
                public void utilizar(File file) throws Exception {
                    lf.add(file);
                }
            });}
        return lf;
    }
    public static File[] getPaquetesDeAplicacionesExternas()throws Exception{
       /* final  LinkedList<File> lf=new LinkedList<>();
        File externo=ArchivoAndroid.getFileExterno("Android/data");
        if (externo!=null){
        Archivo.recorrerCarpetasExternas(externo, new UtilizarConException<File>() {
            @Override
            public void utilizar(File file) throws Exception {
                lf.add(file);
            }
        });}
        return lf.toArray(new File[0]);*/
        return getPaquetesDeAplicacionesExternasLL().toArray(new File[0]);
    }

    public static File[] getFilesInternos()throws Exception{
      final  LinkedList<File> lf=new LinkedList<>();
        recorrerFileInternoSinEntrar(new UtilizarConException<File>() {
            @Override
            public void utilizar(File file) throws Exception {
                lf.add(file);
            }
        });
        return lf.toArray(new File[0]);
    }



    public static void recorrerFileInternoSinEntrar( UtilizarConException<File> c) throws Exception {
        Archivo.recorrerCarpetasExternas(ArchivoAndroid.getFileInterno(), c);
    }

    public static File escribirLogInterno(String carpeta,String nombre,String contenido) throws IOException {
        File archivo=getFileInterno(carpeta+"/" +nombre+".txt");
        if (!archivo.exists()){
            //crearArchivoTEXTOInterno(carpeta,nombre,".txt",null);
            crearArchivoTEXTOInterno(carpeta,nombre,".txt");
        }
        escribirEnArchivoTEXTO(archivo,new String[]{contenido},true);
        return archivo;
    }

    public static void escribirEnArchivoTEXTO(File archivo,String contenido[],boolean aContinuacion) throws IOException {
        if (!archivo.exists()){
            PrintWriter pp= new PrintWriter(archivo);
            pp.close();
            //archivo.createNewFile();
        }
        PrintWriter p=new PrintWriter(new FileWriter(archivo,aContinuacion));
        if(contenido!=null){
            for (int i = 0; i <contenido.length ; i++) {
                p.println(contenido[i]);
            }
        }
        p.close();
    }
    public static File crearTXT(String carpeta,String nombre,String contenido[],boolean interno) throws Exception{
        return interno?crearTXTInterno(carpeta, nombre, contenido):crearTXTExterno(carpeta, nombre, contenido);

    }
    public static File crearTXTInterno(String carpeta,String nombre,String contenido[]) throws IOException {
      return  crearArchivoTEXTOInterno(carpeta,nombre,".txt",contenido);
    }
    public static File crearTXTExterno(String carpeta,String nombre,String contenido[]) throws Exception {
        return  crearArchivoTEXTOExterno(carpeta,nombre,".txt",contenido);
    }
    public static File crearArchivoTEXTOInterno(String carpeta,String nombre,String extencion,String contenido) throws IOException {
        return crearArchivoTEXTOInterno(carpeta, nombre, extencion, contenido.split("\n"));
    }
    public static File crearArchivoTEXTOInterno(String carpeta,String nombre,String extencion,String contenido[]) throws IOException {
        File fcarpeta=crearCarpetaInterna(carpeta);
        File archivo=new File(fcarpeta+File.separator +nombre+extencion);
        //System.out.println("salida: crear archivo interno existe car="+fcarpeta.exists()+" archivo="+archivo);

        escribirEnArchivoTEXTO(archivo,contenido,false);
        return archivo;
    }
    public static File crearArchivoTEXTOInterno(String carpeta,String nombre,String extencion) throws IOException {
        File fcarpeta=crearCarpetaInterna(carpeta);
        File archivo=new File(fcarpeta+File.separator +nombre+extencion);
        //System.out.println("salida: crear archivo interno existe car="+fcarpeta.exists()+" archivo="+archivo);
        escribirEnArchivoTEXTO(archivo,null,false);
      return  archivo;
    }
    public static File crearArchivoTEXTOExterno(String carpeta,String nombre,String extencion,String contenido[]) throws Exception {
        File fcarpeta=crearCarpetaExterna(carpeta);
        File archivo=new File(fcarpeta+File.separator +nombre+extencion);
        //System.out.println("salida: crear archivo interno existe car="+fcarpeta.exists()+" archivo="+archivo);

        escribirEnArchivoTEXTO(archivo,contenido,false);
        return archivo;
    }
    public static File crearCarpetaInterna(String direccionCarpeta){
        File f=getFileInterno(direccionCarpeta);
        //System.out.println("salida: crear carpeta f="+f);
       // if (!f.isDirectory()){
         //   System.out.println("salida: no creo la carpeta");
        // return null;
        //}

        f.mkdirs();
        //System.out.println("salida: la creo");
        return f;
    }
    public static File crearCarpetaExterna(String direccionCarpeta) throws Exception{
        File f=getFileExterno(direccionCarpeta);
        if (f==null){
            throw new SDNoExisteException();
        }
         f.mkdirs();
        //System.out.println("salida: la creo");
        return f;
    }
    public static File getFileInterno(){
        return new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"");
    }
    public static File getFileInterno(String direccion){
        if (direccion.trim().isEmpty()){
            return null;
 }
File f=new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/" + direccion);

   return f;
    }

    public static void recorrerAssets(Context context, String carpeta, UtilizarConException<String> c) throws Exception {
        if (esCarpetaAssets(carpeta)) {
            String F[] = context.getAssets().list(carpeta);
            for (int i = 0; i < F.length; i++) {
                recorrerAssets(context,carpeta+"/"+F[i], c);
            }
        } else {
//            System.out.println("realiza");
            c.utilizar(carpeta);
        }
    }
    public static boolean esCarpetaAssets(String nombreCarpeta){
        return !nombreCarpeta.contains(".");
    }
    public static String getParentAssets(String carpeta){
        // System.out.println("carpeta="+carpeta);
        int indice=contieneStringContrario(carpeta,"/");
        // System.out.println("indice="+indice);
        return indice==-1?carpeta:carpeta.substring(0,indice);
    }
    public static String getDireccionRelativaAssets(String carpeta, String archivoInterno) {

        String patchInterno = archivoInterno, patchCarpeta = getParentAssets(carpeta);
        if (carpeta==null||isEmptyOR(patchInterno, patchCarpeta) || patchCarpeta.length() + 2 > patchInterno.length()) {
            return "";
        }
        return patchInterno.substring(patchCarpeta.length() + 1);
    }
    public static void copiarCarpetasDesdeAssetYAplastarSiExistenDiferentes(final Context context, final String carpeta, final File destino) throws  Exception {
        if (existenYSonDirectory(destino)&&esCarpetaAssets(carpeta)) {
            recorrerAssets(context,carpeta, new UtilizarConException<String>() {
                @Override
                public void utilizar(String f) throws Exception {

                    //File nuevaDireccion = new File(destino+"/" + getDireccionRelativaAssets(carpeta, f));
                    File nuevaDireccion = new File(destino+"/" + f);
                    // System.out.println("va por f="+f);
                    if (nuevaDireccion.exists()) {
                        // System.out.println("existe esto "+nuevaDireccion.getAbsolutePath());
                        // if (nuevaDireccion.length() == context.getAssets().open(f).) {
                        return;
                        //  }
                        //  nuevaDireccion.delete();
                    }else{
                        File parent=nuevaDireccion.getParentFile();
                        if(parent!=null){
                            parent.mkdirs();
                        }

                    }
                    // System.out.println("nuevaDireccion="+nuevaDireccion.getAbsolutePath());
                    copiar(context.getAssets().open(f), nuevaDireccion);
                }
            });


        }else{
            System.out.println("salida: no creo nada");
        }
    }

    public static File copiarDesdeAssetsDeSerNecesario(Context context,String direccionAssets,File CarpetaDestino)throws Exception{
        File nuevaDireccion = new File(CarpetaDestino+"/" +direccionAssets);
        if (!nuevaDireccion.exists()) {
            File parent=nuevaDireccion.getParentFile();
            if(parent!=null){
                parent.mkdirs();
            }
            copiar(context.getAssets().open(direccionAssets), nuevaDireccion);
        }
                return nuevaDireccion;
    }
    public static File copiarDesdeAssetsDeSerNecesario(Context context,String direccionAssets,String nuevoNombre,File CarpetaDestino)throws Exception{
        File nuevaDireccion = new File(CarpetaDestino+"/" +nuevoNombre);
        File parent=nuevaDireccion.getParentFile();
        if(parent!=null){
            parent.mkdirs();
            nuevaDireccion = new File(parent+"/" +nuevoNombre);
        }


        if (!nuevaDireccion.exists()) {
//            log("--------------------------------------");
//            String S[]=context.getAssets().list("BD");
//            for (int i = 0; i <S.length ; i++) {
//                log("S="+S[i]+" i="+i);
//            }
            copiar(context.getAssets().open(direccionAssets), nuevaDireccion);//No la encuentra
        }
        return nuevaDireccion;
    }
    public static File copiarDesdeAssets(Context context,String direccionAssets,String nuevoNombre,File CarpetaDestino)throws Exception{

        if(!CarpetaDestino.exists()){
            //log("va a crear la carpeta destino="+CarpetaDestino);
            //CarpetaDestino.mkdirs();
            CarpetaDestino=ArchivoAndroid.crearCarpetaInterna(CarpetaDestino+"");
        }

        File nuevaDireccion = new File(CarpetaDestino+"/" +nuevoNombre);
        File parent=nuevaDireccion.getParentFile();
        if(parent!=null){
            //log("va a crear al padre");
            parent.mkdirs();
            nuevaDireccion = new File(parent+"/" +nuevoNombre);
            //log("nuevaDireccion00="+nuevaDireccion+"");
        }


        if (nuevaDireccion.exists()) {
nuevaDireccion.delete();

        }
        //log("nuevaDireccion="+nuevaDireccion+"");
        copiar(context.getAssets().open(direccionAssets), nuevaDireccion);
        return nuevaDireccion;
    }


    public static void log(Object o){
        try {
            ArchivoAndroid.escribirLogInterno("LeyenApps/Logs", "logs", o.toString());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public static void vaciarLogYErrors(){
        vaciarLog();
        vaciarErrors();
    }
    public static void vaciarLog(){
        File f=getFileInterno("LeyenApps/Logs/logs.txt");
        if(f.exists()){
            f.delete();
        }

    }
    public static void vaciarErrors(){
        File f=getFileInterno("LeyenApps/Errors/LogError.txt");
        if(f.exists()){
            f.delete();
        }
    }
    public static Uri getUriRaw(ContextWrapper c,int idRaw){

        return Uri.parse("android.resource://"+c.getPackageName()+"/"+idRaw);
    }


    public static String[] leerDeAssets(Context c,String dire) throws IOException {
       return leer(c.getAssets().open(dire));
      }


    public static String[] leer(InputStream in){
        LinkedList<String> lineas = new LinkedList<String>();
        Scanner e = new Scanner(new InputStreamReader(in));
        int cant = 0;
        while (e.hasNextLine()) {
            lineas.add(e.nextLine());
        }

        return lineas.toArray(new String[]{});
    }

    public static void recorrerSoloArchivosExternosEnCarpeta_Interno(File f, UtilizarConException<File> c) throws Exception {
        f=getFileInterno(f+"");
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

}
