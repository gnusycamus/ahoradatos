package ar.com.datos.grupo5;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;

import ar.com.datos.UnidadesDeExpresion.IunidadDeHabla;
import ar.com.datos.grupo5.interfaces.InterfazUsuario;
import ar.com.datos.grupo5.registros.RegistroDiccionario;
import ar.com.datos.parser.ITextInput;
import ar.com.datos.parser.TextInterpreter;
import ar.com.datos.reproduccionaudio.exception.SimpleAudioPlayerException;

/**
 * @author LedZeppeling
 *
 */
public class Core {


	/**
	 * Obtiene todas las posibles palabras a ser lidas. 
	 */
	private ITextInput parser;
	
	/**
	 * Contiene todas las palabras conocidas por el sistema.
	 */
	private Diccionario diccionario;
	
	/**
	 * Permite grabar y reproducir el audio correspondiente a un palabra.
	 */
	private AudioManager audioManager;
	
	/**
	 * Se encarga de manejar los documentos en disco.
	 */
	private DocumentsManager documentManager;
	
	/**
	 * 
	 */
	private AudioFileManager audioFileManager;
	
	/**
	 * Conteniene todas las palabras a grabar o a leer del documento ingresado.
	 */
	private Collection<IunidadDeHabla>  contenedor; 

	private FTRSManager ftrsManager;
	/**
	 * Logger para la clase.
	 */
	private static Logger logger = Logger.getLogger(Core.class);
	
	private long tiempoConsulta;
	
	/**
	 * Busca las palabras a grabar, graba el audio y lo guarda.
	 * 
	 * @param invocador
	 *            Interfaz por la cual se realizan peticiones al usuario y se
	 *            obtienen las respuestas.
	 * @param pathDocumento
	 *            Direcci�n donde se encuentra el archivo a ser examinado.
	 * @return devuelve un mensaje informando el estado final del proceso.
	 */
	public final String load(final InterfazUsuario invocador,
			final String pathDocumento) {
		
		logger.debug("Entre en load");
		
		try {
			Iterator<IunidadDeHabla> iterador;

			// Cargo el parser con el documento en modo aprendizaje
			try {
				contenedor = this.parser.modoCarga(pathDocumento, true, this.documentManager);
			} catch (FileNotFoundException e) {
				return "No se pudo abrir el archivo: " + pathDocumento;
			} catch (Exception e) {
				logger.error("Error al crear contenedor: " + e.getMessage(), e);
				return "Error inesperado, consulte al proveedor del software";
			}
			
			logger.debug("tengo el contenedor de palabras.");
			
			Long offsetRegistroAudio;
			
			if (!abrirArchivo(invocador)) {
				return "Intente denuevo";
			}
			Long offsetDoc = 0L;
			IunidadDeHabla elemento;
			iterador = contenedor.iterator();
			
			// Mientras tenga palabras para verificar consulto
			while (iterador.hasNext()) {

				elemento = iterador.next();
				logger.debug("Itero una vez.txt.");
				
				// Si no es StopWord entonces utilizo el Ftrs.
				if (!elemento.isStopWord()) {
					
					this.ftrsManager.validarTermino(elemento.getEquivalenteFonetico(), offsetDoc);
					
				}
				
				/*
				 * Si es una palabra pronunciable la proxima palabra, sino pido
				 * el audio para la misma. 
				 * Si, es pronunciable, si la encuntra
				 * sigo con la proxima palabra, sino pido el audio para la
				 * misma.
				 */
				if (this.diccionario.buscarPalabra(elemento
						.getEquivalenteFonetico()) != null) {
					logger.debug("existe en el archivo de texto.");
					continue;
				}
				
				logger.debug("No esta en el archivo de texto.");
				
				
				// Si no encontro la palabra pido ingresar el audio
				String mensaje = new String(
						"Para ingresar el audio para la palabra: "
								+ elemento.getTextoEscrito());

				String respuesta = "0";
				invocador.mensaje(mensaje);

				int resultado;
				//pido que grabe hasta que sea correcta la grabaci�n
				while (!respuesta.equalsIgnoreCase("S")) {
					
					// Protocolo de Grabaci�n
					resultado = this.iniciarGrabacion(invocador);
					// Segun el resultado
					switch(resultado) {
						case -1:
							return "Operacion cancelada.";
						case -2:
							continue;
						default:
					}
					
					// Protocolo para terminar la grabaci�n
					resultado = this.finalizarGrabacion(invocador);
					
					//Segun el resultado
					switch(resultado) {
						case -1:
							return "Operacion cancelada.";
						default:
					}
					
					this.playWord();
				    
				    mensaje = "La grabaci�n ha sido correcta? S/N: ";
				    respuesta = invocador.obtenerDatos(mensaje);
				    }

				offsetRegistroAudio = this.audioFileManager
						.agregar(this.audioManager.getAudio());
				
				//Agrego la palabra al diccionario 
				this.diccionario.agregar(elemento.getEquivalenteFonetico(),
						offsetRegistroAudio);
				
			}
			
			this.ftrsManager.generarListasInvertidas();

			cerrarArchivo(invocador);
		} catch (SimpleAudioPlayerException e) {
			logger.error("Error: " + e.getMessage());
			return "Error en dispositivo de audio";
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
			return "Error inesperado";
		}
		
		logger.debug("Sali de al funcion load");
		return "Las palabras han sido correctamente ingresadas.";
	}

	

