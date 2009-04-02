package ar.com.datos.grupo5;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;

import ar.com.datos.grupo5.interfaces.Archivo;
import ar.com.datos.grupo5.interfaces.Registro;

/**
 * Clase que permite manipular el diccionario.
 * 
 * @see ar.com.datos.grupo5.interfaces.Archivo
 * @author Diego
 * 
 */
public class Diccionario {
	
	/**
	 * Logger.
	 */
	private static final Logger logger = Logger.getLogger(Diccionario.class);

	/**
	 * Archivo que contendr� las palabras y que ser� manejado por el
	 * diccionario.
	 */
	private Archivo archivo;

	/**
	 * @param archivo
	 *            El archivo f�sico diccionario.
	 */
	private void setArchivo(final Archivo archivo) {
		this.archivo = archivo;
	}
	
	/**
	 * Metodo para cargar el diccionario, accediendo al archivo.
	 * 
	 * @see ar.com.datos.grupo5.interfaces.Archivo#cargar()
	 * @throws FileNotFoundException
	 */
	public boolean cargar(final String archivo, final String modo)
			throws FileNotFoundException {
		return this.archivo.abrir(archivo, modo);
	}
	
	/**
	 * M�todo que cierra el diccionario.
     * @see ar.com.datos.grupo5.interfaces.Archivo#cerrar()
	 */
	public final void cerrar() throws IOException {
		this.archivo.cerrar();
	}
	
	/**
	 * Busca en el diccionario la palabra que recibe.
	 * @param palabra La palabra que se quiere buscar.
	 * @return null si no encuentra la palabra, si no devuelve elregitro.
	 */
	public final RegistroDiccionario buscarPalabra(final String palabra) {
		
		RegistroDiccionario reg = (RegistroDiccionario) archivo.primero();
		
		while (reg != null) {
			if (reg.getDato().equals(palabra)) {
				break;
			}
			reg = (RegistroDiccionario) archivo.siguiente();
		}
		return reg;
	}
	
	/**
	 * Metodo para agregar una palabra al diccionario.
	 * @param palabra La palabra que se quiere agregar al diccionario.
	 * @param offset Es la posici�n de la palabra dentro del archivo de audio.
	 * @return retorna TRUE si pudo agregr la palabra, o FALSE en caso contrario.
	 * @throws FileNotFoundException
	 * @link ar.com.datos.grupo5.interfaces.Archivo#insertar(Registro)
	 */
	public boolean agregar(final String palabra, final Long offset){
		
		RegistroDiccionario reg = new RegistroDiccionario();
		
		reg.setOffset(offset);
		reg.setDato(palabra);
		try {
			this.archivo.insertar(reg);
			return true;
		} catch (Exception e) {
			return false;
		}		
	}

	
	/**
	 * Constructor de la clase.
	 *
	 */
	public Diccionario(){
		this.archivo = new Secuencial();
	}
}

