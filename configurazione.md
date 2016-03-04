Configurazione
==============

Jdk7
----
[Download](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)

Per installare Java vanno settate due variabili di ambiente:

- `JAVA_HOME` Dovrà contenere il path alla cartella `jdk-{versione}` di java (quella che contiene la cartella `bin`)
- `PATH` La variabile `PATH` da al sistema operativo una lista di cartelle dove cercare per gli eseguibili. A questa variabile dovremo aggiungere il path alla cartella `bin` della jdk, per farlo aggiungeremo al path un riferimento a `${JAVA_HOME}/bin`

Windows:
`C:\qualcheprogramma;%JAVA_HOME%\bin`
Linux:
`export PATH=$PATH:$JAVA_HOME/bin`

Verifica Installazione Java
---------------------------
java -version
javac -version

JRE Java Runtime Environment

JDK Java Development Kit

Eclipse (IDE)
-------------
[Download](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/marsr)
