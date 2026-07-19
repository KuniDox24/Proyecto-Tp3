
public class Paquete {
    private int ID;
    private String contenido;
    private int cantidad;
    private double peso;

    public Paquete(int _ID, String _contenido, int _cantidad, double _peso){
        ID = _ID;
        contenido = _contenido;
        cantidad = _cantidad;
        peso = _peso;
    }

    //getters
    public int getCantidad() {
        return cantidad;
    }
    public String getContenido() {
        return contenido;
    }
    public int getID() {
        return ID;
    }
    public double getPeso() {
        return peso;
    }

}
