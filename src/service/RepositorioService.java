package service;

import model.RepositorioLocal;
import model.ArchivoVersion;
import model.ArchivoTexto;
import model.ArchivoCodigo;
import model.ArchivoImagen;
import model.ArchivoConfig;
import model.Commit;

import java.util.Scanner;
import model.Rama;

/**
 * Clase de servicio que gestiona las operaciones del repositorio.
 * Permite inicializar repositorio, agregar archivos, crear commits,
 * cambiar ramas y ver historial.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see RepositorioLocal
 * @see ArchivoVersion
 * @see Commit
 */
public class RepositorioService {
    
    // ==================== ATRIBUTOS ====================
    
    private RepositorioLocal repositorio;
    
    /**
     * Constructor del servicio de repositorio.
     */
    public RepositorioService() {
        this.repositorio = null;
    }
    
    /**
     * Inicializa el repositorio.
     * 
     * @param scanner Scanner para entrada de datos
     */
    public void init(Scanner scanner) {
        if (repositorio != null && repositorio.isInicializado()) {
            System.out.println("Ya hay un repositorio inicializado");
            return;
        }
        
        System.out.println("\n=== INICIALIZAR REPOSITORIO ===");
        System.out.print("Nombre del repositorio: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Ruta del repositorio (ej: C:/repos/): ");
        String path = scanner.nextLine();
        
        repositorio = new RepositorioLocal(nombre, path);
        repositorio.init();
    }
    
    /**
     * Agrega un archivo al repositorio.
     * 
     * @param scanner Scanner para entrada de datos
     */
    public void add(Scanner scanner) {
        if (repositorio == null || !repositorio.isInicializado()) {
            System.out.println("Primero debe inicializar el repositorio");
            return;
        }
        
        System.out.println("\n=== AGREGAR ARCHIVO ===");
        System.out.println("Tipo de archivo:");
        System.out.println("1. Texto (.txt)");
        System.out.println("2. Codigo (.java, .py, .js)");
        System.out.println("3. Imagen (.png, .jpg)");
        System.out.println("4. Configuracion (.properties, .json)");
        System.out.print("Seleccione: ");
        
        int tipo = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Nombre del archivo: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Contenido del archivo: ");
        String contenido = scanner.nextLine();
        
        ArchivoVersion archivo = switch (tipo) {
            case 2 -> new ArchivoCodigo(nombre, contenido);
            case 3 -> new ArchivoImagen(nombre, contenido);
            case 4 -> new ArchivoConfig(nombre, contenido);
            default -> new ArchivoTexto(nombre, contenido);
        };
        
        if (archivo.validarContenido(contenido)) {
            repositorio.add(archivo);
        } else {
            System.out.println("Contenido invalido para el tipo de archivo");
        }
    }
    
    /**
     * Crea un commit.
     * 
     * @param scanner Scanner para entrada de datos
     */
    public void commit(Scanner scanner) {
        if (repositorio == null || !repositorio.isInicializado()) {
            System.out.println("Primero debe inicializar el repositorio");
            return;
        }
        
        System.out.println("\n=== CREAR COMMIT ===");
        System.out.print("Mensaje del commit: ");
        String mensaje = scanner.nextLine();
        
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        
        repositorio.commit(mensaje, autor);
    }
    
    /**
     * Muestra el historial de commits.
     */
    public void log() {
        if (repositorio == null || !repositorio.isInicializado()) {
            System.out.println("Primero debe inicializar el repositorio");
            return;
        }
        
        repositorio.log();
    }
    
    /**
     * Cambia a una rama especifica.
     * 
     * @param scanner Scanner para entrada de datos
     */
    public void checkout(Scanner scanner) {
        if (repositorio == null || !repositorio.isInicializado()) {
            System.out.println("Primero debe inicializar el repositorio");
            return;
        }
        
        System.out.println("\n=== CAMBIAR RAMA ===");
        listarRamas();
        
        System.out.print("Nombre de la rama: ");
        String nombreRama = scanner.nextLine();
        
        repositorio.checkout(nombreRama);
    }
    
    /**
     * Crea una nueva rama.
     * 
     * @param scanner Scanner para entrada de datos
     */
    public void branch(Scanner scanner) {
        if (repositorio == null || !repositorio.isInicializado()) {
            System.out.println("Primero debe inicializar el repositorio");
            return;
        }
        
        System.out.println("\n=== CREAR RAMA ===");
        System.out.print("Nombre de la nueva rama: ");
        String nombreRama = scanner.nextLine();
        
        repositorio.branch(nombreRama);
    }
    
    /**
     * Muestra las ramas disponibles.
     */
    public void listarRamas() {
        if (repositorio == null || !repositorio.isInicializado()) {
            System.out.println("Primero debe inicializar el repositorio");
            return;
        }
        
        System.out.println("\n=== RAMAS DISPONIBLES ===");
        for (Rama r : repositorio.getRamas()) {
            String indicador = (repositorio.getRamaActual() != null && 
                               repositorio.getRamaActual().getNombre().equals(r.getNombre())) 
                               ? " *" : "";
            System.out.println("  " + r.getNombre() + indicador);
        }
    }
    
    /**
     * Compara dos commits.
     * 
     * @param scanner Scanner para entrada de datos
     */
    public void diff(Scanner scanner) {
        if (repositorio == null || !repositorio.isInicializado()) {
            System.out.println("Primero debe inicializar el repositorio");
            return;
        }
        
        System.out.println("\n=== COMPARAR COMMITS ===");
        log();
        
        System.out.print("ID del primer commit: ");
        int id1 = Integer.parseInt(scanner.nextLine());
        
        System.out.print("ID del segundo commit: ");
        int id2 = Integer.parseInt(scanner.nextLine());
        
        Commit c1 = repositorio.getCommits().stream()
                .filter(c -> c.getIdCommit() == id1)
                .findFirst()
                .orElse(null);
        
        Commit c2 = repositorio.getCommits().stream()
                .filter(c -> c.getIdCommit() == id2)
                .findFirst()
                .orElse(null);
        
        if (c1 == null || c2 == null) {
            System.out.println("Commit no encontrado");
            return;
        }
        
        System.out.println(repositorio.diff(c1, c2));
    }
    
    /**
     * Muestra el estado del repositorio.
     */
    public void status() {
        if (repositorio == null || !repositorio.isInicializado()) {
            System.out.println("Primero debe inicializar el repositorio");
            return;
        }
        
        System.out.println("\n=== ESTADO DEL REPOSITORIO ===");
        System.out.println("Repositorio: " + repositorio.getNombre());
        System.out.println("Ruta: " + repositorio.getPath());
        System.out.println("Rama actual: " + repositorio.getRamaActual().getNombre());
        System.out.println("Archivos en staging: " + repositorio.getArchivos().size());
        
        if (!repositorio.getArchivos().isEmpty()) {
            System.out.println("\nArchivos preparados para commit:");
            for (ArchivoVersion a : repositorio.getArchivos().values()) {
                System.out.println("  - " + a.getNombre() + " (" + a.getTipoArchivo() + ")");
            }
        }
    }
    
    /**
     * Verifica si hay repositorio inicializado.
     * 
     * @return true si hay repositorio
     */
    public boolean hayRepositorio() {
        return repositorio != null && repositorio.isInicializado();
    }
    /**
    * Obtiene el repositorio actual.
    * 
    * @return RepositorioLocal actual
    */
   public RepositorioLocal getRepositorio() {
       return repositorio;
   }
    
}//fin de la clase