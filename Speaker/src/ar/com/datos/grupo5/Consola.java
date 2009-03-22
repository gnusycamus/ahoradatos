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
	 * Para que el que usa la consola pueda pedir datos al usuario.
	 * @param mensaje un mensaje.
	 * @return mensaje de resultado.
	 */
	public final String obtenerDatos(final String mensaje) {
		
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		
		if (mensaje != null && !mensaje.equals("")) {
			System.out.print(mensaje);
		}
		
		String result = "";
		
		try {
			result = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Para que el que usa la consola pueda comunicar algo.
	 * @param mensaje El mensaje que va a la salida standar.
	 */
	public void mensaje(final String mensaje) {
		
		System.out.println(mensaje);
	}
	
	/**
	 * Lee desde la entrada estandar y ejecuta el metodo correspondiente.
	 */
	public final void leer() {
		
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		
		String linea = "";
		int pos = 0;
		Object[] params = null;
		Class[] paramsClass = null;
		String comando = "";
		try {
			
			do {
				System.out.print("> ");
				linea = br.readLine();
				try {
					
					pos = linea.indexOf(" ");
					if (pos > 0) {
						params = linea.substring(pos + 1).split(" ");
						params[0] = this;
						paramsClass = new Class[params.length];
						paramsClass[0] = this.getClass();
						for (int i = 0; i < params.length; i++) {
							paramsClass[i] = String.class;
						}
						comando = linea.substring(0, linea.indexOf(" "));
					} else {
						comando = linea.trim();
						paramsClass = new Class[1];
						paramsClass[0] = this.getClass();
						params = new Object[1];
						params[0] = this;
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
						invocador.getClass().getMethod(comando, paramsClass)
							.invoke(invocador, (Object[]) params);
						
						System.out.println("El resultado fue: " 
								+ resultado.toString());
						
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
