package ar.com.datos.UnidadesDeExpresion;

/**
 * Interfaz que implementan los objetos plausibles de pronunciarse.
 * 
 * @author LedZeppelin
 */
public interface IunidadDeHabla extends Comparable{
	
	/**
	 * Este m�todo permite obtener la cadena a la que hace referencia el objeto.
	 * 
	 * @return string con la palabra a la que refiere el objeto
	 */
	String toString();

	/**
	 * Al igual que el m�todo toString, devuelve el string tal cual fue escrito
	 * por el usuario o levantado del archivo de texto. sin procesamiento
	 * fon�tico.
	 * 
	 * @return
	 */
	String getTextoEscrito();
	
	/**
	 * Permite saber si el la expresi�n asociada debe pronunciarse o no, depende
	 * del contexto en el que se encuentre.
	 * 
	 * @return boolean
	 */
	boolean esPronunciable();

	/**
	 * 
	 * @param pronunciable
	 */
	void setPronunciable(boolean pronunciable);
	
	/**
	 * Por la naturaleza de los algoritmos utilizados (HashMap) y las mejoras
	 * propuestas al c�digo, cualquier objeto plausible de pronunciaci�n debe
	 * redefinir el m�todo hashCode de Object, para asegurar la unicidad y
	 * correcto trato de las palabras a las cuales refieren.
	 * 
	 * @return se devuelve un entero que representa la identidad (DNI) de dicho
	 *         objeto.
	 */
	int hashCode();

	/**
	 * "Setter" del audio asociado a la palabra. Como no se cual es el tipo de
	 * dato pongo Object
	 * 
	 * @param o
	 *            objeto de sonido
	 */
	void setAudio(Object o);
	
	/**
	 * M�todo que permite reproducir la palabra actual.
	 * 
	 * @return Objeto con el stream de sonido asociado a la palabra
	 */
	Object getAudio();

	/**
	 * M�todo que devuelve el string con el que ser� guardada en el diccionario
	 * la palabra en cuesti�n. Puede o no diferir del texto escrito por el
	 * usuario.
	 * 
	 * @return string con la informacion detallada anteriormente.
	 */
	String getEquivalenteFonetico();
	
	/**
	 * Setea el String para ser almacenado por el diccionario. Este String
	 * representa un equivalente fon�tico a la palabra o s�mbolo escrito.
	 * 
	 * @param equivalenteFonetico
	 *            String equivalente
	 */
	void setEquivalenteFonetico(String equivalenteFonetico);
	/**
	 * Redefinicion de equals para busquedas.
	 * @param o Objeto a chequear su igualdad.
	 * @return true si son iguales.
	 */ 
	boolean equals(Object o);
	
	/**
	 * Redefinicion de Compare para ordenacion.
	 * @param o Objeto a comparar
	 * @return -1 
	 */
	int compareTo(Object o); 

}
