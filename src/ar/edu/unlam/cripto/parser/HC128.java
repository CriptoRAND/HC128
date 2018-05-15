package ar.edu.unlam.cripto.parser;

import java.util.BitSet;

import com.sun.xml.internal.bind.v2.TODO;

/**
 * Clase que contiene el algoritmo criptografico que realiza la encriptacion
 * propiamente dicha.
 * 
 * @author Martin
 *
 */
public class HC128 {

	// Cada tabla de 512 filas de 32 bits - [512] con cada BitSet de 32 bits.
	public BitSet[] tablaP;
	public BitSet[] tablaQ;
	public BitSet key;
	public BitSet vectorDeInicializacion;

	/*
	 * Funcion principal de encriptacion
	 */
	public void encriptar() {

	}

	/*
	 * Funciones varias principales del algoritmo
	 */
	public void inicializarKey() {

	}

	public void inicializarVectorDeInicializacion() {

	}
	
	public void generarEspacioDeLlaves() {
		
	}

	/*
	 * Funciones varias de apoyo al algoritmo principal
	 */
	public void f1(EntidadAParsear entidad) {
		String s = null;
		BitSet bits = entidad.getCadenaDeBits();
		// BitSet bitsCambiados = (bits >> 7) ^ (bits >> 18) ^ (bits >> 3);
	}

	public void f2() {

	}

	public void g1() {

	}

	public void g2() {

	}

	public void h1() {

	}

	public void h2() {

	}
}
