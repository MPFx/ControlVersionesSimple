package model;

/**
 * Interfaz que define el comportamiento para comparar versiones de archivos.
 * Las clases que implementen esta interfaz podran calcular diferencias
 * entre dos versiones de un archivo.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see ArchivoVersion
 */
public interface Diferencia {
    
    /**
     * Compara este archivo con otro y genera un reporte de diferencias.
     * 
     * @param otro Archivo a comparar
     * @return Reporte de diferencias entre los dos archivos
     */
    String comparar(ArchivoVersion otro);
    
}//fin de la interfaz