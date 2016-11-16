package json;

import java.util.Date;

import javax.persistence.ManyToOne;

import entity.Ambiente;
import entity.Cliente;
import entity.Turma;

public class CalendarioJson {
	private String titulo;
	private Date inicio;
	private Date fim;
	private Turma nomeTurma;
	private Ambiente ambiente;
	private Cliente professor;

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

	public Turma getNomeTurma() {
		return nomeTurma;
	}

	public void setNomeTurma(Turma nomeTurma) {
		this.nomeTurma = nomeTurma;
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

}
