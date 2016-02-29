Oggetti e OOP
=============

Cos'è un oggetto? (1/2)
-----------------------
Un oggetto non è nient'altro che un insieme di valori che decidiamo di mettere insieme per comodità, come ad esempio tutti i valori che descrivono una persona, un libro, una connessione ad un database o un file.
Java essendo un linguaggi 'statico' ci obbliga a dichiarare i nostri oggetti attraverso le classi. Dichiarare un oggetto significa specificare le sue proprietà, dandogli dei nomi e dichiarando il loro tipo.

```java
	public class Persona {
		public String nome;
		public String cognome;
		public int eta;
	}
```

In questa maniera viene fatta la dichiarazione di un'oggetto persona (dichiarazione = classe) contenente due proprietà di tipo String (nome e cognome) e una int (età)

I Wrapper (Integer, String, etc...)
---------
I valori utilizzati fino ad ora (int, float, boolean, etc...) vengono considerati dal linguaggi come 'valori primitivi', ovvero valori semplici.
Il linguaggio ci offre anche la loro controparte object, i cosidetti wrapper che sono le classi Integer, Float, Boolean, etc..

Il costruttore e le variabili di istanza
---------
Quando si dichiara un oggetto si può dichiarare anche il suo costruttore, ovvero un metodo "speciale" che utilizzeremo per creare un nuovo "contenitore" e passargli i suoi valori.
```java
	public class Integer {

		public int value;

		public Integer(int v) {
			value = v;
		}
	}
```
Il costruttore è un metodo "speciale" perchè il suo nome è lo stesso della classe e agisce in maniera particolare, assegnando le variabili di "istanza" dell'oggetto.

Il costruttore viene utilizzato attraverso la keyword 'new' e genererà ogni volta un nuovo contenitore:
```java
	Integer a = new Integer(1);
	Integer b = new Integer(2);

	System.out.println(a.value); //1
	System.out.println(b.value); //2
```
Quello che ci viene restituito da 'new Integer(1)' è un nuovo contenitore con dentro il valore 1 (etichettato come 'value').

Nel caso della Persona:
```java
	public class Persona {
		public String nome;
		public String cognome;

		public Persona(String n, String c) {
			nome = n;
			cognome = c;
		}
	}
```
E quindi poi: 
```java
	Persona p = new Persona("John", "Lennon");
```
Si dice "stato dell'oggetto" tutti i valori che un oggetto contiene in un determinato momento.
Ovviamente un oggetto, può contenere anche altri oggetti costruendo così una sorta di matrioska.
```java
	public class Indirizzo {
		public String via;
		public String citta;

		public Indirizzo(String v, String c) {
			via = v;
			citta = c;
		}
	}

	public class Persona {
		public String nome;
		public String cognome:
		public Indirizzo indirizzo;

		public Persona(String n, String c, Indirizzo i) {
			nome = n;
			cognome = c;
			indirizzo = i;
		}
	}
```

```java
	Persona p = new Persona("Sherlock", "Holmes", new Indirizzo("Baker Street, 221B", "London"));

	System.out.println(p.nome + " " + p.cognome + " vive a " + p.indirizzo.citta + " in " + p.indirizzo.via);
```
Una classe può definire anche più di un costruttore, ma solo uno potrà essere utilizzato per creare l'oggetto.
```java
	public class Persona {
		...
		public Persona(String n, String c, Indirizzo i) {
			nome = n;
			cognome = c;
			indirizzo = i;
		}

		public Persona(String c, Indirizzo i) {
			nome = "";
			cognome = c;
			indirizzo = i;
		}
	}
```

```java
	Persona p = new Persona("Sherlock", "Holmes", new Indirizzo("Baker Street, 221B", "London"));
	Persona p1 = new Persona("Watson", p.indirizzo);
```
Cos'è un oggetto? (2/2)
-------
Un'oggetto quindi non è un valore, ma bensì un contenitore di valori, per capire questo si prenda ad esempio il seguente pezzo di codice.

