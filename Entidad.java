import java.util.Vector;

public class Entidad {
    public enum ROLES {PROVEEDOR, DISTRIBUIDOR, ALMACEN, FABRICANTE, MINORISTA};
    
    private String nombre; //nombre de la entidad
    private Vector<String> historial; //historial de entradas y salidas  de envios
    private int[] ubicacion = {0,0}; //donde se ubica la entidad
    private String contacto; //contacto de la entidad
    private ROLES rol; //rol de esa entidad
    private Vector<Envio> enEspera; //envios en espera de revision

    @Override
    public String toString(){
        return nombre;
    }

    //constructores
    public Entidad(){
        nombre = "Nombre no Proporcionado.";
        historial = new Vector<>();
    }

    public Entidad(String _nombre, int[] _ubicacion, String _contacto, String _rol){
        ubicacion = _ubicacion;
        nombre = _nombre;
        contacto = _contacto;
        historial = new Vector<>();
        rol = ROLES.valueOf(_rol);
    }

    //getters
    public String getContacto() {
        return contacto;
    }
    public Vector<String> getHistorial() {
        return historial;
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
 
    public void recibirEnvio(Envio nuevo){
        enEspera.add(nuevo);
    }
    public void liberarEnvio(Envio porLiberar){
        enEspera.remove(porLiberar);

    }
}
    