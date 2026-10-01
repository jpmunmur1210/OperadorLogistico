package controller;

import model.Aereo;
import model.Envio;
import model.Fluvial;
import model.Terrestre;
import service.EnvioService;

import java.util.ArrayList;

public class EnvioController {

    private EnvioService servicio;

    public EnvioController() {
        servicio = new EnvioService();
    }

    public void agregarEnvio(String codigo, String cliente, String tipo,
                              double peso, double distancia) {

        Envio envio;

        if (tipo.equals("Terrestre")) {
            envio = new Terrestre(codigo, cliente, peso, distancia);
        } else if (tipo.equals("Aereo")) {
            envio = new Aereo(codigo, cliente, peso, distancia);
        } else {
            envio = new Fluvial(codigo, cliente, peso, distancia);
        }

        servicio.agregarEnvio(envio);
    }

    public void retirarEnvio(int posicion) {
        servicio.retirarEnvio(posicion);
    }

    public ArrayList<Envio> listarEnvios() {
        return servicio.listarEnvios();
    }
}