```java
	int a = 5;
	int b = 5;

	System.out.println(a == b); //true

	Integer a1 = new Integer(5);
	Integer b1 = new Integer(5);
	Integer c = a1;

	System.out.println(a1 == b1); // false
	System.out.println(a1.value == b1.value); // true
	System.out.println(a1 == c); // true
	System.out.println(b1 == c); // false
```
Qui possiamo vedere la differenza tra valori ed oggetti. 
Quando creiamo due valori (int) e li proviamo a confrontare questi restituiranno true se i due 	valori sono uguali.
Se andiamo a controllare la loro controparte ad oggetti il comportamento ci risulterà strano all'inizio. Quello che stiamo facendo è creare due contenitori (`a1` e `b1`) che contengono al loro interno lo stesso valore (`5`), ma quando andiamo a fare il confronto (`==`), noi confronteremo i due contenitori, che non sono lo stesso, ma due contenitori differenti e quindi il risultato ci darà false. Alla variabile c invece non viene assegnato un nuovo contenitore, ma uno già esistente, per questo quando si andrà a confrontare con lo stesso contenitore il risultato darà `true`.

Se volessimo vedere una versione semplificata della dichiarazione di Integer sarebbe:

Questa definisce un solo valore gestito (value) di tipo `int`, con modificatore d'accesso privato impossibile da accedere da fuori.
Per poter confrontare due interi quindi potremmo implementare un metodo statico che faccia il confronto delle due proprietà per noi, es.

```java
	public class Integer {

		public static boolean equals(Integer n1, Integer n2) {
			return n1.value == n2.value;
		}

		/*
		 * In realtà int viene dichiarato come 'private' in maniera da nascondere
		 * la sua esistenza all'estero, il motivo verrà spiegato in seguito.
		 */
		private int value;

		public Integer(int v) {
			value = v;
		}
	}
```

In questo caso:

```java
	Integer a = new Integer(5);
	Integer b = new Integer(5);
	Integer c = new Integer(6);

	Integer.equals(a, b); //true
	Integer.equals(b, c); //false
```

Metodi di istanza
---------
Molto spesso ci troveremo a scrivere metodi che riguardano 1 solo oggetto, o comunque dove c'è un oggetto principale:

```java
	public class Richiesta {
		private Integer id;
		private String stato:
		private String motivoRifiuto;

		public Richiesta(Integer _id) {
			id = _id;
			stato = "BOZZA";
		}

		public static void accetta(Richiesta r) {
			r.stato = "ACCETTATA";
		}

		public static void rifiuta(Richiesta r, String motivazione) {
			r.stato = "RIFIUTATA";
			r.motivoRifiuto = motivazione;
		}

		public static String stato(Richiesta r) {
			return r.stato;
		}
	}
```

Nello scrivere questi metodi Java ci aiuta, avendo quelli che vengono chiamati "metodi di istanza". Vista la necessità di avere metodi che fanno calcoli su oggetti, rimuovendo la keyword static, faremo in modo che il metodo non appartenga più in generale alla class Richiesta, ma apparterrà solamente alle istanze della classe.
Cosa vuol dire questo?
In realtà questo è una comodità che ci offre il compilatore, per far si che ogni volta che dichiariamo un metodo "di istanza", il metodo abbia un parametro in automatico (implicito) del tipo dell'oggetto stesso, il cui nome sarà 'this'.

Ovvero: 

```java
	public void accetta() {
		this.stato = "ACCETTATA";
	}

	/*
		Non compila in quanto 'this' è una keyword Java

		public void static accetta(Richiesta this) {
			this.stato = "ACCETTATA";
		}

	*/

	public void rifiuta(String motivazione) {
		this.stato = "RIFIUTATA";
		this.motivoRifiuto = motivazione;
	}

	public String stato() {
		return this.stato;
	}
```

