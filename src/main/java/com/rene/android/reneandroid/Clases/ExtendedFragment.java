package com.rene.android.reneandroid.Clases;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar2;
import com.rene.android.reneandroid.Utiles.MetodosUtiles.MetodosUtiles;
import com.rene.android.reneandroid.VisualAndroid;

/**
 * Created by Rene on 30/10/2020.
 */

public class ExtendedFragment extends Fragment {
    public static int inT(String a){
        return MetodosUtiles.inT(a);
    }
    public static int inT(EditText a){
        return VisualAndroid.inT(a);
    }
    public static boolean isEmptyOR(String... A){
        return MetodosUtiles.isEmptyOR(A);
    }
    public static boolean esNumeroAll(EditText ... T){
        return VisualAndroid.esNumeroAll(T);
    }
    public static boolean esNumeroAll(String... A) {
        return MetodosUtiles.esNumeroAll(A);
    }
    public static  void showDialogInformacion(FragmentActivity a, String mensage){
        VisualAndroid.showDialogAceptarInformacion(a,mensage);
    }
    public static void irActivity(Activity packageContext, Class<?> cls){
        VisualAndroid.irActivity(packageContext,cls);
    }
    public static void setVisible(Menu menu, boolean visible, int ... IDs){
        VisualAndroid.setVisible(menu,visible,IDs);
    }
    public static  void showDialogAdvertencia(FragmentActivity a, String mensage){
        VisualAndroid.showDialogAceptarAdvertencia(a,mensage);
    }
    public static  void showDialogAdvertenciaAceptarCancelar(FragmentActivity a, String mensage, Utilizar2<DialogFragment,View> accionAceptar){
        VisualAndroid.showDialogAceptarCancelarAdvertencia(a,mensage,accionAceptar);
    }
    public static boolean isEmpty(TextView t){
        return VisualAndroid.isEmpty(t);
    }
    public static boolean isEmpty(EditText t){
        return VisualAndroid.isEmpty(t);
    }
    public static void setVisible(View view,boolean visible){
        VisualAndroid.setVisible(view,visible);
    }
    public static  void loadAssetHtml(Activity a, @IdRes int id, String nombre){

        VisualAndroid.loadAssetHtml((WebView)a.findViewById(id),nombre);
    }
    public static void showDialogSalir(FragmentActivity a){
        VisualAndroid.showDialogSalir(a);
    }
    public static double dou(EditText t){
        return VisualAndroid.dou(t);
    }

}
