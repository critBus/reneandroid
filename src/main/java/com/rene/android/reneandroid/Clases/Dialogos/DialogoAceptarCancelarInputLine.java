package com.rene.android.reneandroid.Clases.Dialogos;

import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.rene.android.reneandroid.R;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar2;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar3;

/**
 * Created by Rene on 22/3/2022.
 */

public  class DialogoAceptarCancelarInputLine extends DialogoFragment {
    private String titulo;
    private String hint;
    private View vista;
    private Utilizar3<DialogoAceptarCancelarInputLine,View,String> accionAceptarConTexto;
    private Utilizar2<DialogoAceptarCancelarInputLine,View> accionCancelar;
    private Utilizar2<DialogoAceptarCancelarInputLine,View> alCrearLaVista;


    public void inicializarAceptarCancelar(String tituloActual,String hint,final Utilizar2<DialogoAceptarCancelarInputLine,View> alCrearLaVista,final Utilizar3<DialogoAceptarCancelarInputLine,View,String> accionAceptarConTexto) {

        inicializarAceptarCancelar(tituloActual,hint,alCrearLaVista,accionAceptarConTexto,null);
    }

    public void inicializarAceptarCancelar(String tituloActual,String hintActual,final Utilizar2<DialogoAceptarCancelarInputLine,View> alCrearLaVista,final Utilizar3<DialogoAceptarCancelarInputLine,View,String> accionAceptarConTexto, final Utilizar2<DialogoAceptarCancelarInputLine,View> accionCancelar) {
        //super.inicializarAceptar(mensaje,accionAceptar );
        final DialogoAceptarCancelarInputLine dfActual=this;
        super.inicializarAceptar(new Utilizar2<DialogFragment, View>() {
            @Override
            public void utilizar(DialogFragment dialogFragment, View view) {
                //EditText et=(EditText)view.findViewById(R.id.et_texto_del_usuario_fragment_edit_text);
                //accionAceptarConTexto.utilizar(dfActual,view,et.getText().toString());
                accionAceptarConTexto.utilizar(dfActual,view,getTexto());
            }
        });
        this.titulo=tituloActual;
        this.hint=hintActual;
        this.accionCancelar=accionCancelar;

        preparVista=new Utilizar2<DialogFragment,View>() {
            @Override
            public void utilizar(DialogFragment df, View a) {
                // ((Toolbar)a.findViewById(R.id.TBDialogoAceptar)).setTitle("Informacion");
                vista=a;
                setTitulo(titulo);
                setHint(hint);
                if(alCrearLaVista!=null){
                    alCrearLaVista.utilizar(dfActual,a);
                }
            }
        };
        preparBuilder=new Utilizar2<View, AlertDialog.Builder>() {
            @Override
            public void utilizar(final View view, AlertDialog.Builder builder) {
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(accionCancelar!=null){
                            accionCancelar.utilizar(dfActual,view);
                        }
                        dialog.cancel();

                    }
                });
            }
        };

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        ((TextView)this.vista.findViewById(R.id.tv_titulo_en_fragment_edit_text)).setText(this.titulo);
        this.titulo = titulo;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        ((TextInputLayout)this.vista.findViewById(R.id.tf_fragment_text_edit)).setHint(hint);
        this.hint = hint;
    }

    public void setTexto(String texto){
        EditText et=(EditText)this.vista.findViewById(R.id.et_texto_del_usuario_fragment_edit_text);
        et.setText(texto);
    }
    public String getTexto(){
        EditText et=(EditText)this.vista.findViewById(R.id.et_texto_del_usuario_fragment_edit_text);
        return et.getText().toString();
    }


    @Override
    protected @LayoutRes
    int  getR_Layout(){
        return R.layout.fragment_dialogo_text_edit;
    }
}
