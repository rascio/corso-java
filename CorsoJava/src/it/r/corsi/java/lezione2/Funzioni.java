package it.r.corsi.java.lezione2;

class Funzioni {

	/**
	 * Calcola il Massimo Comun Divisore tra due numeri, utilizzando
	 * l'algoritmo di Euclide.
	 * @param a
	 * @param b
	 * @return
	 */
	public static int mcd(int a, int b) {
		if (a == 0) {
			return b;
		}
		if (b == 0) {
			return a;
		}
		if (a > b) {
			return mcd(a - b, b);
		}
		else {
			return mcd(a, b - a);
		}
	}

	/**
	 * Crea un array con un numero di elementi definiti, ma riempiendolo
	 * con valori random, entro un certo range.
	 * @param numeroElementi Il numero di elementi dell'array
	 * @param minimo Il valore minimo che gli elementi possono assumere
	 * @param massimo Il valore massimo che gli elementi possono assumere
	 * @return
	 */
	public static int[] creaArray(int numeroElementi, int minimo, int massimo) {
		int[] array = new int[numeroElementi];

		riempiConNumeriCasuali(minimo, massimo, array);

		return array;
	}

	private static void riempiConNumeriCasuali(int minimo, int massimo, int[] array) {
		int differenza = massimo - minimo;

		for (int i = 0 ; i < array.length ; i++) {
			/*
			 * Math.random() restituisce un numero casuale compreso tra 0 e 1
			 * lo moltiplichiamo per il numero massimo che vogliamo fargli raggiungere
			 */
			int casuale = (int) (Math.random() * differenza);
			array[i] = casuale + minimo;
		}
	}
}
