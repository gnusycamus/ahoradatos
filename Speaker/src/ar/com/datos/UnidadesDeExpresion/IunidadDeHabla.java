package ar.com.datos.UnidadesDeExpresion;

/**
 * Interfaz que implementan los objetos plausibles de pronunciarse.
 * @author Exequiel
 */
public interface IunidadDeHabla {
	
	/**
	 * M�todo que permite reproducir la palabra actual.
	 * @return Objeto con el stream de sonido asociado a la palabra
	 */
	Object getAudio();
	
	/**
	 * Este m�todo permite obtener la cadena a la que hace referencia el objeto.
	 * @return string con la palabra a la que refiere el objeto
	 */
	String toString();
	
	/**
	 * Permite saber si el la expresi�n asociada debe pronunciarse o no,
	 * depende del contexto en el que se encuentre.
	 * @return boolean
	 */
	boolean esPronunciable();
	
	/**
	 * Por la naturaleza de los algoritmos utilizados (HashMap) y las mejoras
	 * propuestas al c�digo, cualquier objeto plausible de pronunciaci�n
	 * debe redefinir el m�todo hashCode de Object, para asegurar la unicidad
	 * y correcto trato de las palabras a las cuales refieren.
	 * @return se devuelve un entero que representa la identidad (DNI) de 
	 * dicho objeto.
	 */
	int hashCode();
	
	/**
	 * "Setter" del audio asociado a la palabra. Como no se cual es el
	 * tipo de dato pongo Object 
	 * @param o objeto de sonido
	 */
	void setAudio(Object o);

}
