package ar.com.datos.parser;

import java.util.Collection;
import ar.com.datos.UnidadesDeExpresion.IunidadDeHabla;

/**
 * Interface implementada por las clases del paquete Parser. Permite obtener una
 * colecci�n de palabras para su verificaci�n de almacenamiento en disco o para
 * su administrar su reproducci�n. A tales efectos se disponen 2 m�todos
 * diferentes, de modo que las clases que lo implementen agregen f�cilmente
 * optimizaciones sobre los diferentes procesos.
 * 
 * @author LedZeppeling
 * 
 */
public interface ITextInput  {

	/**
	 * M�todo que permite obtener una colecci�n de palabras para verificar su
	 * presencia en disco.
	 * 
	 * @param rutaOlinea .
	 *            ruta absoluta del archivo de carga
	 * @param esArchivo .
	 * @return Colecci�n de objetos "palabra"
	 * @throws Exception .
	 */
	Collection<IunidadDeHabla> modoCarga(String rutaOlinea, boolean esArchivo)
			throws Exception;
	
	/**
	 * M�todo que permite obtener una colecci�n ordenada de palabras para su
	 * reproducci�n.
	 * 
	 * @param rutaOlinea 
	 *            String que contiene una ruta absoluta de archivo a reproducir,
	 *            � bien directamente un string con texto a ser reproducido.
	 * @param esArchivo
	 *            boolean que permite decidir si el String pasado por par�metro
	 *            es un archivo o un texto.
	 * @return Se devuelve una colecci�n ordenada con los elementos a
	 *         pronunciarse
	 * @throws Exception .
	 */
	Collection<IunidadDeHabla> modoLectura(String rutaOlinea, boolean esArchivo)
			throws Exception;
	
}
