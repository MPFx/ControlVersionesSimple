package model;

/**
 * Clase que representa un archivo de configuracion.
 * Hereda de ArchivoVersion para archivos de configuracion (JSON, XML, YAML).
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see ArchivoVersion
 */
public class ArchivoConfig extends ArchivoVersion {
    
    // ==================== ATRIBUTOS ====================
    
    /** Formato de configuracion (JSON, XML, YAML, PROPERTIES). */
    private String formato;
    
    /** Indica si el archivo es obligatorio para el sistema. */
    private boolean esObligatorio;
    
    /**
     * Constructor para crear un archivo de configuracion.
     * 
     * @param nombre Nombre del archivo
     * @param contenido Contenido del archivo
     */
    public ArchivoConfig(String nombre, String contenido) {
        super(nombre, contenido);
        this.formato = "PROPERTIES";
        this.esObligatorio = false;
    }
    
    /**
     * Obtiene el tipo de archivo.
     * 
     * @return "Configuracion"
     */
    @Override
    public String getTipoArchivo() {
        return "Configuracion";
    }
    
    /**
     * Valida el contenido del archivo de configuracion.
     * Verifica que tenga formato clave=valor.
     * 
     * @param contenido Contenido a validar
     * @return true si el contenido tiene lineas con formato clave=valor
     */
    @Override
    public boolean validarContenido(String contenido) {
        if (contenido == null || contenido.isEmpty()) {
            return false;
        }
        String[] lineas = contenido.split("\n");
        for (String linea : lineas) {
            if (linea.trim().isEmpty()) continue;
            if (!linea.contains("=")) {
                return false;
            }
        }
        return true;
    }
    
    // ==================== GETTERS Y SETTERS ====================
    
    /** @return Formato de configuracion */
    public String getFormato() {
        return formato;
    }
    
    /** @param formato Nuevo formato */
    public void setFormato(String formato) {
        this.formato = formato;
    }
    
    /** @return true si es obligatorio */
    public boolean isEsObligatorio() {
        return esObligatorio;
    }
    
    /** @param esObligatorio Nueva obligatoriedad */
    public void setEsObligatorio(boolean esObligatorio) {
        this.esObligatorio = esObligatorio;
    }
    
    /**
     * Devuelve una representacion textual del archivo.
     * 
     * @return Cadena con informacion
     */
    @Override
    public String toString() {
        return super.toString() + " - Formato: " + formato + 
               (esObligatorio ? " [OBLIGATORIO]" : " [OPCIONAL]");
    }
    
}//fin de la clase