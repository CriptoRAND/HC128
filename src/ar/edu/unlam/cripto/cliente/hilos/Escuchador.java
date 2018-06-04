package ar.edu.unlam.cripto.cliente.hilos;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import ar.edu.unlam.cripto.cliente.Cliente;

public class Escuchador extends Thread {
	
	private Cliente cliente;
	private Socket socketCliente;
	private DataInputStream entrada;
	
	public Escuchador(Cliente cliente) throws IOException {
		this.cliente=cliente;
		socketCliente=cliente.getServicio().getCliente();
		entrada = new DataInputStream(socketCliente.getInputStream());
	}

	public void run() {
		while(true) {
			try {
				String string = entrada.readUTF();
				System.out.println(string);
				 cliente.setLabelText(string);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	}
	
	

}
