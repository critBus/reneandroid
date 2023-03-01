package com.rene.android.reneandroid.Utiles.MetodosUtiles;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.Date;
import java.util.Locale;


/**
 * Created by Rene on 23/10/2020.
 */

public abstract class Tempus {
    public final static int CANTIDAD_DIAS_AÑO = 365;
    public final static int CANTIDAD_DIAS_AÑO_BISIESTO = CANTIDAD_DIAS_AÑO + 1;
    public static final String nombresMes[] = {"enero", "febrero", "marzo", "abril",
            "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
    public static final String nombresDia[] = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
    public static final String nombresDiaEntreSemana[] = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
    public static final String nombresDiaFinesDeSemana[] = {"Sabado", "Domingo"};

    public static Date tiMin(int min) {
        return tiMin(min,0);
    }
    public static Date tiMin(int min, int segundos) {
        return new Date(0, 0, 0, 0, min, segundos);
    }
//    public static String getTaim(Date d, String formato) {
//
//        return getTaim(getLocalDate(d), formato);
////        return DateTimeFormatter.ofPattern(formato).format(d);
//    }

//    public static String getNombreDiaDate(int i) {
//
//        return nombreDia((i + 6) % 7);
//    }
//
//    public static String getNombreDia(Date d) {
//        return getNombreDiaDate(d.getDay());
//        //return nombreDia((d.getDay()+6)%7);
//    }
//
//    public static boolean esMenorQue(Date a, Date b) {
//        return a.compareTo(b) == -1;
//    }
//
//    public static boolean esMayorQue(Date a, Date b) {
//        return a.compareTo(b) == 1;
//    }
//
//    public static Date primerDiaSemana(Date d) {
//        Date domingo = restarDias(d, d.getDay());
//        return domingo;
//    }
//
//    public static Date primerDiaSemanaAnterior(Date d) {
//        return restarDias(primerDiaSemana(d), 7);
//    }
//
//    public static Date aumentarDias(Date d, int cantidad) {
//        if (cantidad == 0) {
//            return d;
//        }
//        return getDate(getLocalDate(d).plusDays(cantidad));
//    }
//
//    public static Date restarDias(Date d, int cantidad) {
//        if (cantidad == 0) {
//            return d;
//        }
//        return getDate(getLocalDate(d).minusDays(cantidad));
//    }
//
//    /**
//     * 19/04/2020 0:16:02
//     *
//     * @param d
//     * @return
//     */
//    public static String getFechaYHoraConSegundos(Date d) {
//        return getTaim(d, '/') + " " + getHoraMinutoSegundo(d);
//    }
//
//
//    /**
//     * tipo 17:40:02
//     *
//     * @param d
//     * @return
//     */
//    public static String getHoraMinutoSegundo(Date d) {
//        return DateFormat.getTimeInstance(DateFormat.MEDIUM).format(d);
//    }
//
//    /**
//     * tipo 17:40
//     *
//     * @param d
//     * @return
//     */
//    public static String getHoraMinuto(Date d) {
//        return DateFormat.getTimeInstance(DateFormat.SHORT).format(d);
//    }
//
//    /**
//     * el formato se elige entre DateFormat.FORMATO_A_ELEGIR<br>
//     * Ejemplos<br>
//     * DateFormat.SHORT 17:40<br>
//     * DateFormat.MEDIUM 17:40:02
//     *
//     * @param formato
//     * @param d
//     * @return
//     */
//    public static String getTimeconFormato(int formato, Date d) {
//        return DateFormat.getTimeInstance(formato).format(d);
//    }
//
//    public static LocalDate getLocalDate(Date d) {
//        return LocalDate.of(d.getYear() + 1900, d.getMonth() + 1, d.getDate());
//    }
//
//    public static Date getDate(LocalDate d) {
//        return new Date(d.getYear() - 1900, d.getMonthValue() - 1, d.getDayOfMonth());
//    }
//
//    public static String getTaim(Date d, char separador) {
//        return getTaim(getLocalDate(d), "dd" + separador + "MM" + separador + "yyyy");
//    }
//
//    public static String getTaim(char separador) {
//        return getTaim(LocalDate.now(), "dd" + separador + "MM" + separador + "yyyy");
//    }
//
//    public static String getTaim(LocalDate d, String formato) {
//        return DateTimeFormatter.ofPattern(formato).format(d);
//    }
//
//    public static String[] getMeses(Locale idioma) {
//        return DateFormatSymbols.getInstance(idioma).getMonths();
//    }
//
//    public static String[] getDias(Locale idioma) {
//        return DateFormatSymbols.getInstance(idioma).getWeekdays();
//    }
//
//    public static boolean esFecha(String a, int indice) {
//        return obtenerFecha(a, indice) != null;
//    }
}
