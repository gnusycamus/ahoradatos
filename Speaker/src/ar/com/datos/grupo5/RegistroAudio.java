
package ar.com.datos.grupo5;

import ar.com.datos.grupo5.interfaces.Registro;

/**
 * Esta Clase implementa la interfaz del registro para audio.
 * @see ar.com.datos.grupo5.interfaces.Registro
 * @author Diego
 */
public class RegistroAudio implements Registro {

	private byte[] buffer;
	private Long longitud;
	private byte[] dato;
	
	/** 
	 * M�todo toBytes(). Nos sirve para devoler la cadena de bytes a escribir.
	 * @see ar.com.datos.grupo5.interfaces.Registro#toBytes()
	 */
	public byte[] toBytes() {
		String cadena;
		/**
		 * 1� Agrego la longitud.
		 */
		this.longitud = (long)this.dato.toString().length();
		cadena = this.longitud.toString();
		/**
		 * Por ultimo, el dato.
		 */
		cadena += this.dato;
		this.buffer = cadena.getBytes();
		return this.buffer;
	}

	/**
	 * M�todo para devolver la longitud.
	 * @return la longitud del dato.
	 */
	public Long getLongitud() {
		return longitud;
	}

	/**
	 * M�todo para setear la longitud.
	 * @param longitud La longitud a setear.
	 */
	public void setLongitud(Long longitud) {
		this.longitud = longitud;
	}

	/**
	 * M�todo para devolver el dato.
	 * @return El dato.
	 */
	public byte[] getDato() {
		return dato;
	}

	/**
	 * M�todo para setear el dato.
	 * @param dato El dato a setear.
	 */
	public void setDato(byte[] dato) {
		this.dato = dato;
	}

}
