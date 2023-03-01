package com.rene.android.reneandroid.Clases.Dialogos;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar2ConException;
import com.rene.android.reneandroid.VisualAndroid;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar2;
import com.rene.android.reneandroid.Utiles.MetodosUtiles.MetodosUtiles;

/**
 * Created by Rene on 22/3/2020.
 */

public  class  DialogoFragment extends DialogFragment {

    private Utilizar2<DialogFragment,View> accionAceptar;
//    private Utilizar2ConException<DialogFragment,View> accionAceptarConException;
//    private String mensaje;
    //private View vista;

    protected Utilizar2<View,AlertDialog.Builder> preparBuilder;
    protected Utilizar2<DialogFragment,View> preparVista;
    private    @LayoutRes    int idLayout=-1;
    public DialogoFragment(){

    }


//    protected void inicializarAceptar( String mensaje,Utilizar2<DialogFragment,View> accionAceptar) {
//        this.accionAceptar = accionAceptar;
//        this.mensaje = mensaje;
//
//    }
    public DialogoFragment inicializarAceptar( Utilizar2<DialogFragment,View> accionAceptar) {
        this.accionAceptar = accionAceptar;
//        this.mensaje = mensaje;
        return this;
//
    }
    public DialogoFragment inicializarAceptar(@LayoutRes    int idLayout, Utilizar2<DialogFragment,View> accionAceptar) {
        this.accionAceptar = accionAceptar;
        this.idLayout=idLayout;
//        this.mensaje = mensaje;
        return this;
//
    }

    public DialogoFragment inicializarAceptar(@LayoutRes    int idLayout,Utilizar2<DialogFragment,View> preparVista, Utilizar2<DialogFragment,View> accionAceptar) {
        setPreparVista(preparVista);
        return inicializarAceptar(idLayout,accionAceptar);

    }
    public DialogoFragment inicializarAceptarCancelar(@LayoutRes    int idLayout,Utilizar2<DialogFragment,View> preparVista, Utilizar2<DialogFragment,View> accionAceptar) {
        setPreparVista(preparVista);
        preparBuilder=new Utilizar2<View, AlertDialog.Builder>() {
            @Override
            public void utilizar(final View view, AlertDialog.Builder builder) {
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
            }
        };
        return inicializarAceptar(idLayout,accionAceptar);

    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //  final AlertDialog dial[]=new AlertDialog[1];
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        //final  View vv=inflater.inflate(R.layout.fragment_dialogo_aceptar, null);
        final  View vv=inflater.inflate(getR_Layout(), null);
        final DialogFragment df=this;
        //((TextView)vv.findViewById(getR_id_mensaje())).setText(getMensaje());
        if(preparVista!=null){preparVista.utilizar(df,vv);}
        builder.setView(vv)
                // Add action buttons
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if(accionAceptar!=null){
                            accionAceptar.utilizar(df,vv);
                        }
//                        if(accionAceptarConException!=null){
//                            accionAceptarConException.utilizar(df,vv);
//                        }

                    }
                });
        if(preparBuilder!=null){
            preparBuilder.utilizar(vv,builder);
        }

        return builder.create();
    }


    public Utilizar2<DialogFragment, View> getAccionAceptar() {
        return accionAceptar;
    }

    public void setAccionAceptar(Utilizar2<DialogFragment, View> accionAceptar) {
        this.accionAceptar = accionAceptar;
    }

    public Utilizar2<View, AlertDialog.Builder> getPreparBuilder() {
        return preparBuilder;
    }

    public void setPreparBuilder(Utilizar2<View, AlertDialog.Builder> preparBuilder) {
        this.preparBuilder = preparBuilder;
    }

    public Utilizar2<DialogFragment, View> getPreparVista() {
        return preparVista;
    }

    public void setPreparVista(Utilizar2<DialogFragment, View> preparVista) {
        this.preparVista = preparVista;
    }

//    public String getMensaje() {
//        return mensaje;
//    }
//
//    public void setMensaje(String mensaje) {
//        this.mensaje = mensaje;
//    }

//    protected abstract  @LayoutRes
//    int  getR_Layout();
protected   @LayoutRes
int  getR_Layout(){
    return idLayout;
}

    //public abstract @IdRes     int getR_id_mensaje();

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
    public  void showDialogInformacionEnAntivityAnterior( String mensage){
       VisualAndroid.showDialogAceptarInformacion(getActivity(),mensage);
    }
    public  void responderException( Exception ex){
        VisualAndroid.responderException(getActivity(),ex);
    }
}
