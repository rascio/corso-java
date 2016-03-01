package it.r.corsi.java.lezione4;

import java.util.Objects;

public class Persona {

	private String nome;
	private String cognome;
	private Indirizzo indirizzo;
	
	public Persona() {	
		
	}
	
	public Persona(String nome, String cognome, Indirizzo indirizzo) {
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
	}
	
	public Persona(String cognome, Indirizzo indirizzo) {
		/*
		 * Richiama il costruttore con 3 parametri
		 */
		this("", cognome, indirizzo);
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Indirizzo getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result
				+ ((indirizzo == null) ? 0 : indirizzo.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		
		return Objects.equals(this.nome, other.nome)
			&& Objects.equals(this.cognome, other.cognome)
			&& Objects.equals(this.indirizzo, other.indirizzo);
	}
	@Override
	public String toString() {
		return "Persona [nome=" + nome + ", cognome=" + cognome
				+ ", indirizzo=" + indirizzo + "]";
	}
}
