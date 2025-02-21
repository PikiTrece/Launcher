/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author pikip
 */
public class Json {
    public static void Json () throws IOException {
    
     //Leer JSON desde el archivo
        String filePath = "src/bbdd/BaseDeDatos.json";  // Ruta del archivo JSON
        String jsonContent = Utilidades.readFile(filePath);
        
//        System.out.println("Contenido del archivo JSON:");
//        System.out.println(jsonContent);
        
        //Verificar si el conteido está vacío
        if (jsonContent == null || jsonContent.isEmpty()) {
            System.err.println("Error: El archivo JSON está vacio o no se pudo leer.");
            return;
        }
        
        try {
          
            // Convertir el contenido en un objeto JSON
            JSONObject jsonObject = new JSONObject(jsonContent);  
            
            // Obtener el array de "grados"
            JSONArray gradosArray = jsonObject.getJSONArray("grados");
            
            // Recorrer los grados
            for (int i = 0; i < gradosArray.length(); i++) {
                JSONObject grado = gradosArray.getJSONObject (i);
                System.out.println("\n Grado " + (i + 1) + ": " + grado.getString("nombre"));
                
                //Acceder al array "juegos" dentro del grado
                JSONArray juegosArray = grado.getJSONArray("juegos");
                
                //Recorrer los juegos dentro del grado
                for (int j = 0; j < juegosArray.length(); j++) {
                    JSONObject juego = juegosArray.getJSONObject (j);
                    System.out.println("Titulo " + juego.getString("titulo"));
                }                             
            }
           
            
        } catch (JSONException e) {
             System.err.println("Error al parsear JSON " + e.getMessage());
        } 

    }

    
}
