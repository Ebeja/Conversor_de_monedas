import java.util.Map;

public class Conversor {
    private static Map<String, Double> tasasDeCambio;

    public static void inicializarTasasDeCambio(Map<String, Double> tasas) {
        tasasDeCambio = tasas;
    }

    public static double convertir(double cantidad, String monedaOrigen, String monedaDestino) {
        if (!tasasDeCambio.containsKey(monedaOrigen) || !tasasDeCambio.containsKey(monedaDestino)) {
            System.out.println("error en convertir.");
            return -1; // Retornar un valor negativo para indicar un error
        }
        double tasaDeCambio = tasasDeCambio.get(monedaDestino) / tasasDeCambio.get(monedaOrigen);
        return cantidad * tasaDeCambio;
    }
}
