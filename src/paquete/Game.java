/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

package paquete;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import javax.swing.*;
import paquete.Json3;

/**
 *
 * @author pikip
 */
public final class Game extends javax.swing.JPanel {
    private int indiceActual; 
    public  int indiceGame;
    public  int indiceHome;
    private String key;
    private final String[] imagenes;
    private javax.swing.JLabel[] puntosCarrusel; // Array de JLabel para los puntos del carrusel 
    //private javax.swing.JLabel BtnStart;
    
    
   
    // Crear instancias de Dimension
    private final Dimension dimensionF = new Dimension(22, 35); 
    private final Dimension dimensionMaxF = new Dimension(32, 45);
    private final Dimension dimensionP = new Dimension (15,15);
    private final Dimension dimensionMaxP = new Dimension (20,20);
    private final Dimension dimensionB = new Dimension (253,44);
    private final Dimension dimensionMaxB = new Dimension (273,64);
    
   
  
    public Game(int indiceGame, int indiceHome)  {
        initComponents();   
        this.indiceGame = indiceGame;
        this.indiceHome = indiceHome;
        
        imagenes = obtenerImagenesPorCategoria();
        puntosCarrusel = new javax.swing.JLabel[] {Punto1, Punto2, Punto3, Punto4, Punto5};
        actualizarPuntosCarrusel(indiceActual); // Actualiza los puntos del carrusel 
        actualizarImagen();    
        actualizarTitulo(); // Actualiza el título en JLabel  
        actualizarDescripcion(); //Actualiza la descripcion en el JLabel
      
    

        // Agregar MouseListener al BOTÓN COMENZAR
        BtnStart.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) { 
//                System.out.println("Botón Comenzar pulsado");  // Verifica si el evento se dispara
                try {
                    
                    Object rutaObj = Main.instance.imprimirCampo("ruta",String.valueOf(indiceHome), String.valueOf(indiceGame));
                    
//                    // Ruta del archivo JSON
//                    String jsonPath = "src/bbdd/BaseDeDatos.json";  // Asegúrate de que esta ruta es correcta
////                    System.out.println("Ruta del archivo JSON: " + jsonPath);  // Verifica la ruta del archivo JSON
//
//                    // Cargar JSON
//                    Json3.cargarJSON(jsonPath);
////                    System.out.println("JSON cargado exitosamente");  // Verifica que el JSON se cargue correctamente
//
//                    // Obtener la ruta del ejecutable desde el JSON
//                    Object rutaObj = Json3.acceder("grados", String.valueOf(indiceHome), "juegos", String.valueOf(indiceGame), "ruta");
////                    System.out.println("Ruta del ejecutable: " + rutaObj);  // Verifica si la ruta se obtiene correctamente desde el JSON

                    if (rutaObj instanceof String) {
                        String rutaEjecutable = (String) rutaObj;
//                        System.out.println("Ruta Ejecutable: " + rutaEjecutable);  // Verifica que la ruta del ejecutable sea válida
                         // Verificación si el archivo existe

                        // Verificar si el archivo existe antes de ejecutarlo
                        File archivo = new File(rutaEjecutable);
                        if (archivo.exists()) {
//                            System.out.println("El archivo existe, procediendo a ejecutar el juego.");  // Verifica si el archivo existe
                            // Ejecutar el juego
                            ProcessBuilder pb = new ProcessBuilder(rutaEjecutable);
                            pb.start();
                        } else {
//                            System.out.println("El archivo no existe en la ruta: " + rutaEjecutable);  // Muestra un mensaje si el archivo no existe
                            JOptionPane.showMessageDialog(null, "El archivo no existe en la ruta: " + rutaEjecutable, "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
//                        System.out.println("No se encontró la ruta del juego en el JSON");  // Muestra un mensaje si no se encuentra la ruta
                        JOptionPane.showMessageDialog(null, "No se encontró la ruta del juego en el JSON", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al ejecutar el juego: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Utilidades.SetImageLabel(BtnStart, "src/images/pics/Comenzar.png", dimensionMaxB, true); 
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Utilidades.SetImageLabel(BtnStart, "src/images/pics/Comenzar.png", dimensionB, true); 
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Utilidades.SetImageLabel(BtnStart, "src/images/pics/Comenzar.png", dimensionB, true); 
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Utilidades.SetImageLabel(BtnStart, "src/images/pics/Comenzar.png", dimensionMaxB, true); 
            }
        });
        
        // Agregar MouseListener a las FLECHAS
        
        //**Flecha IZQUIERDA**
        FlechaI.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagenAnterior();
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Utilidades.SetImageLabel(FlechaI, "src/images/pics/Flecha izquierda.png", dimensionMaxF, true);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Utilidades.SetImageLabel(FlechaI, "src/images/pics/Flecha izquierda.png", dimensionF, true);
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Utilidades.SetImageLabel(FlechaI, "src/images/pics/Flecha izquierda.png", dimensionF, true);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Utilidades.SetImageLabel(FlechaI, "src/images/pics/Flecha izquierda.png", dimensionMaxF, true);
            }
        });
        
