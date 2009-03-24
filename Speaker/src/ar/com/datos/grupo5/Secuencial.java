package ar.com.datos.grupo5;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import ar.com.datos.grupo5.interfaces.Archivo;
import ar.com.datos.grupo5.interfaces.Registro;

/**
 * Clase Para el manejo de archivos secuenciales.
 * @see ar.com.datos.grupo5.interfaces.Archivo
 * @see ar.com.datos.grupo5.interfaces.Registro
 * @author Diego
 */
public class Secuencial implements Archivo {

	/**
	 * Archivo en disco.
	 */
	private RandomAccessFile file = null;
	
	/* Metodo para Intentar abrir un archivo, pasado por par�metro.
	 * @see ar.com.datos.grupo5.interfaces.Archivo#abrir()
	 */
	public boolean abrir(final String archivo, final String modo) {
		return false;
	}

	/* Metodo para borrar una cadena en el archivo en el que se est� trabajando.
	 * @see ar.com.datos.grupo5.interfaces.Archivo#borrar()
	 */
	public final boolean borrar(final Registro registro) {
		// TODO Auto-generated method stub
		return false;
	}

	/* Metodo para buscar en un archivo, una cadena pasada por par�metro.
	 * @see ar.com.datos.grupo5.interfaces.Archivo#buscar()
	 */
	public final boolean buscar(final Registro registro) {
		// TODO Auto-generated method stub
		return false;
	}

	/* Metodo para cerrar el archivo que se est� manipulando.
	 * @see ar.com.datos.grupo5.interfaces.Archivo#cerrar()
	 */
	public void cerrar() {
		// TODO Auto-generated method stub

	}

	/* Metodo para Intentar crear un archivo, pasado por par�metro.
	 * @see ar.com.datos.grupo5.interfaces.Archivo#crear()
	 */
	public final void crear(final String archivo, final String tipo)
			throws FileNotFoundException {
		
		try {
			
			file = new RandomAccessFile(archivo, "rw");

		} catch (FileNotFoundException e) {
			throw e;
		}
	}

	/* Metodo para Insertar la cadena en el archivo
	 * @see ar.com.datos.grupo5.interfaces.Archivo#insertar()
	 */
	public void insertar(final Registro registro) {

		Integer a = 0;
	
		int start = 0;
		int bytesTotal = registro.toBytes().length;
		int bytesAux = Constantes.TAMANIO_BUFFER_ESCRITURA;
		int bytesEscritos = 0;
		
		if (bytesTotal < bytesAux) {
			bytesAux = bytesTotal;
		}
    
//        byte[] bytes = new byte[Constantes.TAMANIO_BUFFER_ESCRITURA];
//    
//        // Read in the bytes
//        int offset = 0;
//        int numRead = 0;
//        while (offset < bytes.length
//               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
//            offset += numRead;
//        }
//
//        return bytes;
		
	}
}
