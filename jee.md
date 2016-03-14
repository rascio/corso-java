Java Enterprise Edition
=======================

JEE è la piattaforma Java per l'Enterprise. Esso consiste in una serie di tecnologie standard per la realizzazione di applicativi web.

Servlet
-------

Le Servlet sono una parte delle API offerte dallo standard JEE. Esse vengono utilizzate per implementare il protocollo HTTP. Java definisce lo standard delle Servlet, che consiste in una serie di interfacce e classi astratte per definire il comportamento di base della piattaforma.
Un applicativo sviluppato con utilizzando le servlet, ha bisogno di un *servlet container* per poter essere eseguito.

Moduli WAR
----------
Un file **.war** è un archivio di un modulo web eseguibile. Gli archivi **.war** seguono uno standard per la loro organizzazione interna delle cartelle, permettendo così di automatizzare e facilitare alcune cose durante l'esecuzione.

Struttura:

```
  applicazione.war
    |
    |-----META-INF
    |-----WEB-INF
            |
            |---- web.xml (configurazione della webapp)
            |
            |---- /lib (librerie per la webapp)
            |
            |---- /classes (sorgenti della webapp)

```

Servlet Container e Application Server
--------------------------------------
Essendo JEE solamente un'API, e ne esistono varie implementazioni, più o meno complete.
Un servlet container è un programma che implementa lo standard delle servlet, e permette l'esecuzione di moduli **.war**.
Il servlet container è solo una parte di un application server completo, che invece permette l'utilizzo anche di **EJB**, moduli **ear** e altre tecnologie.