        //**Flecha DERECHA**
        FlechaD.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                siguienteImagen();
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Utilidades.SetImageLabel(FlechaD, "src/images/pics/Flecha derecha.png", dimensionMaxF, true);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Utilidades.SetImageLabel(FlechaD, "src/images/pics/Flecha derecha.png", dimensionF, true);
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Utilidades.SetImageLabel(FlechaD, "src/images/pics/Flecha derecha.png", dimensionF, true);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Utilidades.SetImageLabel(FlechaD, "src/images/pics/Flecha derecha.png", dimensionMaxF, true);
            }
        });

   
        // Agregar MouseListener a los PUNTOS DEL CARRUSEL
        for (int i = 0; i < puntosCarrusel.length; i++) {
            // Itera a través de cada punto del carrusel
            final int indiceActualLocal = i;
            // Guarda el índice actual local en una variable final para usarlo dentro del MouseListener
            
        //EVENTOS:    
        puntosCarrusel[i].addMouseListener(new java.awt.event.MouseAdapter() {
        // Agrega un MouseListener a cada punto del carrusel
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Cuando se hace clic en un punto del carrusel
                indiceActual = indiceActualLocal;
                // Actualiza el índice actual con el índice local
                actualizarImagen();
                // Llama al método para actualizar la imagen
                actualizarPuntosCarrusel(indiceActual);
                // Llama al método para actualizar los puntos del carrusel
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Cuando el mouse entra en un punto del carrusel
                Utilidades.SetImageLabel(puntosCarrusel[indiceActualLocal], "src/images/pics/PuntoCarruselFilled.png", dimensionMaxP, true);
                // Cambia la imagen del punto a "PuntoCarruselFilled.png" con el tamaño máximo
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Cuando el mouse sale de un punto del carrusel
                if (indiceActual != indiceActualLocal) {
                    // Si el índice actual no es igual al índice local
                    Utilidades.SetImageLabel(puntosCarrusel[indiceActualLocal], "src/images/pics/PuntoCarruselEmpty.png", dimensionP, true);
                    // Cambia la imagen del punto a "PuntoCarruselEmpty.png" con el tamaño normal
                }
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                // Cuando se presiona un punto del carrusel
                Utilidades.SetImageLabel(puntosCarrusel[indiceActualLocal], "src/images/pics/PuntoCarruselFilled.png", dimensionP, true);
                // Cambia la imagen del punto a "PuntoCarruselFilled.png" con el tamaño normal
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                // Cuando se suelta un punto del carrusel
                if (indiceActual != indiceActualLocal) {
                    // Si el índice actual no es igual al índice local
                    Utilidades.SetImageLabel(puntosCarrusel[indiceActualLocal], "src/images/pics/PuntoCarruselEmpty.png", dimensionMaxP, true);
                    // Cambia la imagen del punto a "PuntoCarruselEmpty.png" con el tamaño máximo
                }
            }    
        
        });
      
    }
        
}
    //actualizar TITULO del juego *****     
    private void actualizarTitulo() {
        if (Main.instance != null) { //verifica si main está instanciado
           Object tituloJuego = Main.instance.imprimirCampo(String.valueOf(indiceHome),String.valueOf(indiceGame),"titulo");
           TxtGameTitulo.setText(tituloJuego.toString()); //agregar titulo al jlabel
        } else {
        System.out.println("Error: Main.instance no está inicialidado.");
    
        }
    }
    
     //actualizar DESCRIPCION del juego *****     
    private void actualizarDescripcion() {
        if (Main.instance != null) { //verifica si main está instanciado
           Object descripcion = Main.instance.imprimirCampo(String.valueOf(indiceHome),String.valueOf(indiceGame),"descripcion");
           TxtGameTxt.setText(descripcion.toString()); //agregar descripcion al jlabel
        } else {
        System.out.println("Error: Main.instance no está inicialidado.");
    
        }
    }
   
    //actualizar IMAGEN del juego *****
    private String[] obtenerImagenesPorCategoria() {
      String[] array = new String[] {};
      // Llamamos a la función imprimirImagen desde Main para obtener la categoría
        Object categoria = Main.instance.imprimirCampo(String.valueOf(indiceHome),String.valueOf(indiceGame),"imagen");

        if (categoria != null) {
            // Asignamos el valor de la categoría obtenida a 'key'
            key = categoria.toString();  // Convertimos la categoría a String

            // Verificamos si la ruta de las imágenes existe
            File dir = new File("src/images/pics/");
            try {
                if (!dir.exists() || !dir.isDirectory()) {
                    throw new FileNotFoundException("La ruta especificada no existe o no es un directorio válido.");
                }

                // Asignamos los valores para el array usando el key obtenido
                array = Utilidades.CreateStringList("src/images/pics/", key, "png", 5).toArray(String[]::new);

                // Comprobamos si el array tiene imágenes
                if (array.length == 0) {
                    System.out.println("No se encontraron imágenes para la categoría: " + key);
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("No se encontró una categoría válida.");
        }

        return array;
    }
    
    private void actualizarImagen() {
        Utilidades.SetImageLabel(ImgCarr1, imagenes[indiceActual] , new Dimension (640, 480), true);
    }
    
    
    
    //actualiza los puntos del carrusel según el índice actual
    private void actualizarPuntosCarrusel(int indiceActual) {
       
        // Recorre los JLabel (los puntos del carrusel)
        for (int i = 0; i < puntosCarrusel.length; i++) {

            // Comprueba si el índice actual coincide con el índice del punto en la lista
            if (i == indiceActual ) {

                // Si el índice actual es igual al índice del punto, "punto relleno"
                //puntosCarrusel[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("src/images/pics/PuntoCarruselFilled.png")));
                Utilidades.SetImageLabel(puntosCarrusel[i], "src/images/pics/PuntoCarruselFilled.png", dimensionP, true);

            } else {
                // Si no es el índice actual, "punto vacío"
                //puntosCarrusel[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("src/images/pics/PuntoCarruselEmpty.png")));
                Utilidades.SetImageLabel(puntosCarrusel[i], "src/images/pics/PuntoCarruselEmpty.png", dimensionP, true);
            }
        }
    }
    
    
    private void siguienteImagen() {
        if (indiceActual < imagenes.length - 1) {
            indiceActual++;                    
        }
        else {
            indiceActual = 0;
        }
        actualizarImagen(); //Actualiza las imágenes del carrusel
        actualizarPuntosCarrusel(indiceActual); // Actualiza los puntos del carrusel
    }

    private void imagenAnterior() {
        if (indiceActual > 0) {
            indiceActual--;          
        }
        else {
            indiceActual = imagenes.length - 1;
        }
        actualizarImagen(); //Actualiza las imágenes del carrusel
        actualizarPuntosCarrusel(indiceActual); // Actualiza los puntos del carrusel
    }

    
       
    
    
   
        
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnStart = new javax.swing.JLabel();
        TxtGameTitulo = new javax.swing.JLabel();
        TxtGameTxt = new javax.swing.JLabel();
        Punto1 = new javax.swing.JLabel();
        Punto2 = new javax.swing.JLabel();
        Punto3 = new javax.swing.JLabel();
        Punto4 = new javax.swing.JLabel();
        Punto5 = new javax.swing.JLabel();
        Cursor = new javax.swing.JLabel();
        ImgCarr1 = new javax.swing.JLabel();
        FlechaD = new javax.swing.JLabel();
        FlechaI = new javax.swing.JLabel();
        ImgFondoCarrusel = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1550, 880));
        setMinimumSize(new java.awt.Dimension(1550, 880));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1550, 880));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnStart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/Comenzar.png"))); // NOI18N
        BtnStart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnStart.setMaximumSize(new java.awt.Dimension(320, 90));
        BtnStart.setMinimumSize(new java.awt.Dimension(320, 90));
        BtnStart.setPreferredSize(new java.awt.Dimension(320, 90));
        BtnStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnStartMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnStartMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BtnStartMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                BtnStartMouseReleased(evt);
            }
        });
        add(BtnStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 730, -1, -1));

        TxtGameTitulo.setFont(new java.awt.Font("Raleway", 1, 18)); // NOI18N
        TxtGameTitulo.setForeground(new java.awt.Color(247, 247, 247));
        TxtGameTitulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TxtGameTitulo.setText("Embarque o desembarque en helicoptero");
        TxtGameTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        add(TxtGameTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 533, 451, 31));

        TxtGameTxt.setFont(new java.awt.Font("Raleway", 0, 12)); // NOI18N
        TxtGameTxt.setForeground(new java.awt.Color(247, 247, 247));
        TxtGameTxt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TxtGameTxt.setText("<html>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. <br>  Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. <br>  Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. <br>  Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est </html>");
        TxtGameTxt.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        TxtGameTxt.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        TxtGameTxt.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        add(TxtGameTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 624, 691, 163));

        Punto1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Punto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/PuntoCarruselFilled.png"))); // NOI18N
        Punto1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Punto1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(Punto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(692, 493, 30, 26));

        Punto2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Punto2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/PuntoCarruselEmpty.png"))); // NOI18N
        Punto2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Punto2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Punto2.setMaximumSize(new java.awt.Dimension(15, 15));
        Punto2.setMinimumSize(new java.awt.Dimension(15, 15));
        add(Punto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(728, 493, 30, 26));

        Punto3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Punto3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/PuntoCarruselEmpty.png"))); // NOI18N
        Punto3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Punto3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Punto3.setMaximumSize(new java.awt.Dimension(15, 15));
        Punto3.setMinimumSize(new java.awt.Dimension(15, 15));
        Punto3.setPreferredSize(new java.awt.Dimension(15, 15));
        add(Punto3, new org.netbeans.lib.awtextra.AbsoluteConstraints(764, 493, 30, 26));

        Punto4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Punto4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/PuntoCarruselEmpty.png"))); // NOI18N
        Punto4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Punto4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Punto4.setMaximumSize(new java.awt.Dimension(15, 15));
        Punto4.setMinimumSize(new java.awt.Dimension(15, 15));
        Punto4.setPreferredSize(new java.awt.Dimension(15, 15));
        add(Punto4, new org.netbeans.lib.awtextra.AbsoluteConstraints(798, 493, 30, 26));

        Punto5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Punto5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/PuntoCarruselEmpty.png"))); // NOI18N
        Punto5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Punto5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Punto5.setMaximumSize(new java.awt.Dimension(15, 15));
        Punto5.setMinimumSize(new java.awt.Dimension(15, 15));
        Punto5.setPreferredSize(new java.awt.Dimension(15, 15));
        add(Punto5, new org.netbeans.lib.awtextra.AbsoluteConstraints(834, 493, 30, 26));

        Cursor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/Barrita aislada descripción.png"))); // NOI18N
        add(Cursor, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 533, -1, 32));

        ImgCarr1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ImgCarr1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/Embarque0.png"))); // NOI18N
        ImgCarr1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ImgCarr1.setMaximumSize(new java.awt.Dimension(780, 463));
        ImgCarr1.setMinimumSize(new java.awt.Dimension(780, 463));
        ImgCarr1.setPreferredSize(new java.awt.Dimension(780, 463));
        add(ImgCarr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, -1, -1));

        FlechaD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FlechaD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/Flecha derecha.png"))); // NOI18N
        FlechaD.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        FlechaD.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FlechaD.setMaximumSize(new java.awt.Dimension(32, 45));
        FlechaD.setMinimumSize(new java.awt.Dimension(32, 45));
        FlechaD.setPreferredSize(new java.awt.Dimension(32, 45));
        FlechaD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FlechaDMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                FlechaDMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                FlechaDMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                FlechaDMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                FlechaDMouseReleased(evt);
            }
        });
        add(FlechaD, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 240, -1, -1));

        FlechaI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FlechaI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/Flecha izquierda.png"))); // NOI18N
        FlechaI.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        FlechaI.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FlechaI.setMaximumSize(new java.awt.Dimension(32, 45));
        FlechaI.setMinimumSize(new java.awt.Dimension(32, 45));
        FlechaI.setPreferredSize(new java.awt.Dimension(32, 45));
        FlechaI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FlechaIMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                FlechaIMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                FlechaIMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                FlechaIMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                FlechaIMouseReleased(evt);
            }
        });
        add(FlechaI, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, -1, -1));

        ImgFondoCarrusel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ImgFondoCarrusel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/Cuadrado fondo enfocado.png"))); // NOI18N
        add(ImgFondoCarrusel, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    
    //EVENTOS FLECHAS
    private void FlechaDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FlechaDMouseEntered
//        Utilidades.SetImageLabel(FlechaD, "src/images/pics/Flecha derecha.png", dimensionMaxF, true);   
    }//GEN-LAST:event_FlechaDMouseEntered

    private void FlechaDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FlechaDMouseExited
