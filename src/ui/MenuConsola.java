package ui;

import service.RepositorioService;
import service.VersionService;

import java.util.Scanner;

/**
 * Clase que implementa la interfaz de usuario por consola para el Sistema de Control de Versiones.
 * Gestiona la interaccion con el usuario, muestra los menus y procesa las opciones.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see RepositorioService
 * @see VersionService
 */
public class MenuConsola {
    
    // ==================== ATRIBUTOS ====================
    
    private Scanner scanner;
    private RepositorioService repositorioService;
    private VersionService versionService;
    
    /**
     * Constructor del menu de consola.
     */
    public MenuConsola() {
        this.scanner = new Scanner(System.in);
        this.repositorioService = new RepositorioService();
        this.versionService = null;
    }
    
    /**
     * Inicia el bucle principal del menu.
     */
    public void iniciar() {
        boolean salir = false;
        
        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1 -> repositorioService.init(scanner);
                case 2 -> repositorioService.add(scanner);
                case 3 -> repositorioService.commit(scanner);
                case 4 -> repositorioService.log();
                case 5 -> repositorioService.branch(scanner);
                case 6 -> repositorioService.checkout(scanner);
                case 7 -> repositorioService.listarRamas();
                case 8 -> repositorioService.status();
                case 9 -> repositorioService.diff(scanner);
                case 10 -> {
                    System.out.println("\n¡Gracias por usar el Sistema de Control de Versiones!");
                    System.out.println("¡Hasta pronto!");
                    salir = true;
                }
                default -> System.out.println("Opcion no valida");
            }
            
            // Inicializar VersionService si hay repositorio
            if (repositorioService.hayRepositorio() && versionService == null) {
                versionService = new VersionService(
                    repositorioService.getRepositorio()
                );
            }
            
            if (!salir) {
                pausa();
            }
        }
        
        scanner.close();
    }
    
    /**
     * Muestra el menu principal del sistema.
     */
    private void mostrarMenuPrincipal() {
        System.out.println("\n========================================");
        System.out.println("     CONTROL DE VERSIONES");
        System.out.println("        (Git Local)");
        System.out.println("========================================");
        System.out.println("1. init - Inicializar repositorio");
        System.out.println("2. add  - Agregar archivo");
        System.out.println("3. commit - Crear commit");
        System.out.println("4. log  - Ver historial");
        System.out.println("5. branch - Crear rama");
        System.out.println("6. checkout - Cambiar de rama");
        System.out.println("7. branch - Listar ramas");
        System.out.println("8. status - Estado del repositorio");
        System.out.println("9. diff  - Comparar commits");
        System.out.println("10. Salir");
        System.out.println("========================================");
        
        if (repositorioService.hayRepositorio()) {
            System.out.println("📁 Repositorio activo");
        } else {
            System.out.println("📁 No hay repositorio activo");
        }
        System.out.println("========================================");
        System.out.print("Seleccione una opcion: ");
    }
    
    /**
     * Lee y valida la opcion ingresada por el usuario.
     * 
     * @return Numero entero de la opcion seleccionada, o 0 si no es valida
     */
    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    /**
     * Pausa la ejecucion hasta que el usuario presione Enter.
     */
    private void pausa() {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
    
}//fin de la clase