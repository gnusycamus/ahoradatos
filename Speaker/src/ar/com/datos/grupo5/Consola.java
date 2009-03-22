package ar.com.datos.grupo5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * .
 * @author cristian
 *
 */
public class Consola {

	/**
	 * Clase que implementa los metodos de la consola.
	 */
	private Object invocador = null;
	
	/**
	 * Constructor.
	 * @param <clazz> .
	 * @param clazz .
	 */
	public <clazz> Consola(final Class < ? > clazz) {

			try {		
				invocador = clazz.getConstructors()[0]
				                                    .newInstance(new Object[0]);

			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * .
	 */
	public final void leer() {
		
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		
		String linea = "";
		int pos = 0;
		String[] parametrosString = null;
		Class[] parametrosClass = null;
		String comando = "";
		try {
			
			do {
				System.out.print("> ");
				linea = br.readLine();
				try {
					
					pos = linea.indexOf(" ");
					if (pos > 0) {
						parametrosString = linea.substring(pos + 1).split(" ");
						parametrosClass = new Class[parametrosString.length];
						for (int i = 0; i < parametrosString.length; i++) {
							parametrosClass[i] = String.class;
						}
						comando = linea.substring(0, linea.indexOf(" "));
					} else {
						comando = linea.trim();
						parametrosClass = new Class[0];
					}
					
					
					//Se puede hacer asi
//					for (Method metodo : invocador.getClass().getMethods()) {
//						
//						if (linea.equals(metodo.getName().toString())) {
//							metodo.invoke(invocador, new Object[0]);
//						}
//					}
					
					//O asi y atrapar la excepcion si no se encuentra el metodo.
					try {
						Object resultado = 
						invocador.getClass().getMethod(comando, parametrosClass)
							.invoke(invocador, parametrosString);
						
						System.out.println("El resultado fue: " + resultado.toString());
					} catch (Exception e) {
						System.out.println("No se encuentra el comando solicitado: " + linea);
						e.printStackTrace();
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} while (!linea.equalsIgnoreCase("FIN"));	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
