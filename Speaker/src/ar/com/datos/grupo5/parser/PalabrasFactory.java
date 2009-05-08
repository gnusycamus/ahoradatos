package ar.com.datos.grupo5.parser;

import ar.com.datos.grupo5.Constantes;
import ar.com.datos.grupo5.UnidadesDeExpresion.IunidadDeHabla;
import ar.com.datos.grupo5.UnidadesDeExpresion.Palabra;

/**
 * Factory de palabras, que si esta activado la optimizaci�n al espa�ol, se
 * genera un proceso de limpiado de la palabra por su fon�tica de modo que, solo
 * se tienen en cuenta letras de la palabra que influyan a su pronunciaci�n,
 * evitando palabras foneticamente repetidas.
 * 
 * @see PatterRecognizer
 * @author LedZeppeling
 * 
 */
public class PalabrasFactory {

	/**
	 * 
	 * @param palabraEscrita .
	 * @return La palabra.
	 */
	public static Palabra getPalabra(String palabraEscrita) {
		
		String fonetica;
		boolean pronunciable = esPronunciable(palabraEscrita);
		
		/*
		 * Verifico si esta activada la optimizaci�n al espa�ol, en cuyo caso el
		 * equivalente fon�tico puede ser diferente a la palabra escrita, seg�n
		 * los usos de este idioma en particular.
		 */
		if (palabraEscrita.equalsIgnoreCase("~")) {
			palabraEscrita = ".";
		}
		
		if (palabraEscrita.equalsIgnoreCase("�")) {
			palabraEscrita = ",";
		}
		
		if (Constantes.SPANISH_OPTIMIZATION_ACTIVATED) {
			fonetica = PatternRecognizer.posProcesadorFonetico(palabraEscrita);
		} else {
			fonetica = palabraEscrita;
		}
		Palabra nuevapalabra = new Palabra(palabraEscrita, fonetica);

		nuevapalabra.setPronunciable(pronunciable);
		nuevapalabra.setStopWord(chequeaStopWord(nuevapalabra));
		return nuevapalabra;
	}
	
	
	
public static Palabra getPalabraSinChequeoStop(String palabraEscrita) {
		
		String fonetica;
		boolean pronunciable = esPronunciable(palabraEscrita);
		
		/*
		 * Verifico si esta activada la optimizaci�n al espa�ol, en cuyo caso el
		 * equivalente fon�tico puede ser diferente a la palabra escrita, seg�n
		 * los usos de este idioma en particular.
		 */
		if (palabraEscrita.equalsIgnoreCase("~")) {
			palabraEscrita = ".";
		}
		
		if (palabraEscrita.equalsIgnoreCase("�")) {
			palabraEscrita = ",";
		}
		
		if (Constantes.SPANISH_OPTIMIZATION_ACTIVATED) {
			fonetica = PatternRecognizer.posProcesadorFonetico(palabraEscrita);
		} else {
			fonetica = palabraEscrita;
		}
		Palabra nuevapalabra = new Palabra(palabraEscrita, fonetica);

		nuevapalabra.setPronunciable(pronunciable);
		return nuevapalabra;
	}
	
	
	/**
	 * 
	 * @param palabra
	 *            La palabra.
	 * @return true si es pronunciable.
	 */
	private static boolean esPronunciable(final String palabra) {

		char empiezaCon = palabra.charAt(0);
		
		/*
		 * verifico los caracteres de control que vienen en la palabra para
		 * saber si el signo de puntuaci�n se debe decir o no esta informaci�n
		 * la coloca el analizador contextual del patterRecognizer. por defecto
		 * toda palabra debe pronunciarse.
		 */
		switch (empiezaCon) {
		case '�':
			return true;

		case '~':
			return true;

		case '.':
			return false;

		case ',':
			return false;

		default:
			return true;
		}

	}
  /**
   * Revisa si la palabra pasada esta en la lista de stop words conocidas.
   * @param palabra a consultar.
   */
   private static boolean chequeaStopWord(IunidadDeHabla palabra) {
        ParserStopWords stopWords = new ParserStopWords();
        return stopWords.IsStopWord(palabra);
   }
}
