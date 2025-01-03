package org.example;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CurrencyExchangeService {

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/6599565828590e4a68ecd4ae/latest/USD";

    public Map<String, Double> getExchangeRates() {
        Map<String, Double> rates = new HashMap<>();

        try {
            // Conexión a la API
            URL url = new URL(API_URL);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            // Parsear la respuesta JSON
            JsonElement root = JsonParser.parseReader(new InputStreamReader(request.getInputStream()));
            JsonObject jsonObject = root.getAsJsonObject();

            // Depuración: imprimir el JSON completo recibido
            System.out.println("Respuesta completa de la API: " + jsonObject.toString());

            // Acceder a las tasas de cambio
            JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");
            if (conversionRates != null) {
                rates.put("COP", conversionRates.get("COP") != null ? conversionRates.get("COP").getAsDouble() : null);
                rates.put("ARS", conversionRates.get("ARS") != null ? conversionRates.get("ARS").getAsDouble() : null);
                rates.put("BRL", conversionRates.get("BRL") != null ? conversionRates.get("BRL").getAsDouble() : null);
                rates.put("MXN", conversionRates.get("MXN") != null ? conversionRates.get("MXN").getAsDouble() : null);
            }

        } catch (Exception e) {
            System.out.println("Error al obtener las tasas de cambio: " + e.getMessage());
        }

        return rates;
    }
}
