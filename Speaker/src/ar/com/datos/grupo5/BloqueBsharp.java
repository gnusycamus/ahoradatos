package ar.com.datos.grupo5;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;

import ar.com.datos.grupo5.excepciones.UnImplementedMethodException;
import ar.com.datos.grupo5.interfaces.Registro;
import ar.com.datos.grupo5.utils.Conversiones;

/**
 * Clase que permite manipular el arbol B# a nivel fisico.
 * @author LedZeppeling
 */
public class BloqueBsharp extends Directo{
	
	/**
	 * Logger.
	 */
	private static Logger logger  = Logger.getLogger(BloqueBsharp.class);
	
	/**
	 * Tamanio del Bloque en disco.
	 */
	private static int  tamanio = Constantes.SIZE_OF_INDEX_BLOCK;

	/**
	 * Offset del ultimo registro insertado.
	 */
	private long offset = 0L;

	/**
	 * @return El offset del ultimo registro insertado.
	 */
	public final long getOffset() {
		return offset;
	}

	/**
	 * @see Archivo#primero()
	 */
	@Override
	public Registro primero() throws UnImplementedMethodException {
		throw new UnImplementedMethodException("Funcionalidad no implementada.");
	}

	/**
	 * @see Archivo#siguiente()
	 */
	@Override
	public Registro siguiente() throws UnImplementedMethodException {
		
		throw new UnImplementedMethodException("Funcionalidad no implementada.");
	}
	
	/**
	 * M�todo para recuperar un registro de un archivo Directo.
	 * @param offset
	 *              La posici�n en la cual empieza el registro buscado.
	 * @return 
	 *        Retorna el registro que se encuentra en la posici�n offset.
	 * @throws IOException .
	 */
	public final Registro leer(final Long offset) throws IOException {
		Registro reg = null;
		/*
		int longitud = 0;
		
		file.seek(offset);
		
		byte[] bufferInt = new byte[Constantes.SIZE_OF_INT];
        byte[] bufferDato = null;
        file.read(bufferInt, 0, Constantes.SIZE_OF_INT);
        longitud = Conversiones.arrayByteToInt(bufferInt);
        bufferDato = new byte[longitud];
        
        file.read(bufferDato, 0, longitud);
        reg = new RegistroAudio();
        reg.setBytes(bufferDato, (long) longitud);
		*/
		return reg;
	}

}
