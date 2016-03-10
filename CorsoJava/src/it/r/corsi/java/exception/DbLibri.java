package it.r.corsi.java.exception;

public class DbLibri {
	
	public static String[] ricercaLibro(String argomento){
		/*
		 * Quando viene utilizzato un metodo che dichiara un'eccezione, il compilatore ci forzerà a gestirla.
		 * In questo caso ci sono due possibili modi di gestirla:
		 * 
		 *  - aggiungere la dichiarazione 'throws Exception' anche a questo metodo
		 *  - gestire l'eccezione all'interno di un catch
		 */
		try {
			DbUtils.apriConnessioneDb();
		}
		catch (Exception e) {
			/*
			 * Nel caso in cui il database vada in errore, non ha senso far continuare l'esecuzione del metodo
			 * in quanto comunque non sarebbe possibile eseguire la query di ricerca, quindi si preferisce rilanciare
			 * un altro errore.
			 * La RuntimeException essendo unchecked non ha bisogno di essere gestita.
			 */
			throw new RuntimeException("Errore durante l'apertura della connessione", e);
		}
		
		String[] documenti = eseguiQueryRicercaLibro(argomento);
		
		DbUtils.chiudiConnessioneDb();
		
		return documenti;
	}
	
	/*
	 * In questo caso dichiarando l'eccezione nel metodo non è più necessario utilizzare il try/catch e si occuperà
	 * della gestione dell'errore chi chiamerà il metodo 'inserisciLibro'.
	 * 
	 * In questo punto possiamo anche vedere un problema di design, in quanto abbiamo due metodi molto simili (la ricerca e l'inserimento)
	 * che utilizzano il database, ma gestiscono un suo errore in maniera indipendente, e dovendo far utilizzare un comportamento differente
	 * a chi utilizza i metodi, in base al metodo utilizzato.
	 * 
	 * In questi casi è molto importante dare un'uniformità ai metodi esposti dalla classe, semplificandone l'utilizzo con delle dichiarazioni conformi tra loro.
	 */
	public static void inserisciLibro(String titolo) throws Exception {
		if (titolo.length() == 0) {
			throw new RuntimeException("Il titolo non può essere una stringa vuota");
		}
		
		DbUtils.apriConnessioneDb();
		
		System.out.println("esegui query: INSERT INTO libri VALUES ('" + titolo + "')");
		
		DbUtils.chiudiConnessioneDb();
	}


	private static String[] eseguiQueryRicercaLibro(String argomento) {
		System.out.println("esegue query: SELECT titolo FROM libri WHERE argomento = '" + argomento + "'");
		/*
		 * Versione rapida per scrivere:
		 * 
		 * String[] risultato = new String[5];
		 * risultato[0] = "Growing Object-Oriented Software Guided by Test";
		 * risultato[1] = "Patterns of enterprise application architecture";
		 * ...
		 * return risultato;
		 */
		return new String[]{
			"Growing Object-Oriented Software Guided by Test",
			"Patterns of enterprise application architecture",
			"Domain Driven Design: Tackling Complexity in the Heart of Software",
			"Enterpise Integration Pattern",
			"Implementing Domain Driven Design"
		};
	}
}
