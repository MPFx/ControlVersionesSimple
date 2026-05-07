package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una rama en el sistema de control de versiones.
 * Contiene una lista de commits y apunta al commit actual.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Commit
 * @see RepositorioLocal
 */
public class Rama {
    
    // ==================== ATRIBUTOS ====================
    
    /** Identificador unico de la rama. */
    private int idRama;
    
    /** Nombre de la rama (main, develop, feature/*). */
    private String nombre;
    
    /** Lista de commits en esta rama. */
    private List<Commit> commits;
    
    /** Commit actual (HEAD). */
    private Commit commitActual;
    
    /** Contador estatico para generar IDs. */
    private static int contadorIds = 1;
    
    /**
     * Constructor para crear una rama.
     * 
     * @param nombre Nombre de la rama
     */
    public Rama(String nombre) {
        this.idRama = contadorIds++;
        this.nombre = nombre;
        this.commits = new ArrayList<>();
        this.commitActual = null;
    }
    
    /**
     * Agrega un commit a la rama.
     * 
     * @param commit Commit a agregar
     */
    public void agregarCommit(Commit commit) {
        commits.add(commit);
        commitActual = commit;
    }
    
    /**
     * Mueve el puntero HEAD a un commit especifico.
     * 
     * @param commit Commit al que mover
     */
    public void moverCommit(Commit commit) {
        if (commits.contains(commit)) {
            this.commitActual = commit;
        }
    }
    
    // ==================== GETTERS ====================
    
    /** @return Identificador de la rama */
    public int getIdRama() {
        return idRama;
    }
    
    /** @return Nombre de la rama */
    public String getNombre() {
        return nombre;
    }
    
    /** @return Lista de commits */
    public List<Commit> getCommits() {
        return commits;
    }
    
    /** @return Commit actual (HEAD) */
    public Commit getCommitActual() {
        return commitActual;
    }
    
    /**
     * Devuelve una representacion textual de la rama.
     * 
     * @return Cadena con informacion
     */
    @Override
    public String toString() {
        return nombre + " - " + commits.size() + " commits" + 
               (commitActual != null ? " - HEAD: " + commitActual.getHash() : "");
    }
    
}//fin de la clase