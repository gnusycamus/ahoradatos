package ar.com.datos.grupo5.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interface para manejar Archivos.
 * @author Diego
 *
 */
public interface Archivo {
	/**
	 * M�todo para Intentar abrir un archivo, pasado por par�metro.
	 * 
	 * @param archivo
	 *            Path completo del arc/home/xxvkue/Datos/ahoradatoshivo.
	 * @param modo
	 *            Es el modo en el que se abrir� el archivo {R,W,R+,A}.
	 * @return devuelve el resultado de la operaci�n.
	 * @throws FileNotFoundException
	 *             Si no puede abrir el Archivo.
	 */
	boolean abrir(final String archivo, final String modo)
			throws FileNotFoundException;

	/**
	 * M�todo para Intentar crear un archivo, pasado por par�metro.
	 * 
	 * @param archivo
	 *            Path completo del archivo.
	 * @throws FileNotFoundException
	 *             si no se puede crear el archivo.
	 */
	void crear(final String archivo) throws FileNotFoundException;

	/**
	 * M�todo para cerrar el archivo que se est� manipulando.
	 * 
	 * @throws IOException
	 *             Excepcion de entrada/salida.
	 */
	void cerrar() throws IOException;
		
	/**
	 * M�todo para buscar en un archivo, una cadena pasada por par�metro.
	 * 
	 * @param registro
	 *            : Es el registro a buscar en el archivo.
	 * @return Devuelve el resultado de la b�squeda, quedando posicionado el
	 *         puntero en la posici�n donde deber�a estar el patr�n buscado.
	 */
	boolean buscar(final Registro registro);

	/**
	 * Metodo para Insertar la cadena en el archivo en el que se est�
	 * trabajando.
	 * 
	 * @param registro
	 *            Es el registro que se va a agregar al archivo.
	 * @throws IOException
	 *             Excepcion de extrada/salida.
	 */
	void insertar(final Registro registro) throws IOException;
	
	/**
	 * Metodo para borrar una cadena en el archivo en el que se est� trabajando.
	 * 
	 * @param registro
	 *            Es el registro a buscar en el archivo.
	 * @return Devuelve el resultado de la operaci�n, y queda posicionado el
	 *         puntero en la posici�n donde se encontraba la cadena buscada. En
	 *         caso de no encontrar la cadena, devuelve false y el puntero queda
	 *         donde deber�a estar la cadena buscada.
	 */
	boolean borrar(final Registro registro);
	
	/**
	 * Borra el archivo en disco.
	 * 
	 * @return true si lo pudo borrar.
	 * @throws IOException .
	 */
	boolean eliminar() throws IOException;

	/**
	 * devuelve el primer registro.
	 * 
	 * @return EL registro.
	 */
	Registro primero();

	/**
	 * Devuelve el registro siguiente al actual.
	 * 
	 * @return EL registro.
	 */
	Registro siguiente();
}
