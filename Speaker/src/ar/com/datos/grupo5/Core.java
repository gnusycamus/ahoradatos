package ar.com.datos.grupo5;

import java.io.*;
/**
 * @author xxvkue
 *
 */
public class Core {

	/** Atributos de la Clase.
	 *  
	 */
	String 	PalabraActual;
	File	Documento;

	/* Hilo para la parte de grabacion */

	/**
	 * 
	 * @param PathDocumento
	 * @return
	 */
	public String DocumentoAParsear(String PathDocumento) {
		// 0 => Ok, 1 => No se encontro
		int EstadoArchivo = 0;
		try {
			/**
			 * creamos el archivo a leer
			 */
			Documento = new File(PathDocumento);
		} catch (FileNotFoundException e) {
			// El archivo solicitado no se encuentra
			System.out.println(" No se encontro el archivo en la ruta:"
					+ PathDocumento + ". Es necesario crear un archivo.");
		} catch (IOException e) {
			// cachar excepcion
		}
		return "";
	}

	/**
	 * 
	 * @return
	 */
	public int ComenazarGrabacion() {
		return 0;
	}

	/**
	 * 
	 * @return
	 */
	public int FinalizarGrabacion() {
		return 0;
	}

	/**
	 * 
	 * @param PathDocumento
	 * @return
	 */
	public int ReproducirDocumento(String PathDocumento) {
		return 0;
	}

	/**
	 * 
	 * @param TextoAReproducir
	 * @return
	 */
	public int ReproducirTexto(String TextoAReproducir) {
		return 0;
	}

	/**
	 * Recibe la respuesta del usuario si esta bien o no la grabación 
	 * de la palabra. 
	 * En caso de ser correcta la grabacion de la palabra se procede a 
	 * grabar la palabra
	 * y el correspondiente audio, luego devuelve la siquiente palabra 
	 * a ser grabada.
	 * En caso de ser incorrecta la grabacion de la palabra se devuelve 
	 * la proxima palabra
	 * a ser grabada que en este caso es la misma dado que se grabo mal.
	 * @param Respuesta
	 * Confirmación introducida por el usuario. 
	 * @return
	 * Proxima palabra a grabar.
	 */
	public String ConfirmarGrabacion(String Respuesta) {
		if (Respuesta.compareToIgnoreCase("S") == 0) {
			/* 
			 Grabo al archivo 
			 Archivo.WriteAudio(Palabra,Audio);
			 this.PalabraActual = BuscarSiguientePalabra();
			 */
			return this.PalabraActual;
		}
		return this.PalabraActual;
	}
}
