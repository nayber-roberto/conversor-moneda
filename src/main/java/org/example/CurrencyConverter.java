package org.example;

import java.util.Map;

public class CurrencyConverter {

    private Map<String, Double> exchangeRates;

    public CurrencyConverter(Map<String, Double> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    public double convert(double amount, String fromCurrency, String toCurrency) {
        if (fromCurrency.equals("USD")) {
            double fromRate = 1.0; // USD a USD es 1
            double toRate = exchangeRates.getOrDefault(toCurrency, 0.0);
            return amount * (toRate / fromRate);
        } else {
            System.out.println("Solo se puede convertir desde USD.");
            return 0.0;
        }
    }
}
