package com.rene.android.reneandroid.Clases;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.hardware.SensorEventListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.AnimRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.rene.android.reneandroid.Actividades.BuscadorActividad;
import com.rene.android.reneandroid.Actividades.Logica.EABuscador;
import com.rene.android.reneandroid.ArchivoAndroid;
import com.rene.android.reneandroid.Clases.Adaptadores.ListaChecableAdaptador2;
import com.rene.android.reneandroid.Clases.Apoyo.EquipoDeLocalizacion;
import com.rene.android.reneandroid.Clases.Apoyo.EquipoDeMenuPrincipal;
import com.rene.android.reneandroid.Clases.Apoyo.EquipoDeTomarFotoCamara;
import com.rene.android.reneandroid.Clases.Apoyo.ListaAdaptable;
import com.rene.android.reneandroid.Clases.Apoyo.ListaSeleccionable;
import com.rene.android.reneandroid.Clases.Dialogos.DialogoAceptarCancelarInputLine;
import com.rene.android.reneandroid.Clases.Dialogos.DialogoFragment;
import com.rene.android.reneandroid.Clases.TabsFragments.TabContent;
import com.rene.android.reneandroid.Clases.Tipos.TipoDeControlDeVolumen;
import com.rene.android.reneandroid.Clases.Tipos.TipoDePermiso;
import com.rene.android.reneandroid.Clases.Tipos.TipoDeUnidadDeTipoDeLetra;
import com.rene.android.reneandroid.R;
import com.rene.android.reneandroid.UtilAndroid;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Callback;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Creador;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Realizar;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar2;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar3;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar3ConException;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Procedimento.ProcedimientoUtilizarConExecption;
import com.rene.android.reneandroid.Utiles.MetodosUtiles.MetodosUtiles;
import com.rene.android.reneandroid.VisualAndroid;

import org.joda.time.DateTime;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Rene on 21/3/2020.
 */

