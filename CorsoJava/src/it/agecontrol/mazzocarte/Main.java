package it.agecontrol.mazzocarte;

public class Main {

	public static void main(String[] args) {
		Mazzo mazzo = Mazzo.crea();
		
		mazzo.mischia();
		while (mazzo.carteRimanenti() > 0) {
			Carta carta = mazzo.prima();
			System.out.println("La prima carta del mazzo è " + carta);
			mazzo.scarta();
		}
//		c.getValore();
//		Carta c1 = new Carta("PICCHE", 5);
//		System.out.println(c.);
	}
	
//	public static void stampaCartaPiuAlta(Carta c1, Carta c2);
//	public static Carta cartaPiuAlta(Carta c1, Carta c2);
}
