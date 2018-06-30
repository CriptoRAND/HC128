package ar.edu.unlam.cripto.server.hilos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import ar.edu.unlam.cripto.parser.Utils;

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
				if(cantidad==Integer.MIN_VALUE) {
					socketCliente.close();
					break;
				}
				if(cantidad<=0) {
					continue;
				}
				
				byte bytesHeader [] = new byte[Utils.HEADER_LENGTH];
				entrada.read(bytesHeader,0,Utils.HEADER_LENGTH);
				
				System.out.println("Leyendo archivo");
				System.out.println("CANTIDAD EN HILOTRANSMISION: "+cantidad);
				byte[] baits = new byte[cantidad];
				int bloquesAEnviar = cantidad / Utils.TAMAÑO_BLOQUE_A_ENVIAR + 1;
				for (int i = 0; i < bloquesAEnviar; i++) {
					if(i != bloquesAEnviar-1) {
						entrada.read(baits, i*Utils.TAMAÑO_BLOQUE_A_ENVIAR, Utils.TAMAÑO_BLOQUE_A_ENVIAR);
					} else {
						entrada.read(baits, i*Utils.TAMAÑO_BLOQUE_A_ENVIAR, cantidad - (i*Utils.TAMAÑO_BLOQUE_A_ENVIAR));
					}
				}
				for (Socket socket : clientes) {
					if (socket != socketCliente) {
						System.out.println("Servidor: Enviando eimagennviada");
						DataOutputStream salidaCliente = new DataOutputStream(socket.getOutputStream());
						salidaCliente.writeInt(cantidad);
						
						salidaCliente.write(bytesHeader,0,Utils.HEADER_LENGTH);
						for (int i = 0; i < bloquesAEnviar; i++) {
							if(i != bloquesAEnviar-1) {
								salidaCliente.write(baits, i*Utils.TAMAÑO_BLOQUE_A_ENVIAR, Utils.TAMAÑO_BLOQUE_A_ENVIAR);
							} else {
								salidaCliente.write(baits, i*Utils.TAMAÑO_BLOQUE_A_ENVIAR, cantidad - (i*Utils.TAMAÑO_BLOQUE_A_ENVIAR));
							}
							salidaCliente.flush();
//							Thread.sleep(10);
						}
						System.out.println("Servidor: Imagen enviada");
					}
				}
				System.out.println("Servidor: imagenes totalmente enviadas");
			} catch (Exception  e) {
				System.out.println("Error transmisión: " + e.getMessage());
			}

		}
	}

}
