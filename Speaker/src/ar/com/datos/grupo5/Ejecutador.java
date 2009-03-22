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
	 * @return texto con el resultado.
	 */
	public final String test() {
		
		System.out.println("Funciono la invocacion al metodo");
		
		return "Todo OK";
	}
	
	/**
	 * Suma dos numeros ingresados por consola.
	 * @param a .
	 * @param b .
	 * @return el resultado
	 */
	public final int suma(final String a, final String b) {
		
		Integer aa = Integer.valueOf(a);
		Integer bb = Integer.valueOf(b);
		
		return aa.intValue() + bb.intValue();
	}
}
