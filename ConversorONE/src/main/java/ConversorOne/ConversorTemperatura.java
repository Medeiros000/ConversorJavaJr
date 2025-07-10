package ConversorOne;

import java.util.Objects;

public class ConversorTemperatura {
  protected String converteT(double valor, String in, String out) {
    String tmp = null;
    double resposta = 0;
    if (Objects.equals(in, "celsius") && Objects.equals(out, "fahrenheit")) {
      resposta = (valor * 1.8) + 32;
      tmp = "°F";
    } else if (Objects.equals(in, "fahrenheit") && Objects.equals(out, "celsius")) {
      resposta = (valor - 32) / 1.8;
      tmp = "°C";
    } else if (Objects.equals(in, "celsius") && Objects.equals(out, "kelvin")) {
      resposta = valor + 273.15;
      tmp = "K";
    } else if (Objects.equals(in, "kelvin") && Objects.equals(out, "celsius")) {
      resposta = valor - 273.15;
      tmp = "°C";
    } else if (Objects.equals(in, "fahrenheit") && Objects.equals(out, "kelvin")) {
      resposta = ((valor - 32) * 5 / 9) + 273.15;
      tmp = "K";
    } else if (Objects.equals(in, "kelvin") && Objects.equals(out, "fahrenheit")) {
      resposta = ((valor - 273.15) * 9 / 5) + 32;
      tmp = "°F";
    } else if (Objects.equals(in, out)) {
      return "Operação inválida";
    }
    return String.format("%.2f %s", resposta, tmp);
  }
}
