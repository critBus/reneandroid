package com.rene.android.reneandroid;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.support.annotation.AnimRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.RawRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.rene.android.reneandroid.Clases.Actividad;
import com.rene.android.reneandroid.Clases.Adaptadores.ListaChecableAdaptador2;
import com.rene.android.reneandroid.Clases.Dialogos.DialogoAceptar;
import com.rene.android.reneandroid.Clases.Dialogos.DialogoAceptarCancelarInputLine;
import com.rene.android.reneandroid.Clases.Dialogos.DialogoAdvertencia;
import com.rene.android.reneandroid.Clases.Dialogos.DialogoAdvertenciaAceptarCancelar;
import com.rene.android.reneandroid.Clases.Dialogos.DialogoFragment;
import com.rene.android.reneandroid.Clases.Dialogos.DialogoUnMensajeAceptar;
import com.rene.android.reneandroid.Clases.Exceptions.SDNoExisteException;
import com.rene.android.reneandroid.Clases.TabsFragments.TabContent;
import com.rene.android.reneandroid.Clases.Tipos.TipoDeControlDeVolumen;
import com.rene.android.reneandroid.Clases.Tipos.TipoDeMotionEvent;
import com.rene.android.reneandroid.Clases.Tipos.TipoDePermiso;
import com.rene.android.reneandroid.Clases.Tipos.TipoDeUnidadDeTipoDeLetra;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Callback;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar2;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar3;
import com.rene.android.reneandroid.Utiles.MetodosUtiles.Archivo;
import com.rene.android.reneandroid.Utiles.MetodosUtiles.MetodosUtiles;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Rene on 19/2/2020.
 */

