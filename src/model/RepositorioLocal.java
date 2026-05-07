package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que representa un repositorio local de control de versiones.
 * Gestiona ramas, commits y archivos versionados.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Commit
 * @see Rama
 * @see ArchivoVersion
 */
public class RepositorioLocal {
    
    // ==================== ATRIBUTOS ====================
    
    /** Nombre del repositorio. */
    private String nombre;
    
    /** Ruta fisica del repositorio (simulada). */
    private String path;
    
    /** Lista de ramas del repositorio. */
    private List<Rama> ramas;
    
    /** Rama actualmente activa. */
    private Rama ramaActual;
    
    /** Lista de commits del repositorio. */
    private List<Commit> commits;
    
    /** Mapa de archivos versionados (nombre -> archivo). */
    private Map<String, ArchivoVersion> archivos;
    
    /** Indica si el repositorio ha sido inicializado. */
    private boolean inicializado;
    
    /**
     * Constructor para crear un repositorio local.
     * 
     * @param nombre Nombre del repositorio
     * @param path Ruta del repositorio
     */
    public RepositorioLocal(String nombre, String path) {
        this.nombre = nombre;
        this.path = path;
        this.ramas = new ArrayList<>();
        this.commits = new ArrayList<>();
        this.archivos = new HashMap<>();
        this.inicializado = false;
    }
    
    /**
     * Inicializa el repositorio.
     */
    public void init() {
        if (inicializado) {
            System.out.println("El repositorio ya ha sido inicializado");
            return;
        }
        
        Rama main = new Rama("main");
        ramas.add(main);
        ramaActual = main;
        inicializado = true;
        System.out.println("Repositorio inicializado en " + path);
        System.out.println("Rama actual: " + main.getNombre());
    }
    
    /**
     * Agrega un archivo al area de staging.
     * 
     * @param archivo Archivo a agregar
     */
    public void add(ArchivoVersion archivo) {
        if (!inicializado) {
            System.out.println("El repositorio no ha sido inicializado. Ejecute 'init' primero");
            return;
        }
        
        archivos.put(archivo.getNombre(), archivo);
        System.out.println("Archivo agregado: " + archivo.getNombre());
    }
    
    /**
     * Crea un commit con los archivos en staging.
     * 
     * @param mensaje Mensaje del commit
     * @param autor Autor del commit
     * @return Commit creado
     */
    public Commit commit(String mensaje, String autor) {
        if (!inicializado) {
            System.out.println("El repositorio no ha sido inicializado");
            return null;
        }
        
        if (archivos.isEmpty()) {
            System.out.println("No hay archivos para commit. Use 'add' primero");
            return null;
        }
        
        Commit nuevoCommit = new Commit(mensaje, autor);
        
        // Agregar todos los archivos al commit
        for (ArchivoVersion archivo : archivos.values()) {
            nuevoCommit.agregarArchivo(archivo);
        }
        
        // Establecer padre si hay commits previos en la rama
        if (!ramaActual.getCommits().isEmpty()) {
            nuevoCommit.setPadre(ramaActual.getCommitActual());
        }
        
        commits.add(nuevoCommit);
        ramaActual.agregarCommit(nuevoCommit);
        
        System.out.println("Commit creado: " + nuevoCommit.getHash());
        return nuevoCommit;
    }
    
    /**
     * Muestra el historial de commits.
     * 
     * @return Lista de commits
     */
    public List<Commit> log() {
        if (!inicializado) {
            System.out.println("El repositorio no ha sido inicializado");
            return new ArrayList<>();
        }
        
        System.out.println("\n=== HISTORIAL DE COMMITS ===");
        for (Commit c : ramaActual.getCommits()) {
            System.out.println(c);
            System.out.println();
        }
        
        return ramaActual.getCommits();
    }
    
    /**
     * Cambia a una rama especifica.
     * 
     * @param nombreRama Nombre de la rama
     * @return true si se pudo cambiar
     */
    public boolean checkout(String nombreRama) {
        if (!inicializado) {
            System.out.println("El repositorio no ha sido inicializado");
            return false;
        }
        
        Rama rama = ramas.stream()
                .filter(r -> r.getNombre().equals(nombreRama))
                .findFirst()
                .orElse(null);
        
        if (rama == null) {
            System.out.println("Rama '" + nombreRama + "' no encontrada");
            return false;
        }
        
        this.ramaActual = rama;
        System.out.println("Cambiado a rama: " + nombreRama);
        return true;
    }
    
