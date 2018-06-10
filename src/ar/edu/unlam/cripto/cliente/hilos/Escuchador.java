package ar.edu.unlam.cripto.cliente.hilos;

import java.io.IOException;

import ar.edu.unlam.cripto.cliente.Cliente;

public class Escuchador extends Thread {
	
	private Cliente cliente;
	
	public Escuchador(Cliente cliente) throws IOException {
		this.cliente=cliente;
	}

	public void run() {
			while(true) {
				try {
					cliente.getServicio().recibirArchivo();				
				} catch (Exception e) {
					System.out.println("Escuchador: " + e.getMessage());
				}
			}
		
		
	}
	
	

}
