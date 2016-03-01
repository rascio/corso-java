package it.r.corsi.java.lezione4;

public class SideEffects {

	public static void main(String[] args) {
		Scatola scatola = Scatola.vuota();
		
		descrivi(scatola);
	
		/*
		 * Modifica la proprietà contenuto dell'oggetto scatola
		 * 
		 * setContenuto è un mutator, in quanto modifica lo stato dell'oggetto
		 */
		scatola.setContenuto("scarpe");
		
		descrivi(scatola);
		
		inserisciGatto(scatola, "bianco");
		
		descrivi(scatola);
		
		String contenuto1 = svuota(scatola);
		String contenuto2 = svuota(scatola);
		
		/*
		 * Possiamo vedere il problema dei metodi ibridi, in quanto passando due volte lo stesso
		 * parametro, avremo due risultati differenti.
		 */
		System.out.println("contenuto1: " + contenuto1);
		System.out.println("contenuto2: " + contenuto2);
	}
	
	/*
	 * Per quanto strano, anche il metodo descrivi è un mutator, difatti anche lui modifica
	 * uno stato, questa volta però l'oggetto modificato non viene passato come parametro del metodo,
	 * ma viene acceduto staticamente (System.out), la modifica effettuata è proprio all'output
	 * del programma, che anche lui ha un suo stato.
	 */
	public static void descrivi(Scatola scatola) {
		if (scatola.isVuota()) {
			System.out.println("La scatola è vuota");
		}
		else {
			System.out.println("La scatola contiene: " + scatola.getContenuto());
		}
	}
	
	/*
	 * Il metodo inserisciGatto, è un mutator anche lui in quanto modifica un'oggetto passatogli
	 */
	public static void inserisciGatto(Scatola scatola, String colore) {
		scatola.setContenuto("gatto " + colore);
	}
	
	/*
	 * Il metodo svuota è un esempio di un metodo misto, in quanto esegue una lettura
	 * restituendo il contenuto della scatola, modificandone però lo stato.
	 * 
	 * Metodi del genere sono sconsigliati, avere dei metodi idempotenti (ovvero che chiamandoli
	 * più volte restituiscono sempre lo stesso risultato) rende il codice più semplice
	 * da comprendere, e da ragionarci sopra.
	 */
	public static String svuota(Scatola scatola) {
		String contenuto = scatola.getContenuto();
		scatola.setContenuto(null);
		return contenuto;
	}
}
