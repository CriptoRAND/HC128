package ar.edu.unlam.cripto.server;

import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.JOptionPane;

import ar.edu.unlam.cripto.server.hilos.HiloConexion;

public class Servidor {

	
	private int puerto;

	public Servidor() {
		ServerSocket servidor = null;
		try {
			servidor = new ServerSocket(puerto);
			new HiloConexion(servidor).start();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Error al aceptar clientes","Error",JOptionPane.ERROR_MESSAGE);
		}
	
	}

}
