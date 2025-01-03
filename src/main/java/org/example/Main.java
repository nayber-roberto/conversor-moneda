package org.example;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido al Conversor de Monedas");
        System.out.println("Cargando datos de la API...");

        // Inicializar el servicio para obtener las tasas de cambio
        CurrencyExchangeService exchangeService = new CurrencyExchangeService();
        Map<String, Double> rates = exchangeService.getExchangeRates();

        // Depuración: imprimir las tasas de cambio obtenidas
        System.out.println("Tasas de cambio obtenidas:");
        rates.forEach((currency, rate) -> System.out.println(currency + ": " + rate));

        // Inicializar el escáner
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            // Mostrar opciones del menú
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Convertir USD a COP");
            System.out.println("2. Convertir USD a ARS");
            System.out.println("3. Convertir USD a BRL");
            System.out.println("4. Convertir USD a MXN");
            System.out.println("5. Salir");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();

            if (opcion == 5) {
                System.out.println("Saliendo del programa...");
                continuar = false;
                break;
            }

            // Pedir al usuario que ingrese el monto en USD
            System.out.print("Ingrese el valor en USD: ");
            double usdAmount = scanner.nextDouble();

            // Realizar la conversión según la opción seleccionada
            double result = 0.0;
            String currency = "";
            Double rate = null;

            switch (opcion) {
                case 1:
                    rate = rates.get("COP");
                    currency = "COP";
                    break;
                case 2:
                    rate = rates.get("ARS");
                    currency = "ARS";
                    break;
                case 3:
                    rate = rates.get("BRL");
                    currency = "BRL";
                    break;
                case 4:
                    rate = rates.get("MXN");
                    currency = "MXN";
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    continue;
            }

            // Si la tasa de cambio es null, mostrar el mensaje de error
            if (rate != null) {
                result = usdAmount * rate;
                System.out.printf("%.2f USD = %.2f %s\n", usdAmount, result, currency);
            } else {
                System.out.println("No se pudo obtener la tasa de cambio para " + currency);
            }
        }

        // Cerrar el scanner
        scanner.close();
    }
}
