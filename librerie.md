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

List
----
L'interfaccia `List` rappresenta una lista mutabile con random access (tramite il metodo `get`).
Il `size()` di una lista, e l'`add()` e `remove()` di elementi.

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
