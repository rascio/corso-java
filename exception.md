Exception
=========

Cosa sono?
----------
Un'eccezione rappresenta un errore nell'esecuzione del programma, può accadere perchè è successo qualcosa di inaspettato nel sistema, oppure perchè si sta facendo qualcosa di "vietato".
Le eccezioni sono parte fondamentale, perchè vengono utilizzate per proteggere il programma da eventuali errori di programmazione, o input sbagliati.
Ad esempio se si volesse fare un metodo per calcolare la radice quadrata di un numero:

```java
	public static int radiceQuadrata(int n) throws Exception{
		if (n < 0) {
			throw new Exception("La radice quadrata non può essere calcolata per i numeri negativi: " + n);
		}
		//etc..
	}
```

In questa maniera possiamo garantire la correttezza dei nostri calcoli, e gestire con un errore quei casi particolari, per i quali non possiamo calcolare un risultato.

Quando viene lanciata un'eccezione l'esecuzione del codice viene bloccata, e la JVM inizia a ripercorrere lo stack del codice al contrario finchè non si verifica una delle seguenti condizioni:

- Nel tornare indietro la JVM incontra un blocco di codice try/catch utilizzato apposta per gestire l'eccezione e riprendere la normale esecuzione del codice.

- Lo stack torna indietro fino alla prima riga del processo, terminandolo in errore.

- Quando si vuole lanciare un'eccezione viene utilizzata la keyword `throw` accompagnata dalla creazione di un'eccezione.
	Nel caso il compilatore trovi la keyword `throw` ci segnalerà che l'eccezione deve essere "gestita" in qualche maniera, e abbiamo così due possibilità:

	1. Cambiare la dichiarazione del metodo, specificando il fatto che possa andare in errore.

	2. Utilizzare un try/catch per bloccare l'errore.

try/catch
---------
Il costrutto try/catch viene utilizzato per gestire e bloccare un'errore.
E' formato da tre parti 'try', 'catch' e 'finally'

```java
	try {
		operazione();
	}
	catch (Exception e) {
		System.out.println("C'è stato un errore");
		e.printStackTrace(); // <- stampa l'errore nell'output
	}
	finally {
		System.out.println("Il finally viene eseguito sempre");
	}
```
Il catch ed il finally possono essere omessi.

Exception e RuntimeException
----------------------------
In Java sono state differenziate 2 tipi di eccezioni, le checked e le unchecked.
I due tipi di eccezioni sono stati creati per indicare due tipologie differenti di errori, che riguardano la reazione che può avere il client del metodo.
La differenza tra i due tipi è il fatto che il compilatore ci obbligherà a gestire le checked.
Questo fa si che le **unchecked** exceptions vengano utilizzate per indicare errori di programmazione, come ad esempio un numero negativo in un metodo che accetta solo numeri positivi, mentre le **checked** vengono utilizzate per dichiarare errori che non dipendono dal programma stesso, ma da qualcosa di esterno a lui.
La gestione degli errori nei programmi non è cosa semplice, e per effettuarla al meglio è necessaria esperienza e tanti tentativi.

Quando viene creata un'eccezione in Java è possibile specificarne una causa (lancio un errore, perchè ho avuto un altro errore), in maniera tale che quando verrà stampata l'eccezione nei log, si possa fare un debug più accurato attraverso l'analisi dello stacktrace.

Quando gestire le eccezioni
---------------------------
La gestione dell'eccezione prevede la risoluzione del problema, che a volte può voler dire semplicemente segnalare il problema all'utente. Si pensi nell'ambito di un applicazione web, quando effettuando un calcolo si ha un errore, nello stack del codice bisognerà gestire i possibili errori e darne notifica all'utente inviando una pagina di errore, piuttosto che lasciar fallire il programma ed interrompere il processo.

[Sorgente](CorsoJava/src/it/r/corsi/java/exception/Eccezioni.java)
