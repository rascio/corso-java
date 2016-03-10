Java DataBase Connectivity
==========================

JDBC è il framework Java per le connessioni ai database.
Definisce lo standard che viene poi implementato dai vendor di database.
Quando si vuole utilizzare un database va importata la libreria che implementa lo standard JDBC (anche detta il *driver*).

DriverManager
-------------
Il `DriverManager` è la classe che si occupa di aprire la connessione (`Connection`) con il database.
Al database deve essere fornita una stringa di connessione custom per il tipo di database.

Connection
----------
La `Connection` rappresenta una connessione verso il database, e viene utilizzata per eseguire le query.
La `Connection` è transazionale (se supportato dal db) e offre i metodi di commit e rollback.

[Sorgente](CorsoJava/src/it/r/corsi/java/jdbc/Main.java)
