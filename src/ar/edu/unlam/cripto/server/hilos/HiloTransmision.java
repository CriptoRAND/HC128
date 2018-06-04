package ar.edu.unlam.cripto.server.hilos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class HiloTransmision extends Thread {

	private Socket socketCliente;
	private List<Socket> clientes;
	private DataInputStream entrada;

	public HiloTransmision(Socket socketCliente, List<Socket> clientes) throws IOException {
		this.socketCliente = socketCliente;
		this.clientes=clientes;
		entrada = new DataInputStream(socketCliente.getInputStream());
	}

	public void run() {
		while(true) {
			try {
				String string = entrada.readUTF();
				System.out.println(string);
				for(Socket socket : clientes) {
					if(socket!=socketCliente) {
						DataOutputStream salidaCliente = new DataOutputStream(socket.getOutputStream());
						salidaCliente.writeUTF(string);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
