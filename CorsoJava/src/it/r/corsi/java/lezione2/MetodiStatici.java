package it.r.corsi.java.lezione2;

import java.util.Arrays;


public class MetodiStatici {

	public static void main(String[] args) {
		
		System.out.println(calcola(5));
		System.out.println(calcola(4));
		
		System.out.println("MCD tra 10 e 6 -> " + Funzioni.mcd(10, 6));
		System.out.println("MCD tra 252 e 105 -> " + Funzioni.mcd(252, 105));
		
		int[] numeri = Funzioni.creaArray(20, 15, 50);
		
		System.out.println(numeri); //Gli array vengono stampati male
		System.out.println(Arrays.toString(numeri)); //Per stamparli per debug viene utilizzato il toString

		/*
		 * Il metodo sort modifica il nostro array riordinandolo, questo metodo non è side effect free.
		 */
		
		
		System.out.println(Arrays.toString(numeri));
		System.out.println("Contiene il numero 20? " + contiene(numeri, 20));
		
		
	}
	
	public static String calcola(int n) {
		if (pari(n)) {
			return n + " è pari";
		}
		else {
			return n + " è dispari";
		}
	}
	
	public static boolean pari(int n) {
		return n % 2 == 0;
	}
	
	public static boolean contiene(int[] array, int n) {
		Arrays.sort(array);
		return Arrays.binarySearch(array, n) > -1;
	}
}
