import java.util.HashMap;
import java.util.Set;
import java.util.Vector;
import java.util.function.Function;

import javax.swing.table.DefaultTableModel;

import org.jgrapht.*;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.AsWeightedGraph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;


public class CadenaSuministros implements InterfazControlador {
    private Graph<Entidad,Ruta> grafo;
    private HashMap<Integer,Envio> enviosActivos;
    private UIVentanaPrincipal ventana;

    public CadenaSuministros(){
        grafo = new SimpleDirectedWeightedGraph<>(Ruta.class);
        ventana = new UIVentanaPrincipal(this);
        enviosActivos = new HashMap<>();
        preCargar();
        //TODO eliminar precarga
    }

    //=====================================================
    //Gestion de Entidades (Nodos en grafo)
    public void agregarNodo(String nombre,int[] ubicacion ,String contacto, String rol){
        Entidad nuevo = new Entidad(nombre,ubicacion,contacto,rol);
        
        grafo.addVertex(nuevo);
        ventana.actualizarGrafo(grafo);

    }

    public Entidad buscarNodoNombre(String Nombre){
        Entidad encontrado = null;

        for(Entidad e : grafo.vertexSet()){
            if(e.getNombre().equals(Nombre)) return e;
        }

        return encontrado;
    }

    public void eliminarNodo(String Nombre){

        grafo.removeVertex(buscarNodoNombre(Nombre));
        ventana.actualizarGrafo(grafo);

    }



    //====================================================
    //Gestion de Rutas (aristas del grafo)

    private double calcularDistancia(int[] a,int[] b){
        
        return Math.sqrt(Math.pow((b[0]-a[0]), 2) + Math.pow((b[1] - a[1]),2) ); 
    }

    public void agregarRuta(String origen, String objetivo){

        Entidad a  = buscarNodoNombre(origen);
        Entidad b = buscarNodoNombre(objetivo);

        Ruta nueva = new Ruta(origen, objetivo, calcularDistancia(a.getUbicacion(), b.getUbicacion()));
        grafo.addEdge(a, b, nueva);
        ventana.actualizarGrafo(grafo);
    }

    public void eliminarRuta(String origen, String destino){
        grafo.removeEdge(buscarNodoNombre(origen), buscarNodoNombre(destino));
        ventana.actualizarGrafo(grafo);
    }

    //=======================================
    //Gestion de Envios

    private Vector<Ruta> obtenerCamino(Entidad origen, Entidad destino){

        double e = 0.000001;
        Function<Ruta,Double> modificacionPeso = (edge) -> {
            return grafo.getEdgeWeight(edge) + e;
        };
        //se crea una funcion que modifica los pesos para que encuentre el camino con la menor distancia
        //y que en caso de un empate se elija el de menor puntos de control

        DijkstraShortestPath<Entidad,Ruta> buscador = new DijkstraShortestPath<>(
            new AsWeightedGraph<>(grafo, modificacionPeso,false,false)
        );

        GraphPath<Entidad,Ruta> camino = buscador.getPath(origen, destino);


        return new  Vector<Ruta>(camino.getEdgeList());
    }

    public Vector<Envio> getEnviosActivos(){
        return new Vector<>(enviosActivos.values());
    }

    public int nuevoEnvio( String origen, String destino){
        int ID;
        do{
            ID =(int) (Math.random()*(9999999 - 1000000 + 1 ) + 100000);
        }while(enviosActivos.containsKey(ID));

        Vector<Ruta> camino = obtenerCamino(buscarNodoNombre(origen), buscarNodoNombre(destino));
        
        if(camino == null){
            return -1;
        }

        Envio nuevo =  new Envio(ID, origen, destino,camino );

        buscarNodoNombre(origen).recibirEnvio(nuevo);
        enviosActivos.put(ID, nuevo);

        ventana.actualizarEnvios();
        return 0; //OK
    }

    public void avanzarEnvio(int ID){

        if(enviosActivos.get(ID).avanzar()){
            enviosActivos.remove(ID);
        }

    }

    public void cancelarEnvio(int ID){

        Envio cancelado = enviosActivos.remove(ID);

        if(cancelado.getEstado() == Envio.ESTADO.EN_VIA){
            cancelado.geRutaActual().cancelarTransporte(cancelado);

        } else {
            String nombre = cancelado.getUbicacion();
            buscarNodoNombre(nombre).eliminarEnvio(cancelado);
        }
        ventana.actualizarEnvios();
    }



    //=========================================
    //UI

    public Vector<String> getOpciones(){

        Set<Entidad> objetivos = grafo.vertexSet();
        Vector<String> opciones = new Vector<>();
        for(Entidad e : objetivos){
            opciones.add(e.getNombre());
        }
        return opciones;

    }
    
    //=================================
    // MODELOS DE UI

    public DefaultTableModel getTablaEnvios(DefaultTableModel modelo){
        modelo.setRowCount(0);
        for(Envio e : getEnviosActivos()){
            Object[] fila = {
                e.getID(),
                e.getOrigen(),
                e.getDestino(),
                e.getTiempoEspera()
            };
            modelo.addRow(fila);
        }
        
        return modelo;

        

    }



    //getters

    public Graph<Entidad, Ruta> getGrafo() {
        return grafo;
    }

    private void preCargar(){
        agregarNodo("A", new int[]{-2,5}, "Senal de humo", "MINORISTA");
        agregarNodo("B", new int[]{10,5}, "Senal de humo","ALMACEN");
        agregarNodo("C", new int[]{2,6}, "Senal de humo","PROVEEDOR");
        agregarNodo("D", new int[]{-2,3}, "Senal de humo","ALMACEN");
        agregarNodo("E", new int[]{6,12}, "Senal de humo","DISTRIBUIDOR");

        agregarRuta("A", "B");
        agregarRuta("E", "B");
        agregarRuta("D", "B");
        agregarRuta("A", "D");
        agregarRuta("A", "C");
        agregarRuta("C", "E");

        nuevoEnvio("A", "E");

    }


    public static void main(String[] args) {
        //Inicializar la app
        CadenaSuministros app = new CadenaSuministros();

    }
    
}
