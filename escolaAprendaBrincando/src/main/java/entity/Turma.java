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
public class Turma {
	@Id
	@GeneratedValue
	private Long id;
	private String nomeTurma;
	private String qAluno;
	@Enumerated(EnumType.STRING)
	private Turno turno;
	@Enumerated(EnumType.STRING)
	private Semestre semestre;
	private Date dataInicio;
	private Date dataFinal;
	@ManyToOne
	private Cliente professor;
	@ManyToMany
	private List<Cliente> clienteTurma;
	@ManyToMany
	private List<Cliente> aluno;

	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeTurma() {
		return nomeTurma;
	}

	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}

	public String getqAluno() {
		return qAluno;
	}

	public void setqAluno(String qAluno) {
		this.qAluno = qAluno;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Cliente getProfessor() {
		return professor;
	}

	public void setProfessor(Cliente professor) {
		this.professor = professor;
	}

	public List<Cliente> getClienteTurma() {
		return clienteTurma;
	}

	public void setClienteTurma(List<Cliente> clienteTurma) {
		this.clienteTurma = clienteTurma;
	}

}
