import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        Moneda monedaOrigen = new Moneda("ARS");
        Moneda monedaDestino = new Moneda("USD");

        try {
            ConexionApi conexionApi = new ConexionApi();

            do {
                System.out.println("Menú de Opciones:");
                System.out.println("1. Convertir ARS a USD");
                System.out.println("2. Convertir USD a ARS");
                System.out.println("3. Convertir ARS a EUR");
                System.out.println("4. Convertir EUR a ARS");
                System.out.println("5. Salir");
                System.out.print("Ingrese su elección: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        monedaOrigen.setMoneda("ARS");
                        monedaDestino.setMoneda("USD");
                        break;
                    case 2:
                        monedaOrigen.setMoneda("USD");
                        monedaDestino.setMoneda("ARS");
                        break;
                    case 3:
                        monedaOrigen.setMoneda("ARS");
                        monedaDestino.setMoneda("EUR");
                        break;
                    case 4:
                        monedaOrigen.setMoneda("EUR");
                        monedaDestino.setMoneda("ARS");
                        break;
                    case 5:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, intente de nuevo.");
                        continue;
                }

                if (opcion >= 1 && opcion <= 4) {
                    System.out.print("Ingrese la cantidad a convertir: ");
                    double cantidad = scanner.nextDouble();

                    TasaDeCambio tasaDeCambio = conexionApi.obtenerTasaDeCambio(monedaOrigen, monedaDestino);
                    if (tasaDeCambio != null) {
                        Conversor conversor = new Conversor();
                        double resultado = conversor.convertir(cantidad, tasaDeCambio);
                        System.out.println("Resultado de la conversión: " + resultado + " " + monedaDestino.getnombre());
                    }
                }
            } while (opcion != 5);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al conectar con la API: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

