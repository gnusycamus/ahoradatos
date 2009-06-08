package ar.com.datos.tests;

import java.io.IOException;

import org.apache.log4j.Logger;

import ar.com.datos.grupo5.Constantes;
import ar.com.datos.grupo5.compresion.lzp.Lzp;
import ar.com.datos.grupo5.excepciones.SessionException;
import ar.com.datos.grupo5.interfaces.Compresor;

public class TestLZP {

	private static Logger LOG = Logger.getLogger(TestBinario.class);
	
	public static void main(String[] args) throws IOException, SessionException {
		
		Compresor comp = new Lzp();
		String cadena = "ABCC,BABC,CBCC,BCC";
		String[] cadena2 = cadena.split(",");
		String result = "";
		
		comp.iniciarSesion();
		for (int i = 0; i < cadena2.length; i++) {
			result += comp.comprimir(cadena2[i]);
		}
		LOG.info(cadena.replace(",", ""));
		LOG.info(result);
		comp.imprimirHashMap();
		comp.finalizarSession();
		byte[] cad = "A".getBytes(Constantes.CHARSET_UTF16);
		LOG.info("Cad (A) en 16 bits = " + cad.toString());
	}
}
