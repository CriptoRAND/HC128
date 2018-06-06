package ar.edu.unlam.cripto.server.hilos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import javax.swing.JOptionPane;

public class HiloConexion extends Thread {

	private ServerSocket servidor;
	private List<Socket> clientes;

	public HiloConexion(ServerSocket servidor, List<Socket> clientes) {
		this.servidor = servidor;
		this.clientes=clientes;
	}

	public void run() {
		try {
			while(true) {
				Socket cliente = servidor.accept();
				cliente.setTcpNoDelay(true);
				clientes.add(cliente);
				System.out.println("Cliente aceptado y esperando transmisión");
				new HiloTransmision(cliente,clientes).start();				
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al aceptar clientes", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
