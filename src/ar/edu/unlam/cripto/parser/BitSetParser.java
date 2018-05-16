package ar.edu.unlam.cripto.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

import ar.edu.unlam.cripto.parser.EntidadAParsear;

/**
 * Clase dedicada a parsear un objeto entidad y setear sus diferentes atributos
 * de interes como BigInteger y cantidadDeBits.
 * 
 * @author Pablo, Martin
 */
public class BitSetParser {

	private static final String FORMATO_BYTE = "%08d";
	private static final int BYTE = 8;

	/**
	 * Recibe una entidad para parsear y le setea la cantidad de bits que contiene y
	 * le setea un BigInteger acorde.
	 * 
	 * @param entidad
	 */
	public static void parsearStringABit(EntidadAParsear entidad) {
		if (entidad.getCadena() != null || !entidad.getCadena().equals("")) {
			char[] caracteres = entidad.getCadena().toCharArray();
			entidad.setCantidadDeBits(caracteres.length * BYTE);
			String todo = new String("");
			for (int j = 0; j < caracteres.length; j++) {
				char caracter = caracteres[j];
				String bitsDelCaracter = Integer.toBinaryString(caracter);
				String format = String.format(FORMATO_BYTE, Long.parseLong(bitsDelCaracter));
				todo = todo.concat(format);
			}
			entidad.setCadenaDeBits(new BigInteger(todo, 2));
		} else {
			System.out.println("Entidad vacía");
		}
	}

	/**
	 * Setea la cadena en la entidad.
	 * 
	 * @param la entidad
	 */
	public static void parsearBitAString(EntidadAParsear entidad) {
		System.out.println("Cantidad de bits de entidad en parsearBitAString: " + entidad.getCantidadDeBits());
		BigInteger b = entidad.getCadenaDeBits();
		entidad.setCadena(new String(b.toByteArray()));
		System.out.println("getCadena luego de parsearBitAString: " + entidad.getCadena());
	}

	/**
	 * Recibe una entidad para parsear y un file, y le setea la cantidad de bits que
	 * contiene.
	 * 
	 * @param entidad
	 * @param imagen
	 * @throws IOException
	 */
	public static void parsearFileABit(EntidadAParsear entidad, File imagen) throws IOException {
		FileInputStream fileInputStreamReader = new FileInputStream(imagen);
		byte[] bytes = new byte[(int) imagen.length()];
		fileInputStreamReader.read(bytes);
		fileInputStreamReader.close();
		entidad.setCadenaDeBits(new BigInteger(bytes));
	}

	/**
	 * Desencripta los bits de la entidad y escribe la imagen.
	 * 
	 * @param entidad
	 * @param imagen
	 * @throws IOException
	 */
	public static void parsearBitAFile(EntidadAParsear entidad, File imagen) throws IOException {
		byte[] bytes = entidad.getCadenaDeBits().toByteArray();
		FileOutputStream stream = new FileOutputStream(imagen);
		try {
			stream.write(-1);
			stream.write(bytes);
		} finally {
			stream.close();
		}
	}
}
