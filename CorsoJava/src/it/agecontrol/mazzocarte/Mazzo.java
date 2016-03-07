package it.agecontrol.mazzocarte;

public class Mazzo {
	public static Mazzo crea() {
		Carta[] carte = new Carta[40];
		for (int i = 0 ; i < 10 ; i++) {
			carte[i]      = Carta.bastoni(i + 1);
			carte[i + 10] = Carta.coppe(i + 1);
			carte[i + 20] = Carta.denara(i + 1);
			carte[i + 30] = Carta.spade(i + 1);
		}
		return new Mazzo(carte);
	}

	private Carta[] carte;
	
	private Mazzo(Carta[] carte) {
		this.carte = carte;
	}
	
	public void mischia() {
		ArrayUtils.shuffle(this.carte);
	}
	
	public Carta prima() {
		if (this.carte.length == 0) {
			throw new RuntimeException("Le carte sono finite.");
		}
		return this.carte[0];
	}
	
	public void scarta() {
		if (this.carte.length == 0) {
			throw new RuntimeException("Le carte sono finite.");
		}
		Carta[] nuoveCarte = new Carta[this.carte.length - 1];
	
		for (int i = 1 ; i < this.carte.length ; i++) {
			nuoveCarte[i - 1] = this.carte[i];
		}
		
		this.carte = nuoveCarte;
	}
	
	public int carteRimanenti() {
		return this.carte.length;
	}
}
