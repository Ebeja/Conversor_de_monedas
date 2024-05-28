public class TasaDeCambio {
    private String monedaOrigen;
    private String monedaDestino;
    private double tasa;

    public TasaDeCambio(String monedaOrigen, String monedaDestino, double tasa) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.tasa = tasa;
    }

    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public double getTasa() {
        return tasa;
    }

    @Override
    public String toString() {
        return "TasaDeCambio{" +
                "monedaOrigen='" + monedaOrigen + '\'' +
                ", monedaDestino='" + monedaDestino + '\'' +
                ", tasa=" + tasa +
                '}';
    }
}
