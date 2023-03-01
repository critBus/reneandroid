package com.rene.android.reneandroid.Clases.Apoyo;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import com.rene.android.reneandroid.Clases.Actividad;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Procedimento.ProcedimientoUtilizarConExecption;

/**
 * Created by Rene on 28/6/2020.
 */

public class EquipoDeLocalizacion {
   private int distaciaMinima=1,tiempoMinimo=3*1000;

    private LocationListener loc;
    private LocationManager lom;
    private String provedor;

    private ProcedimientoUtilizarConExecption<Actividad> resumePausa;

    public EquipoDeLocalizacion(Activity a,int distaciaMinima, int tiempoMinimo, LocationListener loc) {
        Criteria criteria=new Criteria();
        criteria.setCostAllowed(true);
        criteria.setAltitudeRequired(true);
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        ini(a,criteria,distaciaMinima,tiempoMinimo,loc);
    }
    public EquipoDeLocalizacion(Activity a,Criteria criteria,int distaciaMinima, int tiempoMinimo, LocationListener loc) {
        ini(a,criteria,distaciaMinima,tiempoMinimo,loc);
    }
    private void ini(Activity a,Criteria criteria,int distaciaMinima2, int tiempoMinimo2, LocationListener loc2) {
        this.distaciaMinima = distaciaMinima2;
        this.tiempoMinimo = tiempoMinimo2;
        this.loc = loc2;

        lom=(LocationManager)a.getSystemService(Context.LOCATION_SERVICE);

        provedor=lom.getBestProvider(criteria,true);

        Location location=lom.getLastKnownLocation(provedor);
        resumePausa=new ProcedimientoUtilizarConExecption<>(new Utilizar<Actividad>() {
            @Override
            public void utilizar(Actividad a) {
                lom.requestLocationUpdates(provedor, tiempoMinimo, distaciaMinima, loc);
            }
        }, new Utilizar<Actividad>() {
            @Override
            public void utilizar(Actividad a) {
                lom.removeUpdates(loc);
            }
        });
    }

    public int getDistaciaMinima() {
        return distaciaMinima;
    }

    public int getTiempoMinimo() {
        return tiempoMinimo;
    }

    public LocationListener getLoc() {
        return loc;
    }

    public LocationManager getLom() {
        return lom;
    }

    public String getProvedor() {
        return provedor;
    }

    public ProcedimientoUtilizarConExecption<Actividad> getResumePausa() {
        return resumePausa;
    }
}
