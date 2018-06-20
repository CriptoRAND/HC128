package ar.edu.unlam.cripto.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import ar.edu.unlam.cripto.parser.HC128;

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
	public void testConCadenaEncriptadaModificada() {
		String iv_srt = "@#$$54214AEFDCAE";
		String key_srt = "AAAAAAAAqweAAAAT";
		HC128 hc = new HC128(iv_srt.getBytes(), key_srt.getBytes());
		byte[] cadenaEncriptada = hc.encriptar("Funciona".getBytes());
		cadenaEncriptada[0] |= (byte) (1 << 6);
		String cadenaDesencriptada = new String(hc.encriptar(cadenaEncriptada));
		System.out.println(cadenaDesencriptada);
		assertNotEquals("Funciona", cadenaDesencriptada);
	}
}
