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
	private Socket cliente;
	private DataOutputStream salida;
	private DataInputStream entrada;
	private String ip;
	private int puerto;
	

	public ServicioTransmision() throws IOException {
		ip="localhost";
		puerto=8000;			
		String iv_srt = "@#$$54214AEFDCAE";
		String key_srt = "AAAAAAAAqweAAAAT";
		
		cipher = new HC128(iv_srt.getBytes(), key_srt.getBytes());
		cliente = new Socket(ip, puerto);
		salida = new DataOutputStream(cliente.getOutputStream());
		entrada = new DataInputStream(cliente.getInputStream());
	}

	public void enviarArchivo(File file) throws FileNotFoundException, IOException {
		byte[] bytes = Utils.fileToByte(file);
		bytes = cipher.encriptar(bytes);
//		salida.write(bytes);
		salida.writeUTF(file.getName());
	}
	
	public void recibirArchivo() throws IOException {
		entrada.read();
	}

	public Socket getCliente() {
		return cliente;
	}

	public void setCliente(Socket cliente) {
		this.cliente = cliente;
	}
	
	

}
