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
	private String nivel;
	private Date entregar;
	private Date termino;
	@ManyToOne
	private Cliente professor;
	@ManyToMany
	private List<Pergunta> perguntaProva;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public Date getEntregar() {
		return entregar;
	}

	public void setEntregar(Date entregar) {
		this.entregar = entregar;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assunto == null) ? 0 : assunto.hashCode());
		result = prime * result + ((entregar == null) ? 0 : entregar.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nivel == null) ? 0 : nivel.hashCode());
		result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + ((perguntaProva == null) ? 0 : perguntaProva.hashCode());
		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
		result = prime * result + ((termino == null) ? 0 : termino.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Prova other = (Prova) obj;
		if (assunto == null) {
			if (other.assunto != null) {
				return false;
			}
		} else if (!assunto.equals(other.assunto)) {
			return false;
		}
		if (entregar == null) {
			if (other.entregar != null) {
				return false;
			}
		} else if (!entregar.equals(other.entregar)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (nivel == null) {
			if (other.nivel != null) {
				return false;
			}
		} else if (!nivel.equals(other.nivel)) {
			return false;
		}
		if (observacao == null) {
			if (other.observacao != null) {
				return false;
			}
		} else if (!observacao.equals(other.observacao)) {
			return false;
		}
		if (perguntaProva == null) {
			if (other.perguntaProva != null) {
				return false;
			}
		} else if (!perguntaProva.equals(other.perguntaProva)) {
			return false;
		}
		if (professor == null) {
			if (other.professor != null) {
				return false;
			}
		} else if (!professor.equals(other.professor)) {
			return false;
		}
		if (termino == null) {
			if (other.termino != null) {
				return false;
			}
		} else if (!termino.equals(other.termino)) {
			return false;
		}
		return true;
	}

}