	/**
	 * 
	 * @param invocador .
	 */
	public final void help(final InterfazUsuario invocador) {
		
		String mensaje = "Funcion: load \n"
			+ "Caracteristicas: carga un documento para almacenar las palabras "
			+ "desconocidas \n"
			+ "Uso: load <\"path_absoluto_del_documento\"> \n"
			+ "Ej: load \"/home/usuario/Escritorio/prueba.txt\" \n\n"

			+ "Funcion: playDocument \n"
			+ "Caracteristicas: carga un documento reproduciendo las "
			+ "palabras reconocidas \n"
			+ "Uso: playDocument <\"path_absoluto_del_documento\"> \n"
			+ "Ej: load \"/home/usuario/Escritorio/prueba.txt\" \n\n"

			+ "Funcion: playText \n"
			+ "Caracteristicas: reproduce el texto ingresado, omitiendo las "
			+ "palabras que no conoce \n"
			+ "Uso: playText <\"texto ingresado\"> \n"
			+ "Ej: playText \"hola, como estas\" \n\n"

			+ "Funcion: clear \n"
			+ "Caracteristicas: borra la pantalla \n"
			+ "Uso: clear \n"
			+ "Ej: clear \n\n"

			+ "Funcion: help \n"
			+ "Caracteristicas: muestra los comandos disponibles para su ejecuci�n \n"
			+ "Uso: help \n" + "Ej: help \n\n"

			+ "Funcion: fin \n" + "Caracteristicas: sale del programa \n"
			+ "Uso: fin \n" + "Ej: fin \n\n";
		
		this.clear(invocador);
		invocador.mensaje(mensaje);
	}
	
	
	/**
	 * 
	 * @param invocador .
	 */
	public final void clear(final InterfazUsuario invocador) {
		
		String clear = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
				+ "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
				+ "\n\n\n\n\n\n";
		
		invocador.mensaje(clear);
	}
	
	
	/**
	 * Da comienzo a la grabaci�n del audio de la palabra en cuesti�n.
	 * 
	 * @param invocador
	 *            Interfaz por la cual se realizan peticiones al usuario y se
	 *            obtienen las respuestas.
	 * @return 0 si la grabaci�n termino bien, -1 si la grabaci�n fue cancelada.
	 */
	private int iniciarGrabacion(final InterfazUsuario invocador) {

		String mensaje;
		String respuesta;
	
		mensaje = "Para iniciar la grabaci�n ingrese la tecla i "
				+ "y luego enter: ";
		respuesta = invocador.obtenerDatos(mensaje);
		while (!respuesta.equalsIgnoreCase("i")
				&& !respuesta.equalsIgnoreCase("c")) {
			mensaje = "Comando incorrecto, por favor presione la tecla i.";
			respuesta = invocador.obtenerDatos(mensaje);
		}
		
		if (respuesta.equalsIgnoreCase("c")) {
			return -1;
		}
		
		OutputStream byteArray = new ByteArrayOutputStream();
		
		try {
			// Pido grabar el audio
			this.audioManager.grabar(byteArray);
			return 0;
		} catch (Exception e) {
			logger
				.error("Error, no se pudo grabar el audio intentelo nuevamente."
							+ e.getMessage());
			return -2;
		}
		
	}
	
	/**
	 * Termina la grabaci�n del audio, tanto con un final correcto como por una
	 * cancelaci�n del usuario.
	 * 
	 * @param invocador
	 *            Interfaz por la cual se realizan peticiones al usuario y se
	 *            obtienen las respuestas.
	 * @return 0 si la grabaci�n termino bien, -1 si la grabaci�n fue cancelada.
	 */
	private int finalizarGrabacion(final InterfazUsuario invocador) {
		
		String mensaje, respuesta;

		mensaje = "Para detener la grabaci�n ingrese la tecla f y "
				+ "luego enter: ";
		respuesta = invocador.obtenerDatos(mensaje);
		while (!respuesta.equalsIgnoreCase("f")
				&& !respuesta.equalsIgnoreCase("c")) {
			
			mensaje = "Comando incorrecto, por favor presione la tecla f.";
			respuesta = invocador.obtenerDatos(mensaje);
		}
		//Termino la grabacion
		this.audioManager.terminarGrabacion();
		if (respuesta.equalsIgnoreCase("f")) {
			return 0;
		} else {
			return -1;
		}
	}
	
