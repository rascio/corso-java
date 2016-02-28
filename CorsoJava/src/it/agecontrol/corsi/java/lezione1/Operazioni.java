package it.agecontrol.corsi.java.lezione1;

public class Operazioni {

	public static void main(String[] args) {
		/*
		 * Dichiarazioni di variabili di tipo intero
		 */
		int a = 4;
		int b = 8;
		
		/*
		 * Dichiarazione di una variabile creata a partire dal risultato di un'operazione
		 */
		int risultato = a + b;
		
		/*
		 * Le operazioni base sono particolari e agiscono differentemente su tipi differenti
		 * In questo caso il '+' tra un numero e una stringa fa si che il numero venga trasformato in una stringa
		 */
		System.out.println("a + b = " + risultato);
		
		/*
		 * Per utilizzare una if va indicata una condizione all'interno
		 * delle parentesi. Il == serve per confrontare due valori.
		 * L'operazione modulo (%) viene utilizzata per calcolare il resto della divisione.
		 */
		if ((risultato % 2) == 0) {
			System.out.println("Il risultato è pari");
		}
		else {
			/*
			 * Dopo un if può essere dichiarato un blocco else da eseguire nel caso
			 * la condizione sia false
			 */
			System.out.println("Il risultato è dispari");
		}
		
		/*
		 * Un'if può contenere una serie di condizioni concatenate con le operazioni booleane
		 * && = AND -> true && false
		 * || = OR  -> true || false
		 *  ! = NOT -> !(true)
		 */
		if (risultato > 0 && risultato <= 10) {
			System.out.println("Il risulato è compreso tra 1 e 10");
		}
		
		/*
		 * Le operazioni non cambiano il tipo delle variabili, qundi
		 * una divisione tra interi restituisce un intero, perdendo così
		 * i valori decimali
		 */
		int divisioneInt = a / risultato;
		System.out.println("divisioneInt: " + divisioneInt);
		
		/*
		 * Il cast viene utilizzato per forzare il compilatore a cambiare il tipo
		 * della variabile. Un'operazione tra tipi differenti utilizza sempre il tipo
		 * più specifico dei due.
		 */
		float percentualeA = (((float) a) / risultato) * 100;
		System.out.println("a:" + a + " è il " + percentualeA + "% del risultato totale");
	}
}
