package model;

/**
 * Clase que representa un archivo de texto.
 * Hereda de ArchivoVersion para archivos de texto plano.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see ArchivoVersion
 */
public class ArchivoTexto extends ArchivoVersion {
    
    /**
     * Constructor para crear un archivo de texto.
     * 
     * @param nombre Nombre del archivo
     * @param contenido Contenido del archivo
     */
    public ArchivoTexto(String nombre, String contenido) {
        super(nombre, contenido);
    }
    
    /**
     * Obtiene el tipo de archivo.
     * 
     * @return "Texto"
     */
    @Override
    public String getTipoArchivo() {
        return "Texto";
    }
    
    /**
     * Valida el contenido del archivo.
     * Todo contenido es valido para texto.
     * 
     * @param contenido Contenido a validar
     * @return true siempre
     */
    @Override
    public boolean validarContenido(String contenido) {
        return contenido != null;
    }
    
}//fin de la clase