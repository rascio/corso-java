package it.r.corsi.java.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class EsercizioMap {

	public static void main(String[] args) {
		
		List<String> laLista = new ArrayList<String>();
		List<String> l = new ListProxy<String>(laLista);
		
		l.size();
		
		Map<String, Integer> mappa = new HashMap<String, Integer>();
		
		mappa.put("primo", 1);
		mappa.put("secondo", 2);
		mappa.put("terzo", 2);
		mappa.put("quarto", 4);
		mappa.put("quarto", 6);
		
		System.out.println(mappa);
		
		Set<String> chiaviDellaMappa = mappa.keySet();
		
		for (String k : chiaviDellaMappa) {
			Integer elemento = mappa.get(k);
			System.out.println("Chiave [" + k + "] elemento [" + elemento + "]");
		}
		
		for (Entry<String, Integer> e : mappa.entrySet()) {
			System.out.println("Chiave [" + e.getKey() + "] elemento [" + e.getValue() + "]");
		}
		
		
		if (mappa.containsKey("primo")) {
			System.out.println("Contiene la chiave primo");
		}
		mappa.remove("primo");
		
		
	}
}
