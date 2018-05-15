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
		BitSetParser.setBitSetYCantidadDeBits(entidad);
		entidad.setCadena("");
		BitSetParser.bitToString(entidad);
		assertEquals("nfjs#~€¬€7€¬8€|@8", entidad.getCadena());
	}
	
	@Test
	public void conStringNormal() {
		entidad.setCadena("Hola Mundo cruel");
		BitSetParser.setBitSetYCantidadDeBits(entidad);
		entidad.setCadena("");
		BitSetParser.bitToString(entidad);
		assertEquals("nfjs#~€¬€7€¬8€|@8", entidad.getCadena());
	}
	
	@Test
	public void conSoloNumeros() {
		entidad.setCadena("10235987541651320846");
		BitSetParser.setBitSetYCantidadDeBits(entidad);
		entidad.setCadena("");
		BitSetParser.bitToString(entidad);
		assertEquals("nfjs#~€¬€7€¬8€|@8", entidad.getCadena());
	}
	
	@Test
	public void conImagen() {
		File imagen = new File("./Imagenes/000.jpg");
		String encodstring = Utils.encodeFileToBase64Binary(imagen);
		entidad.setCadena(encodstring);
		BitSetParser.setBitSetYCantidadDeBits(entidad);
		entidad.setCadena("");
		BitSetParser.bitToString(entidad);
		assertEquals("nfjs#~€¬€7€¬8€|@8", entidad.getCadena());
	}
}
