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
//				Thread.sleep(100);
				int cantidad = entrada.readInt();
				if(cantidad==Integer.MIN_VALUE) {
					socketCliente.close();
					break;
				}
				if(cantidad<=0) {
					continue;
				}
				System.out.println("Leyendo archivo");
				System.out.println("CANTIDAD EN HILOTRANSMISION: "+cantidad);
				byte[] baits = new byte[cantidad];
				int bloquesAEnviar = cantidad / Utils.TAMAÑO_BLOQUE_A_ENVIAR + 1;
				for (int i = 0; i < bloquesAEnviar; i++) {
//					Thread.sleep(50);
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
						for (int i = 0; i < bloquesAEnviar; i++) {
							if(i != bloquesAEnviar-1) {
								salidaCliente.write(baits, i*Utils.TAMAÑO_BLOQUE_A_ENVIAR, Utils.TAMAÑO_BLOQUE_A_ENVIAR);
							} else {
								salidaCliente.write(baits, i*Utils.TAMAÑO_BLOQUE_A_ENVIAR, cantidad - (i*Utils.TAMAÑO_BLOQUE_A_ENVIAR));
							}
							salidaCliente.flush();
							Thread.sleep(50);
						}
						System.out.println("Servidor: Imagen enviada");
					}
				}
				System.out.println("Servidor: imagenes totalmente enviadas");
			} catch (IOException | InterruptedException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
