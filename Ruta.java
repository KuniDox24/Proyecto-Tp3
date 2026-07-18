import java.util.ArrayList;

public class Ruta {
    private String origen;
    private String destino;
    private ArrayList<Paquete> enCamino;
    private double distancia;

    public Ruta(String _origen, String _destino, double _distancia){
        origen = _origen;
        destino = _destino;
        distancia = _distancia;
        enCamino = new ArrayList<>();
    }

    @Override
    public String toString(){
        return distancia + "Km";
    }

}
