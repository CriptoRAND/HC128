package ar.edu.unlam.cripto.parser;

import java.math.BigInteger;


/**
 * Clase Utils con metodos varios para la operativa de algoritmo.
 * @author Martin
 *
 */
public class Utils {
	
	/*public static BigInteger rotateLeft(BigInteger bigInt, int bitSize, int shift) {
		BigInteger topBits = bigInt.shiftRight(bitSize - shift);
	    BigInteger mask = BigInteger.ONE.shiftLeft(bitSize).subtract(BigInteger.ONE);
		return bigInt.shiftLeft(shift).or(topBits).and(mask);
	}*/
	
	public static BigInteger rotateLeft(BigInteger bigInt, int bitSize, int shift) {
		return new BigInteger (Integer.toString(Integer.rotateLeft(bigInt.intValue(), shift)));	
	}
}
