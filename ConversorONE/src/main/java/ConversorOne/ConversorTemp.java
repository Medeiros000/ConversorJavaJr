package ConversorOne;

import java.text.DecimalFormat;
import java.util.Objects;

interface ConversorTemp {
    default String converteT(double v, String in, String out) {
        String tmp = null;
        double resposta = 0;
        if (Objects.equals(in, "celsius") && Objects.equals(out, "fahrenheit")) {
            resposta = (v * 1.8) + 32;
            tmp = "°F";
        } else if (Objects.equals(in, "fahrenheit") && Objects.equals(out, "celsius")) {
            resposta = (v - 32) / 1.8;
            tmp = "°C";
        } else if (Objects.equals(in, "celsius") && Objects.equals(out, "kelvin")) {
            resposta = v + 273.15;
            tmp = "K";
        } else if (Objects.equals(in, "kelvin") && Objects.equals(out, "celsius")) {
            resposta = v - 273.15;
            tmp = "°C";
        } else if (Objects.equals(in, "fahrenheit") && Objects.equals(out, "kelvin")) {
            resposta = ((v - 32) * 5 / 9) + 273.15;
            tmp = "K";
        } else if (Objects.equals(in, "kelvin") && Objects.equals(out, "fahrenheit")) {
            resposta = ((v - 273.15) * 9 / 5) + 32;
            tmp = "°F";
        } else if (Objects.equals(in,out)) {
            return "Operação inválida";
        }
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(resposta) + tmp;
    }
}