    /**
     * Crea una nueva rama.
     * 
     * @param nombreRama Nombre de la rama
     * @return true si se pudo crear
     */
    public boolean branch(String nombreRama) {
        if (!inicializado) {
            System.out.println("El repositorio no ha sido inicializado");
            return false;
        }
        
        boolean existe = ramas.stream().anyMatch(r -> r.getNombre().equals(nombreRama));
        if (existe) {
            System.out.println("La rama '" + nombreRama + "' ya existe");
            return false;
        }
        
        Rama nuevaRama = new Rama(nombreRama);
        ramas.add(nuevaRama);
        System.out.println("Rama creada: " + nombreRama);
        return true;
    }
    
    /**
     * Compara dos commits y muestra diferencias.
     * 
     * @param commit1 Primer commit
     * @param commit2 Segundo commit
     * @return Reporte de diferencias
     */
    public String diff(Commit commit1, Commit commit2) {
        if (commit1 == null || commit2 == null) {
            return "Commits invalidos para comparar";
        }
        
        StringBuilder reporte = new StringBuilder();
        reporte.append("\n=== DIFERENCIAS ENTRE COMMITS ===\n");
        reporte.append("Commit A: ").append(commit1.getHash()).append("\n");
        reporte.append("Commit B: ").append(commit2.getHash()).append("\n");
        reporte.append("-".repeat(40)).append("\n");
        
        // Comparar archivos
        Map<String, ArchivoVersion> archivos1 = new HashMap<>();
        Map<String, ArchivoVersion> archivos2 = new HashMap<>();
        
        for (ArchivoVersion a : commit1.getArchivos()) {
            archivos1.put(a.getNombre(), a);
        }
        for (ArchivoVersion a : commit2.getArchivos()) {
            archivos2.put(a.getNombre(), a);
        }
        
        // Archivos solo en commit1
        for (String nombre : archivos1.keySet()) {
            if (!archivos2.containsKey(nombre)) {
                reporte.append("- ").append(nombre).append(" (solo en commit A)\n");
            }
        }
        
        // Archivos solo en commit2
        for (String nombre : archivos2.keySet()) {
            if (!archivos1.containsKey(nombre)) {
                reporte.append("+ ").append(nombre).append(" (solo en commit B)\n");
            }
        }
        
        // Archivos modificados
        for (String nombre : archivos1.keySet()) {
            ArchivoVersion a1 = archivos1.get(nombre);
            ArchivoVersion a2 = archivos2.get(nombre);
            if (a2 != null && !a1.getHash().equals(a2.getHash())) {
                reporte.append("* ").append(nombre).append(" (modificado)\n");
            }
        }
        
        return reporte.toString();
    }
    
    // ==================== GETTERS ====================
    
    /** @return Nombre del repositorio */
    public String getNombre() {
        return nombre;
    }
    
    /** @return Ruta del repositorio */
    public String getPath() {
        return path;
    }
    
    /** @return Archivos en staging */
    public Map<String, ArchivoVersion> getArchivos() {
        return archivos;
    }
    
    /** @return Rama actual */
    public Rama getRamaActual() {
        return ramaActual;
    }
    
    /** @return Lista de ramas */
    public List<Rama> getRamas() {
        return ramas;
    }
    
    /** @return Lista de commits */
    public List<Commit> getCommits() {
        return commits;
    }
    
    /** @return true si esta inicializado */
    public boolean isInicializado() {
        return inicializado;
    }
    
    /**
     * Devuelve una representacion textual del repositorio.
     * 
     * @return Cadena con informacion
     */
    @Override
    public String toString() {
        return nombre + " - " + path + " - " + (inicializado ? "Inicializado" : "No inicializado") +
               " - Rama actual: " + (ramaActual != null ? ramaActual.getNombre() : "ninguna");
    }
    
}//fin de la clase