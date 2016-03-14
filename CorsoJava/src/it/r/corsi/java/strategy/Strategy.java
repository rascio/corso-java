package it.r.corsi.java.strategy;


public interface Strategy<I, O> {

	O execute(I i);
}
