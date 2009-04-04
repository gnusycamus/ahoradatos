package ar.com.datos.parser;

import java.util.Collection;

/**
 * Interfaz que permite usar la colecci�n BufferizableCollection. El objeto que
 * implementa esta interfaz podr� ofrecer al usuario un metodo de acceso a sus
 * items transparentemente bufferisados mediante una colecci�n que se
 * auto-recarga al agotarse su buffer.
 * 
 * @author LedZeppeling
 */
public interface BufferRecharger<E> {
	
	/**
	 * 
	 * @return .
	 */
	Collection<E> listar();

	/**
	 * M�todo que es llamado por la colecci�n bufferizable cuando agota su
	 * buffer por defecto.
	 * 
	 * @param coleccion
	 *            colecci�n bufferisable
	 * @param MaxBufferedObjects
	 *            cantidad m�xima de objetos que entran en el buffer
	 */
	void recargarBuffer (Collection<E> coleccion, int MaxBufferedObjects);

	/**
	 * Permite saber si hay mas elementos para devolver
	 * 
	 * @return boolean que dice si hay mas elementos
	 */
	boolean hasMoreItems(); 
}
