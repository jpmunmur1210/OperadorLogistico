package model;

public class Fluvial extends Envio {

    public Fluvial(String codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }

    @Override
    public double calcularTarifa() {
        // Se usan los valores de la fila "Marítimo" de la tabla del parcial.
        return (distancia * 800) + (peso * 1000);
    }

    @Override
    public String getTipo() {
        return "Fluvial";
    }
}
