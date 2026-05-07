package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un commit (snapshot) en el sistema de control de versiones.
 * Contiene los archivos, mensaje, autor y referencia al commit padre.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Rama
 * @see RepositorioLocal
 */
public class Commit {
    
    // ==================== ATRIBUTOS ====================
    
    /** Identificador unico del commit. */
    private int idCommit;
    
    /** Hash del commit (simulado). */
    private String hash;
    
    /** Mensaje descriptivo del commit. */
    private String mensaje;
    
    /** Fecha y hora del commit. */
    private LocalDateTime fecha;
    
    /** Autor del commit. */
    private String autor;
    
    /** Lista de archivos incluidos en este commit. */
    private List<ArchivoVersion> archivos;
    
    /** Commit padre (anterior). */
    private Commit padre;
    
    /** Contador estatico para generar IDs. */
    private static int contadorIds = 1;
    
    /**
     * Constructor para crear un commit.
     * 
     * @param mensaje Mensaje del commit
     * @param autor Autor del commit
     */
    public Commit(String mensaje, String autor) {
        this.idCommit = contadorIds++;
        this.mensaje = mensaje;
        this.autor = autor;
        this.fecha = LocalDateTime.now();
        this.archivos = new ArrayList<>();
        this.hash = calcularHash();
    }
    
    /**
     * Calcula el hash del commit (simulado).
     * 
     * @return Hash del commit
     */
    private String calcularHash() {
        String data = idCommit + mensaje + autor + fecha.toString();
        return Integer.toHexString(data.hashCode()).substring(0, 7);
    }
    
    /**
     * Agrega un archivo al commit.
     * 
     * @param archivo Archivo a agregar
     */
    public void agregarArchivo(ArchivoVersion archivo) {
        archivos.add(archivo);
    }
    
    // ==================== GETTERS ====================
    
    /** @return Identificador del commit */
    public int getIdCommit() {
        return idCommit;
    }
    
    /** @return Hash del commit */
    public String getHash() {
        return hash;
    }
    
    /** @return Mensaje del commit */
    public String getMensaje() {
        return mensaje;
    }
    
    /** @return Fecha del commit */
    public LocalDateTime getFecha() {
        return fecha;
    }
    
    /** @return Autor del commit */
    public String getAutor() {
        return autor;
    }
    
    /** @return Lista de archivos */
    public List<ArchivoVersion> getArchivos() {
        return archivos;
    }
    
    /** @return Commit padre */
    public Commit getPadre() {
        return padre;
    }
    
    /** @param padre Commit padre */
    public void setPadre(Commit padre) {
        this.padre = padre;
    }
    
    /**
     * Devuelve una representacion textual del commit.
     * 
     * @return Cadena con informacion
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "commit " + hash + " (" + idCommit + ")\n" +
               "Author: " + autor + "\n" +
               "Date:   " + fecha.format(formatter) + "\n" +
               "    " + mensaje + "\n" +
               "    " + archivos.size() + " archivos modificados";
    }
    
}//fin de la clase