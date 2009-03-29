package ar.com.datos.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternRecognizer {
	/**
	 * M�todo que limpia cualquier cadena de caracteres pasada por par�metro,
	 * modificando todos aquellos caracteres considerados inv�lidos. Son
	 * considerados caracteres v�lidos, todos aquellos que aporten datos
	 * pronunciables a la cadena de texto tomado como base el lenguaje Espa�ol.
	 * Ejemplo de uso, se toma: �,�,�,� y que no tienen sentido real y se
	 * reemplazan por "a".
	 * 
	 * @param linea
	 *            String con la cadena a limpiar convertido a min�sculas
	 * @return String con la cadena purificada
	 */
	private static String analisisLexico(String linea) {

		Pattern patron;
		Matcher comparador;

		// busco si hay problemas que arreglar
		patron = Pattern.compile("[�����������������~]");
		comparador = patron.matcher(linea);
		if (comparador.find()) {
			linea = correctorLexico(linea);
		}
		return linea;
	}

	private static String correctorLexico(String termino) {
		Pattern patron;
		Matcher comparador;

		// ---Reemplazo las a---------
		patron = Pattern.compile("[����]");
		comparador = patron.matcher(termino);
		termino = comparador.replaceAll("a");

		patron = Pattern.compile("[�]");
		comparador = patron.matcher(termino);
		termino = comparador.replaceAll("�");

		// ---Reemplazo las e---------
		patron = Pattern.compile("[�]");
		comparador = patron.matcher(termino);
		termino = comparador.replaceAll("�");

		patron = Pattern.compile("[��]");
		comparador = patron.matcher(termino);
		termino = comparador.replaceAll("e");

		// ---Reemplazo las i---------
		patron = Pattern.compile("[�]");
		comparador = patron.matcher(termino);
		termino = comparador.replaceAll("�");

		patron = Pattern.compile("[��]");
		comparador = patron.matcher(termino);
		termino = comparador.replaceAll("i");

		// ---Reemplazo las o---------
		patron = Pattern.compile("[�]");
		comparador = patron.matcher(termino);
		termino = comparador.replaceAll("�");

		patron = Pattern.compile("[���]");
		comparador = patron.matcher(termino);
		termino = comparador.replaceAll("o");

		// ---Reemplazo las u---------
		patron = Pattern.compile("[�]");
		comparador = patron.matcher(termino);
		termino = comparador.replaceAll("�");

		patron = Pattern.compile("[�]");
		comparador = patron.matcher(termino);
		termino = comparador.replaceAll("u");

		// reemplazo caracteres sin sentido en espa�ol
		patron = Pattern.compile("[~]");
		comparador = patron.matcher(termino);
		termino = comparador.replaceAll("");
		return termino;
	}

	private static String correctorSintactico(String linea) {

		Pattern patron;
		Matcher comparador;

		/*
		 * Expresi�n regular para todos los caracteres que no sean una letra o
		 * un numero excluyendo a la �, las vocales acentuadas, la "u" con
		 * di�resis y, por supuesto el espacio mismo
		 */
		String regEx = "[\\W&&[^�������\\s]]";

		patron = Pattern.compile(regEx);
		comparador = patron.matcher(linea);

		StringBuffer sb = new StringBuffer();
		String caracterHallado;

		while (comparador.find()) {
			caracterHallado = comparador.group();
			comparador.appendReplacement(sb, " " + caracterHallado + " ");
		}
		comparador.appendTail(sb);
		return sb.toString();

	}

	private static String[] splitter(String linea) {

		return linea.split("\\s");
	}

	/*
	 * M�todo que permite detectar palabras Hom�nimas, es decir que suenan igual
	 * pero se escriben diferente.
	 */
	public static String posProcesadorFonetico(String linea) {

		Pattern patron;
		Matcher comparador;

		String regEx = "((l{2}))|"
				+ // encuentra la "ll" � la "y"
				"([b])|"
				+ // encuentra b � v
				"((?<!c)h)|"
				+ // encuentra "h" pero no antecedida por "c"
				"((je))|"
				+ // encuentra la combinacion ge � je
				"((ji))|" + "((mb))|" + "((ce))|" + "((ci))|" + "((za))|"
				+ "((zu))";

		patron = Pattern.compile(regEx);
		comparador = patron.matcher(linea);

		StringBuffer sb = new StringBuffer();
		String stringHallado;

		while (comparador.find()) {
			stringHallado = comparador.group();
			comparador.appendReplacement(sb, caracterEscape(stringHallado));
		}
		comparador.appendTail(sb);
		return sb.toString();

	}

	private static String caracterEscape(String hallado) {

		String caracterDeEscape = "x";

		switch (hallado.charAt(0)) {
		case 'l': {
			caracterDeEscape = "y";
			break;
		}
		case 'b': {
			caracterDeEscape = "v";
			break;
		}
		case 'h': {
			caracterDeEscape = "";
			break;
		}
		case 'm': {
			caracterDeEscape = "nv";
			break;
		}
		case 'j': {
			if (hallado.charAt(1) == 'e') {
				caracterDeEscape = "ge";
			} else {
				caracterDeEscape = "gi";
			}
			break;
		}
		case 'c': {
			if (hallado.charAt(1) == 'e') {
				caracterDeEscape = "se";
			} else {
				caracterDeEscape = "su";
			}
			break;
		}
		case 'z': {
			if (hallado.charAt(1) == 'a') {
				caracterDeEscape = "sa";
			} else {
				caracterDeEscape = "su";
			}
			break;
		}

		default:
			break;
		}
		return caracterDeEscape;
	}

	public static String[] procesarLinea(String lineaEntrada) {

		return splitter(correctorSintactico(analisisLexico(lineaEntrada)));

	}
}
