package ar.edu.unlam.cripto.parser;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.stream.IntStream;

/**
 * Clase dedicada a parsear un objeto entidad y setear sus diferentes atributos de interes como bitSet y cantidadDeBits.
 * @author Pablo, Martin
 */
public class BitSetParser {

	private static final String CERO = "0";
	private static final String UNO = "1";
	private static final String FORMATO_BYTE = "%08d";
	private static final int BYTE = 8;

	/**
	 * Recibe una entidad para parsear y le setea la cantidad de bits que contiene y le setea un bitSet acorde.
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
	 * @param la entidad
	 */
	public static void parsearBitAString(EntidadAParsear entidad) {
		System.out.println("Cantidad de bits de entidad en parsearBitAString: "+entidad.getCantidadDeBits());
		BigInteger b = entidad.getCadenaDeBits();
		entidad.setCadena(new String(b.toByteArray()));
		System.out.println("getCadena luego de parsearBitAString: "+entidad.getCadena());
	}
}