	/**
	 * Reproduce un documento entero.
	 * 
	 * @param invocador
	 *            .
	 * @param pathDocumento
	 *            direccion del archivo que va a ser leido.
	 * @return devuelve un mensaje informando el estado final del proceso.
	 */
	public final String playDocument(final InterfazUsuario invocador,
			final String pathDocumento) {
		
		/*
		 * TODO: Version Vieja
		 */
		try {
			Iterator<IunidadDeHabla> iterador;
			
			// Mando a parsear el documento y obtengo un collection
			try {
				contenedor = this.parser.modoLectura(pathDocumento, true);
			} catch (FileNotFoundException e) {
				return "No se pudo abrir el archivo: " + pathDocumento;
			} catch (Exception e) {
				logger.error("Error al crear contenedor: " + e.getMessage(), e);
				return "Error inesperado, consulte al proveedor del software";
			}
			
			if (!abrirArchivo(invocador)) {
				return "Intente denuevo";
			}
			
			IunidadDeHabla elemento;
			iterador = contenedor.iterator();
			// Mientras tenga palabras para verificar consulto
			while (iterador.hasNext()) {
				
				elemento = (IunidadDeHabla) iterador.next();
				
				// Si lo encontro sigo en el bucle
				if (elemento.esPronunciable()) {
				RegistroDiccionario registro = this.diccionario
					.buscarPalabra(elemento.getEquivalenteFonetico());
				
				if (registro != null) {
					invocador.mensajeSinSalto(elemento.getTextoEscrito() + " ");
					playWord(this.audioFileManager.leerAudio(registro
								.getOffset()));
					audioManager.esperarFin();
				}

				}
			}
			logger.debug("Sali de la funcion playDocument");

			invocador.mensaje("");
			cerrarArchivo(invocador);
		} catch (SimpleAudioPlayerException e) {
			logger.error("Error: " + e.getMessage());
			return "Error en dispositivo de audio.";
		} catch (Exception e) {
			logger.error("Error : " + e.getMessage());
			return "Error inesperado.";
		}
			
		/*
		 * TODO: Version Nueva
		 * 
		 */
		return "Reproduccion finalizada";
	}

	/**
	 * Reproduce un texto introducido palabra por palabra.
	 * 
	 * @param textoAReproducir
	 *            Las palabras a leer.
	 * @param invocador .
	 */
	public final void playText(final InterfazUsuario invocador,
			final String textoAReproducir) {
		
		try { 	
			
			Iterator<IunidadDeHabla> iterador;
			// Mando a parsear el documento y obtengo un collection
			try {
	
				contenedor = this.parser.modoLectura(textoAReproducir, false);
			} catch (Exception e) {
				logger.error("Error al crear contenedor: " + e.getMessage(), e);
			}
				
			if (!abrirArchivo(invocador)) {
				invocador.mensaje("no eexisten los archivos de "
						+ "diccionario o datos ");
			}
	
			IunidadDeHabla elemento;
			iterador = contenedor.iterator();
	
			while (iterador.hasNext()) {
	
				elemento = iterador.next();
				if (elemento.esPronunciable()) {
					RegistroDiccionario registro = this.diccionario
							.buscarPalabra(elemento.getEquivalenteFonetico());
					if (registro != null) {
						invocador.mensajeSinSalto(elemento.getTextoEscrito()
								+ " ");
						playWord(this.audioFileManager.leerAudio(registro
								.getOffset()));
						audioManager.esperarFin();
	
					}
				}
			}
			logger.debug("Sali de al funcion playText");
			
		} catch (SimpleAudioPlayerException e) {
			logger.error("Error: " + e.getMessage(), e);
			invocador.mensaje("Error en dispositivo de audio");
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage(), e);
			invocador.mensaje("Error inesperado");
		}
		
