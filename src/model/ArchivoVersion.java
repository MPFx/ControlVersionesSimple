package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Clase abstracta que representa un archivo versionado.
 * Contiene los atributos y comportamientos comunes a todos los tipos de archivos
 * (Texto, Codigo, Imagen, Config). Implementa la interfaz Diferencia.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see ArchivoTexto
 * @see ArchivoCodigo
 * @see ArchivoImagen
 * @see ArchivoConfig
 * @see Diferencia
 */
public abstract class ArchivoVersion implements Diferencia {
    
    // ==================== ATRIBUTOS ====================
    
    /** Identificador unico del archivo. */
    protected int idArchivo;
    
    /** Nombre del archivo. */
    protected String nombre;
    
    /** Contenido del archivo. */
    protected String contenido;
    
    /** Hash del contenido (simulado). */
    protected String hash;
    
    /** Fecha de ultima modificacion. */
    protected LocalDateTime fechaModificacion;
    
    /** Tamaño del archivo en bytes (simulado). */
    protected int tamaño;
    
    /** Contador estatico para generar IDs. */
    private static int contadorIds = 1;
    
    /**
     * Constructor para crear un archivo versionado.
     * 
     * @param nombre Nombre del archivo
     * @param contenido Contenido del archivo
     */
    public ArchivoVersion(String nombre, String contenido) {
        this.idArchivo = contadorIds++;
        this.nombre = nombre;
        this.contenido = contenido;
        this.fechaModificacion = LocalDateTime.now();
        this.tamaño = contenido.length();
        this.hash = calcularHash();
    }
    
    /**
     * Calcula el hash del contenido (simulado).
     * 
     * @return Hash del contenido
     */
    public String calcularHash() {
        return Integer.toHexString(contenido.hashCode());
    }
    
    /**
     * Compara este archivo con otro.
     * 
     * @param otro Archivo a comparar
     * @return Reporte de diferencias
     */
    @Override
    public String comparar(ArchivoVersion otro) {
        StringBuilder reporte = new StringBuilder();
        reporte.append("\n=== DIFERENCIAS ===\n");
        reporte.append("Archivo 1: ").append(this.nombre).append("\n");
        reporte.append("Archivo 2: ").append(otro.nombre).append("\n");
        reporte.append("-".repeat(30)).append("\n");
        
        if (this.contenido.equals(otro.contenido)) {
            reporte.append("Los archivos son identicos\n");
        } else {
            reporte.append("Los archivos son diferentes\n");
            reporte.append("Hash 1: ").append(this.hash).append("\n");
            reporte.append("Hash 2: ").append(otro.hash).append("\n");
            
            // Mostrar primeras líneas diferentes
            String[] lineas1 = this.contenido.split("\n");
            String[] lineas2 = otro.contenido.split("\n");
            
            reporte.append("\nPrimeras diferencias:\n");
            for (int i = 0; i < Math.min(lineas1.length, lineas2.length); i++) {
                if (!lineas1[i].equals(lineas2[i])) {
                    reporte.append("Línea ").append(i + 1).append(":\n");
                    reporte.append("  Archivo 1: ").append(lineas1[i]).append("\n");
                    reporte.append("  Archivo 2: ").append(lineas2[i]).append("\n");
                    break;
                }
            }
        }
        
        return reporte.toString();
    }
    
    /**
     * Obtiene el tipo de archivo.
     * Metodo abstracto implementado por las subclases.
     * 
     * @return Tipo de archivo
     */
    public abstract String getTipoArchivo();
    
    /**
     * Valida el contenido del archivo.
     * Metodo abstracto implementado por las subclases.
     * 
     * @param contenido Contenido a validar
     * @return true si el contenido es valido
     */
    public abstract boolean validarContenido(String contenido);
    
    // ==================== GETTERS ====================
    
    /** @return Identificador del archivo */
    public int getIdArchivo() {
        return idArchivo;
    }
    
    /** @return Nombre del archivo */
    public String getNombre() {
        return nombre;
    }
    
    /** @return Contenido del archivo */
    public String getContenido() {
        return contenido;
    }
    
    /** @param contenido Nuevo contenido */
    public void setContenido(String contenido) {
        this.contenido = contenido;
        this.fechaModificacion = LocalDateTime.now();
        this.tamaño = contenido.length();
        this.hash = calcularHash();
    }
    
    /** @return Hash del contenido */
    public String getHash() {
        return hash;
    }
    
    /** @return Fecha de modificacion */
    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }
    
    /** @return Tamaño del archivo */
    public int getTamaño() {
        return tamaño;
    }
    
    /**
     * Devuelve una representacion textual del archivo.
     * 
     * @return Cadena con informacion
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return nombre + " (" + getTipoArchivo() + ") - " + tamaño + " bytes - " +
               fechaModificacion.format(formatter) + " - Hash: " + hash.substring(0, Math.min(8, hash.length()));
    }
    
}//fin de la clase