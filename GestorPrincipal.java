import java.util.Set;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import org.jgrapht.*;

public class GestorPrincipal implements InterfazControlador {

    private RedEnvios app;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UIVentanaPrincipal.class.getName());
    
    private UIVentanaPrincipal ventana;
    
   public GestorPrincipal(){
        app = new RedEnvios();
        app.cargarTodo();
        ventana = new UIVentanaPrincipal(this);
        ventana.actualizarGrafo(getGrafo());
        ventana.actualizarEnvios();
   }


    
    //==========================================================
    //Control de Red Envios
    //==============================================================

    //ENTIDADES
    public void agregarNodo(String nombre,int[] ubicacion ,String contacto, String rol){
        app.agregarNodo(nombre, ubicacion, contacto, rol);
        ventana.actualizarGrafo(getGrafo());
    }

    public int eliminarNodo(String Nombre){
        int exito = app.eliminarNodo(Nombre);
        ventana.actualizarGrafo(getGrafo());

        return exito;
    }
    
    
    //RUTAS
    public void agregarRuta(String origen, String objetivo){
        app.agregarRuta(origen, objetivo);
        ventana.actualizarGrafo(getGrafo());
    }

    public int eliminarRuta(String origen, String destino){
        int exito = app.eliminarRuta(origen, destino);
        ventana.actualizarGrafo(getGrafo());
        return exito;
    }


    // ENVIOS
    public Vector<Envio> getEnviosActivos(){
        return app.getEnviosActivos();
    }
    public void avanzarEnvio(int ID){
        app.avanzarEnvio(ID);
    }
    public int nuevoEnvio(String origen, String destino, String contenido){
        return app.nuevoEnvio(origen, destino, contenido);
    }
    public void cancelarEnvio(int ID){
        app.cancelarEnvio(ID);
        ventana.actualizarEnvios();
    }
    public int cambiarDestino(int ID, String destino){
        int exito = app.cambiarDestino(ID, destino);
        ventana.actualizarEnvios();
        return exito;
    }

    public Graph<Entidad, Ruta> getGrafo(){
        return app.getGrafo();
    }
    

    //==============================================
    // Control Ventanas
    //============================================


    public Vector<String> getOpciones(){
        Set<Entidad> objetivos = app.getEntidades();
        Vector<String> opciones = new Vector<>();
        for(Entidad e : objetivos){
            opciones.add(e.getNombre());
        }
        return opciones;
    }

    public Vector<String> getHistorial(String nombre){
        return app.getEntidad(nombre).getHistorial();
    }

    public DefaultTableModel getTablaEnvios(DefaultTableModel modelo){
        modelo.setRowCount(0);
        for(Envio e : app.getEnviosActivos()){
            Object[] fila = {
                e.getID(),
                e.getContenido(),
                e.getUbicacion(),
                e.getEstado().toString(),
                e.getTiempoEspera(),
                e.getDestino(), 
                e.getOrigen()
                
                
            };
            modelo.addRow(fila);
        }
        
        return modelo;
    }

    public DefaultTableModel getTablaEnviosEntidad(DefaultTableModel modelo, String entidad){
        modelo.setRowCount(0);
        Entidad seleccionado = app.getEntidad(entidad);
        for(Envio e : seleccionado.getEnEspera()){
            Object[] fila = {
                e.getID(),
                e.getOrigen(),
                e.getDestino(), 
                e.getContenido()
            };
            modelo.addRow(fila);
        }
        
        return modelo;
    }

    public DefaultTableModel getTablaEnviosRuta(DefaultTableModel modelo, String origen,String destino){
        modelo.setRowCount(0);
        Ruta seleccionado = app.getRuta(origen, destino);

        for(Envio e : seleccionado.getEnCamino()){
            Object[] fila = {
                e.getID(),
                e.getOrigen(),
                e.getDestino(), 
                e.getContenido(),
                e.getTiempoEspera()
            };
            modelo.addRow(fila);
        }
        
        return modelo;
    }

    public static void main(String[] args) {
        //Inicializar la app
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        GestorPrincipal sistema = new GestorPrincipal();

    }

    

    
}
