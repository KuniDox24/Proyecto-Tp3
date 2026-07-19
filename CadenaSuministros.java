import org.jgrapht.*;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class CadenaSuministros implements InterfazControlador {
    private Graph<Entidad,Ruta> grafo;
    private UIVentanaPrincipal ventana;

    public CadenaSuministros(){
        grafo = new SimpleDirectedWeightedGraph<>(Ruta.class);
        ventana = new UIVentanaPrincipal(this);
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

    }

    //=======================================
    //Gestion de Envios

    public void nuevoEnvio(){
        //retirar los productos de la entidad
        //agregarlos al objeto envio
        //actualizarlo en la tabla de envios
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


    }


    public static void main(String[] args) {
        //Inicializar la app
        CadenaSuministros app = new CadenaSuministros();

    }
    
}
