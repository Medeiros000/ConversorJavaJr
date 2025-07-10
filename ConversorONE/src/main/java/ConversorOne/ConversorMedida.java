package ConversorOne;

public class ConversorMedida {
  protected double converteM(double valor, String medida) {
    return switch (medida) {
      case "quilômetro" -> valor * 1000;
      case "hectômetro" -> valor * 100;
      case "decâmetro" -> valor * 10;
      case "metro" -> valor * 1;
      case "decímetro" -> valor / 10;
      case "centímetro" -> valor / 100;
      case "milímetro" -> valor / 1000;
      default -> throw new IllegalArgumentException("Medida inválida: " + medida);
    };
  }

  protected String converterResposta(double valor, String medida) {
    String medidaFinal = null;
    switch (medida) {
      case "quilômetro" -> {
        medidaFinal = "km";
        valor /= 1000;
      }
      case "hectômetro" -> {
        medidaFinal = "hm";
        valor /= 100;
      }
      case "decâmetro" -> {
        medidaFinal = "dam";
        valor /= 10;
      }
      case "metro" -> {
        medidaFinal = "m";
        valor *= 1;
      }
      case "decímetro" -> {
        medidaFinal = "dm";
        valor *= 10;
      }
      case "centímetro" -> {
        medidaFinal = "cm";
        valor *= 100;
      }
      case "milímetro" -> {
        medidaFinal = "mm";
        valor *= 1000;
      }
      default -> throw new IllegalArgumentException("Medida inválida: " + medida);
    }
    return String.format("%.2f %s", valor, medidaFinal);
  }
}
