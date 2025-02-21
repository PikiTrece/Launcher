/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete;


// Importamos las clases necesarias para trabajar con JSON y manejar excepciones
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

/**
 *
 * @author User
 */
public class Json3 {
    // Variable estática para almacenar el JSON cargado en memoria
    private static JSONObject jsonBase;

    /**
     * Método para cargar el JSON en memoria desde un archivo.
     * @param filePath Ruta del archivo JSON
     * @throws IOException Si ocurre un error al leer el archivo
     * @throws org.json.JSONException
     */
    public static void cargarJSON(String filePath) throws IOException, JSONException {
        // Lee el contenido del archivo JSON como una cadena de texto
        String content = Utilidades.readFile(filePath);
        
        // Convierte el contenido en un objeto JSON y lo guarda en jsonBase
        jsonBase = new JSONObject(content);
    }

    /**
     * Método para acceder dinámicamente a cualquier nivel del JSON.
     * @param claves Lista de claves que definen la ruta en la jerarquía JSON
     * @return Devuelve el valor encontrado o un mensaje de error si no existe
     */
    public static Object acceder(String... claves) {
        try {
             Object actual = jsonBase; // Comenzamos desde la raíz del JSON

             // Recorremos todas las claves para acceder al dato deseado
             for (String clave : claves) {
                 if (actual instanceof JSONObject) {
                     JSONObject obj = (JSONObject) actual;
                     if (!obj.has(clave)) {
                         return "Error: Clave '" + clave + "' no encontrada";
                     }
                     actual = obj.get(clave); // Avanzamos al siguiente nivel del JSON
                 } 
                 else if (actual instanceof JSONArray) {
                     try {
                         int index = Integer.parseInt(clave); // Convertimos clave a índice
                         JSONArray array = (JSONArray) actual;
                         if (index < 0 || index >= array.length()) {
                             return "Error: Índice fuera de rango '" + clave + "'";
                         }
                         actual = array.get(index); // Accedemos al elemento del array
                     } catch (NumberFormatException e) {
                         return "Error: Se esperaba un índice numérico en '" + clave + "'";
                     }
                 } 
                 else {
                     return "Error: No se puede acceder más allá de '" + clave + "'";
                 }
             }

             return actual; // Retorna el valor final encontrado (puede ser String, int, etc.)
         } catch (JSONException e) {
             return "Error de JSON: " + e.getMessage();
         }               
    }
}


