package ar.edu.unlam.cripto.test;

import static org.junit.Assert.assertEquals;

import java.io.File;

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
		entidad.setCadena("nfjs#~€¬€7€¬8€|@8");
		BitSetParser.parsearStringABit(entidad);
		entidad.setCadena("");
		BitSetParser.parsearBitAString(entidad);
		assertEquals("nfjs#~€¬€7€¬8€|@8", entidad.getCadena());
	}
	
	@Test
	public void conStringNormal() {
		entidad.setCadena("Hola Mundo cruel");
		BitSetParser.parsearStringABit(entidad);
		entidad.setCadena("");
		BitSetParser.parsearBitAString(entidad);
		assertEquals("Hola Mundo cruel", entidad.getCadena());
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
