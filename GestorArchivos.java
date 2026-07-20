import java.io.*;

public class GestorArchivos {

    // Método genérico para guardar CUALQUIER objeto o lista
    public static boolean guardarDatos(Object datos, String rutaArchivo) {
        try {
            FileOutputStream fos = new FileOutputStream(rutaArchivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeObject(datos); 
            
            oos.close();
            fos.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo " + rutaArchivo + ": " + e.getMessage());
            return false;
        }
    }

    // Método genérico para leer CUALQUIER objeto o lista
    public static Object cargarDatos(String rutaArchivo) {
        try {
            FileInputStream fis = new FileInputStream(rutaArchivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            Object datos = ois.readObject();
            
            ois.close();
            fis.close();
            return datos;
        } catch (IOException | ClassNotFoundException e) {
            // Si el archivo no existe o está vacío, retornamos null
            return null; 
        }
    }
}