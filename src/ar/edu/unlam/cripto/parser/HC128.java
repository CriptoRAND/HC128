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

	public HC128(){
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
            throw new java.lang.IllegalArgumentException(
                    "La key debe tener 128 bits");
        }
        count = 0;
        int[] w = new int[1280];
        
        for(int i=0; i<=7; i++) {
        	//w[i] = k[i];
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
