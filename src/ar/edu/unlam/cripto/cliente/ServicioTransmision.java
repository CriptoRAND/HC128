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

	public void enviarArchivo(File file) throws FileNotFoundException, IOException {
		byte[] bytes = Utils.fileToByte(file);
		bytes = cipher.encriptar(bytes);
		salida.writeInt(bytes.length);
		/*
		for(byte bait : bytes) {
			salida.writeByte(bait);
		}*/
		salida.write(bytes);
//		salida.writeUTF(file.getName());
	}
	
	public void recibirArchivo() throws IOException {
		int cantidad = entrada.readInt();
		System.out.println("Leyendo archivo");
		byte[] baits = new byte[cantidad];
		/*
		for(int i=0;i<cantidad;i++) {
			baits[i]=entrada.readByte();		
		}
		*/
		entrada.read(baits);
		System.out.println("termino de recibir");
		
		baits=cipher.encriptar(baits);
		File file = new File("./Imagenes/temp.jpg");
		Utils.byteToFile(baits, file);
		cliente.setLabelText(file);

		//entrada.read();
	}

	public Socket getSocketCliente() {
		return socketCliente;
	}

	public void setSocketCliente(Socket socketCliente) {
		this.socketCliente = socketCliente;
	}
	
	

}
