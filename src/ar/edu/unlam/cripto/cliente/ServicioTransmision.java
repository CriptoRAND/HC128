package ar.edu.unlam.cripto.cliente;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;

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
		ip = "localhost";
		puerto = 8000;
		String iv_srt = "@#$$54214AEFDCAE";
		String key_srt = "AAAAAAAAqweAAAAT";

		this.cliente = cliente;
		cipher = new HC128(iv_srt.getBytes(), key_srt.getBytes());
		socketCliente = new Socket(ip, puerto);
		socketCliente.setTcpNoDelay(true);
		salida = new DataOutputStream(socketCliente.getOutputStream());
		entrada = new DataInputStream(socketCliente.getInputStream());
	}

	public void enviarArchivo(File file) throws FileNotFoundException, IOException, InterruptedException {

		byte[] baits = Utils.fileToByte(file);
		enviarBytes(baits);
	}

	private void enviarBytes(byte[] baits) throws IOException, InterruptedException {
		
		byte bytesHeader [] = new byte[Utils.HEADER_LENGTH];

        for (int i = 0; i < Utils.HEADER_LENGTH; i ++){
            bytesHeader[i] = baits[i];
        }
        
        byte bytesBody [] = new byte[baits.length - Utils.HEADER_LENGTH];

        //for flashero 
        for (int i = 0, j = Utils.HEADER_LENGTH; i < baits.length - Utils.HEADER_LENGTH ; i ++, j++){
            bytesBody[i] = baits[j];
        }

		
        bytesBody = cipher.encriptar(bytesBody);
        
		salida.writeInt(bytesBody.length);
		int bloquesAEnviar = bytesBody.length / Utils.TAMAÑO_BLOQUE_A_ENVIAR + 1;
		
		salida.write(bytesHeader,0,Utils.HEADER_LENGTH);
		Thread.sleep(10);
		for (int i = 0; i < bloquesAEnviar; i++) {
			if (i != bloquesAEnviar - 1) {
				salida.write(bytesBody, i * Utils.TAMAÑO_BLOQUE_A_ENVIAR, Utils.TAMAÑO_BLOQUE_A_ENVIAR);
			} else {
				salida.write(bytesBody, i * Utils.TAMAÑO_BLOQUE_A_ENVIAR, bytesBody.length - (i * Utils.TAMAÑO_BLOQUE_A_ENVIAR));
			}
			Thread.sleep(10);
			salida.flush();
		}
	}

	public void recibirArchivo() {
		try {
			int cantidad = entrada.readInt();
			if (cantidad <= 0) {
				return;
			}
			System.out.println("Leyendo archivo");
			System.out.println("CANTIDAD EN RECIBIRARCHIVO: " + cantidad);
			
			
			byte bytesHeader [] = new byte[Utils.HEADER_LENGTH];
			entrada.read(bytesHeader,0,Utils.HEADER_LENGTH);
			
			byte[] baits = new byte[cantidad];
			int bloquesAEnviar = cantidad / Utils.TAMAÑO_BLOQUE_A_ENVIAR + 1;
			for (int i = 0; i < bloquesAEnviar; i++) {
				// Thread.sleep(10);
				if (i != bloquesAEnviar - 1) {
					entrada.read(baits, i * Utils.TAMAÑO_BLOQUE_A_ENVIAR, Utils.TAMAÑO_BLOQUE_A_ENVIAR);
				} else {
					entrada.read(baits, i * Utils.TAMAÑO_BLOQUE_A_ENVIAR, cantidad - (i * Utils.TAMAÑO_BLOQUE_A_ENVIAR));
				}
			}
			System.out.println("termino de recibir");

			File fileEncriptado = new File("./Imagenes/temp.bmp");
			Utils.byteToFile(bytesHeader,baits, fileEncriptado);
			cliente.setLabelEncriptadoText(fileEncriptado);
			
			
			baits = cipher.encriptar(baits);
			File file = new File("./Imagenes/temp.bmp");
			Utils.byteToFile(bytesHeader,baits, file);
			cliente.setLabelText(file);
		} catch (Exception e) {
			System.out.println("Error al recibir(ServicioTransmision): " + e.getMessage());
		}
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
		if (!socketCliente.isClosed()) {
			this.socketCliente.close();
		}
	}

	public void stremear() throws InterruptedException, IOException {
		Webcam webcam = null;
		try {
					
			Dimension size = WebcamResolution.QVGA.getSize();
			
			webcam = Webcam.getDefault();
			webcam.setViewSize(size);
			webcam.open(true);
	
			for (int i = 0; i < 300; i++) {
				BufferedImage image = webcam.getImage();
				File fileCam = new File("./Imagenes/camara.bmp");
				ImageIO.write(image, "bmp", fileCam);
		//			byte[] imageBytes = ((DataBufferByte) image.getData().getDataBuffer()).getData();
		//			enviarBytes(imageBytes);
				enviarArchivo(fileCam);
				// Este sleep estaba en el ejemplo para limitar a 10FPS, se lo saque y en la
				// cristi funcionó
				// Thread.sleep(100);
		
		}

			
		}catch(Exception e) {
			System.out.println("Error en Servicio Transmision: "+ e.getMessage());
		}finally {
			if(webcam!=null && webcam.isOpen()) {
				webcam.close();
			}
		}

	}

}
