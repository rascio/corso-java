Librerie
========
Import e export di un jar
-------------------------
Java permette l'esportazione di un progetto tramite il formato JAR.
JAR non è nient'altro che uno zip, che con delle META-INF può diventare anche eseguibile.
I JAR vengono utilizzati anche per condividere librerie di codice che risolvono problemi specifici (es. java.util o commons-\*)
Per utilizzare un JAR a runtime è necessario averlo nel classpath di compilazione e esecuzione.

Classpath
---------
Il classpath per caricare i .class
Il classpath è una lista di cartelle e files (archivi) dove la JVM cercherà le classi.

Java Collections Framework
----------------
È una libreria che contiene strutture dati base.
Per ogni struttura dati è definita la sua interfaccia che ne descrive il comportamento generico.
Per ogni interfaccia Java ci mette a disposizione implementazioni differenti.

Collection
----------

```
Class Diagram (semplificato)

                               Collection
                                   |
                    ------------------------------
                    |                            |
                  List                          Set
                    |                       ------------
          --------------------              |          |
          |         |        |           HashSet    SortedSet
      ArrayList   Vector  LinkedList                   |
                                                    TreeSet
```

List
----
L'interfaccia `List<T>` rappresenta una lista mutabile con random access.
La lista offre i metodi:
- `size()` per leggere la lunghezza della lista
- `get(int i)` per leggere l'elemento `i`esimo della lista
- `add(T t)` per aggiungere l'elemento alla lista
- `remove(int/T)` per rimuovere un elemento dalla lista, per indice e per elemento.

`Collections.sort(Comparable)` ovvero come ordinare una collection.

Creare le liste utilizzando `ArrayList`, `LinkedList` e `Vector`.

```java
List lista = new ArrayList();

lista.add(5);
lista.add(6);
lista.add(7);

System.out.println(lista);
```

Set
---
Il `Set` è come una lista, ma implementa il concetto di insieme, garantendo l'unicità degli elementi al suo interno, e non garantendone invece l'ordine.

```java
Set<Integer> set = new HashSet<>();

System.out.println(set);

set.add(1);

System.out.println(set);

set.add(2);
set.add(2);

/*
 * Il 2 viene inserito una sola volta.
 */
System.out.println(set);
```

Java dichiara anche un tipo di `Set` ordinato, tramite l'interfaccia `SortedSet`, implementata dalla classe `TreeSet`.

Map
---
La `Map` non è nient'altro che una lista, dove ogni elemento è associato ad una **key**, che deve essere unica (stesso concetto della PK di un db relazionale).

La `Map` può essere utilizzata per creare degli indici.

```java
Map<String, Persona> map = new HashMap<>();
map.put("batman", new Persona("Bruce", "Wayne"));
map.put("superman", new Persona("Clark", "Ant"));

System.out.println(map);
```

Iterable e foreach
------------------

Il costrutto *for each* in Java non è implementato solo per gli array, anzi, può essere utilizzato con tutte le classi che implementano l'interfaccia `Iterable`.

```java
/*
 * Il metodo 'Arrays.asList' prende un numero indefinito di parametri
 * Questo tipo di parametro speciale è chiamato varargs
 */
List<Integer> lista = Arrays.asList(5, 7, 13, 35, 3, 76, 100, 9, 21);

//Stampa tutti i numeri nella lista maggiori di 15
for (Integer n : lista) {
  if (n > 15) {
    System.out.println(n);
  }
}
```

I/O Stream
----------
Rappresentano una fonte di bytes da usare per la lettura (`InputStream`) o la scrittura (`OutputStream`).
Un esempio del loro utilizzo è la scrittura e lettura di file tramite le loro implementazioni `FileInputStream` e `FileOutputStream`.
Un altro esempio del loro utilizzo è la comunicazione via rete, gestita dagli stream delle socket.
Infine come ultimo esempio possiamo vedere i due stream statici `System.in` e `System.out`.

[Sorgente](CorsoJava/src/it/r/corsi/java/stream/Main.java)
