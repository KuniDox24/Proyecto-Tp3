import java.util.Vector;

public class Ruta {
    private String origen; //donde empieza la ruta
    private String destino; //donde termina la ruta
    private double distancia; //distancia de origen a destino
    private Vector<Envio> enCamino; //envios en camino en esta ruta

    public Ruta(String _origen, String _destino, double _distancia){
        origen = _origen;
        destino = _destino;
        distancia = _distancia;
        enCamino = new Vector<>();
    }

    @Override
    public String toString(){
        return distancia + "Km";
    }

    //getters
    public String getDestino() {
        return destino;
    }
    public double getDistancia() {
        return distancia;
    }
    public Vector<Envio> getEnCamino() {
        return enCamino;
    }
    public String getOrigen() {
        return origen;
    }

    //metodos

    public void comenzarTransporte(Envio nuevo){
        enCamino.add(nuevo);
    }

    public void cancelarTransporte(Envio cancelado){
        enCamino.remove(cancelado);

    }

    public boolean tieneEnvios(){
        return !enCamino.isEmpty();
    }

    public void finalizarTransporte(Envio entregado){
        enCamino.remove(entregado);
        entregado.avanzar();
    }
    
}
