package ar.com.datos.grupo5;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import ar.com.datos.grupo5.utils.Conversiones;

import ar.com.datos.grupo5.interfaces.Registro;

public class RegistroTrie implements Registro {
	/**
	 * Nivel del trie
	 */
	private int nivel;

	/**
	 * nro de Bloque en el que se guarda el registro
	 */
	private int nroBloque;

	private int idtermino;

	private int longitud_termino;

	private String dato;

	/**
	 * Cuantos bytes puedo pasar.
	 */

	private Long moreBytes;

	public RegistroTrie(int nivel, int bloque, int id_termino, int longitud,
			String dato) {
		this.setNivel(nivel);
		this.setNroBloque(bloque);
		this.setIdtermino(id_termino);
		this.setLongitud_termino(longitud);
		this.setDato(dato);
	}

	public byte[] getBytes() {
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		final DataOutputStream dos = new DataOutputStream(bos);
		int tamaniodatosControl = Constantes.SIZE_OF_INT * 3;
		tamaniodatosControl += getLongitud_termino();
		byte[] longdato = this.getDato().getBytes();

		if (tamaniodatosControl == moreBytes) {
			byte[] longdatos = Conversiones.intToArrayByte(tamaniodatosControl);
			byte[] tamdato = Conversiones.intToArrayByte(getLongitud_termino());
			try {
				dos.write(tamdato, 0, tamdato.length);
				moreBytes -= tamdato.length;
				dos.write(longdatos, 0, longdatos.length);
				moreBytes -= longdatos.length;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bos.toByteArray();
	}

	/**
	 * En este caso se devuelve de una vez todos los bytes. Devuelvo true la
	 * primera vez y pongo en false, despues cuando se pregunta nuevamente
	 * devuelvo false, pero pongo en true para que el registro pueda ser usado
	 * denuevo.
	 * 
	 * @return true si hay mas bytes para pedir con getBytes.
	 */
	public final boolean hasMoreBytes() {

		if (moreBytes > 0) {
			return true;
		}
		return false;
	}

	public void setBytes(byte[] buffer, Long offset) {
		// TODO Auto-generated method stub

	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNroBloque(int nroBloque) {
		this.nroBloque = nroBloque;
	}

	public int getNroBloque() {
		return nroBloque;
	}

	public void setIdtermino(int id_termino) {
		this.idtermino = id_termino;
	}

	public int getIdtermino() {
		return idtermino;
	}

	public void setLongitud_termino(int longitud_termino) {
		this.longitud_termino = longitud_termino;
	}

	public int getLongitud_termino() {
		return longitud_termino;
	}

	public void setDato(String dato) {
		this.dato = dato;
	}

	public String getDato() {
		return dato;
	}

}
