package ar.edu.unlam.cripto.parser;

import java.math.BigInteger;

/**
 * Clase que contiene el algoritmo criptografico que realiza la encriptacion
 * propiamente dicha.
 * 
 * @author Martin
 *
 */
public class HC128 {

	// Cada tabla de 512 filas de 32 bits - [512] con cada BigInteger de 32 bits.
	public BigInteger[] tablaP;
	public BigInteger[] tablaQ;
	public BigInteger key;
	public BigInteger vectorDeInicializacion;

	public HC128() {
		tablaP = new BigInteger[512];
		tablaQ = new BigInteger[512];
		// 16 bytes, Secuencia de Fibonacci
		byte[] bytes = { (byte) 1, (byte) 1, (byte) 2, (byte) 3, (byte) 5, (byte) 8, (byte) 13, (byte) 21, (byte) 34,
				(byte) 55, (byte) 89, (byte) 144, (byte) 233, (byte) 377, (byte) 610, (byte) 987, (byte) 1597 };
		key = new BigInteger(bytes);
		System.out.println("KEY :" + key.toString(2));
		// 16 bytes, Secuencia de Números Mágicos
		byte[] bytes2 = { (byte) 1, (byte) 5, (byte) 15, (byte) 34, (byte) 65, (byte) 111, (byte) 175, (byte) 260,
				(byte) 369, (byte) 505, (byte) 671, (byte) 870, (byte) 1105, (byte) 1379, (byte) 1695, (byte) 2056,
				(byte) 2465 };
		vectorDeInicializacion = new BigInteger(bytes2);
		System.out.println("IV :" + vectorDeInicializacion.toString(2));
	}

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

		/*
		 * for i = 0 → N do j = i mod 512 if (i mod 1024) < 512 then P[j] ← (P[j] +
		 * g1(P[j ⊟ 3], P[j ⊟ 10], P[j ⊟ 511])) si = h1(P[j ⊟ 12] ⊕ P[j]) else Q[j] ←
		 * (Q[j] + g1(Q[j ⊟ 3], Q[j ⊟ 10], Q[j ⊟ 511])) si = h2(Q[j ⊟ 12] ⊕ Q[j]) end if
		 * i ← i + 1 end for
		 */
	}

	/*
	 * Funciones varias de apoyo al algoritmo principal
	 */
	public void f1(EntidadAParsear entidad) {
		// String s = null;
		// BitSet bits = entidad.getCadenaDeBits();
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
