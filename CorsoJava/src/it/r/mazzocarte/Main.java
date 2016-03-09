package it.r.mazzocarte;

public class Main {

	public static void main(String[] args) {
		Mazzo mazzo = Mazzo.crea();

		mazzo.mischia();
		while (mazzo.carteRimanenti() > 0) {
			Carta carta = mazzo.prima();
			System.out.println("La prima carta del mazzo è " + carta);
			mazzo.scarta();
		}
		System.out.println("Le carte sono finite!");
	}
}
