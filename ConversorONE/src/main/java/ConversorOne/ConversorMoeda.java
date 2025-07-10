package ConversorOne;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class ConversorMoeda {
  private static final String API_URL = "https://economia.awesomeapi.com.br/json/last/";

  protected String converterNomeMoeda(String moeda) {
    String cifra = switch (moeda) {
      case "dólar" -> "USD";
      case "real" -> "BRL";
      case "euro" -> "EUR";
      case "libra esterlina" -> "GBP";
      case "peso argentino" -> "ARS";
      case "peso chileno" -> "CLP";
      default -> throw new IllegalArgumentException("Moeda inválida: " + moeda);
    };
    return cifra;
  }

  protected String convert(double amount, String fromCurrency, String toCurrency)
      throws IOException, JSONException {
    String De = converterNomeMoeda(fromCurrency);
    String Para = converterNomeMoeda(toCurrency);
    if (Objects.equals(De, Para)) {
      return "Operação inválida";
    }
    String jsonResponse = getApiResponse(API_URL + De + "-" + Para);
    JSONObject rates = new JSONObject(jsonResponse);
    double Cotacao = rates.getJSONObject(De + Para).getDouble("bid");
    return String.format("%s %.3f", Para, amount * Cotacao);
  }

  protected String getApiResponse(String urlString) throws IOException {
    URL url = new URL(urlString);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.connect();
    int responseCode = conn.getResponseCode();
    if (responseCode != 200) {
      throw new RuntimeException("HTTP error code: " + responseCode);
    } else {
      try (Scanner scanner = new Scanner(url.openStream())) {
        scanner.useDelimiter("\\Z");
        return scanner.next();
      }
    }
  }
}
