package it.r.corsi.java.lezione4;

public class OOPBase {

	public static void main(String[] args) {
		Persona sherlock = new Persona("Sherlock", "Holmes", new Indirizzo("Baker Street, 221B", "London"));
		
		Persona watson = new Persona("Watson", sherlock.getIndirizzo());
		
		try {
			Persona errore = null;
			
			System.out.println(errore.getNome());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
