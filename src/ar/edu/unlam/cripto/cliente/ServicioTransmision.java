package ar.edu.unlam.cripto.cliente;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import ar.edu.unlam.cripto.parser.HC128;
import ar.edu.unlam.cripto.parser.Utils;

public class ServicioTransmision {

	HC128 cipher;

	public ServicioTransmision() {
		String iv_srt = "@#$$54214AEFDCAE";
		String key_srt = "AAAAAAAAqweAAAAT";
		cipher = new HC128(iv_srt.getBytes(), key_srt.getBytes());
	}

	public void enviarArchivo(File file) throws FileNotFoundException, IOException {
		byte[] bytes = Utils.fileToByte(file);
		bytes = cipher.encriptar(bytes);
	}

}
