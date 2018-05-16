package ar.edu.unlam.cripto.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

/**
 * Clase Utils con metodos varios para la operativa de algoritmo.
 * @author Martin
 *
 */
public class Utils {

	private static FileInputStream fileInputStreamReader;

	public static String encodeFileToBase64Binary(File file){
        String encodedFile = null;
        try {
            fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            
            fileInputStreamReader.read(bytes);
            System.out.println(bytes);
            encodedFile = Base64.getEncoder().encodeToString(bytes);
//            System.out.println("Imagen codificada base 64: "+encodedFile);
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: "+e);
        } catch (IOException e) {
        	System.out.println("IOException: "+e);
        }
        return encodedFile;
    }
}
