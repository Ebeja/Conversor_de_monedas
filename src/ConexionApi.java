import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class ConexionApi {
    private static final String URL_BASE = "https://v6.exchangerate-api.com/v6/61a06920e6b123b2c837afa0/latest/";

    public Map<String, Double> obtenerTasasDeCambio(String monedaOrigen, String monedaDestino) throws IOException, InterruptedException {
        String url = "https://v6.exchangerate-api.com/v6/61a06920e6b123b2c837afa0/latest/" + monedaOrigen.toUpperCase();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            // Parsear la respuesta JSON utilizando Gson
            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);

            // Verificar si la moneda de destino está presente en las tasas de cambio
            JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");
            if (conversionRates.has(monedaDestino.toUpperCase())) {
                // Obtener la tasa de cambio específica para la moneda de destino
                double tasa = conversionRates.get(monedaDestino.toUpperCase()).getAsDouble();

                // Crear un mapa con la tasa de cambio específica
                Map<String, Double> tasasDeCambio = new HashMap<>();
                tasasDeCambio.put(monedaDestino.toUpperCase(), tasa);

                return tasasDeCambio;
            } else {
                System.out.println("No se encontró la moneda de destino en las tasas de cambio.");
                return null;
            }
        } else {
            System.out.println("Error al obtener las tasas de cambio. Código de estado: " + response.statusCode());
            return null;
        }
    }

}