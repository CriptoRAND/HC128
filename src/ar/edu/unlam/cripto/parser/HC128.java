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
	private byte[] key, iv;
	private int[] p = new int[512];
	private int[] q = new int[512];
	private int count = 0;
	private byte[] buffer = new byte[4];
	private int index = 0;

	public HC128(byte[] iv, byte[] key) {
		this.iv = iv;
		this.key = key;
		inicializar();
	}

	/*
	 * Funcion principal de encriptacion
	 */
	public void encriptar() {

	}

	/*
	 * Funciones varias principales del algoritmo
	 */
	public void inicializar() {
		if (key.length != 16) {
			throw new java.lang.IllegalArgumentException("La key debe tener 128 bits");
		}
		count = 0;
		int[] w = new int[1280];

		byte primerByteKey = key[0];

		String[] s1 = String.format("%8s", Integer.toBinaryString(primerByteKey & 0xFF)).replace(' ', '0').split("");

		for (int i = 0; i <= 7; i++) {
			w[i] = Integer.parseInt(s1[i]);
		}

		byte primerByteIV = iv[0];
		String[] s2 = String.format("%8s", Integer.toBinaryString(primerByteIV & 0xFF)).replace(' ', '0').split("");

		for (int i = 8; i <= 15; i++) {
			w[i] = Integer.parseInt(s2[i]);
		}

		for (int i = 16; i <= 1279; i++) {
			w[i] = f2(w[i - 2]) + w[i - 7] + f1(w[i - 15]) + w[i - 16] + i;
		}

		for (int i = 0; i <= 511; i++) {
			p[i] = w[i + 256];
			q[i] = w[i + 768];
		}
		// TODO: Cambiar según pseudocodigo
		for (int i = 0; i <= 511; i++) {

			// p[i] = w[i+256];
			// q[i] = w[i+768];
		}

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
	public int f1(int x) {
		return Integer.rotateRight(x, 7) ^ Integer.rotateRight(x, 18) ^ (x >>> 3);
	}

	public int f2(int x) {
		return Integer.rotateRight(x, 17) ^ Integer.rotateRight(x, 19) ^ (x >>> 10);
	}

	public int g1(int x, int y, int z) {
		return (Integer.rotateRight(x, 10) ^ Integer.rotateRight(z, 23)) + Integer.rotateRight(y, 8);
	}

	public int g2(int x, int y, int z) {
		return (Integer.rotateLeft(x, 10) ^ Integer.rotateLeft(z, 23)) + Integer.rotateLeft(y, 8);
	}

	
	
//	public int h1(int x) {
//		return q[x & 0xFF] + q[((x >> 16) & 0xFF) + 256];
//	}

//	public int h2(int x) {
//		return p[x & 0xFF] + p[((x >> 16) & 0xFF) + 256];
//	}

}
