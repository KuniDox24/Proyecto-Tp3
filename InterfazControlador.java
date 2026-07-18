import org.jgrapht.*;

public interface InterfazControlador {

    public void agregarNodo(String nombre,int[] ubicacion ,String contacto, String rol);
    public void eliminarNodo(String Nombre);
    
    public Graph<Entidad, Ruta> getGrafo();
    
    public void agregarRuta(String origen, String objetivo);
    public void eliminarRuta(String origen, String destino);
}
