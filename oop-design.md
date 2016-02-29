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

La similitudine migliore per un sistema ad oggetti è un puzzle, dove l'interfaccia corrisponde all'attacco (al lato) di un pezzettino di un puzzle, definendo così l'attacco che deve avere un pezzo per poter essere unito:

![interface as puzzle]
(interface-puzzle.png)

Questa tecnica
Classi Astratte
-----
Inner Class e Anonymous Class
-----
Composition vs Inheritance
-----
Generics
-----
