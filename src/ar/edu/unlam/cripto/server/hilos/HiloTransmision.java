package ar.edu.unlam.cripto.server.hilos;

import java.net.Socket;

public class HiloTransmision extends Thread {

	
	private Socket socketCliente;

	public HiloTransmision(Socket socketCliente) {
		this.socketCliente = socketCliente;
	}
	
	public void run() {
		
	}

}
