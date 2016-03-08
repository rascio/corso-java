package it.r.corsi.java.sintassi;

import java.util.Arrays;

import it.r.corsi.java.lezione4.Scatola;

public class Main {

	public static void main(String[] args) {
		String nome = "manuel";
		
		if (nome != null) {
			System.out.println("nome è null");
		}
		Integer num = new Integer(5);
		
		num = Matematica.quadrato(num);
		
		System.out.println("Creo la scatola");
		Scatola s = new Scatola("dado", 5);
		
		System.out.println(Arrays.toString(s.contenuto));
		System.out.println("Scatola creata");
		Integer volumeS = s.volume();
		System.out.println("Il volume è " + volumeS);
		
		Integer v2 = Scatola.volume2(s);
		
//		Integer v3 = new Scatola(20).volume();
//		Integer v4 = Scatola.volume2(new Scatola(20));
		
		Scatola s2 = new Scatola("bicicletta", 100);
		System.out.println(s2.volume());
		
		
		
		
		
	}
}
