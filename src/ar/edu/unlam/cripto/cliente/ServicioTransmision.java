package ar.edu.unlam.cripto.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;

import ar.edu.unlam.cripto.parser.HC128;
import ar.edu.unlam.cripto.parser.Utils;

public class ServicioTransmision {

	private HC128 cipher;
	private Socket socketCliente;
	private Cliente cliente;
	private DataOutputStream salida;
	private DataInputStream entrada;
	private String ip;
	private int puerto;
	

	public ServicioTransmision(Cliente cliente) throws IOException {
		ip="localhost";
		puerto=8000;			
		String iv_srt = "@#$$54214AEFDCAE";
		String key_srt = "AAAAAAAAqweAAAAT";
		
		this.cliente = cliente;
		cipher = new HC128(iv_srt.getBytes(), key_srt.getBytes());
		socketCliente = new Socket(ip, puerto);
		salida = new DataOutputStream(socketCliente.getOutputStream());
		entrada = new DataInputStream(socketCliente.getInputStream());
	}

	public void enviarArchivo(File file) throws FileNotFoundException, IOException, InterruptedException {
		byte[] baits = Utils.fileToByte(file);
		baits = cipher.encriptar(baits);
		salida.writeInt(baits.length);
		int bloquesAEnviar = baits.length / Utils.TAMAÑO_BLOQUE_A_ENVIAR + 1;
		for (int i = 0; i < bloquesAEnviar; i++) {
			if(i != bloquesAEnviar-1) {
				salida.write(baits, i*Utils.TAMAÑO_BLOQUE_A_ENVIAR, Utils.TAMAÑO_BLOQUE_A_ENVIAR);
			} else {
				salida.write(baits, i*Utils.TAMAÑO_BLOQUE_A_ENVIAR, baits.length - (i*Utils.TAMAÑO_BLOQUE_A_ENVIAR));
			}
			salida.flush();
		}
	}
	
	public void recibirArchivo() throws IOException, InterruptedException {
		Thread.sleep(100);
		int cantidad = entrada.readInt();
		if(cantidad<=0) {
			return;
		}
		System.out.println("Leyendo archivo");
		System.out.println("CANTIDAD EN RECIBIRARCHIVO: "+cantidad);
		byte[] baits = new byte[cantidad];
		int bloquesAEnviar = cantidad / Utils.TAMAÑO_BLOQUE_A_ENVIAR + 1;
		for (int i = 0; i < bloquesAEnviar; i++) {
			Thread.sleep(10);
			if(i != bloquesAEnviar-1) {
				entrada.read(baits, i*Utils.TAMAÑO_BLOQUE_A_ENVIAR, Utils.TAMAÑO_BLOQUE_A_ENVIAR);
			} else {
				entrada.read(baits, i*Utils.TAMAÑO_BLOQUE_A_ENVIAR, cantidad - (i*Utils.TAMAÑO_BLOQUE_A_ENVIAR));
			}
		}
		System.out.println("termino de recibir");
		
		baits=cipher.encriptar(baits);
		File file = new File("./Imagenes/temp.jpg");
		Utils.byteToFile(baits, file);
		cliente.setLabelText(file);
	}

	public Socket getSocketCliente() {
		return socketCliente;
	}

	public void setSocketCliente(Socket socketCliente) {
		this.socketCliente = socketCliente;
	}
	
	

}
