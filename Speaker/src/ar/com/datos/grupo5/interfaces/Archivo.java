/**
 * 
 */
package ar.com.datos.grupo5.interfaces;

import java.io.FileNotFoundException;

/**
 * Interface para manejar Archivos.
 * @author Diego
 *
 */
public interface Archivo {
	/**
	 * Metodo para Intentar abrir un archivo, pasado por par�metro.
	 * @param archivo : Path completo del archivo.
	 * @param modo : Es el modo en el que se abrir� el archivo {R,W,R+,A}.
	 */
	void abrir(final String archivo, final String modo);
	
	/**
	 * Metodo para Intentar crear un archivo, pasado por par�metro.
	 * @param archivo Path completo del archivo.
	 * @param tipo Es el tipo de archivo que se quiere crear {B,T}.
	 * @throws FileNotFoundException si no se puede crear el archivo.
	 */
	void crear(final String archivo, final String tipo) throws FileNotFoundException;
	
	/**
	 * Metodo para cerrar el archivo que se est� manipulando.
	 */
	void cerrar();
		
	/**
	 * Metodo para buscar en un archivo, una cadena pasada por par�metro.
	 * @param registro : Es el registro a buscar en el archivo.
	 * @return Devuelve el resultado de la b�squeda, quedando posicionado el
	 * puntero en la posici�n donde deber�a estar el patr�n buscado.
	 */
	boolean buscar(final Registro registro);
		
	/**
	 * Metodo para Insertar la cadena en el archivo 
	 * en el que se est� trabajando.
	 * @param registro Es el registro que se va a agregar al archivo.
	 */
	void insertar(final Registro registro);
	
	/**
	 * Metodo para borrar una cadena en el archivo en el que se est� trabajando.
	 * @param registro Es el registro a buscar en el archivo.
	 * @return Devuelve el resultado de la operaci�n,
	 * y queda posicionado el puntero
	 * en la posici�n donde se encontraba la cadena buscada.
	 * En caso de no encontrar la cadena, devuelve false
	 *  y el puntero queda donde
	 * deber�a estar la cadena buscada.
	 */
	boolean borrar(final Registro registro);
}
