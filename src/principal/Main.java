package principal;

import ui.MenuConsola;

/**
 * Clase principal que contiene el punto de entrada del Sistema de Control de Versiones.
 * Inicia la aplicacion y muestra el menu de consola al usuario.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see MenuConsola
 */
public class Main {
    
    /**
     * Metodo principal que inicia el sistema de control de versiones.
     * Muestra mensaje de bienvenida y crea una instancia del menu de consola.
     * 
     * @param args Argumentos de linea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   BIENVENIDO AL SISTEMA DE");
        System.out.println("     CONTROL DE VERSIONES");
        System.out.println("        (Git Local)");
        System.out.println("========================================");
        System.out.println("Inicializa repositorios, agrega archivos,");
        System.out.println("crea commits, maneja ramas y compara versiones.");
        System.out.println("========================================\n");
        
        MenuConsola menu = new MenuConsola();
        menu.iniciar();
    }
    
}//fin de la clase