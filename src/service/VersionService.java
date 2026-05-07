package service;

import model.RepositorioLocal;
import model.Commit;
import model.ArchivoVersion;
import model.Rama;

/**
 * Clase de servicio que gestiona operaciones avanzadas de versiones.
 * Permite ver detalles de versiones, revertir cambios y analizar diferencias.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see RepositorioLocal
 * @see Commit
 */
public class VersionService {
    
    private RepositorioLocal repositorio;
    
    /**
     * Constructor del servicio de versiones.
     * 
     * @param repositorio Repositorio a analizar
     */
    public VersionService(RepositorioLocal repositorio) {
        this.repositorio = repositorio;
    }
    
    /**
     * Muestra el detalle de un commit.
     * 
     * @param idCommit Identificador del commit
     */
    public void mostrarDetalleCommit(int idCommit) {
        Commit commit = repositorio.getCommits().stream()
                .filter(c -> c.getIdCommit() == idCommit)
                .findFirst()
                .orElse(null);
        
        if (commit == null) {
            System.out.println("Commit no encontrado");
            return;
        }
        
        System.out.println("\n=== DETALLE DEL COMMIT ===");
        System.out.println(commit);
        System.out.println("\nArchivos en este commit:");
        for (ArchivoVersion a : commit.getArchivos()) {
            System.out.println("  - " + a.getNombre() + " (" + a.getTipoArchivo() + ")");
            System.out.println("    Contenido: " + a.getContenido().substring(0, 
                Math.min(50, a.getContenido().length())) + "...");
        }
    }
    
    /**
     * Muestra el resumen del repositorio.
     */
    public void mostrarResumen() {
        System.out.println("\n=== RESUMEN DEL REPOSITORIO ===");
        System.out.println("Total commits: " + repositorio.getCommits().size());
        System.out.println("Total ramas: " + repositorio.getRamas().size());
        System.out.println("Archivos en staging: " + repositorio.getArchivos().size());
        
        if (!repositorio.getRamas().isEmpty()) {
            System.out.println("\nRamas:");
            for (Rama r : repositorio.getRamas()) {
                System.out.println("  - " + r.getNombre() + ": " + r.getCommits().size() + " commits");
            }
        }
    }
    
    /**
     * Busca commits por autor.
     * 
     * @param autor Autor a buscar
     */
    public void buscarPorAutor(String autor) {
        System.out.println("\n=== COMMITS DE " + autor.toUpperCase() + " ===");
        boolean encontrado = false;
        
        for (Commit c : repositorio.getCommits()) {
            if (c.getAutor().equalsIgnoreCase(autor)) {
                System.out.println(c.getHash() + " - " + c.getMensaje());
                System.out.println("  " + c.getFecha());
                encontrado = true;
            }
        }
        
        if (!encontrado) {
            System.out.println("No se encontraron commits del autor: " + autor);
        }
    }
    
}//fin de la clase