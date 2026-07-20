import java.util.HashMap;
import java.util.Set;
import java.util.Vector;
import java.util.function.Function;

import javax.swing.table.DefaultTableModel;

import org.jgrapht.*;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.AsWeightedGraph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;


public class RedEnvios {
    private Graph<Entidad,Ruta> grafo;
    private HashMap<Integer,Envio> enviosActivos;

    

    public RedEnvios(){
        grafo = new SimpleDirectedWeightedGraph<>(Ruta.class);
        enviosActivos = new HashMap<>();
    }

    //=====================================================
    //Gestion de Entidades (Nodos en grafo)
    public void agregarNodo(String nombre,int[] ubicacion ,String contacto, String rol){
        Entidad nuevo = new Entidad(nombre,ubicacion,contacto,rol);
        grafo.addVertex(nuevo);
        guardarTodo();

    }

    public Entidad buscarNodoNombre(String Nombre){
        Entidad encontrado = null;

        for(Entidad e : grafo.vertexSet()){
            if(e.getNombre().equals(Nombre)) return e;
        }

        return encontrado;
    }

    public int eliminarNodo(String Nombre){

        Entidad aEliminar = buscarNodoNombre(Nombre);
        if(aEliminar.tieneEnvios()){
            return -1;
        }

        grafo.removeVertex(aEliminar);
        guardarTodo();
        return 0;
    }



    //====================================================
    //Gestion de Rutas (aristas del grafo)

    private double calcularDistancia(int[] a,int[] b){
        double resultado = Math.sqrt(Math.pow((b[0]-a[0]), 2) + Math.pow((b[1] - a[1]),2) ); 
        return Math.round(resultado*1000.0)/1000.0;
    }

    public void agregarRuta(String origen, String objetivo){

        Entidad a  = buscarNodoNombre(origen);
        Entidad b = buscarNodoNombre(objetivo);

        Ruta nueva = new Ruta(origen, objetivo, calcularDistancia(a.getUbicacion(), b.getUbicacion()));
        grafo.addEdge(a, b, nueva);
        guardarTodo();
        
    }

    public int eliminarRuta(String origen, String destino){

        Ruta aEliminar = grafo.getEdge(buscarNodoNombre(origen), buscarNodoNombre(destino));
        if(aEliminar.tieneEnvios()){
            return -1;
        }
        grafo.removeEdge(aEliminar);
        guardarTodo();
        return 0;
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
        if(camino == null){
            return null;
        }


        return new  Vector<Ruta>(camino.getEdgeList());
    }

    public Vector<Envio> getEnviosActivos(){
        return new Vector<>(enviosActivos.values());
    }

    public int nuevoEnvio( String origen, String destino, String contenido){
        int ID;
        do{
            ID =(int) (Math.random()*(9999999 - 1000000 + 1 ) + 100000);
        }while(enviosActivos.containsKey(ID));

        Vector<Ruta> camino = obtenerCamino(buscarNodoNombre(origen), buscarNodoNombre(destino));
        
        if(camino == null){
            return -1;
        }

        Envio nuevo =  new Envio(ID, contenido, origen, destino,camino );

        buscarNodoNombre(origen).recibirEnvio(nuevo);
        enviosActivos.put(ID, nuevo);

        guardarTodo();
        return 0; //OK
    }

    public void avanzarEnvio(int ID){
        Envio avanzar = enviosActivos.get(ID);

        if(avanzar.getEstado() == Envio.ESTADO.EN_ESPERA){
            Entidad almacen = buscarNodoNombre(avanzar.getUbicacion());
            almacen.liberarEnvio(avanzar);
            Ruta via = avanzar.geRutaActual();
            via.comenzarTransporte(avanzar);

        }else{
            Ruta via = avanzar.geRutaActual();
            via.finalizarTransporte(avanzar);
            buscarNodoNombre(via.getDestino()).recibirEnvio(avanzar);
            if(avanzar.fueEntregado()){
                enviosActivos.remove(avanzar.getID());
            }

        }
        guardarTodo();
    }

    public void cancelarEnvio(int ID){

        Envio cancelado = enviosActivos.remove(ID);

        if(cancelado.getEstado() == Envio.ESTADO.EN_VIA){
            cancelado.geRutaActual().cancelarTransporte(cancelado);

        } else {
            String nombre = cancelado.getUbicacion();
            buscarNodoNombre(nombre).eliminarEnvio(cancelado);
        }
        guardarTodo();
        
    }

    public int cambiarDestino(int ID, String destino){
        Envio actualizar = enviosActivos.get(ID);
        if(actualizar.getEstado() == Envio.ESTADO.EN_VIA){
            return -1;//EL DESTINO NO SE PUEDE CAMBIAR CUANDO ESTA EN VIA
        }
        String nombre = actualizar.getUbicacion();
        if(nombre.equals(destino)){
            return -2;//EL DESTINO NUEVO NO PUEDE SER LA UBICACION ACTUAL
        }

        Vector<Ruta> camino = obtenerCamino(buscarNodoNombre(nombre), buscarNodoNombre(destino));

        if(camino == null){
            return -3;//NO HAY CAMINO ENTRE LA UBICACION Y EL DESTINO
        }

        actualizar.nuevoDestino(destino, camino);
        guardarTodo();
        return 0;
    }

    public void cargarTodo(){
        Object datosGrafo = GestorArchivos.cargarDatos("Grafo_Rutas.dat");
        if(datosGrafo != null){
            grafo = (Graph<Entidad,Ruta>)datosGrafo;
        }
        Object datosEnvios = GestorArchivos.cargarDatos("Envios_Activos.dat");
        if(datosEnvios != null){
            enviosActivos = (HashMap<Integer,Envio>)datosEnvios;
        }
    
    }

    public void guardarTodo(){
        
        GestorArchivos.guardarDatos(enviosActivos, "Envios_Activos.dat");
        GestorArchivos.guardarDatos(grafo, "Grafo_Rutas.dat");
    }
    //getters

    public Graph<Entidad, Ruta> getGrafo() {
        return grafo;
    }

    public Ruta getRuta(String origen, String destino){
        return grafo.getEdge(buscarNodoNombre(origen), buscarNodoNombre(destino));

    }
    
    public Entidad getEntidad(String nombre){
        return buscarNodoNombre(nombre);
    }
    
    public Set<Entidad> getEntidades(){
        return grafo.vertexSet();
    }
    
    
}
