package service;

import model.Envio;
import java.util.ArrayList;

public class EnvioService {

    private ArrayList<Envio> envios;

    public EnvioService() {
        envios = new ArrayList<>();
    }

    public void agregarEnvio(Envio envio) {
        envios.add(envio);
    }

    public void retirarEnvio(int posicion) {
        if (posicion >= 0 && posicion < envios.size()) {
            envios.remove(posicion);
        }
    }

    public ArrayList<Envio> listarEnvios() {
        return envios;
    }
}
