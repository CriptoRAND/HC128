package ar.edu.unlam.cripto.parser;

import java.util.BitSet;

/**
 * Clase generica que contiene el objeto con el string a parsear, el bitset y la cantidad de bits.
 * @author Martin
 *
 */
public class EntidadAParsear {

	private String cadena;
	private BitSet cadenaDeBits;
	private int cantidadDeBits;
	
	public EntidadAParsear() {
		cadenaDeBits = new BitSet();
	}
	
	public EntidadAParsear(String cadena) {
		this.cadena = cadena;
		cadenaDeBits = new BitSet();
	}

	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	public BitSet getCadenaDeBits() {
		return cadenaDeBits;
	}

	public void setCadenaDeBits(BitSet cadenaDeBits) {
		this.cadenaDeBits = cadenaDeBits;
	}

	public int getCantidadDeBits() {
		return cantidadDeBits;
	}

	public void setCantidadDeBits(int cantidadDeBits) {
		this.cantidadDeBits = cantidadDeBits;
	}
}
