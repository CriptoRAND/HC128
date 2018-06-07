package ar.edu.unlam.cripto.cliente.hilos;

import java.io.IOException;
import java.net.SocketException;

import ar.edu.unlam.cripto.cliente.Cliente;

public class Escuchador extends Thread {
	
	private Cliente cliente;
	
	public Escuchador(Cliente cliente) throws IOException {
		this.cliente=cliente;
	}

	public void run() {
		try {
			while(true) {
				try {
					cliente.getServicio().recibirArchivo();				
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}catch(Exception e) {
			//Se cerró el socket.
			if(!e.getClass().equals(SocketException.class)) {
				e.printStackTrace();				
			}
		}
		
	}
	
	

}