//        Utilidades.SetImageLabel(FlechaD, "src/images/pics/Flecha derecha.png", dimensionF, true); 
    }//GEN-LAST:event_FlechaDMouseExited

    private void FlechaIMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FlechaIMouseEntered
//        Utilidades.SetImageLabel(FlechaI, "src/images/pics/Flecha izquierda.png", dimensionMaxF, true);  
    }//GEN-LAST:event_FlechaIMouseEntered

    private void FlechaIMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FlechaIMouseExited
//        Utilidades.SetImageLabel(FlechaI, "src/images/pics/Flecha izquierda.png", dimensionF, true); 
    }//GEN-LAST:event_FlechaIMouseExited

    private void FlechaDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FlechaDMouseClicked
//        siguienteImagen();
    }//GEN-LAST:event_FlechaDMouseClicked

    private void FlechaIMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FlechaIMouseClicked
//        imagenAnterior();
    }//GEN-LAST:event_FlechaIMouseClicked

    private void FlechaDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FlechaDMousePressed
//        Utilidades.SetImageLabel(FlechaD, "src/images/pics/Flecha derecha.png", dimensionF, true); 
    }//GEN-LAST:event_FlechaDMousePressed

    private void FlechaDMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FlechaDMouseReleased
//        Utilidades.SetImageLabel(FlechaD, "src/images/pics/Flecha derecha.png", dimensionMaxF, true); 
    }//GEN-LAST:event_FlechaDMouseReleased

    private void FlechaIMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FlechaIMousePressed
