import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import org.jgrapht.*;

import com.mxgraph.swing.mxGraphComponent;

public interface InterfazControlador {

    public boolean agregarNodo(String nombre,int[] ubicacion ,String contacto, String rol);
    public int eliminarNodo(String Nombre);
    
    public Graph<Entidad, Ruta> getGrafo();
    
    public void agregarRuta(String origen, String objetivo);
    public int eliminarRuta(String origen, String destino);


    //===================
    // ENVIOS

    public Vector<Envio> getEnviosActivos();
    public void avanzarEnvio(int ID);
    public int nuevoEnvio(String origen, String destino, String contenido);
    public void cancelarEnvio(int ID);
    public int cambiarDestino(int ID, String destino);

    //======================
    // UI
    public Vector<String> getOpciones();
    public Vector<String> getHistorial(String entidad);

    public mxGraphComponent getGrafoVisual();

    //======================
    // Tablas de UI
    public DefaultTableModel getTablaEnvios(DefaultTableModel modelo);
    public DefaultTableModel getTablaEnviosEntidad(DefaultTableModel modelo, String entidad);
    public DefaultTableModel getTablaEnviosRuta(DefaultTableModel modelo, String origen,String destino);
}
