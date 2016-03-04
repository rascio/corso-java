package it.agecontrol.mazzocarte;

import java.util.Random;

public class ArrayUtils {
	
	private static final Random RANDOM = new Random();

	public static void shuffle(Object[] array) {
		for (int i = 0 ; i < array.length ; i++) {
			/*
			 * RANDOM.nextInt(n) restituisce un int casuale compreso tra 0 e n (escluso)
			 */
			int nuovaPosizione = RANDOM.nextInt(array.length);
			Object swap = array[nuovaPosizione];
			
			array[nuovaPosizione] = array[i];
			array[i] = swap;
		}
	}
}