//        Utilidades.SetImageLabel(FlechaI, "src/images/pics/Flecha izquierda.png", dimensionF, true);  
    }//GEN-LAST:event_FlechaIMousePressed

    private void FlechaIMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FlechaIMouseReleased
//        Utilidades.SetImageLabel(FlechaI, "src/images/pics/Flecha izquierda.png", dimensionMaxF, true);
    }//GEN-LAST:event_FlechaIMouseReleased
        
    
//    //EVENTOS BOTÓN COMENZAR DESDE DESIGN
//    
    private void BtnStartMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnStartMouseEntered
//        Utilidades.SetImageLabel(BtnStart, "src/images/pics/Comenzar.png", dimensionMaxB, true); 
    }//GEN-LAST:event_BtnStartMouseEntered

    private void BtnStartMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnStartMouseExited
//        Utilidades.SetImageLabel(BtnStart, "src/images/pics/Comenzar.png", dimensionB, true); 
    }//GEN-LAST:event_BtnStartMouseExited

    private void BtnStartMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnStartMousePressed
//        Utilidades.SetImageLabel(BtnStart, "src/images/pics/Comenzar.png", dimensionB, true);
    }//GEN-LAST:event_BtnStartMousePressed

    private void BtnStartMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnStartMouseReleased
//        Utilidades.SetImageLabel(BtnStart, "src/images/pics/Comenzar.png", dimensionMaxB, true); 
    }//GEN-LAST:event_BtnStartMouseReleased

           
              
              

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BtnStart;
    private javax.swing.JLabel Cursor;
    private javax.swing.JLabel FlechaD;
    private javax.swing.JLabel FlechaI;
    private javax.swing.JLabel ImgCarr1;
    private javax.swing.JLabel ImgFondoCarrusel;
    private javax.swing.JLabel Punto1;
    private javax.swing.JLabel Punto2;
    private javax.swing.JLabel Punto3;
    private javax.swing.JLabel Punto4;
    private javax.swing.JLabel Punto5;
    private javax.swing.JLabel TxtGameTitulo;
    private javax.swing.JLabel TxtGameTxt;
    // End of variables declaration//GEN-END:variables

    

}    

    

    

 
    


