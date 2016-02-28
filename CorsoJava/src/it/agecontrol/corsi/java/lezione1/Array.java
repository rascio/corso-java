package it.agecontrol.corsi.java.lezione1;

public class Array {

	public static void main(String[] args) {
		/*
		 * Dichiarazione di un array di 10 posizioni
		 */
		int[] arrayInt = new int[10];
		
		/*
		 * Imposta le prime tre posizioni con dei valori
		 */
		arrayInt[0] = 14;
		arrayInt[1] = 26;
		arrayInt[2] = 32;
		
		/*
		 * Se decommentata questa linea lancia un errore.
		 * L'ultima posizione per un array di 10 elementi è la '9'
		 */
//		arrayInt[10] = 5;
		
		int contatore = 0;
		
		while (contatore < 5) {
			System.out.println("contatore: " + contatore);
			/*
			 * il ++ viene utilizzato come forma abbreviata di:
			 * contatore = contatore + 1;
			 * Un'altra forma di scrivere la stessa cosa è:
			 * contatore += 1;
			 */
			contatore++;
		}
		
		System.out.println("Contatore vale: " + contatore);
		
		/*
		 * Forma abbreviata
		 */
		for (int i = 0 ; i < 5 ; i++) {
			System.out.println("contatore: " + i);
		}
		
		/*
		 * Se decompilata la linea sotto, il compilatore andrà in errore
		 * segnalandoci che la variabile 'i' non esiste, perchè appartiene ad un blocco
		 * di codice più specifico
		 */
//		System.out.println("i: " + i);
		
		/*
		 * Esegue un for per tutta la lunghezza dell'array e ne legge il contenuto
		 */
		for (int i = 0 ; i < arrayInt.length ; i++) {
			System.out.println("Posizione " + i + " valore " + arrayInt[i]);
		}
	}
}
