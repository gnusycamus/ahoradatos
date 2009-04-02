package ar.com.datos.parser;

import ar.com.datos.UnidadesDeExpresion.Palabra;
import ar.com.datos.grupo5.Constantes;

/**
 * Factory de palabras, que si esta activado la optimizaci�n al espa�ol,
 * se genera un proceso de limpiado de la palabra por su fon�tica de modo que,
 * solo se tienen en cuenta letras de la palabra que influyan a su pronunciaci�n,
 * evitando palabras foneticamente repetidas
 * @see PatterRecognizer
 * @author gabriel
 *
 */
public class PalabrasFactory {

	public static Palabra getPalabra(String palabraEscrita) {
		String fonetica;
		if (Constantes.SPANISH_OPTIMIZATION_ACTIVATED){
		fonetica = PatternRecognizer.posProcesadorFonetico(palabraEscrita);
		}else{
			fonetica = palabraEscrita;
		}
		Palabra nuevapalabra = new Palabra(palabraEscrita, fonetica);
		return nuevapalabra;
	}
}
