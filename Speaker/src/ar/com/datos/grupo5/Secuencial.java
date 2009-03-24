package ar.com.datos.grupo5;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import ar.com.datos.grupo5.interfaces.Archivo;

/**
 * Clase Para el manejo de archivos secuenciales.
 * @author Diego
 */
public class Secuencial implements Archivo {
	
	private static int tamanioBuffer = 128;
	
	/**
	 * OutputStram para el archivo.
	 */
	private DataOutputStream dataOutputStream = null;
	
	/**
	 * Metodo para Intentar abrir un archivo, pasado por par�metro.
	 * @param archivo Path completo del archivo.
	 * @param modo Es el modo en el que se abrir� el archivo {R,W,R+,A}.
	 * @return devuelve el resultado de la operaci�n.
	 */
	public boolean abrir(final String archivo, final String modo) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Metodo para borrar una cadena en el archivo en el que se est� trabajando.
	 * @param registro Es el registro a buscar en el archivo.
	 * @return Devuelve el resultado de la operaci�n,
	 * y queda posicionado el puntero
	 * en la posici�n donde se encontraba la cadena buscada.
	 * En caso de no encontrar la cadena, devuelve false y el
	 * puntero queda donde
	 * deber�a estar la cadena buscada.
	 */
	public final boolean borrar(final Registro registro) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Metodo para buscar en un archivo, una cadena pasada por par�metro.
	 * @param registro Es el registro a buscar en el archivo.
	 * @return Devuelve el resultado de la b�squeda, quedando posicionado el
	 * puntero en la posici�n donde deber�a estar el patr�n buscado.
	 */
	public final boolean buscar(final Registro registro) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Metodo para cerrar el archivo que se est� manipulando.
	 */
	public void cerrar() {
		// TODO Auto-generated method stub

	}

	/**
	 * Metodo para Intentar crear un archivo, pasado por par�metro.
	 * @param archivo Path completo del archivo.
	 * @param tipo Es el tipo de archivo que se quiere crear {B,T}.
	 * @throws FileNotFoundException 
	 */
	public void crear(final String archivo, final String tipo) throws FileNotFoundException {
		
		OutputStream outputStream = null;
		
		try {
			outputStream = new FileOutputStream(archivo);
		} catch (FileNotFoundException e) {
			throw e;
		}
		
		dataOutputStream = new DataOutputStream(outputStream);

		//TODO inicializar el archivo con los datos de control.
	}

	/**
	 * Metodo para Insertar la cadena en el archivo
	 * en el que se est� trabajando.
	 * @param registro Es el registro que se va a agregar al archivo.
	 */
	public void insertar(final Registro registro) {

		Integer a = 0;
	
//		int start = 0;
//		int endAux = cadena.length();
//		
//		if (cadena.length() > tamanioBuffer) {
//			endAux = tamanioBuffer;
//		}
		
	}

}
