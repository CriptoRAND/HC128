package ar.edu.unlam.cripto.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

import org.junit.Test;

import ar.edu.unlam.cripto.parser.BitSetParser;
import ar.edu.unlam.cripto.parser.EntidadAParsear;
import ar.edu.unlam.cripto.parser.HC128;
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
	public void testHC128() {
		String iv_srt = "@#$$54214AEFDCAE";
        String key_srt = "AAAAAAAAqweAAAAT";
		HC128 hc = new HC128(iv_srt.getBytes(),key_srt.getBytes());
		byte[] cadenaEncriptada = hc.encriptar("Funciona".getBytes());
		assertEquals("Funciona", new String(hc.encriptar(cadenaEncriptada)));
		
	}

	@Test
	public void testConStringDeGarabatos() {
		entidad.setCadena("nfjs#~78|@8");
		BitSetParser.parsearStringABit(entidad);
		entidad.setCadena("");
		BitSetParser.parsearBitAString(entidad);
		assertEquals("nfjs#~78|@8", entidad.getCadena());
	}
	
	@Test
	public void testConStringNormal() {
		entidad.setCadena("Hola Mundo cruel");
		BitSetParser.parsearStringABit(entidad);
		entidad.setCadena("");
		BitSetParser.parsearBitAString(entidad);
		//assertEquals("Hola Mundo cruel", entidad.getCadena());
		assertEquals("Hola Mundo cruel", entidad.getCadena());
	}
	
	@Test
	public void testConBigInt() {
		Utils.rotateLeft(new BigInteger("100010"), 6, 3);
	}

	
	@Test
	public void restaModular() {
		int x = 1024-510;  
		assertEquals(x & 0x1FF,2);
	}
	

	
	
	//XOR rellena con 0s al principio
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
	
	@Test
	public void testConSoloNumeros() {
		entidad.setCadena("10235987541651320846");
		BitSetParser.parsearStringABit(entidad);
		entidad.setCadena("");
		BitSetParser.parsearBitAString(entidad);
		assertEquals("10235987541651320846", entidad.getCadena());
	}
	
	@Test
	public void testConImagenChica() throws IOException {
		File imagen = new File("./Imagenes/000.jpg");
		BitSetParser.parsearFileABit(entidad, imagen);
		imagen = new File("./Imagenes/Desencriptada chica.jpg");
		BitSetParser.parsearBitAFile(entidad, imagen);
	}
	
	@Test
	public void testConImagenGrande() throws IOException {
		File imagen = new File("./Imagenes/001.jpg");
		BitSetParser.parsearFileABit(entidad, imagen);
		imagen = new File("./Imagenes/Desencriptada.jpg");
		BitSetParser.parsearBitAFile(entidad, imagen);
	}
	
	@Test
	public void testConImagenMasGrande() throws IOException {
		File imagen = new File("./Imagenes/muy grande.jpg");
		BitSetParser.parsearFileABit(entidad, imagen);
		imagen = new File("./Imagenes/Desencriptada muy grande.jpg");
		BitSetParser.parsearBitAFile(entidad, imagen);
	}
	
	@Test
	public void testUsandoClassInteger(){
		Integer integer = new Integer(1);
		System.out.println(Integer.rotateLeft(1, 2));
		
	}
	
}