Per utilizzare questi metodi, sarà obbligatorio avere a disposizione un'istanza dell'oggetto, e il metodo viene chiamato come fosse una proprietà dell'oggetto.

```java
	Richiesta richiesta = new Richiesta(1);

	richiesta.accetta();

	richiesta.rifiuta("Consegnata in ritardo.");
```

Null: "A billion dollar mistake"
--------
Cosa succede se ad una variabile non viene assegnato nessun valore nel costruttore?
In Java nel caso una variabile non venga inizializzato, la JVM assegnerà un valore di default, differente nel caso essa sia un valore primitivo, o un oggetto.
Nel caso dei valori primitivi il valore di default assegnato sarà 0 (o false nel caso dei boolean).
Nel caso degli oggetti la cosa diventa un po' più complicata.
Come valore 0 di un oggetto è stato introdotto il concetto di 'null', che sta a significare proprio l'assenza di un valore.
Purtroppo questo crea molta confusione, e ci può far trovare di fronte ad errori nel codice che possono portare a molta confusione se il concetto di null non è chiaro.
Linguaggi più evoluti, hanno modalità di gestione differente dell'assenza di un valore, rendendo le cose più semplici.
Una variabile può anche essere forzata ad essere null:
```java
	Persona p = null;
```
Che problemi da il null?
Se prendiamo la dichiarazione di prima e proviamo ad utilizzare l'oggetto:

```java
	Persona p = null;
	System.out.println("Il nome è " + p.nome);
```
Vedremo che durante l'esecuzione del codice questo lancerà un NullPointerException quando si proverà ad accedere una proprietà dell'oggetto, questo perchè essendo l'oggetto "assente" (null), la JVM non avrà nessuna variabile 'nome' da leggere, il NullPointerException serve proprio per avvertirtici di questo.

A cosa serve quindi il null?

Il null viene utilizzato per indicare l'assenza di un valore, un qualcosa di non fatto.
Vediamo per esempio la dichiarazione di un metodo per filtrare un array: 

```java
	public Persona[] filtraPerCognome(Persona[] persone, String cognome){
		Persona[] risultato = new Persona[0];

		for (Persona p : persone) {
			if (Objects.equals(p.cognome, cognome)) {
				risultato = append(p, risultato)
			}
		}

		return risultato;
	}
```

Questo metodo prende in ingresso un'array di persone e ne restituirà uno nuovo contenente solamente le persone filtrate. Cosa succede se non esiste nessuna persona per il cognome passato? La soluzione migliore sarà restituire un nuovo array di 0 posizioni, che ci indica il fatto che sono stati trovati 0 risultati, quando si lavora con risultati multipli questa gestione è molto semplice, ma nel caso di un metodo come:

```java
	public Persona filtraPerCodiceFiscale(Persona[] persone, String codiceFiscale) {
		for (Persona p : persone) {
			if (Objects.equals(p.codiceFiscale, codiceFiscale)) {
				return p;
			}
		}
		return null;
	} 
```

In questo caso quando verrà ricercato un codice fiscale che non identifica nessuna persona all'interno dell'array, allora sarà corretto restituire un null, per indicare il fatto che la cosa cercata è assente.

```java
	Persona p = filtraPerCodiceFiscale(persone, "SADWER55P21R10V");

	if (p == null) {
		System.out.println("Nessun risultato trovato");
	}
	else {
		System.out.println("Trovato " + p);
	}
```

E' importante però limitare lo scope delle variabili che possono essere null, e gestirlo sempre appena viene chiamato un metodo con possibile risposta null, in maniera da limitarne l'utilizzo e prevenire eventuali errori "confusi":

```java
	public static void creaFattura(String codiceFiscale, Float totale) {
		Persona p = PersonaDb.ricercaPerCodiceFiscale(codiceFiscale);

		if (p == null) {
			throw new RuntimeException("Non esiste nessun utente con codice fiscale " + codiceFiscale);
		}

		Fattura fattura = new Fattura(p.nome, p.cognome, p.partitaIva, totale);

		FatturaDb.salva(fattura);
	}
```

