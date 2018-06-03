package ar.edu.unlam.cripto.server.hilos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class HiloConexion extends Thread {

	private ServerSocket servidor;

	public HiloConexion(ServerSocket servidor) {
		this.servidor = servidor;
	}

	public void run() {
		try {
			Socket cliente = servidor.accept();
			new HiloTransmision(cliente).start();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al aceptar clientes", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
