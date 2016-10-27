package entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pergunta {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@Enumerated(EnumType.STRING)
	private Cliente professor;
	private String pergunta;
	private String correta;
	private String primeiraErrada;
	private String segundaErrada;
	private String terceiraErrada;
	private String quartaErrada;
	private String quintaErrada;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getProfessor() {
		return professor;
	}

	public void setProfessor(Cliente professor) {
		this.professor = professor;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public String getCorreta() {
		return correta;
	}

	public void setCorreta(String correta) {
		this.correta = correta;
	}

	public String getPrimeiraErrada() {
		return primeiraErrada;
	}

	public void setPrimeiraErrada(String primeiraErrada) {
		this.primeiraErrada = primeiraErrada;
	}

	public String getSegundaErrada() {
		return segundaErrada;
	}

	public void setSegundaErrada(String segundaErrada) {
		this.segundaErrada = segundaErrada;
	}

	public String getTerceiraErrada() {
		return terceiraErrada;
	}

	public void setTerceiraErrada(String terceiraErrada) {
		this.terceiraErrada = terceiraErrada;
	}

	public String getQuartaErrada() {
		return quartaErrada;
	}

	public void setQuartaErrada(String quartaErrada) {
		this.quartaErrada = quartaErrada;
	}

	public String getQuintaErrada() {
		return quintaErrada;
	}

	public void setQuintaErrada(String quintaErrada) {
		this.quintaErrada = quintaErrada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Pergunta other = (Pergunta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
