package ar.edu.unlam.cripto.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.math.BigInteger;

import org.junit.Test;

import ar.edu.unlam.cripto.parser.BitSetParser;
import ar.edu.unlam.cripto.parser.EntidadAParsear;
import ar.edu.unlam.cripto.parser.Utils;

/**
 * Clase para las pruebas del parser de bits.
 * @author Martin
 *
 */
public class BitSetParserTest {
	EntidadAParsear entidad;
	
	public BitSetParserTest() {
		entidad = new EntidadAParsear();
	}

	@Test
	public void conStringDeGarabatos() {
		entidad.setCadena("nfjs#~78|@8");
		BitSetParser.parsearStringABit(entidad);
		entidad.setCadena("");
		BitSetParser.parsearBitAString(entidad);
		assertEquals("nfjs#~78|@8", entidad.getCadena());
	}
	
	@Test
	public void conStringNormal() {
		entidad.setCadena("Hola Mundo cruel");
		BitSetParser.parsearStringABit(entidad);
		entidad.setCadena("");
		BitSetParser.parsearBitAString(entidad);
		//assertEquals("Hola Mundo cruel", entidad.getCadena());
		assertEquals("Hola Mundo cruel", entidad.getCadena());
	}
	
	@Test
	public void conBigInt() {
		int bitSize = 6;
		int shift = 3;
		BigInteger b = new BigInteger("000101", 2);
		BigInteger topBits = b.shiftRight(bitSize - shift);
	    BigInteger mask = BigInteger.ONE.shiftLeft(bitSize).subtract(BigInteger.ONE);
		System.out.println(b.shiftLeft(shift).or(topBits).and(mask).toString(2));
	}
	
	//XOR rellena con 0s al principio
	@Test
	public void conBigIntXor() {
		BigInteger b1 = new BigInteger("01", 2);
		BigInteger b2 = new BigInteger("10000001", 2);
		System.out.println(b1.xor(b2).toString(2));
	}
	
	@Test
	public void conBigIntMod() {
		BigInteger b1 = new BigInteger("1000", 2);
		System.out.println(b1.mod(new BigInteger("3")).toString(2));
	}
	
	@Test
	public void conSoloNumeros() {
		entidad.setCadena("10235987541651320846");
		BitSetParser.parsearStringABit(entidad);
		entidad.setCadena("");
		BitSetParser.parsearBitAString(entidad);
		assertEquals("10235987541651320846", entidad.getCadena());
	}
	
	@Test
	public void conImagenChica() {
		File imagen = new File("./Imagenes/000.jpg");
		String encodstring = Utils.encodeFileToBase64Binary(imagen);
		entidad.setCadena(encodstring);
		BitSetParser.parsearStringABit(entidad);
		entidad.setCadena("");
		BitSetParser.parsearBitAString(entidad);
		assertEquals(encodstring, entidad.getCadena());
	}
	
	@Test
	public void conImagenGrande() {
		File imagen = new File("./Imagenes/001.jpg");
		String encodstring = Utils.encodeFileToBase64Binary(imagen);
		entidad.setCadena(encodstring);
		BitSetParser.parsearStringABit(entidad);
		entidad.setCadena("");
		BitSetParser.parsearBitAString(entidad);
		assertEquals(encodstring, entidad.getCadena());
	}
}
