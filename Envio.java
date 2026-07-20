import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Vector;

public class Envio implements Serializable {
    public enum ESTADO { EN_VIA, EN_ESPERA };
    private int ID; //ID unico del envio
    private String contenido; //contenido del envio
    private String origen; //que entidad lo envia
    private String destino; //cual es su destino final
    private Queue<Ruta> camino; //conjunto de rutas que debe recorrer el envio
    private String ubicacion; //en que ruta/entidad se encuentra el envio
    private double tiempoEspera; //tiempo en horas
    private ESTADO estado;


    public Envio(int _ID,String _contenido, String _origen, String _destino,Vector<Ruta> _camino){
        ID = _ID;
        origen = _origen;
        destino = _destino;
        ubicacion = _origen;
        contenido = _contenido;
        camino = new ArrayDeque<>();

        for(Ruta r : _camino){
            camino.add(r);
        }
        estado = ESTADO.EN_ESPERA;
        calcularTiempo();


    }

    private void calcularTiempo(){
        double distanciaTotal = 0;
        for(Ruta r : camino){
            distanciaTotal += r.getDistancia();
        }
        tiempoEspera = Math.round((distanciaTotal/45.0)*1000.0)/1000.0;
    }

    public void avanzar(){
        if(camino.isEmpty()){
            return;
        }

        if(estado == ESTADO.EN_ESPERA){
            ubicacion = camino.peek().getOrigen()+"->"+camino.peek().getDestino();
            estado = ESTADO.EN_VIA;
        } else {
            ubicacion = camino.remove().getDestino();
            estado = ESTADO.EN_ESPERA;
            if(camino.isEmpty()){
                return;
            }
        }
        calcularTiempo();



    }

    public void nuevoDestino(String _destino,Vector<Ruta> _camino){
        destino = _destino;
        camino = new ArrayDeque<>();
        for(Ruta r : _camino){
            camino.add(r);
        }
        calcularTiempo();

    }

    public int getID() {
        return ID;
    }
    public String getOrigen() {
        return origen;
    }
    public String getDestino() {
        return destino;
    }
    public String getContenido() {
        return contenido;
    }
    public ESTADO getEstado() {
        return estado;
    }
    public double getTiempoEspera() {
        return tiempoEspera;
    }
    public String getUbicacion() {
        return ubicacion;
    }
    public Ruta geRutaActual(){
        return camino.peek();
    }
    public boolean fueEntregado(){
        return camino.isEmpty();
    }




}
