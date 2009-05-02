package ar.com.datos.grupo5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import ar.com.datos.UnidadesDeExpresion.IunidadDeHabla;
import ar.com.datos.parser.PalabrasFactory;

/**
 * Constantes de la aplicacion.
 * @author LedZeppeling
 *
 */
@SuppressWarnings("unchecked")
public final class Constantes {
	
	/**
	 * Constructor privado.
	 */
	private Constantes() {
		super();
	}

	/**
	 * Para indicar que la clave es mayor que la ultima del nodo.
	 */
	public static final Float FACTOR_CARGA_NODOS = new Float(0.66);
	
	/**
	 * Para indicar que la clave es mayor que la ultima del nodo.
	 */
	public static final int MAYOR = -2;
	
	/**
	 * Para indicar que la clave es menor que la primera del nodo.
	 */
	public static final int MENOR = -1;
	
	/**
	 * Tama�o en bits de un byte.
	 */
	private static final int SIZE_OF_BYTE = 8;

	/**
	 * Tama�o en bytes del bloque del archivo de indice.
	 */
	public static final int SIZE_OF_INDEX_BLOCK = Integer
	.parseInt(getXML("SIZE_OF_INDEX_BLOCK"));

	/**
	 * Tama�o del orden del  Trie.
	 */
	public static final int SIZE_OF_TRIE = Integer
	.parseInt(getXML("SIZE_OF_TRIE"));	

	/**
	 * 
	 */
	private static SAXBuilder builder;
	
	/**
	 * 
	 */
	private static Document doc;
	
	/**
	 * 
	 */
	private static Element raiz; 
	
	/**
	 * Tama�o del buffer de lectura.
	 */
	public static final int TAMANIO_BUFFER_LECTURA = Integer
			.parseInt(getXML("TAMANIO_BUFFER_LECTURA"));
	
	
	/**
	 * Tama�o del buffer de escritura.
	 */
	public static final int TAMANIO_BUFFER_ESCRITURA = Integer
			.parseInt(getXML("TAMANIO_BUFFER_ESCRITURA"));
	
	/**
	 * Abrir un archivo para lectura.
	 */
	public static final String ABRIR_PARA_LECTURA = "r";
	
	/**
	 * Abrir un archivo para lectura y escritura.
	 */
	public static final String ABRIR_PARA_LECTURA_ESCRITURA = "rw";
		
	/**
         * Tama�o en bytes del char.
	 */
	public static final int SIZE_OF_CHAR = Character.SIZE / SIZE_OF_BYTE;
	
    	/**
	* Tama�o en bytes del long.
	 */
	public static final int SIZE_OF_LONG = Long.SIZE / SIZE_OF_BYTE;
	
	/**
	 * Tama�o en bytes del int.
	 */
	public static final int SIZE_OF_INT = Integer.SIZE / SIZE_OF_BYTE;
	
	/**
         * Tama�o en bytes del short.
	 */
	public static final int SIZE_OF_SHORT = Short.SIZE / SIZE_OF_BYTE;
	
	/**
	 * Tama�o del buffer de lectura para la colecci�n auto pagin�ble.
	 */
	public static final int BUFFER_LECTURA_TEXT_INPUT = Integer
			.parseInt(getXML("BUFFER_LECTURA_TEXT_INPUT"));
	
	/**
	 * Tama�o de la cache de registros.
	 */
	public static final int TAMANO_CACHE = Integer
			.parseInt(getXML("TAMANO_CACHE"));
	
	/**
	 * Esta variable permite activar o desactivar las optimizaciones realizadas
	 * para el idioma espa�ol.
	 */
	public static final boolean SPANISH_OPTIMIZATION_ACTIVATED = 
		(getXML("SPANISH_OPTIMIZATION_ACTIVATED")
			.equalsIgnoreCase("true")) ? true : false;
	
	/**
	 * Codificaci�n por defecto utilizada en el parser.
	 */
	public static final String DEFAULT_TEXT_INPUT_CHARSET =
		getXML("DEFAULT_TEXT_INPUT_CHARSET");
	
	/**
	 * Definici�n de juego de caracteres utf8.
	 */
	public static final String CHARSET_UTF8 = "UTF-8";
	
	/**
	 * Definici�n de juego de caracteres ISO.
	 */
	public static final String CHARSET_ISO = "ISO-8859-1";
	
	/**
	 * Definici�n de juego de caracteres UTF16.
	 */
	public static final String CHARSET_UTF16 = "UTF-16";
	
	/**
	 * Definici�n de juego de caracteres ASCII.
	 */
	public static final String CHARSET_ASCII = "US-ASCII";

	/**
	 * Archivo que usa el diccionario para guardar las palabras.
	 */
	public static final String ARCHIVO_DICCIONARIO = 
		getXML("ARCHIVO_DICCIONARIO");
	
	/**
	 * .
	 */
	public static final String ESCAPES_REGEX = getXML("ESCAPES_REGEX");

	/**
	 * Archivo que usa el diccionario para guardar las palabras.
	 */
	public static final String ARCHIVO_AUDIO = getXML("ARCHIVO_AUDIO");

	/**
	 * Archivo que usa ListasInvertidas para guardar las listas invertidas.
	 */
	public static final String ARCHIVO_LISTAS_INVERTIDAS = 
		getXML("ARCHIVO_LISTAS_INVERTIDAS");	
	
	/**
	 * Archivo que usa ListasInvertidas para guardar las listas invertidas.
	 */
	public static final String ARCHIVO_TERMINOS_GLOBALES = 
		getXML("ARCHIVO_TERMINOS_GLOBALES");
	
	/**
	 * Tama�o para los bloques del archivo de listas invertidas.
	 */
	public static final int SIZE_OF_LIST_BLOCK = Integer.parseInt(
			getXML("SIZE_OF_LIST_BLOCK"));
	
	/**
	 * Archivo para el arbol BStar.
	 */
	public static final String ARCHIVO_ARBOL_BSTAR = 
		getXML("ARCHIVO_ARBOL_BSTAR");
	
	/**
	 * Archivo para el arbol BStar.
	 */
	public static final Float FACTOR_CARGA_BLOQUES = Float.parseFloat(
		getXML("FACTOR_CARGA_BLOQUES"));

	/**
	 * Lee delarchivo de configuracion.
	 * 
	 * @param nombre clave para recuperar el valor.
	 * @return El valor leido.
	 */
	public static String getXML(final String nombre) {
		if ((builder == null) || (doc == null)) {
			try {
				builder = new SAXBuilder(false);

				doc = builder.build("SpeakerConfig.xml");
				raiz = doc.getRootElement();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Element e = raiz.getChild(nombre);
		return e.getTextTrim();

	}
	
	
	private static List<IunidadDeHabla> stopWords() {

			try {
				builder = new SAXBuilder(false);
				doc = builder.build("StopWords.xml");
				raiz = doc.getRootElement();

			} catch (Exception e) {
				e.printStackTrace();
			}
		List<Element> lista = raiz.getChildren("palabra");
		Iterator<Element> it = lista.iterator();
		ArrayList<IunidadDeHabla> listaFinal = new ArrayList<IunidadDeHabla>();
		while (it.hasNext()) {
			listaFinal.add(PalabrasFactory.getPalabra(it.next().getText()));
		}
			return listaFinal;
	}
	
	public static final List<IunidadDeHabla> LISTA_STOP_WORDS = stopWords();
	
}
