package ar.com.datos.grupo5.compresion.lzp;
import ar.com.datos.grupo5.compresion.aritmetico.LogicaAritmetica;
import ar.com.datos.grupo5.compresion.ppmc.Orden;
import ar.com.datos.grupo5.compresion.ppmc.Contexto;
import ar.com.datos.grupo5.excepciones.SessionException;
import ar.com.datos.grupo5.interfaces.Compresor;

/**
 * @author Led Zeppelin
 * @version 1.0
 * @created 05-Jun-2009 12:58:24 a.m.
 */
public class Lzp implements Compresor {

	private IndiceContexto Indice;
	
	/**
	 * Contexto para las letra? porque carajo se llama Orden?
	 */
	private Orden letrasCtx;
	
	/**
	 * Contextos para las longitudes, para pasar al compresor aritmetico.
	 */
	private Contexto longitudesCtx;
	
	/**
	 * Motor aritmetico para los cararteres emitodos.
	 */
	private LogicaAritmetica motorAritCaracteres;
	
	/**
	 * Motor aritmetico para las longitudes.
	 */
	private LogicaAritmetica motorAritLongitudes;
	
	/**
	 * Contextos y posiciones.
	 */
	private ListaContextos listaContextos;
	
	/**
	 * Flag para saber si la session fue inicializada. 
	 */
	private boolean sesionIniciada = true;

	public Lzp(){
		listaContextos = new ListaContextos();
		motorAritCaracteres = new LogicaAritmetica();
		motorAritLongitudes = new LogicaAritmetica();
		letrasCtx = new Orden();
		longitudesCtx = new Contexto();
	}

	/**
	 * 
	 * @param cadena
	 */
	public String comprimir(String cadena) throws SessionException{
		if (!sesionIniciada) {
			throw new SessionException();
		}
		
		String resultado = "";
		
		//Trabajar con un StringBuffer es mas rapido.
		StringBuffer buffer = new StringBuffer(cadena);
		
		//Si no hay nada aca, entonces es la primera iteracion.
		if (longitudesCtx.getCantidadLetras() == 0) {
			char primero = buffer.charAt(0);
			char segundo = buffer.charAt(1);
			//Saco los 2 primeros.
			buffer.delete(0, 2);
			
			//TODO: Hay que emitir estos caracteres sin longitudes.
		}
		
		resultado += ComprimirInterno(buffer);
		
		return resultado;
	}

	@Override
	public String descomprimir(String datos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void finalizarSession() {
		sesionIniciada = false;
	}

	@Override
	public void iniciarSesion() {
		listaContextos = new ListaContextos();
		motorAritCaracteres = new LogicaAritmetica();
		motorAritLongitudes = new LogicaAritmetica();
		letrasCtx = new Orden();
		longitudesCtx = new Contexto();
		sesionIniciada = true;
	}

	private String ComprimirInterno(StringBuffer cadena) {
		return "";
	}
}