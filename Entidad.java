import java.util.ArrayList;

public class Entidad {
    //que tipo de entidad es
    public enum ROLES {PROVEEDOR, DISTRIBUIDOR, ALMACEN, FABRICANTE, MINORISTA};
    
    private ArrayList<Paquete> inventario; //paquetes a su disposicion
    private String nombre; //nombre de la entidad
    private ArrayList<String> historial; //historial de entradas y salidas
    private int[] ubicacion = {0,0}; //donde se ubica la entidad
    private String contacto; //contacto de la entidad
    private ROLES rol;

    @Override
    public String toString(){
        return nombre;
    }

    //constructores
    public Entidad(){
        inventario = new ArrayList<>();
        nombre = "Nombre no Proporcionado.";
        historial = new ArrayList<>();
    }

    public Entidad(String _nombre, int[] _ubicacion, String _contacto, String _rol){
        ubicacion = _ubicacion;
        nombre = _nombre;
        contacto = _contacto;
        historial = new ArrayList<>();
        inventario = new ArrayList<>();
        rol = ROLES.valueOf(_rol);
    }

    //getters
    public String getContacto() {
        return contacto;
    }
    public ArrayList<String> getHistorial() {
        return historial;
    }
    public ArrayList<Paquete> getInventario() {
        return inventario;
    }
    public String getNombre() {
        return nombre;
    }
    public int[] getUbicacion() {
        return ubicacion;
    }
    public String getRol(){
        return rol.toString();
    }


    //metodos
    public void recibirPaquete(Paquete recibido){
        //primero se agrega el inventario
        inventario.add(recibido);

        //luego se agrega al historial
        historial.add("Recibido: " + recibido.getID() + " ( Contenido: "+ recibido.getContenido() +" x"+recibido.getCatidad()+" )");
    }


} 