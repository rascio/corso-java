package it.r.corsi.java.lezione4;

public class Ascensore {

	private final Integer numeroTotalePiani;
	private Integer pianoAttuale;
	
	public Ascensore(Integer numeroTotalePiani) {
		this.numeroTotalePiani = numeroTotalePiani;
		this.pianoAttuale = 0;
	}
	
	public void vaiAlPiano(Integer piano) {
		if (piano == null) {
			throw new IllegalArgumentException("Il piano non può essere null");
		}
		if (piano < 0) {
			throw new IllegalArgumentException("Il piano non può essere negativo [" + piano + "]");
		}
		if (piano > this.numeroTotalePiani) {
			throw new IllegalArgumentException("Il piano non può essere maggiore del numero totale [" + piano + "]");
		}
		
		waitFor(Math.abs(this.pianoAttuale - piano) * 1000);
		
		this.pianoAttuale = piano;
	}
	
	public int getPianoAttuale() {
		return pianoAttuale;
	}
	
	private static void waitFor(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
