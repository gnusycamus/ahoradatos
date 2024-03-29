package ar.com.datos.tests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.BitSet;

import org.apache.log4j.Logger;

import ar.com.datos.grupo5.compresion.conversionBitToByte;
import ar.com.datos.grupo5.excepciones.CodePointException;
import ar.com.datos.grupo5.utils.CodePoint;
import ar.com.datos.grupo5.utils.Conversiones;

public class TestBinario {

	private static Logger LOG = Logger.getLogger(TestBinario.class);
	/*
    // Returns a bitset containing the values in bytes.
    // The byte-ordering of bytes must be big-endian which means the most significant bit is in element 0.
    public static BitSet fromByteArray(byte[] bytes) {
        BitSet bits = new BitSet();
        for (int i=0; i<bytes.length*8; i++) {
            if ((bytes[bytes.length-i/8-1]&(1<<(i%8))) > 0) {
                bits.set(i);
            }
        }
        return bits;
    }
    
    // Returns a byte array of at least length 1.
    // The most significant bit in the result is guaranteed not to be a 1
    // (since BitSet does not support sign extension).
    // The byte-ordering of the result is big-endian which means the most significant bit is in element 0.
    // The bit at index 0 of the bit set is assumed to be the least significant bit.
    public static byte[] toByteArray(BitSet bits) {
        byte[] bytes = new byte[bits.length()/8+1];
        for (int i=0; i<bits.length(); i++) {
            if (bits.get(i)) {
                bytes[bytes.length-i/8-1] |= 1<<(i%8);
            }
        }
        return bytes;
    }
*/
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

//		BitSet bits = new BitSet();
//		boolean[] bit = {false, true,false, true, true, false, false, true};
//		//Escribir al reves porque la funcion lo da vuelta.
//		int b = 0;
//		for (int i = bit.length; i > 0; i--) {
//			bits.set(b, b+1, bit[i-1]);
//			b++;
//		}
//
//		System.out.print("Binario: ");
//		for (int i = bit.length; i > 0; i--) {
//			System.out.print(bits.get(i-1)?"1":"0");
//		}
//		System.out.println();
//		byte[] bytes = toByteArray(bits);
//		LOG.debug("Array de bytes: " + bytes);
//		LOG.debug(Integer.toBinaryString(bytes.length==1?bytes[0]:bytes[1]));
//		
//		
//		BitSet rec = fromByteArray(bytes);
//		System.out.print("Binario rec: ");
//		for (int i = 8; i > 0; i--) {
//			System.out.print(rec.get(i-1)?"1":"0");
//		}
		
		//Ejemplo de como guardar 32 bits en disco.
	/*	
		//tenemos que guardar 32 bits.
		String integer = "11110000111100001111000011110000";
		LOG.debug("En binario: " + integer);
		//Lo guardo en un long.
		Long numero = Long.parseLong(integer, 2);
		LOG.debug("En decimal: " + numero);
		//Convierto a bytes
		byte[] bytess = Conversiones.longToArrayByte(numero);
		File file = new File("./testBits");
		FileOutputStream output = new FileOutputStream(file);
		//Escribo en disco los 4 bytes menos significativos.
		output.write(bytess, 4, 4);
		output.flush();
		output.close();

		FileInputStream input = new FileInputStream(file);
		//Creo un buffer para long, inicializando en cero
		byte[] bytesLec = {0, 0, 0, 0, 0, 0, 0, 0};
		//Cargo los 4 bytes inferires del long, que es el numero que guarde.
		input.read(bytesLec, 4, 4);
		input.close();
		Long longRec = Conversiones.arrayByteToLong(bytesLec);
		LOG.debug("Numero recuperado: " + longRec);
		//Si quiero puedo convertirlo a integer y obtener el binario original.
		Integer integerRec = longRec.intValue();
		LOG.debug("Binario recuperado: " + Integer.toBinaryString(integerRec));
		*/
		/*
		LOG.debug(Conversiones.charToBinaryString('Z'));
		conversionBitToByte f = new conversionBitToByte();
		f.setBits(Conversiones.charToBinaryString('H'));
		f.setBits(Conversiones.charToBinaryString('O'));
		f.setBits(Conversiones.charToBinaryString('L'));
		f.setBits(Conversiones.charToBinaryString('A'));
		byte[] algo = f.getBytes();
		if (f.hasMoreBytes()){
			algo = f.finalizarConversion();
		}
		LOG.debug(algo.toString());
		f.setBytes(algo);
		f.setCompletarA16Bits(true);
		LOG.debug(f.getBits());
		byte[] algoFinal = Conversiones.BinaryStringToBytes(f.getBits());
		
		ByteArrayInputStream bis = new ByteArrayInputStream(algoFinal);  
		DataInputStream dis = new DataInputStream(bis);
		algo = new byte[2];
		LOG.debug(Short.toString(dis.readShort()));
		LOG.debug(Character.forDigit(dis.readByte(), 10));
		LOG.debug(Character.forDigit(dis.readByte(), 10));
		//LOG.debug(h);
		//Character h = new Character(Character.toChars(Conversiones.arrayByteToShort(algo))[0]);
		//LOG.debug(h);
		
		LOG.debug(Conversiones.shortToBinaryString(2));
		//Cat257tInTheH258And264eR258
		 *
		 */
		/*
		Character c = new Character('\n');
		String hola = c.toString();
		System.out.println("New character.tostring: " + hola);
		 c = new Character('\r');
		hola = c.toString();
		System.out.println("New character.tostring: " + hola);
		*/
		
		/*
		CodePoint cp = new CodePoint();
		Character letra1 = null;
		try {
			letra1 = cp.getChar(3679700);
			System.out.println(letra1);
			int codepoint = cp.getCodePoint(letra1);
			System.out.println(codepoint);

		} catch (CodePointException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Fuera del Intervalo.");
		}
		*/
		
		for (int i = 0; i < 300; i++) {
			if (Character.UnicodeBlock.forName("BASIC_LATIN") == Character.UnicodeBlock.of(new Character(Character.toChars(i)[0]))) {
				System.out.println( new Character(Character.toChars(i)[0]) +" "+i);
			} else {
				if (Character.UnicodeBlock.forName("LATIN_1_SUPPLEMENT") == Character.UnicodeBlock.of(new Character(Character.toChars(i)[0]))) {
					System.out.println( new Character(Character.toChars(i)[0]) +" "+i);
				}
			}
		}

	}

}
