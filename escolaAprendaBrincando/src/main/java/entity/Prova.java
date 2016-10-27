package entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Prova {
	@Id
	@GeneratedValue
	private Long id;
	private String observacao;
	private String assunto;
	@Enumerated(EnumType.STRING)
	private Nivel nivel;
	private Date entregar;
	private Date termino;
	@ManyToOne
	private Cliente professor;
	@ManyToMany
	private List<Pergunta> perguntaProva;

	public Long getId() {
		return id;
	}

	public Date getEntregar() {
		return entregar;
	}

	public void setEntregar(Date entregar) {
		this.entregar = entregar;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTermino() {
		return termino;
	}

	public void setTermino(Date termino) {
		this.termino = termino;
	}

	public Cliente getProfessor() {
		return professor;
	}

	public void setProfessor(Cliente professor) {
		this.professor = professor;
	}

	public List<Pergunta> getPerguntaProva() {
		return perguntaProva;
	}

	public void setPerguntaProva(List<Pergunta> perguntaProva) {
		this.perguntaProva = perguntaProva;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entregar == null) ? 0 : entregar.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((perguntaProva == null) ? 0 : perguntaProva.hashCode());
		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
		result = prime * result + ((termino == null) ? 0 : termino.hashCode());
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
		Prova other = (Prova) obj;
		if (entregar == null) {
			if (other.entregar != null)
				return false;
		} else if (!entregar.equals(other.entregar))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (perguntaProva == null) {
			if (other.perguntaProva != null)
				return false;
		} else if (!perguntaProva.equals(other.perguntaProva))
			return false;
		if (professor == null) {
			if (other.professor != null)
				return false;
		} else if (!professor.equals(other.professor))
			return false;
		if (termino == null) {
			if (other.termino != null)
				return false;
		} else if (!termino.equals(other.termino))
			return false;
		return true;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

}
