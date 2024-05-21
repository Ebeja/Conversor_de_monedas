import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

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

                double tasaARS = 0;
                double cantidadARS = 0;

                switch (opcion) {
                    case 1:
                        System.out.println("Ha seleccionado la Opción 1 (ARS a USD)");
                        System.out.print("Ingrese la cantidad de ARS a convertir: ");
                        cantidadARS = scanner.nextDouble();

                        Moneda monedaOrigen1 = new Moneda("ARS");
                        Moneda monedaDestino1 = new Moneda("USD");

                        Map<String, Double> tasasARS = conexionApi.obtenerTasasDeCambio(monedaOrigen1.getnombre(), monedaDestino1.getnombre());
                        if (tasasARS != null) {
                            Conversor.inicializarTasasDeCambio(tasasARS);
                            double resultadoUSD = Conversor.convertir(cantidadARS, monedaOrigen1.getnombre(), monedaDestino1.getnombre());
                            System.out.println("Resultado de la conversión: " + resultadoUSD + " USD");
                        }
                        break;

                    case 2:
                        System.out.println("Ha seleccionado la Opción 2 (USD a ARS)");
                        System.out.print("Ingrese la cantidad de USD a convertir: ");
                        Double cantidadUSD = scanner.nextDouble();

                        Moneda monedaOrigen2 = new Moneda("USD");
                        Moneda monedaDestino2 = new Moneda("ARS");

                        Map<String, Double> tasasUSD = conexionApi.obtenerTasasDeCambio(monedaOrigen2.getnombre(), monedaDestino2.getnombre());
                        if (tasasUSD != null) {
                            Conversor.inicializarTasasDeCambio(tasasUSD);
                            double resultadoARS = Conversor.convertir(cantidadUSD, monedaOrigen2.getnombre(), monedaDestino2.getnombre());
                            System.out.println("Resultado de la conversión: " + resultadoARS + " ARS");
                        }
                        break;

                    case 3:
                        System.out.println("Ha seleccionado la Opción 3 (ARS a EUR)");
                        System.out.print("Ingrese la cantidad de ARS a convertir: ");
                        cantidadARS = scanner.nextDouble();

                        Moneda monedaOrigen3 = new Moneda("ARS");
                        Moneda monedaDestino3 = new Moneda("EUR");

                        Map<String, Double> tasasARS_EUR = conexionApi.obtenerTasasDeCambio(monedaOrigen3.getnombre(), monedaDestino3.getnombre());
                        if (tasasARS_EUR != null) {
                            Conversor.inicializarTasasDeCambio(tasasARS_EUR);
                            double resultadoEUR = Conversor.convertir(cantidadARS, monedaOrigen3.getnombre(), monedaDestino3.getnombre());
                            System.out.println("Resultado de la conversión: " + resultadoEUR + " EUR");
                        }
                        break;

                    case 4:
                        System.out.println("Ha seleccionado la Opción 4 (EUR a ARS)");
                        System.out.print("Ingrese la cantidad de EUR a convertir: ");
                        Double cantidadEUR = scanner.nextDouble();

                        Moneda monedaOrigen4 = new Moneda("EUR");
                        Moneda monedaDestino4 = new Moneda("ARS");

                        Map<String, Double> tasasEUR_ARS = conexionApi.obtenerTasasDeCambio(monedaOrigen4.getnombre(), monedaDestino4.getnombre());
                        if (tasasEUR_ARS != null) {
                            Conversor.inicializarTasasDeCambio(tasasEUR_ARS);
                            double resultadoARS = Conversor.convertir(cantidadEUR, monedaOrigen4.getnombre(), monedaDestino4.getnombre());
                            System.out.println("Resultado de la conversión: " + resultadoARS + " ARS");
                        }
                        break;
                }

            } while (opcion != 5);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al conectar con la API: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
