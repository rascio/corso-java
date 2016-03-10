Java Virtual Machine
====================

Esegue bytecode
---------------
La Java Virtual Machine è un programma che viene utilizzato per interpretare il bytecode ed eseguirlo. Il bytecode è un codice di basso livello, veloce da eseguire ma illegibile.

Gestisce la memoria (garbage collector)
------------
La JVM si occupa anche della gestione della memoria, allocando spazio in RAM per le variabili e disallocandolo quando non più utilizzate. Di questo processo se ne occupa il garbage collector, grazie ad algoritmi atti ad evitare sprechi.

I memory leaks
------------
Una gestione erronea della memoria, è difficile, ma quando capita porta ad un malfunzionamento del garbage collector, che non eliminando lo spazio occupato inutilmente, a lungo andare porta all'esauriemento dello spazio causando l'errore `PermGen` e/o `OutOfMemoryException` ad indicare la saturazione della memoria.

Compilazione e linguaggi JVM
-------------
Il bytecode viene generato da un compilatore che funziona da traduttore tra due linguaggi. In questa maniera viene utilizzato per trasformare linguaggi di più alto livello in bytecode. Questo permette di avere più linguaggi di alto livello che compilati possono essere eseguiti sulla JVM sono degli esempi: Java, Scala, Clojure, Groovy.

Linguaggi statici / dinamici
--------------
Java è un linguaggi statico, il che significa che a differenza dei linguaggi dinamici (es. PHP, Javascript) per poter utilizzare una variabile è obbligatorio specificarne il tipo.
Questo viene imposto dal compilatore che durante la sua esecuzione si occupa di effettuare un check delle dichiarazioni, e dell'utilizzo di variabili e metodi individuando eventuali incongruenze e bloccando la compilazione. In questa maniera possono essere descritti e dichiarati tipi differenti rappresentatnti concetti differenti, ed avere un test immediato della correttezza del codice.
