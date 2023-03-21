package ConversorOne;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Scanner;


public class ConversorAPI {
    private static final String API_URL = "https://economia.awesomeapi.com.br/json/last/";

    public static String converterNomeMoeda(String moeda) {
        if (Objects.equals(moeda, "dólar")) {
            moeda = "USD";
        } else if (Objects.equals(moeda, "real")) {
            moeda = "BRL";
        } else if (Objects.equals(moeda, "euro")) {
            moeda = "EUR";
        } else if (Objects.equals(moeda, "libra esterlina")) {
            moeda = "GBP";
        } else if (Objects.equals(moeda, "peso argentino")) {
            moeda = "ARS";
        } else if (Objects.equals(moeda, "peso chileno")) {
            moeda = "CLP";
        }
        return moeda;
    }

    public static String convert(double amount, String fromCurrency, String toCurrency) throws IOException, JSONException {
        String De = converterNomeMoeda(fromCurrency);
        String Para = converterNomeMoeda(toCurrency);
        if(Objects.equals(De, Para)){
            return "Operação inválida";
        }
        String jsonResponse = getApiResponse(API_URL+De+"-"+Para);
        JSONObject rates = new JSONObject(jsonResponse);
        double Cotacao = rates.getJSONObject(De+Para).getDouble("bid");
        DecimalFormat df = new DecimalFormat("0.000");
        return Para + " " + df.format(amount * Cotacao);
    }

    private static String getApiResponse(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HTTP error code: " + responseCode);
        } else {
            Scanner scanner = new Scanner(url.openStream());
            scanner.useDelimiter("\\Z");
            return scanner.next();
        }
    }
}
