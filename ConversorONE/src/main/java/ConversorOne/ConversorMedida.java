package ConversorOne;
import java.text.DecimalFormat;
import java.util.Objects;

interface ConversorMedida {
    default double converteM(double v, String m) {
        if(Objects.equals(m, "quilômetro")){
            v *= 1000;
        } else if (Objects.equals(m, "hectômetro")) {
            v *= 100;
        } else if (Objects.equals(m, "decâmetro")) {
            v *= 10;
        } else if (Objects.equals(m, "metro")) {
            v *= 1;
        } else if (Objects.equals(m, "decímetro")) {
            v /= 10;
        } else if (Objects.equals(m, "centímetro")) {
            v /= 100;
        } else if (Objects.equals(m, "milímetro")) {
            v /= 1000;
        }
        return v;
    }
    default String converterResposta(double v, String m){
        String mFinal = null;
        if(Objects.equals(m, "quilômetro")){
            v /= 1000; mFinal = "km";
        } else if (Objects.equals(m, "hectômetro")) {
            v /= 100; mFinal = "hm";
        } else if (Objects.equals(m, "decâmetro")) {
            v /= 10; mFinal = "dam";
        } else if (Objects.equals(m, "metro")) {
            v *= 1; mFinal = "m";
        } else if (Objects.equals(m, "decímetro")) {
            v *= 10; mFinal = "dm";
        } else if (Objects.equals(m, "centímetro")) {
            v *= 100; mFinal = "cm";
        } else if (Objects.equals(m, "milímetro")) {
            v *= 1000; mFinal = "mm";
        }
        DecimalFormat df = new DecimalFormat("0.00");

        return df.format(v) + " " + mFinal;
    }
}
