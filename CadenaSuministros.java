import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CadenaSuministros {
    private Map<Entidad,ArrayList<Entidad>> grafo;

    public CadenaSuministros(){
        grafo = new HashMap<>();
    }

    //=====================================================
    //Gestion de Entidades (Nodos en grafo)

    public int agregarNodo(String nombre,int[] ubicacion ,String contacto){

        Entidad nuevo = new Entidad(nombre, ubicacion, contacto);

        if(grafo.containsKey(nuevo)){

        }

        grafo.putIfAbsent(nuevo, null);

        return -1;

    }

    public static void main(String[] args) {
        UiLogIn prueba = new UiLogIn();
        prueba.setVisible(true);
    }
    
}
