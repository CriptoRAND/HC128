package ar.edu.unlam.cripto.test;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;

import ar.edu.unlam.cripto.parser.HC128;
import ar.edu.unlam.cripto.parser.Utils;

/**
 * Clase para las pruebas del parser de bits.
 * 
 * @author Martin
 *
 */
public class HC128Test {
	@Test
	public void testHC128() {
		String iv_srt = "@#$$54214AEFDCAE";
		String key_srt = "AAAAAAAAqweAAAAT";
		HC128 hc = new HC128(iv_srt.getBytes(), key_srt.getBytes());
		byte[] cadenaEncriptada = hc.encriptar("Funciona".getBytes());
		assertEquals("Funciona", new String(hc.encriptar(cadenaEncriptada)));

	}

	@Test
	public void testConBigInt() {
		Utils.rotateLeft(new BigInteger("100010"), 6, 3);
	}

	@Test
	public void restaModular() {
		int x = 1024 - 510;
		assertEquals(x & 0x1FF, 2);
	}

	// XOR rellena con 0s al principio
	@Test
	public void testConBigIntXor() {
		BigInteger b1 = new BigInteger("01", 2);
		BigInteger b2 = new BigInteger("10000001", 2);
		System.out.println(b1.xor(b2).toString(2));
	}

	@Test
	public void testConBigIntMod() {
		BigInteger b1 = new BigInteger("1000", 2);
		System.out.println(b1.mod(new BigInteger("3")).toString(2));
	}

}
