import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConexionApi {
    private static final String URL_BASE = "https://v6.exchangerate-api.com/v6/61a06920e6b123b2c837afa0/latest/";

    public TasaDeCambio obtenerTasaDeCambio(Moneda monedaOrigen, Moneda monedaDestino) throws IOException, InterruptedException {
        String url = URL_BASE + monedaOrigen.getnombre().toUpperCase();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return procesarRespuesta(response.body(), monedaOrigen, monedaDestino);
        } else {
            manejarError(response.statusCode());
            return null;
        }
    }

    private TasaDeCambio procesarRespuesta(String responseBody, Moneda monedaOrigen, Moneda monedaDestino) {
        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(responseBody, JsonObject.class);

        JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");
        String destino = monedaDestino.getnombre().toUpperCase();

        if (conversionRates.has(destino)) {
            double tasa = conversionRates.get(destino).getAsDouble();
            return new TasaDeCambio(monedaOrigen.getnombre().toUpperCase(), destino, tasa);
        } else {
            System.out.println("No se encontró la tasa de cambio para " + destino);
            return null;
        }
    }

    private void manejarError(int statusCode) {
        System.out.println("Error al obtener las tasas de cambio. Código de estado: " + statusCode);
    }
}
