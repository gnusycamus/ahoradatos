package ar.com.datos.grupo5;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Constantes de la aplicacion.
 * @author cristian
 *
 */
public class Constantes {

	/**
	 * Constructor.
	 */
	public Constantes() {
		super();
	}
	
	/**
	 * Tama�o del buffer de lectura.
	 */
	public static final int TAMANIO_BUFFER_LECTURA = 128;
	
	/**
	 * Tama�o del buffer de escritura.
	 */
	public static final int TAMANIO_BUFFER_ESCRITURA = 128;
	
	/**
	 * Abrir un archivo para lectura.
	 */
	public static final String ABRIR_PARA_LECTURA = "r";
	
	/**
	 * Abrir un archivo para lectura y escritura.
	 */
	public static final String ABRIR_PARA_LECTURA_ESCRITURA = "rw";
		
    /**
     * Tama�o en bytes del long.
	 */
	public static final int SIZE_OF_LONG = 8;
	
	/**
	 * Tama�o en bytes del int.
	 */
	public static final int SIZE_OF_INT = 4;
	/**
	 * Tama�o
	 */
	public static final int BUFFER_LECTURA_TEXT_INPUT = 100;
	
}
