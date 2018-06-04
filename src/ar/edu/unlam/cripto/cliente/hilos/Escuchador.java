package ar.edu.unlam.cripto.cliente.hilos;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

import ar.edu.unlam.cripto.cliente.Cliente;
import ar.edu.unlam.cripto.parser.HC128;
import ar.edu.unlam.cripto.parser.Utils;

public class Escuchador extends Thread {
	
	private Cliente cliente;
	private Socket socketCliente;
	private DataInputStream entrada;
	private HC128 cipher;
	
	public Escuchador(Cliente cliente) throws IOException {
		this.cliente=cliente;
		socketCliente=cliente.getServicio().getCliente();
		entrada = new DataInputStream(socketCliente.getInputStream());
		String iv_srt = "@#$$54214AEFDCAE";
		String key_srt = "AAAAAAAAqweAAAAT";
		cipher = new HC128(iv_srt.getBytes(), key_srt.getBytes());
	}

	public void run() {
		while(true) {
			try {
				int cantidad = entrada.readInt();
				System.out.println("Leyendo archivo");
				byte[] baits = new byte[cantidad];
				for(int i=0;i<cantidad;i++) {
					baits[i]=entrada.readByte();		
				}
				System.out.println("termino de recibir");
				
				baits=cipher.encriptar(baits);
				File file = new File("./Imagenes/temp.jpg");
				Utils.byteToFile(baits, file);
				cliente.setLabelText(file);
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	

}
