package org.example;

import java.util.Map;
import java.util.Scanner;

public class CurrencyConverterApp {

    public static void main(String[] args) {
        // Crear instancia del servicio de tasas de cambio
        CurrencyExchangeService exchangeService = new CurrencyExchangeService();
        Map<String, Double> exchangeRates = exchangeService.getExchangeRates();

        // Crear instancia del convertidor de moneda
        CurrencyConverter converter = new CurrencyConverter(exchangeRates);

        // Configurar scanner para obtener entradas del usuario
        Scanner scanner = new Scanner(System.in);

        // Mostrar menú de opciones
        while (true) {
            System.out.println("\nConversor de Moneda (USD como base)");
            System.out.println("1. Convertir USD a otra moneda");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();

            if (option == 1) {
                // Solicitar información de conversión
                System.out.print("Ingrese la cantidad en USD: ");
                double amount = scanner.nextDouble();

                System.out.println("Seleccione la moneda de destino:");
                System.out.println("1. COP (Peso colombiano)");
                System.out.println("2. ARG (Peso argentino)");
                System.out.println("3. BRL (Real brasileño)");
                System.out.println("4. MXN (Peso mexicano)");
                System.out.print("Seleccione la opción de moneda: ");
                int currencyOption = scanner.nextInt();

                String toCurrency = "";
                switch (currencyOption) {
                    case 1:
                        toCurrency = "COP";
                        break;
                    case 2:
                        toCurrency = "ARG";
                        break;
                    case 3:
                        toCurrency = "BRL";
                        break;
                    case 4:
                        toCurrency = "MXN";
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        continue;
                }

                // Realizar la conversión
                double convertedAmount = converter.convert(amount, "USD", toCurrency);
                System.out.println(amount + " USD = " + convertedAmount + " " + toCurrency);
            } else if (option == 2) {
                System.out.println("Gracias por usar el conversor de monedas.");
                break;
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        scanner.close();
    }
}
