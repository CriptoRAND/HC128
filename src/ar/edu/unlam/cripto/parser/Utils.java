package ar.edu.unlam.cripto.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Clase Utils con metodos varios para la operativa de algoritmo.
 * 
 * @author Martin
 *
 */
public class Utils {
	
	//Constantes
	
	public static int TAMAÑO_BLOQUE_A_ENVIAR = 20000;
	public static Integer HEADER_LENGTH = 54;

	/*
	 * public static BigInteger rotateLeft(BigInteger bigInt, int bitSize, int
	 * shift) { BigInteger topBits = bigInt.shiftRight(bitSize - shift); BigInteger
	 * mask = BigInteger.ONE.shiftLeft(bitSize).subtract(BigInteger.ONE); return
	 * bigInt.shiftLeft(shift).or(topBits).and(mask); }
	 */

	public static BigInteger rotateLeft(BigInteger bigInt, int bitSize, int shift) {
		return new BigInteger(Integer.toString(Integer.rotateLeft(bigInt.intValue(), shift)));
	}

	public static byte[] fileToByte(File imagen) throws FileNotFoundException, IOException {
		FileInputStream fileInputStreamReader = new FileInputStream(imagen);
		byte[] bytes = new byte[(int) imagen.length()];
		fileInputStreamReader.read(bytes);
		fileInputStreamReader.close();
		return bytes;
	}

	public static void byteToFile(byte[] bytes, File rutaArchivo) throws IOException {

		FileOutputStream stream = new FileOutputStream(rutaArchivo);
		try {
//			stream.write(-1);
			stream.write(bytes);
		} finally {
			stream.close();
		}

	}

	public static void byteToFile(byte[] bytesHeader, byte[] bytes, File rutaArchivo) throws IOException {
		FileOutputStream stream = new FileOutputStream(rutaArchivo);
		try {
			stream.write(bytesHeader);
			stream.write(bytes);
		} finally {
			stream.close();
		}
		
	}
}
