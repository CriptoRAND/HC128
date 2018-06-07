package ar.edu.unlam.cripto.cliente;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

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
		socketCliente.setTcpNoDelay(true);
		salida = new DataOutputStream(socketCliente.getOutputStream());
		entrada = new DataInputStream(socketCliente.getInputStream());
	}

	public  void enviarArchivo(File file) throws FileNotFoundException, IOException, InterruptedException {
		
		byte[] baits = Utils.fileToByte(file);
		enviarBytes(baits);
	}

	private void enviarBytes(byte[] baits) throws IOException, InterruptedException {
		baits = cipher.encriptar(baits);
		salida.writeInt(baits.length);
		int bloquesAEnviar = baits.length / Utils.TAMAÑO_BLOQUE_A_ENVIAR + 1;
		for (int i = 0; i < bloquesAEnviar; i++) {
			if(i != bloquesAEnviar-1) {
				salida.write(baits, i*Utils.TAMAÑO_BLOQUE_A_ENVIAR, Utils.TAMAÑO_BLOQUE_A_ENVIAR);
			} else {
				salida.write(baits, i*Utils.TAMAÑO_BLOQUE_A_ENVIAR, baits.length - (i*Utils.TAMAÑO_BLOQUE_A_ENVIAR));
			}
			Thread.sleep(50);
			salida.flush();
		}
	}
	
	public  void recibirArchivo() throws IOException, InterruptedException,SocketException {
	
		int cantidad = entrada.readInt();
		if(cantidad<=0) {
			return;
		}
		System.out.println("Leyendo archivo");
		System.out.println("CANTIDAD EN RECIBIRARCHIVO: "+cantidad);
		byte[] baits = new byte[cantidad];
		int bloquesAEnviar = cantidad / Utils.TAMAÑO_BLOQUE_A_ENVIAR + 1;
		for (int i = 0; i < bloquesAEnviar; i++) {
//			Thread.sleep(10);
			if(i != bloquesAEnviar-1) {
				entrada.read(baits, i*Utils.TAMAÑO_BLOQUE_A_ENVIAR, Utils.TAMAÑO_BLOQUE_A_ENVIAR);
			} else {
				entrada.read(baits, i*Utils.TAMAÑO_BLOQUE_A_ENVIAR, cantidad - (i*Utils.TAMAÑO_BLOQUE_A_ENVIAR));
			}
		}
		System.out.println("termino de recibir");
		
		baits=cipher.encriptar(baits);
//		File file = new File("./Imagenes/temp.jpg");
//		Utils.byteToFile(baits, file);
		InputStream in = new ByteArrayInputStream(baits);
		BufferedImage bImageFromConvert = ImageIO.read(in);
		cliente.setLabelText(bImageFromConvert);
	}

	public Socket getSocketCliente() {
		return socketCliente;
	}

	public void setSocketCliente(Socket socketCliente) {
		this.socketCliente = socketCliente;
	}

	public void cerrarCliente() throws IOException {
		salida.writeInt(Integer.MIN_VALUE);
		salida.flush();
		if(!socketCliente.isClosed()) {
			this.socketCliente.close();					
		}
	}

	public void stremear() throws InterruptedException, IOException {

		Dimension size = WebcamResolution.QVGA.getSize();

		Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(size);
		webcam.open(true);



		for (int i = 0; i < 50; i++) {
			BufferedImage image=webcam.getImage();
			File fileCam = new File("./Imagenes/camara.jpg");
			ImageIO.write(image,"jpg",fileCam);
			enviarArchivo(fileCam);
			//Este sleep estaba en el ejemplo para limitar a 10FPS, se lo saque y en la cristi funcionó
//			Thread.sleep(100);
			
		}
		
		webcam.close();

	}
	
	

}
