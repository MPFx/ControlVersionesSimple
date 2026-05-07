package model;

/**
 * Clase que representa un archivo de imagen (simulado).
 * Hereda de ArchivoVersion para archivos de imagen.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see ArchivoVersion
 */
public class ArchivoImagen extends ArchivoVersion {
    
    // ==================== ATRIBUTOS ====================
    
    /** Formato de la imagen (PNG, JPG, GIF). */
    private String formato;
    
    /** Dimensiones de la imagen (ej: 1920x1080). */
    private String dimensiones;
    
    /**
     * Constructor para crear un archivo de imagen.
     * 
     * @param nombre Nombre del archivo
     * @param contenido Contenido simulado
     */
    public ArchivoImagen(String nombre, String contenido) {
        super(nombre, contenido);
        this.formato = "PNG";
        this.dimensiones = "800x600";
    }
    
    /**
     * Obtiene el tipo de archivo.
     * 
     * @return "Imagen"
     */
    @Override
    public String getTipoArchivo() {
        return "Imagen";
    }
    
    /**
     * Valida el contenido del archivo de imagen.
     * Simula validacion de formato de imagen.
     * 
     * @param contenido Contenido a validar
     * @return true si el contenido es valido
     */
    @Override
    public boolean validarContenido(String contenido) {
        return contenido != null && !contenido.isEmpty();
    }
    
    // ==================== GETTERS Y SETTERS ====================
    
    /** @return Formato de la imagen */
    public String getFormato() {
        return formato;
    }
    
    /** @param formato Nuevo formato */
    public void setFormato(String formato) {
        this.formato = formato;
    }
    
    /** @return Dimensiones de la imagen */
    public String getDimensiones() {
        return dimensiones;
    }
    
    /** @param dimensiones Nuevas dimensiones */
    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }
    
    /**
     * Devuelve una representacion textual del archivo.
     * 
     * @return Cadena con informacion
     */
    @Override
    public String toString() {
        return super.toString() + " - Formato: " + formato + " - Dimensiones: " + dimensiones;
    }
    
}//fin de la clase