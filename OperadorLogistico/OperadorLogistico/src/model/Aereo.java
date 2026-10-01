package model;

public class Aereo extends Envio {

    public Aereo(String codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }

    @Override
    public double calcularTarifa() {
        return (distancia * 5000) + (peso * 4000);
    }

    @Override
    public String getTipo() {
        return "Aereo";
    }
}
