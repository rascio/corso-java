Rubrica 0.1
===========

Sviluppare un applicazione che offra un servizio di rubrica agli utenti registrati.

Inizializzazione db:

```sql
CREATE TABLE utenti (id INTEGER AUTO_INCREMENT, username VARCHAR(20), password VARCHAR(20), ruolo VARCHAR(10), abilitato BIT(1), PRIMARY KEY(id));

CREATE TABLE contatti (id INTEGER AUTO_INCREMENT, nome VARCHAR(20), cognome VARCHAR(20), telefono VARCHAR(20), email VARCHAR(20), utente_id INTEGER, PRIMARY KEY(id), FOREIGN KEY (utente_id) REFERENCES utenti(id));
```

Teoria: DAO
-----------

Per l'accesso al DB viene utilizzato il design pattern DAO (Data Access Object), che consiste nel creare per ogni tabella nel nostro db un JavaBean rappresentante un record, e un oggetto che espone metodi per eseguire le operazioni che ci interessano per quella tabella utilizzando il JavaBean nei suoi metodi publici.
I DAO delimitano la parte dell'applicazione che si occupa di lavorare con i dati, salvandoli, ricercandoli e aggregandoli.

[Sorgente](CorsoJava/src/it/r/corsi/java/jdbc/esercizio/dao/UtenteDao.java)

Parte 1
-------
- Creare il DAO corrispondente alla tabella `persone`.
- Testare i metodi.

Teoria: Service
---------------

I *Service* utilizzano lo strato dei DAO coordinandoli per realizzare operazioni di un livello più alto, rappresentando le operazioni che si vogliono far compiere ad un applicazione ad esempio la registrazione di un utente o la verifica della login. I metodi dei service utilizzano i DAO e i JavaBean nell'implementazione dei loro metodi, ma non li utilizzano nella definizione, nascondendo così lo strato del db ai loro client ed esprimendo operazioni di **dominio**.

[Sorgente](CorsoJava/src/it/r/corsi/java/jdbc/esercizio/service/UtenzeService.java)

Parte 2
-------

- Realizzare il service che si occupa della *rubrica* dell'utente registrato, offrendo operazioni per aggiungere e modificare un contatto.
- Testare il funzionamento.
