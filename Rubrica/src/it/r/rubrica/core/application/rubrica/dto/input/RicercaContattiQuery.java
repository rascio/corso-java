package it.r.rubrica.core.application.rubrica.dto.input;

public class RicercaContattiQuery {
	private Integer userId;
	private String testo;

	public RicercaContattiQuery(Integer userId, String testo) {
		this.userId = userId;
		this.testo = testo;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((testo == null) ? 0 : testo.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		RicercaContattiQuery other = (RicercaContattiQuery) obj;
		if (testo == null) {
			if (other.testo != null)
				return false;
		} else if (!testo.equals(other.testo))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RicercaContattiQuery [userId=" + userId + ", testo=" + testo
				+ "]";
	}
	
}