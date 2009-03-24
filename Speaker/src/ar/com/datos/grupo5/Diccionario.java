package ar.com.datos.grupo5;
import ar.com.datos.grupo5.interfaces.Archivo;



/**
 * Clase que permite manipular el diccionario
 * @author Diego
 *
 */
public class Diccionario {
	
	private Archivo archivo;
	
	/**
	 * @param archivo El archivo f�sico diccionario.
	 */
	private void setArchivo(final Archivo archivo) {
		this.archivo = archivo;
	}
	/**
	 * Metodo para cargar el diccionario, accediendo al archivo.
	 * @param archivo Path completo del archivo.
	 * @param modo Es el modo en el que se abrir� el archivo {R,W,R+,A}.
	 * @return devuelve el resultado de la operaci�n.
	 */
	public boolean  cargar(final String archivo, final String modo) {
		return this.archivo.abrir(archivo, modo);
	}
	/**
	 * M�todo que devuelve el archivo referenciado.
	 * @return El archivo diccionario.
	 */
	public Archivo getArchivo() {
		return archivo;
	}
	/**
	 * M�todo que cierra el diccionario.
	 */
	public void cerrar() {
		this.archivo.cerrar();
	}

}
