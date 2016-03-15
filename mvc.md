Model View Controller
=====================

L'MVC è un pattern architetturale che ci aiuta a strutturare la parte riguardante le interfacce della nostra applicazione.

L'MVC distingue 3 tipi differenti di classi

- **Model**: Il model è una struttura dati contenente i dati di ciò che ci interessa mostrare agli utenti
- **View**: La view è la parte del codice che gestisce l'interfaccia grafica utilizzando i dati di un *model*
- **Controller** Il controller è la logica che gestisce la *view* unendola con un *model*, e gestendone gli input chiamando il *business layer*

L'MVC è un design pattern molto comune nelle applicazioni web, ed è adottato dai maggiori framework.  
Utilizzando framework appositi, questi concetti vengono esplicitati con appositi oggetti che ce ne facilitano l'utilizzo.

Una Servlet può essere vista come il *controller* di una view che è una *jsp*. Il model invece è rappresentato da tutti gli oggetti che passiamo alla jsp tramite il `request.setAttribute(String, Object)`.
