package com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos;

import com.rene.android.reneandroid.Utiles.MetodosUtiles.MetodosUtiles;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Rene on 4/4/2022.
 */

public class BDResultadoInsertar {
    class BDDatosColumnaInsertada {

        public TipoDeDatoSQL tipoDeColumna;//nombreColumna,
        public Object valor;

        public BDDatosColumnaInsertada(TipoDeDatoSQL tipoDeColumna, Object valor) {
            this.tipoDeColumna = tipoDeColumna;
            this.valor = valor;
        }

        public TipoDeDatoSQL getTipoDeColumna() {
            return tipoDeColumna;
        }

        public Object getValor() {
            return valor;
        }

    }
    public boolean esID_Numero_Autoincrementado;
    public int id;
    public String nombreColumnaID;

    private List<BDDatosColumnaInsertada> columnasInsertadas;

    public BDResultadoInsertar() {
        columnasInsertadas = new ArrayList<>();
        this.esID_Numero_Autoincrementado = false;
    }

    public BDDatosColumnaInsertada add(TipoDeDatoSQL tipoDeColumna, Object valor) {
        BDDatosColumnaInsertada d = new BDDatosColumnaInsertada(tipoDeColumna, valor);
        columnasInsertadas.add(d);
        comprobarSiEsIDAutomatico();
        return d;
    }

    private void comprobarSiEsIDAutomatico() {
        if (columnasInsertadas.size() == 1) {
            BDDatosColumnaInsertada d = columnasInsertadas.get(0);
            if (d.tipoDeColumna == TipoDeDatoSQL.INTEGER) {
                setID_Autoincrementado(d.getValor());
                return;
            }

        }
        this.esID_Numero_Autoincrementado = false;

    }

    public void setID_Autoincrementado(Object o) {
        if (MetodosUtiles.esNumeroAll(o + "")) {
            this.id = MetodosUtiles.inT(o + "");
            this.nombreColumnaID = "id";
            this.esID_Numero_Autoincrementado = true;
        }
    }

    public boolean esID_Numero_Autoincrementado() {
        return esID_Numero_Autoincrementado;
    }

    public int getId() {
        return id;
    }

    public String getNombreColumnaID() {
        return nombreColumnaID;
    }

    public int getCantidadDeColumnasKey() {
        return columnasInsertadas.size();
    }

    public BDResultadoInsertar.BDDatosColumnaInsertada get(int i) {
        return columnasInsertadas.get(i);
    }

    public boolean isEmpty() {
        return columnasInsertadas.isEmpty();
    }

}

