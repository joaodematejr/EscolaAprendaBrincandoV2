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
	private String turno;
	private String semestre;
	private Date dataInicio;
	private Date dataFinal;
	@ManyToOne
	private Cliente professor;
	@ManyToMany
	private List<Cliente> clienteTurma;
	@ManyToMany
	private List<Cliente> aluno;

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

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
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

	public List<Cliente> getAluno() {
		return aluno;
	}

	public void setAluno(List<Cliente> aluno) {
		this.aluno = aluno;
	}

	public Turma() {

	}

	public Turma(Long id, String nomeTurma, String qAluno, String turno, String semestre, Date dataInicio,
			Date dataFinal, Cliente professor, List<Cliente> clienteTurma, List<Cliente> aluno) {
		this.id = id;
		this.nomeTurma = nomeTurma;
		this.qAluno = qAluno;
		this.turno = turno;
		this.semestre = semestre;
		this.dataInicio = dataInicio;
		this.dataFinal = dataFinal;
		this.professor = professor;
		this.clienteTurma = clienteTurma;
		this.aluno = aluno;
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Turma other = (Turma) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
