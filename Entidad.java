import java.util.ArrayList;



public class Entidad {
    public enum ROLES {PROVEEDOR, DISTRIBUIDOR, ALMACEN, FABRICANTE, MINORISTA};
    
    private ArrayList<Paquete> inventario;
    private String nombre;
    private ArrayList<String> historial;
    private int[] ubicacion = {0,0};
    private String contacto;

    public Entidad(){
        inventario = new ArrayList<>();
        nombre = "Nombre no Proporcionado.";
        historial = new ArrayList<>();
    }

    public Entidad(String _nombre, int[] _ubicacion, String _contacto){
        ubicacion = _ubicacion;
        nombre = _nombre;
        contacto = _contacto;
        historial = new ArrayList<>();
        inventario = new ArrayList<>();
    }


    


} 