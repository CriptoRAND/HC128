package ar.edu.unlam.cripto.parser;

import java.math.BigInteger;
import java.util.BitSet;

/**
 * @author Pablo
 *
 */
public class BitSetStringParser {
	
	private static final String CERO = "0";
	private static final String UNO = "1";
	private static final String FORMATO_BYTE = "%08d";
	private static final int BYTE = 8;
	
	private String cadena;
	private BitSet cadenaDeBits;
	private int cantidadDeBits;
	
	public BitSetStringParser() {
		cadenaDeBits=new BitSet();
	}
	
	public BitSetStringParser(String cadena) {
		this.cadena = cadena;
		cadenaDeBits=new BitSet();
	}
	
	/**
	 * @return  la cadena parseada a bits
	 */
	public BitSet toBits(){
		if(cadena==null || cadena.equals("")) {
			return new BitSet();
		}
		char[] caracteres=cadena.toCharArray();
		setCantidadDeBits(caracteres.length*BYTE);
		for(int j=0;j<caracteres.length;j++) {
			char caracter = caracteres[j];
			String bitsDelCaracter =Integer.toBinaryString(caracter);
			String[] bits = String.format(FORMATO_BYTE, Integer.parseInt(bitsDelCaracter)).split("");
			for(int i =0;i<bits.length;i++){
				cadenaDeBits.set(j*BYTE+i,Integer.parseInt(bits[i])==1);
			}			
		}
		return cadenaDeBits;
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
	
	/**
	 * 
	 * @return la cadena representada por el bitset
	 */
	public String bitToString() {
		String respuesta="";
		for(int i = 0; i<cantidadDeBits;i++) {
			respuesta+=cadenaDeBits.get(i)?UNO:CERO;
		}
		return new String(new BigInteger(respuesta, 2).toByteArray());
	}

	public int getCantidadDeBits() {
		return cantidadDeBits;
	}

	public void setCantidadDeBits(int cantidadDeBits) {
		this.cantidadDeBits = cantidadDeBits;
	}
	
	/**
	 * @return un string representando a la cadena de bits
	 */
	public String toBitString() {
		String respuesta ="";
		for(int i = 0; i<this.getCantidadDeBits();i++) {
			respuesta+=cadenaDeBits.get(i)?"1":"0";
		}	
		return respuesta;
	}
	

}
