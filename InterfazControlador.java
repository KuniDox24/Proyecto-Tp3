import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import org.jgrapht.*;

public interface InterfazControlador {

    public Entidad buscarNodoNombre(String Nombre);

    public void agregarNodo(String nombre,int[] ubicacion ,String contacto, String rol);
    public void eliminarNodo(String Nombre);
    
    public Graph<Entidad, Ruta> getGrafo();
    
    public void agregarRuta(String origen, String objetivo);
    public void eliminarRuta(String origen, String destino);


    //===================
    // ENVIOS

    public Vector<Envio> getEnviosActivos();
    public void avanzarEnvio(int ID);
    public int nuevoEnvio(String origen, String destino);
    public void cancelarEnvio(int ID);

    //======================
    // UI
    public Vector<String> getOpciones();

    //======================
    // Tablas de UI
    public DefaultTableModel getTablaEnvios(DefaultTableModel modelo);
}
