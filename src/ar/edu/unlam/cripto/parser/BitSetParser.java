package ar.edu.unlam.cripto.parser;

import java.math.BigInteger;
import java.util.BitSet;

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
	public static void setBitSetYCantidadDeBits(EntidadAParsear entidad) {
		if (entidad.getCadena() != null || !entidad.getCadena().equals("")) {
			char[] caracteres = entidad.getCadena().toCharArray();
			entidad.setCantidadDeBits(caracteres.length * BYTE);
			for (int j = 0; j < caracteres.length; j++) {
				char caracter = caracteres[j];
				String bitsDelCaracter = Integer.toBinaryString(caracter);
				String[] bits = String.format(FORMATO_BYTE, Integer.parseInt(bitsDelCaracter)).split("");
				BitSet cadenaDeBits = new BitSet();
				for (int i = 0; i < bits.length; i++) {
					cadenaDeBits.set(j * BYTE + i, Integer.parseInt(bits[i]) == 1);
				}
				entidad.setCadenaDeBits(cadenaDeBits);
			}
		} else {
			System.out.println("Entidad vacía");
		}
	}
	
	/**
	 * Setea la cadena en la entidad.
	 * @param la entidad
	 */
	public static void bitToString(EntidadAParsear entidad) {
		String respuesta = "";
		for (int i = 0; i < entidad.getCantidadDeBits(); i++) {
			respuesta += entidad.getCadenaDeBits().get(i) ? UNO : CERO;
		}
		entidad.setCadena(new String(new BigInteger(respuesta, 2).toByteArray()));
	}

	/**
	 * Setea en la entidad una cadena con 1s y 0s acorde al bitSet que tiene.
	 */
	public void toBitString(EntidadAParsear entidad) {
		String respuesta = "";
		for (int i = 0; i < entidad.getCantidadDeBits(); i++) {
			respuesta += entidad.getCadenaDeBits().get(i) ? "1" : "0";
		}
		entidad.setCadena(respuesta);
	}

}
