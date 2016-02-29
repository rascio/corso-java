OOP Design
==========

Interfacce
----------
Ogni oggetto nella programmazione ad oggetti viene utilizzato da altri oggetti "client". I client possono utilizzare solamente l'interfaccia pubblica di un oggetto (ovvero tutti i metodi e variabili `public`).
I linguaggi ad oggetti, ci permettono di dichiarare anche solamente l'interfaccia pubblica di un oggetto, senza la sua implementazione, es.

```java
public interface LibroDb {

	public void inserisci(Libro libro);

	public Libro[] cercaTutti();

	public Libro[] cercaPerArgomento(String argomento);

}
```

A differenza degli oggetti, le interfacce non possono essere istanziate, questo perchè non sono una definizione completa di oggetto (manca l'implementazione dei metodi).
Un oggetto però può "implementare" un'interfaccia, e questo lo obbligherà a definire quei metodi dichiarati nell'interfaccia.

```java
public class LibroDbFinto implements LibroDb {

	public void inserisci(Libro libro) {
		System.out.println("Inserisci libro " + libro)
	}

	public Libro[] cercaTutti() {
		return new Libro[] {
			new Libro("Spring in Action"),
			new Libro("Pragmatic Programmer"),
			new Libro("Solr in Action")
		};
	}

	public Libro[] cercaPerArgomento(String argomento) {
		System.out.println("ricerca per: " + argomento);
		return cercaTutti();
	}
}
```

`implements` come `extends` crea una relazione *is-a* tra le due classi, facendo così diventare la classe `LibroDbFinto` un `LibroDb`, permettendo quindi dichiarazioni tipo:

```java
public static void main(String[] args) {
	LibroDb libroDb = new LibroDbFinto();

	inserisciLibri(3, libroDb);
}

public static void inserisciLibri(Integer numero, LibroDb libroDb) {
	for (int i = 0 ; i < numero ; i++) {
		Libro libro = new Libro("Libro #" + i);
		System.out.println("Inserisci libro " + libro);

		libroDb.inserisci(libro);
	}
}
```

Le interfacce sono un componente molto importante al livello di design, e un loro utilizzo corretto semplifica di molto il codice, perchè ci permettono di dividere il **cosa** un oggetto deve fare dal **come** esso lo esegue.
Nell'esempio precedente possiamo vedere come le operazioni su database per gestire un `Libro` siano state dichiarate in un'interfaccia, che ci permette di utilizzarle tramite l'interfaccia, ma senza dover conoscere a priori il vero oggetto che verrà utilizzato per effettuare l'operazione.
Questo trucco ci permette di avere implementazioni differenti degli stessi metodi, e decidere quale implementazione utilizzare in base a quello che dobbiamo fare.
Definire le operazioni verso il database tramite un'interfaccia, ci permette di poter scrivere il metodo `inserisciLibri` senza dover conoscere (e/o implementare) la logica di connessione al database, anzi ci permette di avere anche *implementazioni* differenti degli stessi metodi. Es:
```java
public class MysqlLibroDb implements LibroDb {
	public void inserisci(Libro libro) {
		Mysql mysql = new Mysql("host", "username", "password");
		//implementazione di connessione e query mysql
	}
}

public class OracleLibroDb implements LibroDb {
	public void inserisci(Libro libro) {
		OracleDb oracle = new OracleDb("SID", "host", "username", "password");
		//implementazione di connessione e query mysql
	}
}
```
Interfacce come puzzle
-----
Riprendendo il metodo `inserisciLibri`, osserviamo bene come l'utilizzo dell'interfaccia ci permette di dividere due tipologie differenti di problemi e poterli risolvere in *contesti* differenti, senza che questi si conoscano l'un l'altro.
Il ruolo dell'interfaccia nel design OOP è proprio questo, ci permette di definire solamente l'interazione tra due componenti del sistema, lasciando che esse conoscano solamente il contratto (l'interfaccia), ed essendo libere così di evolversi in autonomia senza avere dipendenze.
Questo ci permette così di ragionare più facilmente su alcuni metodi, sapendo che determinate cose verranno gestite da chi implementa l'interfaccia, o quando si implementa l'interfaccia sapendo che determinate cose vengono gestite da chi la richiama.

La similitudine migliore per un sistema ad oggetti è un puzzle, dove l'interfaccia corrisponde all'attacco (al lato) di un pezzettino di un puzzle, definendo così l'attacco che deve avere un altro pezzo per poter essere unito:

![interface as puzzle]
(interface-puzzle.png)

Questa tecnica ci permette di poter avere implementazioni differenti degli stessi metodi, e poter utilizzare una determinata logica in un momento, ed un'altra in un altro caso. Si pensi ad esempio nella situazione di test di logiche complesse, supponendo che la nostra applicazione debba eseguire dei calcoli complessi facendo 3-4 ricerche nel database per poter calcolare qualcosa, potremmo avere un metodo che utilizza l'interfaccia `EntityDb` che implementa questa logica, demandando all'interfaccia l'esecuzione effettiva delle query.
In questa maniera potremmo utilizzare un'implementazione finta di `EntityDb` che restituisce dati preimpostati (*mock*) per eseguire dei test da codice dei quali siamo certi del risultato che il nostro metodo ci deve restituire, es:

```java
public class TestCalcoloComplesso {
	public static void main(String[] args) {
		EntityDb entityDb = new EntityDbMock();
		Integer risultatoAtteso = 25;
		Integer risultato = Applicazione.calcoloComplesso(entityDb);

		if (!Objects.equals(risultato, risultatoAtteso)) {
			System.out.println("Errore! Atteso: " + risultatoAtteso + ", invece è stato calcolato: " + risultato);
		}
		else {
			System.out.println("Test eseguito con successo!");
		}
	}
}
```

Classi Astratte
-----
In Java possiamo creare anche quelle che vengono chiamate classi astratte, che sono un mix tra classi normali (concrete) e interfacce. Una classe astratta definisce un tipo che non può essere istanziato, ma che può dichiarare metodi *astratti* come un'interfaccia che dovrà essere implementato dalla classe concreta che lo estenderà.

```java
public abstract class AbstractRichiestaHttpManager {
	public String gestisci(HttpRequest request) {
		if (Objects.equals("GET", request.getMethod())) {
			return doGet(request);
		}
		else {
			return doPost(request);
		}
	}

	protected abstract String doGet(HttpRequest request);
	protected abstract String doPost(HttpRequest request);
}

public class PaginaFormPersona extends AbstractRichiestaHttpManager {
	@Override
	protected String doGet(HttpRequest request) {
		return "<html><body><form><input name='nome'/><input name='cognome'/></form></body></html>";
	}
	@Override
	protected String doPost(HttpRequest request) {
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");

		Rubrica.inserisciPersona(nome, cognome, new MySqlPersonaDb());

		return "<html><body><h1>" + nome + " " + cognome + " inserito con successo!</h1></body></html>";

	}
}
```

Composition vs Inheritance
--------------------------

Molto spesso l'utilizzo di una classe astratta può essere riscritto attraverso l'utilizzo di un'interfaccia, si prenda l'esempio:

```java
public abstract class AbstractScriptMySql {
	public void esegui(MysqlDb db) {
		Connessione connessione = Mysql.creaConnessione(db);
		try {
			this.execute(connessione);
			connessione.commit();
		}
		catch (Exception e) {
			connessione.rollback();
		}
		finally {
			connessione.chiudi();
		}
	}

	protected void execute(Connessione connessione);
}

public class InserimentoIniziale extends AbstractScriptMySql {
	@Override
	protected void execute(Connessione connessione) {
		connessione.eseguiQuery("INSERT INTO colori VALUES ('GIALLO')");
		connessione.eseguiQuery("INSERT INTO colori VALUES ('ROSSO')");
		connessione.eseguiQuery("INSERT INTO colori VALUES ('VERDE')");
	}
}

//infine

InserimentoIniziale inserimento = new InserimentoIniziale();
inserimento.esegui(DbApplicazione.crea());
```

In questo caso può essere riscritto come:

```java
public interface ScriptLogic {
	void execute(Connessione connessione);
}

//ex AbstractScriptMySql
public class MysqlScript {
	private ScriptLogic logic;

	public MysqlScript(ScriptLogic logic) {
		if (logic == null) {
			throw new RuntimeException("La logica non può essere null");
		}
		this.logic = logic;
	}

	public void esegui(MysqlDb db) {
		Connessione connessione = Mysql.creaConnessione(db);
		try {
			this.logic.execute(connessione);
			connessione.commit();
		}
		catch (Exception e) {
			connessione.rollback();
		}
		finally {
			connessione.chiudi();
		}
	}
}

public class InserimentoIniziale implements ScriptLogic {
	@Override
	protected void execute(Connessione connessione) {
		connessione.eseguiQuery("INSERT INTO colori VALUES ('GIALLO')");
		connessione.eseguiQuery("INSERT INTO colori VALUES ('ROSSO')");
		connessione.eseguiQuery("INSERT INTO colori VALUES ('VERDE')");
	}
}

//infine

MysqlScript script = new MysqlScript(new InserimentoIniziale());
script.esegui(DbApplicazione.crea());
```

Anonymous Class
---------------
Java in realtà ci permette di istanziare classi astratte e interfacce, a patto però che noi gli forniamo un'implementazione dei metodi, ad esempio avendo:

```java
public interface Eseguibile {
	/*
	 * Come 'abstract' anche il 'public' nelle interfacce è implicito
	 */
	void esegui();
}
```

Possiamo poi usarla come:

```java
Eseguibile e = new Eseguibile() {
	public void esegui() {
		System.out.println("Fai qualcosa!");
	}
};
eseguiTraDieciMinuti(e);
```

oppure:

```java
eseguiTraDieciMinuti(new Eseguibile() {
	public void esegui() {
		System.out.println("Fai qualcosa!");
	}
});
```
Generics
-----
