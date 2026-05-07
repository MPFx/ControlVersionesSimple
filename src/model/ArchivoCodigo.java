package model;

/**
 * Clase que representa un archivo de codigo fuente.
 * Hereda de ArchivoVersion para archivos de programacion.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see ArchivoVersion
 */
public class ArchivoCodigo extends ArchivoVersion {
    
    // ==================== ATRIBUTOS ====================
    
    /** Lenguaje de programacion. */
    private String lenguaje;
    
    /**
     * Constructor para crear un archivo de codigo.
     * 
     * @param nombre Nombre del archivo
     * @param contenido Contenido del archivo
     */
    public ArchivoCodigo(String nombre, String contenido) {
        super(nombre, contenido);
        this.lenguaje = "Java";
    }
    
    /**
     * Obtiene el tipo de archivo.
     * 
     * @return "Codigo"
     */
    @Override
    public String getTipoArchivo() {
        return "Codigo";
    }
    
    /**
     * Valida el contenido del archivo de codigo.
     * Verifica que no este vacio.
     * 
     * @param contenido Contenido a validar
     * @return true si el contenido no esta vacio
     */
    @Override
    public boolean validarContenido(String contenido) {
        return contenido != null && !contenido.trim().isEmpty();
    }
    
    // ==================== GETTERS Y SETTERS ====================
    
    /** @return Lenguaje de programacion */
    public String getLenguaje() {
        return lenguaje;
    }
    
    /** @param lenguaje Nuevo lenguaje */
    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }
    
    /**
     * Devuelve una representacion textual del archivo.
     * 
     * @return Cadena con informacion
     */
    @Override
    public String toString() {
        return super.toString() + " - Lenguaje: " + lenguaje;
    }
    
}//fin de la clase