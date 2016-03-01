package it.r.corsi.java.lezione3;

public class DbUtils {
	
	private static final boolean DATABASE_ATTIVO = false;

	
	public static void chiudiConnessioneDb() {
		System.out.println("Connessione chiusa");
	}

	/*
	 * Il metodo che apre la connessione verso il database dichiara la possibilità di errore
	 * durante la sua esecuzione, costringendo i 'client' del metodo a gestire il caso in cui
	 * il database non sia attivo.
	 * 
	 * Se rimuovessimo il 'throws Exception' dalla dichiarazione del metodo il compilatore andrebbe in errore
	 * alla riga dove viene effettuato il 'throw new Exception', in quanto avremmo il lancio di un'eccezione checked
	 * non gestita.
	 */
	public static void apriConnessioneDb() throws Exception {
		if (DATABASE_ATTIVO) {
			System.out.println("Connessione aperta");
		}
		else {
			throw new Exception("Il database non è attivo.");
		}
		
	}
}
