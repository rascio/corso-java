package it.r.corsi.java.sintassi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.agecontrol.mazzocarte.Carta;
import it.r.corsi.java.lezione4.Scatola;

public class Main {

	public static void main(String[] args) {
		
		List<Carta> carte = new ArrayList<Carta>();
		
		carte.add(Carta.bastoni(7));
		carte.add(Carta.bastoni(8));
		carte.add(Carta.coppe(8));
		carte.add(Carta.coppe(1));
		carte.add(Carta.coppe(2));
//		for (int i = 0 ; i < 10 ; i++){
//			carte.add(Carta.bastoni(i + 1));
//			carte.add(Carta.spade(i + 1));
//			carte.add(Carta.coppe(i + 1));
//			carte.add(Carta.denara(i + 1));
//		}
//		
//		
		
		System.out.println(carte.size());
		carte.remove(Carta.bastoni(7));
		System.out.println(carte.size());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		String nome = "manuel";
		
		if (nome != null) {
			System.out.println("nome è null");
		}
		Integer num = new Integer(5);
		
		num = Matematica.quadrato(num);
		
		System.out.println("Creo la scatola");
		Scatola s = new Scatola("dado", 5);
		
//		System.out.println(Arrays.toString(s.contenuto));
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