		invocador.mensaje("");
	}

	/**
	 * Reproduce la �ltima palabra leida.
	 * 
	 * @throws SimpleAudioPlayerException .
	 */
	public final void playWord() throws SimpleAudioPlayerException {
		this.audioManager.reproducir();
	}

	/**
	 * Reproduce el audio que recibe.
	 * 
	 * @param audioAReproducir
	 *            es el audio que se va a reproducir.
	 * @throws SimpleAudioPlayerException .
	 */
	public final void playWord(final InputStream audioAReproducir)
			throws SimpleAudioPlayerException {
		this.audioManager.reproducir(audioAReproducir);
	}
	
	/**
	 * Constructor de la clase.
	 */
	public Core() {
		this.diccionario = new Diccionario();
		this.audioManager = new AudioManager();
		this.parser = new TextInterpreter();
		this.audioFileManager = new AudioFileManager();
		this.documentManager = new DocumentsManager();
		this.ftrsManager = new FTRSManager();
	}
	
	/**
	 * Se llama al terminar, para dar un mensaje.
	 * 
	 * @param invocador .
	 */
	public final void quit(final InterfazUsuario invocador) {

		invocador.mensaje("gracias por usar TheSpeaker");
	}
	
	/**
	 * Cierra los archivos.
	 * 
	 * @param invocador
	 *            .
	 * @return true si pudo cerrar los archivos.
	 */
	private boolean cerrarArchivo(final InterfazUsuario invocador) {
		
		try {
			
			if (this.diccionario != null) {
				this.diccionario.cerrar();
			}
			if (this.audioFileManager != null) {
				this.audioFileManager.cerrar();
			}
			if (this.documentManager != null) {
				this.documentManager.cerrar();
			}
			return true;

		} catch (Exception e) {
			
			try {
				if (this.audioFileManager != null) {
					this.audioFileManager.cerrar();
				}
			} catch (Exception g) {
				invocador.mensaje("Error al cerrar el archivo de audio.");
			}
			try {
				if (this.documentManager != null) {
					this.documentManager.cerrar();
				}
			} catch (Exception g) {
				invocador.mensaje("Error al cerrar el archivo de documentos.");
			}
			
			invocador.mensaje("Error al cerrar el archivo de diccionario.");
			
			this.ftrsManager.cerrarArchivos();
			return false;
		}
	}
	
	/**
	 * Abre los archivo.
	 * 
	 * @param invocador .
	 * @return true si pudo abrir los archivos.
	 */
	private boolean abrirArchivo(final InterfazUsuario invocador) {

		/*
		 * Abro el archivo para la carga y consulta del diccionario
		 */
		try {
			this.diccionario.abrir(Constantes.ARCHIVO_DICCIONARIO,
					Constantes.ABRIR_PARA_LECTURA_ESCRITURA);
		} catch (FileNotFoundException e) {
			invocador.mensaje("No se pudo abrir el diccionario.");
			return false;
		}

		logger.debug("Abrio el archivo Diccionario");

		/*
		 * Abro el archivo para la carga y consulta de los audios
		 */
		try {
			this.audioFileManager.abrir(Constantes.ARCHIVO_AUDIO,
					Constantes.ABRIR_PARA_LECTURA_ESCRITURA);
		} catch (FileNotFoundException e) {
			invocador.mensaje("No se pudo abrir el archivo de audio.");
			return false;
		}

		logger.debug("Abrio el archivo Audio");
		
		/*
		 * Abro el archivo para la carga y consulta de los documentos
		 */
		try {
			this.documentManager.abrir(Constantes.ARCHIVO_DOCUMENTOS,
					Constantes.ABRIR_PARA_LECTURA_ESCRITURA);
		} catch (FileNotFoundException e) {
			invocador.mensaje("No se pudo abrir el archivo de documentos.");
			return false;
		}
		
		logger.debug("Abrio el archivo documentos");
		
		this.ftrsManager.abrirArchivos();
		return true;
	}
	

	/**
	 * Metodo que se encarga de administrar la consulta del usuario.
	 * @param invocador 
	 * 			.
	 * @param query cadena de caracteres que debe tener los documentos.
	 * @return
	 * 		Devuelve un mensaje con el estado final del proceso.
	 */
	public final String query(final InterfazUsuario invocador, final String query) {
		this.tiempoConsulta = System.currentTimeMillis();
		/*
		 * TODO: 
		 * 		1) enviar consulta al FTRS
		 * Supongo que el FTRS me devolvera 5 elementos donde tenga offsetDocumento 
		 * y deberia estar ordenado por prioridad
		 * 		this.ranking = resultadoFTRS
		 * Iterator<Long> it;
		 * it = this.ranking.iterator();
		 * Long nodo;
		 * Integer i = 1;
		 * invocador.mensaje("Seleccione un de los documentos para ser reproducido:");
		 * while (it.hasNext()) {
		 * 		nodo = it.next();
		 * 		String mensaje = i.toString() + ". " + this.documentManager.leerDocumento(nodo);  
		 * 		invocador.mensaje(mensaje);
		 * }
		 * 
		 * invocador.mensaje("Para reproducir el documento: playDocument <Nro del lista>");
		 * 
		 */
		if (query.isEmpty()) {
			return "Consulta inv�lida.";
		}
		
		Float tiempoFinal = (float)(System.currentTimeMillis() - this.tiempoConsulta) / 1000;
		return tiempoFinal.toString() + " segundos";
	}
}