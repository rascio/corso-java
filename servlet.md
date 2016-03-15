Servlet
=======

Una servlet è un componente web che risponde ad una request http su un determinato indirizzo.  

Per definire una servlet basta creare una classe che estende `javax.servlet.http.HttpServlet` e registrarla con un url attraverso l'annotation `javax.servlet.annotation.WebServlet`:

```java
@WebServlet(urlPatterns="/hello-world")
public class HelloWorldServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		String nome = req.getParameter("nome");
		if (nome == null || nome.isEmpty()) {
			nome = "World";
		}

    /*
     * Lascia finire la gestione della request ad una jsp
     */
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/pages/hello.jsp");
		dispatcher.forward(req, resp);
	}
}
```

Nell'esempio precedente viene definita una servlet che risponde ad una chiamata `GET` all'indirizzo `http://host/context-path/hello-world`.  
Per gli altri verbi HTTP si può fare l'override del relativo metodo `doGet`, `doPost`, `doPut`, `doDelete`, `doOptions`.  

L'`HttpServletRequest` e l'`HttpServletResponse` sono due oggetti che vengono utilizzati per interagire con la request e la response della chiamata.  
I principali metodi dell'`HttpServletRequest`:

- `getParameter(String)` legge un query param per le chiamate GET o un form param per le chiamate POST.
- `getHeader(String)` legge un'header della richiesta
- `getSession()` recupera la sessione dell'utente
- `getInputStream()` legge il body della richiesta
- `getRequestDispatcher(String)` restituisce un `RequestDispatcher` per fare il *forward* della richiesta ad una jsp
- `getServletContext()` recupera il "context" dove è in esecuzione la servlet
- `setAttribute(String, Object)` scrive un'attributo nel "context" della richiesta

Scopes
------
Il servlet container permette di gestire diversi scope o context, per depositare degli oggetti.  
Lo scope/context in cui viene depositato un oggetto ne delimita la visibilità e la durata, gli scope sono gerarchici e divisi in:

- **Application Scope** => `ServletContext` Viene creato quando l'applicazione è inizializzata e viene terminato quando l'applicazione viene stoppata
- **Session Scope** => `HttpSession` È lo scope rappresentante la sessione dell'utente, viene creato ad una nuova connessione, e viene terminato dopo un tempo preimpostato di inattività
- **Request Scope** => `HttpServletRequest` È lo scope utilizzato per impostare attributi da utilizzare durante l'elaborazione della request


Listener
--------
Possono essere definiti dei componenti "listener" che ascoltano determinati "eventi" ai quali reagiscono.  
Per definire un listener basta creare una classe Java che implementa l'interfaccia listener che ci interessa.  
I due listener più importanti sono:

- `javax.servlet.ServletContextListener` che offre i metodi per reagire allo startup e allo stop dell'applicazione
- `javax.servlet.http.HttpSessionListener` che offre i metodi per reagire alla creazione alla distruzione di una sessione.

Una volta definito un listener basta marcarlo con l'annotation `javax.servlet.annotation.WebListener` per attivarlo nell'applicazione.

Esempio:

```java
@WebListener
public class ApplicationStartupListener implements ServletContextListener{

  @Override
  public void contextDestroyed(ServletContextEvent evt) {

  }

  @Override
  public void contextInitialized(ServletContextEvent evt) {
    DataSource applicationDataSource = Application.dataSource();
    evt.getServletContext().setAttribute("datasource", applicationDataSource);
  }

}
```
