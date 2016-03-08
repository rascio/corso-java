package it.r.corsi.java.stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {
	
	public static void main(String[] args) {
		OutputStream fos = apriFile("testo.txt");
		
		write(fos, "Testo di prova");
		
		InputStream inputStream = leggiFile("testo.txt");
		
		String contenuto = read(inputStream);
		
		System.out.println("Il file contiene ora: " + contenuto);
	}
	
	public static OutputStream apriFile(String filename) {
		try {
			return new FileOutputStream(filename);
		} 
		catch (FileNotFoundException e) {
			throw new RuntimeException("Errore durante il recupero dello FileOutputStream per il file " + filename, e);
		}
	}
	
	public static InputStream leggiFile(String filename) {
		try {
			return new FileInputStream(filename);
		}
		catch (IOException e) {
			throw new RuntimeException("Errore durante l'apertura del file " + filename, e);
		}
	}

	
	public static void write(OutputStream stream, String text) {
		try {
			stream.write(text.getBytes());
		}
		catch (IOException e) {
			throw new RuntimeException("Errore durante la scrittura sullo stream", e);
		}
	}
	
	public static String read(InputStream stream) {
		return read(stream, 1024);
	}
	

	public static String read(InputStream stream, int readSize) {
		StringBuilder builder = new StringBuilder();
		/*
		 * Per leggere uno stream viene utilizzazto il metodo read
		 * che prende in ingresso un array di byte.
		 * Quando legge lo stream scrive i bytes letti nell'array
		 * e restituisce il numero di bytes letti (quando restituisce 0 è finito lo stream).
		 */
		byte[] read = new byte[readSize];
		try {
			while (stream.read(read) > 0) {
				builder.append(new String(read));
			}
		} catch (IOException e) {
			throw new RuntimeException("Errore durante la lettura dello Stream", e);
		}
		return builder.toString();
	}
}
