package ar.edu.unlam.cripto.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ar.edu.unlam.cripto.server.hilos.HiloConexion;

public class Servidor {

	private int puerto;
	private List<Socket> clientes = new ArrayList<>();

	public Servidor() {
		ServerSocket servidor = null;
		puerto=8000;
		try {
			servidor = new ServerSocket(puerto);
			new HiloConexion(servidor,clientes).start();
			System.out.println("Servidor iniciado");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al aceptar clientes", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Servidor servidor = new Servidor();
	}

	public List<Socket> getClientes() {
		return clientes;
	}

	public void setClientes(List<Socket> clientes) {
		this.clientes = clientes;
	}
	
	

}
