import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.mxgraph.util.mxConstants;

import org.jgrapht.*;
import org.jgrapht.ext.JGraphXAdapter;

import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxICell;
import com.mxgraph.swing.mxGraphComponent;

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
    public boolean agregarNodo(String nombre,int[] ubicacion ,String contacto, String rol){
        boolean exito = app.agregarNodo(nombre, ubicacion, contacto, rol);
        if(exito){
            ventana.actualizarGrafo(getGrafo());
        }
        return exito;
    }

    public int eliminarNodo(String Nombre){
        int exito = app.eliminarNodo(Nombre);
        ventana.actualizarGrafo(getGrafo());

        return exito;
    }
    
    
    //RUTAS
    public void agregarRuta(String origen, String objetivo){
        if(!app.agregarRuta(origen, objetivo)){
            JOptionPane.showMessageDialog(ventana, "Ruta invalida, debe respetar la jerarquia de los roles\nProveedor, Fabricante, Distribuidor, Alamacen, Minorista");
        }
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
        ventana.actualizarEnvios();
    }
    public int nuevoEnvio(String origen, String destino, String contenido){
        int exito = app.nuevoEnvio(origen, destino, contenido);
        if(exito == 0){
            ventana.actualizarEnvios();
        }

        return exito;
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
    
    public mxGraphComponent getGrafoVisual(){
        JGraphXAdapter<Entidad, Ruta> adapter = new JGraphXAdapter<>(getGrafo());

        //crea un elemento visual usando el adaptador
        mxGraphComponent grafoVisual = new mxGraphComponent(adapter);
        grafoVisual.setMinimumSize(new java.awt.Dimension(600, 400));

        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;

        //asigna posiciones a los nodos segun su ubicacion
        for (Map.Entry<Entidad, mxICell> entry : adapter.getVertexToCellMap().entrySet()) {
            Entidad entidad = entry.getKey();
            int[] ubicacion = entidad.getUbicacion();

            int x = ubicacion[0] * 180 + 250;
            int y = ubicacion[1] * 60 + 150;

            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);
        }

        int padding = 250;
        int offsetX = padding - Math.min(0, minX);
        int offsetY = padding - Math.min(0, minY);

        for (Map.Entry<Entidad, mxICell> entry : adapter.getVertexToCellMap().entrySet()) {
            Entidad entidad = entry.getKey();
            Object cell = entry.getValue();
            int[] ubicacion = entidad.getUbicacion();

            int x = ubicacion[0] * 180 + offsetX;
            int y = ubicacion[1] * 60 + offsetY;

            mxGeometry geometry = new mxGeometry(x, y, 90, 30);
            geometry.setRelative(false);
            adapter.getModel().setGeometry(cell, geometry);
        }

        int width = Math.max(800, maxX - minX + 90 + padding * 2);
        int height = Math.max(600, maxY - minY + 30 + padding * 2);
        grafoVisual.setPreferredSize(new java.awt.Dimension(width, height));
        grafoVisual.setAutoscrolls(true);

        adapter.getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_NOLABEL, "false");
        adapter.getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_EDGE, "orthogonalEdgeStyle");
        adapter.getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_ROUNDED, "true");

        grafoVisual.getGraph().setCellsEditable(false);
        grafoVisual.getGraph().setCellsResizable(false);
        grafoVisual.getGraph().setCellsMovable(false);
        grafoVisual.getGraph().setVertexLabelsMovable(false);
        grafoVisual.getGraph().setCellsLocked(true);

        return grafoVisual;

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
