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
		this.clientes = clientes;
		entrada = new DataInputStream(socketCliente.getInputStream());
	}

	public void run() {
		while (true) {
			try {
				int cantidad = entrada.readInt();
				System.out.println("Leyendo archivo");
				byte[] baits = new byte[cantidad];
				for (int i = 0; i < cantidad; i++) {
					baits[i] = entrada.readByte();
				}
				for (Socket socket : clientes) {
					if (socket != socketCliente) {
						System.out.println("Servidor: Enviando eimagennviada");
						DataOutputStream salidaCliente = new DataOutputStream(socket.getOutputStream());
						salidaCliente.writeInt(cantidad);
						for (int i = 0; i < cantidad; i++) {
							byte bait = baits[i];
							salidaCliente.writeByte(bait);
						}
						System.out.println("Servidor: Imagen enviada");
					}
				}
				System.out.println("Servidor: imagenes totalmente enviadas");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
