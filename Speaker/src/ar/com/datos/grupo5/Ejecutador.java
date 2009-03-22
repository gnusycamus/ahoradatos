package ar.com.datos.grupo5;

/**
 * Clase que se crea desde la consola.
 * Por facilidad, hacer que todos los metodos invocados desde la consola
 * reciban string y parsearlos.
 * @author cristian
 *
 */
public class Ejecutador {

	/**
	 * metodo para test.
	 * @param consola en este caso la consola para poder pedir datos y escribir.
	 * @return texto con el resultado.
	 */
	public final String test(final Consola consola ) {
		
		System.out.println("Funciono la invocacion al metodo");
		
		consola.mensaje("Este mensaje esta escrito desde el objeto que invoca a la consola.");
		
		String nombre = consola.obtenerDatos("Ingrese su nombre: ");
		
		System.out.println("Recibi esto: " + nombre);
		return "Todo OK";
	}
	
	/**
	 * Suma dos numeros ingresados por consola.
	 * @param invocador .
	 * @param a .
	 * @param b .
	 * @return el resultado
	 */
	public final int suma(final Consola invocador, final String a, final String b) {
		
		Integer aa = Integer.valueOf(a);
		Integer bb = Integer.valueOf(b);
		
		return aa.intValue() + bb.intValue();
	}
}