Encapsulation
-------
I metodi di istanza vengono usati anche per incapsulare, proprietà dell'oggetto e renderle accessibili all'esterno solo attraverso dei metodi predefiniti, avendo così un punto per poter gestire l'accesso ad una proprietà e garantendone sempre la "consistenza". Esempio:

```java
	public class Persona {
		private String nome;
		private String cognome;

		public Persona(String nome, String cognome) {
			this.nome = nome;
			this.cognome = cognome;
		}

		public String getNome() {
			return this.nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getCognome() {
			return this.cognome;
		}

		public void setCognome() {
			this.cognome = cognome;
		}
	}
```

In questa maniera si può nascondere all'esterno la vera natura interna dell'oggetto, ed avere un punto nel codice dove poter intercettare l'accesso e/o la modifica ad una proprietà dello stesso.

Il void e la modifica dello stato (CQS)
-----
La definizione più semplicistica di void è un metodo che non restituisce nulla, ma a cosa può servire un metodo del genere?
I metodi void vengono utilizzati per effettuare dei side effect, ovvero modifiche ad uno stato del sistema.
Un principio molto importante riguardante questo è CQS (Command Query Separation), che dice che ogni metodo può essere un 'command' che esegue un'azione (modifica allo stato), o una 'query' una domanda per ottenere un risultato, ma non tutti e due.
Ovvero fare 'domande' al sistema non dovrebbe modificarne la risposta.
Non sempre è possibile avere questa separazione (es. metodo 'pop' degli stack), ma più si riesce a mantenere questa separazione, e meno il codice aumenterà la sua complessità.
		
hashCode e toString
-------
Tutti gli oggetti possono implementare 2 metodi particolari hashCode e toString.
Il metodo toString viene utilizzato a fini di debug, e ci serve per dare una descrizione del nostro oggetto.

Persona.java
```java
	public String toString() {
		return "Persona(" + this.nome + " " + this.cognome + ")";
	}
```

Questo metodo viene chiamato automaticamente quando proviamo ad aggiungere un'oggetto ad una stringa:

```java
	Persona p = new Persona("Anakin", "Skywalker");

	System.out.println("la variabile 'p' vale: " + p);
```
L'hashCode invece è un metodo che implementa una funzione di hash sull'oggetto, in grado di generare un numero, che solo un'oggetto con quello stato può essere in grado di generare.
Questo metodo viene utilizzato in alcune strutture dati e in alcuni algoritmi di default della JVM proprio per le sue caratteristiche, che permettono di creare algoritmi generali a prescindere dal tipo di oggetto con il quale viene utilizzato.

JavaBean
-------
Per JavaBean si intende uno standard Java per scrivere determinati oggetti. Un JavaBean non è nient'altro che un oggetto che espone le sue proprietà tramite metodi get e set implementa i metodi equals, toString e hashCode ed ha un costruttore vuoto.

Lo standard JavaBean serve a Java per poter fare "magie" con i nostri oggetti e semplificare operazioni come la serializzazione di classi, o il binding automatico di parametri.

La classe Object
-------
I linguaggi ad oggetti implementano un concetto chiamato ereditarietà, ovvero tipi di oggetti differenti possono estendersi l'un l'altro e così ereditarietarne proprietà e metodi, e creando tra i due una relazione is-a. Ovvero se il tipo Integer estende il tipo Numero, allora il tipo Integer sarà anche un numero, permettendo cose tipo:

```java
	Number a = new Integer(3);
```
L'estensione viene dichiarata così: 

```java
	public class Number {

	}

	public class Integer extends Number {
		...
	}
```

Ogni classe in Java estende implicitamente la classe Object permettendo dichiarazioni del tipo:

```java
	Object a = new Integer(3);
```