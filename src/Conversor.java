public class Conversor {
    public double convertir(double cantidad, TasaDeCambio tasaDeCambio) {
        return cantidad * tasaDeCambio.getTasa();
    }
}