public class Actividad extends AppCompatActivity {
    private final static int REQUEST_CODE_TOMAR_FOTO_GALERIA=1,REQUEST_CODE_TOMAR_FOTO_CAMARA=2;
    private final static String SHARE_PREFERENS_KEY="dataActividad";
    private Utilizar2<Intent,Uri> accionTomarFotoGaleria;//accionTomarFotoCamara
    private LinkedList<ProcedimientoUtilizarConExecption<Actividad>> accionesResumePause;
    private ProgressDialog progressDialog;
    private EquipoDeTomarFotoCamara equipoDeTomarFotoCamara;
    private EquipoDeMenuPrincipal equipoDeMenuPrincipal;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // System.out.println("salida: accionObtenerFileDelBuscador="+ EABuscador.accionObtenerFileDelBuscador);
        if(EABuscador.accionObtenerFileDelBuscador!=null&&!(this instanceof BuscadorActividad)){
            //String respuestaBuscador=getIntent().getStringExtra(BuscadorActividad.BUSCADOR_SELECCIONADO);
            String respuestaBuscador=getIntent().getStringExtra("sele");
           // System.out.println("salida: respuestaBuscador="+respuestaBuscador);
            if(respuestaBuscador!=null&&!respuestaBuscador.isEmpty()){
                EABuscador.accionObtenerFileDelBuscador.utilizar(new File(respuestaBuscador));
            }
            EABuscador.accionObtenerFileDelBuscador=null;
            //ea.setAccionObtenerFileDelBuscador(null);
        }

    }

    public void addResumeYPause(ProcedimientoUtilizarConExecption<Actividad> p){
        if(accionesResumePause==null){
            accionesResumePause=new LinkedList<>();
        }
        accionesResumePause.add(p);
    }
    public ProcedimientoUtilizarConExecption<Actividad> removeResumeYPause(int i){
        ProcedimientoUtilizarConExecption<Actividad> p=accionesResumePause.remove(i);
        if (accionesResumePause.isEmpty()){
            accionesResumePause=null;
        }
     return   p;
    }
    public void removeResumeYPause(ProcedimientoUtilizarConExecption<Actividad> p){
        accionesResumePause.remove(p);
        if (accionesResumePause.isEmpty()){
            accionesResumePause=null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (accionesResumePause!=null&&!accionesResumePause.isEmpty()){
            for (int i = 0; i <accionesResumePause.size() ; i++) {
                accionesResumePause.get(i).getAccionPrimera().utilizar(this);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (accionesResumePause!=null&&!accionesResumePause.isEmpty()){
            for (int i = 0; i <accionesResumePause.size() ; i++) {
                accionesResumePause.get(i).getAccionSegunda().utilizar(this);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==REQUEST_CODE_TOMAR_FOTO_GALERIA&&accionTomarFotoGaleria!=null){
                accionTomarFotoGaleria.utilizar(data,data.getData());
                accionTomarFotoGaleria=null;
            }

            if(requestCode==REQUEST_CODE_TOMAR_FOTO_CAMARA&&equipoDeTomarFotoCamara!=null){
                equipoDeTomarFotoCamara.accionDespuesDeTomarLaFoto();
                equipoDeTomarFotoCamara=null;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (equipoDeMenuPrincipal==null){

            return super.onCreateOptionsMenu(menu);
        }else {
          return equipoDeMenuPrincipal.onCreateOptionsMenu(this,menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (equipoDeMenuPrincipal!=null){
        equipoDeMenuPrincipal.onOptionsItemSelected(this,item);

        }
       return super.onOptionsItemSelected(item);
    }
    public void toast(Object o){
        VisualAndroid.toast(this,o);
    }
    public static int inT(String a){
        return MetodosUtiles.inT(a);
    }
    public static int inT(EditText a){
        return VisualAndroid.inT(a);
    }
    public static boolean isEmptyOR(String... A){
        return MetodosUtiles.isEmptyOR(A);
    }
    public static boolean isEmptyOR(EditText... A){
        return VisualAndroid.isEmptyOR(A);
    }
    public static boolean isEmptyOR(TextView... A){
        return VisualAndroid.isEmptyOR(A);
    }
    public static boolean esNumeroAll(EditText ... T){
        return VisualAndroid.esNumeroAll(T);
    }
    public static boolean esNumeroAll(String... A) {
        return MetodosUtiles.esNumeroAll(A);
    }
    public  void showDialogInformacion( String mensage){
        VisualAndroid.showDialogAceptarInformacion(this,mensage);
    }
    public  void showDialogAceptar(@LayoutRes    int idLayout){
        VisualAndroid.showDialogAceptar(idLayout,this);
    }
    public  void showDialogAceptar_AccionPrepararVista(@LayoutRes    int idLayout, Utilizar2<DialogFragment,View> preparVista){
        VisualAndroid.showDialogAceptar_AccionPrepararVista(idLayout,this,preparVista);

    }
    public  void irActivity( Class<?> cls){
        VisualAndroid.irActivity(this,cls);
    }
    public static void setVisible(Menu menu, boolean visible, int ... IDs){
       VisualAndroid.setVisible(menu,visible,IDs);
    }
    public  void showDialogAdvertencia( String mensage){
        VisualAndroid.showDialogAceptarAdvertencia(this,mensage);
    }
    public  void showDialogAdvertenciaAceptarCancelar( String mensage,Utilizar2<DialogFragment,View> accionAceptar){
        VisualAndroid.showDialogAceptarCancelarAdvertencia(this,mensage,accionAceptar);
    }
    public  void showDialogAceptarCancelar( @LayoutRes    int idLayout,Utilizar2<DialogFragment,View> preparVista, Utilizar2<DialogFragment,View> accionAceptar){
        DialogoFragment d=new DialogoFragment();
        d.inicializarAceptarCancelar(idLayout,preparVista,accionAceptar );
        d.show(this.getSupportFragmentManager(), "NoticeDialogFragment");
    }
    public static boolean isEmpty(TextView t){
        return VisualAndroid.isEmpty(t);
    }
    public static boolean isEmpty(EditText t){
        return VisualAndroid.isEmpty(t);
    }
    public  void setVisible(@IdRes int id,boolean visible){
        setVisible(findViewById(id),visible);
    }
    public static void setVisible(View view,boolean visible){
        VisualAndroid.setVisible(view,visible);
    }
    public  void loadAssetHtml(@IdRes int id, String nombre){

        VisualAndroid.loadAssetHtml((WebView)findViewById(id),nombre);
    }
    public  void startAnimation( @IdRes int id, @AnimRes int idAnimacion){
       VisualAndroid.startAnimation(this,id,idAnimacion);
    }
    public  void showDialogSalir(){
        VisualAndroid.showDialogSalir(this);
    }
    public  void setTitle( String title){
        VisualAndroid.setTitle(this,title);
    }
    public static void setVisible(boolean visible,View ...V){
        VisualAndroid.setVisible(visible,V);
    }
    public  void pedirPermiso( TipoDePermiso... T){
        VisualAndroid.pedirPermiso(this,T);

    }
    public  void setHorizontal(){
       setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public  void setVertical(){
      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public  void setRotativa(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }
    public  <E> ArrayAdapter<E> getArrayAdapterLista( E e[]){

      return VisualAndroid.getArrayAdapterLista(this,e);
    }
    public  boolean setSensorEventListenerACCELEROMETER( SensorEventListener s){
        return VisualAndroid.setSensorEventListenerACCELEROMETER(this,s);
    }
    public  void setControlDeAudio( TipoDeControlDeVolumen c){
        VisualAndroid.setControlDeAudio(this,c);
    }

    public  boolean estaVertical(ContextThemeWrapper contextThemeWrapper){
        //getResources().getConfiguration()
        return VisualAndroid.estaVertical(this);
    }

    public void tomarFotoDeGaleria(Utilizar2<Intent,Uri> u){
        Intent in=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        accionTomarFotoGaleria=u;
        startActivityForResult(in,REQUEST_CODE_TOMAR_FOTO_GALERIA);
    }
    public  void setAdapterListView( @IdRes int id, LinkedList<String> l){
        VisualAndroid.setAdapterListView(this,id,l);
    }
    public <E> void setAdapterListView( @IdRes int id, E e[]){
        VisualAndroid.setAdapterListView(this,id,e);
    }
    public <E> void setAdapterListViewAzul( @IdRes int id, E e[]){
        VisualAndroid.setAdapterListViewAzul(this,id,e);
    }
    public <E> Spinner setAdapterSpinner( @IdRes int id, E e[], final Utilizar2<View,Integer> alSeleccionar){
        return VisualAndroid.setAdapterSpinner(this,id,e,alSeleccionar);
    }
    public <E> Spinner setAdapterSpinner(@IdRes int id, E e[]){
       return VisualAndroid.setAdapterSpinner(this,id,e);
    }
    public <E> void setAdapterAutoComplete( @IdRes int id, E e[]){
         VisualAndroid.setAdapterAutoComplete(this,id,e);
    }
    public void addOnLocationChange(EquipoDeLocalizacion e){
        addResumeYPause(e.getResumePausa());
    }
    public void removeOnLocationChange(EquipoDeLocalizacion e){
        removeResumeYPause(e.getResumePausa());
    }
    public  void appendLnTextView(@IdRes int id,String text){
        VisualAndroid.appendLn((TextView)findViewById(id),text);
    }
    public static void appendLn(TextView t,String text){
        VisualAndroid.appendLn(t,text);
    }

    public  ProgressDialog showProgressDialog(String mensaje){//"Descargando.."
        return (progressDialog=ProgressDialog.show(this,"Espere",mensaje,false));
    }
    public  ProgressDialog showProgressDialog(String mensaje,Realizar subProceso){
        ProgressDialog pd=   showProgressDialog(mensaje);
        subProceso(subProceso, new Realizar() {
            @Override
            public void realizar() {
                hideProgressDialog();
            }
        });
        return pd;
    }
    public  ProgressDialog showProgressDialog(String mensaje,Realizar subProceso,final Realizar alTerminarVisual){
        ProgressDialog pd=   showProgressDialog(mensaje);
        subProceso(subProceso, new Realizar() {
            @Override
            public void realizar() {
                alTerminarVisual.realizar(); hideProgressDialog();
            }
        });
        return pd;
    }
    public void hideProgressDialog(){
        if(progressDialog!=null){
            progressDialog.dismiss();
            progressDialog=null;
        }

    }
    public static int getVersionSDK(){
       return VisualAndroid.getVersionSDK();
    }
    public  <E extends RetainedFragment> E setEA( Creador<E> c){
        return UtilAndroid.getRetainedFragment(this,c);
    }
    private  <E extends RetainedFragment> E setEA( String tag,Creador<E> c){
        return UtilAndroid.getRetainedFragment(this,tag,c);
    }
    public void tomarFotoCamara(Utilizar<Uri> accionTomarFotoCamara ){
        equipoDeTomarFotoCamara=new EquipoDeTomarFotoCamara(accionTomarFotoCamara);
        equipoDeTomarFotoCamara.tomarFoto(this,REQUEST_CODE_TOMAR_FOTO_CAMARA);

    }
    public SharedPreferences getSharedPreferences(){
        return getSharedPreferences(SHARE_PREFERENS_KEY);
    }
    public SharedPreferences getSharedPreferences(String key){
        return getSharedPreferences(key, Context.MODE_PRIVATE);
    }
    public String getStringSP(String key){
        return getSharedPreferences().getString(key,"");
    }
    public String getStringSP(String key,String porSiHayNull){
        return getSharedPreferences().getString(key,porSiHayNull);
    }
    public int getIntSP(String key){
        return getSharedPreferences().getInt(key,-1);
    }
    public int getIntSP(String key,int porDefault){
        return getSharedPreferences().getInt(key,porDefault);
    }
    public double getDoubSP(String key){
        return getSharedPreferences().getFloat(key,-1);
    }
    public double getDoubSP(String key,double defaul){
        return getSharedPreferences().getFloat(key,(float) defaul);
    }
    public boolean getBoolSP(String key){
        return getSharedPreferences().getBoolean(key,false);
    }
    public boolean getBoolSP(String key,boolean porDefecto){
        return getSharedPreferences().getBoolean(key,porDefecto);
    }

    public void putSP(String key,String data){
         getSharedPreferences().edit().putString(key,data).commit();
    }
    public void putSP(String key,int data){
        getSharedPreferences().edit().putInt(key,data).commit();
    }
    public void putSP(String key,double data){
        getSharedPreferences().edit().putFloat(key,(float) data).commit();
    }
    public void putSP(String key,boolean data){
        getSharedPreferences().edit().putBoolean(key, data).commit();
    }
    public  void vibrar(){
        VisualAndroid.vibrar(this);
    }
    public  void sonidoCorto(Activity a, @RawRes int id){
       VisualAndroid.sonidoCorto(this,id);
    }
    public  void sonido(Activity a,@RawRes int id){
        VisualAndroid.sonido(this,id);
    }
    public  void sonidoPresionar(){
        VisualAndroid.sonidoPresionar(this);
    }

    public void setMenu(@MenuRes int menuRes, Utilizar2<Actividad,MenuItem> accionSeleccionarMI){
        setMenu(new EquipoDeMenuPrincipal(menuRes,accionSeleccionarMI));
    }
    public void setMenu(@MenuRes int menuRes,int[] IDs_MI_A_Invisible, Utilizar2<Actividad,MenuItem> accionSeleccionarMI){
        setMenu(new EquipoDeMenuPrincipal(menuRes,IDs_MI_A_Invisible,accionSeleccionarMI));
    }
    public void setMenu(EquipoDeMenuPrincipal e){
        equipoDeMenuPrincipal=e;
    }
    public  void responderException( Exception ex){
        VisualAndroid.responderException(this,ex);
        //responderExcepionLog(ex);
//
//        try{
//            class auxiliar{
//                Exception ex;
//
//                public auxiliar(Exception ex) {
//                    this.ex = ex;
//                }
//
//                private  File getFileInterno(String direccion){
//                    if (direccion.trim().isEmpty()){
//                        return null;
//                    }
//                    File f=new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/" + direccion);
//
//                    return f;
//                }
//
//                public  File escribirLogInterno(String carpeta,String nombre) throws IOException {
//                    File archivo=getFileInterno(carpeta+"/" +nombre+".txt");
//
//                    if (!archivo.exists()){
//                        //toast("paso por aqui");
//                        //crearArchivoTEXTOInterno(carpeta,nombre,".txt",null);
//                        crearArchivoTEXTOInterno(carpeta,nombre,".txt");
//                    }
//                    escribirEnArchivoTEXTO(archivo,true);
//
//                    return archivo;
//                }
//                private  File crearArchivoTEXTOInterno(String carpeta,String nombre,String extencion) throws IOException {
//                    File fcarpeta=crearCarpetaInterna(carpeta);
//                    File archivo=new File(fcarpeta+File.separator +nombre+extencion);
//                    //System.out.println("salida: crear archivo interno existe car="+fcarpeta.exists()+" archivo="+archivo);
//                    escribirEnArchivoTEXTO(archivo,false);
//                    return  archivo;
//                }
//                private  File crearCarpetaInterna(String direccionCarpeta){
//                    File f=getFileInterno(direccionCarpeta);
//                    //System.out.println("salida: crear carpeta f="+f);
//                    // if (!f.isDirectory()){
//                    //   System.out.println("salida: no creo la carpeta");
//                    // return null;
//                    //}
//
//                    f.mkdirs();
//                    //System.out.println("salida: la creo");
//                    return f;
//                }
//
//                private  void escribirEnArchivoTEXTO(File archivo,boolean aContinuacion) throws IOException {
//                    if (!archivo.exists()){
//                        PrintWriter pp= new PrintWriter(archivo);
//                        pp.close();
//                        //archivo.createNewFile();
//                    }
//                    PrintWriter p=new PrintWriter(new FileWriter(archivo,aContinuacion));
////                    if(contenido!=null){
////                        for (int i = 0; i <contenido.length ; i++) {
////                            p.println(contenido[i]);
////                        }
////                    }
//
//
//                    ex.printStackTrace(p); //Novedoso
//
//
//                    p.close();
//                }
//
//            }
//
//            new auxiliar(ex).escribirLogInterno("LeyenApps/Errors","LogError");
//        }catch (Exception exx){}
    }

    public void responderExcepionLog( Exception ex){
        try{
            class auxiliar{
                Exception ex;

                public auxiliar(Exception ex) {
                    this.ex = ex;
                }

                private  File getFileInterno(String direccion){
                    if (direccion.trim().isEmpty()){
                        return null;
                    }
                    File f=new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/" + direccion);

                    return f;
                }

                public  File escribirLogInterno(String carpeta,String nombre) throws IOException {
                    File archivo=getFileInterno(carpeta+"/" +nombre+".txt");

                    if (!archivo.exists()){
                        //toast("paso por aqui");
                        //crearArchivoTEXTOInterno(carpeta,nombre,".txt",null);
                        crearArchivoTEXTOInterno(carpeta,nombre,".txt");
                    }
                    escribirEnArchivoTEXTO(archivo,true);

                    return archivo;
                }
                private  File crearArchivoTEXTOInterno(String carpeta,String nombre,String extencion) throws IOException {
                    File fcarpeta=crearCarpetaInterna(carpeta);
                    File archivo=new File(fcarpeta+File.separator +nombre+extencion);
                    //System.out.println("salida: crear archivo interno existe car="+fcarpeta.exists()+" archivo="+archivo);
                    escribirEnArchivoTEXTO(archivo,false);
                    return  archivo;
                }
                private  File crearCarpetaInterna(String direccionCarpeta){
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

                private  void escribirEnArchivoTEXTO(File archivo,boolean aContinuacion) throws IOException {
                    if (!archivo.exists()){
                        PrintWriter pp= new PrintWriter(archivo);
                        pp.close();
                        //archivo.createNewFile();
                    }
                    PrintWriter p=new PrintWriter(new FileWriter(archivo,aContinuacion));
//                    if(contenido!=null){
//                        for (int i = 0; i <contenido.length ; i++) {
//                            p.println(contenido[i]);
//                        }
//                    }
                    p.println();
                    p.println(new Date()+"------------------------------------------");
                    ex.printStackTrace(p); //Novedoso


                    p.close();
                }

            }

            new auxiliar(ex).escribirLogInterno("LeyenApps/Errors","LogError");
        }catch (Exception exx){}
    }


    public void enProcesoVisual(final Realizar accion){

     /*   new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                      synchronized (this){
                          accion.realizar();
                      }

                    }
                });

            }



        }).start(); */
        handler.post(new Runnable() {
            @Override
            public void run() {
                synchronized (this){
                    accion.realizar();
                }

            }
        });
    }
    public void subProceso(final Realizar accion){
        ExecutorService exx= Executors.newCachedThreadPool();
        exx.execute(new Runnable() {
            @Override
            public void run() {
                accion.realizar();
            }
        });
    }
    public void subProceso(final Realizar accion,final Realizar alTerminarVisual){
        ExecutorService exx= Executors.newCachedThreadPool();
        exx.execute(new Runnable() {
            @Override
            public void run() {
                if(accion!=null){
                    accion.realizar();
                }

                enProcesoVisual(alTerminarVisual);
            }
        });
    }

    public void subProcesosEnVisual(final Realizar ... acciones){
        for (int i = 0; i <acciones.length ; i++) {
            final int indice=i;
            subProceso(new Realizar() {
                @Override
                public void realizar() {
                    enProcesoVisual(new Realizar() {
                        @Override
                        public void realizar() {
                            acciones[indice].realizar();
                        }
                    });
                }
            });
        }

    }

    public <E> ListaChecableAdaptador2 setAdapterListViewChecable(@IdRes int id, E e[]){
        return VisualAndroid.setAdapterListViewChecable(this,id,e);
    }

    public void setFondoActionBar(@DrawableRes int id){
        VisualAndroid.setFondoActionBar(this,id);
    }
    public void setFondoActionBarNormal(){
        setFondoActionBar(R.drawable.fondo_action_bar);
    }
    public<E> ListaSeleccionable<E> setListaSeleccionable( @IdRes int idTableLayout, E[] objetos ){
        return new ListaSeleccionable<E>(this,idTableLayout,objetos);
    }
    public<E> ListaSeleccionable<E> setListaSeleccionable( @IdRes int idTableLayout, E[] objetos ,Utilizar3ConException<ListaSeleccionable,String,Integer> accionClick ){
        return new ListaSeleccionable<E>(this,idTableLayout,objetos,accionClick);
    }
    public<E> ListaSeleccionable<E> setListaSeleccionable( @IdRes int idTableLayout, E[] objetos ,boolean seleccionMultiple,Utilizar3ConException<ListaSeleccionable,String,Integer> accionClick ){
        return new ListaSeleccionable<E>(this,idTableLayout,objetos,seleccionMultiple,accionClick);
    }
    public static boolean esVisible(View view){
        return VisualAndroid.esVisible(view);
    }
    public static boolean existeSD(){return ArchivoAndroid.existeSD();}

    public DatePickerDialog showDialogDatePicker(DatePickerDialog.OnDateSetListener d){
        DatePickerDialog dp=new DatePickerDialog(this, d,  -1, -1, -1);
        dp.show();
      return  dp;

        //DatePickerDialog dp=  new DatePickerDialog(this);
        //dp.setOnDateSetListener(d);
        //dp.show();
     }
    public TimePickerDialog showDialogTimerPicker(TimePickerDialog.OnTimeSetListener d){
        TimePickerDialog tp=new TimePickerDialog(this,d,0,0,false);
        tp.show();
        return  tp;
    }

    /**
     * compatible con EditText, Autocomplete,TextView
     * @param id
     * @param u
     */
    public  void addOnTextChanged_TextView( @IdRes int id,final Utilizar<String> u){
        addOnTextChanged(((TextView)findViewById(id)),u);
    }

    /**
     * compatible con EditText, Autocomplete,TextView
     * @param tv
     * @param u
     */
    public static void addOnTextChanged(TextView tv,final Utilizar<String> u){
        VisualAndroid.addOnTextChanged(tv,u);

    }

    public void irABuscadorDirectoriosSimple(Utilizar<File> accionObtenerFileDelBuscador){
        setEA("eabuscador",new Creador<RetainedFragment>() {
            @Override
            public RetainedFragment crear() {
                return new EABuscador();
            }
        });
        //this.accionObtenerFileDelBuscador=accionObtenerFileDelBuscador;
        EABuscador.accionObtenerFileDelBuscador=accionObtenerFileDelBuscador;
        //System.out.println("salida: en el set ac="+EABuscador.accionObtenerFileDelBuscador);
        //System.out.println("salida: this="+this);
       final String clase=this.getClass().getName();
        //System.out.println("salida: clase="+clase);
        //System.out.println("salida: CanonicalName="+this.getClass().getCanonicalName());
        VisualAndroid.irActivity(this, BuscadorActividad.class, new Utilizar<Intent>() {
            @Override
            public void utilizar(Intent a) {

                a.putExtra(BuscadorActividad.BUSCADOR_ANTERIOR,clase);
            }
        });
    }
    public static double dou(EditText t){
        return VisualAndroid.dou(t);
    }
    public static double dou(String t){
        return MetodosUtiles.dou(t);
    }
    public  void iniTabLayout( @IdRes int idTabLayout, @IdRes int idViewPager, final String nombres[], final Callback<Integer,TabContent> creadorViews){
        VisualAndroid.iniTabLayout(this,idTabLayout,idViewPager,nombres,creadorViews);
    }
    public  void loadFile(@IdRes int idHTML,File f){
        loadFile(f,(WebView)findViewById(idHTML));
    }
    public static void loadFile(File f,WebView w){
        VisualAndroid.loadFile(f,w);
    }
    public static void loadHTML(WebView w,String html){
        VisualAndroid.loadHTML(w,html);
    }
    public static void loadFileInterno(String direccion,WebView w){
        VisualAndroid.loadFileInterno(direccion,w);
    }
    /*
    R.mipmap.NOMBRE_ICONO
     */
    public  void setIcono(int id){
        VisualAndroid.setIcono(this,id);
    }
    public <E> ListaAdaptable<E> setListaAdaptable(@IdRes int idTableLayoutOScrollView, @LayoutRes int idFila, E[] objetos, Utilizar3ConException<View,E,Integer> accionCrearView){
        return new ListaAdaptable<E>(this,idTableLayoutOScrollView,idFila,objetos,accionCrearView);
    }
    public <E> ListaAdaptable<E> setListaAdaptable(@IdRes int idTableLayoutOScrollView,@LayoutRes int idHeader, @LayoutRes int idFila, E[] objetos, Utilizar3ConException<View,E,Integer> accionCrearView){
        return new ListaAdaptable<E>(this,idTableLayoutOScrollView,idHeader,null,idFila,objetos,accionCrearView,null);
    }
    public static void setEnable(boolean enable,View ... V){
        VisualAndroid.setEnable(enable,V);
    }

    public  void ocultarBarraNavegacion(){
        VisualAndroid.ocultarBarraNavegacion(this);
    }

    public  DialogoAceptarCancelarInputLine showDialogoAceptarCancelarInputLine( String tituloActual,String hint, final Utilizar3<DialogoAceptarCancelarInputLine,View,String> accionAceptarConTexto){
        return VisualAndroid.showDialogoAceptarCancelarInputLine(this,tituloActual,hint,accionAceptarConTexto);
    }
    public  DialogoAceptarCancelarInputLine showDialogoAceptarCancelarInputLine(String tituloActual,String hint,final Utilizar2<DialogoAceptarCancelarInputLine,View> alCrearLaVista,final Utilizar3<DialogoAceptarCancelarInputLine,View,String> accionAceptarConTexto){
        return VisualAndroid.showDialogoAceptarCancelarInputLine(this,tituloActual,hint,alCrearLaVista,accionAceptarConTexto);

    }



    public DatePickerDialog showDialogDate_DateTime(DateTime dt,final Utilizar2<View,DateTime> alAceptar){
        DatePickerDialog dp=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                DateTime dtActual=new DateTime(year,month+1,dayOfMonth,0,0);
                alAceptar.utilizar(view,dtActual);

            }
        }, dt.year().get(), dt.monthOfYear().get()-1, dt.dayOfMonth().get());

        dp.show();
        return dp;
    }


    public static void log(Object o){
         ArchivoAndroid.log(o);
    }
    public static void vaciarLogYErrors(){
        ArchivoAndroid.vaciarLogYErrors();
    }
    public static void vaciarLog(){
        ArchivoAndroid.vaciarLog();

    }
    public static void vaciarErrors(){
        ArchivoAndroid.vaciarErrors();
    }

    public static void setTextSise(View tv,int tamaño){
        VisualAndroid.setTextSise(tv,tamaño);
    }
    public static void setTextSise(View tv,TipoDeUnidadDeTipoDeLetra t,int tamaño){
        VisualAndroid.setTextSise(tv,t,tamaño);
    }
    public  SeekBar setOnProgres( @IdRes int id, int progresDefault, final Utilizar2<SeekBar,Integer> u){
        return VisualAndroid.setOnProgres(this,id,progresDefault,u);
    }
}
