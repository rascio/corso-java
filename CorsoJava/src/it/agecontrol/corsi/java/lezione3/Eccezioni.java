package it.agecontrol.corsi.java.lezione3;



public class Eccezioni {
	
	public static void main(String[] args) {
		
		String risultato = ricerca("software design");
		
		System.out.println(risultato);
		
		System.out.println("Fine del programma.");
	}
	
	private static String ricerca(String argomento) {
		/*
		 * Tenta la ricerca e ne gestisce un possibile errore. 
		 * In questo caso il try/catch viene utilizzato perchè sappiamo che ogni riga può andare in errore
		 * a prescindere se li dichiara oppure no, e questo in questo caso è il posto ottimale per gestire
		 * un qualsiasi errore e dare un messaggio di risposta all'utente.
		 */
		String[] risultati;
		try {
			risultati = DbLibri.ricercaLibro(argomento);
		}
		catch (Exception e) {
			/*
			 * Nel caso accada un errore, questo viene stampato nel log per questioni di debug, ed il metodo
			 * viene interrotto (return) restituendo un messaggio di errore.
			 */
			e.printStackTrace();
			return "Si è verificato un problema durante l'esecuzione del programma";
		}
		//Nel caso la ricerca abbia successo, i risultati vengono formattati in una stringa
		return trasformaInTesto(risultati);
	}



	private static String trasformaInTesto(String[] libri) {
		String risultato = "Sono stati trovati " + libri.length + " libri:\n";
		
		for (String documento : libri) {
			risultato += "    - " + documento + "\n";
		}
		return risultato;
	}
}
