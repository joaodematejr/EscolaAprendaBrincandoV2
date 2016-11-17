package entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Calendario {

	@Id
	@GeneratedValue
	private long id;
	private String titulo;
	private Date inicio;
	private Date fim;
	private String descricao;
	@ManyToOne
	private Turma turma;
	@ManyToOne
	private Ambiente ambiente;
	@ManyToOne
	private Cliente professor;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Ambiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
	}

	public Cliente getProfessor() {
		return professor;
	}

	public void setProfessor(Cliente professor) {
		this.professor = professor;
	}

	public Calendario() {
	}

	public Calendario(long id, String titulo, Date inicio, Date fim, String descricao, Turma turma, Ambiente ambiente,
			Cliente professor) {
		this.id = id;
		this.titulo = titulo;
		this.inicio = inicio;
		this.fim = fim;
		this.descricao = descricao;
		this.turma = turma;
		this.ambiente = ambiente;
		this.professor = professor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Calendario other = (Calendario) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

}