public abstract class VisualAndroid {//finishAfterTransition();
    /*
    R.mipmap.NOMBRE_ICONO
     */
    public static void setIcono(AppCompatActivity a,int id){
        a.getSupportActionBar().setIcon(id);
        a.getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    public static void loadHTML(WebView w,String html){
        w.loadData(html, "text/html",  null);
    }
    public static void loadFileInterno(String direccion,WebView w){
        loadFile(ArchivoAndroid.getFileInterno(direccion),w);
    }
    public static void loadFile(File f,WebView w){
        w.loadUrl("file:///"+f.getAbsolutePath());
    }

    public static void iniTabLayout(FragmentActivity fa , @IdRes int idTabLayout, @IdRes int idViewPager,final String nombres[],final Callback<Integer,TabContent> creadorViews){
        class pageAdater extends FragmentPagerAdapter {
            public pageAdater(FragmentManager fr){
                super(fr);
            }
            @Override
            public Fragment getItem(int position) {
                return creadorViews.call(position).getTf();
            }

            @Override
            public int getCount() {
                return nombres.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return nombres[position];
            }
        }
        TabLayout tab=(TabLayout)fa.findViewById(idTabLayout);
        // Log.v("Mensaje"," 2");
        ViewPager vie=(ViewPager)fa.findViewById(idViewPager);
        // Log.v("Mensaje"," 3");
        vie.setAdapter(new pageAdater(fa.getSupportFragmentManager()));
        //  Log.v("Mensaje"," 4");
        tab.setupWithViewPager(vie);
    }

//    public static void iniTabLayout(FragmentActivity fa , @IdRes int idTabLayout, @IdRes int idViewPager,final String nombres[],final Callback<Integer,Fragment> creadorViews){
//        class pageAdater extends FragmentPagerAdapter {
//            public pageAdater(FragmentManager fr){
//                super(fr);
//            }
//            @Override
//            public Fragment getItem(int position) {
//               return creadorViews.call(position);
//            }
//
//            @Override
//            public int getCount() {
//                return nombres.length;
//            }
//
//            @Override
//            public CharSequence getPageTitle(int position) {
//                return nombres[position];
//            }
//        }
//        TabLayout tab=(TabLayout)fa.findViewById(idTabLayout);
//        // Log.v("Mensaje"," 2");
//        ViewPager vie=(ViewPager)fa.findViewById(idViewPager);
//        // Log.v("Mensaje"," 3");
//        vie.setAdapter(new pageAdater(fa.getSupportFragmentManager()));
//        //  Log.v("Mensaje"," 4");
//        tab.setupWithViewPager(vie);
//    }

    public static void addOnTextChanged(TextView tv,final Utilizar<String> u){
        tv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                u.utilizar(s.toString());
            }
        });

    }
    public static void setFondoActionBar(AppCompatActivity a,@DrawableRes int id){
        Drawable d= a.getDrawable(id);
        a.getSupportActionBar().setBackgroundDrawable(d);
    }

    public static void setTitle(AppCompatActivity act, String title){
        act.getSupportActionBar().setTitle(title);
    }
    public static boolean esVisible(View view){
        return view.getVisibility()==View.VISIBLE;
    }
    public static void setVisible(View view,boolean visible){
         view.setVisibility(visible?View.VISIBLE:View.INVISIBLE);
    }
    public static void setVisibleSinEspacion(View view,boolean visible){
        view.setVisibility(visible?View.VISIBLE:View.GONE);
    }
    public static void setVisible(boolean visible,View ...V){
        for (int i = 0; i <V.length ; i++) {
            setVisible(V[i],visible);
        }
    }

    public static boolean isEmptyOR(EditText... A){
        for (int i = 0; i <A.length ; i++) {
            if (isEmpty(A[i])){
                return true;
            }
        }
        return false;
    }
    public static boolean isEmptyOR(TextView... A){
        for (int i = 0; i <A.length ; i++) {
            if (isEmpty(A[i])){
                return true;
            }
        }
        return false;
    }
    public static boolean isEmpty(TextView t){
        return t.getText().toString().isEmpty();
    }
    public static boolean isEmpty(EditText t){
        return t.getText().toString().isEmpty();
    }
    public static void showDialogListaCancelar(FragmentActivity a, String titulo,String lista[], final Utilizar2<DialogInterface,Integer> accionAceptar,
                                               final Utilizar<DialogInterface> accionCancelar){
        new AlertDialog.Builder(a).setIcon(R.drawable.informacion1)
                .setTitle(titulo)//.setMessage(mensage)
                .setItems(lista,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                accionAceptar.utilizar(dialog,which);
                            }
                        })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        accionCancelar.utilizar(dialog);
                    }
                }).show();
    }
    public static void showDialogListaChecableAceptarCancelar(FragmentActivity a, String titulo,String lista[], final Utilizar2<DialogInterface,boolean[]> accionAceptar,
                                               final Utilizar<DialogInterface> accionCancelar){
      final  boolean seleccionadosDefault[]=new boolean[lista.length];
        Arrays.fill(seleccionadosDefault,false);
        new AlertDialog.Builder(a).setIcon(R.drawable.informacion1)
                .setTitle(titulo)//.setMessage(mensage)
                .setMultiChoiceItems(lista, seleccionadosDefault, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                seleccionadosDefault[which]=isChecked;
                            }
                        }
                )
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        accionCancelar.utilizar(dialog);
                    }
                }).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                accionAceptar.utilizar(dialog,seleccionadosDefault);
            }
        }).show();
    }
    public static void showDialogConfiguracionChecableAceptarCancelar(FragmentActivity a,String lista[],final  boolean seleccionadosDefault[], final Utilizar2<DialogInterface,boolean[]> accionAceptar
                                                              ){
       // final  boolean seleccionadosDefault[]=new boolean[lista.length];
        //Arrays.fill(seleccionadosDefault,false);
        new AlertDialog.Builder(a).setIcon(android.R.drawable.ic_menu_preferences)
                .setTitle("Configuracion")//.setMessage(mensage)
                .setMultiChoiceItems(lista, seleccionadosDefault, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                seleccionadosDefault[which]=isChecked;
                            }
                        }
                )
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //accionCancelar.utilizar(dialog);
                    }
                }).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                accionAceptar.utilizar(dialog,seleccionadosDefault);
            }
        }).setInverseBackgroundForced(true)
                .show();
    }
    public static void showDialogAceptarInformacionDefault(FragmentActivity a, String mensage, final Utilizar<DialogInterface> accionAceptar){
        new AlertDialog.Builder(a).setIcon(R.drawable.informacion1)
                .setTitle("Informacion").setMessage(mensage)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        accionAceptar.utilizar(dialog);
                    }
                }).show();

    }
    public static void showDialogAceptarAdvertenciaDefault(Context a, String titulo,String mensage ){
        new AlertDialog.Builder(a).setIcon(R.drawable.advertencia1)
                .setTitle(titulo).setMessage(mensage)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }
    public static void showDialogAceptarAdvertenciaDefault(FragmentActivity a, String mensage, final Utilizar<DialogInterface> accionAceptar){
        new AlertDialog.Builder(a).setIcon(R.drawable.advertencia1)
                .setTitle("Advertencia").setMessage(mensage)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        accionAceptar.utilizar(dialog);
                    }
                }).show();

    }
    public static void showDialogAceptarCancelarAdvertenciaDefault(FragmentActivity a, String mensage, final Utilizar<DialogInterface> accionAceptar
            , final Utilizar<DialogInterface> accionCancelar){
        new AlertDialog.Builder(a).setIcon(R.drawable.advertencia1)
                .setTitle("Advertencia").setMessage(mensage)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        accionAceptar.utilizar(dialog);
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                accionCancelar.utilizar(dialog);
            }
        }).show();

    }

    public static void showDialogAceptarInformacion(FragmentActivity a, String mensage){
        showDialogAceptarInformacion(a,mensage,null);
    }
    public static void showDialogAceptarInformacion(FragmentActivity a, String mensage, Utilizar2<DialogFragment,View> accionAceptar){
        showDialogoUnMensajeAceptar(new DialogoAceptar(),a,mensage,accionAceptar);
     }
    public static void showDialogAceptar(@LayoutRes    int idLayout,FragmentActivity a){
        showDialogAceptar(idLayout,a,null,null);
    }
    public static void showDialogAceptar_AccionPrepararVista(@LayoutRes    int idLayout,FragmentActivity a,Utilizar2<DialogFragment,View> preparVista){
        showDialogAceptar(idLayout,a,preparVista,null);
    }
    private static void showDialogAceptar(@LayoutRes    int idLayout,FragmentActivity a,Utilizar2<DialogFragment,View> preparVista, Utilizar2<DialogFragment,View> accionAceptar){
        DialogoFragment dl=new DialogoFragment();
        if(preparVista!=null){
            dl.setPreparVista(preparVista);
        }
        dl.inicializarAceptar(idLayout,accionAceptar).show(a.getSupportFragmentManager(), "NoticeDialogFragment");
    }



    public static void showDialogAceptarAdvertencia(FragmentActivity a, String mensage){
        showDialogAceptarAdvertencia(a,mensage,null);
    }
    public static void showDialogAceptarAdvertencia(FragmentActivity a, String mensage, Utilizar2<DialogFragment,View> accionAceptar){
        showDialogoUnMensajeAceptar(new DialogoAdvertencia(),a,mensage,accionAceptar);

    }
    public static void showDialogAceptarCancelarAdvertencia(FragmentActivity a, String mensage, Utilizar2<DialogFragment,View> accionAceptar){
        showDialogAceptarCancelarAdvertencia(a,mensage,accionAceptar,null);
    }
    public static void showDialogAceptarCancelarAdvertencia(FragmentActivity a, String mensage, Utilizar2<DialogFragment,View> accionAceptar, Utilizar2<DialogFragment,View> accionCancelar){
        DialogoAdvertenciaAceptarCancelar dialog=new DialogoAdvertenciaAceptarCancelar();
        dialog.inicializarAceptarCancelar(mensage,accionAceptar,accionCancelar);
        dialog.show(a.getSupportFragmentManager(), "NoticeDialogFragment");

    }
    public static  void showDialogAceptarCancelar(FragmentActivity a, @LayoutRes    int idLayout,Utilizar2<DialogFragment,View> preparVista, Utilizar2<DialogFragment,View> accionAceptar){
        DialogoFragment d=new DialogoFragment();
        d.inicializarAceptarCancelar(idLayout,preparVista,accionAceptar );
        d.show(a.getSupportFragmentManager(), "NoticeDialogFragment");
    }
    private static void showDialogoUnMensajeAceptar(DialogoUnMensajeAceptar dialog, FragmentActivity a, String mensage, Utilizar2<DialogFragment,View> accionAceptar){
        dialog.inicializarAceptar(mensage,accionAceptar);
        dialog.show(a.getSupportFragmentManager(), "NoticeDialogFragment");
       /* new AlertDialog.Builder(a).setMessage(mensage).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();*/
    }
    public static DialogoAceptarCancelarInputLine showDialogoAceptarCancelarInputLine(FragmentActivity a,String tituloActual,String hint,final Utilizar3<DialogoAceptarCancelarInputLine,View,String> accionAceptarConTexto){
        return showDialogoAceptarCancelarInputLine(a,tituloActual,hint,null,accionAceptarConTexto);
    }
    public static DialogoAceptarCancelarInputLine showDialogoAceptarCancelarInputLine(FragmentActivity a,String tituloActual,String hint,final Utilizar2<DialogoAceptarCancelarInputLine,View> alCrearLaVista,final Utilizar3<DialogoAceptarCancelarInputLine,View,String> accionAceptarConTexto){
        DialogoAceptarCancelarInputLine dialog=new DialogoAceptarCancelarInputLine();
        dialog.inicializarAceptarCancelar(tituloActual,hint,alCrearLaVista,accionAceptarConTexto);
        dialog.show(a.getSupportFragmentManager(), "NoticeDialogFragment");
        return dialog;
    }
    public static void showDialogSalir(final FragmentActivity a){
        showDialogAceptarCancelarAdvertencia(a, "Desea salir de la app", new Utilizar2<DialogFragment,View>() {
            @Override
            public void utilizar(DialogFragment df,View v) {
                a.finishAffinity();
                System.exit(0);
            }
        }, null);
    }
    public static boolean esNumeroAll(EditText ... T){
        for (EditText t:T) {
           if(!MetodosUtiles.esNumeroAll(t.getText().toString())){
              return false;
           }
        }
        return true;
    }
    public static void startAnimation(Context context, @IdRes int id, @AnimRes int idAnimacion){
        startAnimation(context,id,idAnimacion,false);
    }
    public static void startAnimation(Context context, @IdRes int id, @AnimRes int idAnimacion,boolean quedarseAlTerminar){
       // Animation rotacion= AnimationUtils.loadAnimation(context,idAnimacion);
        //ImageButton BComenzar=(ImageButton)((Activity)context).findViewById(id);
      // BComenzar.startAnimation(rotacion);
        Animation an=AnimationUtils.loadAnimation(context,idAnimacion);
        if(quedarseAlTerminar){
            an.setFillAfter(quedarseAlTerminar);
        }

        ((View)((Activity)context).findViewById(id)).startAnimation(an);
    }
    public static int inT(EditText t){
        return MetodosUtiles.inT(t.getText().toString());
    }
    public static double dou(EditText t){
        return MetodosUtiles.dou(t.getText().toString());
    }
    public static void pedirPermiso(Activity activity, TipoDePermiso... T){

        ActivityCompat.requestPermissions(activity, TipoDePermiso.getNombres(T),1000);
    }

    //public static void toast(Object o){
    //    toast(null,o);
   // }
    public static void toast(Context c, Object o){
        Toast.makeText(c,o==null?"null":o.toString(),Toast.LENGTH_SHORT).show();

    }
    public static void irActivity(Activity packageContext, Class<?> cls){
        irActivity(packageContext,cls,null);
    }
    public static void irActivity(Activity packageContext, Class<?> cls, Utilizar<Intent> u){
        Intent t=new Intent(packageContext,cls);
        if(u!=null){
            u.utilizar(t);
        }
        packageContext.startActivity(t);

    }

    public static void loadAssetHtml(WebView wb,String nombre){
        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wb.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                return super.shouldOverrideUrlLoading(view, request);
                return false;
            }
        });
        //wb.getSettings().setAppCacheEnabled(true);
        //wb.getSettings().setAppCachePath("");
        wb.getSettings().setDomStorageEnabled(true);
        wb.loadUrl("file:///android_asset/"+nombre);

    }
    public static void iniLoadGoogleRapido(WebView wb){
        wb.getSettings().setJavaScriptEnabled(false);
        wb.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        wb.getSettings().setBlockNetworkImage(true);
        //wb.getSettings().setBlockNetworkLoads(true);
        //wb.getSettings().setAllowContentAccess(false);
        //wb.getSettings().setAllowFileAccess(false);
        //wb.getSettings().setAllowFileAccessFromFileURLs(false);
        //wb.getSettings().setAllowUniversalAccessFromFileURLs(false);
        //wb.getSettings().setUseWideViewPort();
        iniLoadUrl(wb,"www.google.com");
    }
    public static void iniLoadUrl(WebView wb,String sitio){
        wb.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        wb.getSettings().setBuiltInZoomControls(true);
        loadUrl(wb,sitio);
    }

    public static void loadUrl(WebView wb,String sitio){
        String http="http://",url=sitio.startsWith(http)?sitio:http+sitio;
        wb.loadUrl(url);
    }
    public static void setVisible(Menu menu,boolean visible,int ... IDs){
        for (int id: IDs) {
            menu.findItem(id).setVisible(visible);
        }
    }

    public static  void encenderFlash(){
        Camera camera=Camera.open();
        Camera.Parameters parameters=camera.getParameters();

        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);




        camera.setParameters(parameters);
        camera.startPreview();

    }
    public static  void apagarFlash(){
       Camera camera=Camera.open();
        camera.stopPreview();
        camera.release();

    }

    public static void reproducirVideoInterno(Context context,VideoView vw,String direccion){
        File f= ArchivoAndroid.getFileInterno(direccion);
        // System.out.println("salida: f="+f);

        //  System.out.println("salida: exste="+f.exists());
        Uri uri=Uri.parse(f.toURI().toString());
        //  System.out.println("salida: pas1");
        vw.setMediaController(new MediaController(context));
        // System.out.println("salida: 2");
        vw.setVideoURI(uri);
        // System.out.println("salida: 3");
        vw.requestFocus();
        // System.out.println("salida: 4");
        vw.start();
        // System.out.println("salida: lo logro!!");

    }

    public static void setHorizontal(Activity a){
        a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public static void setVertical(Activity a){
        a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public static void setRotativa(Activity a){
        a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

    /**
     * la direccion tiene que apuntar a un .ttf<br/>
     * y estar en la carpeta assets
     * @param c
     * @param direccion
     * @return
     */
    public static Typeface getTypefaceFromAsset(ContextThemeWrapper c,String direccion){
        Typeface font=Typeface.createFromAsset(c.getAssets(),direccion);
        return font;
    }

    /**
     * sustituye el elemento id por el fragment
     * @param activity
     * @param containerViewId
     * @param fra
     */
    public static void replace(FragmentActivity activity,@IdRes int containerViewId,Fragment fra){
        FragmentTransaction transaction=activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(containerViewId,fra);
        transaction.commit();

    }

    public static void llamadaTelefonicaVentana(Activity packageContext,int telefono){
        Intent in=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+telefono));
        packageContext.startActivity(in);

    }

    public static void setAdapterListView(Activity a, @IdRes int id, LinkedList<String>  l){
        setAdapterListView(a,id,l.toArray(new String[0]));
    }
    public static<E> void setAdapterListViewAzul(Activity a, @IdRes int id, E e[]){
        setAdapter(a,(ListView)a.findViewById(id),R.layout.item_lista_fondo_azul,e);
    }
    public static<E> ListaChecableAdaptador2 setAdapterListViewChecable(Activity a, @IdRes int id, E e[]){
        ListView l=(ListView)a.findViewById(id);
        // ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked,nombresPaquetesFiltrados);

        ListaChecableAdaptador2 adaptador=new ListaChecableAdaptador2<E>(a,e);

        l.setAdapter(adaptador);
        return adaptador;
    }
    public static<E> void setAdapterListView(Activity a, @IdRes int id, E e[]){
        setAdapter(a,(ListView)a.findViewById(id),e);
    }
    public static<E> void setAdapter(Activity a, ListView l, E e[]){
        l.setAdapter(getArrayAdapterLista(a,e));
    }
    public static<E> Spinner setAdapterSpinner(Activity a, @IdRes int id, E e[], final Utilizar2<View,Integer> alSeleccionar){
        Spinner s=(Spinner)a.findViewById(id);
        setAdapter(a,s,e);
        if(alSeleccionar!=null){
            final boolean[] primeraSeleccion={false};

            s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(primeraSeleccion[0]){
                        alSeleccionar.utilizar(view,position);

                    }else{
                        primeraSeleccion[0]=true;
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        return s;
    }

    public static SeekBar setOnProgres(Actividad a, @IdRes int id, int progresDefault,final Utilizar2<SeekBar,Integer> u){
        SeekBar sek=(SeekBar)a.findViewById(id);

        if(progresDefault!=-1){
            sek.setProgress(progresDefault);
        }

        sek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //final boolean[] primeraSeleccion={false};
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                if(primeraSeleccion[0]){
                if(fromUser){
                    u.utilizar(seekBar,progress);
                }

//                }else{
//                    primeraSeleccion[0]=true;
//                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return sek;
    }
    public static void setTextSise(View tv,int tamaño){
        setTextSise(tv,TipoDeUnidadDeTipoDeLetra.SP,tamaño);
    }
    public static void setTextSise(View tv,TipoDeUnidadDeTipoDeLetra t,int tamaño){
        if(tv instanceof TextView){
            ((TextView)tv).setTextSize(t.getUnidad(),tamaño);
            return;
        }
        if(tv instanceof Button){
            ((Button)tv).setTextSize(t.getUnidad(),tamaño);
            return;
        }

    }
    public static<E> Spinner setAdapterSpinner(Activity a, @IdRes int id, E e[]){
        return setAdapterSpinner(a,id,e,null);
    }
    public static<E> void setAdapter(Activity a, Spinner l, E e[]){

        l.setAdapter(new ArrayAdapter<E>(a, android.R.layout.simple_spinner_item,e));
    }
    public static<E> void setAdapter(Activity a, AutoCompleteTextView l, E e[]){
        ArrayAdapter<E> ar= new ArrayAdapter<E>(a, android.R.layout.simple_list_item_1,e);
        l.setAdapter(ar);

    }
    public static<E> void setAdapterAutoComplete(Activity a, @IdRes int id, E e[]){
        AutoCompleteTextView au=(AutoCompleteTextView)a.findViewById(id);

        setAdapter(a,au,e);
        //return au;
    }
    /**
     * El resource es para el layout, usar R.layout.archivo<br/>
     * Su root deveria ser un TextView
     * @param a
     * @param l
     * @param resource
     * @param e
     * @param <E>
     */
    public static<E> void setAdapter(Activity a, Spinner l, @LayoutRes int resource, E e[]){
        l.setAdapter(new ArrayAdapter<E>(a, resource,e));
    }
    /**
     * El resource es para el layout, usar R.layout.archivo<br/>
     * Su root deveria ser un TextView
     * @param a
     * @param l
     * @param resource
     * @param e
     * @param <E>
     */
    public static<E> void setAdapter(Activity a, ListView l,@LayoutRes int resource, E e[]){
        l.setAdapter(new ArrayAdapter<E>(a, resource,e));
    }


    public static <E> ArrayAdapter<E> getArrayAdapterLista(Activity a,E e[]){

        return new ArrayAdapter<E>(a, android.R.layout.simple_list_item_1,e);
    }

    public static int getPointerID(MotionEvent e){
        return TipoDeMotionEvent.getPointerID(e);
      //  int pointerIndex=(e.getAction()&MotionEvent.ACTION_POINTER_ID_MASK)>>MotionEvent.ACTION_POINTER_ID_SHIFT;
       // int pointerID=e.getPointerId(pointerIndex);
        //return pointerID;
    }


    public static boolean setSensorEventListenerACCELEROMETER(Activity a,SensorEventListener s){
        SensorManager man=(SensorManager)a.getSystemService(Context.SENSOR_SERVICE);
        if (man.getSensorList(Sensor.TYPE_ACCELEROMETER).size()>0){
            Sensor acel=man.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            if(man.registerListener(s,acel,SensorManager.SENSOR_DELAY_GAME)){
               return true;
            }
        }
        return false;
    }
    public static void setControlDeAudio(Activity a,TipoDeControlDeVolumen c){
        a.setVolumeControlStream(c.getValor());
    }

    public static void reproducirConAppExterna(Activity a,File f){
        if(Archivo.esAudio(f)){
            String extencionSinPunto=Archivo.getExtencion(f).substring(1).toLowerCase();
            Intent in=new Intent(Intent.ACTION_VIEW);
            in.setDataAndType(Uri.fromFile(f),"audio/"+extencionSinPunto);
            a.startActivity(in);
        }

    }
    public static boolean estaVertical(Activity contextThemeWrapper){//ContextThemeWrapper
        return contextThemeWrapper.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE;
    }


    public static void appendLn(TextView t,String text){
        t.setText(t.getText()+"\n"+text);
    }


    public static void salir(Activity a){
        Intent in=new Intent(Intent.ACTION_MAIN);
        in.addCategory(Intent.CATEGORY_HOME);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        a.startActivity(in);
    }

    public static int getVersionSDK(){
        return Build.VERSION.SDK_INT;
    }

    public static void vibrar(Activity a){
        Vibrator vibrator=(Vibrator)a.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator.hasVibrator()){
            vibrator.vibrate(500);
        }
        vibrator=null;
    }

    public static void sonidoCorto(Activity a,@RawRes int id){
        SoundPool sp=new SoundPool(1, AudioManager.STREAM_MUSIC,1);
       int idSonido= sp.load(a,id,1);
        sp.play(idSonido,1,1,1,0,0);

        //sp.unload(idSonido);
        //sp.release();
        //sp=null;
    }
    public static void sonido(Activity a,@RawRes int id){
        MediaPlayer mp=MediaPlayer.create(a,id);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
                mp=null;
            }
        });
    }
    public static void sonidoExterno(Activity a,String direccion)throws Exception{
        sonido(a,ArchivoAndroid.getFileInterno(direccion).toString());
    }
    public static void sonido(Activity a,String direccion)throws Exception{
        MediaPlayer mp=new MediaPlayer();
        mp.setDataSource(direccion);
        mp.prepare();
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
                mp=null;
            }
        });
    }
    public static void sonidoPresionar(Activity a){
        sonido(a,R.raw.presionar_corto);
    }
    public static MediaPlayer getMediaPlayer(Activity a,@RawRes int id){
        return MediaPlayer.create(a,id);
    }

    public static MediaRecorder startGravarAudio(String direccionSalida)throws Exception{
        MediaRecorder mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(direccionSalida);
        mediaRecorder.prepare();
        mediaRecorder.start();
        return mediaRecorder;
     }
    public static MediaRecorder startGravarAudioExterno(String direccionSalida)throws Exception{//
      return  startGravarAudio(ArchivoAndroid.getFileInterno(direccionSalida).toString());
    }
    public static void stopGravarAudio(MediaRecorder mediaRecorder)throws Exception{
        mediaRecorder.stop();
        mediaRecorder.release();
    }//ArchivoAndroid.getFileInterno(direccion).toString()


    public static void responderException(Context a,Exception ex){
        String []mensajes= getResponderExceptionMensage(ex);
        showDialogAceptarAdvertenciaDefault(a,mensajes[1],mensajes[0]);
    }

    private static  String[] getResponderExceptionMensage(Exception ex){
        String []mensajes=MetodosUtiles.getResponderExceptionMensage(ex);
        if (mensajes==null){
            if(ex instanceof SDNoExisteException){
                String cont=ex.getLocalizedMessage();
               return  new String[]{cont!=null&&!cont.isEmpty()?cont:"No hay conectada ninguna SD","Error"};
            }
            mensajes=new String[]{"Error de codigo interno","Error"};
        }

        return mensajes;
    }


    public static void setEnable(boolean enable,View ... V){
        for (int i = 0; i <V.length ; i++) {
            V[i].setEnabled(enable);
        }

    }

    public static void ocultarBarraNavegacion(AppCompatActivity act){
        act.getSupportActionBar().hide();

    }
}
