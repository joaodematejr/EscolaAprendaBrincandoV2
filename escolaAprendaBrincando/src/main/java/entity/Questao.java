package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Questao {
	@Id
	@GeneratedValue
	private Long id;
	private String dificudade;
	private String modalidade;
	private String pergunta;
	private String resposta;
	private String assunto;
	private Date dtPergunta;
	private String comentario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDificudade() {
		return dificudade;
	}

	public void setDificudade(String dificudade) {
		this.dificudade = dificudade;
	}

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Date getDtPergunta() {
		return dtPergunta;
	}

	public void setDtPergunta(Date dtPergunta) {
		this.dtPergunta = dtPergunta;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assunto == null) ? 0 : assunto.hashCode());
		result = prime * result + ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result + ((dificudade == null) ? 0 : dificudade.hashCode());
		result = prime * result + ((dtPergunta == null) ? 0 : dtPergunta.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((modalidade == null) ? 0 : modalidade.hashCode());
		result = prime * result + ((pergunta == null) ? 0 : pergunta.hashCode());
		result = prime * result + ((resposta == null) ? 0 : resposta.hashCode());
		return result;
	}

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
		Questao other = (Questao) obj;
		if (assunto == null) {
			if (other.assunto != null) {
				return false;
			}
		} else if (!assunto.equals(other.assunto)) {
			return false;
		}
		if (comentario == null) {
			if (other.comentario != null) {
				return false;
			}
		} else if (!comentario.equals(other.comentario)) {
			return false;
		}
		if (dificudade == null) {
			if (other.dificudade != null) {
				return false;
			}
		} else if (!dificudade.equals(other.dificudade)) {
			return false;
		}
		if (dtPergunta == null) {
			if (other.dtPergunta != null) {
				return false;
			}
		} else if (!dtPergunta.equals(other.dtPergunta)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (modalidade == null) {
			if (other.modalidade != null) {
				return false;
			}
		} else if (!modalidade.equals(other.modalidade)) {
			return false;
		}
		if (pergunta == null) {
			if (other.pergunta != null) {
				return false;
			}
		} else if (!pergunta.equals(other.pergunta)) {
			return false;
		}
		if (resposta == null) {
			if (other.resposta != null) {
				return false;
			}
		} else if (!resposta.equals(other.resposta)) {
			return false;
		}
		return true;
	}

}