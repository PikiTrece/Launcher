/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete;

import java.io.IOException;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import paquete.Utilidades;

/**
 *
 * @author pikip
 */
public class Json2 {

    public static void main(String[] args) throws IOException {
        buscarJuegoPorTitulo();
    }

    public static void buscarJuegoPorTitulo() throws IOException {
        // Leer JSON desde el archivo
        String filePath = "src/bbdd/BaseDeDatos.json";  // Ruta del archivo JSON
        String jsonContent = Utilidades.readFile(filePath);

        // Verificar si el contenido está vacío
        if (jsonContent == null || jsonContent.isEmpty()) {
            System.err.println("Error: El archivo JSON está vacío o no se pudo leer.");
            return;
        }

        try {
            // Convertir el contenido en un objeto JSON
            JSONObject jsonObject = new JSONObject(jsonContent);

            // Obtener el array de "grados"
            JSONArray gradosArray = jsonObject.getJSONArray("grados");

            // Solicitar al usuario que introduzca un título a buscar
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el título del juego que desea buscar: ");
            String tituloBuscado = scanner.nextLine().trim().toLowerCase();

            boolean encontrado = false;

            // Recorrer los grados
            for (int i = 0; i < gradosArray.length(); i++) {
                JSONObject grado = gradosArray.getJSONObject(i);
                JSONArray juegosArray = grado.getJSONArray("juegos");

                // Recorrer los juegos dentro del grado
                for (int j = 0; j < juegosArray.length(); j++) {
                    JSONObject juego = juegosArray.getJSONObject(j);
                    String titulo = juego.getString("titulo").trim().toLowerCase();
                   

                    // Comparar con el título buscado
                    if (titulo.equals(tituloBuscado)) {
                        System.out.println("\nJuego encontrado:");
                        System.out.println("Título: " + juego.getString("titulo"));
                        System.out.println("Grado: " + grado.getString("nombre"));
                        encontrado = true;
                    }
                }
            }

            if (!encontrado) {
                System.out.println("No se encontró un juego con el título ingresado.");
            }

        } catch (JSONException e) {
            System.err.println("Error al parsear JSON: " + e.getMessage());
        }
    }

   
}

