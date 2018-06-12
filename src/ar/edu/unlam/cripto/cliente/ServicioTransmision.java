package ar.edu.unlam.cripto.cliente;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

import ar.edu.unlam.cripto.parser.HC128;
import ar.edu.unlam.cripto.parser.Utils;

public class ServicioTransmision {

	private HC128 cipher;
	private Cliente cliente;

	public ServicioTransmision(Cliente cliente) throws IOException {

		String iv_srt = "@#$$54214AEFDCAE";
		String key_srt = "AAAAAAAAqweAAAAT";

		this.cliente = cliente;
		cipher = new HC128(iv_srt.getBytes(), key_srt.getBytes());

	}

	public void stremear() throws InterruptedException, IOException {

		Dimension size = WebcamResolution.QVGA.getSize();

		Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(size);
		webcam.open(true);

		for (int i = 0; i < 500; i++) {
			BufferedImage image = webcam.getImage();
			File fileCam = new File("./Imagenes/camara.jpg");
			ImageIO.write(image, "jpg", fileCam);
			// byte[] imageBytes = ((DataBufferByte)
			// image.getData().getDataBuffer()).getData();
			// enviarBytes(imageBytes);
			// enviarArchivo(fileCam);
			encriptarArchivo(fileCam);
			// Este sleep estaba en el ejemplo para limitar a 10FPS, se lo saque y en la
			// cristi funcionó
			// Thread.sleep(100);

		}

		webcam.close();

	}

	public void encriptarArchivo(File file) throws FileNotFoundException, IOException {
		byte[] baits = Utils.fileToByte(file);
		cliente.setLabelText(file);
		encriptarBytes(baits);

	}

	public void encriptarBytes(byte[] baits) throws IOException {
		baits = cipher.encriptar(baits);
		InputStream in = new ByteArrayInputStream(baits);
		BufferedImage imagenEncriptada = ImageIO.read(in);
		cliente.setLabelEncriptadoText(imagenEncriptada);

		baits = cipher.encriptar(baits);
		in = new ByteArrayInputStream(baits);
		BufferedImage imagenDesencriptada = ImageIO.read(in);
		cliente.setLabelDesencriptadoText(imagenDesencriptada);

	}

}
