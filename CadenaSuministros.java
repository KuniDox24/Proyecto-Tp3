
import org.jgrapht.*;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import com.mxgraph.swing.mxGraphComponent;

public class CadenaSuministros {
    private Graph<Entidad,Ruta> grafo;

    public CadenaSuministros(){
        grafo = new SimpleDirectedWeightedGraph<>(Ruta.class);
        preCargar();
    }

    //=====================================================
    //Gestion de Entidades (Nodos en grafo)

    public void agregarNodo(String nombre,int[] ubicacion ,String contacto){
        Entidad nuevo = new Entidad(nombre,ubicacion,contacto);
        
        grafo.addVertex(nuevo);

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

    }

    //====================================================
    //Gestion de Rutas (aristas del grafo)

    private double calcularDistancia(int[] a,int[] b){
        
        return Math.sqrt(Math.pow((b[0]-a[0]), 2) + Math.pow((b[1] - a[1]),2) ); 
    }

    public void agregarRuta(Entidad origen, Entidad objetivo){
        Ruta nueva = new Ruta(origen.getNombre(), null, calcularDistancia(origen.getUbicacion(), objetivo.getUbicacion()));
        grafo.addEdge(origen, objetivo, nueva);
    }


    //getters

    public Graph<Entidad, Ruta> getGrafo() {
        return grafo;
    }

    private void preCargar(){
        agregarNodo("A", new int[]{-2,5}, "Senal de humo");
        agregarNodo("B", new int[]{10,5}, "Senal de humo");
        agregarNodo("C", new int[]{2,6}, "Senal de humo");
        agregarNodo("D", new int[]{-2,3}, "Senal de humo");
        agregarNodo("E", new int[]{6,12}, "Senal de humo");

        agregarRuta(buscarNodoNombre("A"), buscarNodoNombre("B"));
        agregarRuta(buscarNodoNombre("A"), buscarNodoNombre("C"));
        agregarRuta(buscarNodoNombre("C"), buscarNodoNombre("B"));
        agregarRuta(buscarNodoNombre("D"), buscarNodoNombre("E"));
    }

    public static void main(String[] args) {
        
        CadenaSuministros app = new CadenaSuministros();

        UiVentanaPrincipal p2 = new UiVentanaPrincipal();

        JGraphXAdapter<Entidad,Ruta> adapter = new JGraphXAdapter<>(app.getGrafo());
        p2.setGrafo(app.getGrafo());
        app.agregarRuta(app.buscarNodoNombre("E"), app.buscarNodoNombre("D"));
        
        
        p2.setVisible(true);
    }
    
}
