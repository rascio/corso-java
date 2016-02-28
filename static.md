Metodi Statici
==============


I metodi statici sono funzioni che possono essere richiamate nel codice e ci servono per dividere al meglio la logica del nostro programma. Un metodo statico appartiene ad una classe e può essere utilizzando solamente facendo riferimento ad essa.

[Sorgente](CorsoJava/src/it/agecontrol/corsi/java/lezione2/MetodiStatici.java)

Package e import
-------
Le classi servono per raggruppari metodi che hanno qualcosa in comune, i package per raggruppare classi che hanno qualcosa in comune.
Nelle applicazioni si tendono a dividere le classi in base alle funzionalità dell'applicazione ed al tipo di problema che quella classe riceve, ad esempio un package:

        it.azienda.rubrica.model.contatti

		it.azienda.rubrica.database.contatti


Modificatori d'accesso (chi deve usare il metodo?)
-------
Quando si dichiara un metodo deve esserne specificato il suo _modificatore d'accesso_.
I modificatori d'accesso servono per limitare la visibilità del metodo in maniera da poterlo 	nascondere a chi non ha bisogno di utilizzarlo.
Per decidere quale modificatore d'accesso utilizzare è sempre necessario domandarci "A chi serve questo metodo?"

**public** => Il metodo è pubblico e chiunque ha visibilità sulla classe potrà vedere anche il metodo.

**private** => Il metodo è privato alla classe, viene utilizzato per i metodi che definiscono una logica che interessa solo alla classe che lo contiene e che non ci interessa condividere con altri.

**protected** / *default* (niente) => Il metodo è disponibile a tutte le classi facenti parte dello stesso package, viene utilizzato per quei metodi di utils che ci servono in più classi che trattano gli stessi concetti, ma non ci interessa condividere con tutti.